package com.derry.httpp_request_proxy.network.http;

import com.derry.httpp_request_proxy.network.callback.ICallback;

import java.util.Map;

// 房产公司
public interface IHttpRequest {

    // 有买卖房的能力 平台 标准 规则
    // 网络访问的能力

    void post(String url, Map<String, Object> params, ICallback callback);

    void get(String url, ICallback callback);
}
