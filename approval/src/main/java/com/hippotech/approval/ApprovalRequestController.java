package com.hippotech.approval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.hippotech.fraud.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApprovalRequestController {
    private final ApprovalRequestRepository repository;
    private static final Logger log = LoggerFactory.getLogger(ApprovalRequestController.class);
    private String fraudUri = System.getenv("FRAUD_URI");
    private String kafkaUri = System.getenv("KAFKA_URI");

    private final CustomerDetailsProducer producer;

    @Autowired
    ApprovalRequestController(ApprovalRequestRepository repository, CustomerDetailsProducer CustomerDetailsProducer) {
        this.repository = repository;
        this.producer = CustomerDetailsProducer;
    }

    @GetMapping("/approvals")
    List<ApprovalRequest> all() {
        return repository.findAll();
    }

    @GetMapping("/approvals/{id}")
    ApprovalRequest one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ApprovalRequestNotFoundException(id));
    }

    @PostMapping("/approvals")
    ApprovalRequest newApprovalRequest(ApprovalRequest newApprovalRequest) {
        URL url = null;
        HttpURLConnection con = null;

        log.info("Received new request");

        if (newApprovalRequest == null) {
          log.error("Received null ApprovalRequest");
        } else {
          log.error(newApprovalRequest.toString());
        }

        if (this.kafkaUri != null && this.kafkaUri != "") {
          log.info("Submitting request to Credit Check pipeline over Kafka queue.");
          log.info("Kafka on: " + kafkaUri);
          this.producer.sendMessage(newApprovalRequest.getCardNumber());
          log.info("Credit card number sent to Credit Check service over Kafka");
        } else {
          log.info("Credit checking not configured. Please sent KAFKA_URI to Kafka broker to enable.");
        }

        if (this.fraudUri != null && this.fraudUri != "") {
          log.info("Submitting request to Fraud Analytics pipeline over gRPC.");
          log.info("Fraud analytics on: " + fraudUri);
          ManagedChannel channel = ManagedChannelBuilder.forTarget(fraudUri)
                  .usePlaintext()
                  .build();

          FraudAnalyticsServiceGrpc.FraudAnalyticsServiceBlockingStub stub = FraudAnalyticsServiceGrpc.newBlockingStub(channel);

          FraudAnalyticsResponse helloResponse = stub.fraudAnalysis(FraudAnalyticsRequest.newBuilder()
                  .setFamilyName(newApprovalRequest.getCardName())
                  .setCardNumber(newApprovalRequest.getCardNumber())
                  .build());

          System.out.println("Response received from Fraud Analytics:\n" + helloResponse);

          channel.shutdown();


        } else {
          log.info("Fraud Analytics not configured. Please sent FRAUD_URL to gRPC endpoint to enable.");
        }

        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "-c", "echo", newApprovalRequest.getAddress1());

        try {
            Process process = builder.start();
            int exitCode = process.waitFor();
        } catch (Exception e) {

        }

        return repository.save(newApprovalRequest);
    }

    @PutMapping("/approvals/{id}")
    ApprovalRequest replaceApprovalRequest(ApprovalRequest approvalRequest, @PathVariable Long id) {
        return repository.save(approvalRequest);
    }

    @DeleteMapping("/appovals/{id}")
    void deleteApprovalRequest(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
