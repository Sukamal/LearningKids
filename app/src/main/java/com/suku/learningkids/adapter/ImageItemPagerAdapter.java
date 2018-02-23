package com.suku.learningkids.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.Toast;

import com.suku.learningkids.R;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.home.HomeActivity;
import com.suku.learningkids.models.ItemModel;
import com.suku.learningkids.util.AppConstant;
import com.suku.learningkids.util.AppDialog;

import java.util.List;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class ImageItemPagerAdapter extends PagerAdapter {

    protected Activity context;
    protected LayoutInflater mLayoutInflater;;
    protected List<ItemModel> flatImageModels;
    protected View itemView;
    protected AdapterItemClickListener clickListener;

    public void setItemClickListner(AdapterItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public ImageItemPagerAdapter(Activity context, List<ItemModel> flatImageModels){
        this.context = context;
        this.flatImageModels = flatImageModels;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(flatImageModels != null)
            return flatImageModels.size();
        else
            return 0;
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

        final ItemModel flatImageModel = flatImageModels.get(position);
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/kid1.ttf");
        tvTitle.setTypeface(custom_font);
        if(!flatImageModel.isLocked()){
            tvTitle.setText(flatImageModel.getHeading());
            ivImage.setImageResource(flatImageModel.getImage());
            container.addView(itemView);
        }else{
            tvTitle.setText("Please Subscribe");
            ivImage.setImageResource(R.drawable.subscribe);
            container.addView(itemView);
        }

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flatImageModel.isLocked()){
                    showPurchaseDialog();
                }
            }
        });


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

    protected void showPurchaseDialog(){
        AppDialog appDialog = new AppDialog();
        appDialog.showPurchaseDialog(context,context.getString(R.string.subscribe), context.getString(R.string.subscription_details));

    }
}
