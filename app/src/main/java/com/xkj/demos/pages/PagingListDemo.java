package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import com.xkj.demos.adapter.PageListAdapter;
import com.xkj.ui.paginglist.PagingBaseAdapter;
import com.xkj.ui.paginglist.PagingListView;
import com.xkj.xkjdemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuqiang on 15/1/8.
 */
public class PagingListDemo extends Activity {
    private PagingListView mListView;
    private PageListAdapter mAdapter;
    private int pager = 0;


    private List<String> firstList;
    private List<String> secondList;
    private List<String> thirdList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_list);
        mListView = (PagingListView) findViewById(R.id.paging_list);
        mAdapter = new PageListAdapter();
        initData();
        mListView.setAdapter(mAdapter);
        mListView.setHasMoreItems(true);
        mListView.setPagingableListener(new PagingListView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                if (pager < 3) {
                    List<String> list = null;
                    switch (pager) {
                        case 0:
                            list = firstList;
                            break;
                        case 1:
                            list = secondList;
                            break;
                        case 2:
                            list = thirdList;
                            break;
                    }
                    mListView.onFinishLoading(true, list);
                    pager++;
                } else {
                    mListView.onFinishLoading(false, null);
                }
            }
        });
    }

    private void initData() {
        firstList = new ArrayList<String>();
        firstList.add("Afghanistan");
        firstList.add("Albania");
        firstList.add("Algeria");
        firstList.add("Andorra");
        firstList.add("Angola");
        firstList.add("Antigua and Barbuda");
        firstList.add("Argentina");
        firstList.add("Armenia");
        firstList.add("Aruba");
        firstList.add("Australia");
        firstList.add("Austria");
        firstList.add("Azerbaijan");

        secondList = new ArrayList<String>();
        secondList.add("Bahamas, The");
        secondList.add("Bahrain");
        secondList.add("Bangladesh");
        secondList.add("Barbados");
        secondList.add("Belarus");
        secondList.add("Belgium");
        secondList.add("Belize");
        secondList.add("Benin");
        secondList.add("Bhutan");
        secondList.add("Bolivia");
        secondList.add("Bosnia and Herzegovina");
        secondList.add("Botswana");
        secondList.add("Brazil");
        secondList.add("Brunei");
        secondList.add("Bulgaria");
        secondList.add("Burkina Faso");
        secondList.add("Burma");
        secondList.add("Burundi");

        thirdList = new ArrayList<String>();
        thirdList.add("Denmark");
        thirdList.add("Djibouti");
        thirdList.add("Dominica");
        thirdList.add("Dominican Republic");
    }

}
