package com.xkj.demos.pages;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.xkj.utils.BitmapLruCache;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 14/12/1.
 */
public class SSHActivity extends Activity implements View.OnClickListener {

    ImageLoader mImgLoader;
    RequestQueue mQueue;
    private Button mLoadBtn;
    private ImageView mImg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssh);
        mLoadBtn = (Button) findViewById(R.id.loadImage);
        mImg = (ImageView) findViewById(R.id.show_img);

        mLoadBtn.setOnClickListener(this);
        mQueue = Volley.newRequestQueue(this);

        mImgLoader = new ImageLoader(mQueue,new BitmapLruCache());
    }

    @Override
    public void onClick(View v) {
        mImgLoader.get("https://www.ihaveu.com/image/auction/product/003/935/071/major_pic/d3fc3b59.jpg.l200.jpg", new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if(imageContainer.getBitmap()!=null)
                mImg.setImageBitmap(imageContainer.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }
}