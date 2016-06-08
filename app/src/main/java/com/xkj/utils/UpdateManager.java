package com.xkj.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.RemoteViews;
import com.ihaveu.utils.Log;
import com.xkj.xkjdemos.R;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.HttpHandler;

import java.io.File;

/**
 * 更新管理
 * Created by fuqiang on 15/1/12.
 */
public class UpdateManager {
    /**
     * 下载的文件的名称和地址
     */
    private String mFileName = "";
    private final String TAG = "UpdateManager";
    private String DEFAULT_FILE_NAME = "uapp.apk";
    private String basePath = "";
    private final int PROGRESS_ID = 0;
    private Notification notification;
    private NotificationManager notificationManager;
    private HttpHandler mHandler;
    private final int NOTIFICATION_ID = 0x2915;

    private Context mContext;

    private static UpdateManager mUpdateManager;
    private boolean mIsUpdating = false;

    private UpdateManager(Context context) {
        mContext = context;
        mFileName = ensureFileName(mFileName);
        initNotification();
    }

    public static UpdateManager getInstance(Context context) {
        if (mUpdateManager == null) {
            mUpdateManager = new UpdateManager(context);
        }
        return mUpdateManager;
    }

    public void setFileName(String file) {
        mFileName = ensureFileName(file);
    }

    private String ensureFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return DEFAULT_FILE_NAME;
        }
        return fileName;
    }


    public void install(String file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(String.valueOf(file))), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

    public void install(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

    public interface IDownload {
        public void onSuccess(File file);

        public void onFailure(Throwable t, int errorNo, String msg);

        public void onLoading(long count, long current);
    }

    /**
     * 下载
     *
     * @param url
     * @param onDownload
     */
    public void download(String url, final IDownload onDownload) {
        if (mIsUpdating) {
            Log.d(TAG, "正在更新");
            return;
        }
        mIsUpdating = true;
        Log.d(TAG, " start download");
        FinalHttp fh = new FinalHttp();
        //调用download方法开始下载
        //如果下载过 ，先删除
        deleteApk(getFilePath());
        mHandler = fh.download(url, //这里是下载的路径
                getFilePath(), //这是保存到本地的路径
                true,//true:断点续传 false:不断点续传（全新下载）
                new AjaxCallBack<File>() {

                    @Override
                    public void onSuccess(File file) {
                        notificationManager.cancel(NOTIFICATION_ID);
                        install(file);
                        mIsUpdating = false;
                        onDownload.onSuccess(file);

                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        notificationManager.cancel(NOTIFICATION_ID);
                        mIsUpdating = false;
                        deleteApk(getFilePath());
                        onDownload.onFailure(t, errorNo, strMsg);
                    }

                    @Override
                    public void onLoading(long count, long current) {
                        mIsUpdating = true;
                        notification.contentView.setProgressBar(R.id.notification_progressbar, (int) count, (int) current, false);
                        long rate = current * 100 / count;
                        notification.contentView.setTextViewText(R.id.notification_progress_tv, "当前进度:" + rate + "%");
                        notificationManager.notify(NOTIFICATION_ID, notification);
                        onDownload.onLoading(count, current);
                    }

                }

        );
    }

    /**
     * 1、判断SD卡是否存在
     */
    public static boolean hasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取路径
     *
     * @return
     */
    public String getBasePath() {
        if (basePath == null || basePath.isEmpty()) {
            if (hasSdcard()) {
                basePath = Environment.getExternalStorageDirectory().getPath() + "/uapp_download/";
            } else {
                basePath = "/uapp_download/";
            }
            createPath(basePath);
        }
        return basePath;
    }

    public String getFilePath() {
        Log.d(TAG, " FilePath " + getBasePath() + mFileName);
        return getBasePath() + mFileName;
    }

    /**
     * 5、创建目录
     */
    public static void createPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 停止下载
     */
    public void stop() {
        try {
            if (mHandler != null) {
                mHandler.stop();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void deleteApk(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public void cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID);
    }

    private void initNotification() {
        notification = new Notification();
        notification.icon = R.drawable.ic_launcher;
        notification.when = System.currentTimeMillis();
        notification.contentView = new RemoteViews(((Activity) mContext).getApplication().getPackageName(), R.layout.download_notification);
        notification.contentView.setProgressBar(R.id.notification_progressbar, 100, 0, false);
        notification.contentView.setTextViewText(R.id.notification_progress_tv, "当前进度:0%");
        notificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
    }
}
