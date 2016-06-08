package com.xkj.helpers;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;


/**
 * 动画帮助类
 * min SDK 10
 * Created by fuqiang on 16/1/8.
 */
public class AnimationHelper {

    private boolean mHasStop = false;

    private final String TAG = "AnimationHelper";

    /**
     * 摇一摇抖动
     * 属性动画实现
     */
    public void shake(View view, long duration, final long midDelay) {

        mHasStop = false;
        if(view.getTag() ==true){
            return;
        }
        view.setTag(true);

        Animator shake = ObjectAnimator
                .ofFloat(view, "translationX", 0.0F, -20F, 0f, 20f, 0f, -20f, 0f)
                .setDuration(600);
        Animator rotate = ObjectAnimator
                .ofFloat(view, "rotation", 0f, -30f, 0f, 30f, 0f, -30f, 0f);


        final AnimatorSet as = new AnimatorSet();
        rotate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!mHasStop) {
                    as.setStartDelay(midDelay);
                    as.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        as.playSequentially(shake, rotate);
        as.setDuration(duration);
        as.start();
    }

    /**
     * 停止循环
     */
    public void release() {
        //停止循环
        mHasStop = true;
    }


    /**
     * 抛物线动画
     *
     * @param view
     */
    public void parabola(final View view, final float targetPointX, final float targetPointY, final long duration) {
        final float startX = view.getLeft();
        final float startY = view.getTop();
        final int defaultWidth = view.getWidth();
        final int defaultHeight = view.getHeight();
        //计算抛物方程参数
        final ParabolaAlgorithm.ParabolaParam param = ParabolaAlgorithm.caculate(targetPointX, targetPointY, startX, startY);

        //动画
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(duration);
        valueAnimator.setObjectValues(new EvaluatorResult());
        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.setEvaluator(new TypeEvaluator<EvaluatorResult>() {
            @Override
            public EvaluatorResult evaluate(float fraction, EvaluatorResult startValue,
                                            EvaluatorResult endValue) {
                EvaluatorResult result = new EvaluatorResult();
                //X方向值, 起点->顶点
                Log.d(TAG, " mid " + ((targetPointX - startX) * fraction));
                result.x = startX + ((targetPointX - startX) * fraction);
                result.y = getY(result.x, param);
                result.scaleX = 1 - fraction * 0.5f;
                result.scaleY = 1 - fraction * 0.5f;
                return result;
            }
        });


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                EvaluatorResult result = (EvaluatorResult) animation.getAnimatedValue();
                Log.d(TAG, " x" + result.x + "  y" + result.y + " scaleX " + result.scaleX + "  scaleY " + result.scaleY + " " + animation.getAnimatedFraction());

//              view.setX(result.x);
//              view.setY(result.y);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                params.width = (int) (defaultWidth * result.scaleX);
                params.height = (int) (defaultHeight * result.scaleX);
                params.setMargins((int) (result.x - startX), (int) (result.y - startY), 0, 0);
                view.setLayoutParams(params);
//              view.setScaleX(result.scaleX);
//              view.setScaleY(result.scaleY);
            }
        });
        valueAnimator.start();
    }

    /**
     * 渐隐
     *
     * @param view
     * @param duration
     */
    public void fadeOut(View view, long duration) {
        ObjectAnimator.ofFloat(view, "alpha", 0, 1.0f).setDuration(duration).start();
    }

    /**
     * 渐现
     *
     * @param view
     * @param duration
     */
    public void fadeIn(View view, long duration) {
        ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f).setDuration(duration).start();
    }

    /**
     * 抛物线方程求Y
     *
     * @param x
     * @return
     */
    private float getY(float x, ParabolaAlgorithm.ParabolaParam params) {
        return params.getA() * x * x + params.getB() * x + params.getC();
    }

    public class EvaluatorResult {
        public float x;
        public float y;
        public float scaleX;
        public float scaleY;
    }

}
