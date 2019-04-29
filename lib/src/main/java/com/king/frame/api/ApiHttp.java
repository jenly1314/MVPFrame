package com.king.frame.api;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;

import com.king.frame.util.SSLSocketFactoryUtils;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
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
    public static final int DEFAULT_TIME_OUT = 15;

    private int mTimeout = DEFAULT_TIME_OUT;

    private String mBaseUrl;

    private OkHttpClient mOkHttpClient;

    private Retrofit mRetrofit;

    private Map<String,Object> mRetrofitServiceCache;


    public ApiHttp(String baseUrl){
        this(baseUrl,DEFAULT_TIME_OUT);
    }

    /**
     *
     * @param baseUrl
     * @param timeout  超时时间 单位/秒
     */
    public ApiHttp(String baseUrl,int timeout){
        this.mBaseUrl = baseUrl;
        this.mTimeout = timeout;
    }

    public Retrofit getRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory( GsonConverterFactory.create())
                    .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient(){
        if(mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(mTimeout, TimeUnit.SECONDS)
                    .readTimeout(mTimeout, TimeUnit.SECONDS)
                    .writeTimeout(mTimeout, TimeUnit.SECONDS)
                    .addInterceptor(new LogInterceptor())
                    .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(),SSLSocketFactoryUtils.createTrustAllManager())
                    .hostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .build();
        }

        return mOkHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    public <T> T getRetrofitService(@NonNull Class<T> service) {
        if(mRetrofitServiceCache == null){
            mRetrofitServiceCache = new HashMap<>();
        }

        T retrofitService = (T)mRetrofitServiceCache.get(service.getCanonicalName());
        if(retrofitService == null){
            synchronized (mRetrofitServiceCache) {
                if(retrofitService == null){
                    retrofitService = getRetrofit().create(service);
                    //缓存
                    mRetrofitServiceCache.put(service.getCanonicalName(),retrofitService);
                }

            }
        }

        return retrofitService;
    }

}
