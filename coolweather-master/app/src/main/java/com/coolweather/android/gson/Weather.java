package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;
/*
将所有的数据整合到一个类中
status string 存放了返回的状态，成功的话会返回“OK”
分别将basic,now,lifestyle,daily_forecast定义为weather的成员
便于将数据直接解析到weather
 */
public class Weather{
    @SerializedName("status")
    public String status;
    public Basic basic;
    public Now now;
    @SerializedName("lifestyle")
    public List<Lifestyle>lifestyles;
    @SerializedName("daily_forecast")
    public List<Forecast> forecasts;
    //private Lifestyle lifestyle;
}
