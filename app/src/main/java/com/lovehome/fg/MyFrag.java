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

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
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

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
                        share();
                        break;
                    case 8://修改密码
                        startActivity(new Intent(getActivity(),AlterPassword.class));
                        break;
                    case 9://清除缓存
                        NormalDialogStyleTwo();
//                        AlertDialog.Builder clean = new AlertDialog.Builder(getActivity());
//                        clean.setTitle("确认清除缓存？");
//                        clean.setNegativeButton("确认", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                //调用工具类，清除缓存
//                                DataCleanManager.cleanInternalCache(getActivity());
//                                Toast.makeText(getActivity(), "清除成功！", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        clean.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                        clean.setCancelable(false);
//                        clean.show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //分享
    private void share() {
            ShareSDK.initSDK(getActivity());
            OnekeyShare oks = new OnekeyShare();
            //关闭sso授权
            oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
            //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            oks.setTitle("爱家乡");
            // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
            oks.setTitleUrl("http://baidu.cn");
            // text是分享文本，所有平台都需要这个字段
            oks.setText("老乡见老乡，两眼。。。。");
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
//            oks.setUrl("http://sharesdk.cn");
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//            oks.setComment("呵呵");
            // site是分享此内容的网站名称，仅在QQ空间使用
//            oks.setSite(getString(R.string.app_name));
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//            oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
            oks.show(getActivity());
    }

    //清除缓存
    private void NormalDialogStyleTwo() {
        final NormalDialog dialog = new NormalDialog(getActivity());
        dialog.content("确认清除缓存")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
                .showAnim(new BounceTopEnter())//
                .dismissAnim( new SlideBottomExit())//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {//取消
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//确定
                    @Override
                    public void onBtnClick() {
                        DataCleanManager.cleanInternalCache(getActivity());
                        Toast.makeText(getActivity(), "清除成功！", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

    }
}


