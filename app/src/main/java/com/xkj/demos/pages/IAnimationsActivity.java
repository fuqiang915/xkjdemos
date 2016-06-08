package com.xkj.demos.pages;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xkj.helpers.ParabolaAlgorithm;
import com.xkj.ui.FlowLayout;
import com.xkj.xkjdemos.R;

public class IAnimationsActivity extends Activity {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ianimations);
//        mImageView = (ImageView) findViewById(R.id.anim_image);

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setPadding(0, 100, 0, 0);
        layout.setOrientation(LinearLayout.VERTICAL);

        final ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 200, 0, 0);
        imageView.setLayoutParams(params);

        final TextView textView1 = new TextView(this);
        textView1.setText("第一坐标点（空格区分x y）");
        layout.addView(textView1);
        final EditText editText1 = new EditText(this);
        editText1.setPadding(20, 10, 20, 10);
        editText1.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        editText1.setHint("第一坐标点（空格区分x y）");
        editText1.setText("6 15");
        editText1.setSingleLine();
        editText1.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        layout.addView(editText1);

        final TextView textView2 = new TextView(this);
        textView2.setText("第二坐标点（空格区分x y）");
        layout.addView(textView2);
        final EditText editText2 = new EditText(this);
        editText2.setHint("第二坐标点（空格区分x y）");
        editText2.setPadding(20, 10, 20, 10);
        editText2.setSingleLine();
        editText2.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        editText2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        editText2.setText("50 70");
        layout.addView(editText2);

        final TextView textView3 = new TextView(this);
        textView3.setText("第三坐标点（空格区分x y）");
        layout.addView(textView3);
        final EditText editText3 = new EditText(this);
        editText3.setHint("第三坐标点（空格区分x y）");
        editText3.setPadding(20, 10, 20, 10);
        editText3.setSingleLine();
        editText3.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        editText3.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, FlowLayout.LayoutParams.WRAP_CONTENT));
        editText3.setText("120 -100");
        layout.addView(editText3);

        Button button = new Button(this);
        button.setText("test");
        button.setPadding(20, 10, 20, 10);
        button.setLayoutParams(new LinearLayout.LayoutParams(FlowLayout.LayoutParams.MATCH_PARENT, FlowLayout.LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] string1 = editText1.getText().toString().split(" ");
                String[] string2 = editText2.getText().toString().split(" ");
                String[] string3 = editText3.getText().toString().split(" ");

                final float[][] points = {{Float.valueOf(string1[0]), Float.valueOf(string1[1])},
                        {Float.valueOf(string2[0]), Float.valueOf(string2[1])},
                        {Float.valueOf(string3[0]), Float.valueOf(string3[1])}};
                ParabolaAlgorithm.ParabolaParam value = ParabolaAlgorithm.calculate(points);
                a = value.getA();
                b = value.getB();
                c = value.getC();
                count = (int) (points[2][0] - points[0][0]);

                //startAnimation(imageView);
                //摇一摇动画
                shake(imageView);
            }
        });

        layout.addView(button);
        layout.addView(imageView);
        setContentView(layout);

    }

    int count = 20;

    /**
     * 要start 动画的那张图片的ImageView
     *
     * @param imageView
     */
    private void startAnimation(final ImageView imageView) {

        Keyframe[] keyframes = new Keyframe[count];
        final float keyStep = 1f / (float) count;
        float key = keyStep;
        for (int i = 0; i < count; ++i) {
            keyframes[i] = Keyframe.ofFloat(key, i + 1);
            key += keyStep;
        }

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("translationX", keyframes);
        key = keyStep;
        for (int i = 0; i < count; ++i) {
            keyframes[i] = Keyframe.ofFloat(key, -getY(i + 1));
            key += keyStep;
        }

        PropertyValuesHolder pvhY = PropertyValuesHolder.ofKeyframe("translationY", keyframes);
        ObjectAnimator yxBouncer = ObjectAnimator.ofPropertyValuesHolder(imageView, pvhY, pvhX).setDuration(2000);
//        yxBouncer.setInterpolator(new BounceInterpolator());
        yxBouncer.start();
    }

    private void shake(ImageView imageView) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_appstore);
        imageView.startAnimation(shake);
    }

    float a = 0f, b = 0f, c = 0f;

    /**
     * 这里是根据三个坐标点{（0,0），（300,0），（150,300）}计算出来的抛物线方程
     *
     * @param x
     * @return
     */
    private float getY(float x) {
        return a * x * x + b * x + c;
    }

}
