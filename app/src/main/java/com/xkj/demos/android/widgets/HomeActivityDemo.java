package com.xkj.demos.android.widgets;

import android.app.Activity;
import android.os.Bundle;
import com.xkj.demos.pages.CustomViewDemo;
import com.xkj.ui.CustomHomeItemView;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 14/12/26.
 */
public class HomeActivityDemo extends Activity {
    CustomHomeItemView homeItemView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_demo);
        homeItemView = (CustomHomeItemView) findViewById(R.id.home_item);
    }
}