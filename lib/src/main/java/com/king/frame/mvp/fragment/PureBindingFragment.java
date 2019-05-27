package com.king.frame.mvp.fragment;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;
import com.king.frame.mvp.base.BindingFragment;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class PureBindingFragment<VDB extends ViewDataBinding> extends BindingFragment<BaseView, BasePresenter<BaseView>,VDB> {


    @NonNull
    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>();
    }
}