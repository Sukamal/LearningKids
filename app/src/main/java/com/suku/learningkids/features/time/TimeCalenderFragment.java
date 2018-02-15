package com.suku.learningkids.features.time;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suku.learningkids.R;
import com.suku.learningkids.adapter.ButtomImageAdapter;
import com.suku.learningkids.adapter.ButtomTextAdapter;
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.features.safety.SafetyPagerAdapter;
import com.suku.learningkids.models.ItemModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class TimeCalenderFragment extends BaseFragment {

    @BindView(R.id.vp_time)
    ViewPager vpTime;
    @BindView(R.id.vp_day_week)
    ViewPager vpDayWeek;
    @BindView(R.id.rv_days_week)
    RecyclerView rvDaysWeek;

    private TimePagerAdapter timePagerAdapter;
    private DayWeekPagerAdapter dayWeekPagerAdapter;
    private ArrayList<ItemModel> timeItemModelList;
    private ArrayList<ItemModel> dayItemModelList;
    private ButtomTextAdapter buttomTextAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_calender, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        checkVersion(view);
        return view;
    }

    private void setAddType() {
        addTypeList = new ArrayList<>();
//        addTypeList.add(AddManager.AddType.STARTAPP_BANNER);
        addTypeList.add(AddManager.AddType.GOOGLE_BANNER);
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
        setTimeItem(true);
        setDayWeekItem(true);
        initCommonItems();
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);

    }

    private void initPaidVersion(View view) {
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
        setTimeItem(true);
        setDayWeekItem(true);
        initCommonItems();
    }


    @Override
    public void onResume() {
        super.onResume();
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();
        initTextToSpeach();
    }

    private void initCommonItems() {
        initTimePager();
        initDayPager();
        initBottomImageRecyclerView();
    }

    private void initTimePager() {
        timePagerAdapter = new TimePagerAdapter(getContext(), timeItemModelList);
        vpTime.setAdapter(timePagerAdapter);
        vpTime.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerItemPosition = position;
                ItemModel itemModel = timeItemModelList.get(position);
                String text = itemModel.getHeading();
                if(itemModel.isLocked()){
                    speakOut("Please Subscribe");
                }else{
                    speakOut(text);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDayPager() {
        dayWeekPagerAdapter = new DayWeekPagerAdapter(getContext(), dayItemModelList);
        vpDayWeek.setAdapter(dayWeekPagerAdapter);
        vpDayWeek.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerItemPosition = position;
                rvDaysWeek.scrollToPosition(position);
//                pagerAdapter.startAnimation();
                ItemModel itemModel = dayItemModelList.get(position);
                String text = itemModel.getHeading();
                if(itemModel.isLocked()){
                    speakOut("Please Subscribe");
                }else{
                    speakOut(text);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void initBottomImageRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDaysWeek.setLayoutManager(layoutManager);
        rvDaysWeek.addItemDecoration(new RecyclerSpacesItemDecoration(0));

        buttomTextAdapter = new ButtomTextAdapter(dayItemModelList, new AdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                if(pagerItemPosition == position){
                    ItemModel itemModel = dayItemModelList.get(position);
                    String text = itemModel.getHeading();
                    if(itemModel.isLocked()){
                        speakOut("Please Subscribe");
                    }else{
                        speakOut(text);
                    }
                }
                vpDayWeek.setCurrentItem(position, true);

            }
        });

        rvDaysWeek.setAdapter(buttomTextAdapter);
    }



    private void setTimeItem(boolean isPaid) {
        timeItemModelList = new ArrayList<>();

        ItemModel itemModel;

        itemModel = new ItemModel("60 Seconds = 1 Minute",0, false);
        timeItemModelList.add(itemModel);

        itemModel = new ItemModel("60 Minutes = 1 Hour",0, false);
        timeItemModelList.add(itemModel);

        itemModel = new ItemModel("24 Hours = 1 Day",0, false);
        timeItemModelList.add(itemModel);

        itemModel = new ItemModel("7 Days = 1 Week",0, false);
        timeItemModelList.add(itemModel);

        itemModel = new ItemModel("4 Weeks = 1 Month",0, false);
        timeItemModelList.add(itemModel);

        itemModel = new ItemModel("12 Months = 1 Year",0, false);
        timeItemModelList.add(itemModel);

        itemModel = new ItemModel("365 Days = 1 Year",0, false);
        timeItemModelList.add(itemModel);

        itemModel = new ItemModel("52 Weeks = 1 Year",0, false);
        timeItemModelList.add(itemModel);




    }

    private void setDayWeekItem(boolean isPaid) {
        dayItemModelList = new ArrayList<>();

        ItemModel itemModel;

        itemModel = new ItemModel("SUNDAY",0, false);
        dayItemModelList.add(itemModel);

        itemModel = new ItemModel("MONDAY",0, false);
        dayItemModelList.add(itemModel);

        itemModel = new ItemModel("TUESDAY",0, false);
        dayItemModelList.add(itemModel);

        itemModel = new ItemModel("WEDNESDAY",0, false);
        dayItemModelList.add(itemModel);

        itemModel = new ItemModel("THURSDAY",0, false);
        dayItemModelList.add(itemModel);

        itemModel = new ItemModel("FRIDAY",0, false);
        dayItemModelList.add(itemModel);

        itemModel = new ItemModel("SATURDAY",0, false);
        dayItemModelList.add(itemModel);

    }

}
