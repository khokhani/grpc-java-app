package com.kbn.grpc.calculator.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.PrimeNumberDecompositionRequest;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub calcService = CalculatorServiceGrpc.newBlockingStub(channel);

//        SumRequest request = SumRequest.newBuilder()
//                .setFirstNumber(10)
//                .setSecondNumber(25)
//                .build();
//
//        SumResponse response = calcService.sum(request);
//        System.out.println(request.getFirstNumber() + " + " +request.getSecondNumber() +" = " +response.getSumResult());

        long number = 678753244L;

        calcService.primeNumberDecomposition(PrimeNumberDecompositionRequest.newBuilder()
                .setNumber(number).build())
                .forEachRemaining(primeNumberDecompositionResponse -> {
                    System.out.println("Number Decomposition: "+primeNumberDecompositionResponse.getPrimeNumber());
                });

        System.out.println("Shutting Down Channel");
        channel.shutdown();
    }
}
