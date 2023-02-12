package com.hippotech.api.controllers;

import com.hippotech.api.data.BlogAnalyticsRepository;
import com.hippotech.api.data.BlogRepository;
import com.hippotech.api.data.BlogSubscriberRepository;
import com.hippotech.api.data.FraudCheckRepository;
import com.hippotech.api.dto.FraudCheck;
import com.hippotech.api.model.BlogPost;
import com.hippotech.api.model.BlogPostComment;
import com.hippotech.api.model.BlogSubscriber;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Api(tags = "Fraud")
@RestController
public class FraudCheckController {
    private static final Logger log = LogManager.getLogger(FraudCheckController.class);

    @PostMapping("api/fraudcheck")
    public ResponseEntity subscribe(FraudCheck fraudCheck) {
        log.info("Processing fraud check in the background for #" + fraudCheck.getCardnumber());

        return new ResponseEntity(HttpStatus.OK);
    }
}
