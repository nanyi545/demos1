package test1.nh.com.demos1.customView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import test1.nh.com.demos1.R;

/**
 * Created by Administrator on 16-7-30.
 */
public class LoadView extends View {


    private int viewWidth;
    private int viewHeight;

    public LoadView(Context context) {
        super(context);
    }

    public LoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadInitValues(context, attrs);
    }

    private void loadInitValues(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.LoadView
        );
        try {
            ratio=a.getFloat(R.styleable.LoadView_loadPercent, 0);
        } finally {
            a.recycle();
        }
    }

    public LoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadInitValues(context, attrs);
    }

    boolean bitmapsReady=false;
    Bitmap star1,star2;
    float height,width;


    private void loadImages(){
        star1=getScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.star1),viewWidth);
        star2=getScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.star2),viewWidth);
        height=star1.getHeight();
        width=star2.getWidth();
        bitmapsReady=true;
    }

    public void setProgress(float ratio){
        this.ratio = ratio;
        invalidate();
    }


    public void animateTo(float ratio){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, ratio);
        valueAnimator.setStartDelay(100);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator(2f));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoadView.this.setProgress((Float)valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }






    float ratio=0f;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!bitmapsReady)loadImages();
        canvas.save();   //*************
        //**********-----restrict the drawing area----
        canvas.clipRect(0,0,width*ratio,height);
        canvas.drawBitmap(star1,0,0,null);
        canvas.restore();//*************

        canvas.save();   //*************
        //**********-----restrict the drawing area----
        canvas.clipRect(width*ratio,0,width,height);
        canvas.drawBitmap(star2,0,0,null);
        canvas.restore();//*************
    }

    /**
     * Returns a bitmap scaled to a specific width.
     */
    private Bitmap getScaledBitmap(Bitmap bitmap, float width) {
        float scale = width / bitmap.getWidth();
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * scale),
                (int) (bitmap.getHeight() * scale), false);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth=w;
        viewHeight=h;
    }



}
