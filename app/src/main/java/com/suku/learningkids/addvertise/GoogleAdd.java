package com.suku.learningkids.addvertise;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.suku.learningkids.R;

/**
 * Created by SukamalD on 01-02-2018.
 */

public class GoogleAdd {

    private Context context;
    private AdView googleAdd;

    public GoogleAdd(Context context){
        this.context = context;
    }

    public GoogleAdd(Context context, AdView googleAdd){
        this.context = context;
        this.googleAdd = googleAdd;
    }

    public void showGoogleBannerAdd(){
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

    public void showGoogleInterstitialAdd(){

        final InterstitialAd mInterstitialAd;
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.add_interstitial_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
    }
}
