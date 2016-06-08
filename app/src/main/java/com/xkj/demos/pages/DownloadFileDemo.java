package com.xkj.demos.pages;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;
import com.ihaveu.utils.Log;
import com.xkj.utils.UpdateManager;
import com.xkj.xkjdemos.R;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.HttpHandler;

import java.io.File;

/**
 * Created by ihaveu on 14-11-2.
 */
public class DownloadFileDemo extends Activity implements View.OnClickListener {

    private final String TAG = "DownloadFileDemo";
    private TextView mFileContent;
    private Button mDownloadBtn;
    private Button mInstallBtn;

    private RemoteViews mNotificationView = null;
    private ProgressBar mProgressBar = null;
    private TextView mProgressTV = null;

    private Notification notification = new Notification();
    private NotificationManager notificationManager = null;
    String mFile = "/mnt/sdcard/testapk1.apk";
    private int mProgressBarId = 0;
    UpdateManager updateManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_file);
        mFileContent = (TextView) findViewById(R.id.download_content);
        mDownloadBtn = (Button) findViewById(R.id.download_file_btn);
        mDownloadBtn.setOnClickListener(this);
        mInstallBtn = (Button) findViewById(R.id.install_apk);
        mInstallBtn.setOnClickListener(this);
        mProgressBarId = R.id.notification_progressbar;
//        initNotification();
        updateManager = UpdateManager.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_file_btn:
                updateManager.download("http://www.ihaveu.com/data/common/files/488/get", new UpdateManager.IDownload() {
                    @Override
                    public void onSuccess(File file) {
                        Toast.makeText(DownloadFileDemo.this, "下载成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String msg) {
                        Toast.makeText(DownloadFileDemo.this, "下载失败" + msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLoading(long count, long current) {
                    }
                });
                break;
            case R.id.install_apk:
                install(mFile);
                break;
        }
    }

    private void install(String file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(String.valueOf(file))), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void download() {
        Log.d(TAG, " start download");
        FinalHttp fh = new FinalHttp();
        //调用download方法开始下载
        //如果下载过 ，先删除
        deleteApk(mFile);
        fh.configRequestExecutionRetryCount(10);
        fh.configTimeout(60000);
        HttpHandler handler = fh.download("http://www.ihaveu.com/data/common/files/488/get", //这里是下载的路径
                mFile, //这是保存到本地的路径
                true,//true:断点续传 false:不断点续传（全新下载）
                new AjaxCallBack<File>() {

                    @Override
                    public void onSuccess(File file) {
                        mFileContent.setText("done\n" + file);
                        notificationManager.cancel(0);
                        //String str = "/CanavaCancel.apk";
                        //String fileName = Environment.getExternalStorageDirectory() + str;
                        install(mFile);
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        mFileContent.setText("failure" + strMsg);
                        deleteApk(mFile);
                    }

                    @Override
                    public void onLoading(long count, long current) {
                        mFileContent.setText("Current :" + current + "/" + count);
                        notification.contentView.setProgressBar(mProgressBarId, (int) count, (int) current, false);
                        long rate = current * 100 / count;
                        notification.contentView.setTextViewText(R.id.notification_progress_tv, "当前进度:" + rate + "%");
                        notificationManager.notify(0, notification);
                    }

                }

        );

        //调用stop()方法停止下载
//        handler.stop();
    }

    private void deleteApk(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    private void initNotification() {
        notification.icon = R.drawable.ic_launcher;
        notification.when = System.currentTimeMillis();
        notification.contentView = new RemoteViews(getApplication().getPackageName(), R.layout.download_notification);
        notification.contentView.setProgressBar(R.id.notification_progressbar, 100, 0, false);
        notification.contentView.setTextViewText(R.id.notification_progress_tv, "当前进度:0%");
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

}