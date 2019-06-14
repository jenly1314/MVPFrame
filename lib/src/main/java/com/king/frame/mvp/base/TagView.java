package com.king.frame.mvp.base;

/**
 * 支持TAG的{@link BaseView}
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public interface TagView<TAG> extends BaseView {

    void showProgress(TAG tag);

    void onCompleted(TAG tag);

    void onError(Throwable e,TAG tag);

}
