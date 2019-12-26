package com.coolweather.android.db;

import org.litepal.crud.DataSupport;
/*
数据库，存放省信息
id int litepal中必须包含的属性
provinceName String 城市名
provinceCode int 省id(极速数据API中查询附属城市时使用)
 */
public class Province extends DataSupport {

    private int id;

    private String provinceName;

    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
