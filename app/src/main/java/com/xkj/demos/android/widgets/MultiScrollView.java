package com.xkj.demos.android.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import com.ihaveu.utils.Log;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 14/12/17.
 */
public class MultiScrollView extends ScrollView {
    private final String TAG = "MultiScrollView";
    /**
     * 标记tag
     * 根据View.tag 获取需要滚动的View
     */
    private final String MARK_TAG = "scroll";
    private InnerListView mChildScrollView;

    private boolean mNoTouch = false;

    public MultiScrollView(Context context) {
        super(context);
    }

    public MultiScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initChildView();
    }

    private void initChildView() {
        if(getChildCount()==0){
            Log.d(TAG," no child ");
           return;
        }
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        if(viewGroup!=null){
            //mChildScrollView = (ListView)viewGroup.findViewWithTag(MARK_TAG);
            mChildScrollView = (InnerListView)viewGroup.findViewById(R.id.child_list);
        }else{
            Log.d(TAG," viewGourp is null");
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d(TAG, " l , t "+l+"  "+t);
        if(mChildScrollView==null){
            Log.d(TAG," mChildScrollView is null");
            return;
        }
        Log.d(TAG," y "+mChildScrollView.getY());
        if(t>mChildScrollView.getY()){
//            mChildScrollView.scrollTo(l, t);
            mChildScrollView.setEnableTouch(true);
            mNoTouch=true;
        }else{
            mNoTouch = false;
            mChildScrollView.setEnableTouch(false);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(mNoTouch){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mNoTouch){
//            return false;
        }else{
//            return super.onTouchEvent(ev);
        }

        return super.onTouchEvent(ev);
    }
}
