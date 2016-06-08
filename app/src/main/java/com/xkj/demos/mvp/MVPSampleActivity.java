package com.xkj.demos.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.xkj.xkjdemos.R;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * MVP 模式的例子
 * 模拟：UApp中的HomeActivity类
 *
 * Created by fuqiang on 14/12/10.
 */
public class MVPSampleActivity extends Activity implements IHomeView{

    private final static String TAG = "MVPSampleActivity";
    private HomePresenter mHomePresenter;
    private TextView mLogView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_sample);
        mLogView = (TextView) findViewById(R.id.log_view);

        mHomePresenter = new HomePresenter(this,this,getIntent());
        //加载列表数据
        mHomePresenter.loadHomeJson();
        mHomePresenter.loadLimitJson();
    }

    /**
     * 渲染商场限额
     * @param limitResponse
     */
    @Override
    public void renderLimit(JSONObject limitResponse) {
        Log.d(TAG, " render Limit "+limitResponse);
        mLogView.append("\n------"+limitResponse.toString());
    }

    /**
     * 渲染Home列表
     * @param homeResponse
     */
    @Override
    public void renderHomeList(JSONObject homeResponse) {
        Log.d(TAG, " render Home List "+homeResponse);
        mLogView.append("\n------"+homeResponse.toString());
    }

    /**
     * 渲染用户信息
     * @param userResponse
     */
    @Override
    public void renderUserInfo(JSONObject userResponse) {
        Log.d(TAG, " render User Info");

    }
}