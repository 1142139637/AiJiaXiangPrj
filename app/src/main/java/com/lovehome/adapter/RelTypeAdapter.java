package com.lovehome.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lovehome.R;
import com.lovehome.bean.ReleaseTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JH on 2016/7/15.
 */
public class RelTypeAdapter extends BaseAdapter {
    private List<ReleaseTypeBean> releaseTypeList = new ArrayList<>();
    private Context context;

    public RelTypeAdapter(Context context, List<ReleaseTypeBean> releaseTypeList) {
        this.context = context;
        this.releaseTypeList = releaseTypeList;
    }

    @Override
    public int getCount() {
        return releaseTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return releaseTypeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReleaseTypeBean releaseTypeBean = releaseTypeList.get(position);
        RelTypeHolder typeHolder;

        // 优化适配器
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_release_type,parent,false);
            typeHolder = new RelTypeHolder(convertView);
            convertView.setTag(typeHolder);
        }else{
            typeHolder = (RelTypeHolder) convertView.getTag();
        }

        // 设置资源和文本
        typeHolder.relTypeImg.setImageResource(releaseTypeBean.getReleaseTypeImgID());
        typeHolder.relTypeName.setText(releaseTypeBean.getReleaseTypeName());
        return convertView;
    }

    private class RelTypeHolder{
        private ImageView relTypeImg;
        private TextView relTypeName;

        public RelTypeHolder(View convertView) {
            relTypeImg = (ImageView) convertView.findViewById(R.id.iv_item_release_type);
            relTypeName = (TextView) convertView.findViewById(R.id.tv_item_release_type_name);
        }
    }
}
