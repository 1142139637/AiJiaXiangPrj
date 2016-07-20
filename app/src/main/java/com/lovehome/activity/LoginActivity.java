package com.lovehome.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


public class LoginActivity extends AppCompatActivity {
    private MultiAutoCompleteTextView etAccount;
    private EditText etPassword;
    private Button btnLogin;
    String accountText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        etAccount = (MultiAutoCompleteTextView) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        etAccount.setThreshold(1);
    }

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
                    SharedPreferences share = getSharedPreferences("user_login_info", MODE_PRIVATE);
                    share.edit().putString("addr", loginInfo.getData().getU_addr())
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

}
