package com.suku.learningkids.features.shape;

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
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.models.ItemModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class ShapeFragment extends BaseFragment {

    @BindView(R.id.vp_flower)
    ViewPager vpFlower;
    @BindView(R.id.rv_image_list)
    RecyclerView rvImageList;

    private ShapeImageAdapter pagerAdapter;
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
        setShapeImages(false);
        initCommonItems();
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);

    }

    private void initPaidVersion(View view) {
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
        setShapeImages(true);
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

    private void initPager() {
        pagerAdapter = new ShapeImageAdapter(getContext(), itemModelList);
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



    private void setShapeImages(boolean isPaid) {
        itemModelList = new ArrayList<>();

        ItemModel itemModel;

        itemModel = new ItemModel("CIRCLE",R.drawable.cookies, R.drawable.circle,  false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("SQUARE", R.drawable.carrom_board, R.drawable.square, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("TRIANGLE", R.drawable.sandwich, R.drawable.triangle,  false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("DIAMOND", R.drawable.diamond_pic, R.drawable.diamond,false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("OVAL", R.drawable.rugby,R.drawable.oval, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("SPHERE",R.drawable.ball, R.drawable.sphere, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("CRESCENT",R.drawable.cashew,R.drawable.crescent,  !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("RECTANGLE",R.drawable.frame, R.drawable.rectangle, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("CUBE",R.drawable.rubikcube, R.drawable.cube, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("CYLINDER",R.drawable.thermosflusk,R.drawable.cylinder,  !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("CONE",R.drawable.bdcap, R.drawable.cone, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("PYRAMID",R.drawable.pyramidimage,R.drawable.pyramid,  !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("SPIRAL",R.drawable.spring,R.drawable.spiral,  !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("HEXAGON",R.drawable.hexagon_box,R.drawable.hexagon,  !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("STAR",R.drawable.starfuit,R.drawable.star,  !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("HEART",R.drawable.heart_shaped,R.drawable.heart,  !isPaid);
        itemModelList.add(itemModel);


    }

}
