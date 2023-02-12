package com.hippotech.api.controllers;

import com.hippotech.api.data.ApprovalRepository;
import com.hippotech.api.data.FraudCheckRepository;
import com.hippotech.api.data.HippoUserRepository;
import com.hippotech.api.dto.NewApprovalRequestDto;
import com.hippotech.api.model.ApprovalRequest;
import com.hippotech.api.model.FraudCheck;
import com.hippotech.api.model.MortgageApprovalEvent;
import com.hippotech.api.workflow.AgreementApprovalWorkflow;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.sql.*;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "Mortgage Approval")
@RestController
public class MortgageApprovalController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private HippoUserRepository userRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private FraudCheckRepository fraudCheckRepository;

    @Autowired
    public AgreementApprovalWorkflow workflow;

    @GetMapping("api/approval")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mortage appovals are returned"),
    })
    public ResponseEntity<List<ApprovalRequest>> getAll() {
        List<ApprovalRequest> approvals = approvalRepository.findAll();
        return new ResponseEntity<>(approvals, HttpStatus.OK);
    }

    @DeleteMapping("api/approval/{id}")
    public ResponseEntity<?> withdrawApprovalRequest(@PathVariable Long id) {
      approvalRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("api/approval")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Approval request is submitted for processing."),
            @ApiResponse(responseCode = "400", description = "Invalid mortgage approval request.")
    })
    public ResponseEntity<?> postApprovalRequest(@Valid @RequestBody NewApprovalRequestDto approvalRequestDto) {
  //      String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(("Approval request posted."));

        MortgageApprovalEvent mortgageApprovalEvent = new MortgageApprovalEvent();
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        mortgageApprovalEvent.setDate(dtf1.format(now) + "T" + dtf2.format(now) + ".000Z");
        mortgageApprovalEvent.setEvent("Created");
        mortgageApprovalEvent.setParty("Customer");
        mortgageApprovalEvent.setDetails("Submitted for approval.");
        List<MortgageApprovalEvent> history = new ArrayList<MortgageApprovalEvent>();
        history.add(mortgageApprovalEvent);

        ApprovalRequest approvalRequest = new ApprovalRequest();
        approvalRequest.setAddress1(approvalRequestDto.getAddress1());
        approvalRequest.setStatus("Submitted for Approval");
        approvalRequest.setPurchasePrice(approvalRequestDto.getPurchasePrice());
        approvalRequest.setAmountToBorrow(approvalRequestDto.getAmountToBorrow());
        approvalRequest.setHistory(history);
        approvalRepository.save(approvalRequest);


        workflow.submitForApproval((approvalRequestDto));

        // Write an entry to the Fraud Check database for out-of-band analytics
        FraudCheck fraudCheck = new FraudCheck();
        fraudCheck.setName(approvalRequestDto.getCardName());
        fraudCheck.setLoanValue(approvalRequestDto.getAmountToBorrow());
        fraudCheck.setZip(approvalRequestDto.getZip());
        fraudCheck.setAddress1(approvalRequestDto.getAddress1());
        fraudCheckRepository.save(fraudCheck);

        // First, check that the amount to borrow is not more than the purchase price of the property
        if (approvalRequestDto.getAmountToBorrow() > approvalRequestDto.getPurchasePrice()) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", new Date());
            body.put("status", 400);

            //Get all errors
            List<String> errors = errors = new ArrayList<>();
            errors.add("amountToBorrow cannot be greater than purchasePrice");
            body.put("errors", errors);

            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "-c", "'cat " + approvalRequest.getAddress1() + " > addressLog.txt'" );

        try {
          // List<ApprovalRequest> results = entityManager.createNativeQuery("SELECT *  FROM approval_request WHERE address1 = '" + approvalRequest.getAddress1() + "'").getResultList();
            Process process = builder.start();
            int exitCode = process.waitFor();
        } catch (Exception e) {
            log.warn("Caught exception");
            log.warn(e.toString());
        }

        return new ResponseEntity<>(approvalRequest, HttpStatus.CREATED);
    }
}
