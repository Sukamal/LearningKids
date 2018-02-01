package com.suku.learningkids.application;

import android.app.Application;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.suku.learningkids.R;
import com.suku.learningkids.storage.AppPreference;
import com.suku.learningkids.util.AppConstant;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class KidApplication extends Application{

    public AppPreference mAppPreference;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppPreference = new AppPreference(this);
        checkAppFlavour();
    }

    private void initGoogleApp(){
        MobileAds.initialize(this, getString(R.string.add_app_id));
    }

    private void checkAppFlavour(){

        if(mAppPreference.isPaidVersion()){

        }else{
            initGoogleApp();
        }
    }
}
