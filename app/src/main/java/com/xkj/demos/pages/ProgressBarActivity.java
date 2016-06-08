package com.xkj.demos.pages;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Window;
import android.widget.EditText;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 14/12/11.
 */
public class ProgressBarActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_progress_bar_demo);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, 1300);
        /**
         * 显示方式1
         */
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.show();

        /**
         * 显示方式2
         */
//        ProgressDialog.show(this,"","正在加载请稍后...");
        // Get our EditText object.
        EditText vw = (EditText)findViewById(R.id.text);

// Set the EditText's text.
        vw.setText("Italic, highlighted, bold.");

// If this were just a TextView, we could do:
// vw.setText("Italic, highlighted, bold.", TextView.BufferType.SPANNABLE);
// to force it to use Spannable storage so styles can be attached.
// Or we could specify that in the XML.

// Get the EditText's internal text storage
        Spannable str = vw.getText();

// Create our span sections, and assign a format to each.
        str.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(new BackgroundColorSpan(0xFFFFFF00), 8, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(new StyleSpan(Typeface.BOLD), 21, str.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}