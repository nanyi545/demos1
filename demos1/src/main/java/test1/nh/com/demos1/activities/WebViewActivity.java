package test1.nh.com.demos1.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import test1.nh.com.demos1.R;

public class WebViewActivity extends AppCompatActivity {

    public static void start(Context c){
        Intent i=new Intent(c,WebViewActivity.class);
        c.startActivity(i);
    }


    WebView webView;
    EditText editText1;
    Button button1,button2;


    public void loadLocal(View v){
//        webView.loadUrl("file:///android_asset/www/yellow.html");
//        webView.loadUrl("file:///android_asset/www/frameset.html");
//        webView.loadUrl("file:///android_asset/www/tables.html");
        webView.loadUrl("file:///android_asset/www/fee.html");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView= (WebView) findViewById(R.id.my_web_view);
        webView.setWebViewClient(new WebViewClient());

        // enable JS
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        editText1= (EditText) findViewById(R.id.path);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=editText1.getText().toString();
                webView.loadUrl(url);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=editText1.getText().toString();
                webView.loadData(url,"text/html","utf-8");
            }
        });

    }
}
