package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xkj.xkjdemos.R;


public class ThreadLocalActivity extends Activity {


    private final String TAG = "ThreadLocalActivity";
    ThreadLocal<Boolean> mThreadLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_local);
        threadlocalTest();
    }

    private void threadlocalTest() {
        mThreadLocal = new ThreadLocal<>();
        mThreadLocal.set(true);

        Log.d(TAG, "main thread   " + mThreadLocal.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                mThreadLocal.set(false);
                Log.d(TAG, "thread 1  " + mThreadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "thread 2 " + mThreadLocal.get());
            }
        }).start();

    }

}

