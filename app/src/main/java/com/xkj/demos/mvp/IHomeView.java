package com.xkj.demos.mvp;

import org.json.JSONObject;

/**
 * Home View 接口
 * 展示首页数据
 * 功能：
 * 展示限额数据
 * 展示列表数据
 * 展示用户信息：2个接口
 * 初始化Ihaveu 两个服务
 *
 * Created by fuqiang on 14/12/10.
 */
public interface IHomeView {

    /**
     * 渲染限额数据
     * @param limitResponse
     */
    public void renderLimit(JSONObject limitResponse);

    /**
     * 渲染列表数据
     * @param homeResponse
     */
    public void renderHomeList(JSONObject homeResponse);

    /**
     * 渲染用户数据
     * U元
     * User Id 等
     * @param userResponse
     */
    public void renderUserInfo(JSONObject userResponse);
}
