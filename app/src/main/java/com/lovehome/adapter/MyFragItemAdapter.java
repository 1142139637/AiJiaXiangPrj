package com.lovehome.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lovehome.R;
import com.lovehome.bean.MyFragItemBean;

public class MyFragItemAdapter extends BaseAdapter {

    private List<MyFragItemBean> objects ;

    private Context context;
    private LayoutInflater layoutInflater;

    public MyFragItemAdapter(Context context, List<MyFragItemBean> objects) {
        this.context = context;
        this.objects = objects ;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public MyFragItemBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.my_frag_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyFragItemBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MyFragItemBean object, ViewHolder holder) {
            holder.myFragItemImg.setImageResource(object.img);
            holder.myFragItemContext.setText(object.content);
    }

    protected class ViewHolder {
        private ImageView myFragItemImg;
        private TextView myFragItemContext;

        public ViewHolder(View view) {
            myFragItemImg = (ImageView) view.findViewById(R.id.my_frag_item_img);
            myFragItemContext = (TextView) view.findViewById(R.id.my_frag_item_context);
        }
    }
}
