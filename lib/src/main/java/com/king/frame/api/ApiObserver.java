package com.king.frame.api;

import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2017/7/3
 */

public class ApiObserver<T> implements Observer<T> {

    private static final String TAG  = "Jenly";

    private ApiCallback<T> mCallback;

    private String mTag = TAG;

    public ApiObserver(ApiCallback<T> callback){
        this.mCallback = callback;
    }

    public ApiObserver(ApiCallback<T> callback,String tag){
        this.mCallback = callback;
        this.mTag = tag;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        Logger.t(mTag).e(e,"onError");
        if(mCallback != null){
            mCallback.onError( e );
        }
    }

    @Override
    public void onNext(T t) {
        Logger.t(mTag).d("Response:" + t);
        if(mCallback != null){
            mCallback.onNext( t );
        }
    }

    @Override
    public void onComplete() {
        if(mCallback != null){
            mCallback.onCompleted();
        }
    }

    public static <T> ApiObserver<T> getApiObserver(ApiCallback<T> callback){
        return new ApiObserver<>( callback );
    }

}
