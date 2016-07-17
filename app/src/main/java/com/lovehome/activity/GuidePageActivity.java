package com.lovehome.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lovehome.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_guidepage)
public class GuidePageActivity extends BaseActivity {

    private int imgArr[] = {R.drawable.guide_one,R.drawable.guide_one,R.drawable.guide_three};
    private List<View> guideFGList;
    @ViewInject(R.id.guide_vp)
    private ViewPager guideVP;

    private LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }


    @Override
    protected void initData() {
        super.initData();
        guideFGList = new ArrayList<>();
        for(int i=0;i<imgArr.length;i++){
            linear = new LinearLayout(this);
            linear.setBackgroundResource(imgArr[i]);
            guideFGList.add(linear);
        }
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return guideFGList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            // 初始化布局
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(guideFGList.get(position));
                return guideFGList.get(position);
            }
            // 销毁布局
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(guideFGList.get(position));
            }
        };

        guideVP.setAdapter(adapter);
        guideVP.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==2){
                    guideFGList.get(position).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(GuidePageActivity.this,MainActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
