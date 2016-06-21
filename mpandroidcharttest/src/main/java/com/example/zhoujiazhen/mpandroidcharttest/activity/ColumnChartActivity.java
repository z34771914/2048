package com.example.zhoujiazhen.mpandroidcharttest.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.zhoujiazhen.mpandroidcharttest.R;
import com.example.zhoujiazhen.mpandroidcharttest.chart.BaseBarChart;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by zhoujiazhen on 16/6/17.
 * <p>
 * 柱状图
 */

public class ColumnChartActivity extends Activity implements OnChartValueSelectedListener {
    private BaseBarChart mBarChart;

    private String[] mMonths = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec"};
    private int count = 5;
    private int range = 50;
    private List<List<String>> mDatas = new ArrayList<>();
    private List<String> names = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_chart);

        initView();
        begin();

        List<String> data = new ArrayList<>();
        for (int i = 0; i < count; i ++)
            data.add(String.valueOf(Math.random() * (range + 1)));

        mBarChart.addDataSet(data,"第三组");
    }

    private void initView() {
        mBarChart = (BaseBarChart) findViewById(R.id.barChartView);
        mBarChart.setOnChartValueSelectedListener(this);
    }

    private void begin(){
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            labels.add(mMonths[i % 12]);
        }

        List<String> data = new ArrayList<>();
        for (int i = 0; i < count; i ++)
            data.add(String.valueOf(Math.random() * (range + 1)));

        List<String> data1 = new ArrayList<>();
        for (int i = 0; i < count; i ++)
            data1.add(String.valueOf(Math.random() * (range + 1)));

        mDatas.add(data);
        mDatas.add(data1);

        names.add("第一组");
        names.add("第二组");

        mBarChart.init(this,labels,mDatas,names);

        mBarChart.getDataSet(0).setColor(Color.BLACK);
        mBarChart.getDataSet(1).setColor(Color.RED);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
