package com.xkj.demos.pages;

import android.os.Bundle;
import android.app.Activity;

import com.xkj.ui.RippleLayout;
import com.xkj.ui.WaterRipplesView;
import com.xkj.xkjdemos.R;

public class WaterRippleActivity extends Activity {


    //水波纹view
    RippleLayout mWaterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_ripple);
//        mWaterView = (RippleLayout) findViewById(R.id.water_view);
//        mWaterView.startRippleAnimation();

    }

}
