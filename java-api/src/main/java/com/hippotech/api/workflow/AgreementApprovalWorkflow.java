package com.hippotech.api.workflow;

import com.hippotech.api.dto.NewApprovalRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


@Component("agreementApprovalWorkflow")
public class AgreementApprovalWorkflow {
//    @Value("${approval.url:#{null}}")
    private String approvalUrl = System.getenv("APPROVAL_URL");
    Logger logger = LoggerFactory.getLogger(AgreementApprovalWorkflow.class);

    public void submitForApproval(NewApprovalRequestDto approvalRequest) {
        logger.info("submitForApproval: ");
        if (this.approvalUrl != null) {
            logger.info(this.approvalUrl);
            try {
                String url = this.approvalUrl + "/approvals";

                MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                map.add("address1", approvalRequest.getAddress1());
                map.add("purchasePrice", Integer.toString(approvalRequest.getPurchasePrice()));
                map.add("amountToBorrow", Integer.toString(approvalRequest.getAmountToBorrow()));
                map.add("cardNumber", approvalRequest.getCardNumber());
                map.add("cardName", approvalRequest.getCardName());

                RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
                logger.info("Ensure change is tested");
                logger.info(map.getFirst("cardNumber"));
                ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class);
            } catch (Exception e) {
                logger.error((e.toString()));
            }
        }
    }
}