package com.king.mvpframe.api;

import com.king.frame.api.ApiManager;
import com.king.frame.api.ApiObserver;
import com.king.frame.api.SimpleCallback;
import com.king.mvpframe.bean.PoetryInfo;
import com.king.mvpframe.bean.Result;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class Api {

    private ApiService mApiService;

    private static Api sInstance;

    public static Api getInstance(){
        if(sInstance == null){
            synchronized (Api.class){
                if(sInstance == null){
                    sInstance = new Api();
                }
            }
        }
        return sInstance;
    }

    private Api(){
        mApiService = ApiManager.getInstance().getApiService(ApiService.class);
    }

    /**
     * 获取推荐诗词
     * @param callback
     */
    public void getRecommendPoetry(SimpleCallback<Result<PoetryInfo>> callback){
        ApiObserver.subscribe(mApiService.getRecommendPoetry(),callback);
    }

}
