package cn.com.zhoujiazhen.retrofittest.base;

import java.io.Serializable;

/**
 * Created by zhoujiazhen on 16/5/24.
 */
public abstract class BaseModel implements Serializable{
    private final String TAG = "BaseModel";

    public String getTAG() {
        return TAG;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "TAG='" + TAG + '\'' +
                '}';
    }
}
