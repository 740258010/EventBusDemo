package com.longrise.pczhaojue.eventbusdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * @author PCzhaojue
 * @name EventBusDemo
 * @class name：com.longrise.pczhaojue.eventbusdemo
 * @class describe
 * @time 2018/8/7 上午11:27
 * @change
 * @chang time
 * @class describe
 */

public class MyButton extends android.support.v7.widget.AppCompatButton
{
    public MyButton(Context context)
    {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }
}
