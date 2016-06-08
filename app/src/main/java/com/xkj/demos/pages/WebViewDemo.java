package com.xkj.demos.pages;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ihaveu.utils.Log;
import com.xkj.helpers.WebViewBridge;
import com.xkj.xkjdemos.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo for webview 传递参数
 * v1: 实现传递参数和回调函数
 * v2: 将功能封装成WebView
 * Created by fuqiang on 15/1/28.
 *
 *
 * //TODO: need comfirm
 * 1，事件是否肯定可以触发
 */
public class WebViewDemo extends Activity{
    private static final String TAG = "WebViewDemo";
    WebView webView;
    WebViewBridge mBridge;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);
        webView = (WebView) findViewById(R.id.webview);
        setUpWebView(webView);
        mBridge = new WebViewBridge();
        webView.loadUrl("http://dtouch.ihaveu.com/android_weixin.html");
    }

    /**
     * 初始化WebView
     *
     * @param webView
     */
    private void setUpWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webView.setWebViewClient(new myWebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
    }

    private class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
            //处理js调用，传递参数
            //如果是传参数
            Log.d(TAG,"url"+url);
            if(mBridge.handleJsArgument(url,new WebViewBridge.handleJsCallback() {
                @Override
                public void onHandleJs(String func, Map<String, String> argument) {
                    String argus = "";
                    // 处理Js调用Java
                    // 根据方法名称和参数来执行java代码
                    if(func.equals("weixinshare")){
                        argus +="message:"+argument.get("message")+" callback_id:"+argument.get("callback_id");
                    }
                    // 执行Js代码
                    mBridge.executeJs(webView,"alert(\""+argus+"\")");
                    // 调用成功的回调
                    mBridge.executeSuccessCallback(view,argument.get("callback_id"),"成功之后的数据");
                }
            })){
                return true;
            }
            // 关于 Android WebView set document.referer
            Map<String, String> extraHeaders = new HashMap<String, String>();
            extraHeaders.put("Referer", url);
            view.loadUrl(url, extraHeaders);
            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            // 注入桥接代码
            view.loadUrl(mBridge.createBaseJs(WebViewDemo.this,"js/webviewBridgeBaseCode.js"));
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.setVisibility(View.VISIBLE);
            super.onPageFinished(view, url);
            // 触发注入完成事件，用于解决 注入的代码没有及时加载成功导致无法调用的问题
            mBridge.triggerOnBridgeLoadedEvent(view);
        }
    }
}