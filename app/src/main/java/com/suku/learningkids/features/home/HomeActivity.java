package com.suku.learningkids.features.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.suku.learningkids.R;
import com.suku.learningkids.features.parent.ParentActivity;
import com.suku.learningkids.util.AppConstant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rv_home)
    RecyclerView mRvHome;

    private HomeMenuAdapter menuAdapter;
    private  ArrayList<MenuModel> menuModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initRecyclerView();
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
                    Toast.makeText(HomeActivity.this,((MenuModel)selectedItem).getMenuTitle(),Toast.LENGTH_SHORT).show();
                    gotoNextScreen(actionCode);
                }
            });
        }

    }

    private void gotoNextScreen(int actionCode){
        Intent intent = new Intent(HomeActivity.this, ParentActivity.class);
        intent.putExtra(AppConstant.ExtraTag.HOME_MENU_ACTION.name(),actionCode);
        startActivity(intent);
    }

    private void setHomeMenu(){
        menuModels = new ArrayList<>();
        MenuModel menuModel ;

        menuModel = new MenuModel("ENGLISH ALPHABET",R.drawable.alphabet,R.color.color1,R.color.color_11);
        menuModel.setActionCode(AppConstant.HomeMenu.ABC.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel("NUMBERS",R.drawable.number,R.color.color2,R.color.color_11);
        menuModel.setActionCode(AppConstant.HomeMenu.NUMBER.getEnumValue());
        menuModels.add(menuModel);

        menuModel = new MenuModel("COLOURS",R.drawable.color,R.color.color3,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("SHAPES",R.drawable.shape,R.color.color4,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("FLOWERS",R.drawable.flower,R.color.color5,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("FRUITS",R.drawable.fruit,R.color.color6,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("VEGETABLES",R.drawable.carrot,R.color.color7,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("DOMESTIC ANIMALS",R.drawable.cow,R.color.color8,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("WILD ANIMALS",R.drawable.tiger,R.color.color9,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("BIRDS",R.drawable.dove,R.color.color10,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("INSECTS REPTILES & AMPHIBIANS",R.drawable.ladybug,R.color.color1,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("SEA CREATURES",R.drawable.shark,R.color.color2,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("FOOD AND BEVERAGES",R.drawable.hamburger,R.color.color3,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("VEHICLES",R.drawable.bus,R.color.color4,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("KITCHEN",R.drawable.teapot,R.color.color5,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("BATHROOM",R.drawable.bathtub,R.color.color6,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("BEDROOM",R.drawable.bed,R.color.color7,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("STATIONERY",R.drawable.pencils,R.color.color8,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("MUSICAL INSTRUMENTS",R.drawable.guitar,R.color.color9,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("SPORTS EQUIPMENTS",R.drawable.football,R.color.color10,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("SEASONS",R.drawable.summer,R.color.color8,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("MONTHS",R.drawable.calendar,R.color.color7,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("TIME AND CALENDER",R.drawable.schedule,R.color.color6,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("COMPUTER PARTS & PERIPHERALS",R.drawable.computer,R.color.color5,R.color.color_11);
        menuModels.add(menuModel);


        menuModel = new MenuModel("OPPOSITES",R.drawable.splash,R.color.color4,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("BODY PARTS",R.drawable.splash,R.color.color3,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("GOOD HABITS",R.drawable.splash,R.color.color2,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("GOOD MANNERS",R.drawable.splash,R.color.color1,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("SAFETY",R.drawable.splash,R.color.color9,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("ACTIONS",R.drawable.splash,R.color.color10,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("OUR HELPERS",R.drawable.splash,R.color.color1,R.color.color_11);
        menuModels.add(menuModel);

        menuModel = new MenuModel("SOURCE OF WATER",R.drawable.splash,R.color.color2,R.color.color_11);
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

    }

}
