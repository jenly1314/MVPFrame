package com.king.frame.api;

import android.support.annotation.NonNull;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class ApiManager {

    private ApiHttp mApiHttp;

    private static String mBaseUrl;
    private static int mTimeout = ApiHttp.DEFAULT_TIME_OUT;
    private static ApiManager mInstance;

    public static ApiManager getInstance(){
        if(mInstance == null){
            synchronized (ApiManager.class){
                if(mInstance == null){
                    mInstance = new ApiManager();
                }
            }
        }
        return mInstance;
    }

    public static void init(String baseUrl){
        init(baseUrl,ApiHttp.DEFAULT_TIME_OUT);
    }

    public static void init(String baseUrl,int timeout){
        mBaseUrl = baseUrl;
        mTimeout = timeout;

    }

    private ApiManager(){
        mApiHttp = new ApiHttp(mBaseUrl,mTimeout);
    }

    public ApiHttp getApiHttp(){
        return mApiHttp;
    }

    public void setApiHttp(ApiHttp apiHttp){
        this.mApiHttp = apiHttp;
    }

    public <T> T getApiService(@NonNull Class<T> service){
        return mApiHttp.getRetrofitService(service);
    }

}
