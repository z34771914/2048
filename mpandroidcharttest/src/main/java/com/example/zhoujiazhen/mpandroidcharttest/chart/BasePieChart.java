package com.example.zhoujiazhen.mpandroidcharttest.chart;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.example.zhoujiazhen.mpandroidcharttest.exception.DataSizeZeroException;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujiazhen on 16/6/21.
 * <p>
 * 饼图基类
 */

public class BasePieChart extends PieChart {

    private Context mContext;
    private List<String> mXVals = new ArrayList<>();
    private List<String> mData = new ArrayList<>();
    private List<Entry> mYVals = new ArrayList<>();
    private String mName;

    private PieDataSet mDataSet;
    private PieData mPieData;

    public BasePieChart(Context context) {
        super(context);
    }

    public BasePieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasePieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(Context context, List<String> xVals, List<String> data, String name) {
        this.mContext = context;
        this.mXVals = xVals;
        this.mData = data;
        this.mName = name;

        initChart();

        try {
            buildXVals();
            buildYVals();
            buildDataSet();
            buildPieData();
            bindData();
            animateChart();
        } catch (DataSizeZeroException e) {
            e.printStackTrace();
        }
    }

    private void initChart() {
        //设置描述文字
        setDescription("");
        //显示百分比
        setUsePercentValues(true);
        //是否显示页码
        setDrawSliceText(false);
        //设置四个方向偏移量
        setExtraOffsets(10, 10, 10, 10);
        //设置中心文字
//        mPieChart.setCenterText("饼图");
        //内圆颜色
        setTransparentCircleColor(Color.BLUE);
        //内圆透明度
        setTransparentCircleAlpha(150);
        //内圆半径(百分比>50才有显示,默认55%)
        setTransparentCircleRadius(60f);
        //是否空心
        setDrawHoleEnabled(false);
        //空心圆填充颜色
//        setHoleColor(Color.BLACK);
        //空心圆半径(默认50%)
//        setHoleRadius(20);
        //起始旋转角度(默认270°)
//        setRotationAngle(100f);
        highlightValue(null);
    }

    private void buildXVals() throws DataSizeZeroException {
        if (mXVals == null || mXVals.size() == 0)
            throw new DataSizeZeroException();
    }

    private void buildYVals() throws DataSizeZeroException {
        if (mData == null || mData.size() == 0)
            throw new DataSizeZeroException();
        int length = mData.size();
        for (int i = 0; i < length; i ++)
            mYVals.add(new Entry(Float.valueOf(mData.get(i)),i));
    }

    private void buildDataSet() throws DataSizeZeroException {
        if (mName == null)
            throw new DataSizeZeroException();

        mDataSet = new PieDataSet(mYVals,mName);
        configDataSet();
    }

    private void configDataSet(){
        mDataSet.setSliceSpace(3f);
        mDataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        mDataSet.setColors(colors);
    }

    private void buildPieData(){
        mPieData = new PieData(mXVals,mDataSet);
        configPieData();
    }

    private void configPieData(){
        mPieData.setValueFormatter(new PercentFormatter());
        mPieData.setValueTextColor(Color.BLACK);
    }

    private void bindData(){
        setData(mPieData);
    }

    private void animateChart(){
        animateY(2000);
    }
}
