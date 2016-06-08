package com.xkj.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * 数据库操作类
 * Created by fuqiang on 15/10/21.
 */
public class XSQLiteOpenHelper extends SQLiteOpenHelper {

    public final String TAG = "MySqlHelper";

    public final String USER_TABLE = "create table user(id integer primary key autoincrement,name varchar(20))";

    public XSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public XSQLiteOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //执行一些初始化操作，比如创建数据表
        sqLiteDatabase.execSQL(USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, " 更新数据库 v1" + i + " " + i1);
//        sqLiteDatabase.execSQL("alter table user alter column id integer primary key autoincrement");
        sqLiteDatabase.execSQL("drop table if exists user");
        onCreate(sqLiteDatabase);
    }

    public boolean insertUser(String name) {
        try {

            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            db.insert("user", "id", cv);
            db.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> getUsers() {
        ArrayList<String> names = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("user", new String[]{"id,name"}, "", null, "", "", "id desc");
        if (cursor.getCount() != 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                names.add(cursor.getString(0) + "_____" + cursor.getString(1));
            }

        }
        cursor.close();
        db.close();

        return names;
    }


}
