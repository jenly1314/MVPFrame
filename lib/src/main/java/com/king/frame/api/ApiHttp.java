package com.king.frame.api;

import android.support.annotation.NonNull;

import com.king.frame.util.SSLSocketFactoryUtils;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.lang.Class;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
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

    private int mTimeout;

    private String mBaseUrl;

    private OkHttpClient mOkHttpClient;

    private Retrofit mRetrofit;

    private Map<String,Object> mRetrofitServiceCache;


    /**
     * 构造
     * @param baseUrl
     */
    public ApiHttp(String baseUrl){
        this(baseUrl,DEFAULT_TIME_OUT);
    }

    /**
     * 构造
     * @param baseUrl
     * @param timeout  超时时间 单位/秒，默认{@link #DEFAULT_TIME_OUT}
     */
    public ApiHttp(String baseUrl,int timeout){
        this.mBaseUrl = baseUrl;
        this.mTimeout = timeout;
    }

    /**
     * 获得{@link Retrofit}
     * @return {@link #mRetrofit}
     */
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

    /**
     * 获得{@link OkHttpClient}
     * @return {@link #mOkHttpClient}
     */
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

    /**
     * 对外暴露方法，提供自定义配置{@link OkHttpClient}
     * @param okHttpClient
     */
    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    /**
     * 对外暴露方法，提供自定义配置{@link Retrofit}
     * @param retrofit
     */
    public void setRetrofit(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    /**
     * 传入Class 通过{@link Retrofit}获得对应的Retrofit service
     * @param service
     * @param <T>
     * @return
     */
    public <T> T getRetrofitService(@NonNull Class<T> service) {
        //通过二次代理获得
        return createWrapperService(service);
    }

    /**
     * 传入Class 通过{@link Retrofit#create(Class)}获得对应的Class
     * @param service
     * @param <T>
     * @return
     */
    private <T> T getService(@NonNull Class<T> service) {
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


    /**
     * 二次动态代理 Retrofit优化  详情参见：https://zhuanlan.zhihu.com/p/40097338
     * @param serviceClass
     * @param <T>
     * @return
     */
    private <T> T createWrapperService(@NonNull Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class<?>[]{serviceClass}, (proxy, method, args) -> {
                    if (method.getReturnType() == Observable.class) {
                        // 如果方法返回值是 Observable 的话，则包一层再返回
                        return Observable.defer(() -> {
                            final T service = getService(serviceClass);
                            // 执行真正的 Retrofit 动态代理的方法
                            return ((Observable) getRetrofitMethod(service, method)
                                    .invoke(service, args));
                        });
                    } else if (method.getReturnType() == Single.class) {
                        // 如果方法返回值是 Single 的话，则包一层再返回
                        return Single.defer(() -> {
                            final T service = getService(serviceClass);
                            // 执行真正的 Retrofit 动态代理的方法
                            return ((Single) getRetrofitMethod(service, method)
                                    .invoke(service, args));
                        });
                    }

                    // 返回值不是 Observable 或 Single 的话不处理
                    final T service = getService(serviceClass);
                    return getRetrofitMethod(service, method).invoke(service, args);
                });
    }

    private <T> Method getRetrofitMethod(T service, Method method) throws NoSuchMethodException {
        return service.getClass().getMethod(method.getName(), method.getParameterTypes());
    }

}
