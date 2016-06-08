package com.xkj.demos.android.widgets;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import com.xkj.xkjdemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuqiang on 14/11/29.
 */
public class ListViewSimpleAdapterActivity extends ListActivity {
    private String [] mTitles = new String[]{"姓名","性别","公司","职位","GF"};
    private String [] mContent = new String[]{"陈福强","男","优众","Android开发","汪萍"};
    private SimpleAdapter mAdapter ;
    private ArrayList<Map<String,Object>> mListData = new ArrayList<Map<String, Object>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<mTitles.length;i++){
            Map<String,Object> item = new HashMap();
            item.put("title",mTitles[i]);
            item.put("text",mContent[i]);
            mListData.add(item);
        }
        mAdapter = new SimpleAdapter(this,mListData,android.R.layout.simple_list_item_2,new String[]{"title","text"},new int[]{android.R.id.text1,android.R.id.text2});
        setListAdapter(mAdapter);
    }
}