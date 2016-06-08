package com.xkj.helpers;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.*;
import com.android.volley.toolbox.ImageLoader;
import com.ihaveu.network.LruBitmapCache;
import com.ihaveu.utils.Log;

/**
 * Created by fuqiang on 16/2/1.
 */
public class VolleyLoader {

    private static final String TAG = "VolleyLoader";
    private static VolleyLoader mVolley;
    private static RequestQueue mQueue;
    private static ImageLoader mImageLoader;

    private VolleyLoader() {

    }

    //初始化
    public static synchronized void init(Context context) {
        if (context != null) {
            mQueue = Volley.newRequestQueue(context);
            mImageLoader = new ImageLoader(mQueue, new LruBitmapCache(400000));
        }
    }

    public static synchronized VolleyLoader getInstance() {
        if (mVolley == null) {
            mVolley = new VolleyLoader();
        }
        return mVolley;
    }

    public void loadImage(ImageView imageView) {
        loadImage(imageView, imageView.getTag() + "");
    }

    public void loadImage(final ImageView imageView, final String url) {
        if (mImageLoader == null) {
            Log.e(TAG, "mImageLoader is null");
            return;
        }
        imageView.setTag(url);
        mImageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if (imageContainer.getBitmap() != null && TextUtils.equals(imageView.getTag() + "", url)) {
                    imageView.setImageBitmap(imageContainer.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }
}
