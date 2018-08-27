package com.longrise.pczhaojue.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity
{

    @BindView(R.id.guide_home)
    RadioButton guideHome;
    @BindView(R.id.guide_store)
    RadioButton guideStore;
    @BindView(R.id.guide_cart)
    RadioButton guideCart;
    @BindView(R.id.guide_ww)
    RadioButton guideWw;
    @BindView(R.id.main_tab_group)
    RadioGroup mainTabGroup;
    @BindView(R.id.content1)
    LinearLayout content1;
    @BindView(R.id.content2)
    LinearLayout content2;
    @BindView(R.id.content3)
    LinearLayout content3;
    @BindView(R.id.content4)
    LinearLayout content4;
    @BindView(R.id.second_msv)
    MyScrollView secondMsv;
    @BindView(R.id.second_fl)
    FrameLayout secondFl;
    @BindView(R.id.second_tab)
    TabHost secondTab;
    private int top;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            top = secondTab.getTop();
        }
    }

    private void init()
    {
        secondMsv.setOnScrollListener(new MyScrollView.OnScrollListener()
        {
            @Override
            public void onScroll(int Y)
            {
                Log.v("1111", "y" + String.valueOf(Y));
                Log.v("1111", "top" + String.valueOf(top));
                Log.v("1111", "==============");
                Log.v("1111", String.valueOf(Y - top));
                //划过了屏
                if(Y-top <= 0)
                {

                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        Log.v("second", "111111");
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Log.v("second", String.valueOf(content1.getX()));
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v("second", String.valueOf(content1.getX()) + "@@");
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }

}
