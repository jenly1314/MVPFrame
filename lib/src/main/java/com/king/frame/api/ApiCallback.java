package com.king.frame.api;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public interface ApiCallback<T> {

    void onSubscribe(@NonNull Disposable d);

    void onNext(T result);

    void onError(Throwable e);

    void onCompleted();

}
