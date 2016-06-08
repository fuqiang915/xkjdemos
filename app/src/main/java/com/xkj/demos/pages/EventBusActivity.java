package com.xkj.demos.pages;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xkj.utils.XEvent;
import com.xkj.xkjdemos.R;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class EventBusActivity extends Activity implements View.OnClickListener {

    Button mTriggerEvent;
    private String TAG = "EventBusActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        mTriggerEvent = (Button) findViewById(R.id.trigger_event);
        mTriggerEvent.setOnClickListener(this);

        ///注册事件
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.trigger_event:
                EventBus.getDefault().post(new XEvent("test event"));
                break;
            default:

                break;
        }
    }

    @Subscribe
    public void onEvent(XEvent event){
       Log.d(TAG,"事件触发"+event.getMessage());
    }

    @Subscribe
    public void onEventMainThread(XEvent event){
        Log.d(TAG, "事件已经被触发:"+event.getMessage());
    }
}
