package com.suku.learningkids.features.safety;

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
import com.suku.learningkids.features.season.SeasonsPagerAdapter;
import com.suku.learningkids.models.ItemModel;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class SafetyFragment extends BaseFragment {

    @BindView(R.id.vp_flower)
    ViewPager vpFlower;
    @BindView(R.id.rv_image_list)
    RecyclerView rvImageList;

    private SafetyPagerAdapter pagerAdapter;
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
        setSeasonImages(false);
        initCommonItems();
        setAddType();
        displayAddBasedOnAppType(addTypeList, view);

    }

    private void initPaidVersion(View view) {
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
        setSeasonImages(true);
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
        pagerAdapter = new SafetyPagerAdapter(getContext(), itemModelList);
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


    private void setSeasonImages(boolean isPaid) {
        itemModelList = new ArrayList<>();

        ItemModel itemModel;

        itemModel = new ItemModel("Do not cross the street walking between parked vehicles","dont",R.drawable.dont_walk_car, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not run after a moving bus","dont",R.drawable.movingbus, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Always cross a busy road at the Zebra crossing","do",R.drawable.zebracrossing, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not play with electric plug point","dont",R.drawable.electric_plug, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not touch any hot surface","dont",R.drawable.hot_surface, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Watch TV from a safe distance","do",R.drawable.watchtv, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not play with gas burner","dont",R.drawable.gasburner_dont, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not touch electric iron","dont",R.drawable.touch_iron, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Always use seat belt while traveling with car","do",R.drawable.wear_seatbelt, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not play on the road","dont",R.drawable.playing_onroad, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not play with sharp items like knife, scissor","dont",R.drawable.sharp_item, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Keep distance while bursting fire crackers","do",R.drawable.crackers_distance, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not come close to fire","dont",R.drawable.stay_from_fire, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not accept anything from strangers","dont",R.drawable.strenger, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Always use float while swimming","do",R.drawable.swimming_tered, !isPaid);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Do not use phone while walking","dont",R.drawable.walk_mobile, !isPaid);
        itemModelList.add(itemModel);

    }

}
