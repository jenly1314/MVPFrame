package com.king.mvpframe.api;

import com.king.mvpframe.bean.PoetryInfo;
import com.king.mvpframe.bean.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public interface ApiService {

    /**
     * 获取推荐诗词
     * @return
     */
    @GET("recommendPoetry")
    Observable<Result<PoetryInfo>> getRecommendPoetry();

}
