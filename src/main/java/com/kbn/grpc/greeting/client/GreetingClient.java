package com.kbn.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 */
public class GreetingClient {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("gRPC Client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                                .usePlaintext()
                                .build();

        System.out.println("Creating stubs");

        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        //Unary service
        //Build Greeting Message
//        Greeting greeting = Greeting.newBuilder()
//                .setFirstName("BharatKumar")
//                .setLastName("Khokhani")
//                .build();
//
//        //Build Greet Request
//        GreetRequest greetRequest = GreetRequest.newBuilder().setGreeting(greeting).build();
//
//        //call rpc function
//        GreetResponse greetResponse = greetClient.greet(greetRequest);
//        System.out.println("Result: "+ greetResponse.getResult());

        //Server Streaming

        greetClient.greetManyTimes(GreetManyTimesRequest.newBuilder().setGreeting(Greeting.newBuilder().setFirstName("BharatKumar").build()).build())
                .forEachRemaining(greetManyTimesResponse -> {
                    System.out.println(greetManyTimesResponse.getResult());
            });

        System.out.println("Shutting Down Channel");
        channel.shutdown();

    }
}
