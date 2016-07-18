package com.lovehome.activity.myview;
import com.lovehome.R;
import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

public class AlterPassword extends Activity  {

    private TextView forgetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_password);

        forgetpassword = (TextView) findViewById(R.id.forgetpassword);
    }

    private EditText getOldpassword(){
        return (EditText) findViewById(R.id.oldpassword);
    }

    private EditText getNewonepwd(){
        return (EditText) findViewById(R.id.newonepwd);
    }

    private EditText getNewtwopwd(){
        return (EditText) findViewById(R.id.newtwopwd);
    }
}

