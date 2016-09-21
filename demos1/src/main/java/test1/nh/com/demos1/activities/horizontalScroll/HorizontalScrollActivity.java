package test1.nh.com.demos1.activities.horizontalScroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import test1.nh.com.demos1.R;

public class HorizontalScrollActivity extends AppCompatActivity {



    public static void start(Context c){
        Intent i=new Intent(c,HorizontalScrollActivity.class);
        c.startActivity(i);
    }


    RelativeLayout layout;
    Button scrollToBtn,scrollByBtn,scrollByBtn_anim;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll);



        layout = (RelativeLayout) findViewById(R.id.container_scroll);
        scrollToBtn = (Button) findViewById(R.id.btn_scroll_to);
        scrollByBtn = (Button) findViewById(R.id.btn_scroll_by);
        scrollByBtn_anim = (Button) findViewById(R.id.btn_scroll_by_anim);





        scrollToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollTo(-60, -100);
            }
        });
        scrollByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollBy(-60, 0);// scroll with out animation
            }
        });
        scrollByBtn_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ScrollerRun().startScroll();// scroll with animation
            }
        });


    }


    private class ScrollerRun implements Runnable{

        private int lastX = 0;

        Scroller myscroller;
        public ScrollerRun() {
            this.myscroller=new Scroller(HorizontalScrollActivity.this);
            myscroller.startScroll(layout.getScrollX(), layout.getScrollY(), -60, -100,1500);
        }



        void startScroll(){

            int initialX = layout.getScrollX();

            lastX = initialX;
            layout.post(this);

        }

        @Override
        public void run() {
            if (myscroller.isFinished()) {
                Log.i("ccc", "scroller is finished, done with fling");
                return;
            }

            boolean more = myscroller.computeScrollOffset();
            int x = myscroller.getCurrX();
            int diff = lastX - x;
            if (diff != 0) {
                layout.scrollBy(diff, 0);
                lastX = x;
            }

            if (more) {
                layout.post(this);
            }

        }
    }

}
