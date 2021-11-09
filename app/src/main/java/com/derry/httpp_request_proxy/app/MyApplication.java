package com.derry.httpp_request_proxy.app;

import android.app.Application;

import com.derry.httpp_request_proxy.network.OkHttpRequest;
import com.derry.httpp_request_proxy.network.VolleyRequest;
import com.derry.httpp_request_proxy.network.XUtilsRequest;
import com.derry.httpp_request_proxy.network.help.HttpHelper;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initNetworkLib(); // 必须调用
    }

    /**
     * TODO 注意：此项目工程，想启用网络模块封装，就必须调用此函数，否则神仙看了也摇头
     */
    private void initNetworkLib() {
        // TODO 我们的成果就是：多年后，需要更换到OKHttp，只需要修改一行代码即可
        // HttpHelper.init(new VolleyRequest(this));   // 例如：2016年 用这个库
        // HttpHelper.init(new XUtilsRequest(this)); // 例如：2018年 用这个库
        HttpHelper.init(new OkHttpRequest()); // 例如：2021年 用这个库

        // 例如：其他的 SDK版本  1.x  2.x  3.x  4.x 升级的改动，只需要动一行代码
        // 所以，同学们要活学活用哦
    }
}
