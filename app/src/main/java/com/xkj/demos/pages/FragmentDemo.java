package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.xkj.demos.fragments.DemoFragment;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 15/1/21.
 */
public class FragmentDemo extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager.enableDebugLogging(true);

        setContentView(R.layout.activity_fragment_demo);
        DemoFragment fragment = new DemoFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_holder, fragment, "demo_fragment").addToBackStack("demdd").commit();

    }
}