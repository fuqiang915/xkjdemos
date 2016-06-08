package com.xkj.demos.design.pattern.factory;

/**
 * Created by fuqiang on 15/11/8.
 */
public class DivisionOperation extends Operation{
    @Override
    public double getResult() {
        return getX()/getY();
    }
}
