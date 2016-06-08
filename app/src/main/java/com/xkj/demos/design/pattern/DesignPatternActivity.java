package com.xkj.demos.design.pattern;

import android.app.Activity;
import android.os.Bundle;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 14/12/13.
 */
public class DesignPatternActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_design_pattern);
        Person xkj = new Person("cfq");
        TShirts tShirts = new TShirts();
        BigTrouser bigTrouser = new BigTrouser();
        tShirts.decorate(xkj);
        bigTrouser.decorate(tShirts);
        bigTrouser.show();
    }
}