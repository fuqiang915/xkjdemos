package com.xkj.demos.pages;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.xkj.utils.MyLRUCache;
import com.xkj.xkjdemos.R;

public class LRUCacheActivity extends Activity {

    private String TAG = "LRUCacheActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrucache);

        test();

    }

    private void test() {
        //Test LRUCache
        MyLRUCache cache = new MyLRUCache(6);
        cache.put("a",new MyLRUCache.Entity("A"));
        cache.put("b",new MyLRUCache.Entity("B"));
        cache.put("c",new MyLRUCache.Entity("C"));
        Log.d(TAG, "LRUCache "+cache);

        cache.put("d",new MyLRUCache.Entity("D"));
        cache.put("e",new MyLRUCache.Entity("E"));
        cache.put("f",new MyLRUCache.Entity("F"));
        Log.d(TAG, "LRUCache "+cache);

        cache.put("a",new MyLRUCache.Entity("Z"));
        cache.put("b",new MyLRUCache.Entity("BB"));
        Log.d(TAG, "LRUCache "+cache);

        cache.get("a");
        Log.d(TAG, "LRUCache "+cache);

        cache.put("z",new MyLRUCache.Entity("X"));
        Log.d(TAG, "超出size 删除表头元素 LRUCache "+cache);

    }

}
