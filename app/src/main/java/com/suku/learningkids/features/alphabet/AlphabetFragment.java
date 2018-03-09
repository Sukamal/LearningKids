package com.suku.learningkids.features.alphabet;

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
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.models.ItemModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class AlphabetFragment extends BaseFragment {

    @BindView(R.id.vp_alphabet_vp)
    ViewPager vpAlphabetVp;
    @BindView(R.id.rv_alphabet_list)
    RecyclerView rvAlphabetList;
    private AlphabetPagerAdapter pagerAdapter;
    private AlphabetListAdapter listAdapter;
    private ArrayList<ItemModel> itemModelList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alphabet,container,false);
        ButterKnife.bind(this,view);
        checkVersion(view);
        return view;
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
//        addTypeList.add(AddManager.AddType.GOOGLE_INTERSTITIAL);
//        addTypeList.add(AddManager.AddType.GOOGLE_BANNER);
//        addTypeList.add(AddManager.AddType.STARTAPP_BANNER);
    }

    private void initFreeVersion(View view) {
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);
        initCommonItems();
    }

    private void initPaidVersion(View view) {
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
        initCommonItems();
    }

    private void initCommonItems(){
        setAlphabets();
        initPager();
        initAlphabetRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();
        initTextToSpeach();

//        applyAnimation();
    }



    private void setAlphabets(){
        itemModelList = new ArrayList<>();

        ItemModel alphabetModel;

        alphabetModel = new ItemModel("A","a","APPLE",R.drawable.apple,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("B","b","BALL",R.drawable.ball,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("C","c","CAT",R.drawable.cat,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("D","d","DOG",R.drawable.dog,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("E","e","ELEPHANT",R.drawable.elephant,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("F","f","FISH",R.drawable.fish,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("G","g","GOAT",R.drawable.goat,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("H","h","HORSE",R.drawable.horse,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("I","i","ICE CREAM",R.drawable.ice_cream,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("J","j","JUICE",R.drawable.juice,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("K","k","KITE",R.drawable.kite,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("L","l","LION",R.drawable.lion,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("M","m","MONKEY",R.drawable.monkey,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("N","n","NEST",R.drawable.nest,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("O","o","ORANGE",R.drawable.orange,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("P","p","PARROT",R.drawable.parrot,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("Q","q","QUEEN",R.drawable.queen,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("R","r","RABBIT",R.drawable.rabbit,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("S","s","SHIP",R.drawable.ship,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("T","t","TIGER",R.drawable.tiger,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("U","u","UMBRELLA",R.drawable.umbrella,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("V","v","VAN",R.drawable.van,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("W","w","WATCH",R.drawable.watch,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("X","x","XYLOPHONE",R.drawable.xylophone,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("Y","y","YAK",R.drawable.yak,false);
        itemModelList.add(alphabetModel);

        alphabetModel = new ItemModel("Z","z","ZEBRA",R.drawable.zebra,false);
        itemModelList.add(alphabetModel);
    }

    private void initPager(){
        pagerAdapter = new AlphabetPagerAdapter(getContext(),itemModelList);
        vpAlphabetVp.setAdapter(pagerAdapter);
        vpAlphabetVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerItemPosition = position;
                rvAlphabetList.scrollToPosition(position);
                pagerAdapter.startAnimation();
                ItemModel alphabetModel = itemModelList.get(position);
                String text = alphabetModel.getHeading() + " for " + alphabetModel.getSubheading2();
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
        rvAlphabetList.setLayoutManager(layoutManager);
        rvAlphabetList.addItemDecoration(new RecyclerSpacesItemDecoration(0));

        listAdapter = new AlphabetListAdapter(itemModelList, new AdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                if(pagerItemPosition == position){
                    ItemModel alphabetModel = itemModelList.get(position);
                    String text = alphabetModel.getHeading() + " for " + alphabetModel.getSubheading2();
                    speakOut(text);
                }
                vpAlphabetVp.setCurrentItem(position,true);
            }
        });

        rvAlphabetList.setAdapter(listAdapter);
    }

}
