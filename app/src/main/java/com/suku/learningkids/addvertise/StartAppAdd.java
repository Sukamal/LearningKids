package com.suku.learningkids.addvertise;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.suku.learningkids.R;

/**
 * Created by SukamalD on 02-02-2018.
 */

public class StartAppAdd {

    private Activity activity;

    public StartAppAdd(Activity activity){
        this.activity = activity;
    }

    public void showStartAppBanner(View view){
        LinearLayout mainLayout = (LinearLayout)view.findViewById(R.id.ll_banner);

        int childCount = mainLayout.getChildCount();
        for(int index=0; index<childCount; ++index) {
            View nextChild = mainLayout.getChildAt(index);
            if(nextChild instanceof Banner);
                mainLayout.removeView(nextChild);
        }

        Banner startAppBanner = new Banner(activity);
        startAppBanner.setId(R.id.startAppBannerId);
        LinearLayout.LayoutParams bannerParameters =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.addView(startAppBanner, bannerParameters);
    }

    public void showStartAppInterstitial(View view){
        StartAppAd.showAd(activity);
    }
}
