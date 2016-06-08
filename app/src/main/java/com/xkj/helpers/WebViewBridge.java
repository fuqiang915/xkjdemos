package com.xkj.helpers;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.ihaveu.utils.Log;
import com.xkj.demos.interfaces.IWebViewBridge;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fuqiang on 15/1/28.
 */
public class WebViewBridge implements IWebViewBridge {

    private final String TAG = "WebViewBridge";

    @Override
    public String createBaseJs() {
        String js = "(function(){window._callbacks = {}; window._callbacks_id = 0; window.UAPPJSBridge ={weixinshare:function(message,success,error){var id = _callbacks_id++; _callbacks[id]={success:success, error:error }; window.location.href= \"uapp://weixinshare?message='i am message '&callback_id=\"+id; return id; } }; })();";
        return "javascript:" + js;
    }

    @Override
    public String createBaseJs(Context context, String fileName) {
        String js = "";
        FileReader fileReader = new FileReader();
        js = fileReader.readFileFromAssets(context,fileName);
        /**
         * 注意：加载的代码中不能有注释，尤其是"//"的注释
         * 代码必须包裹在 (function(){  代码here  })();
         */
        // 处理加载完成的代码，删除换行和制表符
        js=JavaScriptCompressor.compress(js);
        Log.d(TAG, " js from file:\n" + js);
        return "javascript:"+js;
    }

    @Override
    public void executeJs(WebView view, String jsCode) {
        view.loadUrl("javascript:" + jsCode);
    }

    @Override
    public java.util.Map<String, String> parseJsArgument(String argument) {
        Map<String, String> argMap = new HashMap<String, String>();
        if (argument == null || argument.isEmpty() || !argument.startsWith(UAPP_PRE)|| argument.indexOf("?")==-1) {
            return null;
        }
        //TODO:解析参数
        String pstr = "\\w+=[^\\s~&]*";
        argument = argument.substring(argument.indexOf("?") + 1);
        String []result = argument.split("&");
        for(int i=0;i<result.length;i++){
            String item = result[i];
            if(item.indexOf("=")!=-1){
                argMap.put(item.substring(0, item.indexOf("=")), item.substring(item.indexOf("=") + 1));
            }
        }
        return argMap;
    }

    @Override
    public boolean handleJsArgument(String url, handleJsCallback jscallback) {
        if (url == null || !url.startsWith(UAPP_PRE)) {
            return false;
        }

        //解析参数
        //参数格式  uapp://weixinshare?message=99&abc=lsdf
        String func = parseFuncName(url);
        jscallback.onHandleJs(func, parseJsArgument(url));
        return true;
    }

    @Override
    public void triggerOnBridgeLoadedEvent(WebView view) {
       executeJs(view,"(function(){if(window.onBridgeLoaded){window.onBridgeLoaded();}})()");
    }

    @Override
    public void executeSuccessCallback(WebView view, String callbackId, String argument) {
        executeJs(view,"try{if(window._callbacks&&window._callbacks["+callbackId+"])window._callbacks["+callbackId+"].success(\""+argument+"\")}catch(ex){}");
    }

    @Override
    public void executeErroCallback(WebView view, String callbackId, String argument) {
        executeJs(view,"try{if(window._callbacks&&window._callbacks["+callbackId+"])window._callbacks["+callbackId+"].error(\""+argument+"\")}catch(ex){}");
    }

    public String parseFuncName(String url) {
        if (url == null || url.isEmpty()) {
            return "";
        }
        String a = UAPP_PRE + "\\w+\\??";
        String result = "";
        Pattern pattern = Pattern.compile(a);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            result = matcher.group(0);
            int start = 0, end = 0;
            start = result.indexOf("/") + 2;
            start = start >= result.length() ? result.length() : start;
            if (result.indexOf("?") == -1) {
                end = result.length();
            } else {
                end = result.indexOf("?");
            }
            return result.substring(start, end);
        }
        return result;
    }

    /**
     * 处理Js传递过来的参数
     */
    public static interface handleJsCallback {
        public void onHandleJs(String func, Map<String, String> argument);
    }

}
