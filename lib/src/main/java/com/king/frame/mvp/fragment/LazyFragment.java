package com.king.frame.mvp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;
import com.king.frame.mvp.base.QuickFragment;

/**
 * 懒加载Fragment
 *
 * 说明：需要使用懒加载的几种方式
 *
 * 1. 在{@link Fragment}与{@link ViewPager}一起使用时使用懒加载，通过调用{@link #setUserVisibleHint(boolean)} 来触发懒加载。
 *
 * 2. 在{@link Fragment}通过{@link FragmentTransaction}的{@link FragmentTransaction#show(Fragment)} 和 {@link FragmentTransaction#hide(Fragment)}
 *    来控制显示时使用懒加载，通过调用{@link #onHiddenChanged(boolean)}来触发懒加载。因为初次通过{@link FragmentTransaction#show(Fragment)}并不会触发
 *    {@link #onHiddenChanged(boolean)}，要规避这一点可以在{@link FragmentTransaction#add(int, Fragment)}后，先调用
 *    {@link FragmentTransaction#hide(Fragment)}，然后再调用{@link FragmentTransaction#show(Fragment)}来触发{@link #onHiddenChanged(boolean)}
 *
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
        lazyLoad();
    }

    private void onVisible(){
        isVisible = true;
        lazyLoad();

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
        if(getUserVisibleHint()){
            onVisible();
        }else{
            onInvisible();
        }
    }

    /**
     * 懒加载，触发{@link #onLazyLoad()}
     */
    private void lazyLoad(){
        if(isFirstLoad && isPrepared && isVisible){
            //保证只加载一次
            isFirstLoad = false;
            onLazyLoad();
        }
    }

    public abstract void onLazyLoad();

}
