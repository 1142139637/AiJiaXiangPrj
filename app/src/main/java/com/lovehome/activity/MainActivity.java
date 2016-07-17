package com.lovehome.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.lovehome.R;
import com.lovehome.fg.LoveHomeTownFrag;
import com.lovehome.fg.MyFrag;
import com.lovehome.fg.RelTypeFrag;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.main_vp)
    private ViewPager mainVP;

    // 底部导航栏的布局
    @ViewInject(R.id.linear_lovehome)
    private LinearLayout linearLV;
    @ViewInject(R.id.linear_release)
    private LinearLayout linearRel;
    @ViewInject(R.id.linear_my)
    private LinearLayout linearMy;

    // 碎片集合
    private List<Fragment> fragmentList;

    // 碎片管理器
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void initData() {
        super.initData();
        fragmentList = new ArrayList<>();
        fragmentList.add(new LoveHomeTownFrag());
        fragmentList.add(new RelTypeFrag());
        fragmentList.add(new MyFrag());

        // 适配器
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        mainVP.setAdapter(pagerAdapter);
        mainVP.setCurrentItem(0);
        linearLV.setSelected(true);
        // 设置导航栏上的小布局点击事件
        linearLV.setOnClickListener(this);
        linearRel.setOnClickListener(this);
        linearMy.setOnClickListener(this);

    }
    @Override
    protected void initView() {
        super.initView();
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View v) {
        linearLV.setSelected(false);
        linearRel.setSelected(false);
        linearMy.setSelected(false);
        switch (v.getId()){
            case R.id.linear_lovehome:
                linearLV.setSelected(true);
                mainVP.setCurrentItem(0);
                break;
            case R.id.linear_release:
                linearRel.setSelected(true);
                mainVP.setCurrentItem(1);
                break;
            case R.id.linear_my:
                linearMy.setSelected(true);
                mainVP.setCurrentItem(2);
                break;
        }
    }
}
