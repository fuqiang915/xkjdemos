package com.xkj.demos.pages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Activity;
import android.os.HandlerThread;
import android.util.Log;

import com.xkj.demos.threads.MHandlerThread;
import com.xkj.xkjdemos.R;

public class HandlerThreadActivity extends Activity {

    private final String TAG = "HandlerThreadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        Log.d(TAG, " 启动HandlerThread ");
        MHandlerThread handlerThread = new MHandlerThread("HandlerThread");
        handlerThread.start();
    }

}
