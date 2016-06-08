package com.xkj.utils;

/**
 * 测试EventBus 事件
 * Created by fuqiang on 15/12/21.
 */
public class XEvent {
    private String  mMessage;

    public XEvent(String msg) {
        super();
        setmMessage(msg);
    }

    public String getMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
