package com.lovehome.activity.myview;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lovehome.R;
import com.lovehome.fg.MyWebAppInterface;

public class Register extends AppCompatActivity {
    private WebView mWebView;
    String netUrl = "http://172.16.46.184:8080/AiJiaXiangPrj/index.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl(netUrl);//加载Url
        //设置WebView
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //得到设置类
        WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);//允许js脚本
        //把js调用的方法写在WebApp
        mWebView.addJavascriptInterface(new MyWebAppInterface(this), "android");
    }
}
