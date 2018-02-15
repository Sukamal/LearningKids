package com.suku.learningkids.features.promotion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.suku.learningkids.R;
import com.suku.learningkids.adapter.ButtomImageAdapter;
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.BaseFragment;
import com.suku.learningkids.features.home.RecyclerSpacesItemDecoration;
import com.suku.learningkids.features.season.SeasonsPagerAdapter;
import com.suku.learningkids.models.ItemModel;
import com.suku.learningkids.util.AppConstant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class PromotionalFragment extends BaseFragment {

    @BindView(R.id.vp_promotion)
    ViewPager vpPromotion;
    @BindView(R.id.ll_indicator)
    LinearLayout llIndicator;
    @BindView(R.id.btn_submit)
    Button btnSubmit;


    private PromotionPagerAdapter pagerAdapter;
    private ArrayList<ItemModel> itemModelList;
    private ButtomImageAdapter imageAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promossion, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
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
        setSeasonImages(true);
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
        initIndicator();
        setIndicator(0);
        initClickListner();
    }

    private void initClickListner(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((KidApplication) getActivity().getApplication()).mAppPreference.isPaidVersion()){
                    setApplicationMode(AppConstant.AppType.FREE);
                }else{
                    setApplicationMode(AppConstant.AppType.PAID);
                }
            }
        });
    }

    private void initPager() {
        pagerAdapter = new PromotionPagerAdapter(getContext(), itemModelList);
        vpPromotion.setAdapter(pagerAdapter);
        vpPromotion.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



    private void setSeasonImages(boolean isPaid) {
        itemModelList = new ArrayList<>();

        ItemModel itemModel;

        itemModel = new ItemModel("Promote Code and Earn Money","Promote Code and Earn Money, Promote Code and Earn Money, Promote Code and Earn Money,Promote Code and Earn Money",R.drawable.summer_s, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Promote Code and Get Reward", "Promote Code and Get Reward,Promote Code and Get Reward,Promote Code and Get Reward,Promote Code and Get Reward",R.drawable.monsoon_s, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Promote Code and Remove ADD","Promote Code and Get Reward,Promote Code and Get Reward, Promote Code and Get Reward, Promote Code and Get Reward", R.drawable.autumn_s, false);
        itemModelList.add(itemModel);

        itemModel = new ItemModel("Promote Code and Make it Paid APP","Promote Code and Make it Paid APP, Promote Code and Make it Paid APP, Promote Code and Make it Paid APP", R.drawable.prewinter_s, false);
        itemModelList.add(itemModel);

    }

    private void initIndicator(){
        for(int i=0; i < itemModelList.size(); i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20,20);
            layoutParams.setMargins(5,0,5,0);
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.circle_dot);
            imageView.setLayoutParams(layoutParams);
            llIndicator.addView(imageView);
        }
    }

    private void setIndicator(int position){
        for(int i=0; i < itemModelList.size(); i++){
            ((ImageView)llIndicator.getChildAt(i)).setImageResource(R.drawable.circle_dot);
            if(i==position)
                ((ImageView)llIndicator.getChildAt(i)).setImageResource(R.drawable.circle_dot_fill);
        }
    }

}
