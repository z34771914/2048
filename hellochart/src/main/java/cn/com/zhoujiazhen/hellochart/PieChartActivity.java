package cn.com.zhoujiazhen.hellochart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by zhoujiazhen on 16/6/17.
 *
 * 饼图
 */

public class PieChartActivity extends AppCompatActivity implements PieChartOnValueSelectListener{

    PieChartView mPieChartView;
    PieChartData mPieChartData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        mPieChartView = (PieChartView) findViewById(R.id.pieChartView);
        mPieChartView.setOnValueTouchListener(this);

        generateData();
        prepareDataAnimation();
        mPieChartView.startDataAnimation(1000);
    }


    void generateData(){
        List<SliceValue> values = new ArrayList<>();
        for (int i = 0; i < 6; i ++){
            SliceValue value = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            values.add(value);
        }

        mPieChartData = new PieChartData(values);
        //正常状态下即显示数量
        mPieChartData.setHasLabels(true);
        //点击时显示数量
//        mPieChartData.setHasLabelsOnlyForSelected(true);
        //数字在外部显示
//        mPieChartData.setHasLabelsOutside(true);
        //中间有空心圆
        mPieChartData.setHasCenterCircle(true);
        //每一块之间的间隔
//        mPieChartData.setSlicesSpacing(24);
        //一级标题,位于饼图中间,仅在空心圆存在时成立
        mPieChartData.setCenterText1("hello");
        //二级标题,位于一级标题下方
        mPieChartData.setCenterText2("hello2");


        mPieChartView.setPieChartData(mPieChartData);
    }

    @Override
    public void onValueSelected(int arcIndex, SliceValue value) {
        Toast.makeText(this, value.getValue() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValueDeselected() {

    }

    void prepareDataAnimation(){
        for (SliceValue value : mPieChartData.getValues()){
            value.setTarget((float)Math.random() * 30 + 15);
        }
    }
}
