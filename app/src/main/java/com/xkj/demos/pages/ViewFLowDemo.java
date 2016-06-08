package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import com.ihaveu.ui.UnScrollListView;
import com.xkj.demos.adapter.DiffAdapter;
import com.xkj.ui.CircleFlowIndicator;
import com.xkj.ui.XViewFlowSlidingTabStrip;
import com.xkj.ui.XViewFlow;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 15/1/6.
 */
public class ViewFLowDemo extends Activity {
    private final String TAG = "ViewFLowDemo";
    private XViewFlow viewFlow;
    private CircleFlowIndicator indicator;
    private UnScrollListView listView;
    private XViewFlowSlidingTabStrip mTab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flow);
        viewFlow = (XViewFlow) findViewById(R.id.view_flow);
//        indicator = (CircleFlowIndicator) findViewById(R.id.view_flow_indicator);

         /** To populate ListView in diff_view1.xml */
//        listView = (UnScrollListView) findViewById(R.id.listView1);
//        String[] names = new String[] { "Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb","Cupcake", "Donut", "Eclair", "Froyo",
//                "Gingerbread", "Honeycomb", "IceCream Sandwich"};
//         // Create an ArrayAdapter, that will actually make the Strings above
//        // appear in the ListView
//        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, names);
//
//        listView.setAdapter(listAdapter);

        mTab = (XViewFlowSlidingTabStrip) findViewById(R.id.pager_tab);

        DiffAdapter adapter = new DiffAdapter(this);
//        viewFlow.setFlowIndicator(indicator);
        viewFlow.setAdapter(adapter);
        mTab.setViewFlow(viewFlow);

        viewFlow.setOnViewSwitchListener(new XViewFlow.ViewSwitchListener() {
            @Override
            public void onSwitched(View view, int position) {
                Log.d(TAG, " position"+position+" view height"+view.getHeight());
            }
        });
        viewFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG," onItemSelected "+position+" id "+id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}