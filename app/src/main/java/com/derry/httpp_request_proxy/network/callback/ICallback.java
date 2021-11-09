package com.derry.httpp_request_proxy.network.callback;

/**
 * 把结果集 请求的 结果 怎么给用户
 */
public interface ICallback {

    void onSuccess(String result); // InputStream,String,Object,T

    void onFailure(String e);

}
