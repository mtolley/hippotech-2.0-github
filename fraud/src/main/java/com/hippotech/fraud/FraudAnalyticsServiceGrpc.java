package com.hippotech.fraud;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: FraudAnalyticsRequest.proto")
public final class FraudAnalyticsServiceGrpc {

  private FraudAnalyticsServiceGrpc() {}

  public static final String SERVICE_NAME = "com.hippotech.fraud.FraudAnalyticsService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.hippotech.fraud.FraudAnalyticsRequest,
      com.hippotech.fraud.FraudAnalyticsResponse> METHOD_FRAUD_ANALYSIS =
      io.grpc.MethodDescriptor.<com.hippotech.fraud.FraudAnalyticsRequest, com.hippotech.fraud.FraudAnalyticsResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.hippotech.fraud.FraudAnalyticsService", "fraudAnalysis"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.hippotech.fraud.FraudAnalyticsRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.hippotech.fraud.FraudAnalyticsResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FraudAnalyticsServiceStub newStub(io.grpc.Channel channel) {
    return new FraudAnalyticsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FraudAnalyticsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FraudAnalyticsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FraudAnalyticsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FraudAnalyticsServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class FraudAnalyticsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void fraudAnalysis(com.hippotech.fraud.FraudAnalyticsRequest request,
        io.grpc.stub.StreamObserver<com.hippotech.fraud.FraudAnalyticsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_FRAUD_ANALYSIS, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_FRAUD_ANALYSIS,
            asyncUnaryCall(
              new MethodHandlers<
                com.hippotech.fraud.FraudAnalyticsRequest,
                com.hippotech.fraud.FraudAnalyticsResponse>(
                  this, METHODID_FRAUD_ANALYSIS)))
          .build();
    }
  }

  /**
   */
  public static final class FraudAnalyticsServiceStub extends io.grpc.stub.AbstractStub<FraudAnalyticsServiceStub> {
    private FraudAnalyticsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FraudAnalyticsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FraudAnalyticsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FraudAnalyticsServiceStub(channel, callOptions);
    }

    /**
     */
    public void fraudAnalysis(com.hippotech.fraud.FraudAnalyticsRequest request,
        io.grpc.stub.StreamObserver<com.hippotech.fraud.FraudAnalyticsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FRAUD_ANALYSIS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FraudAnalyticsServiceBlockingStub extends io.grpc.stub.AbstractStub<FraudAnalyticsServiceBlockingStub> {
    private FraudAnalyticsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FraudAnalyticsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FraudAnalyticsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FraudAnalyticsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.hippotech.fraud.FraudAnalyticsResponse fraudAnalysis(com.hippotech.fraud.FraudAnalyticsRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_FRAUD_ANALYSIS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FraudAnalyticsServiceFutureStub extends io.grpc.stub.AbstractStub<FraudAnalyticsServiceFutureStub> {
    private FraudAnalyticsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FraudAnalyticsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FraudAnalyticsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FraudAnalyticsServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.hippotech.fraud.FraudAnalyticsResponse> fraudAnalysis(
        com.hippotech.fraud.FraudAnalyticsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FRAUD_ANALYSIS, getCallOptions()), request);
    }
  }

  private static final int METHODID_FRAUD_ANALYSIS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FraudAnalyticsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FraudAnalyticsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FRAUD_ANALYSIS:
          serviceImpl.fraudAnalysis((com.hippotech.fraud.FraudAnalyticsRequest) request,
              (io.grpc.stub.StreamObserver<com.hippotech.fraud.FraudAnalyticsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class FraudAnalyticsServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.hippotech.fraud.FraudAnalyticsRequestOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FraudAnalyticsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FraudAnalyticsServiceDescriptorSupplier())
              .addMethod(METHOD_FRAUD_ANALYSIS)
              .build();
        }
      }
    }
    return result;
  }
}
