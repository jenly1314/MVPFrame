package com.king.frame.api;

import com.king.frame.mvp.base.TagView;

import io.reactivex.disposables.Disposable;

/**
 * 带Tag的API回调接口，主要用于：在保证统一处理错误的同时，又能通过Tag区分特殊接口，单独处理。
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class TagCallback<T,TAG> implements ApiCallback<T> {

    private TAG tag;

    private TagView mView;

    public TagCallback(TagView iview){
        this(iview,null);
    }

    public TagCallback(TagView iview,TAG tag){
        this.mView = iview;
        this.tag = tag;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        if(tag!=null){
            mView.onError(e,tag);
        }else{
            mView.onError(e);
        }

    }

    @Override
    public void onCompleted() {
        if(tag!=null){
            mView.onCompleted(tag);
        }else{
            mView.onCompleted();
        }

    }
}
