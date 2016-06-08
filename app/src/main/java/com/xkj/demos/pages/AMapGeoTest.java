package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 14/11/24.
 */
public class AMapGeoTest extends Activity {

    private Button btnLocation;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap_geo_test);
        btnLocation =(Button) findViewById(R.id.btn_location);
        
    }
}