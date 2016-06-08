package com.xkj.helpers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AnimImageView extends ImageView {
	public AnimImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public AnimImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AnimImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void setPathPoint(PathPoint newLoc) {
		setTranslationX(newLoc.mX);
		setTranslationY(newLoc.mY);
	}

}
