package com.hippotech.api.controllers;

import com.hippotech.api.dto.FraudCheck;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Heartbeat")
@RestController
public class HeartbeatController {
    private static final Logger log = LogManager.getLogger(HeartbeatController.class);

    @GetMapping("api/heartbeat")
    public ResponseEntity subscribe() {
        log.info("Received heartbeat");

        return new ResponseEntity(HttpStatus.OK);
    }
}
