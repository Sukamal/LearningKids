package com.suku.learningkids.addvertise;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.startapp.android.publish.ads.banner.Banner;
import com.suku.learningkids.R;

/**
 * Created by SukamalD on 01-02-2018.
 */

public class GoogleAdd {

    private Context context;

    public GoogleAdd(Context context){
        this.context = context;
    }

    public void showGoogleInterstitialAdd(){

        final InterstitialAd mInterstitialAd;
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.google_add_interstitial_id));
        AdRequest adRequest = new AdRequest.Builder()
                /*.addTestDevice("D463C1CED27146A7809AA8C59A1E5E8B")
                .addTestDevice("34BED8A5AA68EBF2EC529B13620F256D")
                .addTestDevice("8468887C6E2CC15ECCA22E9A5B75D7B6")*/
                .build();

        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });

    }

    public void showGoogleBanner(View view){
        LinearLayout mainLayout = (LinearLayout)view.findViewById(R.id.ll_banner);

        int childCount = mainLayout.getChildCount();
        for(int index=0; index<childCount; ++index) {
            View nextChild = mainLayout.getChildAt(index);
            if(nextChild instanceof AdView);
                mainLayout.removeView(nextChild);
        }

        AdView adView = new AdView(context);
        adView.setId(R.id.googleBannerId);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(context.getResources().getString(R.string.google_add_banner_id));
        LinearLayout.LayoutParams bannerParameters =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.addView(adView, bannerParameters);
        showGoogleBannerAdd(adView);
    }

    private void showGoogleBannerAdd(AdView googleAdd){
//        AdView googleAdd = findViewById(R.id.av_google_add);
//        googleAdd.setAdSize(AdSize.BANNER);
//        googleAdd.setAdUnitId(getString(R.string.banner_footer));
//        AdRequest adRequest = new AdRequest.Builder().build();
//        googleAdd.loadAd(adRequest);

//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("D463C1CED27146A7809AA8C59A1E5E8B")
//                .build();

        AdRequest adRequest = new AdRequest.Builder()
                /*.addTestDevice("D463C1CED27146A7809AA8C59A1E5E8B")
                .addTestDevice("34BED8A5AA68EBF2EC529B13620F256D")
                .addTestDevice("8468887C6E2CC15ECCA22E9A5B75D7B6")*/
//                .setIsDesignedForFamilies(true)
//                .tagForChildDirectedTreatment(true)
                .build();

        googleAdd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
//                Toast.makeText(context, "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
//                Toast.makeText(context, "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
//                Toast.makeText(context, "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        googleAdd.loadAd(adRequest);
    }
}
