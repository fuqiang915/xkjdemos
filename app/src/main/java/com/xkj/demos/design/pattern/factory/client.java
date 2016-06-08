package com.xkj.demos.design.pattern.factory;

import com.ihaveu.utils.Log;

/**
 * 模拟客户端代码
 * Created by fuqiang on 15/11/8.
 */
public class client {
    public static final String TAG = "client";

    public static void run() {

        //但是需要那种运算符需要在客户端判断
        //需求更改时，比如增加了另一个运算，只要修改客户端这里就可以了,不需要修改每个运算符的Factory or Operaction类
        String operStr = "-";
        IFactory factory;
        switch (operStr) {
            case "+":
                factory = new AddFactory();
                break;
            case "-":
                factory = new DivisionFactory();
                break;
            default:
                factory = new AddFactory();
                break;
        }

        //这里也不需要变
        //加法工厂类
        Operation operation = factory.createOperation();
        operation.setX(9);
        operation.setY(19);
        Log.d(TAG, "result is " + operation.getResult());
    }
}
