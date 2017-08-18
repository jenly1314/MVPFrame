package com.king.frame.api;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class ApiHttp {

    /**
     *  默认超时时间 单位/秒
     */
    public static final int DEFAULT_TIME_OUT = 20;

    private int mTimeout = DEFAULT_TIME_OUT;

    private String mBaseUrl;

    private OkHttpClient mOkHttpClient;

    private Retrofit mRetrofit;


    public ApiHttp(String baseUrl){
        this(baseUrl,DEFAULT_TIME_OUT);
    }

    public ApiHttp(String baseUrl,int timeout){
        this(baseUrl,timeout,null);
    }


    public ApiHttp(String baseUrl,OkHttpClient okHttpClient){
        this(baseUrl,DEFAULT_TIME_OUT,okHttpClient);
    }

    /**
     *
     * @param baseUrl
     * @param timeout  超时时间 单位/秒
     * @param okHttpClient
     */
    private ApiHttp(String baseUrl,int timeout,OkHttpClient okHttpClient){
        this.mBaseUrl = baseUrl;
        this.mTimeout = timeout;

        if(okHttpClient == null){
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(mTimeout, TimeUnit.SECONDS)
                    .readTimeout(mTimeout, TimeUnit.SECONDS)
                    .writeTimeout(mTimeout, TimeUnit.SECONDS)
                    .build();
        }else{
            this.mOkHttpClient = okHttpClient;
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }

}
