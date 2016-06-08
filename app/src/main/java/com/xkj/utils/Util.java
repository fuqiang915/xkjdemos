package com.xkj.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by fuqiang on 16/1/24.
 */
public class Util {
    /**
     * Toast
     * @param cxt
     * @param msg
     */
    public static void toast(Context cxt, String msg) {
        Toast.makeText(cxt, msg, Toast.LENGTH_SHORT).show();
    }
}
