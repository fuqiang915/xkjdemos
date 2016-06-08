package com.xkj.demos.design.pattern;

/**
 * 服饰类
 * Created by fuqiang on 14/12/13.
 */
public class Finery extends Person{

    protected Person component;

    public void decorate(Person component){
        this.component = component;
    }
    @Override
    public void show() {
        if(component!=null){
            component.show();
        }
    }
}
