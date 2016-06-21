package cn.com.zhoujiazhen.retrofittest.base;

import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.com.zhoujiazhen.retrofittest.constants.RequestMethod;
import cn.com.zhoujiazhen.retrofittest.factory.RetrofitFactory;
import cn.com.zhoujiazhen.retrofittest.listener.NetworkListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhoujiazhen on 16/5/24.
 * <p/>
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final String TAG = "BaseActivity";

    protected Map<String, Call<ResponseBody>> mCallMap = new HashMap<>();
    protected NetworkListener mListener;

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 创建接口请求
     *
     * @param url 请求url
     * @return
     */
    public BaseNetworkService createApi(String url) {
        return RetrofitFactory.createApi(BaseNetworkService.class, url);
    }

    /**
     * 新建一个请求Call<ResponseBody>
     * 不带请求方式参数，默认GET
     *
     * @param service 接口实例
     * @param params  参数集合
     * @param tag     请求标签
     * @return
     */
    public boolean setRequest(BaseNetworkService service, Map<String, String> params, String tag) {
        if (mCallMap.containsKey(tag))
            return false;
        else {
            mCallMap.put(tag, RetrofitFactory.setGetRequest(service, params));
            return true;
        }
    }

    /**
     * 新建一个请求Call<ResponseBody>
     * 带请求方式参数
     *
     * @param service 接口实例
     * @param params  参数集合
     * @param tag     请求标签
     * @param method  请求方法
     * @return
     */
    public boolean setRequest(BaseNetworkService service, Map<String, String> params, String tag,
                              RequestMethod method) {
        if (mCallMap.containsKey(tag))
            return false;
        else {
            if (method.equals(RequestMethod.GET)) {
                mCallMap.put(tag, RetrofitFactory.setGetRequest(service, params));
                return true;
            } else if (method.equals(RequestMethod.POST)) {
                mCallMap.put(tag, RetrofitFactory.setPostRequest(service, params));
                return true;
            } else
                return false;
        }
    }


    /**
     * 设置请求回调
     *
     * @param listener
     */
    public void setListener(NetworkListener listener) {
        mListener = listener;
    }

    /**
     * 加入请求队列
     *
     * @param tag 请求标签，用于查找对应的Call
     * @return
     */
    public boolean startRequest(String tag) {
        Call<ResponseBody> call = mCallMap.get(tag);

        if (call == null) {
            mListener.onFailed(NetworkListener.noSuchCall);
            return false;
        } else {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            if (mListener != null)
                                mListener.onSuccess(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

            return true;
        }
    }

    /**
     * 根据标签获取请求实例
     *
     * @param tag
     * @return
     */
    public Call<ResponseBody> getCall(String tag) {
        if (tag == null) throw new NullPointerException("请求名称为空！");
        return mCallMap.get(tag);
    }

    /**
     * 根据指定标签取消请求
     *
     * @param tag
     * @return
     */
    public boolean cancelRequest(String tag) {
        Call call = mCallMap.remove(tag);

        if (call != null) {
            call.cancel();
            if (call.isCanceled())
                return true;
        }

        return false;
    }

    /**
     * 取消所有请求
     *
     * @return 成功取消返回true, 遇到某个请求无法取消返回false.
     */
    public boolean cancelAllRequest() {
        for (Map.Entry<String, Call<ResponseBody>> callEntry : mCallMap.entrySet()) {
            if (!cancelRequest(callEntry.getKey()))
                return false;
        }
        return true;
    }
}
