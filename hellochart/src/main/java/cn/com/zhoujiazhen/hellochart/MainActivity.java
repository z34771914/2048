package cn.com.zhoujiazhen.hellochart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends Activity implements View.OnClickListener{
    Button btnPieChart,btnLineChart,btnColumnChart;

    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    void initView() {
        btnPieChart = (Button) findViewById(R.id.btnPieChart);
        btnPieChart.setOnClickListener(this);

        btnLineChart = (Button) findViewById(R.id.btnLineChart);
        btnLineChart.setOnClickListener(this);

        btnColumnChart = (Button) findViewById(R.id.btnColumnChart);
        btnColumnChart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPieChart:
                intent.setClass(this,PieChartActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLineChart:
                intent.setClass(this,LineChartActivity.class);
                startActivity(intent);
                break;
            case R.id.btnColumnChart:
                intent.setClass(this,ColumnChartActivity.class);
                startActivity(intent);
                break;
        }
    }
}
