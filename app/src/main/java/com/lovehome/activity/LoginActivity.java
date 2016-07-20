package com.lovehome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.lovehome.R;
import com.lovehome.bean.LoginBean;
import com.lovehome.common.ConstantsData;



public class LoginActivity extends AppCompatActivity {
    private EditText etAccount;
    private EditText etPassword;
    private Button btnLogin;
    String accountText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        etAccount = (EditText) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    public void login(View view){
        accountText = etAccount.getText().toString();
        passwordText = etPassword.getText().toString();
        Log.e("TAG",accountText+passwordText);
        AsyncHttpClient async = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("mobile",accountText);
        params.add("password",passwordText);
        async.get(ConstantsData.LOGIN_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, org.apache.http.Header[] headers, String s, Throwable throwable) {
                Toast.makeText(LoginActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, org.apache.http.Header[] headers, String s) {
                Log.e("TAG",s.toString());
                LoginBean loginInfo = JSON.parseObject(s.toString(),LoginBean.class);
                if (loginInfo.getState()==1){
                    Toast.makeText(LoginActivity.this, loginInfo.getMsg(), Toast.LENGTH_SHORT).show();
                    //登录成功之后回到上一页，还要传值回去
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    Bundle b = new Bundle();
                    b.putString("addr",loginInfo.getData().getU_addr());
                    b.putString("pNumber",loginInfo.getData().getU_phone());
                    b.putInt("name",loginInfo.getData().getU_id());
                    intent.putExtras(b);
                    LoginActivity.this.setResult(2,intent);
//                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, loginInfo.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}
