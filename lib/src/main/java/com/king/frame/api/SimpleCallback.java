package com.king.frame.api;

import com.king.frame.mvp.base.BaseView;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public abstract class SimpleCallback<T,V extends BaseView> implements ApiCallback<T> {

    private V mView;

    public SimpleCallback(V iview){
        this.mView = iview;
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
