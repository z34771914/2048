package cn.com.zhoujiazhen.secret.callback;

/**
 * Created by zhoujiazhen on 16/5/20.
 *
 * 网络请求回调
 */
public interface NetworkCallback{
    void onSuccess(String result);

    void onFailed();
}
