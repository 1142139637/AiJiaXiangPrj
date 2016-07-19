package com.lovehome.fg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
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
    private WebView mWebView;
    private Handler mHandler = new Handler();
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
        mWebView = (WebView) view.findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        mWebView.setWebChromeClient(new MyWebChromeClient());

        mWebView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
        mWebView.loadUrl(netUrl);//加载Url

    }

    final class DemoJavaScriptInterface {//被js调用
        DemoJavaScriptInterface() { }
        @JavascriptInterface//必须要有注解
        public void clickOnAndroid() {//被js掉用的方法   js端的方法名是<a onClick="window.demo.clickOnAndroid()"> 其中demo 是new DemoJavaScriptInterface() 的别名
            mHandler.post(new Runnable() {
                public void run() {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"15874908437"));//拨打电话
                    startActivity(intent);
                }
            });

        }
    }


    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return true;
        }
    }
}
