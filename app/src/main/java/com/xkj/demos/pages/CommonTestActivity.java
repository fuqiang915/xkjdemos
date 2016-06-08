package com.xkj.demos.pages;

import android.os.Bundle;
import android.app.Activity;

import com.xkj.demos.tests.Test;
import com.xkj.xkjdemos.R;

public class CommonTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_test);

        //测试
        Test.m();
    }

}
