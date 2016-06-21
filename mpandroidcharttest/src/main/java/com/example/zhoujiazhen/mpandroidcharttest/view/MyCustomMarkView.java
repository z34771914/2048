package com.example.zhoujiazhen.mpandroidcharttest.view;

import android.content.Context;
import android.widget.TextView;

import com.example.zhoujiazhen.mpandroidcharttest.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

/**
 * Created by zhoujiazhen on 16/6/17.
 */

public class MyCustomMarkView extends MarkerView {
    private TextView mTextView;
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MyCustomMarkView(Context context, int layoutResource) {
        super(context, layoutResource);
        mTextView = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        mTextView.setText("" + e.getVal());
    }

    @Override
    public int getXOffset(float xpos) {
        return -(getWidth()/2);
    }

    @Override
    public int getYOffset(float ypos) {
        return -getHeight();
    }
}
