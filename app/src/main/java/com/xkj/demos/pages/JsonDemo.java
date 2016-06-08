package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.xkj.xkjdemos.R;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fuqiang on 15/3/3.
 */
public class JsonDemo extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_demo);
        String json = "xkj";
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.d("JsonDemo", "jsonObject" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}