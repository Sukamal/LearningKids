package com.suku.learningkids.features.safety;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.adapter.ImageItemPagerAdapter;
import com.suku.learningkids.models.ItemModel;

import java.util.List;

/**
 * Created by SukamalD on 05-02-2018.
 */

public class SafetyPagerAdapter extends ImageItemPagerAdapter {

    public SafetyPagerAdapter(Activity context, List<ItemModel> flatImageModels) {
        super(context, flatImageModels);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        itemView = mLayoutInflater.inflate(R.layout.safety_pager_item, container, false);
        TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_item_image);
        LinearLayout llDoDont = (LinearLayout) itemView.findViewById(R.id.ll_do_dont);
        ImageView ivImageDont = (ImageView) itemView.findViewById(R.id.iv_item_dont);



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

        if(flatImageModel.getSubheading() != null && flatImageModel.getSubheading().equalsIgnoreCase("dont")){
            llDoDont.setBackgroundColor(context.getResources().getColor(R.color.color_red));
            ivImageDont.setImageResource(R.drawable.dont);
            tvTitle.setTextColor(context.getResources().getColor(R.color.color2));
        }else{
            llDoDont.setBackgroundColor(context.getResources().getColor(R.color.color_green));
            tvTitle.setTextColor(context.getResources().getColor(R.color.color1));
            ivImageDont.setImageResource(R.drawable.does);
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
}
