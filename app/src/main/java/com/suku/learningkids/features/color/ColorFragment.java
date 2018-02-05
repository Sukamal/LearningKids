package com.suku.learningkids.features.color;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suku.learningkids.R;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.alphabet.AlphabetListAdapter;
import com.suku.learningkids.features.alphabet.AlphabetModel;
import com.suku.learningkids.features.alphabet.AlphabetPagerAdapter;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.features.numbers.NumberPagerAdapter;

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
    private ArrayList<AlphabetModel> alphabetModels;
    private TextToSpeech textToSpeech;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color,container,false);
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

        alphabetModel = new AlphabetModel("","","RED",R.color.color_red);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","GREEN",R.color.color_green);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","BLACK",R.color.color_black);
        alphabetModel.setTextColor(R.color.color_white);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","BLUE",R.color.color_blue);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","WHITE",R.color.color_white);
        alphabetModel.setTextColor(R.color.color_black);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","GRAY",R.color.color_grey);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","BROWN",R.color.color_brown);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","ORANGE",R.color.color_orange);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","YELLOW",R.color.color_yellow);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","VIOLET",R.color.color_violet);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","PINK",R.color.color_pink);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","PURPLE",R.color.color_purple);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","CREAM",R.color.color_cream);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","GOLDEN",R.color.color_golden);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","SILVER",R.color.color_silver);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","LIGHT BLUE",R.color.color_light_blue);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","SKY BLUE",R.color.color_sky_blue);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","IRIS BLUE",R.color.color_iris_blue);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","ROYAL BLUE",R.color.color_royal_blue);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","NAVY BLUE",R.color.color_navy_blue);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","CHOCOLATE",R.color.color_chocolate);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","OLIVE GREEN",R.color.color_oliv_green);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("","","MAGENTA",R.color.color_magenta);
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
                pagerAdapter.startAnimation();
                AlphabetModel alphabetModel = alphabetModels.get(position);
                String text = alphabetModel.getWord() + " color";
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
                vpColor.setCurrentItem(position,true);
            }
        });

        rvColorList.setAdapter(listAdapter);
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


//    private void applyAnimation(){
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.zom_in);
//                tvAlphabetCaps.startAnimation(animation);
//                tvAlphabetSmall.startAnimation(animation);
//
//                Animation animation1 = AnimationUtils.loadAnimation(getActivity(),R.anim.translate_anim_from_left);
//                tvWord.startAnimation(animation1);
//
//                Animation animation2 = AnimationUtils.loadAnimation(getActivity(),R.anim.translate_anim_from_right);
//                ivImage.startAnimation(animation2);            }
//        });
//
//
//    }
}
