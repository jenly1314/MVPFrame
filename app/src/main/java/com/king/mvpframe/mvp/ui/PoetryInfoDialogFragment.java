package com.king.mvpframe.mvp.ui;

import android.os.Bundle;

import com.king.frame.mvp.base.BasePresenter;
import com.king.frame.mvp.base.BaseView;
import com.king.frame.mvp.base.BindingDialogFragment;
import com.king.mvpframe.Constants;
import com.king.mvpframe.R;
import com.king.mvpframe.bean.PoetryInfo;
import com.king.mvpframe.databinding.PoetryInfoDialogFragmentBinding;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class PoetryInfoDialogFragment extends BindingDialogFragment<BaseView, BasePresenter<BaseView>, PoetryInfoDialogFragmentBinding> {

    public static PoetryInfoDialogFragment newInstance(PoetryInfo data) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.KEY_POETRY_INFO,data);
        PoetryInfoDialogFragment fragment = new PoetryInfoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.poetry_info_dialog_fragment;
    }

    @Override
    public void initData() {
        PoetryInfo data = getArguments().getParcelable(Constants.KEY_POETRY_INFO);
        mBinding.setData(data);

        mBinding.btnClose.setOnClickListener(view -> getDialog().dismiss());
    }

    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>();
    }
}