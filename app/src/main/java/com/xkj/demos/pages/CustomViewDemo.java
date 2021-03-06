package com.xkj.demos.pages;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.xkj.ui.PieChart;
import com.xkj.xkjdemos.R;

/**
 * Created by fuqiang on 14/11/26.
 */
public class CustomViewDemo extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        Resources res = getResources();
        final PieChart pie = (PieChart) this.findViewById(R.id.Pie);
        pie.addItem("Agamemnon", 2, res.getColor(R.color.seafoam));
        pie.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
        pie.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
        pie.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
        pie.addItem("Euripides", 1, res.getColor(R.color.turquoise));
        pie.addItem("Ganymede", 3, res.getColor(R.color.slate));

        ((Button) findViewById(R.id.Reset)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pie.setCurrentItem(0);
            }
        });
    }
}