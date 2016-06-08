package com.xkj.xkjdemos;

import com.xkj.helpers.WebViewBridge;
import junit.framework.TestCase;

import java.util.Map;

/**
 * Created by fuqiang on 15/1/29.
 */
public class WebViewBridgeTest extends TestCase{
    public WebViewBridgeTest() {
        super();
    }

    public void testParseFunc(){
        WebViewBridge bridge = new WebViewBridge();
        String url = "uapp://weixinshare?message={\"title\":\"ll\",\"content\":\"内容\"}&title=abc";
        assertEquals("weixinshare",bridge.parseFuncName(url));
        assertEquals("uapp",bridge.parseFuncName("uapp://uapp?uapp"));
        assertEquals("uapp",bridge.parseFuncName("uapp://uapp"));
        assertEquals("",bridge.parseFuncName("uapp://"));
        assertEquals("",bridge.parseFuncName(""));
        assertEquals("",bridge.parseFuncName(null));
        assertEquals("",bridge.parseFuncName("uapp"));
    }

    public void testParseJsArgument(){
        WebViewBridge bridge = new WebViewBridge();
        String url = "uapp://weixinshare?message={\"title\":\"ll\",\"content\":\"内容\"}&title=abc";
        Map<String,String> a = bridge.parseJsArgument(url);
        assertEquals("{\"title\":\"ll\",\"content\":\"内容\"}",a.get("message"));
        assertEquals("abc",a.get("title"));
        assertEquals(null,bridge.parseJsArgument(null));
        assertEquals(null,bridge.parseJsArgument(""));
        assertEquals(null,bridge.parseJsArgument("uapp://"));
        assertEquals(null,bridge.parseJsArgument("message"));
        a = bridge.parseJsArgument("uapp://?title=xkj");
        assertEquals("xkj",a.get("title"));
    }

    public void testDemo(){

    }
}
