package com.derry.httpp_request_proxy.network;

import com.derry.httpp_request_proxy.app.MyApplication;
import com.derry.httpp_request_proxy.network.callback.ICallback;
import com.derry.httpp_request_proxy.network.http.IHttpRequest;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

// 业主三
// TODO 真实的网络操作（业主，有房的人）
public class XUtilsRequest implements IHttpRequest {

    public XUtilsRequest(MyApplication app) {
        x.Ext.init(app); // XUtils 内部需要持有 application
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        RequestParams requestParams = new RequestParams(url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void get(String url, ICallback callback) {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
