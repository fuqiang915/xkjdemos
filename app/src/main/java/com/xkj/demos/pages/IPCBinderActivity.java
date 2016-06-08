package com.xkj.demos.pages;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.xkj.demos.aidl.IBookManager;
import com.xkj.demos.models.Book;
import com.xkj.demos.services.BookManagerService;
import com.xkj.xkjdemos.R;

import java.util.List;

public class IPCBinderActivity extends FragmentActivity {

    private static final String TAG = "IPCBinderActivity";
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);

            try {
                List<Book> bookList = bookManager.getBookList();
                Log.i(TAG, " get Book list" + bookList + " " + bookList.getClass().getCanonicalName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipcbinder);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

}
