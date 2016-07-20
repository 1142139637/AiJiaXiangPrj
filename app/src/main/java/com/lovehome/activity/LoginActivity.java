package com.lovehome.activity;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.lovehome.R;
import com.lovehome.bean.LoginBean;
import com.lovehome.common.ConstantsData;
import com.lovehome.common.Util;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


public class LoginActivity extends AppCompatActivity {
    private MultiAutoCompleteTextView etAccount;
    private EditText etPassword;
    private Button mNewLoginButton;
    String accountText, passwordText;
    SharedPreferences share_phone,share_qq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        etAccount = (MultiAutoCompleteTextView) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        mNewLoginButton = (Button) findViewById(R.id.btn_login);
        etAccount.setThreshold(1);
    }

    //手机号登录
    public void login(View view) {
        accountText = etAccount.getText().toString();
        passwordText = etPassword.getText().toString();
        Log.e("TAG", accountText + passwordText);
        AsyncHttpClient async = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("mobile", accountText);
        params.add("password", passwordText);
        async.get(ConstantsData.LOGIN_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, org.apache.http.Header[] headers, String s, Throwable throwable) {
                Toast.makeText(LoginActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, org.apache.http.Header[] headers, String s) {
                Log.e("TAG", s.toString());
                LoginBean loginInfo = JSON.parseObject(s.toString(), LoginBean.class);
                if (loginInfo.getState() == 1) {
                    Toast.makeText(LoginActivity.this, loginInfo.getMsg(), Toast.LENGTH_SHORT).show();
                    //登录成功之后把数据存在SharedPreferences
                    share_phone= getSharedPreferences("user_login_info", MODE_PRIVATE);
                    share_phone.edit().putString("addr", loginInfo.getData().getU_addr())
                            .putString("pNumber", loginInfo.getData().getU_phone())
                            .putInt("name", loginInfo.getData().getU_id())
                            .putString("pwd", loginInfo.getData().getU_pwd())
                            .commit();

                    //登录成功之后回到上一页
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.setResult(2, intent);
//                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, loginInfo.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    //变量
    boolean isServerSideLogin = false;
    public static Tencent mTencent;
    String resultInfo;
    private UserInfo mInfo;

    //点击按钮之后QQ登录
    public void loginQQ(View view){
        //初始化，得到APPID
        mTencent = Tencent.createInstance(ConstantsData.QQ_APP_ID, this);
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            isServerSideLogin = false;
            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", loginListener);
                isServerSideLogin = false;
                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
            updateUserInfo();
        }
    }

    //更新用户信息
    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    //登录成功后应把数据存在SharedPreferences中，再返回上一页
                    new Thread(){
                        @Override
                        public void run() {
                            JSONObject json = (JSONObject)response;
                            if(json.has("figureurl")){
                                Bitmap bitmap = null;
                                try {
                                    bitmap = Util.getbitmap(json.getString("figureurl_qq_2"));
                                    share_qq= getSharedPreferences("user_login_info_qq", MODE_PRIVATE);
                                    share_qq.edit().putString("addr",((JSONObject) response).getString("city"))
                                            .putString("pNumber",((JSONObject) response).getString("nickname"))
                                            .putString("image",json.getString("figureurl_qq_2"))
                                            .commit();

                                    //登录成功之后回到上一页
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    LoginActivity.this.setResult(3, intent);
                                    finish();
                                } catch (JSONException e) {

                                }
                            }
                        }

                    }.start();
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        } else {
            etAccount.setText("");
            etAccount.setVisibility(android.view.View.GONE);
//            mUserLogo.setVisibility(android.view.View.GONE);
        }
    }


    //初始化OPENID和TOKEN值（为了得了用户信息）
    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
        }
    };

    //实现回调
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                Util.showResultDialog(LoginActivity.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                Util.showResultDialog(LoginActivity.this, "返回为空", "登录失败");
                return;
            }
//            Util.showResultDialog(LoginActivity.this, response.toString(), "登录成功");
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            Util.toastMessage(LoginActivity.this, "onError: " + e.errorDetail);
            Util.dismissDialog();
        }

        @Override
        public void onCancel() {
            Util.toastMessage(LoginActivity.this, "onCancel: ");
            Util.dismissDialog();
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }

    //QQ登录后的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG", "-->onActivityResult " + requestCode  + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
