package com.lovehome.fg;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lovehome.R;
import com.lovehome.activity.ActionSheetDialog;
import com.lovehome.adapter.RelTypeAdapter;
import com.lovehome.common.ConstantMethod;
import com.lovehome.util.ToastManageUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by JH on 2016/7/15.
 */
@ContentView(R.layout.frag_release_type)
public class RelTypeFrag extends BaseFragment{
    @ViewInject(R.id.gv_release_type)
    private GridView gridView;

    private RelTypeAdapter relTypeAdapter;

    private Context context;

    private String strArr[] = {"A","B","C","D"};
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();
        context = this.getActivity();
    }

    @Override
    protected void initData() {
        super.initData();
        relTypeAdapter = new RelTypeAdapter(context, ConstantMethod.getReleaseTypeList());
        gridView.setAdapter(relTypeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showBasicNoTitle();
            }
        });
    }

    public void showBasicNoTitle() {
        new ActionSheetDialog(context)
                .builder()
                .setTitle("好友列表")
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem(strArr, ActionSheetDialog.SheetItemColor.Red
                        , new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                //填写事件
                                Log.i("TAG",strArr[which-1]+""+(which-1));
                            }
                        })
                .show();
    }
}
