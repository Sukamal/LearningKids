package com.suku.learningkids.addvertise;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.ads.banner.Banner;
import com.suku.learningkids.R;

import java.util.List;

/**
 * Created by SukamalD on 01-02-2018.
 */

public class AddManager {

    public static enum AddType{
        GOOGLE_BANNER(1),
        GOOGLE_INTERSTITIAL(2),
        STARTAPP_BANNER(3)
        ;

        private int enumValue;

        AddType(int enumValue){
            this.enumValue = enumValue;
        }

        public int getEnumValue() {
            return enumValue;
        }
    }

    private Activity activity;

    public AddManager(Activity activity){
        this.activity = activity;
    }

    public void displayAdd(List<AddType> addTypes,View view){
        if(addTypes != null){
            for(int i =0; i < addTypes.size(); i++){
                AddType addType = addTypes.get(i);
                switch (addType){
                    case GOOGLE_BANNER:
                        displayGoogleBanner(view);
                        break;
                    case GOOGLE_INTERSTITIAL:
                        displayGoogleInterstitialAdd();
                        break;
                    case STARTAPP_BANNER:
                        displayStartAppBanner(view);
                        break;
                }
            }
        }
    }

    private void displayGoogleBanner(View view){
        GoogleAdd googleAdd = new GoogleAdd(activity);
        googleAdd.showGoogleBanner(view);

    }

    private void displayGoogleInterstitialAdd(){
        GoogleAdd googleAdd1 = new GoogleAdd(activity);
        googleAdd1.showGoogleInterstitialAdd();
    }

    private void displayStartAppBanner(View view){
        StartAppAdd startAppAdd = new StartAppAdd(activity);
        startAppAdd.showStartAppBanner(view);
    }


}
