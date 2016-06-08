package com.xkj.demos.design.pattern.factory;

/**
 * Created by fuqiang on 15/11/8.
 */
public class DivisionFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new DivisionOperation();
    }
}
