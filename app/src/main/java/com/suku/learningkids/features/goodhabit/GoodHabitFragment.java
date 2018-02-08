package com.suku.learningkids.features.goodhabit;

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

public class GoodHabitFragment extends BaseFragment {

    @BindView(R.id.vp_flower)
    ViewPager vpFlower;
    @BindView(R.id.rv_image_list)
    RecyclerView rvImageList;

    private GoodHabitPagerAdapter pagerAdapter;
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
        view.findViewById(R.id.ll_banner).setVisibility(View.VISIBLE);
        setHabitsImages(true);
        initCommonItems();
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);

    }

    private void initPaidVersion(View view) {
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
        setHabitsImages(true);
        initCommonItems();
    }


    @Override
    public void onResume() {
        super.onResume();
        isPaidApp = ((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion();

    }

    private void initCommonItems() {
        initPager();
        initBottomImageRecyclerView();
    }

    private void initPager() {
        pagerAdapter = new GoodHabitPagerAdapter(getContext(), itemModelList);
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



    private void setHabitsImages(boolean isPaid) {
        itemModelList = new ArrayList<>();

        ItemModel itemModel;

        itemModel = new ItemModel("Get up early in the morning",R.drawable.getup_early, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Brush your teeth twice a day",R.drawable.brushteeth, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Take a bath everyday",R.drawable.take_bath, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Comb your hair neatly",R.drawable.combinghair, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Go to school regularly",R.drawable.gotoschool, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Wash your hand before and after meal",R.drawable.washhand, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Eat healthy food",R.drawable.healthy_food, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Drink plenty of water",R.drawable.drinkwater, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Finish your homework in time",R.drawable.finishhomework, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Always put the garbage in dustbin",R.drawable.garbagedustbin, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Always flush the toilet after use",R.drawable.flush_toilet, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Cover your mouth when you sneeze",R.drawable.covermouth, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Cover your mouth when you coughing",R.drawable.coughing_cover, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Cut your nails regularly",R.drawable.cut_nails, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Go to bet on time",R.drawable.gotobed, !isPaid);
        itemModelList.add(itemModel);








    }

}
