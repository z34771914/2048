package cn.com.zhoujiazhen.hellochart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by zhoujiazhen on 16/6/17.
 *
 * 折线图
 */

public class LineChartActivity extends AppCompatActivity implements LineChartOnValueSelectListener {

    private int numberOfLines = 1;      //曲线数量
    private int numberOfPoints = 10;    //点数量
    private int maxNumberOfLines = 4;   //最大曲线数量

    private float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    private LineChartView mLineChartView;

    private boolean isCubic = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        initView();

        generateValues();
        generateData();
    }

    void initView(){
        mLineChartView = (LineChartView) findViewById(R.id.lineChartView);
    }

    void generateValues(){
        for (int i = 0; i < maxNumberOfLines; i ++){
            for (int j = 0; j < numberOfPoints; j ++)
                randomNumbersTab[i][j] = (float)Math.random() * 20f;
        }
    }

    void generateData(){
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i ++){
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < numberOfPoints; j ++){
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(Color.BLUE);
            //设置点形状,CIRCLE圆形,DIAMOND菱形,SQUARE正方形
            line.setShape(ValueShape.CIRCLE);
            //值为true时,线型为曲线,值为false时,为折线
            line.setCubic(true);
            //底部填充颜色
//            line.setFilled(true);
            //显示数值
            line.setHasLabels(true);
            //是否显示线,否则为点阵
            line.setHasLines(true);
            //设置点颜色
            line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            //设置点半径
//            line.setPointRadius(10);
            //梯形图
            line.setSquare(false);
            //是否显示点
            line.setHasPoints(true);

            lines.add(line);
        }

        LineChartData data = new LineChartData(lines);
        mLineChartView.setLineChartData(data);

        setAxis(data);

        data.setBaseValue(Float.NEGATIVE_INFINITY);

        if (isCubic){
            Viewport v = new Viewport(mLineChartView.getMaximumViewport());
            v.bottom = -5;
            v.top = 25;
            mLineChartView.setMaximumViewport(v);
            mLineChartView.setCurrentViewportWithAnimation(v);
        }
    }

    /**
     * 添加坐标轴
     * @param data
     */
    private void setAxis(LineChartData data) {
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);

        List<AxisValue> values = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            AxisValue value = new AxisValue(i * 5 - 5);
            values.add(value);
        }

        axisY.setValues(values);

        axisX.setName("Axis X");
        axisY.setName("Axis Y");

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
    }

    @Override
    public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {

    }

    @Override
    public void onValueDeselected() {

    }
}
