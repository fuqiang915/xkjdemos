package com.xkj.demos.tests;

import android.util.Log;

/**
 * 一道题
 * http://www.nowcoder.com/questionTerminal/305b2d834f0a4369805f29576e40e8c5?orderByHotValue=2&done=0&pos=2&mutiTagIds=642_570&onlyReference=true
 * Created by fuqiang on 15/11/16.
 */
public class Test {
    private final static String TAG = "Test";

    private static void test(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            try {
                if (arr[i] % 2 == 0) {
                    throw new NullPointerException();
                } else {
                    Log.d(TAG, i+"");
                }
            } finally {
                Log.d(TAG, "e");
            }
        }
    }

    public static void m() {
        try {
            test(new int[]{0, 1, 2, 3, 4, 5});
        } catch (Exception e) {
            Log.d(TAG, "E");
            ;
        }
    }

}
