package com.longrise.pczhaojue.eventbusdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author PCzhaojue
 * @name EventBusDemo
 * @class name：com.longrise.pczhaojue.eventbusdemo
 * @class describe
 * @time 2018/8/2 下午1:24
 * @change
 * @chang time
 * @class describe
 */
public class ManInfo implements Parcelable
{
    private  String name;
    private  String sex;

    public ManInfo(String name, String sex)
    {
        this.name = name;
        this.sex = sex;
    }

    protected ManInfo(Parcel in)
    {
        name = in.readString();
        sex = in.readString();
    }

    public static final Creator<ManInfo> CREATOR = new Creator<ManInfo>()
    {
        @Override
        public ManInfo createFromParcel(Parcel in)
        {
            return new ManInfo(in);
        }

        @Override
        public ManInfo[] newArray(int size)
        {
            return new ManInfo[size];
        }
    };

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeString(sex);
    }

    @Override
    public String toString()
    {
        return "ManInfo{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

