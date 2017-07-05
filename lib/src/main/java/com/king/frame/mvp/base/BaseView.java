package com.king.frame.mvp.base;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2017/7/3
 */

public interface BaseView extends MvpView {

    void showProgress();

    void onCompleted();

    void onError(Throwable e);
}
