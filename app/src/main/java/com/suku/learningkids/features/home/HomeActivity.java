package com.suku.learningkids.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.suku.learningkids.R;
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.features.BaseActivity;
import com.suku.learningkids.features.parent.ParentActivity;
import com.suku.learningkids.purchase.PurchaseActivity;
import com.suku.learningkids.util.AppConstant;
import com.suku.learningkids.util.AppDialog;
import com.suku.learningkids.util.UtilClass;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.rv_home)
    RecyclerView mRvHome;
    @BindView(R.id.fab_share)
    FloatingActionButton fabShare;

    private HomeMenuAdapter menuAdapter;
    private  ArrayList<MenuModel> menuModels;
    private ArrayList<AddManager.AddType> addTypeList;
    private boolean isPaidApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        checkVersion();
    }

    private void checkVersion() {
        isPaidApp = ((KidApplication)getApplication()).mAppPreference.isPaidVersion();
        if (isPaidApp) {
            initPaidVersion();
        } else {
            initFreeVersion();
        }
    }

    private void initPaidVersion(){
        initRecyclerView();
        initFloatingButton();
    }

    private void initFreeVersion(){
        initRecyclerView();
        initFloatingButton();
    }

    private void initFloatingButton(){
        fabShare.setImageResource(R.drawable.megaphone);
        fabShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
//        fabShare.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#33691E")));
//        fabShare.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNextScreen(AppConstant.HomeMenu.PROMOTE.getEnumValue());
//                startActivity(new Intent(HomeActivity.this, PurchaseActivity.class));
            }
        });
    }


    private void setAddType(){
        addTypeList = new ArrayList<>();
        addTypeList.add(AddManager.AddType.GOOGLE_BANNER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAddType();
        displayAddBasedOnAppType(addTypeList);
    }

    private void initRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        mRvHome.setLayoutManager(gridLayoutManager);
        mRvHome.addItemDecoration(new RecyclerSpacesItemDecoration(0));
        setHomeMenu();

        if(menuModels != null && menuModels.size() > 0){
            menuAdapter = new HomeMenuAdapter(menuModels);
            mRvHome.setAdapter(menuAdapter);
            menuAdapter.setItemClickListner(new HomeMenuAdapter.ClickListener() {
                @Override
                public void onAdapterItemClick(View view, int position, Object selectedItem) {
                    int actionCode = ((MenuModel)selectedItem).getActionCode();
                    gotoNextScreen(actionCode);
                }
            });
        }

    }

    private void gotoNextScreen(int actionCode){
        Intent intent = new Intent(HomeActivity.this, ParentActivity.class);
        intent.putExtra(AppConstant.ExtraTag.HOME_MENU_ACTION.name(),actionCode);
        isPaidApp = ((KidApplication)getApplication()).mAppPreference.isPaidVersion();
        if(isPaidApp){
            startActivity(intent);
        }else{
            if(UtilClass.isNetworkAvailable(this)){
                startActivity(intent);
            }else{
                AppDialog appDialog = new AppDialog();
                appDialog.showErrorDialog(HomeActivity.this, "Network Error", "Check your network connection. For offline mode please subscribe", new AppDialog.DialogListener() {

                    @Override
                    public void OnPositivePress(Object val) {
                        if(val != null){

                        }
                    }

                    @Override
                    public void OnNegativePress() {

                    }

                });
            }
        }



    }

    private void setHomeMenu(){
        menuModels = new ArrayList<>();
        MenuModel menuModel ;

        menuModel = new MenuModel(getString(R.string.menu_english_alphabet),R.drawable.alphabet,R.color.color1,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.ABC.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_numbers),R.drawable.number,R.color.color2,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.NUMBER.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_colours),R.drawable.color,R.color.color3,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.COLOR.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_shapes),R.drawable.shape,R.color.color4,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.SHAPE.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_flowers),R.drawable.flower,R.color.color5,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.FLOWER.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_fruits),R.drawable.fruit,R.color.color6,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.FRUIT.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_vegetables),R.drawable.carrot_i,R.color.color7,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.VEGETABLE.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_domestic_animals),R.drawable.cow_i,R.color.color8,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.DOMESTICANIMAL.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_wild_animals),R.drawable.tiger_i,R.color.color9,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.WILDANIMAL.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_birds),R.drawable.dove,R.color.color10,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.BIRD.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_insects_reptiles_amphibians),R.drawable.ladybug,R.color.color1,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.INSECTS.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_sea_creatures),R.drawable.shark_i,R.color.color2,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.SEACREATURES.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_food_and_beverages),R.drawable.hamburger,R.color.color3,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.FOOD.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_vehicles),R.drawable.bus_i,R.color.color4,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.VEHICLES.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_kitchen),R.drawable.teapot_i,R.color.color5,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.KITCHEN.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_bathroom),R.drawable.bathtub_i,R.color.color6,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.BATHROOM.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_bedroom),R.drawable.bed_i,R.color.color7,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.BEDROOM.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_stationery),R.drawable.pencils,R.color.color8,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.STATIONARY.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_musical_instruments),R.drawable.guitar,R.color.color9,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.MUSICALINSTRUMENT.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_sports_equipments),R.drawable.football_i,R.color.color10,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.SPORTSEQUIPEMENT.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_computer_parts),R.drawable.computer,R.color.color5,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.COMPUTERPARTS.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_body_parts),R.drawable.eye_i,R.color.color3,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.BODYPARTS.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_actions),R.drawable.crawl_i,R.color.color10,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.ACTIONS.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_seasons),R.drawable.summer,R.color.color8,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.SEASONS.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_months),R.drawable.calendar,R.color.color7,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.MONTHS.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_time_calender),R.drawable.schedule,R.color.color6,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.TIMEANDCALENDER.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_good_habits),R.drawable.toothbrush_i,R.color.color2,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.GOODHABIT.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_good_manners),R.drawable.manners_i,R.color.color7,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.GOODMANERS.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_safety),R.drawable.float_i,R.color.color9,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.SAFETY.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel(getString(R.string.menu_our_helpers),R.drawable.doctor_i,R.color.color1,R.color.colorPrimary);
        menuModel.setActionCode(AppConstant.HomeMenu.OURHELPERS.getEnumValue());
        menuModels.add(menuModel);

        /*menuModel = new MenuModel("PROMOTE APP",R.drawable.megaphone,R.color.color2,R.color.color_11);
        menuModel.setActionCode(AppConstant.HomeMenu.PROMOTE.getEnumValue());
        menuModels.add(menuModel);*/

        /*menuModel = new MenuModel("SOURCE OF WATER",R.drawable.splash,R.color.color2,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("PLACE OF WORSHIP",R.drawable.splash,R.color.color3,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("FESTIVALS",R.drawable.splash,R.color.color4,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("HISTORICAL PLACES OF INDIA",R.drawable.splash,R.color.color5,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("NATIONAL SYMBOLS",R.drawable.splash,R.color.color6,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("OUR GREAT LEADER",R.drawable.splash,R.color.color7,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("OPPOSITES",R.drawable.splash,R.color.color4,R.color.color_11);
        menuModels.add(menuModel);*/

    }

}
