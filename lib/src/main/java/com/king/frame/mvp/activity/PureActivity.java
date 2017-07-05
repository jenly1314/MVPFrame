package com.king.frame.mvp.activity;

import android.support.annotation.NonNull;

import com.king.frame.mvp.base.BaseActivity;
import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public abstract class PureActivity extends BaseActivity<BaseView,BasePresenter<BaseView>> {


    @NonNull
    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<BaseView>();
    }
}
