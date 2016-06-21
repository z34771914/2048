package cn.com.zhoujiazhen.secret;

import android.content.Intent;
import android.os.Bundle;

import cn.com.zhoujiazhen.secret.activity.LoginActivity;
import cn.com.zhoujiazhen.secret.activity.TimeLineActivity;
import cn.com.zhoujiazhen.secret.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String token = Config.getCachedToken(this);
        if (token != null){
            Intent intent = new Intent(this, TimeLineActivity.class);
            intent.putExtra(Config.KEY_TOKEN,token);
            startActivity(intent);
        }else
            startActivity(new Intent(this, LoginActivity.class));

        finish();
    }
}
