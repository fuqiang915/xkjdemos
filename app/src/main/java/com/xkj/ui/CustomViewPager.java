package com.xkj.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import com.ihaveu.utils.Log;

/**
 * Created by fuqiang on 14/12/29.
 */
public class CustomViewPager extends ViewPager {

    private final String TAG = "CustomViewPager";
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取实际绘制的宽度，排除padding
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingRight() - getPaddingLeft();
        // 获取Mode
        int height = 0;
        int childCount = getChildCount();
        int current = getCurrentItem();
        if (childCount > 0 && current < childCount) {
            View child = getChildAt(current);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            height = child.getMeasuredHeight();
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),resolveSize(height,heightMeasureSpec));
        }else{
            Log.d(TAG, " childCount =" + childCount + " current " + current);
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }
    }

}
