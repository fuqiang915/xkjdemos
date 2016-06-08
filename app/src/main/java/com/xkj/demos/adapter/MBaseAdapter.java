package com.xkj.demos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihaveu.utils.Log;
import com.xkj.helpers.VolleyLoader;

import java.util.ArrayList;

/**
 * BaseAdapter
 * Created by fuqiang on 16/2/1.
 */
public class MBaseAdapter<T> extends android.widget.BaseAdapter {

    private final String TAG = "MBaseAdapter";
    protected LayoutInflater mInfalter;
    protected ArrayList<T> mList;
    protected Context mContext;
    protected VolleyLoader mVolleyLoader;
    protected boolean mIsScrolling;


    public MBaseAdapter(Context context, ArrayList<T> list) {
        super();
        if (context == null) {
            Log.e(TAG, "Context is null");
        }
        mContext = context;
        mList = list;
        mInfalter = LayoutInflater.from(context);
        mVolleyLoader = VolleyLoader.getInstance();
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mList != null) {
            return mList.size();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setIsScrolling(boolean scrolling){
        mIsScrolling = scrolling;
    }
}
