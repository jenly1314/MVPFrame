package com.king.frame.mvp.fragment;

import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;
import com.king.frame.mvp.base.QuickFragment;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class LazyFragment<V extends BaseView, P extends BasePresenter<V>> extends QuickFragment<V,P> {


    private boolean isVisible;

    private boolean isFirstLoad;

    private boolean isPrepared;

    @Override
    public void initUI() {
        isFirstLoad = true;
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
