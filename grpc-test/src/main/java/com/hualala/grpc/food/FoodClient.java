package com.hualala.grpc.food;


import com.hualala.app.shop.document.query.bean.grpc.BeanCommon;
import com.hualala.app.shop.document.query.grpc.ShopDocumentFoodData;
import com.hualala.app.shop.document.query.grpc.ShopDocumentFoodServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class FoodClient {

    private final ShopDocumentFoodServiceGrpc.ShopDocumentFoodServiceBlockingStub blockingStub;
    private ManagedChannel channel;

    public FoodClient(String host, int port){
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
    }

    public FoodClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.maxInboundMessageSize(20971520).build();
        blockingStub = ShopDocumentFoodServiceGrpc.newBlockingStub(channel);
    }

    public static void main(String[] args) {
        FoodClient foodClient = new FoodClient("10.32.13.142", 6566);
        BeanCommon.ShopInfoRequest.Builder builder = BeanCommon.ShopInfoRequest.newBuilder();
        builder.setGroupID(1155L).setShopID(76068602L);
        ShopDocumentFoodData.ShopFoodResponse openFood = foodClient.blockingStub
                .withMaxOutboundMessageSize(20971520).withMaxInboundMessageSize(20971520).getOpenFood(builder.build());
        System.out.println(openFood.getResult().getCode());
    }
}
