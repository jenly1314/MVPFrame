package com.king.frame.mvp.fragment;

import android.support.annotation.NonNull;

import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;
import com.king.frame.mvp.base.QuickFragment;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class PureFragment extends QuickFragment<BaseView,BasePresenter<BaseView>> {

    @NonNull
    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>();
    }
}
