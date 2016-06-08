package com.xkj.demos.pages;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.xkj.helpers.AnimationHelper;
import com.xkj.helpers.ParabolaAlgorithm;
import com.xkj.xkjdemos.R;

public class IShakeAnimActivity extends Activity implements View.OnClickListener {

    ImageView mImageView;
    Button mShakeBtn;
    Button mMoveBtn;
    AnimationHelper mAnimationHelper;
    View mDownloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ishake_anim);
        mImageView = (ImageView) findViewById(R.id.shake_image);
        mShakeBtn = (Button) findViewById(R.id.shake_btn);
        mMoveBtn = (Button) findViewById(R.id.move_btn);
        mDownloadBtn = findViewById(R.id.download_btn);

        mShakeBtn.setOnClickListener(this);
        mMoveBtn.setOnClickListener(this);
        mDownloadBtn.setOnClickListener(this);

        mAnimationHelper = new AnimationHelper();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shake_btn:
                mAnimationHelper.shake(mImageView, 800, 3000);
                break;
            case R.id.move_btn:
                mAnimationHelper.parabola(mImageView, mDownloadBtn.getX(), mDownloadBtn.getY(), 500);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAnimationHelper.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
