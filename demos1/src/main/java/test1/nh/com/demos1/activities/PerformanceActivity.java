package test1.nh.com.demos1.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import test1.nh.com.demos1.R;

public class PerformanceActivity extends AppCompatActivity {

    public static void start(Context context){
        final Intent intent=new Intent(context,PerformanceActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        //  temp
//        this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public void dowork(View v){
        new Thread(new Runnable(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("AAA","thread waiting---"+Thread.currentThread().getName());
                }
            }
        }).start();
    }


    public void cal_fib(View v){
        new Thread(testRunnable).start();
    }

    private Runnable testRunnable=new Runnable(){
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void run() {
//            Trace.beginSection("Data Structures");
            Log.i("AAA",""+calculate_fib(200));
//            Trace.endSection();
        }
    };

    private int calculate_fib(int a1){
        Log.i("AAA","--"+Thread.currentThread().getName()+"--"+a1);
        if (a1<2) return 1;
        else return calculate_fib(a1-1)+calculate_fib(a1-2);
    }
}
