package cn.com.zhoujiazhen.animatortest;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int[] ids = new int[]{R.id.image_a, R.id.image_b, R.id.image_c, R.id.image_d,
            R.id.image_e, R.id.image_f, R.id.image_g, R.id.image_h};

    private List<ImageView> mImageViews = new ArrayList<>();

    private boolean isShown = false;
    private float moveDistance = 800f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (int i = 0; i < ids.length; i ++) {
            ImageView imageView = (ImageView) findViewById(ids[i]);
            imageView.setOnClickListener(this);
            mImageViews.add(imageView);
        }

    }

    void move() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_a:
                // TODO: 16/5/27  展开动画
                if (isShown) {
                    dismiss();
                    isShown = false;
                }
                else {
                    show();
                    isShown = true;
                }
                break;
            default:
                break;
        }
    }

    private void show() {
        for (int i = 1; i < mImageViews.size(); i ++){
            ObjectAnimator.ofFloat(mImageViews.get(i),
                    "translationY",0f, (float) (moveDistance * Math.cos((Math.PI / 2 / (ids.length - 2) * (i - 1)))))
                    .setDuration(500).start();

            ObjectAnimator.ofFloat(mImageViews.get(i),
                    "translationX",0f, (float) (moveDistance * Math.sin((Math.PI / 2 / (ids.length - 2) * (i - 1)))))
                    .setDuration(500).start();
        }
    }

    private void dismiss() {
        for (int i = 1; i < mImageViews.size(); i ++){
            ObjectAnimator.ofFloat(mImageViews.get(i),
                    "translationY",(float) (moveDistance * Math.cos((Math.PI / 2 / (ids.length - 2) * (i - 1)))), 0f)
                    .setDuration(500).start();

            ObjectAnimator.ofFloat(mImageViews.get(i),
                    "translationX",(float) (moveDistance * Math.sin((Math.PI / 2 / (ids.length - 2) * (i - 1)))), 0f)
                    .setDuration(500).start();
        }
    }
}
