package com.xkj.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import com.ihaveu.ui.IScrollView.OnScrollChangedListener;

public class UnScrollListView extends ListView {

	private OnScrollChangedListener onScrollChangedListener;

	public UnScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public UnScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public UnScrollListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		if (onScrollChangedListener != null) {
			onScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
		}
		
	}
	/** * * @param onScrollChangedListener */
	public void setOnScrollListener(
			OnScrollChangedListener onScrollChangedListener) {
		this.onScrollChangedListener = onScrollChangedListener;
	}
	
}
