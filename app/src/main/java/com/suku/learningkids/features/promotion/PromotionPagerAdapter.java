package com.suku.learningkids.features.promotion;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.adapter.ImageItemPagerAdapter;
import com.suku.learningkids.models.ItemModel;

import java.util.List;

/**
 * Created by SukamalD on 05-02-2018.
 */

public class PromotionPagerAdapter extends ImageItemPagerAdapter {

    public PromotionPagerAdapter(Activity context, List<ItemModel> flatImageModels) {
        super(context, flatImageModels);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        itemView = mLayoutInflater.inflate(R.layout.promotion_pager_item, container, false);
        TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_promotion_heading);
        TextView tvSubTitle = (TextView) itemView.findViewById(R.id.tv_promotion_text);
        ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_backimage);

        final ItemModel flatImageModel = flatImageModels.get(position);
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/kid1.ttf");
        tvTitle.setTypeface(custom_font);
        tvTitle.setText(flatImageModel.getHeading());
        tvSubTitle.setText(flatImageModel.getSubheading());
        ivImage.setImageResource(flatImageModel.getImage());
        container.addView(itemView);
        return itemView;
    }
}
