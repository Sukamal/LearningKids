package com.suku.learningkids.application;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.suku.learningkids.R;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class KidApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, getString(R.string.add_app_id));

    }
}
