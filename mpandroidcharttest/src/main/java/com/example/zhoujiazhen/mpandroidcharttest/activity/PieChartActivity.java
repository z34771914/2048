package com.example.zhoujiazhen.mpandroidcharttest.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.zhoujiazhen.mpandroidcharttest.R;
import com.example.zhoujiazhen.mpandroidcharttest.chart.BasePieChart;
import com.example.zhoujiazhen.mpandroidcharttest.view.MyCustomMarkView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by zhoujiazhen on 16/6/17.
 * <p>
 * 饼图
 */

public class PieChartActivity extends Activity implements OnChartValueSelectedListener {
    private static final String TAG = "PieChartActivity";

    private BasePieChart mPieChart;
    private MyCustomMarkView markView;
    private int[] ids = new int[20];
    private int count = 3;
    private int range = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        initView();
//        initData();
        begin();
    }

    private void begin() {
        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < count + 1; i++) {
            xVals.add(String.valueOf(ids[i % ids.length]));
        }

        ArrayList<String> yVals = new ArrayList<>();

        for (int i = 0; i < count + 1; i ++){
            yVals.add(String.valueOf((Math.random() * range) + range / 5));
        }

        mPieChart.init(this,xVals,yVals,"饼图一");
    }

    private void initView() {
        mPieChart = (BasePieChart) findViewById(R.id.pieChartView);
        //设置点击item时弹窗
        markView = new MyCustomMarkView(this, R.layout.view_my_custom_marker);
        mPieChart.setMarkerView(markView);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
