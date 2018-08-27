package com.longrise.pczhaojue.eventbusdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

/**
 * @author PCzhaojue
 * @name EventBusDemo
 * @class name：com.longrise.pczhaojue.eventbusdemo
 * @class describe
 * @time 2018/8/7 下午2:33
 * @change
 * @chang time
 * @class describe
 */
public class SearchButtonView extends View
{

    private Context mContext;
    private Paint mPaint;
    private Canvas mCanvas;
    private int mStrokeWidth;
    private int mPaintColor;
    private Path mPath;

    public SearchButtonView(Context context)
    {
        this(context, null);
    }

    public SearchButtonView(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SearchButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray attr = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.SearchButtonView, defStyleAttr, 0);
        if (attr != null && attr.length() > 0)
        {
            initAttr(attr);
        }

        initPaint();
        initView();
        initEvent();
    }

    private void initAttr(TypedArray ta)
    {
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.SearchButtonView_strokeWidth, 0);
        mPaintColor = ta.getColor(R.styleable.SearchButtonView_paintColor, 0);
        ta.recycle();
    }

    private void initEvent()
    {

    }

    private void initView()
    {
    }

    private void initPaint()
    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mCanvas = new Canvas();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("11",""+w);
        Log.d("11",""+h);
    }

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
        }
        else
        {
              int defWidth = 150;
//            p.setTextSize(textSize);
//            p.getTextBounds(text, 0, text.length(), mBound);
//            float textWidth = mBound.width();
//            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
              width = (int) (getPaddingLeft() + defWidth + getPaddingRight());
//            width = defWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        }
        else
        {
              int defHeight = 150;
//            p.setTextSize(textSize);
//            p.getTextBounds(text, 0, text.length(), mBound);
//            float textHeight = mBound.height();
//            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
              height = (int) (getPaddingTop() +defHeight+ getPaddingBottom());
//            height =defHeight;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Log.v("22","#1#"+getHeight());
        Log.v("22","#2#"+getWidth());
        Log.v("22","#@1#"+getMeasuredHeight());
        Log.v("22","#@2#"+getMeasuredWidth());

        mPaint.setColor(mPaintColor);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawColor(Color.WHITE);
        canvas.translate(canvas.getHeight()/2,canvas.getHeight()/2);
        canvas.translate(canvas.getHeight()/4,canvas.getHeight()/4);
        int height = canvas.getHeight();
        canvas.drawCircle(-height/4, -height/4, height/4, mPaint);
        Log.v("22","#@@1#"+canvas.getHeight());
        Log.v("22","#@@2#"+canvas.getWidth());
        canvas.save();

        mPath = new Path();
        mPath.moveTo(height/8*1.414f-height/4, height/8*1.414f-height/4);
        mPath.lineTo(height/8, height/8);
        canvas.drawPath(mPath, mPaint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (listener != null)
        {
            listener.onSearch();
        }
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mPaintColor = Color.RED;
                Log.d("11","down x" + event.getX());
                Log.d("11","down Rowx" + event.getRawX());
                this.invalidate();
                break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_OUTSIDE:
                Log.d("11","outside");
                case MotionEvent.ACTION_UP:
                    mPaintColor = Color.BLUE;
                    this.invalidate();
                    break;
        }
        return super.onTouchEvent(event);
    }

    interface OnSearchListener{
        void onSearch();
    }

    private OnSearchListener listener;

    public void setOnSearchListener(OnSearchListener listener){
        this.listener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        return super.dispatchTouchEvent(event);
    }
}
