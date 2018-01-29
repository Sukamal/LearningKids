package com.suku.learningkids.features.parent;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.suku.learningkids.R;
import com.suku.learningkids.features.alphabet.AlphabetFragment;
import com.suku.learningkids.util.AppConstant;

public class ParentActivity extends AppCompatActivity {

    private AdView googleAdd;
    private int selectedMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        initializeGoogleAdd();
        getExtra();
        displayView(selectedMenu);
    }

    private void getExtra(){
        if(getIntent().hasExtra(AppConstant.ExtraTag.HOME_MENU_ACTION.name())){
            selectedMenu = getIntent().getIntExtra(AppConstant.ExtraTag.HOME_MENU_ACTION.name(),AppConstant.HomeMenu.ABC.getEnumValue());
        }
    }

    private void displayView(int menuItem){
        Fragment fragment = null;

        if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){
            fragment = new AlphabetFragment();
        }else if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){

        }if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){

        }if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){

        }if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){

        }if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){

        }if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){

        }if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){

        }

        if(fragment != null){
            addFragment(fragment,false);
        }
    }

    private void addFragment(final Fragment fragment, final boolean addtoBac){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStackImmediate(fragment.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_MainContainer,fragment,fragment.getClass().getName());
                if(addtoBac){
                    fragmentTransaction.addToBackStack(fragment.getClass().getName());
                }
                fragmentTransaction.commit();
            }
        });
    }




    private void initializeGoogleAdd(){
        googleAdd = findViewById(R.id.av_google_add);
//        googleAdd.setAdSize(AdSize.BANNER);
//        googleAdd.setAdUnitId(getString(R.string.banner_footer));
//        AdRequest adRequest = new AdRequest.Builder().build();
//        googleAdd.loadAd(adRequest);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("D463C1CED27146A7809AA8C59A1E5E8B")
                .build();

        googleAdd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        googleAdd.loadAd(adRequest);
    }


}
