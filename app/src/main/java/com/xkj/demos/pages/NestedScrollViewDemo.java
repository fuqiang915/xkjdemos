package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import com.xkj.ui.InnerScrollView;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 15/2/5.
 */
public class NestedScrollViewDemo extends Activity {
    InnerScrollView innerScrollView;
    ScrollView outScrollView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrollview);
        innerScrollView = (InnerScrollView) findViewById(R.id.innerscroll);
        outScrollView = (ScrollView) findViewById(R.id.out_scrolview);

        innerScrollView.parentScrollView =outScrollView;
    }
}