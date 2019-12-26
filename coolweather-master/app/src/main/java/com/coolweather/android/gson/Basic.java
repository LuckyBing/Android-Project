package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;
/*
解析basic数据对应的类
将basic中的cid,parent_city,location解析出来
HeFeng中basic类型如下：
"basic":{"cid":"CN101200101","location":"武汉","parent_city":"武汉","admin_area":"湖北","cnty":"中国","lat":"30.5843544","lon":"114.29856873","tz":"+8.00"}
 */

public class Basic {

    @SerializedName("cid")
    public String cid;

    @SerializedName("parent_city")
    public String pName;

    @SerializedName("location")
    public String cName;



}
