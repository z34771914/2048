package cn.com.zhoujiazhen.secret.activity;

import android.os.Bundle;

import cn.com.zhoujiazhen.secret.R;
import cn.com.zhoujiazhen.secret.base.BaseActivity;

/**
 * Created by zhoujiazhen on 16/5/20.
 *
 * 登录界面
 */
public class LoginActivity extends BaseActivity {
    private final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }
}
