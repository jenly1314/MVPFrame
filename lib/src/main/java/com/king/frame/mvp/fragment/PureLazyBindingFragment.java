package com.king.frame.mvp.fragment;

import android.databinding.ViewDataBinding;

import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class PureLazyBindingFragment<VDB extends ViewDataBinding> extends LazyBindingFragment<BaseView, BasePresenter<BaseView>,VDB> {

    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>();
    }
}
