package com.king.mvpframe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.king.frame.mvp.base.QuickActivity;
import com.king.mvpframe.bean.IPAddress;
import com.king.mvpframe.mvp.presenter.IPAddrPresenter;
import com.king.mvpframe.mvp.view.IIPAddrView;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends QuickActivity<IIPAddrView, IPAddrPresenter> implements IIPAddrView {

    @BindView(R.id.etIp)
    EditText etIp;
    @BindView(R.id.btnQuery)
    Button btnQuery;
    @BindView(R.id.tvAddr)
    TextView tvAddr;

    private Toast mToast;



    @NonNull
    @Override
    public IPAddrPresenter createPresenter() {
        return new IPAddrPresenter();
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {
        ButterKnife.bind(this);
        etIp.setRawInputType(InputType.TYPE_CLASS_NUMBER);

        etIp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(EditorInfo.IME_ACTION_DONE == actionId || event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    btnQuery.callOnClick();
                    hideKeyboard(etIp);
                    return true;
                }
                return false;
            }
        });
    }

    //隐藏虚拟键盘
    public static void hideKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) et.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow( et.getApplicationWindowToken( ) , 0 );
        }
    }

    @Override
    public void initData() {
        getPresenter().getIp(null);
    }

    private void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etIp.getText())) {
            return true;
        }

        if (!Pattern.matches(Constants.REG_IP, etIp.getText())) {
            showToast("请输入正确的IP地址");
            return false;
        }
        return true;
    }

    @OnClick(R.id.btnQuery)
    public void onViewClicked() {
        if (checkInput()) {
            getPresenter().getIp(etIp.getText().toString());
        }else{
            tvAddr.setText("");
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        tvAddr.setText("获取失败！");
    }

    @Override
    public void onGetIPAddr(IPAddress ipAddress) {
        tvAddr.setText(ipAddress.getAddr());
    }

}
