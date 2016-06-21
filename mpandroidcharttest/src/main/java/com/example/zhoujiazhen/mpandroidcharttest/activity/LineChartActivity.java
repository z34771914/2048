package com.example.zhoujiazhen.mpandroidcharttest.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.zhoujiazhen.mpandroidcharttest.R;
import com.example.zhoujiazhen.mpandroidcharttest.chart.BaseLineChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.renderer.YAxisRendererHorizontalBarChart;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujiazhen on 16/6/17.
 * <p>
 * 折线图
 */

public class LineChartActivity extends Activity implements OnChartValueSelectedListener {
    private BaseLineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        initView();
        initData();
    }

    private void initView() {
        mLineChart = (BaseLineChart) findViewById(R.id.lineChartView);
    }

    private void initData() {

        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            xVals.add(String.valueOf(i));
        }

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 12; i ++)
            data.add(String.valueOf(Math.random() * 10));

        List<String> data1 = new ArrayList<>();
        for (int i = 0; i < 12; i ++)
            data1.add(String.valueOf(Math.random() * 10));

        List<List<String>> datas = new ArrayList<>();
        datas.add(data);
        datas.add(data1);

        List<String> names = new ArrayList<>();
        names.add("图一");
        names.add("图二");

        mLineChart.init(this,xVals,datas,names);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
