package com.xkj.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by fuqiang on 16/1/8.
 */
public class IRippleView extends View {

    /**
     * static final fields
     */
    private static final int DEFAULT_RIPPLE_COUNT = 6;
    private static final int DEFAULT_RIPPLE_COLOR = Color.rgb(0x33, 0xcc, 0x99);
    private static final int DEFAULT_STROKE_WIDTH = 15;
    private final int DRAW_MID = 80;

    private Paint mPaint;
    private ValueAnimator mValueAnimator;

    private int mStrokeWidth = DEFAULT_STROKE_WIDTH;
    private int[] mRadius;
    private int mMaxRadius;

    public IRippleView(Context context) {
        this(context, null);
    }

    public IRippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        setCircleCount(DEFAULT_RIPPLE_COUNT);
        initRadius();
        startAnimator();
    }

    void startAnimator() {
        mValueAnimator = ValueAnimator.ofFloat(0f, 1f);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setDuration(400);
        mValueAnimator.start();
        invalidate();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(DEFAULT_RIPPLE_COLOR);
        mStrokeWidth = 15;
        mPaint.setStyle(Paint.Style.STROKE);
    }

    private void setCircleCount(int count) {
        mRadius = new int[count];
    }

    private void initRadius() {
        for (int i = 0; i < mRadius.length; i++) {
            mRadius[i] = (i + 1) * DRAW_MID;
        }
        mMaxRadius = (mRadius.length + 1) * DRAW_MID;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius;
        float centerX = (Math.min(getWidth(), getHeight())) / 2;
        for (int i = 0; i < mRadius.length; i++) {
            radius = mRadius[i] + DRAW_MID * mValueAnimator.getAnimatedFraction();
            mPaint.setAlpha((int) (255 * (1-i/(mRadius.length+0.0f))));
            canvas.drawCircle(centerX, centerX, radius - mStrokeWidth, mPaint);
        }
        invalidate();
    }
}
