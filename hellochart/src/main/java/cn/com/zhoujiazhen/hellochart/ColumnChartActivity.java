package cn.com.zhoujiazhen.hellochart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by zhoujiazhen on 16/6/17.
 * <p>
 * 柱状图
 */

public class ColumnChartActivity extends AppCompatActivity implements ColumnChartOnValueSelectListener {
    private ColumnChartView mColumnChartView;

    private int mNumColumns = 8;        //列数
    private int mNumSubColumns = 1;     //子列数

    private ColumnChartData mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_chart);

        initView();
    }

    void initView() {
        mColumnChartView = (ColumnChartView) findViewById(R.id.columnChartView);
        mColumnChartView.setOnValueTouchListener(this);

        generateData();
    }

    void generateData() {

        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;

        for (int i = 0; i < mNumColumns; i++) {
            values = new ArrayList<>();
            for (int j = 0; j < mNumSubColumns; j++)
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));

            Column column = new Column(values);
            column.setHasLabels(true);
            columns.add(column);
        }

        mData = new ColumnChartData(columns);

        //设定坐标轴
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);

        axisX.setName("Axis X");
        axisY.setName("Axis Y");

        //添加坐标轴
        mData.setAxisXBottom(axisX);
        mData.setAxisYLeft(axisY);

        mColumnChartView.setColumnChartData(mData);
    }

    @Override
    public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
        Toast.makeText(this, Math.round(value.getValue()) + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValueDeselected() {

    }
}
