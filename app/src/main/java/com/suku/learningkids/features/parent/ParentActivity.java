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
import com.suku.learningkids.features.color.ColorFragment;
import com.suku.learningkids.features.flatimages.FlatImageFragment;
import com.suku.learningkids.features.numbers.NumberFragment;
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
        Bundle bundle = null;

        if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){
            fragment = new AlphabetFragment();
        }else if(menuItem == AppConstant.HomeMenu.NUMBER.getEnumValue()){
            fragment = new NumberFragment();
        }else if(menuItem == AppConstant.HomeMenu.COLOR.getEnumValue()){
            fragment = new ColorFragment();
        }else if(menuItem == AppConstant.HomeMenu.SHAPE.getEnumValue()){

        }else if(menuItem == AppConstant.HomeMenu.FLOWER.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.FLOWER.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.FRUIT.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.FRUIT.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.VEGETABLE.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.VEGETABLE.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.DOMESTICANIMAL.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.DOMESTICANIMAL.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.WILDANIMAL.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.WILDANIMAL.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.WILDANIMAL.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BIRD.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BIRD.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BIRD.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.INSECTS.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.INSECTS.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.SEACREATURES.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.SEACREATURES.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.FOOD.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.FOOD.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.VEHICLES.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.VEHICLES.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.KITCHEN.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.KITCHEN.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BATHROOM.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BATHROOM.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BEDROOM.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BEDROOM.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.STATIONARY.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.STATIONARY.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.MUSICALINSTRUMENT.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.MUSICALINSTRUMENT.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.SPORTSEQUIPEMENT.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.SPORTSEQUIPEMENT.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.SEASONS.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.SEASONS.getEnumValue());
        }

        if(fragment != null){
            addFragment(fragment,false,bundle);
        }
    }

    private void addFragment(final Fragment fragment, final boolean addtoBac, final Bundle bundle){
        new Handler().post(new Runnable() {
            @Override
            public void run() {


                if(bundle != null){
                    fragment.setArguments(bundle);
                }

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
