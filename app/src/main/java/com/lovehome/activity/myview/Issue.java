package com.lovehome.activity.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lovehome.R;
import com.lovehome.activity.BaseActivity;

public class Issue extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);

    }

    @Override
    protected void initView() {
        super.initView();
        findViewById(R.id.aitv);
    }
}
