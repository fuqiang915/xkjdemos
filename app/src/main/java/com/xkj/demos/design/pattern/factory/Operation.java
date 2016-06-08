package com.xkj.demos.design.pattern.factory;

/**
 * 运算符类
 * Created by fuqiang on 15/11/8.
 */
public abstract class Operation {
    public double x;
    public double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public abstract double getResult();
}
