package cn.com.zhoujiazhen.retrofittest.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by zhoujiazhen on 16/5/26.
 */
public class RetrofitApplication extends Application{
    private final String TAG = "RetrofitApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
