package com.king.mvpframe.mvp.view;

import com.king.frame.mvp.base.BaseView;
import com.king.mvpframe.bean.IPAddress;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2017/7/5
 */

public interface IIPAddrView extends BaseView {

    void onGetIPAddr(IPAddress ipAddress);
}
