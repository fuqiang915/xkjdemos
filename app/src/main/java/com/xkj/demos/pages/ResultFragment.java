package com.xkj.demos.pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 14/11/12.
 */
public class ResultFragment extends Fragment {

    private static final String TAG = "ResultFragment";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG," onViewCreated start activity for result");
        Intent targetIntent = new Intent(getActivity(),ResultTargetActivity.class);
        startActivityForResult(targetIntent,915);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.result_fragment,null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, " Fragment resultCode " + resultCode + " requestCode" + requestCode);
    }
}
