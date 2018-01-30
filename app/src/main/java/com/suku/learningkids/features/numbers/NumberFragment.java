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
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.alphabet.AlphabetListAdapter;
import com.suku.learningkids.features.alphabet.AlphabetModel;
import com.suku.learningkids.features.alphabet.AlphabetPagerAdapter;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;

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

    private ArrayList<AlphabetModel> alphabetModels;
    private NumberPagerAdapter pagerAdapter;
    private AlphabetListAdapter listAdapter;
    private TextToSpeech textToSpeech;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number,container,false);
        ButterKnife.bind(this,view);
        initItems();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

//        applyAnimation();
    }

    private void initItems(){
        setAlphabets();
        initTextToSpeach();
        initPager();
        initAlphabetRecyclerView();

    }



    private void setAlphabets(){
        alphabetModels = new ArrayList<>();
        AlphabetModel alphabetModel;
        for(int i=1; i <= 20;i++){
            alphabetModel = new AlphabetModel(String.valueOf(i),"","APPLE",R.drawable.apple);
            alphabetModels.add(alphabetModel);
        }
    }

    private void initPager(){
        pagerAdapter = new NumberPagerAdapter(getContext(),alphabetModels);
        vpNumber.setAdapter(pagerAdapter);
        vpNumber.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                pagerAdapter.startAnimation();
                AlphabetModel alphabetModel = alphabetModels.get(position);
                String text = alphabetModel.getAlphabetCaps();
                speakOut(text);

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

        listAdapter = new AlphabetListAdapter(alphabetModels, new AlphabetListAdapter.ClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                vpNumber.setCurrentItem(position,true);
            }
        });

        rvNnumberList.setAdapter(listAdapter);
    }

    private void initTextToSpeach(){
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                }
            }
        });
    }

    private void speakOut(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


}
