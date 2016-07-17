package com.lovehome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.ViewUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.lovehome.util.ToastManageUtil;

import org.xutils.x;

/**
 * Created by JH on 2016/7/15.
 */
public class BaseActivity extends FragmentActivity{
    private long exitTime = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 再按一次退出
     */
    private void exitApp(){
        // 获取当前时间
        long secondTime = System.currentTimeMillis();
        if(secondTime-exitTime>2000){
            ToastManageUtil.showShort(this,"再按一次退出APP");
            // 更新退出时间
            exitTime = secondTime;
        }else{
            this.finish();
        }
    }

    protected void initData(){}

    protected void initView(){}
}
