package com.xkj.ui;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fuqiang on 14/12/26.
 */
public class CustomHomeItemView extends View {

    Paint mTextPaint = null;

    public CustomHomeItemView(Context context) {
        super(context);
    }

    public CustomHomeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTag(canvas,"王府井旗下");
    }

    private Paint getTextPaint() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(30);
        mTextPaint.setStrokeJoin(Paint.Join.ROUND);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setStrokeWidth(3);
        return mTextPaint;
    }

    /**
     * 绘制标签
     */
    private void drawTag(Canvas canvas,String text) {


        //绘制圆角矩形
        Paint paint = new Paint();
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.parseColor("#cb2431"));    //设置画笔颜色
//        canvas.drawColor(Color.parseColor("#cb2431"));

        paint.setStrokeWidth((float) 8.0);              //线宽
        paint.setStyle(Paint.Style.FILL);                   //空心效果
        Rect r1=new Rect();                         //Rect对象
        r1.left=50;                                 //左边
        r1.top=50;                                  //上边
        r1.right=450;                                   //右边
        r1.bottom=250;                              //下边
        canvas.drawRect(r1, paint);                 //绘制矩形

        RectF r2=new RectF();                           //RectF对象
        r2.left=50;                                 //左边
        r2.top=500;                                 //上边
        r2.right=450;                                   //右边
        r2.bottom=900;                              //下边
        canvas.drawRoundRect(r2, 200, 200, paint);        //绘制圆角矩形

        //绘制文字
        Paint textPaint = getTextPaint();
        canvas.drawText(text,200,300,textPaint);
    }

    public CustomHomeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int minW = getPaddingLeft()+getPaddingRight()+getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minW,widthMeasureSpec,1);
        int h = resolveSizeAndState(MeasureSpec.getSize(w),heightMeasureSpec,0);

        setMeasuredDimension(w,h);
    }


    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }
}
