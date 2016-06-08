package com.xkj.demos.pages;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.xkj.xkjdemos.R;


import java.io.FileInputStream;

/**
 * Created by fuqiang on 15/2/10.
 */
public class MockTestDemo extends Activity {

    public final static String FILE_NAME = "myfile.txt";
    private TextView mTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_test);
        mTv = (TextView) findViewById(R.id.text_content);
        String txt = readFile(this);
        mTv.setText(txt);
    }

    public String readFile(Context context) {
        String res = "";
        try {
//            InputStream in = getResources().getAssets().open(FILE_NAME);
            final FileInputStream in = openFileInput(FILE_NAME);
            int length = in.available();
            byte[] buffer = new byte[length];

            in.read(buffer);
            in.close();
//            res = EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            return e.getMessage();
        }
        return res;
    }

    public String getText() {
        return mTv.getText().toString();
    }

}