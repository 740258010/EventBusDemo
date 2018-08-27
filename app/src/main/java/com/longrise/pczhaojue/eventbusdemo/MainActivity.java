package com.longrise.pczhaojue.eventbusdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.longrise.pczhaojue.eventbusdemo.Demo.DemoActivity;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author PCzhaojue
 * @time 2018/8/2  上午
 * @params
 */
public class MainActivity extends AppCompatActivity
{


    @BindView(R.id.tv_testBus)
    public TextView tvTestBus;
    @BindView(R.id.btn)
    Button btn;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
        initView();

//        test();
    }

    private void initView()
    {
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,ToolbarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init()
    {
        ServiceConnection connection = new ServiceConnection()
        {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder)
            {
                MyService.MyBinder binder = (MyService.MyBinder) iBinder;
//                MyService myService = binder.getMyService();
//                ManInfo info = myService.sendMydata();
//                EventBus.getDefault().post(info, "data");
                ManInfo manInfo = new ManInfo("张三", "男");
//        intent.putExtra("info", manInfo);
                EventBus.getDefault().post(manInfo, "sendData");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName)
            {

            }
        };

        //方式1：activity和service通信：intent传值
        Intent intent = new Intent(this, MyService.class);
//        ManInfo manInfo = new ManInfo("张三", "男");
////        intent.putExtra("info", manInfo);
//        EventBus.getDefault().post(manInfo,"sendData");
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Subscriber(tag = "evt")
    private void sendData(EventMessage data)
    {
        if (data != null)
        {
            Log.v("Main", data.getMessage());
//            mTv.setText(data.getMessage().toString());
            if (tvTestBus != null)
            {
                tvTestBus.setText(data.getMessage());
            }

        }
    }

    @Subscriber(tag = "data")
    private void getData(ManInfo info)
    {
        if (info != null)
        {
            Log.v("Main", info.toString());
            mTv.setText(info.getSex());
        }
    }

    @Subscriber(tag = "infoData")
    private void getDatafromService(ManInfo info)
    {
        if (info != null)
        {
            Log.v("Main", info.toString());
            tvTestBus.setText(info.getSex());
        }
    }

    private void test()
    {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                SystemClock.sleep(1000);
                EventBus.getDefault().post(new EventMessage("1111111111"), "evt");
            }
        }).start();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
