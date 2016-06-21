package cn.com.zhoujiazhen.retrofittest.listener;

/**
 * Created by zhoujiazhen on 16/5/25.
 */
public interface NetworkListener {
    String noSuchCall = "未找到对应请求，请先初始化该请求";

    void onSuccess(String result);

    void onFailed(String errMsg);
}
