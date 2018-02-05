package com.suku.learningkids.features.alphabet;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suku.learningkids.R;

import java.util.List;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class AlphabetPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;;
    private List<AlphabetModel> alphabetModels;
    private View itemView;

    public AlphabetPagerAdapter(Context context,List<AlphabetModel> alphabetModels){
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

        itemView = mLayoutInflater.inflate(R.layout.alphabet_pager_item, container, false);
        TextView tvAlphabetCaps = (TextView) itemView.findViewById(R.id.tv_alphabet_caps);
        TextView tvAlphabetSmall = (TextView) itemView.findViewById(R.id.tv_alphabet_small);
        TextView tvWord = (TextView) itemView.findViewById(R.id.tv_word);
        ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_image);

        AlphabetModel alphabetModel = alphabetModels.get(position);
        tvAlphabetCaps.setText(alphabetModel.getAlphabetCaps());
        tvAlphabetSmall.setText(alphabetModel.getAlphabetSmall());
        tvWord.setText(alphabetModel.getWord());
        ivImage.setImageResource(alphabetModel.getImage());
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
}
