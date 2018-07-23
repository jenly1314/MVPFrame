package com.king.mvpframe.mvp.presenter;

import android.support.annotation.NonNull;

import com.king.frame.api.SimpleCallback;
import com.king.frame.mvp.base.BasePresenter;
import com.king.mvpframe.api.Api;
import com.king.mvpframe.bean.IPAddress;
import com.king.mvpframe.mvp.view.IIPAddrView;

import timber.log.Timber;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2017/7/5
 */

public class IPAddrPresenter extends BasePresenter<IIPAddrView>{

    public void getIp(final String ip){

        ifViewAttached(new ViewAction<IIPAddrView>() {
            @Override
            public void run(final @NonNull IIPAddrView view) {
                view.showProgress();
                Api.getApiAddr(ip, new SimpleCallback<IPAddress>(view) {
                    @Override
                    public void onNext(IPAddress ipAddress) {
                        view.onGetIPAddr(ipAddress);
                    }
                });
            }
        });

    }
}
