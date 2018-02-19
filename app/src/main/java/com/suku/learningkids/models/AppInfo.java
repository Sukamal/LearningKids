package com.suku.learningkids.models;

/**
 * Created by SukamalD on 19-02-2018.
 */

public class AppInfo {
    private String deviceId;
    private boolean ispaid;
    private String appRefNum;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isIspaid() {
        return ispaid;
    }

    public void setIspaid(boolean ispaid) {
        this.ispaid = ispaid;
    }

    public String getAppRefNum() {
        return appRefNum;
    }

    public void setAppRefNum(String appRefNum) {
        this.appRefNum = appRefNum;
    }
}
