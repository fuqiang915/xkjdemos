package com.xkj.demos.pages;

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.xkj.xkjdemos.R;

public class EventActivity extends Activity {

    View mView;
    int mLastX = 0;
    int mLastY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mView = findViewById(R.id.mview);
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //计算速度
                VelocityTracker vt = VelocityTracker.obtain();
                vt.addMovement(event);

                //参数为单位时间ms
                vt.computeCurrentVelocity(1000);
                vt.getXVelocity();
                vt.getYVelocity();
                vt.clear();
                vt.recycle();

                int x = (int) event.getRawX();
                int y = (int) event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        event.getX();//相对于View x
                        event.getRawX();//相对手机左上角  x
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int deltaX = x - mLastX;
                        int deltaY = y - mLastY;

                        int translationX = (int) mView.getTranslationX() + deltaX;
                        int translationY = (int) mView.getTranslationY() + deltaY;
                        mView.setTranslationX(translationX);
                        mView.setTranslationY(translationY);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }
                mLastX = x;
                mLastY = y;
                return true;
            }
        });
    }


}
