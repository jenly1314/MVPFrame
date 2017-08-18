package com.king.mvpframe.api;

import com.king.frame.api.ApiManager;
import com.king.frame.api.ApiObserver;
import com.king.frame.api.SimpleCallback;
import com.king.mvpframe.bean.IPAddress;
import com.king.mvpframe.mvp.view.IIPAddrView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class Api {

    private Api(){
        throw new AssertionError();
    }

    private static ApiService getApiService(){
        return ApiManager.getInstance().getApiService(ApiService.class);
    }

    public static void getApiAddr(String ip, SimpleCallback<IPAddress> callback){
        ApiObserver.subscribe(getApiService().getIPAddr(ip),callback);
    }
}
