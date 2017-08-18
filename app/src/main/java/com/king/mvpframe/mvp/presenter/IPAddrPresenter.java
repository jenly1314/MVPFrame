package com.king.mvpframe.mvp.presenter;

import com.king.frame.api.SimpleCallback;
import com.king.frame.mvp.base.BasePresenter;
import com.king.mvpframe.api.Api;
import com.king.mvpframe.bean.IPAddress;
import com.king.mvpframe.mvp.view.IIPAddrView;
import com.orhanobut.logger.Logger;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2017/7/5
 */

public class IPAddrPresenter extends BasePresenter<IIPAddrView>{



    public void getIp(String ip){
        Logger.d("query:" + ip);
        getView().showProgress();
        Api.getApiAddr(ip, new SimpleCallback<IPAddress>(getView()) {
            @Override
            public void onNext(IPAddress ipAddress) {
                getView().onGetIPAddr(ipAddress);
            }
        });
    }
}
