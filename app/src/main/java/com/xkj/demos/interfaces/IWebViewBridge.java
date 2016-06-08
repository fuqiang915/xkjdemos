package com.xkj.demos.interfaces;

import android.content.Context;
import android.webkit.WebView;
import com.xkj.helpers.WebViewBridge;

/**
 * Created by fuqiang on 15/1/28.
 */
public interface IWebViewBridge {

    /**
     * js传递到java层的url协议开头
     */
    final String UAPP_PRE = "uapp://";
    /**
     * 回调数组名称
     */
    final String CALLBACKS = "callbacks";

    /**
     * 基础
     */
    public String createBaseJs();

    /**
     * 加载Js代码
     * @return
     */
    public String createBaseJs(Context context,String fileName);

    /**
     * Java 调用 js
     *
     * @param view
     * @param jsCode
     */
    public void executeJs(WebView view, String jsCode);

    /**
     * 解析js传递过来的字符串
     * @param argument
     */
    public java.util.Map<String, String> parseJsArgument(String argument);

    /**
     * 根据url格式判断
     * 是否需要阻止Url的加载
     * Js通过这种方式调用java代码和传递参数
     * @param url
     * @param jscallback
     * @return
     */
    public boolean handleJsArgument(String url, WebViewBridge.handleJsCallback jscallback);

    /**
     * 触发BridgeLoaded事件
     * 用于解决加载页面时注入的代码没有成功执行的问题
     * 当页面加载完成之后调用此方法，网页端监听onBridgeLoaded事件执行后续方法
     * @param view
     */
    public void triggerOnBridgeLoadedEvent(WebView view);

    /**
     * 执行js成功回调
     * @param view
     * @param callbackId  回调Id
     * @param argument
     */
    public void executeSuccessCallback(WebView view, String callbackId, String argument);

    /**
     * 执行js 失败回调
     * @param view
     * @param callbackId
     * @param argument
     */
    public void executeErroCallback(WebView view, String callbackId, String argument);
}
