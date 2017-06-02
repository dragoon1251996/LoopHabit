package com.t3h.whiyew.loophabit;


/**
 * Created by hoadu on 09/05/2017.
 */

public class NameHabit {
    String color;
    String tenThuocTinh;
    Time time;
    Data date;

    public NameHabit(String color, String tenThuocTinh, Time time, Data date) {
        this.color = color;
        this.tenThuocTinh = tenThuocTinh;
        this.time = time;
        this.date = date;
    }

    public NameHabit(String color, String tenThuocTinh, Time time) {
        this.color = color;
        this.tenThuocTinh = tenThuocTinh;
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Data getDate() {
        return date;
    }

    public void setDate(Data date) {
        this.date = date;
    }

    public NameHabit(String color, String tenThuocTinh) {
        this.tenThuocTinh = tenThuocTinh;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getTenThuocTinh() {
        return tenThuocTinh;
    }

    public void setTenThuocTinh(String tenThuocTinh) {
        this.tenThuocTinh = tenThuocTinh;
    }
}
