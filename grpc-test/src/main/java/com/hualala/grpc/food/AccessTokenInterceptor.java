package com.hualala.grpc.food;

import io.grpc.*;

import java.util.UUID;

public class AccessTokenInterceptor implements ClientInterceptor {

    private static final Metadata.Key<String> SERVICE_ACCESS_TOKEN_KEY =
            Metadata.Key.of("serviceAccessToken", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> SHOP_ID_KEY =
            Metadata.Key.of("shopId", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> TRACE_ID_KEY =
            Metadata.Key.of("traceID", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> GROUP_ID_KEY =
            Metadata.Key.of("groupId", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        //仅对需要验证的服务添加meta信息
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) {

            @Override
            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                if (null == headers) {
                    headers = new Metadata();
                }
                headers.put(SERVICE_ACCESS_TOKEN_KEY, "d363afd9-2e4d-42d5-b386-6e65c45d7208");
                headers.put(SHOP_ID_KEY, "76128066");
                headers.put(GROUP_ID_KEY, "99712");
                headers.put(TRACE_ID_KEY, UUID.randomUUID().toString());

                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        switch (status.getCode()) {
                            //如果状态是验证未通过，将当前的HPosInfo置为过期.
                            //serviceAccessToken已过期，需要重新申请。
                            case UNAUTHENTICATED:
                                break;
                            default:
                                break;
                        }
                        super.onClose(status, trailers);
                    }
                }, headers);
            }
        };
    }
}
