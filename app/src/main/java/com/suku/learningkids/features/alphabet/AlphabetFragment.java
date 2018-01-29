package com.suku.learningkids.features.alphabet;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.features.BaseFragment;

import java.util.ArrayList;

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

    private AlphabetPagerAdapter pagerAdapter;
    ArrayList<AlphabetModel> alphabetModels;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alphabet,container,false);
        ButterKnife.bind(this,view);
        setAlphabets();
        initPager();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

//        applyAnimation();
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

    private void setAlphabets(){
        alphabetModels = new ArrayList<>();

        AlphabetModel alphabetModel;

        alphabetModel = new AlphabetModel("A","a","APPLE",R.drawable.alphabet);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("B","b","BALL",R.drawable.computer);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("C","c","CAT",R.drawable.summer);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("D","d","DOG",R.drawable.football);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("E","e","ELEPHANT",R.drawable.guitar);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("F","f","FISH",R.drawable.ladybug);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("G","g","GOAT",R.drawable.schedule);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("H","h","HORSE",R.drawable.hamburger);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("I","i","ICE CREAM",R.drawable.pencils);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("J","j","JUICE",R.drawable.shark);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("K","k","KITE",R.drawable.bed);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("L","l","LION",R.drawable.ladybug);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("M","m","MONKEY",R.drawable.cow);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("N","n","NEST",R.drawable.computer);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("O","o","ORANGE",R.drawable.dove);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("P","p","PARROT",R.drawable.teapot);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("Q","q","QUEEN",R.drawable.guitar);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("R","r","RABBIT",R.drawable.ladybug);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("S","s","SUN",R.drawable.alphabet);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("T","t","TIGER",R.drawable.tiger);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("U","u","UMBRELLA",R.drawable.bus);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("V","v","VAN",R.drawable.football);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("W","w","WATCH",R.drawable.guitar);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("X","x","XYLOPHONE",R.drawable.ladybug);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("Y","y","YAK",R.drawable.number);
        alphabetModels.add(alphabetModel);

        alphabetModel = new AlphabetModel("Z","z","ZEBRA",R.drawable.ladybug);
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

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
