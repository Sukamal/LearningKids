package com.suku.learningkids.features.color;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suku.learningkids.R;
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.models.ItemModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class ColorFragment extends BaseFragment {

    @BindView(R.id.vp_color)
    ViewPager vpColor;
    @BindView(R.id.rv_color_list)
    RecyclerView rvColorList;
    private ColorPagerAdapter pagerAdapter;
    private ColorListAdapter listAdapter;
    private ArrayList<ItemModel> alphabetModels;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color,container,false);
        ButterKnife.bind(this,view);
        checkVersion(view);
        return view;
    }

    private void setAddType() {
        addTypeList = new ArrayList<>();
        addTypeList.add(AddManager.AddType.STARTAPP_BANNER);
        addTypeList.add(AddManager.AddType.GOOGLE_INTERSTITIAL);
    }

    private void checkVersion(View view) {
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();
        if (isPaidApp) {
            initPaidVersion(view);
        } else {
            initFreeVersion(view);
        }
    }

    private void initFreeVersion(View view) {
        setAlphabets(false);
        initCommonItems();
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);

    }

    private void initPaidVersion(View view) {
        setAlphabets(true);
        initCommonItems();
    }

    @Override
    public void onResume() {
        super.onResume();
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();
        initTextToSpeach();
//        applyAnimation();
    }

    private void initCommonItems(){
        initPager();
        initAlphabetRecyclerView();

    }



    private void setAlphabets(boolean isPaid){
        alphabetModels = new ArrayList<>();

        ItemModel alphabetModel;

        alphabetModel = new ItemModel("","","RED",R.color.color_red,false);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","GREEN",R.color.color_green,false);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","BLACK",R.color.color_black,false);
        alphabetModel.setTextColor(R.color.color_white);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","BLUE",R.color.color_blue,false);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","WHITE",R.color.color_white,false);
        alphabetModel.setTextColor(R.color.color_black);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","GRAY",R.color.color_grey,false);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","BROWN",R.color.color_brown,false);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","ORANGE",R.color.color_orange,false);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","YELLOW",R.color.color_yellow,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","VIOLET",R.color.color_violet,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","PINK",R.color.color_pink,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","PURPLE",R.color.color_purple,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","CREAM",R.color.color_cream,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","GOLDEN",R.color.color_golden,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","SILVER",R.color.color_silver,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","LIGHT BLUE",R.color.color_light_blue,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","MAROON",R.color.color_maroon,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","SKY BLUE",R.color.color_sky_blue,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","IRIS BLUE",R.color.color_iris_blue,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","ROYAL BLUE",R.color.color_royal_blue,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","NAVY BLUE",R.color.color_navy_blue,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","CHOCOLATE",R.color.color_chocolate,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","OLIVE GREEN",R.color.color_oliv_green,!isPaid);
        alphabetModels.add(alphabetModel);

        alphabetModel = new ItemModel("","","MAGENTA",R.color.color_magenta,!isPaid);
        alphabetModels.add(alphabetModel);



    }

    private void initPager(){
        pagerAdapter = new ColorPagerAdapter(getContext(),alphabetModels);
        vpColor.setAdapter(pagerAdapter);
        vpColor.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerItemPosition = position;
                rvColorList.scrollToPosition(position);
                pagerAdapter.startAnimation();
                ItemModel alphabetModel = alphabetModels.get(position);
                String text = alphabetModel.getSubheading2() + " color";
                speakOut(text);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initAlphabetRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        rvColorList.setLayoutManager(gridLayoutManager);
        rvColorList.addItemDecoration(new RecyclerSpacesItemDecoration(0));

        listAdapter = new ColorListAdapter(alphabetModels, new AdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                if(pagerItemPosition == position){
                    ItemModel alphabetModel = alphabetModels.get(position);
                    String text = alphabetModel.getSubheading2() + " color";
                    speakOut(text);
                }
                vpColor.setCurrentItem(position,true);
            }
        });

        rvColorList.setAdapter(listAdapter);
    }

}
