package com.suku.learningkids.features.flatimages;

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
import com.suku.learningkids.features.alphabet.AlphabetModel;

import java.util.List;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class FlatImagePagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;;
    private List<FlatImageModel> flatImageModels;
    private View itemView;

    public FlatImagePagerAdapter(Context context, List<FlatImageModel> flatImageModels){
        this.context = context;
        this.flatImageModels = flatImageModels;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return flatImageModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        itemView = mLayoutInflater.inflate(R.layout.flatimage_pager_item, container, false);
        TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_item_image);

        FlatImageModel flatImageModel = flatImageModels.get(position);

        tvTitle.setText(flatImageModel.getText());
        ivImage.setImageResource(flatImageModel.getImage());
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }



    public void startAnimation(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_item_image);

                Animation animation1 = AnimationUtils.loadAnimation(context,R.anim.translate_anim_from_left);
                tvTitle.startAnimation(animation1);

                Animation animation2 = AnimationUtils.loadAnimation(context,R.anim.translate_anim_from_bottom);
                ivImage.startAnimation(animation2);
            }
        });
    }
}