package com.xkj.demos.mvp;

import android.content.Context;
import android.content.Intent;
import com.android.volley.VolleyError;
import com.ihaveu.network.UtilVolley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * MVP 中的P 主逻辑层
 * Created by fuqiang on 14/12/10.
 */
public class HomePresenter {
    private IHomeModel mHomeModel;
    private IHomeView mHomeView;
    private Intent mIntent;

    /**
     * 构造函数
     * 传入 View 和 Model
     * @param view
     * @param intent
     */
    public HomePresenter(IHomeView view,Context context,Intent intent){
        mHomeView = view;
        mHomeModel = new HomeModel(context);
        mIntent = intent;
    }

    /**
     * 渲染首页列表
     */
    public void loadHomeJson(){
        try {
            mHomeView.renderHomeList(new JSONObject(mIntent.getStringExtra("home")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void loadGeofences(){

    }

    /**
     * 加载商场限额
     */
    public void loadLimitJson(){
        int geoId = 13;
        mHomeModel.loadLimit(geoId,new UtilVolley.JsonResponse() {
            @Override
            public void onSuccess(JSONObject jsonObject, JSONArray jsonArray) {
                mHomeView.renderLimit(jsonObject);
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

}
