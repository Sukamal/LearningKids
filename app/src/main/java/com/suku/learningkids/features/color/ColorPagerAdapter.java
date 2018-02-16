package com.suku.learningkids.features.color;

import android.app.Activity;
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
import com.suku.learningkids.models.ItemModel;
import com.suku.learningkids.util.AppDialog;

import java.util.List;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class ColorPagerAdapter extends PagerAdapter {

    private Activity context;
    private LayoutInflater mLayoutInflater;;
    private List<ItemModel> alphabetModels;
    private View itemView;

    public ColorPagerAdapter(Activity context, List<ItemModel> alphabetModels){
        this.context = context;
        this.alphabetModels = alphabetModels;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(alphabetModels != null)
            return alphabetModels.size();
        return 0;
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

        ItemModel alphabetModel = alphabetModels.get(position);
        if(alphabetModel.getImage() == R.color.color_white){
            parent.setBackgroundColor(context.getResources().getColor(R.color.color12));
        }else{
            parent.setBackgroundColor(context.getResources().getColor(R.color.color_white));

        }

        if(!alphabetModel.isLocked()){
            tvWord.setText(alphabetModel.getSubheading2());
            tvWord.setTextColor(context.getResources().getColor(alphabetModel.getImage()));
            ivImage.setColorFilter(context.getResources().getColor(alphabetModel.getImage()));
            container.addView(itemView);
        }else{
            tvWord.setText("Please Subscribe");
            tvWord.setTextColor(context.getResources().getColor(R.color.color_red));
            ivImage.setImageResource(R.drawable.subscribe);
            container.addView(itemView);
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppDialog appDialog = new AppDialog();
                    appDialog.showPurchaseDialog(context,"Purchase", "To access please purchase");
                }
            });
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
