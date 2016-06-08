package com.xkj.demos.threads;

import android.os.HandlerThread;
import android.util.Log;

/**
 * Created by fuqiang on 15/12/10.
 */
public class MHandlerThread extends HandlerThread {

    private String TAG = "HandlerThread";

    public MHandlerThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        Log.d(TAG, " HandlerThread is running ");
    }
}
