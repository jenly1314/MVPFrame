package com.king.mvpframe.api;

import com.king.mvpframe.bean.PoetryInfo;
import com.king.mvpframe.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public interface ApiService {

    /**
     * 获取随机十条推荐的诗词
     * @return
     */
    @POST("poetry/poetrys/randomTenPoetry")
    Observable<Result<List<PoetryInfo>>> getRecommendPoetry();


}
