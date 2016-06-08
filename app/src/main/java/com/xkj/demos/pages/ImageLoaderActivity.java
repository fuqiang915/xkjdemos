package com.xkj.demos.pages;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.ihaveu.utils.ImageHelper;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.xkj.demos.adapter.MBaseAdapter;
import com.xkj.xkjdemos.R;

import java.util.ArrayList;

public class ImageLoaderActivity extends Activity {

    private GridView mImageGrid;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        mImageGrid = (GridView) findViewById(R.id.image_grid);
        ArrayList<String> imageList = new ArrayList<String>();
        imageList.add("http://pic2.52pk.com/files/140103/3401720_184445_8200.png");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0D/05/ChMkJlaqy5qITVS0AAmwyWpDmdUAAH0TQGIEw0ACbDh444.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0D/05/ChMkJ1aqy5qICQ5CAAYVvX5zJqgAAH0TQGSD4AABhXV311.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0D/05/ChMkJ1aqy5qIP1CGAAcekkwV5GUAAH0TQGY5IgABx6q039.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0D/05/ChMkJlaqy5qIJwtjAAY7QOfDK5wAAH0TQGgNxUABjtY921.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0C/0B/ChMkJlapvG-IDhTBAARAT_oX2d8AAHxvwAj-iUABEBn581.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0C/0B/ChMkJlapvG-II8dLAAWS8OHFIVEAAHxvwAoZn0ABZMI952.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0C/0B/ChMkJ1apvG-IF7TqAAYr3oRZikkAAHxvwA4uiMABiv2296.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0C/07/ChMkJlapfQ6IJfbeAAaObROMa9YAAHwrAPjyY0ABo6F760.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0C/07/ChMkJ1apfQ-ITeUjAAf-UTJaudwAAHwrAPr5SAAB_5p019.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0C/07/ChMkJ1apfQ-IEdo-AAccdjpvPh4AAHwrQAAAAAABxyO803.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/g5/M00/0B/09/ChMkJlaoXAyIDuy4AAeh5ysTp_UAAHtPAFlS-cAB6H_134.jpg");
        mAdapter = new ImageAdapter(this, imageList);
        mImageGrid.setAdapter(mAdapter);
        //滚动时暂停加载图片
        mImageGrid.setOnScrollListener(new PauseOnScrollListener(com.nostra13.universalimageloader.core.ImageLoader.getInstance(), true, true));
    }

    private class ImageAdapter extends MBaseAdapter<String> {
        public ImageAdapter(Context context, ArrayList list) {
            super(context, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = mInfalter.inflate(R.layout.image_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.image = (ImageView) convertView.findViewById(R.id.item_image);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.image.setImageBitmap(null);
            ImageHelper.displayImage(mList.get(position), viewHolder.image);
            return convertView;
        }


        private class ViewHolder {
            private ImageView image;

        }
    }
}
