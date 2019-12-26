package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;
/*
解析now数据对应的类
将now中的tmp,cond_txt,wind_spd,vis解析出来
HeFeng中now类型如下：
"now":{"cloud":"98","cond_code":"101","cond_txt":"多云","fl":"3","hum":"84","pcpn":"0.0",
"pres":"1026","tmp":"6","vis":"16","wind_deg":"298","wind_dir":"西北风","wind_sc":"2","wind_spd":"9"},
 */
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
