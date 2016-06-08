package com.xkj.ui;
/*
 * Sample FlowLayout wrote by Romain Guy: http://www.parleys.com/play/514892280364bc17fc56c0e2/chapter38/about
 * Fixed and tweaked since
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.xkj.xkjdemos.R;

/**
 * 流布局
 */
public class FlowLayout extends ViewGroup {
    // 水平，垂直 间距
    private int mHorizontalSpacing;
    private int mVerticalSpacing;
    private Paint mPaint;

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        try {
            mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_horizontalSpacing, 0);
            mVerticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_verticalSpacing, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取实际绘制的宽度，排除padding
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingRight() - getPaddingLeft();
        // 获取Mode
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        //  MeasureSpec.EXACTLY 或 AT_MOST 都需要growHeight
        boolean growHeight = widthMode != MeasureSpec.UNSPECIFIED;

        //
        int width = 0;
        int height = getPaddingTop();

        //当前宽高
        int currentWidth = getPaddingLeft();
        int currentHeight = 0;

        boolean newLine = false;
        int spacing = 0;

        final int count = getChildCount();
        //测量每一个子View
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            // 设置xml中的spacing
            spacing = mHorizontalSpacing;
            // 布局参数中的spacing 优先
            if (lp.horizontalSpacing >= 0) {
                spacing = lp.horizontalSpacing;
            }


            // 如果宽度不够，新建一行
            if (growHeight && currentWidth + child.getMeasuredWidth() > widthSize) {
                height += currentHeight + mVerticalSpacing;
                currentHeight = 0;
                width = Math.max(width, currentWidth - spacing);
                currentWidth = getPaddingLeft();
                newLine = true;
            } else {
                newLine = false;
            }

            lp.x = currentWidth;
            lp.y = height;

            currentWidth += child.getMeasuredWidth() + spacing;
            currentHeight = Math.max(currentHeight, child.getMeasuredHeight());
        }

        if (!newLine) {
            width = Math.max(width, currentWidth - spacing);
        }
        width += getPaddingRight();
        height += currentHeight + getPaddingBottom();

        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y + child.getMeasuredHeight());
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p.width, p.height);
    }

    /**
     * 布局参数
     */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        int x;
        int y;

        public int horizontalSpacing;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_LayoutParams);
            try {
                horizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_LayoutParams_layout_horizontalSpacing, -1);
            } finally {
                a.recycle();
            }
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }
    }
}
