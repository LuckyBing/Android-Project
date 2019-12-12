package com.example.myapplication;

public class Mess {
    private int iconId;
    private String name;
    private String number;
    public Mess(int iconID,String name,String number)
    {
        this.iconId=iconID;
        this.name=name;
        this.number=number;
    }
    public int getIconId()
    {
        return this.iconId;
    }
    public String getName()
    {
        return this.name;
    }
    public String getNumber()
    {
        return this.number;
    }


}
