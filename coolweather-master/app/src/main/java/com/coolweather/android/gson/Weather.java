package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
