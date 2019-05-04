package com.example.dell.myui.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    private static String baseUrl = "http://10.0.2.2:8080";
    public static String getBaseUrl() {
        return baseUrl;
    }
    public static void postDataWithParame(String url, String key, String value) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add(key, value);//传递键值对参数
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("未能发送成功！！！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.err.println(response.body().string());
                }
            }
        });
    }

    public static void postJsonData(String url, String json) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";//json数据.
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(body)//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("未能发送成功！！！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.err.println(response.body().string());
                }
            }
        });
    }
}
