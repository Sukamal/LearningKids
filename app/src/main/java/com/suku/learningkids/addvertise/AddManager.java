package com.suku.learningkids.addvertise;

import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdView;
import com.suku.learningkids.R;

import java.util.List;

/**
 * Created by SukamalD on 01-02-2018.
 */

public class AddManager {

    public static enum AddType{
        GOOGLE_BANNER(1),
        GOOGLE_INTERSTITIAL(2)
        ;

        private int enumValue;

        AddType(int enumValue){
            this.enumValue = enumValue;
        }

        public int getEnumValue() {
            return enumValue;
        }
    }

    private Context context;

    public AddManager(Context context){
        this.context = context;
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
                }
            }
        }
    }

    private void displayGoogleBanner(View view){
        AdView googleAdd = view.findViewById(R.id.av_google_add);
        GoogleAdd googleAdd1 = new GoogleAdd(context,googleAdd);
        googleAdd1.showGoogleBannerAdd();
    }

    private void displayGoogleInterstitialAdd(){
        GoogleAdd googleAdd1 = new GoogleAdd(context);
        googleAdd1.showGoogleInterstitialAdd();
    }


}
