package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("date")
    public String date;


    @SerializedName("tmp_max")
    public String max;



    @SerializedName("tmp_min")
    public String min;


    @SerializedName("cond_txt_d")
        public String lightinfo;

    @SerializedName("cond_txt_n")
    public String nightinfo;


}
