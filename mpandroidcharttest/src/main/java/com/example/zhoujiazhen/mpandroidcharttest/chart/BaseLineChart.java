package com.example.zhoujiazhen.mpandroidcharttest.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhoujiazhen.mpandroidcharttest.exception.DataSizeZeroException;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujiazhen on 16/6/20.
 * <p>
 * 折线图基类
 */

public class BaseLineChart extends LineChart {
    private static final int maxLength = 5;

    private Context mContext;
    protected List<String> mLabels;
    protected List<List<String>> mDatas;
    protected LineData mLineData;
    protected List<String> mNames = new ArrayList<>();

    protected ArrayList<ILineDataSet> mDataSets = new ArrayList<>();
    protected ArrayList<String> mXVals = new ArrayList<>();

    public BaseLineChart(Context context) {
        super(context);
    }


    public BaseLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(Context context, List<String> labels, List<List<String>> data) {
        this.mContext = context;
        this.mLabels = labels;
        this.mDatas = data;

        initChart();
        try {
            buildXVals();
            buildYVals();
        } catch (DataSizeZeroException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "数据源为空", Toast.LENGTH_SHORT).show();
        }
        buildLineData();
        bindLineData();
        animateChart();
    }

    public void init(Context context, List<String> labels, List<List<String>> data, List<String> names) {
        this.mContext = context;
        this.mLabels = labels;
        this.mDatas = data;
        this.mNames = names;

        initChart();
        try {
            buildXVals();
            buildYVals();
        } catch (DataSizeZeroException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "数据源为空", Toast.LENGTH_SHORT).show();
        }
        buildLineData();
        bindLineData();
        animateChart();
    }

    /**
     * 初始化Chart配置
     */
    private void initChart(){
        setDescription("");
        //可缩放
        setPinchZoom(true);
        //x坐标轴位于底部
        getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        //隐藏网格线
        getAxisLeft().setDrawGridLines(false);
        getAxisRight().setDrawGridLines(false);
        //隐藏右侧y坐标轴
        getAxisRight().setEnabled(false);
        //禁用双击放大
        setDoubleTapToZoomEnabled(false);
    }

    /**
     * 构建x轴刻度
     */
    private void buildXVals() throws DataSizeZeroException {
        int xLength = mLabels.size();
        if (xLength == 0)
            throw new DataSizeZeroException();

        for (int i = 0; i < xLength; i++)
            mXVals.add(mLabels.get(i));
    }

    /**
     * 构建Y轴数据
     */
    private void buildYVals() throws DataSizeZeroException {
        int yLength = mDatas.size();

        if (yLength == 0)
            throw new DataSizeZeroException("data cannot be empty");
        if (yLength > maxLength)
            throw new DataSizeZeroException("data size cannot larger than 5");

        for (int i = 0; i < yLength; i++) {
            if (mDatas.get(i) != null) {
                List<String> data = mDatas.get(i);
                int length = data.size();
                if (length > 0) {
                    ArrayList<Entry> vals = new ArrayList<>();
                    for (int j = 0; j < length; j ++)
                        vals.add(new Entry(Float.valueOf(data.get(j)), j));

                    mDataSets.add(buildDataSet(vals,i));
                }
            }
        }

        buildLineData();
    }

    /**
     * 构建LineDataSet
     *
     * @param vals
     * @param index
     * @return
     */
    private LineDataSet buildDataSet(ArrayList<Entry> vals, int index){
        LineDataSet set = new LineDataSet(vals,mNames.get(index));

        //设置平滑曲线
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //设置曲线颜色
        set.setColor(ColorTemplate.JOYFUL_COLORS[index]);
        //设置线宽
        set.setLineWidth(2.0f);
        //设置基准坐标轴为左边y轴
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        //不绘制数据点数值
        set.setDrawValues(false);

        return set;
    }

    /**
     * 构建LineData
     */
    private void buildLineData(){
        mLineData = new LineData(mXVals,mDataSets);

        bindLineData();
    }

    /**
     * 绑定LineData到Chart
     */
    private void bindLineData(){
        setData(mLineData);

        animateChart();
    }

    /**
     * 出场动画
     */
    private void animateChart(){
        animateXY(1000,1000);
    }

    public LineDataSet getDataSet(int index){
        return (LineDataSet) mDataSets.get(index);
    }
}
