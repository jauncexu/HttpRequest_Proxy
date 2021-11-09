package com.derry.httpp_request_proxy.network.help;

import com.derry.httpp_request_proxy.network.OkHttpRequest;
import com.derry.httpp_request_proxy.network.VolleyRequest;
import com.derry.httpp_request_proxy.network.callback.ICallback;
import com.derry.httpp_request_proxy.network.http.IHttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

// 业务员
public class HttpHelper implements IHttpRequest {

    // 单例
    private static HttpHelper instance;

    public static HttpHelper obtain() {
        synchronized (HttpHelper.class) {
            if (instance == null) {
                instance = new HttpHelper();
            }
        }
        return instance;
    }

    private HttpHelper() {}

    // 业务员 持有 业主的 钥匙  业主的的信息
    /*OkHttpRequest okHttpRequest;
    VolleyRequest volleyRequest;*/
    // 依赖倒置原则  面向高层 不明显具体细节
    // 定义一个业主，卖房的人
    private static IHttpRequest mIHttpRequest = null;

    // 通过一个API来设置哪一个业主卖出自己的房子，（谁来完成网络访问）
    // 设置 业主1 业主2   Application
    public static void init(IHttpRequest httpProcessor) {
        mIHttpRequest = httpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        // 用户 手抽风 用get方式，调用 post请求？ 就是为了严谨

        // 下面的appendParams函数，为了适配如下get链接情况
        // http://www.aaa.bbb/index?&user=derry&pwd=123456  这种情况要考虑

        // 这种情况也要考虑
        // http://www.aaa.bbb/index
        // &user=derry&pwd=123456
        String finalUrl = appendParams(url, params); // 此函数就是为了适配get方式，拆出来组params
        mIHttpRequest.post(finalUrl, params, callback); // 业主1  业主2 业主3 OkHttp
    }

    @Override
    public void get(String url, ICallback callback) {
        mIHttpRequest.get(url, callback);
    }

    // 下面就是把  get链接里面的参数内容 ，截取出来 封装成 post请求需要的 params参数集
    public static String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        if (urlBuilder.indexOf("?") <= 0) {
            urlBuilder.append("?");
        } else {
            if (!urlBuilder.toString().endsWith("?")) {
                urlBuilder.append("&");
            }
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlBuilder.append("&" + entry.getKey())
                    .append("=")
                    .append(encode(entry.getValue().toString()));
        }
        return urlBuilder.toString();
    }

    private static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
