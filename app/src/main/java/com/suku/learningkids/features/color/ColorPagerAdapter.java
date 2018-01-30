package com.suku.learningkids.features.color;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
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
import com.suku.learningkids.features.alphabet.AlphabetModel;

import java.util.List;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class ColorPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;;
    private List<AlphabetModel> alphabetModels;
    private View itemView;

    public ColorPagerAdapter(Context context, List<AlphabetModel> alphabetModels){
        this.context = context;
        this.alphabetModels = alphabetModels;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return alphabetModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        itemView = mLayoutInflater.inflate(R.layout.color_image_item, container, false);
        LinearLayout parent = (LinearLayout) itemView.findViewById(R.id.ll_parent_view);
        TextView tvWord = (TextView) itemView.findViewById(R.id.tv_color);
        ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_images);

        AlphabetModel alphabetModel = alphabetModels.get(position);
        tvWord.setText(alphabetModel.getWord());
        tvWord.setTextColor(context.getResources().getColor(alphabetModel.getImage()));
        ivImage.setColorFilter(context.getResources().getColor(alphabetModel.getImage()));
        container.addView(itemView);

        if(alphabetModel.getImage() == R.color.color_white){
            parent.setBackgroundColor(context.getResources().getColor(R.color.color_12));
        }else{
            parent.setBackgroundColor(context.getResources().getColor(R.color.color_white));

        }

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

                TextView tvWord = (TextView) itemView.findViewById(R.id.tv_color);
                ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_images);

                Animation animation1 = AnimationUtils.loadAnimation(context,R.anim.translate_anim_from_left);
                tvWord.startAnimation(animation1);

                Animation animation2 = AnimationUtils.loadAnimation(context,R.anim.translate_anim_from_bottom);
                ivImage.startAnimation(animation2);
            }
        });
    }
}
