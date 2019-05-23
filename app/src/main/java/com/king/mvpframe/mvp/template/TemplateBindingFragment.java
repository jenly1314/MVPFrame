package com.king.mvpframe.mvp.template;

import com.king.frame.mvp.base.BindingFragment;
import com.king.mvpframe.R;
import com.king.mvpframe.databinding.TemplateBindingFragmentBinding;

/**
 * BindingFragment模板
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class TemplateBindingFragment extends BindingFragment<TemplateView,TemplatePresenter,TemplateBindingFragmentBinding> {
    @Override
    public int getRootViewId() {
        return R.layout.template_binding_fragment;
    }

    @Override
    public void initData() {

    }

    @Override
    public TemplatePresenter createPresenter() {
        return new TemplatePresenter();
    }
}
