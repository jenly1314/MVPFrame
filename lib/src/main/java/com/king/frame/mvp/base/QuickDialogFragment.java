package com.king.frame.mvp.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.king.frame.R;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public abstract class QuickDialogFragment<V extends BaseView, P extends BasePresenter<V>> extends BaseDialogFragment<V, P> implements BaseView {

    private Dialog mDialog;

    private Dialog mProgressDialog;

    protected void finish(){
        getActivity().finish();
    }

    protected Intent getIntent(){
        return getActivity().getIntent();
    }

    protected void setIntent(Intent intent){
        getActivity().setIntent(intent);
    }

    protected void setResult(int resultCode){
        getActivity().setResult(resultCode);
    }

    protected void setResult(int resultCode,Intent intent){
        getActivity().setResult(resultCode,intent);
    }

    //---------------------------------------

    protected Intent newIntent(Class<?> cls){
        return new Intent(getContext(),cls);
    }

    protected Intent newIntent(Class<?> cls,int flags){
        Intent intent = newIntent(cls);
        intent.setFlags(flags);
        return intent;
    }

    protected void startActivity(Class<?> cls){
        startActivity(newIntent(cls));
    }

    protected void startActivity(Class<?> cls,int flags){
        startActivity(newIntent(cls,flags));
    }

    protected void startActivityFinish(Class<?> cls){
        startActivity(cls);
        finish();
    }

    protected void startActivityFinish(Class<?> cls,int flags){
        startActivity(cls,flags);
        finish();
    }

    protected void startActivityForResult(Class<?> cls,int requestCode){
        startActivityForResult(newIntent(cls),requestCode);
    }

    //---------------------------------------

    protected View inflate(@LayoutRes int id){
        return inflate(id,null);
    }

    protected View inflate(@LayoutRes int id,@Nullable ViewGroup root){
        return LayoutInflater.from(getContext()).inflate(id,root);
    }

    //---------------------------------------

    protected void showDialogFragment(DialogFragment dialogFragment){
        String tag = dialogFragment.getTag() !=null ? dialogFragment.getTag() : dialogFragment.getClass().getSimpleName();
        showDialogFragment(dialogFragment,tag);
    }

    protected void showDialogFragment(DialogFragment dialogFragment,String tag) {
        dialogFragment.show(getFragmentManager(),tag);
    }

    protected void showDialogFragment(DialogFragment dialogFragment, FragmentManager fragmentManager, String tag) {
        dialogFragment.show(fragmentManager,tag);
    }

    private View.OnClickListener mOnDialogCancelClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismissDialog();

        }
    };

    protected View.OnClickListener getDialogCancelClick(){
        return mOnDialogCancelClick;
    }

    protected void dismissDialog(){
        dismissDialog(mDialog);
    }

    protected void dismissDialog(Dialog dialog){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    protected void dismissPopupWindow(PopupWindow popupWindow){
        if(popupWindow!=null && popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }

    protected void dismissProgressDialog(){
        dismissDialog(mProgressDialog);
    }

    protected void showProgressDialog(){
        showProgressDialog(R.layout.mvpframe_progress_dialog);
    }

    protected void showProgressDialog(@LayoutRes int resId){
        showProgressDialog(inflate(resId));
    }

    protected void showProgressDialog(View v){
        dismissProgressDialog();
        mProgressDialog =  BaseProgressDialog.newInstance(getContext());
        mProgressDialog.setContentView(v);
        mProgressDialog.show();
    }

    protected void showDialog(View contentView){
        showDialog(contentView,DEFAULT_WIDTH_RATIO);
    }

    protected void showDialog(View contentView,float widthRatio){
        showDialog(getContext(),contentView,widthRatio);
    }

    protected void showDialog(Context context,View contentView,float widthRatio){
        showDialog(context,contentView, R.style.mvpframe_dialog,widthRatio);
    }

    protected void showDialog(Context context, View contentView, @StyleRes int resId, float widthRatio){
        showDialog(context,contentView,resId,widthRatio,true);
    }

    protected void showDialog(Context context, View contentView, @StyleRes int resId, float widthRatio,final boolean isCancel){
        dismissDialog();
        mDialog = new Dialog(context,resId);
        mDialog.setContentView(contentView);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    if(isCancel){
                        dismissDialog();
                    }
                    return true;
                }
                return false;

            }
        });
        setDialogWindow(mDialog,widthRatio);
        mDialog.show();

    }


    //---------------------------------------

    @Override
    public boolean isContentView(int layoutId) {
        return false;
    }

    @Override
    public void showProgress(){
        showProgressDialog();
    }

    @Override
    public void onCompleted(){
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e){
        dismissProgressDialog();
    }

}
