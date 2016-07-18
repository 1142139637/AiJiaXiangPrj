package com.lovehome.fg;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;

import com.lovehome.R;
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
        Object[] obj = {R.drawable.img_my_fragment_release_left,"我的发布",
                R.drawable.img_my_fragment_draft_left,"我的草稿",
                R.drawable.img_my_fragment_collection_left,"我的收藏",
                R.drawable.img_my_fragment_msg_left,"我的消息"
        };
        addData(obj,mfitlist01);
        adapter01 = new MyFragItemAdapter(getActivity(),mfitlist01) ;
        myFragLv01.setAdapter(adapter01);


        Object[] objs = {R.drawable.img_my_fragment_stament_left,"平台声明",
                R.drawable.img_my_fragment_about_left,"关于\"爱家乡\"",
                R.drawable.img_my_fragment_share_left,"分享软件",
                R.drawable.img_my_fragment_password_left,"修改密码",
                R.drawable.img_my_fragment_buffer_left,"清除缓存"
        };
        addData(objs,mfitlist02);
        adapter02 = new MyFragItemAdapter(getActivity(),mfitlist02) ;
        myFragLv02.setAdapter(adapter02);

    }

    private void addData(Object[] obj, List list){
        for (int i=0;i<obj.length;i++){
            bean = new MyFragItemBean();
            bean.setImg((int)obj[i]);
            bean.setContent((String)obj[i+1]);
            list.add(bean) ;
            Log.e("aaaaaaaaaaaassa",obj[i]+"  "+obj[i+1]);
            i++;
        }
    }

}
