package com.king.frame.api;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class ApiObserver<T> implements Observer<T> {

    private ApiCallback<T> mCallback;

    public ApiObserver(ApiCallback<T> callback){
        this.mCallback = callback;
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        Timber.e(e,"onError");
        if(mCallback != null){
            mCallback.onError( e );
        }
    }

    @Override
    public void onNext(T t) {
        Timber.d("Response:" + t);
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

    public static <T> void subscribe(Observable<T> observable, ApiCallback<T> callback){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getApiObserver(callback));
    }

}

