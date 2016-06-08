package com.xkj.xkjdemos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xkj.demos.android.widgets.HomeActivityDemo;
import com.xkj.demos.android.widgets.ListViewArrayAdapterActivity;
import com.xkj.demos.android.widgets.ListViewSimpleAdapterActivity;
import com.xkj.demos.android.widgets.MultiScrollViewDemoActivity;
import com.xkj.demos.design.pattern.DesignPatternActivity;
import com.xkj.demos.mvp.MVPSampleActivity;
import com.xkj.demos.pages.*;
import com.xkj.fulldemos.listviewremovalanimation.ListViewRemovalAnimation;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends ListActivity {

    private final String KEY_PAGE_NAME = "pageName";
    private final String KEY_PAGE_CLASS = "pageClass";
    private String[] mPageNames = new String[]{"tabs"};
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

        addItem("自定义ImageLoader Demo",ImageLoaderActivity.class);
        addItem("Dialog Demo",DialogDemoActivity.class);
        addItem("爱奇艺-附近人波纹效果",WaterRippleActivity.class);
        addItem("爱奇艺-摇一摇动画",IShakeAnimActivity.class);
        addItem("爱奇艺-添加到下载抛物线动画",IAnimationsActivity.class);
        addItem("Cube Framework Demo",CubeDemoActivity.class);
        addItem("EventBus Demo",EventBusActivity.class);
        addItem("ThreadPool 线程池",ThreadPoolDemoActivity.class);
        addItem("ContentProvider Demo", ContentProviderActivity.class);
        addItem("ImageDownload Service", ImageDownloadActivity.class);
        addItem("HandlerThread Demo", HandlerThreadActivity.class);
        addItem("LRUCache Demo", LRUCacheActivity.class);
        addItem("Handler 线程Demo", MyThreadActivity.class);

        addItem("ActionBarTabs", ActionbarTabActivity.class);
        addItem("FragmentOnActivityResultDemo", FragmentActResultDemo.class);
        addItem("CustomView", CustomViewDemo.class);
        addItem("ListView With ArrayAdapter", ListViewArrayAdapterActivity.class);
        addItem("ListView With SimpleAdapter", ListViewSimpleAdapterActivity.class);
        addItem("SSH Request Demo", SSHActivity.class);
        addItem("MVP Sample", MVPSampleActivity.class);
        addItem("ProgressBar Demo", ProgressBarActivity.class);

        addItem("设计模式 Demo", DesignPatternActivity.class);
        addItem("MultiScrollView Demo", MultiScrollViewDemoActivity.class);
        addItem("Custom Home Item ", HomeActivityDemo.class);
        addItem("webp Image Demo", WebpDemoActivity.class);

        addItem("ViewFlow Demo", ViewFLowDemo.class);
        addItem("Paging ListView Demo", PagingListDemo.class);
        addItem("Download File", DownloadFileDemo.class);

//        addItem("OverlappingPaneLayoutDemo ",OverlappingPaneLayoutDemo.class);
        addItem("WebViewDemo 实现参数传递，回调函数传递 类似phonegap", WebViewDemo.class);

        addItem(" NestedScrollView ", NestedScrollViewDemo.class);

        addItem("Mock Test ", MockTestDemo.class);
        addItem("Handler Demo ", HandlerDemo.class);
        addItem("Contaces Demo", ContactDemo.class);
        addItem("Fragment Demo ", FragmentDemo.class);

        addItem("RecycleView Demo ", RecycleViewDemo.class);

        addItem("JsonDemo ", JsonDemo.class);
        addItem("list view remove with animation", ListViewRemovalAnimation.class);
        addItem("SQLiteOpenHelper Demo", SQLiteHelperDemo.class);

        addItem("ThreadLocal Demo", ThreadLocalActivity.class);
        addItem("IPC Messenger Demo", IPCMessengerActivity.class);

        addItem("IPC Binder AIDL Demo", IPCBinderActivity.class);
        addItem("Event Demo", EventActivity.class);
        addItem("CommonTestDemo", CommonTestActivity.class);


        SimpleAdapter adapter = new SimpleAdapter(this, mListContent, android.R.layout.simple_list_item_1,
                new String[]{KEY_PAGE_NAME}, new int[]{android.R.id.text1});
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
        Class<?> clazz = (Class<?>) selectedMap.get(KEY_PAGE_CLASS);
        Intent intent = new Intent(this, clazz);
        if (clazz == MVPSampleActivity.class) {
            JSONObject homeJson = new JSONObject();
            try {
                homeJson.put("list", "list name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            intent.putExtra("home", homeJson.toString());
        }
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
