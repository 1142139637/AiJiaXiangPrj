package com.lovehome.common;


import com.lovehome.bean.ReleaseTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JH on 2016/7/15.
 * 放置一些常量方法
 */
public class ConstantMethod {
    public static final List<ReleaseTypeBean> getReleaseTypeList(){
        List<ReleaseTypeBean> releaseTypeBeen = new ArrayList<ReleaseTypeBean>();
        for(int i = 0; i< ConstantsData.relTypeimgArr.length; i++){
            releaseTypeBeen.add(new ReleaseTypeBean(ConstantsData.relTypeimgArr[i], ConstantsData.relTypeNameArr[i]));
        }
        return releaseTypeBeen;
    }
}
