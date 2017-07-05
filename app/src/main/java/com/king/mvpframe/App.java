package com.king.mvpframe;

import android.app.Application;

import com.king.frame.api.ApiManager;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class App extends Application {

    private static final String TAG = "Jenly";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        ApiManager.init(Constants.BASE_URL);

        if(BuildConfig.DEBUG){
            Logger.init(TAG);
        }else{
            Logger.init(TAG).logLevel( LogLevel.NONE );
        }
    }
}
