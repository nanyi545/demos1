package test1.nh.com.demos1.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import test1.nh.com.demos1.R;
import test1.nh.com.demos1.customView.LoadView;

public class CustomViewActivity2 extends AppCompatActivity {


    public static void start(Context c){
        Intent i=new Intent(c,CustomViewActivity2.class);
        c.startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view2);
    }

    public void startLoad(View v){
        LoadView.startLoading(this);
    }
}
