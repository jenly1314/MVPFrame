package com.king.frame.mvp.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class BindingFragment<V extends BaseView, P extends BasePresenter<V>,VDB extends ViewDataBinding> extends BaseFragment<V,P> {

    protected VDB mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initUI() {
        mBinding = DataBindingUtil.bind(getRootView());
    }
}
