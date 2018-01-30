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
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class AlphabetFragment extends BaseFragment {

//    @BindView(R.id.tv_alphabet_caps)
//    TextView tvAlphabetCaps;
//    @BindView(R.id.tv_alphabet_small)
//    TextView tvAlphabetSmall;
//    @BindView(R.id.tv_word)
//    TextView tvWord;
//    @BindView(R.id.iv_image)
//    ImageView ivImage;

    @BindView(R.id.vp_alphabet_vp)
    ViewPager vpAlphabetVp;
    @BindView(R.id.rv_alphabet_list)
    RecyclerView rvAlphabetList;

    private AlphabetPagerAdapter pagerAdapter;
    private AlphabetListAdapter listAdapter;
    private ArrayList<AlphabetModel> alphabetModels;
    private TextToSpeech textToSpeech;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alphabet,container,false);
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

        alphabetModel = new AlphabetModel("A","a","APPLE",R.drawable.apple);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("B","b","BALL",R.drawable.ball);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("C","c","CAT",R.drawable.cat);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("D","d","DOG",R.drawable.dog);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("E","e","ELEPHANT",R.drawable.elephant);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("F","f","FISH",R.drawable.fish);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("G","g","GOAT",R.drawable.goat);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("H","h","HORSE",R.drawable.horse);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("I","i","ICE CREAM",R.drawable.ice_cream);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("J","j","JUICE",R.drawable.juice);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("K","k","KITE",R.drawable.kite);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("L","l","LION",R.drawable.lion);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("M","m","MONKEY",R.drawable.monkey);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("N","n","NEST",R.drawable.nest);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("O","o","ORANGE",R.drawable.orange);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("P","p","PARROT",R.drawable.parrot);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("Q","q","QUEEN",R.drawable.queen);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("R","r","RABBIT",R.drawable.rabbit);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("S","s","SHIP",R.drawable.ship);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("T","t","TIGER",R.drawable.tiger);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("U","u","UMBRELLA",R.drawable.umbrella);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("V","v","VAN",R.drawable.van);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("W","w","WATCH",R.drawable.watch);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("X","x","XYLOPHONE",R.drawable.xylophone);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("Y","y","YAK",R.drawable.yak);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("Z","z","ZEBRA",R.drawable.zebra);
        alphabetModels.add(alphabetModel);
    }

    private void initPager(){
        pagerAdapter = new AlphabetPagerAdapter(getContext(),alphabetModels);
        vpAlphabetVp.setAdapter(pagerAdapter);
        vpAlphabetVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerAdapter.startAnimation();
                AlphabetModel alphabetModel = alphabetModels.get(position);
                String text = alphabetModel.getAlphabetCaps() + " for " + alphabetModel.getWord();
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

        listAdapter = new AlphabetListAdapter(alphabetModels, new AlphabetListAdapter.ClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                vpAlphabetVp.setCurrentItem(position,true);
            }
        });

        rvAlphabetList.setAdapter(listAdapter);
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