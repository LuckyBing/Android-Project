package com.coolweather.android.db;

import org.litepal.crud.DataSupport;
/*
数据库，存放县和区信息
id int litepal中必须包含的属性
countyName String 城市名
cityId String 上一级名称，即城市名称
 */
public class County extends DataSupport {

    private int id;

    private String countyName;


    private String cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }



    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

}
