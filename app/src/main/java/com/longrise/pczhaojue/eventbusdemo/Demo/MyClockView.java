package com.longrise.pczhaojue.eventbusdemo.Demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;


/**
 * @author PCzhaojue
 * @name EventBusDemo
 * @class name：com.longrise.pczhaojue.eventbusdemo.Demo
 * @class describe
 * @time 2018/8/9 下午1:53
 * @change
 * @chang time
 * @class describe
 */
public class MyClockView extends View
{

    private static final int START_CLOCK = 1000;
    private Paint mPaint;

    public MyClockView(Context context)
    {
        this(context, null);
    }

    public MyClockView(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public MyClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

    }

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if (msg.what == START_CLOCK){
                invalidate();
                handler.sendEmptyMessageDelayed(START_CLOCK,1000);
            }
        }
    };


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            int defWidth = 150;
            width = (int) (getPaddingLeft() + defWidth + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            int defHeight = 150;
            height = (int) (getPaddingTop() + defHeight + getPaddingBottom());

        }
        setMeasuredDimension(width, height);
    }

    public void initPaint()
    {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Calendar mCalendar= Calendar.getInstance();
        //获取当前小时数
        int hours = mCalendar.get(Calendar.HOUR);
        //获取当前分钟数
        int minutes = mCalendar.get(Calendar.MINUTE);
        //获取当前秒数
        int seconds=mCalendar.get(Calendar.SECOND);

        //画圈
        drawCircle(canvas);

        //2.画点
        mPaint.setStrokeWidth(15);
        canvas.drawPoint(0, 0, mPaint);

        //3.画文字
        initPaint();
        mPaint.setColor(Color.BLUE);

        //4.画刻度
        drawMark(canvas);

        //5.画指针
        drawClock(canvas, hours, minutes, seconds);
    }

    private void drawCircle(Canvas canvas)
    {
        initPaint();
        mPaint.setStyle(Paint.Style.FILL);
        //1.画圆
//        canvas.drawColor(Color.BLUE);
//        canvas.drawCircle(canvas.getHeight()/2,canvas.getHeight()/2,canvas.getHeight()/2,mPaint);

        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2); //将位置移动画纸的坐标点:150,150
        canvas.drawColor(Color.BLUE);
        canvas.drawCircle(0, 0, canvas.getHeight() / 2, mPaint);
    }

    private void drawMark(Canvas canvas)
    {
        canvas.save();
        //3.刻度小时
        initPaint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(9);

        //数字刻度
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(25);
        paint.setColor(Color.RED);

        float y = canvas.getHeight() / 2;
        int count = 60; //总刻度数

        for (int i = 0; i < count; i++)
        {
            if (i % 5 == 0)
            {
                canvas.drawLine(0f, y, 0, y - 30f, mPaint);
                canvas.drawText(String.valueOf(12 - i / 5), -2f, -y + 50f, paint);
            } else
            {
                canvas.drawLine(0f, y, 0f, y - 10f, mPaint);
            }
            canvas.rotate(-360 / count, 0f, 0f); //旋转画纸
        }
    }

    private void drawClock(Canvas canvas, int hours, int minutes, int seconds)
    {
        //秒
        initPaint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);
        int degree = 360/60;
        canvas.save();
        canvas.rotate(degree*seconds,0,0);
        canvas.drawLine(0, 50f, 0, -canvas.getHeight() / 3, mPaint);
        canvas.restore();

        //分
        initPaint();
        float minDegree = (minutes*60+seconds) / 60f/ 60f * 360;
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(9);
        canvas.save();
        canvas.rotate(minDegree,0,0);
        canvas.drawLine(0,30f,0,-canvas.getHeight()/3,mPaint);
        canvas.restore();

        //时
        initPaint();
        float hourDegree = (hours * 60 + minutes) / 60f / 12f  * 360;
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStrokeWidth(13);
        canvas.save();
        canvas.rotate(hourDegree,0,0);
        canvas.drawLine(0,20f,0,-canvas.getHeight()/6,mPaint);
        canvas.restore();
        handler.sendEmptyMessageDelayed(START_CLOCK,1000);
    }
}
