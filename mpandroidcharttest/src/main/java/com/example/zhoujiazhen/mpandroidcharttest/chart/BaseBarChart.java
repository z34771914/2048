package com.example.zhoujiazhen.mpandroidcharttest.chart;

import android.content.Context;
import android.util.AttributeSet;

import com.example.zhoujiazhen.mpandroidcharttest.exception.DataSizeZeroException;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujiazhen on 16/6/20.
 * <p>
 * 柱状图基类
 */

public class BaseBarChart extends BarChart {

    private Context mContext;

    private List<String> mLabels = new ArrayList<>();
    private List<List<String>> mDatas = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();

    private List<IBarDataSet> mDataSets = new ArrayList<>();
    private BarData mBarData;

    public BaseBarChart(Context context) {
        super(context);
    }

    public BaseBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 初始化,传入数据
     * @param context 上下文
     * @param labels X轴刻度
     * @param datas 数据点
     * @param dataNames 每组数据名称
     */
    public void init(Context context, List<String> labels, List<List<String>> datas, List<String> dataNames) {
        clearData();

        this.mContext = context;
        this.mLabels = labels;
        this.mDatas = datas;
        this.mNames = dataNames;

        initChart();

        try {
            buildXVals();
            buildYVals();
        } catch (DataSizeZeroException e) {
            e.printStackTrace();
        }

        buildBarData();
        bindBarData();
        animateChart();
    }

    /**
     * 清空数据集
     */
    private void clearData(){
        if (mDataSets != null)
            mDataSets.clear();
    }

    /**
     * 初始化图表配置
     */
    private void initChart() {
        //禁用双击放大
        setDoubleTapToZoomEnabled(false);
        //禁用柱状阴影
        setDrawBarShadow(false);
        //数值显示在柱状图之上
        setDrawValueAboveBar(true);
        //启用手指缩放
        setPinchZoom(true);
        //禁用网格背景
        setDrawGridBackground(false);
        //高亮时顶部箭头指示
        setDrawHighlightArrow(true);
        //图表描述
        setDescription("");

        XAxis xAxis = getXAxis();
        //横坐标位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //是否显示垂直线
        xAxis.setDrawGridLines(false);
        //横坐标间距
        xAxis.setSpaceBetweenLabels(2);

        YAxis leftAxis = getAxisLeft();
        //Y轴刻度位置--图表外
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        getAxisRight().setEnabled(false);

        Legend legend = getLegend();
        //图例位置
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        //图标样式
        legend.setForm(Legend.LegendForm.CIRCLE);
        //图标大小
        legend.setFormSize(9f);
        //文字大小
        legend.setTextSize(11f);
        //文字与图例间距
        legend.setFormToTextSpace(10f);
        //图例之间距离
        legend.setXEntrySpace(5f);
        //图例方向
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        //图例与文字方向,水平和竖直
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
    }

    /**
     * 构建X轴数据
     * @throws DataSizeZeroException
     */
    private void buildXVals() throws DataSizeZeroException {
        if (mLabels == null || mLabels.size() == 0)
            throw new DataSizeZeroException();
    }

    /**
     * 构建Y轴数据,即实际数据点
     * @throws DataSizeZeroException
     */
    private void buildYVals() throws DataSizeZeroException {
        if (mDatas == null || mDatas.size() == 0)
            throw new DataSizeZeroException();

        int length = mDatas.size();

        for (int i = 0; i < length; i ++){
            if (mDatas.get(i) == null || mDatas.get(i).size() == 0)
                throw new DataSizeZeroException();

            int temp = mDatas.get(i).size();
            List<String> data = mDatas.get(i);
            List<BarEntry> yVals = new ArrayList<>();
            for (int j = 0; j < temp; j ++){
                yVals.add(new BarEntry(Float.valueOf(data.get(j)),j));
            }

            if (mNames != null && mNames.get(i) != null && !mNames.get(i).equals(""))
                buildDataSet(yVals, mNames.get(i));
            else
                buildDataSet(yVals,"");
        }
    }

    /**
     * 构建数据集
     * @param yVals 数据点
     * @param name 数据集名称
     */
    private void buildDataSet(List<BarEntry> yVals, String name) {
        BarDataSet set = new BarDataSet(yVals,name);
        set.setBarSpacePercent(35f);
        set.setColors(ColorTemplate.MATERIAL_COLORS);

        mDataSets.add(set);
    }

    /**
     * 构建图表总数据集
     */
    private void buildBarData() {
        mBarData = new BarData(mLabels,mDataSets);
        mBarData.setValueTextSize(10f);
    }

    /**
     * 绑定数据
     */
    private void bindBarData() {
        setData(mBarData);
    }

    /**
     * 动画展示图表
     */
    private void animateChart() {
        animateY(2000, Easing.EasingOption.Linear);
    }

    public void addDataSet(List<String> data,String name){
        mDatas.add(data);
        mNames.add(name);

        init(mContext,mLabels,mDatas,mNames);
    }

    /**
     * 获取数据集
     * @return List<IBarDataSet>
     */
    public List<IBarDataSet> getDataSets() {
        return mDataSets;
    }

    /**
     * 根据index获取数据子集
     * @param index 坐标
     * @return BarDataSet
     */
    public BarDataSet getDataSet(int index){
        return (BarDataSet) mDataSets.get(index);
    }
}
