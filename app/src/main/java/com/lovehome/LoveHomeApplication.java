package com.lovehome;

import android.app.Application;
import org.xutils.x;

/**
 * Created by JH on 2016/7/15.
 */
public class LoveHomeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
