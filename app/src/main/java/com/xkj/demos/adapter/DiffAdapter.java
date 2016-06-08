package com.xkj.demos.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import com.xkj.ui.UnScrollListView;
import com.xkj.ui.TitleProvider;
import com.xkj.xkjdemos.R;

public class DiffAdapter extends BaseAdapter implements TitleProvider {

    private static final int VIEW1 = 0;
    private static final int VIEW2 = 1;
    private static final int VIEW_MAX_COUNT = VIEW2 + 1;
    private final String[] names = {"View1", "View2"};
    private Context context;
    private LayoutInflater mInflater;

    public DiffAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("DiffAdapter", "getView "+position);
        int view = getItemViewType(position);
//        if (convertView == null) {
            switch (view) {
                case VIEW1:
                    convertView = mInflater.inflate(R.layout.diff_view1, null);

                    UnScrollListView listView = (UnScrollListView) convertView.findViewById(R.id.listView1);
                    String[] names = new String[]{"Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "Cupcake", "Donut", "Eclair", "Froyo",
                            "Gingerbread", "Honeycomb", "IceCream Sandwich"};
                    // Create an ArrayAdapter, that will actually make the Strings above
                    // appear in the ListView
                    ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(context,
                            android.R.layout.simple_list_item_1, names);
                    listView.setAdapter(listAdapter);
                    break;
                case VIEW2:
                    convertView = mInflater.inflate(R.layout.diff_view2, null);
                    break;
            }
//        }
        return convertView;
    }


    /* (non-Javadoc)
     * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
	 */
    public String getTitle(int position) {
        return names[position];
    }

}
