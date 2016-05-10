package test1.nh.com.demos1.activities;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import test1.nh.com.demos1.R;
import test1.nh.com.demos1.customView.ExpandView;
import test1.nh.com.demos1.customView.PieChartc;

public class CustomViewActivity extends AppCompatActivity {


    private ExpandView shape;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity_from, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_tab);

        Resources res = getResources();

        final PieChartc pie = (PieChartc) this.findViewById(R.id.costum3);
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


        shape = (ExpandView) findViewById(R.id.ev1);
        shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shape.state == ExpandView.EXPAND_STATE) {
                    contractView();
                    shape.state = ExpandView.CONTRACT_STATE;
                }
                else {
                    expandView();
                    shape.state = ExpandView.EXPAND_STATE;
                }
////--------------------why below doesn't work/???
//                if (shape.state == ExpandView.EXPAND_STATE) {
//                    contractView();
//                    shape.state = ExpandView.CONTRACT_STATE;
//                }
//                if (shape.state == ExpandView.CONTRACT_STATE) {
//                    expandView();
//                    shape.state = ExpandView.EXPAND_STATE;
//                }
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void contractView() {
//        Log.i("AAA",""+shape.getMeasuredWidth());
        ValueAnimator anim = ValueAnimator.ofInt(shape.getMeasuredWidth(), 108);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = shape.getLayoutParams();
                layoutParams.width = val;
                shape.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(1000);
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void expandView() {
        ValueAnimator anim = ValueAnimator.ofInt(shape.getMeasuredWidth(), 324);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = shape.getLayoutParams();
                layoutParams.width = val;
                shape.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(1000);
        anim.start();
    }


}
