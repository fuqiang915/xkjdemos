package com.xkj.demos.pages;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xkj.demos.services.ImageDownloaderService;
import com.xkj.xkjdemos.R;

import java.lang.ref.WeakReference;

public class ImageDownloadActivity extends Activity implements View.OnClickListener {

    Button mBtn;
    ImageView mImageView;
    MyReceiver myReceiver;
    ServiceConnection mServiceConnection;
    boolean mIsBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_download);
        mBtn = (Button) findViewById(R.id.download_image);
        mImageView = (ImageView) findViewById(R.id.my_image);
        mBtn.setOnClickListener(this);
        myReceiver = new MyReceiver();
        mServiceConnection = new MServiceConnection(mImageView);

        int test = 1;
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(myReceiver.M_ACTION);
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (myReceiver != null) unregisterReceiver(myReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceConnection != null && mIsBound) {
            unbindService(mServiceConnection);
            mServiceConnection = null;
        }
    }

    @Override
    public void onClick(View v) {
        //启动Service下载图片
        Intent intent = new Intent(this, ImageDownloaderService.class);
        intent.putExtra(ImageDownloaderService.IMAGE_URL, "http://desk.fd.zol-img.com.cn/g2/M00/06/05/Cg-4WlVIiuOIetbHAAZh5LasN-cAAC9CQMZVDAABmH8937.jpg");
//        startService(intent);

        //BindService
        mIsBound = bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

    }

    public class MyReceiver extends BroadcastReceiver {
        public String M_ACTION = "Download_done";

        @Override
        public void onReceive(Context context, Intent intent) {
            byte[] img = intent.getByteArrayExtra("image");
            Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
            mImageView.setImageBitmap(image);
        }
    }

    public static class MServiceConnection implements ServiceConnection {

        private WeakReference<ImageView> mImageView;

        public MServiceConnection(ImageView imageView) {
            super();
            mImageView = new WeakReference<ImageView>(imageView);
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ImageDownloaderService.MBinder mBinder = (ImageDownloaderService.MBinder) service;
            mBinder.setDownloadListener(new ImageDownloaderService.OnDownLoaded() {
                @Override
                public void onDownload(Bitmap image) {
                    if (mImageView.get() != null)
                        mImageView.get().setImageBitmap(image);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
