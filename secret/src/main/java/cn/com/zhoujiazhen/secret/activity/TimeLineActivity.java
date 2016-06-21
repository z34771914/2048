package cn.com.zhoujiazhen.secret.activity;

import android.app.ListActivity;
import android.os.Bundle;

import cn.com.zhoujiazhen.secret.R;

/**
 * Created by zhoujiazhen on 16/5/20.
 *
 * 消息列表界面
 */
public class TimeLineActivity extends ListActivity {
    private final String TAG = "TimeLineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timeline);
    }
}
