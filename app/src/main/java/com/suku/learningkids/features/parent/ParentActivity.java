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

    private void setAddType(){
        addTypeList = new ArrayList<>();
        addTypeList.add(AddManager.AddType.GOOGLE_INTERSTITIAL);
//        addTypeList.add(AddManager.AddType.GOOGLE_BANNER);
//        addTypeList.add(AddManager.AddType.STARTAPP_BANNER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAddType();
        displayAddBasedOnAppType(addTypeList);
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

        if(menuItem == AppConstant.HomeMenu.ABC.getEnumValue()){
            setToolbarTitle("Alphabets");
            fragment = new AlphabetFragment();
        }else if(menuItem == AppConstant.HomeMenu.NUMBER.getEnumValue()){
            setToolbarTitle("Numbers");
            fragment = new NumberFragment();
        }else if(menuItem == AppConstant.HomeMenu.COLOR.getEnumValue()){
            setToolbarTitle("Colours");
            fragment = new ColorFragment();
        }else if(menuItem == AppConstant.HomeMenu.SHAPE.getEnumValue()){
            setToolbarTitle("Shapes");
            fragment = new ShapeFragment();
        }else if(menuItem == AppConstant.HomeMenu.FLOWER.getEnumValue()){
            setToolbarTitle("Flowers");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.FLOWER.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.FRUIT.getEnumValue()){
            setToolbarTitle("Fruits");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.FRUIT.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.VEGETABLE.getEnumValue()){
            setToolbarTitle("Vegetables");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.VEGETABLE.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.DOMESTICANIMAL.getEnumValue()){
            setToolbarTitle("Domestic Animals");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.DOMESTICANIMAL.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.WILDANIMAL.getEnumValue()){
            setToolbarTitle("Wild Animals");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.WILDANIMAL.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BIRD.getEnumValue()){
            setToolbarTitle("Birds");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BIRD.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.INSECTS.getEnumValue()){
            setToolbarTitle("Insects, Reptiles & Amphibians");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.INSECTS.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.SEACREATURES.getEnumValue()){
            setToolbarTitle("Sea Creatures");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.SEACREATURES.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.FOOD.getEnumValue()){
            setToolbarTitle("Food & Beverage");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.FOOD.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.VEHICLES.getEnumValue()){
            setToolbarTitle("Vehicles");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.VEHICLES.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.KITCHEN.getEnumValue()){
            setToolbarTitle("Kitchen");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.KITCHEN.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BATHROOM.getEnumValue()){
            setToolbarTitle("Bathroom");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BATHROOM.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BEDROOM.getEnumValue()){
            setToolbarTitle("Bedroom");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.BEDROOM.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.STATIONARY.getEnumValue()){
            setToolbarTitle("Stationary");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.STATIONARY.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.MUSICALINSTRUMENT.getEnumValue()){
            setToolbarTitle("Musical Instruments");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.MUSICALINSTRUMENT.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.SPORTSEQUIPEMENT.getEnumValue()){
            setToolbarTitle("Sports Equipments");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.SPORTSEQUIPEMENT.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.COMPUTERPARTS.getEnumValue()){
            setToolbarTitle("Computer Parts");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.COMPUTERPARTS.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.SEASONS.getEnumValue()){
            setToolbarTitle("Seasons");
            fragment = new SeasonFragment();
        }else if(menuItem == AppConstant.HomeMenu.MONTHS.getEnumValue()){
            setToolbarTitle("Months");
            fragment = new MonthsFragment();
        }else if(menuItem == AppConstant.HomeMenu.SAFETY.getEnumValue()){
            setToolbarTitle("Safety");
            fragment = new SafetyFragment();
        }else if(menuItem == AppConstant.HomeMenu.GOODHABIT.getEnumValue()){
            setToolbarTitle("Good Habits");
            fragment = new GoodHabitFragment();
        }else if(menuItem == AppConstant.HomeMenu.GOODMANERS.getEnumValue()){
            setToolbarTitle("Good Manners");
            fragment = new GoodMannersFragment();
        }else if(menuItem == AppConstant.HomeMenu.OURHELPERS.getEnumValue()){
            setToolbarTitle("Our Helpers");
            fragment = new FlatImageFragment();
            bundle = new Bundle();
            bundle.putInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name(),AppConstant.HomeMenu.OURHELPERS.getEnumValue());
        }else if(menuItem == AppConstant.HomeMenu.BODYPARTS.getEnumValue()){
            setToolbarTitle("Body Parts");
            fragment = new BodyPartsFragment();
        }else if(menuItem == AppConstant.HomeMenu.TIMEANDCALENDER.getEnumValue()){
            setToolbarTitle("Time & Calender");
            fragment = new TimeCalenderFragment();
        }else if(menuItem == AppConstant.HomeMenu.ACTIONS.getEnumValue()){
            setToolbarTitle("Actions");
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
