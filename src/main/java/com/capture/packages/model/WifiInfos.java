package com.capture.packages.model;

/**
 * Created by Benedict Jin on 2016/4/15.
 */
public class WifiInfos {

    private String deviceIP;

    public WifiInfos() {
    }

    public WifiInfos(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }
}
