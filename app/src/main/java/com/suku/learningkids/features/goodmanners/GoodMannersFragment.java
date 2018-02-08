package com.suku.learningkids.features.goodmanners;

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
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.goodhabit.GoodHabitPagerAdapter;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.models.ItemModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class GoodMannersFragment extends BaseFragment {

    @BindView(R.id.vp_flower)
    ViewPager vpFlower;
    @BindView(R.id.rv_image_list)
    RecyclerView rvImageList;

    private GoodHabitPagerAdapter pagerAdapter;
    private ArrayList<ItemModel> itemModelList;
    private ButtomImageAdapter imageAdapter;

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
        setMannersImages(true);
        initCommonItems();
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);

    }

    private void initPaidVersion(View view) {
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
        setMannersImages(true);
        initCommonItems();
    }


    @Override
    public void onResume() {
        super.onResume();
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();

    }

    private void initCommonItems() {
        initPager();
        initBottomImageRecyclerView();
    }

    private void initPager() {
        pagerAdapter = new GoodHabitPagerAdapter(getContext(), itemModelList);
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

        imageAdapter = new ButtomImageAdapter(itemModelList, new AdapterItemClickListener() {
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

        rvImageList.setAdapter(imageAdapter);
    }



    private void setMannersImages(boolean isPaid) {
        itemModelList = new ArrayList<>();

        ItemModel itemModel;

        itemModel = new ItemModel("Say Namaste to elder people",R.drawable.saynamaste, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Greed the elders properly",R.drawable.greed_politely, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Behave people very politely ",R.drawable.politeeee, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Stand in a queue to get your turn",R.drawable.standinque, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Respect your elders",R.drawable.respectelder, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Respect your teacher",R.drawable.respect_teacher, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Always help other people",R.drawable.helppeople, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Raise your hand to say something in class",R.drawable.raisehand, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Say sorry when you commit a mistake",R.drawable.drinkwater, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Say Goodbye when you leaving someone",R.drawable.saygoodbye, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Say Thanks to the person who helps you",R.drawable.thankyou, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Say Excuse me when you disturb someone",R.drawable.excuseme, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Always share things with others",R.drawable.sharetoyes, false);
        itemModelList.add(itemModel);

    }

}
