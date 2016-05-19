package cn.com.zhoujiazhen.mytestproject.view;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by zhoujiazhen on 16/5/19.
 */
public class CardView extends FrameLayout {
    private final String TAG = "CardView";

    private int num = 0;
    private TextView label;

    public CardView(Context context) {
        super(context);

        label = new TextView(context);
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x33ffffff);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(10, 10, 0, 0);

        addView(label, params);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        if (num <= 0)
            label.setText("");
        else
            label.setText(num + "");
    }

    public boolean equals(CardView o) {
        return getNum() == o.getNum();
    }
}
