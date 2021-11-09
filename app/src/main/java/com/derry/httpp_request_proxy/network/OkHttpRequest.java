package com.derry.httpp_request_proxy.network;

import android.os.Handler;

import com.derry.httpp_request_proxy.network.callback.ICallback;
import com.derry.httpp_request_proxy.network.http.IHttpRequest;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// 业主一
// TODO 真实的网络操作（业主，有房的人）
public class OkHttpRequest implements IHttpRequest {

    private OkHttpClient mOkHttpClient;
    private Handler myHandler; // 此Handler是为了切换到主线程用的，处理成果

    public OkHttpRequest() {
        mOkHttpClient = new OkHttpClient();
        myHandler = new Handler();
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        final RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if (response.isSuccessful()) {
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                } else {
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure(result);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure("onFailure");
                    }
                });
            }
        });
    }

    @Override
    public void get(String url, ICallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if (response.isSuccessful()) {
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                } else {
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure(result);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure("onFailure");
                    }
                });
            }
        });
    }

    // 就是为了post请求的， 参数组装
    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if (params == null || params.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        return body.build();
    }
}
