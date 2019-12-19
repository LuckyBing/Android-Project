package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond_txt")
    public String weathercase;

    @SerializedName("wind_spd")
    public String windspeed;

    @SerializedName("vis")
    public String visiable;

    @SerializedName("hum")
    public String hum;

}
