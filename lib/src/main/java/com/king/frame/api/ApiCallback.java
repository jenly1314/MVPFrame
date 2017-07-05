package com.king.frame.api;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2017/7/3
 */

public interface ApiCallback<T> {

    void onNext(T t);

    void onError(Throwable e);

    void onCompleted();

}
