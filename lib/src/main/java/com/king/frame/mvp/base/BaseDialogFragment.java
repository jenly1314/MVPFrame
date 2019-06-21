package com.king.frame.mvp.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.king.frame.R;

/**
 * MVPFrame的DialogFragment基类
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */


public abstract class BaseDialogFragment<V extends BaseView, P extends BasePresenter<V>> extends MvpDialogFragment<V, P> {

    protected static final float DEFAULT_WIDTH_RATIO = 0.85f;

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        super.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int layoutId = getRootViewId();
        if(!isContentView(layoutId)){
            mRootView = inflater.inflate(layoutId,container,false);
        }
        initUI();
        return mRootView;
    }

    protected View getRootView(){
        return mRootView;
    }

    protected void setRootView(View rootView){
        this.mRootView = rootView;
    }

    /**
     * use {@link #findViewById(int)}
     * @param id
     * @param <T>
     * @return
     */
    @Deprecated
    public <T extends View> T findView(@IdRes int id){
        return findViewById(id);
    }

    public <T extends View> T findViewById(@IdRes int id){
        return (T)mRootView.findViewById(id);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        super.getDialog().getWindow().getAttributes().windowAnimations = R.style.mvpframe_dialog_animation;
        setDialogWindow(getDialog(),DEFAULT_WIDTH_RATIO);

    }

    protected void setDialogWindow(Dialog dialog, float widthRatio){
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int)(getWidthPixels()*widthRatio);
        window.setAttributes(lp);
    }


    //---------------------------------------

    protected DisplayMetrics getDisplayMetrics(){
        return getResources().getDisplayMetrics();
    }

    protected int getWidthPixels(){
        return getDisplayMetrics().widthPixels;
    }

    protected int getHeightPixels(){
        return getDisplayMetrics().heightPixels;
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public void replaceChildFragment(@IdRes int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public abstract int getRootViewId();

    public abstract void initUI();

    public abstract void initData();

    public abstract boolean isContentView(@LayoutRes int layoutId);
}
