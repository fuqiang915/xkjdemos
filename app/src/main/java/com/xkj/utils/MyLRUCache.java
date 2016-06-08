package com.xkj.utils;

import android.graphics.Bitmap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 图片缓存
 * Created by fuqiang on 15/12/7.
 */
public class MyLRUCache {
    private final LinkedHashMap<String, MyLRUCache.Entity> mCaches;

    private final int DEFAULT_SIZE = 1024 * 1024;
    private int mMaxSize = DEFAULT_SIZE;

    public MyLRUCache(int maxSize) {
        super();
        mCaches = new LinkedHashMap<>(1, 0.75f, true);
        mMaxSize = maxSize >= 1 ? maxSize : DEFAULT_SIZE;
    }

    public Entity put(String key, Entity entity) {
        synchronized (mCaches) {
            if (mCaches.keySet().size() >= mMaxSize) {
                removeOldest();
            }
            return mCaches.put(key, entity);
        }
    }

    public Entity get(String key) {
        synchronized (mCaches) {
            return mCaches.get(key);
        }
    }

    private void removeOldest() {
        while (true) {
            synchronized (mCaches) {
                if (mCaches.keySet().size() < mMaxSize) {
                    return;
                }
                Map.Entry entry = (Map.Entry) mCaches.entrySet().iterator().next();
                mCaches.remove(entry.getKey());
            }
        }
    }

    public static class Entity {
        private Object body;

        public Entity(String value) {
            super();
            body = value;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Iterator<String> iterator = ((LinkedHashMap<String, Entity>) mCaches.clone()).keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            sb.append(key);
            sb.append(":");
            sb.append(mCaches.get(key).body);
            sb.append(" ");
        }
        return sb.toString();
    }
}

