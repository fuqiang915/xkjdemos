package com.xkj.demos.pages;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.xkj.demos.design.pattern.Finery;
import com.xkj.utils.Util;
import com.xkj.xkjdemos.R;

/**
 * 各种常用对话框的显示方式
 * 如何修改对话框的各种样式
 */
public class DialogDemoActivity extends Activity implements View.OnClickListener {

    Spinner mSpinner;
    Button mShowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        mSpinner = (Spinner) findViewById(R.id.alert_spinner);
        mShowBtn = (Button) findViewById(R.id.show_dialog);
        mShowBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_dialog:
                AlertDialog dialog = createDialog(mSpinner.getSelectedItemPosition());
                if (dialog != null) {
                    dialog.show();
                }else{
                    Util.toast(this,"is null");
                }

                break;
        }
    }

    private AlertDialog createDialog(int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        AlertDialog dialog = null;
        switch (type) {
            case 0:
                dialog = builder.setMessage("测试message").create();
                break;
            case 1:
                dialog = builder.setMessage("测试message")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Util.toast(DialogDemoActivity.this, "确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Util.toast(DialogDemoActivity.this, "取消");
                            }
                        })
                        .create();
                break;
            case 2:
                break;
            default:
                break;
        }
        return dialog;
    }

}
