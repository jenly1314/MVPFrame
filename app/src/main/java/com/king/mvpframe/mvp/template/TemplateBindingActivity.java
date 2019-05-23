package com.king.mvpframe.mvp.template;

import android.support.annotation.NonNull;

import com.king.frame.mvp.base.BindingActivity;
import com.king.mvpframe.R;
import com.king.mvpframe.databinding.TemplateBindingActivityBinding;

/**
 * BindingActivity模板
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class TemplateBindingActivity extends BindingActivity<TemplateView,TemplatePresenter,TemplateBindingActivityBinding> {
    @Override
    public int getRootViewId() {
        return R.layout.template_binding_activity;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public TemplatePresenter createPresenter() {
        return new TemplatePresenter();
    }
}
