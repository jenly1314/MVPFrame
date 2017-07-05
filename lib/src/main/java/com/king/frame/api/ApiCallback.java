package com.king.frame.api;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public interface ApiCallback<T> {

    void onNext(T t);

    void onError(Throwable e);

    void onCompleted();

}
