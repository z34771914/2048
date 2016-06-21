package cn.com.zhoujiazhen.secret.network;

import android.os.AsyncTask;

import java.util.Map;

import cn.com.zhoujiazhen.secret.constants.HttpMethod;

/**
 * Created by zhoujiazhen on 16/5/23.
 */
public class RequestTask extends AsyncTask<Void,Void,Void>{

    private String mUrl;
    private Map<String,String> mParams;
    private HttpMethod mHttpMethod = HttpMethod.GET;

    public RequestTask(String url, Map<String,String> params) {
        this(url,params,HttpMethod.GET);
    }

    public RequestTask(String url, Map<String,String> params,HttpMethod method) {
        this.mUrl = url;
        this.mParams = params;
        this.mHttpMethod = method;
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    void sendGetRequest(){

    }

    void sendPostRequest(){

    }
}
