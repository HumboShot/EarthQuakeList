package com.humboshot.earthquakelist.DataObject;

/**
 * Created by Christian on 01-03-2017.
 */

public class Earthquake {
    String LocationOfQuake, Time, Mag;

    public String getLocationOfQuake() {
        return LocationOfQuake;
    }

    public void setLocationOfQuake(String locationOfQuake) {
        LocationOfQuake = locationOfQuake;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMag() {
        return Mag;
    }

    public void setMag(String mag) {
        Mag = mag;
    }

}
