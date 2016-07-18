package com.lovehome.fg;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.lovehome.R;
import com.lovehome.activity.myview.AlterPassword;
import com.lovehome.activity.myview.ClearBuffer;
import com.lovehome.activity.myview.MyCollect;
import com.lovehome.activity.myview.MyInformation;
import com.lovehome.activity.myview.MyIssue;
import com.lovehome.activity.myview.MyManuscript;
import com.lovehome.activity.myview.PlatformStatement;
import com.lovehome.activity.myview.RegardingLoveHometown;
import com.lovehome.activity.myview.ShareSoftware;
import com.lovehome.adapter.MyFragItemAdapter;
import com.lovehome.bean.MyFragItemBean;

import java.util.ArrayList;
import java.util.List;

public class MyFrag extends BaseFragment {

    private ImageView myFragImgId;
    private TextView myFragPhoneId;
    private TextView myFragGoId;
    private ListView myFragLv01;
    private ListView myFragLv02;
    private List<MyFragItemBean> mfitlist01 ;
    private List<MyFragItemBean> mfitlist02 ;
    MyFragItemAdapter adapter01;
    MyFragItemAdapter adapter02;
    MyFragItemBean bean = null ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_frag, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myFragImgId = (ImageView) view.findViewById(R.id.my_frag_img_id);
        myFragPhoneId = (TextView) view.findViewById(R.id.my_frag_phone_id);
        myFragGoId = (TextView) view.findViewById(R.id.my_frag_go_id);
        myFragLv01 = (ListView) view.findViewById(R.id.my_frag_lv_01);
        myFragLv02 = (ListView) view.findViewById(R.id.my_frag_lv_02);
        mfitlist01 = new ArrayList<>();
        mfitlist02 = new ArrayList<>();
        //第一个adapter的数据
        Object[] obj = {R.drawable.img_my_fragment_release_left,"我的发布",1,
                R.drawable.img_my_fragment_draft_left,"我的草稿",2,
                R.drawable.img_my_fragment_collection_left,"我的收藏",3,
                R.drawable.img_my_fragment_msg_left,"我的消息",4
        };
        addData(obj,mfitlist01);
        adapter01 = new MyFragItemAdapter(getActivity(),mfitlist01) ;
        myFragLv01.setAdapter(adapter01);

        //第二个adapter的数据
        Object[] objs = {R.drawable.img_my_fragment_stament_left,"平台声明",5,
                R.drawable.img_my_fragment_about_left,"关于\"爱家乡\"",6,
                R.drawable.img_my_fragment_share_left,"分享软件",7,
                R.drawable.img_my_fragment_password_left,"修改密码",8,
                R.drawable.img_my_fragment_buffer_left,"清除缓存",9
        };
        addData(objs,mfitlist02);
        adapter02 = new MyFragItemAdapter(getActivity(),mfitlist02) ;
        myFragLv02.setAdapter(adapter02);

        //adapter的点击事件
        myFragLv01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyFragItemBean mfib  =  mfitlist01.get(position);
                int ids = mfib.getId();
                switch (ids){
                    case 1://我的发布
                        startActivity(new Intent(getActivity(),MyIssue.class));
                        break;
                    case 2://我的草稿
                        startActivity(new Intent(getActivity(),MyManuscript.class));
                        break;
                    case 3://我的收藏
                        startActivity(new Intent(getActivity(),MyCollect.class));
                        break;
                    case 4://我的消息
                        startActivity(new Intent(getActivity(),MyInformation.class));
                        break;
                    case 5://平台声明
                        startActivity(new Intent(getActivity(),PlatformStatement.class));
                        break;
                    case 6://关于爱家乡
                        startActivity(new Intent(getActivity(),RegardingLoveHometown.class));
                        break;
                    case 7://分享软件
                        startActivity(new Intent(getActivity(),ShareSoftware.class));
                        break;
                    case 8://修改密码
                        startActivity(new Intent(getActivity(),AlterPassword.class));
                        break;
                    case 9://清除缓存
                        startActivity(new Intent(getActivity(),ClearBuffer.class));
                        break;
                    default:
                        break;
                }
            }
        });


    }

    private void addData(Object[] obj, List list){
        for (int i=0;i<obj.length;i++){
            bean = new MyFragItemBean();
            bean.setImg((int)obj[i]);
            bean.setContent((String)obj[i+1]);
            bean.setId((int)obj[i+2]);
            list.add(bean) ;
            i = i+2;
        }
    }

}
