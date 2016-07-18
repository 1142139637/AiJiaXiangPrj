package com.lovehome.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lovehome.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JH on 2016/7/15.
 */
public class LoveHomeTownFrag extends BaseFragment {

    WebView myWebView;
    //    String loadUrl = "file:///android_assets/index.html";本地
    String netUrl = "http://172.16.46.114:14023";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_hometown,container,false);
//        Unbinder bind = ButterKnife.bind(getActivity());//一定要在 setContentView 之后哈，不然你就等着玩空指针吧
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myWebView = (WebView) view.findViewById(R.id.webview);
        myWebView.loadUrl(netUrl);//加载Url
        //设置WebView
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //得到设置类
        WebSettings ws = myWebView.getSettings();
        ws.setJavaScriptEnabled(true);//允许js脚本
        //把js调用的方法写在WebApp
        myWebView.addJavascriptInterface(new MyWebAppInterface(getActivity()), "android");

    }
}
