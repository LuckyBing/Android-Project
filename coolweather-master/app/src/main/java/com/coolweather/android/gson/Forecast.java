package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;
/*
解析daily_forecast数据对应的类
将daily_forcast中的date\tmp_max,tmp_min,cond_txt_d,cond_txt_n解析出来
HeFeng中daily_forecast类型如下：
"daily_forecast":[
{"cond_code_d":"101","cond_code_n":"305","cond_txt_d":"多云","cond_txt_n":"小雨","date":"2019-12-20",
"hum":"70","mr":"00:46","ms":"13:19","pcpn":"0.0","pop":"0","pres":"1021","sr":"07:15","ss":"17:25",
"tmp_max":"10","tmp_min":"4","uv_index":"0","vis":"25","wind_deg":"92","wind_dir":"东风","wind_sc":"1-2","wind_spd":"9"},
{"cond_code_d":"305","cond_code_n":"305","cond_txt_d":"小雨","cond_txt_n":"小雨","date":"2019-12-21",
"hum":"62","mr":"01:50","ms":"13:54","pcpn":"2.9","pop":"67","pres":"1018","sr":"07:16","ss":"17:26",
"tmp_max":"8","tmp_min":"2","uv_index":"1","vis":"25","wind_deg":"308","wind_dir":"西北风","wind_sc":"1-2","wind_spd":"3"},
{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2019-12-22",
"hum":"66","mr":"02:53","ms":"14:31","pcpn":"0.0","pop":"4","pres":"1017","sr":"07:16","ss":"17:26",
"tmp_max":"12","tmp_min":"3","uv_index":"1","vis":"25","wind_deg":"125","wind_dir":"东南风","wind_sc":"1-2","wind_spd":"1"}]

因为daily_forecast返回的是一个列表数据，所以在weather类中需定义为List<E>型
 */
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
