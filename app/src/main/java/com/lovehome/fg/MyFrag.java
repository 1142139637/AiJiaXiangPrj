package com.lovehome.fg;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.lovehome.R;
import com.lovehome.activity.myview.AlterPassword;
import com.lovehome.activity.myview.MyCollect;
import com.lovehome.activity.myview.MyInformation;
import com.lovehome.activity.myview.MyIssue;
import com.lovehome.activity.myview.MyManuscript;
import com.lovehome.activity.myview.PlatformStatement;
import com.lovehome.activity.myview.RegardingLoveHometown;
import com.lovehome.adapter.MyFragItemAdapter;
import com.lovehome.bean.MyFragItemBean;
import com.lovehome.util.DataCleanManager;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

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
    View views ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_frag, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        views = view ;
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
        jump(myFragLv01,mfitlist01);//adapter的点击事件
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
        jump(myFragLv02,mfitlist02);//adapter的点击事件
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

    public void jump(ListView view ,final List<MyFragItemBean> list){
        //adapter的点击事件
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyFragItemBean mfib  =  list.get(position);
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

                        break;
                    case 8://修改密码
                        startActivity(new Intent(getActivity(),AlterPassword.class));
                        break;
                    case 9://清除缓存
                        showWindows(views.findViewById(R.id.my_frag_img_id));

                        AlertDialog.Builder clean = new AlertDialog.Builder(getActivity());
                        clean.setTitle("确认清除缓存？");
                        clean.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //调用工具类，清除缓存
                                DataCleanManager.cleanInternalCache(getActivity());
                                Toast.makeText(getActivity(), "清除成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        clean.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        clean.setCancelable(false);
                        clean.show();
                        break;
                    default:
                        break;
                }
            }
        });
    }


    public void showWindows(View views) {
//        //打布局
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popouwindow, null);
//        //找到文本
//        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
//        //文本的触摸事件
//        tv1.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View arg0, MotionEvent arg1) {
//                Toast.makeText(getActivity(), "你好ssssssssssssssss", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        /**
//         * view popup加载的布局视图
//         * LayoutParams.MATCH_PARENT 窗口的宽度
//         *  LayoutParams.WRAP_CONTENT 窗口的高度
//         */
//        PopupWindow pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        pop.setFocusable(true);//是否聚焦
//        pop.setOutsideTouchable(true);//这只为true 点击popup窗口以为的地方  窗口会关闭背景
//        pop.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_getcode_bg));
//        pop.getBackground().setAlpha(100);//设置透明度
//        pop.showAsDropDown(views);
    }

}


