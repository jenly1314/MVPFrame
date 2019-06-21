package com.king.mvpframe.mvp.iview;

import com.king.frame.mvp.base.BaseView;
import com.king.mvpframe.bean.PoetryInfo;
import com.king.mvpframe.bean.Result;

import java.util.List;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2017/7/5
 */

public interface PoetryView extends BaseView {

    void onGetRecommendPoetry(Result<List<PoetryInfo>> result);
}
