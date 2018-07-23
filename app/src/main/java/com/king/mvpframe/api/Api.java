package com.king.mvpframe.api;

import com.king.frame.api.ApiManager;
import com.king.frame.api.ApiObserver;
import com.king.frame.api.SimpleCallback;
import com.king.mvpframe.bean.IPAddress;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class Api {

    private static ApiService apiService;

    private Api(){
        throw new AssertionError();
    }

    private static ApiService getApiService(){
        if(apiService == null){
            apiService = ApiManager.getInstance().getApiService(ApiService.class);
        }
        return apiService;
    }

    public static void getApiAddr(String ip, SimpleCallback<IPAddress> callback){
        ApiObserver.subscribe(getApiService().getIPAddr(ip),callback);
    }
}
