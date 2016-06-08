package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.xkj.demos.adapter.MyRecycleAdapter;
import com.xkj.demos.models.ContactInfo;
import com.xkj.xkjdemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuqiang on 15/3/2.
 */
public class RecycleViewDemo extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_demo);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        List<ContactInfo> mContacts = new ArrayList<ContactInfo>();
        mContacts.add(new ContactInfo());
        ContactInfo c1 =new ContactInfo();
        c1.name = "12123";
        c1.email = "mail.google.com";
        c1.surname = "surname";
        mContacts.add(c1);

        recList.setAdapter(new MyRecycleAdapter(mContacts));
  //      recList.setItemAnimator(new DefaultItemAnimator());// default animator
    }
}