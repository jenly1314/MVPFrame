package com.king.mvpframe.mvp.presenter;

import com.king.frame.api.SimpleCallback;
import com.king.frame.mvp.base.BasePresenter;
import com.king.mvpframe.api.Api;
import com.king.mvpframe.bean.PoetryInfo;
import com.king.mvpframe.bean.Result;
import com.king.mvpframe.mvp.iview.PoetryView;

import java.util.List;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2017/7/5
 */
public class PoetryPresenter extends BasePresenter<PoetryView>{


    /**
     * 获取推荐诗词
     */
    public void getRecommendPoetry(){
        ifViewAttached(view -> {
//            view.showProgress();
            Api.getInstance().getRecommendPoetry(new SimpleCallback<Result<List<PoetryInfo>>>(view) {
                @Override
                public void onNext(Result<List<PoetryInfo>> result) {
                    view.onGetRecommendPoetry(result);
                }
            });
        });

    }
}
