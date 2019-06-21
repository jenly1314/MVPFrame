package com.king.frame.api;

import com.king.frame.mvp.base.TagView;

/**
 * 带Tag的API回调接口简单实现
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class SimpleTagCallback<T> extends TagCallback<T,Integer> {

    public SimpleTagCallback(TagView iview) {
        super(iview);
    }

    public SimpleTagCallback(TagView iview,Integer tag){
        super(iview,tag);
    }

}
