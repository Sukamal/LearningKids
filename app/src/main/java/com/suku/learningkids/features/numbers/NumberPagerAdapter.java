package com.suku.learningkids.features.numbers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.adapter.NumberImageAdapter;
import com.suku.learningkids.models.ItemModel;
import com.suku.learningkids.util.AppDialog;

import java.util.List;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class NumberPagerAdapter extends PagerAdapter {

    private Activity context;
    private LayoutInflater mLayoutInflater;;
    private List<ItemModel> alphabetModels;
    private View itemView;

    public NumberPagerAdapter(Activity context, List<ItemModel> alphabetModels){
        this.context = context;
        this.alphabetModels = alphabetModels;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(alphabetModels != null)
            return alphabetModels.size();
        else
            return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        itemView = mLayoutInflater.inflate(R.layout.number_pager_item, container, false);
        TextView tvAlphabetCaps = (TextView) itemView.findViewById(R.id.tv_number);
        TextView tvWord = (TextView) itemView.findViewById(R.id.tv_word);
        ImageView ivNumberImage = (ImageView) itemView.findViewById(R.id.iv_number_image);
        RecyclerView recyclerView = (RecyclerView)  itemView.findViewById(R.id.rv_number_image);

        ItemModel alphabetModel = alphabetModels.get(position);

//        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/kid1.ttf");
//        tvAlphabetCaps.setTypeface(custom_font);
        tvAlphabetCaps.setTextColor(context.getResources().getColor(R.color.color1));

        if(!alphabetModel.isLocked()){
            tvAlphabetCaps.setText(alphabetModel.getHeading());
            ivNumberImage.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            initImages(recyclerView,Integer.valueOf(alphabetModel.getHeading()),alphabetModel.getImage());
        }else{
            tvAlphabetCaps.setText("Please Subscribe");
            tvAlphabetCaps.setTextSize(30);
            ivNumberImage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            ivNumberImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppDialog appDialog = new AppDialog();
                    appDialog.showPurchaseDialog(context,"Purchase", "To access please purchase");
                }
            });
        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    private void applyAnimation(final View view1,final View view2,final View view3,final View view4){
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                Animation animation = AnimationUtils.loadAnimation(context,R.anim.zom_in);
                view1.startAnimation(animation);
                view2.startAnimation(animation);

                Animation animation1 = AnimationUtils.loadAnimation(context,R.anim.translate_anim_from_left);
                view3.startAnimation(animation1);

                Animation animation2 = AnimationUtils.loadAnimation(context,R.anim.translate_anim_from_bottom);
                view4.startAnimation(animation2);

            }
        });
    }

    public void startAnimation(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                TextView tvAlphabetCaps = (TextView) itemView.findViewById(R.id.tv_alphabet_caps);
                TextView tvAlphabetSmall = (TextView) itemView.findViewById(R.id.tv_alphabet_small);
                TextView tvWord = (TextView) itemView.findViewById(R.id.tv_word);
                ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_image);

                Animation animation = AnimationUtils.loadAnimation(context,R.anim.zom_in);
                tvAlphabetCaps.startAnimation(animation);
                tvAlphabetSmall.startAnimation(animation);

                Animation animation1 = AnimationUtils.loadAnimation(context,R.anim.translate_anim_from_left);
                tvWord.startAnimation(animation1);

                Animation animation2 = AnimationUtils.loadAnimation(context,R.anim.translate_anim_from_bottom);
                ivImage.startAnimation(animation2);
            }
        });
    }

    private void initImages(RecyclerView recyclerView,int count,int image){

        int rowCount = 1;
        if(count <6){
            rowCount = count;
        }else {
            rowCount = 5;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,rowCount);
        recyclerView.setLayoutManager(gridLayoutManager);

        NumberImageAdapter numberImageAdapter = new NumberImageAdapter(count,image);
        recyclerView.setAdapter(numberImageAdapter);

    }


}
