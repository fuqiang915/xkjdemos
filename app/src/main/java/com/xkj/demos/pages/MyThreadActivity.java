package com.xkj.demos.pages;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xkj.helpers.MyThread;
import com.xkj.xkjdemos.R;

/**
 * Handler消息机制 和 线程Demo
 */
public class MyThreadActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_thread);
        textView = (TextView) findViewById(R.id.test);
//        runNewThread();


//        new Handler().post(new Runnable(){
//            @Override
//            public void run() {
////                Toast.makeText(MyThreadActivity.this, "sdfsd", Toast.LENGTH_SHORT).show();
//            }
//        });

//        Handler handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                Toast.makeText(MyThreadActivity.this, "show in MyThread", Toast.LENGTH_SHORT).show();
//            }
//        };

//        MyThread thread = new MyThread(this);
//        thread.start();

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(MyThreadActivity.this, "show in t Thread", Toast.LENGTH_SHORT).show();
//                Looper.loop();
//            }
//        });
//        t.start();

//        AsyncTask<String, Integer, Integer> task = new AsyncTask<String, Integer, Integer>() {
//            @Override
//            protected Integer doInBackground(String... params) {
//
//                try {
//                    Thread.sleep(4000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return 223;
//            }
//        };
//        task.execute("sdfsdf");
    }


    private void runNewThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setText("tianjinsdf");
//                textView.setVisibility(View.GONE);
                textView.setBackground(getResources().getDrawable(R.drawable.ic_launcher));
//                Toast.makeText(MyThreadActivity.this, "sdfsd", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public void run() {
                super.run();
                textView.setText("sdfasdfasdf");
            }
        };
        thread.start();
    }

//    private class MyHandler1 extends Handler{
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Toast.makeText(MyThreadActivity.this, "show in MyThread", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private class MyThread extends Thread{
//        Handler handler;
//        public MyThread(Handler handler) {
//            super();
//            this.handler = new MyHandler();
//
//        }
//
//        @Override
//        public void run() {
//            super.run();
//            Message msg = Message.obtain();
//            handler.sendMessage(msg);
//        }
//    }


    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                textView.setBackground(getResources().getDrawable(R.drawable.ic_launcher));
//                Toast.makeText(MyThreadActivity.this, "1 show in MyThread", Toast.LENGTH_SHORT).show();
            }
        }).start();

//        new MyThread(this).start();
    }
}
