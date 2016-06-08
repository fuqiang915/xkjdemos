package com.xkj.helpers;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by fuqiang on 15/11/13.
 */

public class MyThread extends Thread {
//    Handler handler;
    Context context;

    public MyThread(Handler handler) {
        super();
//        this.handler = handler;
    }

    public MyThread(final Context context) {
        this.context = context;

//        this.handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                Toast.makeText(context, "show in MyThread", Toast.LENGTH_SHORT).show();
//            }
//        };

    }

    @Override
    public void run() {
        super.run();
//        Message msg = Message.obtain();
//        handler.sendMessage(msg);

        Looper.prepare();
        Toast.makeText(context, "1 show in MyThread", Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
}
