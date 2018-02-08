package com.suku.learningkids.application;

import android.app.Application;
import android.speech.tts.TextToSpeech;

import com.google.android.gms.ads.MobileAds;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.suku.learningkids.R;
import com.suku.learningkids.storage.AppPreference;

import java.util.Locale;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class KidApplication extends Application{

    public AppPreference mAppPreference;
    public TextToSpeech textToSpeech;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppPreference = new AppPreference(this);
        checkAppFlavour();
        initTextToSpeach();
    }

    private void initGoogleAdd(){
        MobileAds.initialize(this, getString(R.string.google_add_app_id));
    }

    private void initStartAppAdd(){
        StartAppSDK.init(this, "Your App ID", true);
    }


    private void checkAppFlavour(){

        if(mAppPreference.isPaidVersion()){

        }else{
            initGoogleAdd();
            initStartAppAdd();

        }
    }

    public void initTextToSpeach() {
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                }
            }
        });
    }
}
