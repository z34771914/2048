package com.example.zhoujiazhen.mpandroidcharttest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.zhoujiazhen.mpandroidcharttest.R;

/**
 * Created by zhoujiazhen on 16/6/21.
 */

public class PointProgressBar extends View {
    private static final String TAG = "PointProgressBar";

    private static final int MAX_POINT_NUM = 20;
    private static final int DANGER_POINT_NUM = 5;

    private int width;
    private int height;

    private int pointNormalColor;
    private int pointDangerColor;
    private float pointRadius;

    private float startX;     //开始位置
    private float interval;   //点间隔
    private float maxWidth;   //最大宽度
    private float startY;     //纵坐标

    private int maxValue;
    private int currentValue;
    private int visiblePointNum = 0;

    public PointProgressBar(Context context) {
        this(context, null, 0);
    }

    public PointProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PointProgressBar, 0, 0);
        if (array != null) {
            pointNormalColor = array.getColor(R.styleable.PointProgressBar_pointNormalColor, Color.GREEN);
            pointDangerColor = array.getColor(R.styleable.PointProgressBar_pointDangerColor, Color.RED);
            pointRadius = array.getDimension(R.styleable.PointProgressBar_pointRadius, 10.0f);
            maxValue = array.getInteger(R.styleable.PointProgressBar_maxValue, 100);

            array.recycle();
        }

        initData();
    }

    private void initData() {
        startX = pointRadius * 2.0f + getPaddingLeft();
        interval = pointRadius * 3.0f;
        maxWidth = pointRadius * 2.0f + pointRadius * 2.0f * MAX_POINT_NUM + pointRadius * (MAX_POINT_NUM - 1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize + getPaddingLeft() + getPaddingRight();
            if (width < maxWidth) {
                pointRadius = pointRadius * (width / maxWidth);
            } else
                width = Math.round(maxWidth) + getPaddingRight() + getPaddingLeft();
        } else {
            width = Math.round(maxWidth);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
            if (height > pointRadius * 2) {
                height = (int) (getPaddingBottom() + getPaddingTop() + pointRadius * 2);
            }
        } else {
            height = (int) (getPaddingBottom() + getPaddingTop() + pointRadius * 2);
        }

        startY = height / 2;

        initData();

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        if (visiblePointNum < DANGER_POINT_NUM)
            paint.setColor(pointDangerColor);
        else
            paint.setColor(pointNormalColor);
        for (int i = 0; i < visiblePointNum; i ++){
            canvas.drawCircle(startX + interval * i, startY, pointRadius, paint);
        }
    }

    public synchronized void refresh(int value) {
        currentValue = value;
        if (value == 0) {
            visiblePointNum = 1;
        } else
            visiblePointNum = (int) (Math.ceil((double) currentValue / maxValue * 20));

        Log.e(TAG, "refresh: visiblePoint = " + visiblePointNum);

        invalidate();
    }
}
