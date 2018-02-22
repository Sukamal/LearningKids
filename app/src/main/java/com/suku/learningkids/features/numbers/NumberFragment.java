package com.suku.learningkids.features.numbers;

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
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.alphabet.AlphabetListAdapter;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.models.ItemModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class NumberFragment extends BaseFragment {

    @BindView(R.id.vp_number)
    ViewPager vpNumber;
    @BindView(R.id.rv_number_list)
    RecyclerView rvNnumberList;

    private ArrayList<ItemModel> alphabetModels;
    private NumberPagerAdapter pagerAdapter;
    private AlphabetListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number,container,false);
        ButterKnife.bind(this,view);
        checkVersion(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();
        initTextToSpeach();
//        applyAnimation();
    }

    private void checkVersion(View view) {
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();
        if (isPaidApp) {
            initPaidVersion(view);
        } else {
            initFreeVersion(view);
        }
    }

    private void setAddType() {
        addTypeList = new ArrayList<>();
        addTypeList.add(AddManager.AddType.GOOGLE_INTERSTITIAL);
//        addTypeList.add(AddManager.AddType.GOOGLE_BANNER);
        addTypeList.add(AddManager.AddType.STARTAPP_BANNER);
    }

    private void initFreeVersion(View view) {
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);
        setAlphabets(false);
        initCommonItems();


    }

    private void initPaidVersion(View view) {
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
        setAlphabets(true);
        initCommonItems();
    }

    private void initCommonItems(){
        initPager();
        initAlphabetRecyclerView();

    }



    private void setAlphabets(boolean isPaid){
        alphabetModels = new ArrayList<>();
        ItemModel alphabetModel;
        for(int i=1; i <= 10;i++){
            alphabetModel = new ItemModel(String.valueOf(i),"","APPLE",R.drawable.apple,false);
            alphabetModels.add(alphabetModel);
        }

        for(int i=11; i <= 100;i++){
            alphabetModel = new ItemModel(String.valueOf(i),"","APPLE",R.drawable.apple,!isPaid);
            alphabetModels.add(alphabetModel);
        }
    }

    private void initPager(){
        pagerAdapter = new NumberPagerAdapter(getActivity(),alphabetModels);
        vpNumber.setAdapter(pagerAdapter);
        vpNumber.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerItemPosition = position;
                rvNnumberList.scrollToPosition(position);
//                pagerAdapter.startAnimation();
                ItemModel alphabetModel = alphabetModels.get(position);
                String text = alphabetModel.getHeading();
                if(alphabetModel.isLocked()){
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

    private void initAlphabetRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNnumberList.setLayoutManager(layoutManager);
        rvNnumberList.addItemDecoration(new RecyclerSpacesItemDecoration(0));

        listAdapter = new AlphabetListAdapter(alphabetModels, new AdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                if(pagerItemPosition == position){
                    ItemModel alphabetModel = alphabetModels.get(position);
                    String text = alphabetModel.getHeading();
                    if(alphabetModel.isLocked()){
                        speakOut("Please Subscribe");
                    }else{
                        speakOut(text);
                    }
                }
                vpNumber.setCurrentItem(position,true);

            }
        });

        rvNnumberList.setAdapter(listAdapter);
    }



}
