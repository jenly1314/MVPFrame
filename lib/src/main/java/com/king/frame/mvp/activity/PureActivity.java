package com.king.frame.mvp.activity;

import android.support.annotation.NonNull;

import com.king.frame.mvp.base.BaseActivity;
import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;
import com.king.frame.mvp.base.QuickActivity;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public abstract class PureActivity extends QuickActivity<BaseView,BasePresenter<BaseView>> {


    @NonNull
    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>();
    }
}
