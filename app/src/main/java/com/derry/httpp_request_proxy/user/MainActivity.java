package com.derry.httpp_request_proxy.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.derry.httpp_request_proxy.R;
import com.derry.httpp_request_proxy.bean.ResponseData;
import com.derry.httpp_request_proxy.network.callback.HttpCallback;
import com.derry.httpp_request_proxy.network.help.HttpHelper;

import java.util.HashMap;

// 客户
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // TODO APP端 买房的人 （刚需）
    public void click(View view) {
        // 公网支持
        String url = "https://v.juhe.cn/historyWeather/citys";
        HashMap<String, Object> params = new HashMap<>();
        // https://v.juhe.cn/historyWeather/citys?&province_id=2&key=bb52107206585ab074f5e59a8c73875b
        params.put("province_id", "2");
        params.put("key", "bb52107206585ab074f5e59a8c73875b");

        HttpHelper.obtain().post(url, params, new HttpCallback<ResponseData>() {
            @Override
            public void onSuccess(ResponseData objResult) {
                Toast.makeText(MainActivity.this, objResult.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}