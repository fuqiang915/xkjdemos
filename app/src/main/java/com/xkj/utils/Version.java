package com.xkj.utils;

/**
 * Created by fuqiang on 14/11/14.
 */
public class Version {
    /**
     * 版本比较
     * @param _old x.x.x
     * @param _new x.x.x
     * @return
     */
    public static int compare(String _old,String _new){
        if(_old ==null && _new ==null){
            return 0;
        }
        if(_old==null){
            return -1;
        }
        if(_new==null){
            return 1;
        }
        return _old.compareTo(_new);
    }
}
