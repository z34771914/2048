package com.example.zhoujiazhen.mpandroidcharttest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.zhoujiazhen.mpandroidcharttest.activity.ColumnChartActivity;
import com.example.zhoujiazhen.mpandroidcharttest.activity.LineChartActivity;
import com.example.zhoujiazhen.mpandroidcharttest.activity.LoginActivity;
import com.example.zhoujiazhen.mpandroidcharttest.activity.PieChartActivity;
import com.example.zhoujiazhen.mpandroidcharttest.view.PointProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button btnLineChart, btnBarChart, btnPieChart;
    private Button btnLoginActivity;
    private PointProgressBar mProgressBar;

    private Intent mIntent;

    private int value = 50;

    private Handler mHandler = new Handler() {
    };

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            value = (int) (Math.random() * 100);

            update();

            mHandler.postDelayed(this, 300);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntent = new Intent();

        btnLineChart = (Button) findViewById(R.id.btnLineChart);
        btnLineChart.setOnClickListener(this);

        btnBarChart = (Button) findViewById(R.id.btnBarChart);
        btnBarChart.setOnClickListener(this);

        btnPieChart = (Button) findViewById(R.id.btnPieChart);
        btnPieChart.setOnClickListener(this);

        btnLoginActivity = (Button) findViewById(R.id.btnLoginActivity);
        btnLoginActivity.setOnClickListener(this);

        mProgressBar = (PointProgressBar) findViewById(R.id.pgProgressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mHandler.postDelayed(mRunnable, 300);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLineChart:
                mIntent.setClass(this, LineChartActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnBarChart:
                mIntent.setClass(this, ColumnChartActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnPieChart:
                mIntent.setClass(this, PieChartActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnLoginActivity:
                mIntent.setClass(this, LoginActivity.class);
                startActivity(mIntent);
                break;
        }
    }

    private void update() {
        if (mProgressBar != null)
            mProgressBar.refresh(value);
    }
}
