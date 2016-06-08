package com.xkj.demos.android.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by fuqiang on 14/12/18.
 */
public class InnerListView extends ListView {

    private  boolean mEnableTouch = false;

    public InnerListView(Context context) {
        super(context);
    }

    public InnerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置是否可以触摸
     * @param enable
     */
    public void setEnableTouch(boolean enable){
        mEnableTouch = enable;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mEnableTouch){
            return super.onTouchEvent(ev);
        }else{
//            super.onTouchEvent(ev);
            return false;
        }
        //return super.onTouchEvent(ev);
    }
}
