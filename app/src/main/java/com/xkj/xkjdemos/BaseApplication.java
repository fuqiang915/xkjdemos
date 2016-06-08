package com.xkj.xkjdemos;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;

import com.ihaveu.utils.ImageHelper;
import com.ihaveu.utils.Log;
import com.xkj.helpers.VolleyLoader;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuqiang on 15/2/12.
 */
public class BaseApplication extends Application{
    private final String TAG = "BaseApplication";
    private VolleyLoader mVolleyLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, " on BaseApplication create ");

        //初始化
        VolleyLoader.init(this);
        ImageHelper.initImageCache(this);

        ApplicationLifecycleMonitor pnBackendMonitor = new ApplicationLifecycleMonitor();
        registerComponentCallbacks(pnBackendMonitor);
        registerActivityLifecycleCallbacks(pnBackendMonitor);
    }
    private class ApplicationLifecycleMonitor implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
        boolean isInBackground = true;

        @Override
        public void onConfigurationChanged(final Configuration newConfig) {
        }

        @Override
        public void onLowMemory() {
        }

        @Override
        public void onTrimMemory(final int level) {
            if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
                // We're in the Background
                isInBackground = true;
            } else {
                isInBackground = false;
            }
        }

        /**
         * This method is called when:
         * 1. the app starts (but it's not opened by a service, i.e. an activity is resumed)
         * 2. the app was in background and is now foreground
         */
        public void onFromBackground() {
            Log.d("BaseAplication", "onFromBackground ");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            if (isInBackground) {
                // was in background before
                onFromBackground();
            }
            isInBackground = false;
        }

        @Override
        public void onActivityCreated(Activity arg0, Bundle arg1) {
        }

        @Override
        public void onActivityDestroyed(Activity arg0) {
        }

        @Override
        public void onActivityPaused(Activity arg0) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity arg0, Bundle arg1) {
        }

        @Override
        public void onActivityStarted(Activity arg0) {
        }

        @Override
        public void onActivityStopped(Activity arg0) {
        }
    }
}
