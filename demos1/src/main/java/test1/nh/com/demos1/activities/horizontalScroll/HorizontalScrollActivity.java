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





        runner=new ScrollerRun();
        scrollToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollTo(-60, -100);
            }
        });
        scrollByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollBy(-60, -100);// scroll with out animation
            }
        });
        scrollByBtn_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new ScrollerRun().startScroll();// scroll with animation
                runner.startScroll();
            }
        });


    }


    ScrollerRun runner;


    private class ScrollerRun implements Runnable{

        private int lastX,lastY = 0;

        Scroller myscroller;
        public ScrollerRun() {
            this.myscroller=new Scroller(HorizontalScrollActivity.this);

        }



        void startScroll(){
            myscroller.startScroll(layout.getScrollX(), layout.getScrollY(), -60, -100,5000);
            int initialX = layout.getScrollX();
            lastX = initialX;
            lastY =layout.getScrollY();
            layout.post(this);
        }

        @Override
        public void run() {
            if (myscroller.isFinished()) {
                Log.i("ccc", "scroller is finished, done with scrolling");
                return;
            }

//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            boolean more = myscroller.computeScrollOffset();
            int x = myscroller.getCurrX();
            int diff = x-lastX;

            int y=myscroller.getCurrY();
            int diffY = y-lastY;

            if (diff != 0) {
                layout.scrollBy(diff, 0);
                lastX = x;
            }

            if (diffY != 0) {
                layout.scrollBy(0, diffY);
                lastY = y;
            }


            if (more) {
                layout.post(this);
            }

        }
    }

}
