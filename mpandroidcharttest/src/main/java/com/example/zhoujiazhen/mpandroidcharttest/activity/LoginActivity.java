package com.example.zhoujiazhen.mpandroidcharttest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoujiazhen.mpandroidcharttest.R;

/**
 * Created by zhoujiazhen on 16/6/21.
 * <p>
 * 登录Activity
 */

public class LoginActivity extends Activity{
    private EditText etUsername, etPassword, etVerifyCode;
    private Button btnGetVerifyCode, btnLogin;
    private TextView tvForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etVerifyCode = (EditText) findViewById(R.id.etVerifyCode);

        btnGetVerifyCode = (Button) findViewById(R.id.btnGetVerifyCode);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        tvForgetPassword = (TextView) findViewById(R.id.tvForgetPassword);
    }

    public void login(View view) {
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
    }

    public void forgetPassword(View view) {
        Toast.makeText(this, "forgetPassword", Toast.LENGTH_SHORT).show();
    }

    public void getVerifyCode(View view) {
        Toast.makeText(this, "getVerifyCode", Toast.LENGTH_SHORT).show();
    }
}
