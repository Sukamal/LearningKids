package com.suku.learningkids.features.flatimages;

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
import com.suku.learningkids.features.BaseFragment;
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
    @BindView(R.id.rv_image_list)
    RecyclerView rvImageList;

    private FlatImagePagerAdapter pagerAdapter;
    private ArrayList<FlatImageModel> flatImageList;
    private ButtomImageAdapter imageAdapter;
    private TextToSpeech textToSpeech;
    private int displayCode = -1;
    private ArrayList<AddManager.AddType> addTypeList;
    private boolean isPaidApp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flat_images, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle.containsKey(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name())) {
            displayCode = bundle.getInt(AppConstant.ExtraTag.FLAT_IMAGE_DISPLAY_CODE.name());
        }
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
        getImageSets(false);
        initCommonItems();
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);

    }

    private void initPaidVersion(View view) {
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
        getImageSets(true);
        initCommonItems();
    }


    @Override
    public void onResume() {
        super.onResume();
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();

    }

    private void initCommonItems() {
        initTextToSpeach();
        initPager();
        initBottomImageRecyclerView();
    }

    private void getImageSets(boolean isPaid) {
        if (displayCode == AppConstant.HomeMenu.FLOWER.getEnumValue()) {
            setFlowerImages(isPaid);
        } else if (displayCode == AppConstant.HomeMenu.FRUIT.getEnumValue()) {
            setFruitesImages(isPaid);
        } else if (displayCode == AppConstant.HomeMenu.VEGETABLE.getEnumValue()) {
            setVegetableImages(isPaid);
        } else if (displayCode == AppConstant.HomeMenu.DOMESTICANIMAL.getEnumValue()) {
            setDomesticAnimalImages(isPaid);
        } else if (displayCode == AppConstant.HomeMenu.WILDANIMAL.getEnumValue()) {
            setWildAnimalImages(isPaid);
        } else if (displayCode == AppConstant.HomeMenu.BIRD.getEnumValue()) {
            setBirds(isPaid);
        } else if (displayCode == AppConstant.HomeMenu.INSECTS.getEnumValue()) {
            setInsectsReptiles(isPaid);
        } else if (displayCode == AppConstant.HomeMenu.SEACREATURES.getEnumValue()) {
            setSeaCreatures(isPaid);
        } else if (displayCode == AppConstant.HomeMenu.FOOD.getEnumValue()) {
            setFoodAndBevarage(isPaid);
        } else if (displayCode == AppConstant.HomeMenu.VEHICLES.getEnumValue()) {
            setVehicles(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.KITCHEN.getEnumValue()) {
            setKitchen(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.BATHROOM.getEnumValue()) {
            setBathroom(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.BEDROOM.getEnumValue()) {
            setBedroom(isPaid);
        }

        else if (displayCode == AppConstant.HomeMenu.SPORTSEQUIPEMENT.getEnumValue()) {
            setSportsEquipment(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.MUSICALINSTRUMENT.getEnumValue()) {
            setMusicalInstrument(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.STATIONARY.getEnumValue()) {
            setStationaryItem(isPaid);
        }


        else if (displayCode == AppConstant.HomeMenu.ACTIONS.getEnumValue()) {
            setActions(isPaid);
        }

    }


    private void initPager() {
        pagerAdapter = new FlatImagePagerAdapter(getContext(), flatImageList);
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


    private void initBottomImageRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvImageList.setLayoutManager(layoutManager);
        rvImageList.addItemDecoration(new RecyclerSpacesItemDecoration(0));

        imageAdapter = new ButtomImageAdapter(flatImageList, new ButtomImageAdapter.ClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                vpFlower.setCurrentItem(position, true);
            }
        });

        rvImageList.setAdapter(imageAdapter);
    }


    private void initTextToSpeach() {
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


    private void setFlowerImages(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("MARIGOLD", R.drawable.marigold, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ROSE", R.drawable.rose, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WATERLILY", R.drawable.waterlily, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HIBISCUS", R.drawable.hibiscus, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("IRIS", R.drawable.iris, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DAISY", R.drawable.daisy, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("POPPY", R.drawable.poppy, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MORNING GLORY", R.drawable.morningglory, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LOTUS", R.drawable.lotus, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SUNFLOWER", R.drawable.sunflower, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BOUGAINVILLEA", R.drawable.bougainvillea, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TULIP", R.drawable.tulip, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LILY", R.drawable.lily, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PANSY", R.drawable.pansy, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DAFFODIL", R.drawable.daffodil, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ASTER", R.drawable.aster, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ZINNIA", R.drawable.zinnia, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CANNA", R.drawable.canna, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BLUEBELL", R.drawable.bluebell, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DAHLIA", R.drawable.dahlia, !isPaid);
        flatImageList.add(flatImageModel);


    }

    private void setFruitesImages(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("APPLE", R.drawable.apple, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BANANA", R.drawable.banana, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MANGO", R.drawable.mango, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CUSTARD APPLE", R.drawable.custeredapple, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("POMEGRANATE", R.drawable.pomegranate, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("STRAWBERRY", R.drawable.strawberry, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MUSKMELON", R.drawable.maskmelon, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SAPODILLA", R.drawable.sapodilla, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LYCHEE", R.drawable.lychee, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHERRY", R.drawable.cherry, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("APRICOT", R.drawable.apricot, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PEAR", R.drawable.pear, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GUAVA", R.drawable.guava, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ORANGE", R.drawable.orange, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PINEAPPLE", R.drawable.pineapple, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PHYSALIS", R.drawable.physalis, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GRAPES", R.drawable.green_grapes, !isPaid);
        flatImageList.add(flatImageModel);

    }

    private void setVegetableImages(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("TOMATO", R.drawable.tomato, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BOTTLE GOURD", R.drawable.bouttlegourd, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("POTATO", R.drawable.potato, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("BRINJAL", R.drawable.brinjal, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CARROT", R.drawable.carrot, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TURNIP", R.drawable.turnip, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("OKRA", R.drawable.okra, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PEAS", R.drawable.peas, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("COLOCASIA", R.drawable.colocasia, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TINDA", R.drawable.tinda, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GINGER", R.drawable.ginger, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CUCUMBER", R.drawable.cucumber, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAULIFLOWER", R.drawable.cauliflower, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PUMPKIN", R.drawable.pumpkin, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RIDGE GOURD", R.drawable.ridgegourd, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ONION", R.drawable.onion, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BITTER GOURD", R.drawable.bittergourd, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CABBAGE", R.drawable.cabbage, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAPSICUM", R.drawable.capsicum, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RADISH", R.drawable.radish, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CORIANDER LEAVES", R.drawable.coriander_leaves, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHILLI", R.drawable.chilli, !isPaid);
        flatImageList.add(flatImageModel);

    }

    private void setDomesticAnimalImages(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("COW", R.drawable.cow, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DOG", R.drawable.dog, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("OX", R.drawable.ox, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("PARROT", R.drawable.parrot, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAT", R.drawable.cat, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BUFFALO", R.drawable.buffalo, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DONKEY", R.drawable.donkey, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LLAMA", R.drawable.llama, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SHEEP", R.drawable.sheep, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HORSE", R.drawable.horse, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PIG", R.drawable.pig, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GOAT", R.drawable.goat, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("YAK", R.drawable.yak, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAMEL", R.drawable.camel, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WHITE RAT", R.drawable.whiterat, !isPaid);
        flatImageList.add(flatImageModel);
    }

    private void setWildAnimalImages(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("LEOPARD", R.drawable.leopard, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BEAR", R.drawable.bear, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FOX", R.drawable.fox, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("WOLF", R.drawable.wolf, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ELEPHANT", R.drawable.elephant, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GIRAFFE", R.drawable.giraffe, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GORILA", R.drawable.gorilla, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DEER", R.drawable.deer, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("STAG", R.drawable.stag, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LION", R.drawable.lion, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHIMPANZEE", R.drawable.chimpanzee, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MONKEY", R.drawable.monkey, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHEETAH", R.drawable.cheetah, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TIGER", R.drawable.tiger, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RHINOCEROS", R.drawable.rhino, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("KANGAROO", R.drawable.kangaroo, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PANDA", R.drawable.panda, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("APE", R.drawable.ape, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ZEBRA", R.drawable.zebra, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PANTHER", R.drawable.panther, !isPaid);
        flatImageList.add(flatImageModel);

    }

    private void setBirds(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("MYNA", R.drawable.myna, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PIGEON", R.drawable.pigeon, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SWALLOW", R.drawable.swallow, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("PENGUIN", R.drawable.penguin, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HERON", R.drawable.heron, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SWAN", R.drawable.swan, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PARTRIDGE", R.drawable.partridge, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("QUAIL", R.drawable.quail, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SPARROW", R.drawable.sparrow, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MAGPIE", R.drawable.magpies, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("OSTRICH", R.drawable.ostrich, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PEACOCK", R.drawable.peacock, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("OWL", R.drawable.owl, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CROW", R.drawable.crow, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HOOPOE", R.drawable.hoopoe, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PARROT", R.drawable.parrot, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CUCKOO", R.drawable.cuckoo, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SEAGULL", R.drawable.seagull, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("EAGLE", R.drawable.eagle, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MACAW", R.drawable.macaw, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WOODPECKER", R.drawable.woodpecker, !isPaid);
        flatImageList.add(flatImageModel);

    }

    private void setInsectsReptiles(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("ANT", R.drawable.ant, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BEDBUG", R.drawable.brdbug, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("COCKROACH", R.drawable.cockroach, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("HONEY BEE", R.drawable.honeybee, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WASP", R.drawable.wasp, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CENTIPEDE", R.drawable.centipede, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HOUSEFLY", R.drawable.housefly, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LADYBIRD", R.drawable.ladybird, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SPIDER", R.drawable.spider, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MANTIS", R.drawable.mantis, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SCORPION", R.drawable.scorpion, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BUTTERFLY", R.drawable.butterfly, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MOSQUITO", R.drawable.mosquito, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GREEN IGUANA", R.drawable.greeniguana, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CROCODILE", R.drawable.crocodile, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHAMELEON", R.drawable.chameleon, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ALLIGATOR", R.drawable.alligator, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("COBRA", R.drawable.cobra, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LIZARD", R.drawable.lizard, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PYTHON", R.drawable.python, !isPaid);
        flatImageList.add(flatImageModel);

    }

    private void setSeaCreatures(boolean isPaid) {
        flatImageList = new ArrayList<>();
        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("OCTOPUS", R.drawable.octopus, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SEAL", R.drawable.seal, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("LOBSTER", R.drawable.lobster, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TORTOISE", R.drawable.tortoise, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TURTLE", R.drawable.turtle, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WHALE", R.drawable.whale, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TOAD", R.drawable.toad, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FISH", R.drawable.fish, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FROG", R.drawable.frog, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SHARK", R.drawable.shark, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SEA HORSE", R.drawable.seahorse, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CRAB", R.drawable.crab, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SEA CUCUMBER", R.drawable.seacucumber, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ANACONDA", R.drawable.anaconda, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("STAR FISH", R.drawable.starfish, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DOLPHIN", R.drawable.dolphin, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("JELLY FISH", R.drawable.jellyfish, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SNAIL", R.drawable.snail, !isPaid);
        flatImageList.add(flatImageModel);

    }

    private void setFoodAndBevarage(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("BURGER", R.drawable.burger, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PIZZA", R.drawable.pizza, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GERLIC BREAD", R.drawable.gerlicbread, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("OMELETTE", R.drawable.omelette, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ROASTED CHICKEN", R.drawable.roastedchiken, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FRIED FISH", R.drawable.friedfish, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("NOODLES", R.drawable.noodles, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SANDWICH", R.drawable.sandwich, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RICE", R.drawable.rice, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TOMATO SAUCE", R.drawable.tomato_sauce, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SOUP", R.drawable.soupe, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FRUIT JAM", R.drawable.fruit_jam, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("COOKIES", R.drawable.cookies, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FRENCH FRIES", R.drawable.frenchfry, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DONUT", R.drawable.donut, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BREAD", R.drawable.bread, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAKE", R.drawable.cake, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHEESE", R.drawable.cheese, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MILK", R.drawable.milk, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SMOOTHIE", R.drawable.smoothies, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SOFT DRINK", R.drawable.soft_drink, !isPaid);
        flatImageList.add(flatImageModel);
    }

    private void setVehicles(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("PARAMBULATOR", R.drawable.parambulator, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TRICYCLE", R.drawable.trycycle, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BICYCLE", R.drawable.bicycle, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("SCOOTER", R.drawable.scooter, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("AEROPLANE", R.drawable.aeroplane, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("JEEP", R.drawable.jeep, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MOTORCYCLE", R.drawable.motorcycle, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HELICOPTER", R.drawable.helicopter, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("METRO", R.drawable.metro, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TRUCK", R.drawable.truck, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SHIP", R.drawable.ship, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAR", R.drawable.car, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BOAT", R.drawable.boat, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TRAIN", R.drawable.train, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BUS", R.drawable.bus, !isPaid);
        flatImageList.add(flatImageModel);

    }

    private void setKitchen(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("BOWL", R.drawable.bowl, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHIMNEY", R.drawable.chimney, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GAS BURNER", R.drawable.gasburner, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("LPG CYLINDER", R.drawable.lpgcylinder, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RICE COOKER", R.drawable.ricecooker, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MICROWAVE OVEN", R.drawable.microwave, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TEAPOT", R.drawable.teapot, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CUP", R.drawable.cup, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SAUCER", R.drawable.saucer, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FRYING PAN", R.drawable.fryingpan, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PRESSURE COOKER", R.drawable.pressurecooker, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FLASK", R.drawable.thermosflusk, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CASSEROLE", R.drawable.casserole, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WOK", R.drawable.wok, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MIXER GRINDER", R.drawable.mixergrinder, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MUG", R.drawable.mug, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("JUG", R.drawable.jug, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("EGG BEATER", R.drawable.eggbeater, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ROLLING PIN", R.drawable.rollingpin, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("REFRIGERATOR", R.drawable.refrigerator, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GLASS", R.drawable.glass, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BUTTER KNIFE", R.drawable.butter_knife, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FORK", R.drawable.fork, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SPOON", R.drawable.spoon, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHOPPING BOARD", R.drawable.choppingboard, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SIEVE", R.drawable.sieve, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TRAIN", R.drawable.train, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SINK", R.drawable.sink, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SPATULA", R.drawable.spatula, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("KNIFE", R.drawable.knife, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DISHWASHER", R.drawable.dishwashers, !isPaid);
        flatImageList.add(flatImageModel);


    }

    private void setBathroom(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("TOOTHPASTE", R.drawable.toothpaste, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TOOTHBRUSH", R.drawable.toothbrush, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SOAP", R.drawable.soap, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("TOWEL", R.drawable.towel, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BUCKET", R.drawable.bucket, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MUG", R.drawable.bathmug, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SHAMPOO", R.drawable.shampoo, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MIRROR", R.drawable.mirror, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WASH BASIN", R.drawable.washbasin, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("COMMODE", R.drawable.commode, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WATER TAP", R.drawable.water_tap, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BATHTUB", R.drawable.bathtub, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SHOWER", R.drawable.shower, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HAND SHOWER", R.drawable.handshower, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WATER GEYSER", R.drawable.water_geyser, !isPaid);
        flatImageList.add(flatImageModel);

    }

    private void setBedroom(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("BED", R.drawable.bed, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BED SHEET", R.drawable.bedsheet, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PILLOW", R.drawable.pillow, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("BOLSTER", R.drawable.bolster, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BLANKET", R.drawable.blanket, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MATTRESS", R.drawable.mattress, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DRAWERS", R.drawable.drawer, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WARDROBE", R.drawable.wardrob, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BEDSIDE TABLE", R.drawable.bedsidetable, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ALARM CLOCK", R.drawable.alarmclock, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CRADLE", R.drawable.cradle, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SLIPPER", R.drawable.slippers, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DRESSING TABLE", R.drawable.dressingtable, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HAIR BRUSH", R.drawable.hairbrush, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("COMB", R.drawable.comb, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TABLE LAMP", R.drawable.table_lamp, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("AIR CONDITIONAl", R.drawable.airconditional, !isPaid);
        flatImageList.add(flatImageModel);



    }

    private void setActions(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("STANDING", R.drawable.standing, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CLAPPING", R.drawable.clapping, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SMILING", R.drawable.smiling, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("LAUGHING", R.drawable.laughing, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("EATING", R.drawable.eating, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CRAWLING", R.drawable.crawling, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CRYING", R.drawable.crying, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SHOUTING", R.drawable.shouting, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("READING", R.drawable.reading, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RUNNING", R.drawable.running, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WALKING", R.drawable.walking, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SINGING", R.drawable.shinging, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("EXERCISING", R.drawable.exercise, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("KICKING", R.drawable.kicking, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SITTING", R.drawable.sitting, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WRITING", R.drawable.writing, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SWIMMING", R.drawable.swiming, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("YAWNING", R.drawable.yawaning, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DRINKING", R.drawable.drinking, !isPaid);
        flatImageList.add(flatImageModel);

    }

    private void setMusicalInstrument(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("ACCORDION", R.drawable.accordion, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BAGPIPE", R.drawable.bagpipe, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BANJO", R.drawable.banjo, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("CLARINET", R.drawable.clarinet, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CONGA", R.drawable.conga, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DHOL", R.drawable.dhol, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DRUM", R.drawable.drums, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FLUTE", R.drawable.flute, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GUITAR", R.drawable.guiter, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HARMONIUM", R.drawable.harmonium, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HARP", R.drawable.harp, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MANDOLIN", R.drawable.mandolin, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("MOUTH ORGAN", R.drawable.mouthorgan, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PIANO", R.drawable.piano, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SARANGI", R.drawable.sarangi, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SAXOPHONE", R.drawable.saxophone, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SYNTHESIZER", R.drawable.synthesizer, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TABLA", R.drawable.tabla, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TAMBOURINE", R.drawable.tambourine, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TANPURA", R.drawable.tanpura, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TRUMPET", R.drawable.trumpet, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("VEENA", R.drawable.veena, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("VIOLIN", R.drawable.violin, !isPaid);
        flatImageList.add(flatImageModel);


    }

    private void setSportsEquipment(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("CRICKET BAT", R.drawable.cricket_bat, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CRICKET BALL", R.drawable.cricket_ball, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WICKET", R.drawable.wicket, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("BASKETBALL", R.drawable.asketball, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BADMINTON RACKET", R.drawable.badminton, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SHUTTLECOCK", R.drawable.shuttlecock, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BASEBALL BAT", R.drawable.baseball_bat, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BASEBALL BALL", R.drawable.baseball_ball, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("BOWLING", R.drawable.bowling, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FOOTBALL", R.drawable.football, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CAROM BOARD", R.drawable.carrom_board, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CHESS BOARD", R.drawable.chessboard, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("RUGBY", R.drawable.rugby, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TENNIS RACKET", R.drawable.tennisracket, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TENNIS BALL", R.drawable.tennis_ball, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HOKEY STICK", R.drawable.hockeystick, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TABLE TENNIS RACKET AND BALL", R.drawable.ttracketball, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("POOL TABLE", R.drawable.pulltable, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GOLF KIT", R.drawable.golfkit, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GOLF BALL", R.drawable.golfball, !isPaid);
        flatImageList.add(flatImageModel);


    }

    private void setStationaryItem(boolean isPaid) {
        flatImageList = new ArrayList<>();

        FlatImageModel flatImageModel;

        flatImageModel = new FlatImageModel("BALL PEN", R.drawable.ballpen, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CALCULATOR", R.drawable.calculator, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CLIPBOARD", R.drawable.clipboard, false);
        flatImageList.add(flatImageModel);


        flatImageModel = new FlatImageModel("CLUCHPENCIL", R.drawable.cluchpencil, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("COLOUR PENCILS", R.drawable.colorpencil, false);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("COLOUR PLATE", R.drawable.colorplate, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("DRAWING COMPASS", R.drawable.compass, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CORRECTION FLUID", R.drawable.correctionfluid, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CRAYON", R.drawable.crayon, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("CUTTER", R.drawable.cutter, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("ERASER", R.drawable.eraser, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FILES", R.drawable.files, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("FOUNTAIN PEN", R.drawable.fountainpen, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GEOMETRY BOX", R.drawable.geobox, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("GLUE STICK", R.drawable.glue_stick, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("HIGHLIGHT PEN", R.drawable.highlight, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("INK POT", R.drawable.inkpot, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PAINT BRUSH", R.drawable.paint_brush, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("PENCIL", R.drawable.pencil, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SCALE", R.drawable.scale, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SCHOOL BAG", R.drawable.schoolbag, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SHARPENER", R.drawable.sharpener, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SKETCH PEN", R.drawable.sketchpen, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("SLATE", R.drawable.slate, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("TAPE DISPENSER", R.drawable.tapedispenser, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WATER COLOUR", R.drawable.watercolor, !isPaid);
        flatImageList.add(flatImageModel);

        flatImageModel = new FlatImageModel("WHITEBOARD MARKER", R.drawable.whiteboardmarker, !isPaid);
        flatImageList.add(flatImageModel);

    }


}
