package test1.nh.com.demos1.customView;

/**
 * Created by zhengdonghui1 on 15/7/15.
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import test1.nh.com.demos1.R;


public class CircleViewPlus extends View {

    private int startAngle=150;  // default start angle
    private int getTotalArcLength(){  // get total arc length so that the arc is symetric with respect to 90deg
        return 360-(startAngle-90)*2;
    }



    Paint paint,textpaint,progresspaint,roundpaint,s_roundpaint;
    RectF area;
    String str = "";
    int value = 0,txt_X = 100,txt_Y = 100;
    float c_x = 0,c_y = 0,radius = 0;
    float s_c_x = 0,s_c_y = 0,s_radius = 13f;
    //LinearGradient shader,progressshader;


    int arcBarColor,progressBarColor,progressTextColor;  // 圆弧背景色，圆弧进度色，进度字体色


    public CircleViewPlus(Context context, AttributeSet attrs, int defStyle,int startAngle) {
        this(context, attrs, defStyle);
        this.startAngle=startAngle;
    }


    public CircleViewPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.CircleViewPlus
        );

        try {
            arcBarColor = a.getColor(R.styleable.CircleViewPlus_arcBarColor, 0xff888888);
            progressBarColor= a.getColor(R.styleable.CircleViewPlus_progressBarColor, 0xff000000);
            progressTextColor= a.getColor(R.styleable.CircleViewPlus_progressTextColor, 0xffffffff);
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }


        init();
        // TODO Auto-generated constructor stub
    }

    public CircleViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.CircleViewPlus
        );

        try {
            arcBarColor = a.getColor(R.styleable.CircleViewPlus_arcBarColor, 0xff888888);
            progressBarColor= a.getColor(R.styleable.CircleViewPlus_progressBarColor, 0xff000000);
            progressTextColor= a.getColor(R.styleable.CircleViewPlus_progressTextColor, 0xffffffff);
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }

        init();
        // TODO Auto-generated constructor stub
    }

    public CircleViewPlus(Context context) {
        super(context);
        init();
        // TODO Auto-generated constructor stub
    }

    public void setProgress(int value){
        this.value = value;
        invalidate();
    }

    public void resetCircleView(int value,View view){
//        Log.i("hcyd","resetCiecleView.value="+value);
        this.value = value;

//        Log.i("hcyd","this.value="+this.value);
        float radius = WidgetController.getWidth(view)/2*0.87f;   // the ratio is set to 0.87f, so that the arc does not get clipped by the container (relative layout)
//        float width = 5f;
        float width = radius/10;


        double textsize = WidgetController.getWidth(view)/2*0.3;
        int size = (int)textsize;
        this.c_x = WidgetController.getWidth(view)/2;
        this.c_y = WidgetController.getHeight(view)/2;
        float R = WidgetController.getWidth(view)/2;
        float r = radius;
        double x = R+r*Math.cos((value*getTotalArcLength()/100+startAngle)*Math.PI/180);
        double y = R+r*Math.sin((value*getTotalArcLength()/100+startAngle)*Math.PI/180);

        this.s_c_x = (float)x;
        this.s_c_y = (float)y;
        this.radius = WidgetController.getWidth(view)/2;
//        Log.i("hcyd","radios="+radius);
//        Log.i("hcyd","width="+WidgetController.getWidth(view));
//        Log.i("hcyd","height="+WidgetController.getHeight(view));
//        Log.i("hcyd","StrokeWidth="+paint.getStrokeWidth());
        this.paint.setStrokeWidth(width);
        this.progresspaint.setStrokeWidth(width);

        s_radius=width*1.4f;
        textpaint.setStrokeWidth(s_radius/10);


        this.textpaint.setTextSize(s_radius);

        this.area = new RectF(WidgetController.getWidth(view)/2-radius,
                WidgetController.getWidth(view)/2-radius,
                WidgetController.getWidth(view)/2+radius,
                WidgetController.getWidth(view)/2+radius);
//        Log.i("hcyd", "area=" + area.toString());
        this.txt_X = WidgetController.getWidth(view)/2 - size-20;
        this.txt_Y = WidgetController.getHeight(view)/2 + size/3;

//        invalidate();
    }

    public void init() {
        paint = new Paint();
        paint.setStrokeWidth(5f);
//        paint.setColor(getResources().getColor(R.color.Red200));
        paint.setColor(arcBarColor);

        paint.setStyle(Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setAntiAlias(true);
        paint.setDither(true);
        progresspaint = new Paint();
        progresspaint.setStrokeWidth(5f);
//        progresspaint.setColor(getResources().getColor(R.color.Red600));
        progresspaint.setColor(progressBarColor);

        progresspaint.setStyle(Style.STROKE);
        progresspaint.setStrokeCap(Paint.Cap.ROUND);
        progresspaint.setStrokeJoin(Paint.Join.ROUND);
        progresspaint.setAntiAlias(true);
        progresspaint.setDither(true);
        roundpaint = new Paint();
        roundpaint.setColor(getResources().getColor(R.color.Blue500));
        s_roundpaint = new Paint();
//        s_roundpaint.setColor(getResources().getColor(R.color.Red600));   // progress indicator circle
        s_roundpaint.setColor(progressBarColor);

        textpaint = new Paint();
        textpaint.setTextSize(35f);
//        textpaint.setColor(getResources().getColor(R.color.White));// progress indicator text
        textpaint.setColor(progressTextColor);


        area=new RectF(0,0,233,233);

//        shader =new LinearGradient(0, 0, 400, 0, getResources().getColor(R.color.app_theme_orange),

//                getResources().getColor(R.color.app_theme_orange),
//                Shader.TileMode.CLAMP);
//        progressshader =
//        paint.setShader(shader);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        //canvas.drawColor(Color.WHITE);
        Log.i("hcyd", "circleview.value=" + value);
//        canvas.drawCircle(c_x, c_y, radius * 0.72f, roundpaint);  // empty circle in the center ...  not needed here
        canvas.drawArc(area, startAngle, getTotalArcLength(), false, paint);
        //canvas.drawCircle();
        if (value == 0){
            //canvas.drawArc(area, 120, 0 , false, progresspaint);
        }else {
            canvas.drawArc(area, startAngle, value*getTotalArcLength()/100 , false, progresspaint);
            canvas.drawCircle(s_c_x,s_c_y,s_radius,s_roundpaint);  // progress indicator
            if (value>9) canvas.drawText(""+value+"%", s_c_x-s_radius*(0.85f), s_c_y+s_radius*(0.4f), textpaint); // number on the indicator  -->  the ratio are set to place the number in the middle of the circle
            else canvas.drawText(""+value+"%", s_c_x-s_radius*(0.6f), s_c_y+s_radius*(0.4f), textpaint);         // number on the indicator  -->  the ratio are set to place the number in the middle of the circle
            // the ratio depends on the length of the text,
        }
//        canvas.drawText(str, txt_X, txt_Y, textpaint);
    }

}
