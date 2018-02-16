package com.suku.learningkids.features.shape;

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

public class ShapeImageAdapter extends ImageItemPagerAdapter {

    public ShapeImageAdapter(Activity context, List<ItemModel> flatImageModels) {
        super(context, flatImageModels);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        itemView = mLayoutInflater.inflate(R.layout.shape_pager_item, container, false);
        TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        ImageView ivShape = (ImageView) itemView.findViewById(R.id.iv_item_shape);
        ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_item_image);

        final ItemModel flatImageModel = flatImageModels.get(position);
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/kid1.ttf");
        tvTitle.setTypeface(custom_font);
        if(!flatImageModel.isLocked()){
            tvTitle.setText(flatImageModel.getHeading());
            ivImage.setImageResource(flatImageModel.getImage());
            ivShape.setImageResource(flatImageModel.getImageIcon());
            ivShape.setVisibility(View.VISIBLE);
            container.addView(itemView);
        }else{
            ivShape.setVisibility(View.GONE);
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
}
