package com.xkj.xkjdemos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xkj.demos.pages.ActionbarTabActivity;

public class MainActivity extends ListActivity {

	private final String KEY_PAGE_NAME = "pageName";
	private final String KEY_PAGE_CLASS = "pageClass";
	private String[] mPageNames = new String[] { "tabs" };
	private List<Map<String, Object>> mListContent = new ArrayList<Map<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initPageList();
	}

	private void initPageList() {
		/**
		 * 初始化List数据
		 */
		// 添加页面列表
		addItem("ActionBarTabs", ActionbarTabActivity.class);
		SimpleAdapter adapter = new SimpleAdapter(this, mListContent, android.R.layout.simple_list_item_1,
				new String[] { KEY_PAGE_NAME }, new int[] { android.R.id.text1 });
		getListView().setAdapter(adapter);
	}

	/**
	 * 添加需要跳转的页面
	 * 
	 * @param pageName
	 * @param page
	 */
	private void addItem(String pageName, Class<?> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(KEY_PAGE_NAME, pageName);
		map.put(KEY_PAGE_CLASS, page);
		mListContent.add(map);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Map<String, Object> selectedMap = mListContent.get(position);
		startActivity(new Intent(this, (Class<?>) selectedMap.get(KEY_PAGE_CLASS)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
