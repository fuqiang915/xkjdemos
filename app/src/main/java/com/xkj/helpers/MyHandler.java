package com.xkj.helpers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.xkj.demos.pages.MyThreadActivity;

/**
 * Created by fuqiang on 15/11/13.
 */
public class MyHandler extends Handler {
    Context context;

    public MyHandler(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        Toast.makeText(this.context, "show in MyThread", Toast.LENGTH_SHORT).show();
    }
}
