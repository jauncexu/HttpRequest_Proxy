package com.derry.httpp_request_proxy.network.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

// OKHttp JSON字符串 String字符串 --->  用户指定的 JavaBean
// Gson的功能给做了，自己做的，很简陋
public abstract class HttpCallback<Result> implements ICallback {

    @Override
    public void onSuccess(String result) {
        // String字符串 --->  用户指定的 JavaBean
        // result就是网络访问第三方框架返回的字符串
        // 1.得到调用者用什么样的javaBean接收数据
        Class<?> clz = analysisClassInfo(this);
        // 2.把String result转成对应的javaBean
        Result objResult = (Result) new Gson().fromJson(result, clz);
        // 3.objResult交给程序员
        onSuccess(objResult);
    }

    public abstract void onSuccess(Result objResult);

    private Class<?> analysisClassInfo(Object object) {
        // getGenericSuperclass();
        // 可以得到包含原始类型，参数化类型，数组，类型变量，基本数据类型
        Type getType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) getType).getActualTypeArguments();
        return (Class<?>) params[0]; // <>里面只有一个，所以取0号元素即可
    }

    @Override
    public void onFailure(String e) {

    }
}
