package test1.nh.com.demos1.activities.horizontalScroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import test1.nh.com.demos1.R;

public class HorizontalScrollActivity extends AppCompatActivity {



    public static void start(Context c){
        Intent i=new Intent(c,HorizontalScrollActivity.class);
        c.startActivity(i);
    }


    RelativeLayout layout;
    Button scrollToBtn,scrollByBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll);

        layout = (RelativeLayout) findViewById(R.id.container_scroll);
        scrollToBtn = (Button) findViewById(R.id.btn_scroll_to);
        scrollByBtn = (Button) findViewById(R.id.btn_scroll_by);
        scrollToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollTo(-60, -100);
            }
        });
        scrollByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollBy(-60, -100);
            }
        });


    }


}
