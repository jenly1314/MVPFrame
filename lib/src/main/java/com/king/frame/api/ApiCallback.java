package com.king.frame.api;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * API回调接口，
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public interface ApiCallback<T> {

    /**
     * 订阅开始时回调
     * @param d
     */
    void onSubscribe(@NonNull Disposable d);

    /**
     * 成功执行时回调
     * @param result
     */
    void onNext(T result);

    /**
     * 出现错误时回调
     * @param e
     */
    void onError(Throwable e);

    /**
     * 完成时回调
     */
    void onCompleted();

}
