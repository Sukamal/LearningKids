package com.suku.learningkids.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.suku.learningkids.util.AppConstant;

/**
 * Created by SukamalD on 01-02-2018.
 */

public class AppPreference {

    public static final String PREF_NAME = "com.learningkid.pref";
    private SharedPreferences preferences;
    private SharedPreferences.Editor appEditor;

    public AppPreference(Context context){
        this.preferences = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        this.appEditor = preferences.edit();
    }

    public void clearAllPreferences(){
        appEditor.clear();
        appEditor.commit();
    }

    public void removePreferences(String key) {
        appEditor.remove(key);
        appEditor.commit();
    }

    public void saveBooleanPref(String touple, boolean data) {

        appEditor.putBoolean(touple, data);
        appEditor.commit();
    }

    public boolean getBooleanPref(String touple) {

        return preferences.getBoolean(touple, false);
    }

    public void saveStringPref(String key, String value) {

        appEditor.putString(key, value);
        appEditor.commit();
    }

    public String getStringPref(String Key) {

        return preferences.getString(Key, null);
    }

    public void saveIntPref(String key, int value) {

        appEditor.putInt(key, value);
        appEditor.commit();
    }

    public int getIntPref(String key) {

        return preferences.getInt(key, AppConstant.AppType.FREE.getEnumValue());
    }

    public boolean isPaidVersion(){
        if(getAppType() == AppConstant.AppType.PAID.getEnumValue()){
            return true;
        }else{
            return false;
        }
    }

    private int getAppType() {
        return preferences.getInt(AppConstant.Preferences.APPLICATION_TYPE.name(), AppConstant.AppType.FREE.getEnumValue());
    }
    public void setAppType(AppConstant.AppType appType) {
        appEditor.putInt(AppConstant.Preferences.APPLICATION_TYPE.name(), appType.getEnumValue());
        appEditor.commit();
    }

    private String getAppRefId() {
        return preferences.getString(AppConstant.Preferences.DEVICE_REGISTERED_KEY.name(), null);
    }
    public void setAppRefId(String appRefId) {
        appEditor.putString(AppConstant.Preferences.DEVICE_REGISTERED_KEY.name(), appRefId);
        appEditor.commit();
    }

    public long getLastAddDisplayTime() {
        return preferences.getLong(AppConstant.Preferences.LAST_INTESTETIAL_ADD_DISPLAYED.name(), System.currentTimeMillis());
    }
    public void setLastAddDisplayTime() {
        appEditor.putLong(AppConstant.Preferences.LAST_INTESTETIAL_ADD_DISPLAYED.name(), System.currentTimeMillis());
        appEditor.commit();
    }



    public boolean isAppRegistered(){
        if(getAppRefId() == null){
            return false;
        }else{
            return true;
        }
    }

}
