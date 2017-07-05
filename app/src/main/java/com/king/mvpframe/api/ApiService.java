package com.king.mvpframe.api;

import com.king.mvpframe.bean.IPAddress;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2017/7/5
 */

public interface ApiService {

    @GET("iplookup/iplookup.php?format=json")
    Observable<IPAddress> getIPAddr(@Query("ip") String ip);
}
