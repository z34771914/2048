package cn.com.zhoujiazhen.retrofittest.activity;

import cn.com.zhoujiazhen.retrofittest.R;
import cn.com.zhoujiazhen.retrofittest.base.BaseActivity;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by zhoujiazhen on 16/5/26.
 */

public class ConstraintLayoutTestActivity extends BaseActivity implements View.OnClickListener,
        ObservableScrollViewCallbacks{
    private ObservableListView mObservableListView;
//    private Toolbar mToolbar;
    private PtrFrameLayout mPtrFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observable_listview);

        initView();
        initData();
    }

    @Override
    protected void initView() {
        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.ptr_frame_layout);
        mObservableListView = (ObservableListView) findViewById(R.id.observable_listview);
        mObservableListView.setScrollViewCallbacks(this);

        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,content,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrameLayout.refreshComplete();
                    }
                },2000);
            }
        });

        final StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15),0,0);
        header.initWithString("zhoujiazhen");

//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setLogo(R.mipmap.ic_launcher);
//        mToolbar.setTitle("Title");
//        mToolbar.setSubtitle("SubTitle");
//        setSupportActionBar(mToolbar);
//        mToolbar.setNavigationIcon(android.R.drawable.btn_star_big_on);
    }

    @Override
    protected void initData() {
        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i ++){
            data.add("Item " + i);
        }

        mObservableListView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,data));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
//        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
//        if (scrollState == ScrollState.UP){
//            if (actionbar.isShowing())
//                actionbar.hide();
//        }else if (scrollState == ScrollState.DOWN){
//            if (!actionbar.isShowing())
//                actionbar.show();
//        }
    }
}
