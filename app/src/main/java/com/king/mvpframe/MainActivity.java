package com.king.mvpframe;

import android.support.annotation.NonNull;

import com.king.frame.mvp.base.BindingActivity;
import com.king.mvpframe.bean.PoetryInfo;
import com.king.mvpframe.bean.Result;
import com.king.mvpframe.databinding.MainActivityBinding;
import com.king.mvpframe.mvp.presenter.PoetryPresenter;
import com.king.mvpframe.mvp.view.PoetryView;

public class MainActivity extends BindingActivity<PoetryView,PoetryPresenter,MainActivityBinding> implements PoetryView {
    @Override
    public int getRootViewId() {
        return R.layout.main_activity;
    }

    @Override
    public void initUI() {

        mBinding.srl.setOnRefreshListener(()-> getPresenter().getRecommendPoetry());
    }

    @Override
    public void initData() {
        mBinding.srl.setRefreshing(true);
        getPresenter().getRecommendPoetry();
    }

    @NonNull
    @Override
    public PoetryPresenter createPresenter() {
        return new PoetryPresenter();
    }

    /**
     * 网络请求相应发生错误的时候会调用onError，可在此方法内做相应的处理或提示
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        super.onError(e);
        mBinding.srl.setRefreshing(false);
    }

    /**
     * 网络请求相应完成后会调用onCompleted。
     */
    @Override
    public void onCompleted() {
        super.onCompleted();
        mBinding.srl.setRefreshing(false);
    }

    /**
     * 网络相应结果回调
     * @param result
     */
    @Override
    public void onGetRecommendPoetry(Result<PoetryInfo> result) {
        if(result!=null){
            if(result.isSuccess()){
                mBinding.setData(result.getData());
            }
        }
    }
}
