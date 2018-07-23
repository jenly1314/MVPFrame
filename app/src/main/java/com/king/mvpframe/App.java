package com.king.mvpframe;

import android.app.Application;

import com.king.frame.api.ApiManager;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        ApiManager.init(Constants.BASE_URL);
    }
}
