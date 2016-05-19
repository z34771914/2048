package cn.com.zhoujiazhen.mytestproject.view;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujiazhen on 16/5/19.
 */
public class GameView extends GridLayout {
    private final String TAG = "GameView";

    float startX, startY, offsetX, offsetY;

    private CardView[][] cardViews = new CardView[4][4];

    private List<Point> emptyPoints = new ArrayList<>();

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initGameView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w, h) - 10) / 4;

        addCards(cardWidth, cardWidth);

        startGame();

    }

    /**
     * 添加卡片
     *
     * @param cardWidth
     * @param cardHeight
     */
    private void addCards(int cardWidth, int cardHeight) {
        CardView c;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                c = new CardView(getContext());
                c.setNum(0);
                addView(c, cardWidth, cardHeight);

                cardViews[x][y] = c;
            }
        }
    }

    private void startGame(){
        for (int y = 0; y < 4; y ++) {
            for (int x = 0; x < 4; x++) {
                cardViews[x][y].setNum(0);
            }
        }

        addRandomNumber();
        addRandomNumber();
    }

    private void initGameView() {
        setColumnCount(4);

        setBackgroundColor(0xffbbada0 );
    }

    private void addRandomNumber(){
        emptyPoints.clear();

        for (int y = 0; y < 4; y ++){
            for (int x = 0; x < 4; x ++){
                if (cardViews[x][y].getNum() <= 0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }

        Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        cardViews[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }

    private void swipeLeft() {
        for (int y = 0; y < 4; y ++){
            for (int x = 0; x < 4; x ++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (cardViews[x1][y].getNum() > 0) {
                        if (cardViews[x][y].getNum() <= 0) {
                            cardViews[x][y].setNum(cardViews[x1][y].getNum());
                            cardViews[x1][y].setNum(0);

                            x--;
                            break;
                        } else if (cardViews[x1][y].equals(cardViews[x][y])) {
                            cardViews[x][y].setNum(cardViews[x][y].getNum() * 2);
                            cardViews[x1][y].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void swipeRight() {
        for (int y = 0; y < 4; y ++){
            for (int x = 3; x >= 0; x --) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cardViews[x1][y].getNum() > 0) {
                        if (cardViews[x][y].getNum() <= 0) {
                            cardViews[x][y].setNum(cardViews[x1][y].getNum());
                            cardViews[x1][y].setNum(0);

                            x ++;
                            break;
                        } else if (cardViews[x1][y].equals(cardViews[x][y])) {
                            cardViews[x][y].setNum(cardViews[x][y].getNum() * 2);
                            cardViews[x1][y].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void swipeUp() {
        for (int x = 0; x < 4; x ++){
            for (int y = 0; y < 4; y ++) {
                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (cardViews[x][y1].getNum() > 0) {
                        if (cardViews[x][y].getNum() <= 0) {
                            cardViews[x][y].setNum(cardViews[x][y1].getNum());
                            cardViews[x][y1].setNum(0);

                            y--;
                            break;
                        } else if (cardViews[x][y1].equals(cardViews[x][y])) {
                            cardViews[x][y].setNum(cardViews[x][y].getNum() * 2);
                            cardViews[x][y1].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void swipeDown() {
        for (int x = 0; x < 4; x ++){
            for (int y = 3; y >= 0; y --) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardViews[x][y1].getNum() > 0) {
                        if (cardViews[x][y].getNum() <= 0) {
                            cardViews[x][y].setNum(cardViews[x][y1].getNum());
                            cardViews[x][y1].setNum(0);

                            y++;
                            break;
                        } else if (cardViews[x][y1].equals(cardViews[x][y])) {
                            cardViews[x][y].setNum(cardViews[x][y].getNum() * 2);
                            cardViews[x][y1].setNum(0);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                offsetX = event.getX() - startX;
                offsetY = event.getY() - startY;

                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    if (offsetX < -5) {
                        swipeLeft();
                    } else if (offsetX > 5) {
                        swipeRight();
                    }
                } else {
                    if (offsetY < -5) {
                        swipeUp();
                    } else if (offsetY > 5) {
                        swipeDown();
                    }
                }

                addRandomNumber();
                break;
        }


        return true;
    }
}
