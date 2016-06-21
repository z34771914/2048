package com.example.zhoujiazhen.williamchart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;
import com.db.chart.view.Tooltip;

/**
 * Created by zhoujiazhen on 16/6/20.
 */

public class LineChartActivity extends Activity {
    private final String[] mLabels = {"Jan", "Fev", "Mar", "Apr", "Jun", "May", "Jul", "Aug", "Sep"};
    private final float[][] mValues = {{3.5f, 4.7f, 4.3f, 8f, 6.5f, 9.9f, 7f, 8.3f, 7.0f},
            {4.5f, 2.5f, 2.5f, 9f, 4.5f, 9.5f, 5f, 8.3f, 1.8f}};
    private LineChartView mLineChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        initView();
        initData();
    }

    private void initView() {
        mLineChartView = (LineChartView) findViewById(R.id.lineChartView);
    }

    private void initData() {
        LineSet lineSet = new LineSet(mLabels, mValues[0]);
        lineSet.setColor(Color.parseColor("#758cbb"))
//                .setFill(Color.parseColor("#2d374c"))
                .setDotsColor(Color.parseColor("#758cbb"))
                .setThickness(4)
                .setDashed(new float[]{10f, 10f});

        mLineChartView.addData(lineSet);

        Tooltip tooltip = new Tooltip(this, R.layout.tooltip_line_chart);
        mLineChartView.setTooltips(tooltip);

        mLineChartView.showTooltip(tooltip, false);

        mLineChartView.show();
    }
}
