package com.example.rxjava_mvp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjava_mvp.MVP.Dispsoable.SubscriptionManager;
import com.example.rxjava_mvp.R;
import com.example.rxjava_mvp.tools.ActivityUtil;
import com.example.rxjava_mvp.tools.StatusBarUtils;

public abstract class BaseMvpActivity<T extends BasePersenter>extends AppCompatActivity implements BaseView {
    public T persenter;
    public Context context;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        persenter=initPersenter();
        StatusBarUtils.transparencyBar(this);
        ActivityUtil.getInstance().addActivity(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        persenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        persenter.detattch();
        ActivityUtil.getInstance().removeActivity(this);
        SubscriptionManager.getInstance().cancelall();
        super.onDestroy();

    }
    public BaseMvpActivity getActivity(){
        return this;
    }
    private void closeLoadingDialog(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
    private void showLoadingDialog(){
        if (dialog==null){
            dialog=new ProgressDialog(this);
        }
        dialog.setCancelable(false);
        dialog.show();
    }
    //Activity之间跳转----无需传递参数
    public void openActivity(Class<?> cla){
        Intent intent=new Intent(this,cla);
        startActivity(intent);
        //设置跳转动画
        overridePendingTransition(R.anim.slide_in_right,R.anim.animo_no);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.slide_out_right);
    }
    //设置toast
    public void toast(String text){
        if (text!=null){
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        persenter.onSaveIntanceState(outState);
    }

    //实例化persenter
    public abstract T initPersenter();
    //设置布局
    public abstract int getLayout();
    //初始化
    public abstract void init();


    @Override
    public void showLoading() {
            showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }
    //设置返回按钮的监听事件
    private long exitTime=0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //监听返回键，点击两次退出程序
        if (keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                ActivityUtil.getInstance().exitSystem();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
