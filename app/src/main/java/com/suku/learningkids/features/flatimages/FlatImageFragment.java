package com.suku.learningkids.features.flatimages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suku.learningkids.R;
import com.suku.learningkids.adapter.ButtomImageAdapter;
import com.suku.learningkids.adapter.ImageItemPagerAdapter;
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.models.ItemModel;
import com.suku.learningkids.util.AppConstant;

import java.util.ArrayList;

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
    private ImageItemPagerAdapter pagerAdapter;
    private ArrayList<ItemModel> imageItemList;
    private ButtomImageAdapter imageAdapter;
    private int displayCode = -1;



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
//        addTypeList.add(AddManager.AddType.STARTAPP_BANNER);
        addTypeList.add(AddManager.AddType.GOOGLE_BANNER);
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
        view.findViewById(R.id.ll_banner).setVisibility(View.VISIBLE);
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
        initTextToSpeach();
    }

    private void initCommonItems() {
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
        }else if (displayCode == AppConstant.HomeMenu.SPORTSEQUIPEMENT.getEnumValue()) {
            setSportsEquipment(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.MUSICALINSTRUMENT.getEnumValue()) {
            setMusicalInstrument(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.STATIONARY.getEnumValue()) {
            setStationaryItem(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.OURHELPERS.getEnumValue()) {
            setHelpersItem(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.ACTIONS.getEnumValue()) {
            setActions(isPaid);
        }else if (displayCode == AppConstant.HomeMenu.COMPUTERPARTS.getEnumValue()) {
            setComputerItem(isPaid);
        }

    }



    private void initPager() {
        pagerAdapter = new ImageItemPagerAdapter(getActivity(), imageItemList);
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
                ItemModel itemModel = imageItemList.get(position);
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
        imageAdapter = new ButtomImageAdapter(imageItemList, new AdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, int position, Object selectedItem) {
                if(pagerItemPosition == position){
                    ItemModel itemModel = imageItemList.get(position);
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



    private void setFlowerImages(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("MARIGOLD", R.drawable.marigold, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ROSE", R.drawable.rose, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WATERLILY", R.drawable.waterlily, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HIBISCUS", R.drawable.hibiscus, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("IRIS", R.drawable.iris, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DAISY", R.drawable.daisy, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("POPPY", R.drawable.poppy, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MORNING GLORY", R.drawable.morningglory, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("LOTUS", R.drawable.lotus, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SUNFLOWER", R.drawable.sunflower, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BOUGAINVILLEA", R.drawable.bougainvillea, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TULIP", R.drawable.tulip, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("LILY", R.drawable.lily, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PANSY", R.drawable.pansy, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DAFFODIL", R.drawable.daffodil, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ASTER", R.drawable.aster, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ZINNIA", R.drawable.zinnia, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CANNA", R.drawable.canna, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BLUEBELL", R.drawable.bluebell, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DAHLIA", R.drawable.dahlia, !isPaid);
        imageItemList.add(flatImageModel);


    }

    private void setFruitesImages(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("APPLE", R.drawable.apple, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BANANA", R.drawable.banana, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MANGO", R.drawable.mango, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CUSTARD APPLE", R.drawable.custeredapple, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("POMEGRANATE", R.drawable.pomegranate, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("STRAWBERRY", R.drawable.strawberry, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MUSKMELON", R.drawable.maskmelon, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SAPODILLA", R.drawable.sapodilla, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("LYCHEE", R.drawable.lychee, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHERRY", R.drawable.cherry, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("APRICOT", R.drawable.apricot, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PEAR", R.drawable.pear, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GUAVA", R.drawable.guava, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ORANGE", R.drawable.orange, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PINEAPPLE", R.drawable.pineapple, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PHYSALIS", R.drawable.physalis, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GRAPES", R.drawable.green_grapes, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("STARFRUIT", R.drawable.starfuit, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CASHEW", R.drawable.cashew, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setVegetableImages(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("TOMATO", R.drawable.tomato, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BOTTLE GOURD", R.drawable.bouttlegourd, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("POTATO", R.drawable.potato, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("BRINJAL", R.drawable.brinjal, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CARROT", R.drawable.carrot, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TURNIP", R.drawable.turnip, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("OKRA", R.drawable.okra, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PEAS", R.drawable.peas, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("COLOCASIA", R.drawable.colocasia, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TINDA", R.drawable.tinda, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GINGER", R.drawable.ginger, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CUCUMBER", R.drawable.cucumber, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CAULIFLOWER", R.drawable.cauliflower, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PUMPKIN", R.drawable.pumpkin, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("RIDGE GOURD", R.drawable.ridgegourd, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ONION", R.drawable.onion, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BITTER GOURD", R.drawable.bittergourd, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CABBAGE", R.drawable.cabbage, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CAPSICUM", R.drawable.capsicum, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("RADISH", R.drawable.radish, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CORIANDER LEAVES", R.drawable.coriander_leaves, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHILLI", R.drawable.chilli, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setDomesticAnimalImages(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("COW", R.drawable.cow, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DOG", R.drawable.dog, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("OX", R.drawable.ox, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("PARROT", R.drawable.parrot, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CAT", R.drawable.cat, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BUFFALO", R.drawable.buffalo, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DONKEY", R.drawable.donkey, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("LLAMA", R.drawable.llama, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SHEEP", R.drawable.sheep, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HORSE", R.drawable.horse, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PIG", R.drawable.pig, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GOAT", R.drawable.goat, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("YAK", R.drawable.yak, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CAMEL", R.drawable.camel, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WHITE RAT", R.drawable.whiterat, !isPaid);
        imageItemList.add(flatImageModel);
    }

    private void setWildAnimalImages(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("LEOPARD", R.drawable.leopard, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BEAR", R.drawable.bear, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FOX", R.drawable.fox, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("WOLF", R.drawable.wolf, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ELEPHANT", R.drawable.elephant, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GIRAFFE", R.drawable.giraffe, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GORILA", R.drawable.gorilla, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DEER", R.drawable.deer, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("STAG", R.drawable.stag, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("LION", R.drawable.lion, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHIMPANZEE", R.drawable.chimpanzee, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MONKEY", R.drawable.monkey, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHEETAH", R.drawable.cheetah, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TIGER", R.drawable.tiger, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("RHINOCEROS", R.drawable.rhino, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("KANGAROO", R.drawable.kangaroo, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PANDA", R.drawable.panda, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("APE", R.drawable.ape, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ZEBRA", R.drawable.zebra, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PANTHER", R.drawable.panther, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setBirds(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("MYNA", R.drawable.myna, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PIGEON", R.drawable.pigeon, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SWALLOW", R.drawable.swallow, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("PENGUIN", R.drawable.penguin, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HERON", R.drawable.heron, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SWAN", R.drawable.swan, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PARTRIDGE", R.drawable.partridge, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("QUAIL", R.drawable.quail, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SPARROW", R.drawable.sparrow, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MAGPIE", R.drawable.magpies, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("OSTRICH", R.drawable.ostrich, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PEACOCK", R.drawable.peacock, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("OWL", R.drawable.owl, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CROW", R.drawable.crow, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HOOPOE", R.drawable.hoopoe, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PARROT", R.drawable.parrot, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CUCKOO", R.drawable.cuckoo, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SEAGULL", R.drawable.seagull, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("EAGLE", R.drawable.eagle, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MACAW", R.drawable.macaw, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WOODPECKER", R.drawable.woodpecker, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setInsectsReptiles(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("ANT", R.drawable.ant, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BEDBUG", R.drawable.brdbug, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("COCKROACH", R.drawable.cockroach, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("HONEY BEE", R.drawable.honeybee, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WASP", R.drawable.wasp, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CENTIPEDE", R.drawable.centipede, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HOUSEFLY", R.drawable.housefly, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("LADYBIRD", R.drawable.ladybird, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SPIDER", R.drawable.spider, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MANTIS", R.drawable.mantis, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SCORPION", R.drawable.scorpion, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BUTTERFLY", R.drawable.butterfly, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MOSQUITO", R.drawable.mosquito, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GREEN IGUANA", R.drawable.greeniguana, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CROCODILE", R.drawable.crocodile, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHAMELEON", R.drawable.chameleon, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ALLIGATOR", R.drawable.alligator, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("COBRA", R.drawable.cobra, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("LIZARD", R.drawable.lizard, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PYTHON", R.drawable.python, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setSeaCreatures(boolean isPaid) {
        imageItemList = new ArrayList<>();
        ItemModel flatImageModel;

        flatImageModel = new ItemModel("OCTOPUS", R.drawable.octopus, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SEAL", R.drawable.seal, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("LOBSTER", R.drawable.lobster, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TORTOISE", R.drawable.tortoise, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TURTLE", R.drawable.turtle, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WHALE", R.drawable.whale, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TOAD", R.drawable.toad, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FISH", R.drawable.fish, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FROG", R.drawable.frog, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SHARK", R.drawable.shark, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SEA HORSE", R.drawable.seahorse, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CRAB", R.drawable.crab, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SEA CUCUMBER", R.drawable.seacucumber, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ANACONDA", R.drawable.anaconda, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("STAR FISH", R.drawable.starfish, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DOLPHIN", R.drawable.dolphin, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("JELLY FISH", R.drawable.jellyfish, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SNAIL", R.drawable.snail, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setFoodAndBevarage(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("BURGER", R.drawable.burger, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PIZZA", R.drawable.pizza, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ICE CREAM", R.drawable.ice_cream, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("OMELETTE", R.drawable.omelette, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ROASTED CHICKEN", R.drawable.roastedchiken, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FRIED FISH", R.drawable.friedfish, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("NOODLES", R.drawable.noodles, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SANDWICH", R.drawable.sandwich, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("RICE", R.drawable.rice, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TOMATO SAUCE", R.drawable.tomato_sauce, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SOUP", R.drawable.soupe, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FRUIT JAM", R.drawable.fruit_jam, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("COOKIES", R.drawable.cookies, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FRENCH FRIES", R.drawable.frenchfry, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DONUT", R.drawable.donut, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BREAD", R.drawable.bread, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CAKE", R.drawable.cake, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHEESE", R.drawable.cheese, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MILK", R.drawable.milk, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SMOOTHIE", R.drawable.smoothies, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GARLIC BREAD", R.drawable.gerlicbread, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SOFT DRINK", R.drawable.soft_drink, !isPaid);
        imageItemList.add(flatImageModel);
    }

    private void setVehicles(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("TRICYCLE", R.drawable.trycycle, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BICYCLE", R.drawable.bicycle, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("SCOOTER", R.drawable.scooter, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("AEROPLANE", R.drawable.aeroplane, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("JEEP", R.drawable.jeep, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("AUTO RICKSHAW", R.drawable.autorik, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MOTORCYCLE", R.drawable.motorcycle, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HELICOPTER", R.drawable.helicopter, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("METRO", R.drawable.metro, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TRUCK", R.drawable.truck, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SHIP", R.drawable.ship, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CAR", R.drawable.car, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BOAT", R.drawable.boat, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TRAIN", R.drawable.train, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BUS", R.drawable.bus, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setKitchen(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("BOWL", R.drawable.bowl, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHIMNEY", R.drawable.chimney, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GAS BURNER", R.drawable.gasburner, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("LPG CYLINDER", R.drawable.lpgcylinder, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("RICE COOKER", R.drawable.ricecooker, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MICROWAVE OVEN", R.drawable.microwave, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TEAPOT", R.drawable.teapot, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CUP", R.drawable.cup, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SAUCER", R.drawable.saucer, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FRYING PAN", R.drawable.fryingpan, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PRESSURE COOKER", R.drawable.pressurecooker, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FLASK", R.drawable.thermosflusk, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CASSEROLE", R.drawable.casserole, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WOK", R.drawable.wok, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MIXER GRINDER", R.drawable.mixergrinder, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MUG", R.drawable.mug, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("JUG", R.drawable.jug, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("EGG BEATER", R.drawable.eggbeater, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ROLLING PIN", R.drawable.rollingpin, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("REFRIGERATOR", R.drawable.refrigerator, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GLASS", R.drawable.glass, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BUTTER KNIFE", R.drawable.butter_knife, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FORK", R.drawable.fork, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SPOON", R.drawable.spoon, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHOPPING BOARD", R.drawable.choppingboard, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SIEVE", R.drawable.sieve, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TRAIN", R.drawable.train, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SINK", R.drawable.sink, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SPATULA", R.drawable.spatula, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("KNIFE", R.drawable.knife, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DISHWASHER", R.drawable.dishwashers, !isPaid);
        imageItemList.add(flatImageModel);


    }

    private void setBathroom(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("TOOTHPASTE", R.drawable.toothpaste, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TOOTHBRUSH", R.drawable.toothbrush, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SOAP", R.drawable.soap, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("TOWEL", R.drawable.towel, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BUCKET", R.drawable.bucket, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MUG", R.drawable.bathmug, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SHAMPOO", R.drawable.shampoo, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MIRROR", R.drawable.mirror, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WASH BASIN", R.drawable.washbasin, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("COMMODE", R.drawable.commode, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WATER TAP", R.drawable.water_tap, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BATHTUB", R.drawable.bathtub, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SHOWER", R.drawable.shower, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HAND SHOWER", R.drawable.handshower, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WATER GEYSER", R.drawable.water_geyser, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setBedroom(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("BED", R.drawable.bed, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BED SHEET", R.drawable.bedsheet, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PILLOW", R.drawable.pillow, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("BOLSTER", R.drawable.bolster, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BLANKET", R.drawable.blanket, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MATTRESS", R.drawable.mattress, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DRAWERS", R.drawable.drawer, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WARDROBE", R.drawable.wardrob, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BEDSIDE TABLE", R.drawable.bedsidetable, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ALARM CLOCK", R.drawable.alarmclock, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CRADLE", R.drawable.cradle, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SLIPPER", R.drawable.slippers, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DRESSING TABLE", R.drawable.dressingtable, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HAIR BRUSH", R.drawable.hairbrush, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("COMB", R.drawable.comb, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TABLE LAMP", R.drawable.table_lamp, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("AIR CONDITIONAl", R.drawable.airconditional, !isPaid);
        imageItemList.add(flatImageModel);



    }

    private void setActions(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("STANDING", R.drawable.standing, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CLAPPING", R.drawable.clapping, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SMILING", R.drawable.smiling, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("LAUGHING", R.drawable.laughing, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("EATING", R.drawable.eating, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CRAWLING", R.drawable.crawling, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CRYING", R.drawable.crying, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SHOUTING", R.drawable.shouting, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("READING", R.drawable.reading, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("RUNNING", R.drawable.running, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WALKING", R.drawable.walking, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SINGING", R.drawable.shinging, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("EXERCISING", R.drawable.exercise, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("KICKING", R.drawable.kicking, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SITTING", R.drawable.sitting, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WRITING", R.drawable.writing, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SWIMMING", R.drawable.swiming, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("YAWNING", R.drawable.yawaning, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DRINKING", R.drawable.drinking, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setMusicalInstrument(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("ACCORDION", R.drawable.accordion, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BAGPIPE", R.drawable.bagpipe, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BANJO", R.drawable.banjo, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("CLARINET", R.drawable.clarinet, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CONGA", R.drawable.conga, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DHOL", R.drawable.dhol, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DRUM", R.drawable.drums, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FLUTE", R.drawable.flute, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GUITAR", R.drawable.guiter, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HARMONIUM", R.drawable.harmonium, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HARP", R.drawable.harp, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MANDOLIN", R.drawable.mandolin, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MOUTH ORGAN", R.drawable.mouthorgan, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PIANO", R.drawable.piano, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SARANGI", R.drawable.sarangi, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SAXOPHONE", R.drawable.saxophone, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SYNTHESIZER", R.drawable.synthesizer, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TABLA", R.drawable.tabla, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TAMBOURINE", R.drawable.tambourine, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TANPURA", R.drawable.tanpura, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TRUMPET", R.drawable.trumpet, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("VEENA", R.drawable.veena, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("VIOLIN", R.drawable.violin, !isPaid);
        imageItemList.add(flatImageModel);


    }

    private void setSportsEquipment(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("CRICKET BAT", R.drawable.cricket_bat, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CRICKET BALL", R.drawable.cricket_ball, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WICKET", R.drawable.wicket, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("BASKETBALL", R.drawable.asketball, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BADMINTON RACKET", R.drawable.badminton, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SHUTTLECOCK", R.drawable.shuttlecock, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BASEBALL BAT", R.drawable.baseball_bat, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BASEBALL BALL", R.drawable.baseball_ball, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BOWLING", R.drawable.bowling, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FOOTBALL", R.drawable.football, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CAROM BOARD", R.drawable.carrom_board, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHESS BOARD", R.drawable.chessboard, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("RUGBY", R.drawable.rugby, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TENNIS RACKET", R.drawable.tennisracket, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TENNIS BALL", R.drawable.tennis_ball, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HOKEY STICK", R.drawable.hockeystick, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TABLE TENNIS RACKET AND BALL", R.drawable.ttracketball, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("POOL TABLE", R.drawable.pulltable, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GOLF KIT", R.drawable.golfkit, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GOLF BALL", R.drawable.golfball, !isPaid);
        imageItemList.add(flatImageModel);


    }

    private void setStationaryItem(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("BALL PEN", R.drawable.ballpen, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CALCULATOR", R.drawable.calculator, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CLIPBOARD", R.drawable.clipboard, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("CLUCHPENCIL", R.drawable.cluchpencil, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("COLOUR PENCILS", R.drawable.colorpencil, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("COLOUR PLATE", R.drawable.colorplate, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DRAWING COMPASS", R.drawable.compass, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CORRECTION FLUID", R.drawable.correctionfluid, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CRAYON", R.drawable.crayon, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CUTTER", R.drawable.cutter, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ERASER", R.drawable.eraser, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FILES", R.drawable.files, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("FOUNTAIN PEN", R.drawable.fountainpen, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GEOMETRY BOX", R.drawable.geobox, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GLUE STICK", R.drawable.glue_stick, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HIGHLIGHT PEN", R.drawable.highlight, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("INK POT", R.drawable.inkpot, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PAINT BRUSH", R.drawable.paint_brush, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PENCIL", R.drawable.pencil, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SCALE", R.drawable.scale, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SCHOOL BAG", R.drawable.schoolbag, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SHARPENER", R.drawable.sharpener, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SKETCH PEN", R.drawable.sketchpen, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SLATE", R.drawable.slate, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TAPE DISPENSER", R.drawable.tapedispenser, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WATER COLOUR", R.drawable.watercolor, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WHITEBOARD MARKER", R.drawable.whiteboardmarker, !isPaid);
        imageItemList.add(flatImageModel);

    }

    private void setHelpersItem(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("NURSE", R.drawable.nurse, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DOCTOR", R.drawable.doctor, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TEACHER", R.drawable.teacher, false);
        imageItemList.add(flatImageModel);


        flatImageModel = new ItemModel("POLICE", R.drawable.police, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SOLDIER", R.drawable.soldier, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("TICKET CHECKER", R.drawable.ticket_checker, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("POST MAN", R.drawable.postman, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("ELECTRICIAN", R.drawable.electrician, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("BARBER", R.drawable.barber, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PORTER", R.drawable.porter, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CHEF", R.drawable.chef, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DRIVER", R.drawable.driver, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("CARPENTER", R.drawable.carpenter, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("DELIVERY BOY", R.drawable.deliveryboy, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WASHER MAN", R.drawable.washerman, !isPaid);
        imageItemList.add(flatImageModel);


    }

    private void setComputerItem(boolean isPaid) {
        imageItemList = new ArrayList<>();

        ItemModel flatImageModel;

        flatImageModel = new ItemModel("DESKTOP", R.drawable.desktop, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("LAPTOP", R.drawable.laptop, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("C.P.U", R.drawable.cpu, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MONITOR", R.drawable.monitor, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("C.D", R.drawable.compact_disc, false);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("U.P.S", R.drawable.ups, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MOTHERBOARD", R.drawable.motherboard, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("KEYBOARD", R.drawable.keyboard, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("MOUSE", R.drawable.mouse, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SCANNER", R.drawable.scanner, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PRINTER", R.drawable.laserprinter, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WEB CAMERA", R.drawable.web_camera, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("SPEAKER", R.drawable.speakers, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("HARD DISK", R.drawable.hard_disc, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("RANDOM ACCESS MEMMORY", R.drawable.ram, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("GRAPHICS CARD", R.drawable.graficscard, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("WIRELESS MODEM", R.drawable.wireless_modem, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PEN DRIVE", R.drawable.pendrive, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("C.D DRIVE", R.drawable.dvddrive, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("PROCESSOR", R.drawable.processor, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("S.M.P.S", R.drawable.smps, !isPaid);
        imageItemList.add(flatImageModel);

        flatImageModel = new ItemModel("C.P.U COOLING FAN", R.drawable.cpu_heatsink_fan, !isPaid);
        imageItemList.add(flatImageModel);



    }


}
