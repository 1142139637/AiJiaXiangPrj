package com.lovehome.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.lovehome.R;

import org.xutils.view.annotation.ContentView;

/**
 * Created by JH on 2016/7/16.
 */
@ContentView(R.layout.activity_welcome)
public class LoadActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    @Override
    protected void initView() {
        super.initView();
        // 延迟3秒跳转
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isFirstEnterApp()){
                    startActivity(new Intent(LoadActivity.this,MainActivity.class));
                }else{
                    startActivity(new Intent(LoadActivity.this,GuidePageActivity.class));
                }
            }
        },3000);
    }


    /**
     * 判断用户是否第一次进入APP
     * @return
     */
    private boolean isFirstEnterApp(){
        // 使用SharedPreferences保存第一次进入APP的标志
        SharedPreferences preferences = getSharedPreferences("ENTER_APP_TAG",0);
        // 获取key为FIRST_ENTER_APP_FLAG的value值，如果没有，那么默认为true
        boolean flag = preferences.getBoolean("FIRST_ENTER_APP_FLAG",true);
        if(flag){
            // flag第一次为true，表示用户第一次进入，此时将true改为false
            preferences.edit().putBoolean("FIRST_ENTER_APP_FLAG",false).commit();
            return false;
        }else{
            return true;
        }
    }
}
