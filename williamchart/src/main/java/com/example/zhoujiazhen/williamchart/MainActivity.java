package com.example.zhoujiazhen.williamchart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntent = new Intent();

        Button btnLineChart = (Button) findViewById(R.id.btnLineChart);
        btnLineChart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLineChart:
                mIntent.setClass(this,LineChartActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
