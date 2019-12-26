package com.coolweather.android.db;

import org.litepal.crud.DataSupport;
/*
数据库，存放城市信息
id int litepal中必须包含的属性
cityName String 城市名
cityCode int 城市的id(极速数据API中查询附属城市时使用)
provinceId String 上一级名称，即省名称
 */

public class City extends DataSupport {

    private int id;

    private String cityName;

    private int cityCode;

    private String provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

}
