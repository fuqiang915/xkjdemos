package com.xkj.demos.android.widgets;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xkj.xkjdemos.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by fuqiang on 14/11/29.
 */
public class ListViewArrayAdapterActivity extends Activity implements AdapterView.OnItemClickListener {
    private final String TAG = "ListViewDemoActivity";
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> mListData;
    private View mHeaderView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
        mListView = (ListView)findViewById(R.id.list_view);
        mListData = new ArrayList<String>();
        for(int i=0;i<20;i++){
            mListData.add(i+" item ");
        }
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mListData);
        mListView.setAdapter(mAdapter);
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.header_view_list_view,null);
        mListView.addHeaderView(mHeaderView);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((TextView)(mHeaderView.findViewById(R.id.header_view_text))).setText(mListData.get(position)+" header");
    }
}