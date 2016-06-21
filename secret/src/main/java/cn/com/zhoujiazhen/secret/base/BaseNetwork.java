package cn.com.zhoujiazhen.secret.base;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import cn.com.zhoujiazhen.secret.callback.NetworkCallback;
import cn.com.zhoujiazhen.secret.constants.HttpMethod;

/**
 * Created by zhoujiazhen on 16/5/20.
 */
public class BaseNetwork {
    private final String TAG = "BaseNetwork";

    private String mUrl;
    private HttpMethod mMethod;
    private NetworkCallback mCallback;
    private Map<String,String> mParams = new HashMap<>();


    public BaseNetwork(final String url, final HttpMethod method, NetworkCallback callback,
                       final Map<String,String> params){

        this.mCallback = callback;

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... data) {

                    switch (method){
                        case POST:
                            sendPostRequest();
                            break;
                        default:
                            sendGetRequest(url,params);
                            break;
                    }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute();
    }


    void sendGetRequest(String url, Map<String,String> params){

    }

    void sendPostRequest(){

    }
}
