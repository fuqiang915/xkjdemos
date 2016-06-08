package com.xkj.demos.pages;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.xkj.xkjdemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuqiang on 15/2/10.
 */
public class ContactDemo extends Activity {

    ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mListView = (ListView) findViewById(R.id.mylist);

        List<String> names = new ArrayList<String>();

        Cursor personCur = null;

        // 获取手机通讯录信息
        ContentResolver resolver = this.getContentResolver();
        // 获取联系人信息
        personCur = resolver.query(ContactsContract.Contacts.CONTENT_URI, null,
                null, null, null);
        // 循环遍历，获取每个联系人的姓名和电话号码
        while (personCur.moveToNext()) {
            // 新建联系人对象
            // 联系人姓名
            String cname = "";
            // 联系人电话
            String cnum = "";
            // 联系人id号码
            String ID;

            ID = personCur.getString(personCur
                    .getColumnIndex(ContactsContract.Contacts._ID));
            // 联系人姓名
            cname = personCur.getString(personCur
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            // id的整型数据
            int id = Integer.parseInt(ID);

            if (id > 0) {
                // 获取指定id号码的电话号码
                Cursor c = resolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + "=" + ID, null, null);
                // 遍历游标
                while (c.moveToNext()) {
                    cnum = c.getString(c
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }

                // 将对象加入到集合中

                names.add(String.format("name:%s,phone:%s", cname, cnum));
            }
        }

        // 新建联系人适配器

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, names);
        mListView.setAdapter(adapter);

    }
}