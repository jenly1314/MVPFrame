package com.king.mvpframe.mvp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.king.base.adapter.divider.DividerItemDecoration;
import com.king.frame.mvp.base.BindingActivity;
import com.king.mvpframe.R;
import com.king.mvpframe.bean.PoetryInfo;
import com.king.mvpframe.bean.Result;
import com.king.mvpframe.databinding.MainActivityBinding;
import com.king.mvpframe.databinding.RvPoetryItemBinding;
import com.king.mvpframe.mvp.ui.adapter.BindingAdapter;
import com.king.mvpframe.mvp.iview.PoetryView;
import com.king.mvpframe.mvp.presenter.PoetryPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BindingActivity<PoetryView,PoetryPresenter,MainActivityBinding> implements PoetryView {

    private BindingAdapter<PoetryInfo, RvPoetryItemBinding> mAdapter;

    private List<PoetryInfo> listData;


    @Override
    public int getRootViewId() {
        return R.layout.main_activity;
    }

    @Override
    public void initUI() {

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL,R.drawable.list_divider_8));

        listData = new ArrayList<>();
        mAdapter = new BindingAdapter<>(getContext(),listData,R.layout.rv_poetry_item);

        mBinding.recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((v, position) -> {
            PoetryInfo data = mAdapter.getItem(position);
            showDialogFragment(PoetryInfoDialogFragment.newInstance(data));
        });

        mBinding.srl.setOnRefreshListener(() -> getPresenter().getRecommendPoetry());

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
    public void onGetRecommendPoetry(Result<List<PoetryInfo>> result) {
        if(result!=null){
            if(result.isSuccess()){
                mAdapter.refreshData(result.getData());
            }
        }
    }
}
