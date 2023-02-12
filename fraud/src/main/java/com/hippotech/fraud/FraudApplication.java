package com.hippotech.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class FraudApplication {

	public static void main(String[] args) {
    try {
		    Server server = ServerBuilder
          .forPort(4005)
          .addService(new FraudAnalyticsServiceImplementation()).build();

        server.start();
        System.out.println("gRPC Server started on port 4005. ");
        SpringApplication.run(FraudApplication.class, args);
        server.awaitTermination();
    } catch (Exception e) {
        System.out.println("Exception thrown during startup.");
        System.out.println(e.toString());
    }
	}

}
