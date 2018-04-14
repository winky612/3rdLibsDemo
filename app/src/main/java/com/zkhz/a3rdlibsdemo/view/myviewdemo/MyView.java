package com.zkhz.a3rdlibsdemo.view.myviewdemo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;

import com.zkhz.a3rdlibsdemo.R;

import java.util.TimeZone;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MyView extends View{

    private Time mCalender;//记录当前时间
    private Drawable hour;
    private Drawable minute;
    private Drawable dial;

    //用来记录表盘图片的宽和高，以便帮助我们在onMeasure中确定View的大小
    private int mDialHeight,mDialWidth;

    //用来记录View是否被加入到了Window中，我们在View attached到Window时注册监听器，监听时间的变更，并根据时间的变更，改变自己的绘制
    //在View从Window中剥离时，解除注册，因为我们不需要再监听时间变更了，没人能看得到我们的View了。
    private boolean mAttached;

    private float mMinutes;
    private float mHour;


    //用来跟踪我们的View 的尺寸的变化，当发生尺寸变化时，我们在绘制自己时要进行适当的缩放。
    private boolean mChanged;





    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    @SuppressLint("NewApi")
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        final Resources resources=context.getResources();
        if (dial==null){
            dial=context.getDrawable(R.drawable.clock);
        }
        if (hour==null){
            hour=context.getDrawable(R.drawable.hour);
        }
        if (minute==null){
            minute=context.getDrawable(R.drawable.minute);
        }

        long time = 0;
        mCalender=new Time();
        mDialHeight=dial.getIntrinsicHeight();
        mDialWidth=dial.getIntrinsicWidth();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        //水平 垂直放缩比例
        float hScale = 1.0f;
        float vScale = 1.0f;

        if (widthMode != MeasureSpec.UNSPECIFIED && widthSize < mDialWidth) {
            hScale = (float) widthSize / (float) mDialWidth;
        }
        if (heightMode != MeasureSpec.UNSPECIFIED && heightSize < mDialHeight) {
            vScale = (float )heightSize / (float) mDialHeight;
        }

        float scale = Math.min(hScale, vScale);

        //这里我们没有直接使用ViewGroup给我们的较小的尺寸，而是对我们的表盘图片的宽高进行相同比例的缩放后，设置的尺寸，这样的好处是，可以防止表盘图片绘制时的拉伸或者挤压变形
        //resolveSizeAndState(期望的大小,,)
        setMeasuredDimension(resolveSizeAndState((int) (mDialWidth*scale),widthMeasureSpec,0)
                ,resolveSizeAndState((int) (mDialHeight*scale),heightMeasureSpec,0));

    }


    //让我们的自定义View能够感知自己尺寸的变化,这样每次绘制时，可以先判断下尺寸是否发生了变化，如果有变化，就及时调整我们的绘制策略
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mChanged=true;//该变量会在onDraw中使用

    }

    //让我们的View能够监听时间变化，并及时更新该View中的mCalendar变量，然后根据它来更新自身的绘制
    private void onTimeChanged(){

        mCalender.setToNow();

        int hour=mCalender.hour;
        int minute = mCalender.minute;
        int second = mCalender.second;
        /*这里我们为什么不直接把minute设置给mMinutes，而是要加上
            second /60.0f呢，这个值不是应该一直为0吗？
            这里又涉及到Calendar的 一个知识点，
            也就是它可以是Linient模式，
            此模式下，second和minute是可能超过60和24的，具体这里就不展开了，
            如果不是很清楚，建议看看Google的官方文档中讲Calendar的部分*/
        mMinutes = minute + second / 60.0f;
        mHour = hour + mMinutes / 60.0f;
        mChanged = true;



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }


    //实现一个广播接收器，接收系统发出的时间变化广播，然后更新该View的mCalendar
    private final BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //这个if判断主要是用来在时区发生变化时，更新mCalendar的时区的，这
            //样，我们的自定义View在全球都可以使用了。
            if (intent.getAction().equals(Intent.ACTION_TIMEZONE_CHANGED)) {
                String tz = intent.getStringExtra("time-zone");
                mCalender = new Time(TimeZone.getTimeZone(tz).getID());
            }
            //进行时间的更新
            onTimeChanged();
            //invalidate当然是用来引发重绘了。
            invalidate();



        }
    };


    //给我们的View动态地注册广播接收器
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!mAttached){
            mAttached=true;
            IntentFilter filter=new IntentFilter();
            //这里确定我们要监听的三种系统广播
            filter.addAction(Intent.ACTION_TIME_TICK);
            filter.addAction(Intent.ACTION_TIME_CHANGED);
            filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);

            getContext().registerReceiver(broadcastReceiver,filter);

        }

        mCalender=new Time();
        onTimeChanged();



    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAttached){
            getContext().unregisterReceiver(broadcastReceiver);
            mAttached=false;
        }

    }
}
