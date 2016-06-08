package com.xkj.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import com.android.volley.toolbox.ImageLoader.ImageCache;

import java.util.ArrayList;

//TODO:  考虑是否是 加载图片时缓存的问题，在这里添加一个清除缓存数据的方法
public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {

    private final String TAG = "BitmapLruCache";
    private ArrayList<String> mImageKeys;
    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        return cacheSize;
    }

    public BitmapLruCache() {
        this(getDefaultLruCacheSize());
        mImageKeys = new ArrayList<String>();
    }

    public BitmapLruCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
        mImageKeys = new ArrayList<String>();
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mImageKeys.add(url);
        put(url, bitmap);
    }

    /**
     * 清空所有缓存
     */
    public void release(){
        Log.d(TAG, " release all cache: " + mImageKeys);
      for(String key : mImageKeys ) {
          Log.d(TAG," 遍历key "+key);
          Bitmap bitmap = getBitmap(key);
          if(bitmap!=null &&!bitmap.isRecycled() ){
              Log.d(TAG," release recycle bitmap "+key);
              bitmap.recycle();
          }else{
              Log.d(TAG,"release not my recycle bitmap "+bitmap + " isRecycled ");
          }
          remove(key);
      }
        mImageKeys.clear();
    }

}