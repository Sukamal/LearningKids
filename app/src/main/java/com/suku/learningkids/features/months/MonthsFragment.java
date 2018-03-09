package com.suku.learningkids.features.months;

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
import com.suku.learningkids.adapter.ButtomTextAdapter;
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

public class MonthsFragment extends BaseFragment {

    @BindView(R.id.vp_flower)
    ViewPager vpFlower;
    @BindView(R.id.rv_image_list)
    RecyclerView rvImageList;

    private MonthsPagerAdapter pagerAdapter;
    private ArrayList<ItemModel> itemModelList;
    private ButtomTextAdapter bottomTextAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flat_images, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        checkVersion(view);
        return view;
    }

    private void setAddType() {
        addTypeList = new ArrayList<>();
//        addTypeList.add(AddManager.AddType.GOOGLE_INTERSTITIAL);
//        addTypeList.add(AddManager.AddType.GOOGLE_BANNER);
//        addTypeList.add(AddManager.AddType.STARTAPP_BANNER);
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
        setMonthsImages(false);
        initCommonItems();
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);

    }

    private void initPaidVersion(View view) {
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
        setMonthsImages(true);
        initCommonItems();
    }


    @Override
    public void onResume() {
        super.onResume();
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();
        initTextToSpeach();
    }

    private void initCommonItems() {
        initPager();
        initBottomImageRecyclerView();
    }

    private void initPager() {
        pagerAdapter = new MonthsPagerAdapter(getActivity(), itemModelList);
        vpFlower.setAdapter(pagerAdapter);
        vpFlower.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerItemPosition = position;
                rvImageList.scrollToPosition(position);
                pagerAdapter.startAnimation();
                ItemModel itemModel = itemModelList.get(position);
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
        rvImageList.setLayoutManager(layoutManager);
        rvImageList.addItemDecoration(new RecyclerSpacesItemDecoration(0));

        bottomTextAdapter = new ButtomTextAdapter(itemModelList, new AdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                if(pagerItemPosition == position){
                    ItemModel itemModel = itemModelList.get(position);
                    String text = itemModel.getHeading();
                    if(itemModel.isLocked()){
                        speakOut("Please Subscribe");
                    }else{
                        speakOut(text);
                    }
                }
                vpFlower.setCurrentItem(position, true);
            }
        });

        rvImageList.setAdapter(bottomTextAdapter);
    }

    private void setMonthsImages(boolean isPaid) {
        itemModelList = new ArrayList<>();

        ItemModel itemModel;

        itemModel = new ItemModel("JANUARY", "31 Days","Winter",R.drawable.winter_s,R.drawable.winter_i, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("FEBRUARY", "28/29 Days","Winter",R.drawable.winter_s,R.drawable.winter_i, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("MARCH", "30 Days","Spring",R.drawable.spring_s,R.drawable.winter_i, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("APRIL", "31 Days","Spring",R.drawable.spring_s,R.drawable.winter_i, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("MAY", "30 Days","Summer",R.drawable.summer_s,R.drawable.winter_i, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("JUNE", "31 Days","Summer",R.drawable.summer_s,R.drawable.winter_i, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("JULY", "31 Days","Monsoon",R.drawable.monsoon_s,R.drawable.winter_i, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("AUGUST", "31 Days","Monsoon",R.drawable.monsoon_s,R.drawable.winter_i, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("SEPTEMBER", "30 Days","Autumn",R.drawable.autumn_s,R.drawable.winter_i, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("OCTOBER", "31 Days","Autumn",R.drawable.autumn_s,R.drawable.winter_i, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("NOVEMBER", "30 Days","Pre-Winter",R.drawable.prewinter_s,R.drawable.winter_i, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("DECEMBER", "31 Days","Pre-Winter",R.drawable.prewinter_s,R.drawable.winter_i, !isPaid);
        itemModelList.add(itemModel);


    }

}
