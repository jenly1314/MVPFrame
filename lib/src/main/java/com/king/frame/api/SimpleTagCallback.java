package com.king.frame.api;

import com.king.frame.mvp.base.TagView;

/**
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
