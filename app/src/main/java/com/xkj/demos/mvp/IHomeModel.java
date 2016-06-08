package com.xkj.demos.mvp;

import com.ihaveu.network.UtilVolley;
import com.ihaveu.network.UtilVolley.*;
import org.json.JSONObject;

/**
 * 提供数据
 * Model 层的接口
 * Created by fuqiang on 14/12/10.
 */
public interface IHomeModel {
    /**
     * 限额数据
     */
    public void loadLimit(int geoId,JsonResponse jsonResponse);

    /**
     * 首页列表接口
     */
    public void loadHomeList(String mCityName, JsonResponse jsonResponse);

    /**
     * 用户信息
     */
    public void loadUserInfo();
}
