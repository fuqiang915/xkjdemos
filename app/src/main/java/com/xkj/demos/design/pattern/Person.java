package com.xkj.demos.design.pattern;

import android.util.Log;

import java.io.Console;

/**
 * Created by fuqiang on 14/12/12.
 */
public class Person {
    protected final String TAG = this.getClass().getName();
    public Person(){

    }

    private String name;
    public Person(String name){
        this.name = name;
    }

    public void show(){
        Log.d(TAG," 我是"+name);
    }

}
