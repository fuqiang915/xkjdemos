package com.xkj.demos.pages;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.xkj.helpers.XSQLiteOpenHelper;
import com.xkj.xkjdemos.R;

public class SQLiteHelperDemo extends Activity implements OnClickListener {

    final String DATABASE_NAME = "test_database";
    final int DATABASE_VERSION = 2;
    XSQLiteOpenHelper xsqLiteOpenHelper;

    Button mAddUser;
    TextView mUsersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper_demo);
        xsqLiteOpenHelper = new XSQLiteOpenHelper(this, DATABASE_NAME, DATABASE_VERSION);

        mAddUser = (Button) findViewById(R.id.add_user);
        mAddUser.setOnClickListener(this);
        mUsersView = (TextView) findViewById(R.id.user_list);
        mUsersView.setText(xsqLiteOpenHelper.getUsers().toString());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_user:
                xsqLiteOpenHelper.insertUser("user_" + Math.random() * 1000);
                mUsersView.setText(xsqLiteOpenHelper.getUsers().toString());
                break;
            default:
        }
    }
}
