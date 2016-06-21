package cn.com.zhoujiazhen.secret;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhoujiazhen on 16/5/20.
 */
public class Config {

    public static final String AppId = "com.zhoujiazhen.secret";
    public static final String KEY_TOKEN = "token";

    private static Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {}


    /**
     * 获取token
     * @param context
     * @return
     */
    public static String getCachedToken(Context context){
        return context.getSharedPreferences(AppId,Context.MODE_PRIVATE).getString(KEY_TOKEN,null);
    }

    /**
     * 缓存token
     * @param context
     * @param token
     */
    public static void cacheToken(Context context, String token){
        SharedPreferences.Editor editor = context.getSharedPreferences(AppId,Context.MODE_PRIVATE)
                .edit();
        editor.putString(KEY_TOKEN,token);
        editor.commit();
    }
}
