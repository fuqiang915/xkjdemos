package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xkj.xkjdemos.R;

import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemoActivity extends Activity implements View.OnClickListener {

    private final String TAG = "ThreadPoolDemoActivity";

    private Button mAddTaskBtn;
    private TextView mLogTxt;
    private StringBuffer mLogs;
    private ExecutorService mPool;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool_demo);


        mLogs = new StringBuffer();

        mAddTaskBtn = (Button) findViewById(R.id.add_task_btn);
        mLogTxt = (TextView) findViewById(R.id.log_txt);
        mAddTaskBtn.setOnClickListener(this);

        /*
        Runnable command = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, " ThreadPoolDemoActivity ");
                SystemClock.sleep(1000);
            }
        };

        final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.execute(command);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(command);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.schedule(command, 1000, TimeUnit.MILLISECONDS);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(command);
        */
        //创建一个固定线程个数的线程池
        mPool = Executors.newFixedThreadPool(5);
        mPool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        addLogTask();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mLogTxt.setText(mLogs);
            }
        };
    }

    private void addLogTask() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        synchronized (mHandler) {
                            Thread.currentThread().sleep(50);
                            mHandler.sendEmptyMessage(1);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_task_btn:
                Log.d(TAG, " add Task");

                for (int i = 0; i < 5; i++) {
                    mPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            mLogs.insert(0, "add Task " + Thread.currentThread().getName() + " " + Math.random() * 100 + "\n");
                            //mHandler.sendEmptyMessage(1);
                            int start = (int) (Math.random() * 1000);
                            mLogs.insert(0, "new Task running" + start + "\n");
                            try {
                                Thread.currentThread().sleep(3000);
                                mLogs.insert(0, "done" + start + "\n");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }


                break;
            default:
                break;
        }
    }
}
