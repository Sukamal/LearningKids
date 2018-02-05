package com.suku.learningkids.features.parent;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.suku.learningkids.R;
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.features.BaseActivity;
import com.suku.learningkids.features.alphabet.AlphabetFragment;
import com.suku.learningkids.features.color.ColorFragment;
import com.suku.learningkids.features.flatimages.FlatImageFragment;
import com.suku.learningkids.features.months.MonthsFragment;
import com.suku.learningkids.features.numbers.NumberFragment;
import com.suku.learningkids.features.season.SeasonFragment;
import com.suku.learningkids.features.shape.ShapeFragment;
import com.suku.learningkids.util.AppConstant;

import java.util.ArrayList;

public class ParentActivity extends BaseActivity {

    private int selectedMenu;
    private ArrayList<AddManager.AddType> addTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        getExtra();
        displayView(selectedMenu);
    }

    private void setAddType(){
//        addTypeList = new ArrayList<>();
//        addTypeList.add(AddManager.AddType.GOOGLE_BANNER);
//        addTypeList.add(AddManager.AddType.STARTAPP_BANNER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAddType();
        displayAddBasedOnAppType(addTypeList);
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
            fragment = new ShapeFragment();
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
            setApplicationMode(AppConstant.AppType.FREE);
        }else if(menuItem == AppConstant.HomeMenu.SEASONS.getEnumValue()){
            fragment = new SeasonFragment();
            setApplicationMode(AppConstant.AppType.PAID);
        }else if(menuItem == AppConstant.HomeMenu.MONTHS.getEnumValue()){
            fragment = new MonthsFragment();
        }



        else if(menuItem == AppConstant.HomeMenu.ACTIONS.getEnumValue()){
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.ACTIONS.getEnumValue());
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

    private void setApplicationMode(AppConstant.AppType mode){
        ((KidApplication)getApplication()).mAppPreference.setAppType(mode);
    }

}
