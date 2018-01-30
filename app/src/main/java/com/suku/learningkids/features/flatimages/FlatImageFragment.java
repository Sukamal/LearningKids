package com.suku.learningkids.features.flatimages;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suku.learningkids.R;
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.alphabet.AlphabetModel;
import com.suku.learningkids.features.color.ColorListAdapter;
import com.suku.learningkids.features.color.ColorPagerAdapter;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.util.AppConstant;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class FlatImageFragment extends BaseFragment {

    @BindView(R.id.vp_flower)
    ViewPager vpFlower;

    private FlatImagePagerAdapter pagerAdapter;
    private ArrayList<FlatImageModel> flatImageList;
    private TextToSpeech textToSpeech;
    private int displayCode = -1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flat_images,container,false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        if(bundle.containsKey(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name())){
            displayCode = bundle.getInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name());
        }
        initItems();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

//        applyAnimation();
    }

    private void initItems(){
        getImageSets();
        initTextToSpeach();
        initPager();
    }

    private void getImageSets(){
        if(displayCode == AppConstant.HomeMenu.FLOWER.getEnumValue()){
            setFlowerImages();
        }else if(displayCode == AppConstant.HomeMenu.FRUIT.getEnumValue()){
            setFruitesImages();
        }else if(displayCode == AppConstant.HomeMenu.VEGETABLE.getEnumValue()){
            setVegetableImages();
        }else if(displayCode == AppConstant.HomeMenu.DOMESTICANIMAL.getEnumValue()){
            setDomesticAnimalImages();
        }else if(displayCode == AppConstant.HomeMenu.WILDANIMAL.getEnumValue()){
            setWildAnimalImages();
        }


    }



    private void initPager(){
        pagerAdapter = new FlatImagePagerAdapter(getContext(),flatImageList);
        vpFlower.setAdapter(pagerAdapter);
        vpFlower.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerAdapter.startAnimation();
                FlatImageModel flatImageModel = flatImageList.get(position);
                String text = flatImageModel.getText();
                speakOut(text);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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


    private void setFlowerImages(){
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("MARIGOLD",R.drawable.marigold);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ROSE",R.drawable.rose);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WATERLILY",R.drawable.waterlily);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HIBISCUS",R.drawable.hibiscus);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("IRIS",R.drawable.iris);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DAISY",R.drawable.daisy);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("POPPY",R.drawable.poppy);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MORNING GLORY",R.drawable.morningglory);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LOTUS",R.drawable.lotus);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SUNFLOWER",R.drawable.sunflower);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BOUGAINVILLEA",R.drawable.bougainvillea);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TULIP",R.drawable.tulip);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LILY",R.drawable.lily);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PANSY",R.drawable.pansy);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DAFFODIL",R.drawable.daffodil);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ASTER",R.drawable.aster);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ZINNIA",R.drawable.zinnia);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CANNA",R.drawable.canna);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BLUEBELL",R.drawable.bluebell);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DAHLIA",R.drawable.dahlia);
        flatImageList.add(flatImageModel);


    }

    private void setFruitesImages(){
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("APPLE",R.drawable.apple);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BANANA",R.drawable.banana);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MANGO",R.drawable.mango);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CUSTARD APPLE",R.drawable.custeredapple);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("POMEGRANATE",R.drawable.pomegranate);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("STRAWBERRY",R.drawable.strawberry);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MUSKMELON",R.drawable.maskmelon);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SAPODILLA",R.drawable.sapodilla);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LYCHEE",R.drawable.lychee);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHERRY",R.drawable.cherry);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("APRICOT",R.drawable.apricot);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PEAR",R.drawable.pear);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GUAVA",R.drawable.guava);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ORANGE",R.drawable.orange);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PINEAPPLE",R.drawable.pineapple);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PHYSALIS",R.drawable.physalis);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GRAPES",R.drawable.green_grapes);
        flatImageList.add(flatImageModel);

    }

    private void setVegetableImages(){
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("TOMATO",R.drawable.tomato);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BOTTLE GOURD",R.drawable.bouttlegourd);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("POTATO",R.drawable.potato);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BRINJAL",R.drawable.brinjal);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CARROT",R.drawable.carrot);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TURNIP",R.drawable.turnip);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("OKRA",R.drawable.okra);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PEAS",R.drawable.peas);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("COLOCASIA",R.drawable.colocasia);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TINDA",R.drawable.tinda);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GINGER",R.drawable.ginger);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CUCUMBER",R.drawable.cucumber);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAULIFLOWER",R.drawable.cauliflower);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PUMPKIN",R.drawable.pumpkin);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RIDGE GOURD",R.drawable.ridgegourd);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ONION",R.drawable.onion);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ZINNIA",R.drawable.zinnia);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BITTER GOURD",R.drawable.bittergourd);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CABBAGE",R.drawable.cabbage);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAPSICUM",R.drawable.capsicum);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RADISH",R.drawable.radish);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CORIANDER LEAVES",R.drawable.coriander_leaves);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHILLI",R.drawable.chilli);
        flatImageList.add(flatImageModel);

    }

    private void setDomesticAnimalImages(){
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("COW",R.drawable.cow);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DOG",R.drawable.dog);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("OX",R.drawable.ox);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PARROT",R.drawable.parrot);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAT",R.drawable.cat);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BUFFALO",R.drawable.buffalo);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DONKEY",R.drawable.donkey);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LLAMA",R.drawable.llama);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SHEEP",R.drawable.sheep);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HORSE",R.drawable.horse);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PIG",R.drawable.pig);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GOAT",R.drawable.goat);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("YAK",R.drawable.yak);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAMEL",R.drawable.camel);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WHITE RAT",R.drawable.whiterat);
        flatImageList.add(flatImageModel);

    }

    private void setWildAnimalImages(){
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("LEOPARD",R.drawable.leopard);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BEAR",R.drawable.bear);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FOX",R.drawable.fox);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WOLF",R.drawable.wolf);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ELEPHANT",R.drawable.elephant);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GIRAFFE",R.drawable.giraffe);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GORILA",R.drawable.gorilla);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DEER",R.drawable.deer);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("STAG",R.drawable.stag);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LION",R.drawable.lion);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHIMPANZEE",R.drawable.chimpanzee);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MONKEY",R.drawable.monkey);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHEETAH",R.drawable.cheetah);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TIGER",R.drawable.tiger);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RHINOCEROS",R.drawable.rhino);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("KANGAROO",R.drawable.kangaroo);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PANDA",R.drawable.panda);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("APE",R.drawable.ape);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ZEBRA",R.drawable.zebra);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PANTHER",R.drawable.panther);
        flatImageList.add(flatImageModel);

    }
}
