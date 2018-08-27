package com.longrise.pczhaojue.eventbusdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author PCzhaojue
 * @name EventBusDemo
 * @class name：com.longrise.pczhaojue.eventbusdemo
 * @class describe
 * @time 2018/8/2 下午5:29
 * @change
 * @chang time
 * @class describe
 */

public class MyScrollView extends ScrollView
{

    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;

    public MyScrollView(Context context)
    {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {

        public void handleMessage(android.os.Message msg)
        {
            int scrollY = MyScrollView.this.getScrollY();

            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY)
            {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }
            if (listener != null)
            {
                listener.onScroll(scrollY);
            }

        }

    };

    //当前窗体得到或失去焦点的时候的时候调用
    //在Activity的生命周期中，onCreate()--onStart()--onResume()都不是窗体Visible的时间点，真正的窗体完成初始化可见获取焦点可交互是在onWindowFocusChanged()方法被执行时，
    // 而这之前，对用户的操作需要做一点限制。
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus)
    {
        super.onWindowFocusChanged(hasWindowFocus);

    }

    //并不是只有屏幕方向改变才可以触发，其他的一些系统设置改变也可以触发，比如打开或者隐藏键盘
    //我们要想当前的activity捕获这个事件：
    //1.第一：权限声明：<uses-permission android:name="android.permission.CHANGE_CONFIGURATION"></uses-permission>
    //2.第二：声明activity要捕获的事件类型Android:configChanges="orientation|keyboard"
    @Override
    protected void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    //当View中所有的子控件均被映射成xml后触发
    //也就是会在Activity中调用setContentView之后就会调用onFinishInflate这个方法，
    //这个方法就代表自定义控件中的子控件映射完成了，然后可以进行一些初始化控件的操作
    //比如： 可以通过findViewById 得到控件，得到控件之后进行一些初始化的操作
    //就像在Activity中的onCreate方法里面一样，当然在这个方法里面是得不到控件的高宽的，控件的高宽是必须在调用了onMeasure方法之后才能得到，
    //而onFinishInflate方法是在setContentView之后、onMeasure之前
    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
    }

    //只能在View中重写
    //该方法是焦点改变的回调方法，当某个控件重写了该方法后，当焦点发生变化时
    //，会自动调用该方法来处理焦点改变的事件
    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect)
    {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    //l, t代表left, top，也就是触摸点相对左上角的偏移量。而oldl, oldt就是滑动前的偏移量。
//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt)
//    {
//        super.onScrollChanged(l, t, oldl, oldt);
//        Log.v("second",String.valueOf(t));
//        Log.v("second",String.valueOf(oldt) +"@@");
//        if (listener != null){
//            listener.onScroll(t);
//        }
//    }

    private OnScrollListener listener;

    interface OnScrollListener
    {
        void onScroll(int Y);
    }

    public void setOnScrollListener(OnScrollListener listener)
    {
        this.listener = listener;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if (listener != null)
        {
            listener.onScroll(lastScrollY = this.getScrollY());
        }
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 20);
                break;
        }
        return super.onTouchEvent(ev);
    }
}
