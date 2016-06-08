package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.ihaveu.utils.Log;
import com.xkj.xkjdemos.R;
import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fuqiang on 15/2/10.
 */
public class HandlerDemo extends Activity {
    TextView mTextView;

    int index = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);
        mTextView = (TextView) findViewById(R.id.my_text);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("HandlerDemo", String.valueOf(msg.what));
                mTextView.setText("HandlerDemoHandlerDemoHandlerDemoHandlerDemo    "+index++);
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0x033;
                handler.sendMessage(msg);
            }
        }, 0, 1000);
    }

}