package com.xkj.demos.mvp;

import android.content.Context;
import com.ihaveu.network.UtilVolley;

/**
 * MVP 中的 M  数据层
 * Created by fuqiang on 14/12/10.
 */
public class HomeModel extends UtilVolley implements IHomeModel{

    public HomeModel(Context context) {
        super(context);
    }

    /**
     * 模拟
     * @param geoId
     */
    @Override
    public void loadLimit(int geoId,JsonResponse jsonResponse) {
        get("http://dww.ihaveu.com/pay/merchants/"+geoId+".json",jsonResponse);
    }

    @Override
    public void loadHomeList(String mCityName, JsonResponse jsonResponse) {

    }

    @Override
    public void loadUserInfo() {

    }
}
