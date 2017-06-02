package com.t3h.whiyew.loophabit;

/**
 * Created by Whiyew on 24/05/2017.
 */

public class Time {
    String hour;
    String minute;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public Time(String hour, String minute) {

        this.hour = hour;
        this.minute = minute;
    }
}
