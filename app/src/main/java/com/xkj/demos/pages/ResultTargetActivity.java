package com.xkj.demos.pages;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 14/11/12.
 */
public class ResultTargetActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_target);
        setResult(915);

        findViewById(R.id.button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultTargetActivity.this.finish();
            }
        });
    }
}