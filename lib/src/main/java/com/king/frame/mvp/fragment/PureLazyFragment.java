package com.king.frame.mvp.fragment;

import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class PureLazyFragment extends LazyFragment<BaseView, BasePresenter<BaseView>> {


    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>();
    }
}
