package com.xkj.demos.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.xkj.demos.pages.HandlerThreadActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.lang.ref.SoftReference;

public class ImageDownloaderService extends BaseService {

    public static final String IMAGE_URL = "P_Image_Url";
    private static final String TAG = "ImageDownloaderService";

    private DownloadThread mThread;
    private OnDownLoaded mDownloadListener;
    private Handler mHandler;

    public interface OnDownLoaded {
        void onDownload(Bitmap image);
    }

    public class MBinder extends Binder {
        public void setDownloadListener(OnDownLoaded onDownLoaded) {
            mDownloadListener = onDownLoaded;
        }
    }



    class AHandler extends Handler{
        private void t(){
            mHandler.sendMessage(new Message());
        }
    }

    class A {
        private void t(){
            mHandler.sendMessage(new Message());
        }
    }


    static class MHandler extends Handler {

        private SoftReference<Context> mWContext;
        private SoftReference<Service> mWService;
        private SoftReference<OnDownLoaded> mWDownloadListener;

        public MHandler(Context context, OnDownLoaded listener, Service service) {
            super();
            mWContext = new SoftReference<Context>(context);
            mWDownloadListener = new SoftReference<OnDownLoaded>(listener);
            mWService = new SoftReference<Service>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (mWContext.get() != null) {
                    Toast.makeText(mWContext.get(), "下载完成", Toast.LENGTH_SHORT).show();
                }
                Bundle bundle = (Bundle) msg.obj;

                /**
                 * One
                 */
//                Intent intent = new Intent();
//                intent.setAction("Download_done");
//                sendBroadcast(intent);

                if (mWDownloadListener.get() != null) {
                    mWDownloadListener.get().onDownload((Bitmap) bundle.getParcelable("image_bitmap"));
                }
            } else {
                if (mWContext.get() != null) {
                    Toast.makeText(mWContext.get(), "下载失败" + msg.what, Toast.LENGTH_SHORT).show();
                }
            }

            if (mWService.get() != null) {
                mWService.get().stopSelf();// 下载完成后停止当前Service
            }
        }
    }

    public ImageDownloaderService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mThread = new DownloadThread();
        mHandler = new MHandler(getApplicationContext(), mDownloadListener, this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendMessage(new Message());
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        init(intent);
        return new MBinder();
    }

    /**
     * 需要下载网络图片
     * 问题：
     * 下载完成之后需要停止Service
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void init(Intent intent) {
        String targetUrl = intent.getStringExtra(IMAGE_URL);

        mThread.setUrl(targetUrl);
        mThread.start();

        Log.d(TAG, " TargetUrl " + targetUrl);
        Toast.makeText(getApplicationContext(), "Start Downlad " + targetUrl, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mThread.interrupt();
        mThread = null;
    }

    public class DownloadThread extends Thread {

        private String mUrl = "";

        public DownloadThread() {
        }

        public void setUrl(String url) {
            mUrl = url;
        }

        @Override
        public void run() {
            Log.d(TAG, "run Download Imaging  ");
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(mUrl);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                    byte[] result = EntityUtils.toByteArray(response.getEntity());
                    //TODO: 保存到本地
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("image_bitmap", convertBitmap(result));
                    bundle.putByteArray("image_data", result);
                    msg.what = 1;
                    msg.obj = bundle;
                    mHandler.sendMessage(msg);
                } else {
                    mHandler.sendEmptyMessage(response.getStatusLine().getStatusCode());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        private Bitmap convertBitmap(byte[] image) {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }

    }

}

