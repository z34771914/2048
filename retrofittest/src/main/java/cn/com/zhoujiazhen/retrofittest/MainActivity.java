package cn.com.zhoujiazhen.retrofittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;

import cn.com.zhoujiazhen.retrofittest.activity.ObservableScrollviewActivity;
import cn.com.zhoujiazhen.retrofittest.base.BaseActivity;
import cn.com.zhoujiazhen.retrofittest.listener.NetworkListener;


public class MainActivity extends BaseActivity implements NetworkListener, SwipeRefreshLayout.OnRefreshListener {
    private final String TAG = "MainActivity";
    private Button mBtnObservableScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    @Override
    protected void initView() {
        mBtnObservableScrollView = (Button) findViewById(R.id.btnObservableScrollView);
        mBtnObservableScrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ObservableScrollviewActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
//        query();
    }

//    private void query() {
//        Map<String, String> params = new HashMap<>();
//        params.put("num", "10");
//        params.put("rand", "1");
//        params.put("page", "1");
//
//        mBaseNetworkService = createApi(RequestUrl.GET_WEIXIN_HOT);
//
//        setRequest(mBaseNetworkService, params, TAG);
//
//        setListener(this);
//
//        startRequest(TAG);
//    }

    @Override
    public void onSuccess(String result) {
//        Log.e(TAG, "onSuccess: " + result);
//        WeixinHotModel model = new Gson().fromJson(result, WeixinHotModel.class);
//
//        if (model != null) {
//            mEntities.addAll(model.getNewslist());
//
//            mAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void onFailed(String errMsg) {

    }

    @Override
    public void onRefresh() {

    }
}
