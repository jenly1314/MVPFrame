package com.king.frame.mvp.fragment;

import android.databinding.ViewDataBinding;

import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;
import com.king.frame.mvp.base.BindingFragment;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class LazyBindingFragment<V extends BaseView, P extends BasePresenter<V>,VDB extends ViewDataBinding> extends BindingFragment<V,P,VDB> {


    private boolean isVisible;

    private boolean isFirstLoad;

    private boolean isPrepared;

    @Override
    public void initUI() {
        isFirstLoad = true;
        super.initUI();
        isPrepared = true;
    }

    @Override
    public void initData() {
        onLazyLoad();
    }

    private void onVisible(){
        isVisible = true;
        if(isLoad()){
            onLazyLoad();
        }

    }


    private void onInvisible(){
        isVisible = false;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            onInvisible();
        }else{
            onVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            onVisible();
        }else{
            onInvisible();
        }
    }

    private boolean isLoad(){
        if(isFirstLoad && isPrepared && isVisible){
            isFirstLoad = false;
            return true;
        }

        return false;
    }

    public abstract void onLazyLoad();

}
