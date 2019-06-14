package com.king.frame.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.king.frame.R;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends MvpActivity<V, P> {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        int layoutId = getRootViewId();
        if(!isContentView(layoutId)){
            setContentView(layoutId);
        }
        initUI();
        initData();

    }

    public <T extends View> T findView(@IdRes int id){
        return (T)findViewById(id);
    }

    public Context getContext(){
        return this;
    }

    public void replaceFragment(Fragment fragmnet){
        replaceFragment( R.id.fragmentContent,fragmnet);
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public abstract @LayoutRes int getRootViewId();

    public abstract void initUI();

    public abstract void initData();

    public abstract boolean isContentView(@LayoutRes int layoutId);

}
