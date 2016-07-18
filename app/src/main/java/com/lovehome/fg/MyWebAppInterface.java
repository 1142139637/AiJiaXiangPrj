package com.lovehome.fg;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by pengdan on 2016/6/27.
 */
public class MyWebAppInterface {
    Context context;

    public MyWebAppInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public String lookData(String data) {
        Log.e("TAG", data + "hello!!");
        //返回信息给html
        return "hello,";
    }
}
