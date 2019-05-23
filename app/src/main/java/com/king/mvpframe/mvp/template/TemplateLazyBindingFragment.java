package com.king.mvpframe.mvp.template;

import com.king.frame.mvp.fragment.LazyBindingFragment;
import com.king.mvpframe.R;
import com.king.mvpframe.databinding.TemplateLazyBindingFragmentBinding;

/**
 * LazyBindingFragment模板
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class TemplateLazyBindingFragment extends LazyBindingFragment<TemplateView,TemplatePresenter,TemplateLazyBindingFragmentBinding> {


    @Override
    public int getRootViewId() {
        return R.layout.template_lazy_binding_fragment;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public TemplatePresenter createPresenter() {
        return new TemplatePresenter();
    }
}
