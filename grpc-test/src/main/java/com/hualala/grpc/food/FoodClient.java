package com.hualala.grpc.food;


import com.hualala.app.shop.document.query.bean.grpc.BeanCommon;
import com.hualala.app.shop.document.query.grpc.ShopDocumentFoodData;
import com.hualala.app.shop.document.query.grpc.ShopDocumentFoodServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;

import java.util.concurrent.ExecutionException;

public class FoodClient {

    private final ShopDocumentFoodServiceGrpc.ShopDocumentFoodServiceBlockingStub blockingStub;
    private final ShopDocumentFoodServiceGrpc.ShopDocumentFoodServiceFutureStub futureStub;

    private ManagedChannel channel;

    public FoodClient(String host, int port){
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
    }

    public FoodClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.maxInboundMessageSize(20971520).intercept(new AccessTokenInterceptor()).build();
        blockingStub = ShopDocumentFoodServiceGrpc.newBlockingStub(channel);
        futureStub = ShopDocumentFoodServiceGrpc.newFutureStub(channel);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FoodClient foodClient = new FoodClient("124.250.35.105", 50051);
        BeanCommon.ShopInfoRequest.Builder builder = BeanCommon.ShopInfoRequest.newBuilder();
        builder.setGroupID(99712L).setShopID(76128066L);
        ShopDocumentFoodData.ShopFoodResponse openFood = foodClient.futureStub
                .withMaxOutboundMessageSize(20971520).withMaxInboundMessageSize(20971520).getOpenFood(builder.build()).get();
        System.out.println(openFood.getResult().getCode());
    }
}
