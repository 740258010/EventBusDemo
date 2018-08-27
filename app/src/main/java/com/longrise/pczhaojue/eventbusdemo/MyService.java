package com.longrise.pczhaojue.eventbusdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class MyService extends Service
{

    private ManInfo info;

    public MyService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        MyBinder myBinder = new MyBinder();
        EventBus.getDefault().register(this);
//        info = intent.getParcelableExtra("info");
//        Log.v("Main",info.toString());
//        info.setSex("女");
        return myBinder;
    }

    @Subscriber(tag = "sendData")
    private void getInfoByMain(ManInfo info){
        if (info != null)
        {
            info.setSex("女");
            info.setName("孙悟空");
            this.info = info;
            Log.v("main",info.toString());

            EventBus.getDefault().post(info,"infoData");
        }
    }

    class MyBinder extends Binder
    {
        MyService getMyService()
        {
            return MyService.this;
        }
    }

    public ManInfo sendMydata(){
        return  info;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
