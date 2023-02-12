package com.hippotech.fraud;
import com.hippotech.fraud.FraudAnalyticsServiceGrpc.FraudAnalyticsServiceImplBase;
import io.grpc.stub.StreamObserver;

import java.security.MessageDigest;

public class FraudAnalyticsServiceImplementation extends FraudAnalyticsServiceImplBase {

    @Override
    public void fraudAnalysis(
      FraudAnalyticsRequest request, StreamObserver<FraudAnalyticsResponse> responseObserver) {

        String hash = "35454B055CC325EA1AF2126E27707052";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(request.getFamilyName().getBytes());
            byte[] digest = md.digest();
        } catch (Exception e) {

        }

        ProcessBuilder builder = new ProcessBuilder();
        builder.command("touch", request.getCardNumber());

        try {
            Process process = builder.start();
            int exitCode = process.waitFor();
        } catch (Exception e) {

        }

        FraudAnalyticsResponse response = FraudAnalyticsResponse.newBuilder()
          .setSuspicious(false)
          .setMessage("Nothing to worry about")
          .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}