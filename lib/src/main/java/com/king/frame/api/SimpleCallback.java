package com.king.frame.api;

import com.king.frame.mvp.base.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * 常规实现
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public abstract class SimpleCallback<T> implements ApiCallback<T> {

    private BaseView mView;

    public SimpleCallback(BaseView iview){
        this.mView = iview;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        mView.onError(e);
    }

    @Override
    public void onCompleted() {
        mView.onCompleted();
    }

}
