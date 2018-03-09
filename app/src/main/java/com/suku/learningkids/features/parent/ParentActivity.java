package com.suku.learningkids.features.parent;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.features.BaseActivity;
import com.suku.learningkids.features.alphabet.AlphabetFragment;
import com.suku.learningkids.features.bodyparts.BodyPartsFragment;
import com.suku.learningkids.features.color.ColorFragment;
import com.suku.learningkids.features.flatimages.FlatImageFragment;
import com.suku.learningkids.features.goodhabit.GoodHabitFragment;
import com.suku.learningkids.features.goodmanners.GoodMannersFragment;
import com.suku.learningkids.features.months.MonthsFragment;
import com.suku.learningkids.features.numbers.NumberFragment;
import com.suku.learningkids.features.promotion.PromotionalFragment;
import com.suku.learningkids.features.safety.SafetyFragment;
import com.suku.learningkids.features.season.SeasonFragment;
import com.suku.learningkids.features.shape.ShapeFragment;
import com.suku.learningkids.features.time.TimeCalenderFragment;
import com.suku.learningkids.purchase.InAppPurchaseManager;
import com.suku.learningkids.util.AppConstant;

import java.util.ArrayList;

public class ParentActivity extends BaseActivity {

    private int selectedMenu;
    private ArrayList<AddManager.AddType> addTypeList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        getExtra();
        displayView(selectedMenu);
        initToolbar();

    }

    private void checkVersion() {
        isPaidApp = ((KidApplication)getApplication()).mAppPreference.isPaidVersion();
        if (!isPaidApp) {
            setAddType();
            displayAddBasedOnAppType(addTypeList);
        }
    }



    private void setAddType(){
        addTypeList = new ArrayList<>();
//        addTypeList.add(AddManager.AddType.GOOGLE_INTERSTITIAL);
//        addTypeList.add(AddManager.AddType.GOOGLE_BANNER);
        addTypeList.add(AddManager.AddType.STARTAPP_BANNER);
//        addTypeList.add(AddManager.AddType.STARTAPP_INTERSTITIAL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkVersion();
    }


    private void initToolbar(){
//        toolbar = findViewById(R.id.toolbar_actionbar);
//        setSupportActionBar(toolbar);
//        setToolbarTitle("hsadhasdasd");

        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    protected void setToolbarTitle(String title){
//        ((TextView)toolbar.findViewById(R.id.toolbar_title)).setText(title);
        getSupportActionBar().setTitle(title);
    }


    private void getExtra(){
        if(getIntent().hasExtra(AppConstant.ExtraTag.HOME_MENU_ACTION.name())){
            selectedMenu = getIntent().getIntExtra(AppConstant.ExtraTag.HOME_MENU_ACTION.name(),AppConstant.HomeMenu.ABC.getEnumValue());
        }
    }

    private void displayView(int menuItem){
        Fragment fragment = null;
        Bundle bundle = null;
//        displayInterstitial();
        if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_english_alphabet));
            fragment = new AlphabetFragment();
        }else if(menuItem == AppConstant.HomeMenu.NUMBER.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_numbers));
            fragment = new NumberFragment();
        }else if(menuItem == AppConstant.HomeMenu.COLOR.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_colours));
            fragment = new ColorFragment();
        }else if(menuItem == AppConstant.HomeMenu.SHAPE.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_shapes));
            fragment = new ShapeFragment();
        }else if(menuItem == AppConstant.HomeMenu.FLOWER.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_flowers));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.FLOWER.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.FRUIT.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_fruits));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.FRUIT.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.VEGETABLE.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_vegetables));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.VEGETABLE.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.DOMESTICANIMAL.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_domestic_animals));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.DOMESTICANIMAL.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.WILDANIMAL.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_wild_animals));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.WILDANIMAL.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BIRD.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_birds));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BIRD.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.INSECTS.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_insects_reptiles_amphibians));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.INSECTS.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.SEACREATURES.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_sea_creatures));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.SEACREATURES.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.FOOD.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_food_and_beverages));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.FOOD.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.VEHICLES.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_vehicles));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.VEHICLES.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.KITCHEN.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_kitchen));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.KITCHEN.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BATHROOM.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_bathroom));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BATHROOM.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BEDROOM.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_bedroom));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BEDROOM.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.STATIONARY.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_stationery));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.STATIONARY.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.MUSICALINSTRUMENT.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_musical_instruments));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.MUSICALINSTRUMENT.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.SPORTSEQUIPEMENT.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_sports_equipments));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.SPORTSEQUIPEMENT.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.COMPUTERPARTS.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_computer_parts));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.COMPUTERPARTS.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.SEASONS.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_seasons));
            fragment = new SeasonFragment();
        }else if(menuItem == AppConstant.HomeMenu.MONTHS.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_months));
            fragment = new MonthsFragment();
        }else if(menuItem == AppConstant.HomeMenu.SAFETY.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_safety));
            fragment = new SafetyFragment();
        }else if(menuItem == AppConstant.HomeMenu.GOODHABIT.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_good_habits));
            fragment = new GoodHabitFragment();
        }else if(menuItem == AppConstant.HomeMenu.GOODMANERS.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_good_manners));
            fragment = new GoodMannersFragment();
        }else if(menuItem == AppConstant.HomeMenu.OURHELPERS.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_our_helpers));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.OURHELPERS.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BODYPARTS.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_body_parts));
            fragment = new BodyPartsFragment();
        }else if(menuItem == AppConstant.HomeMenu.TIMEANDCALENDER.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_time_calender));
            fragment = new TimeCalenderFragment();
        }else if(menuItem == AppConstant.HomeMenu.ACTIONS.getEnumValue()){
            setToolbarTitle(getString(R.string.menu_actions));
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.ACTIONS.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.PROMOTE.getEnumValue()){
            fragment = new PromotionalFragment();
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

}
