package com.suku.learningkids.features.time;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.adapter.ImageItemPagerAdapter;
import com.suku.learningkids.models.ItemModel;

import java.util.List;

/**
 * Created by SukamalD on 05-02-2018.
 */

public class TimePagerAdapter extends ImageItemPagerAdapter {

    public TimePagerAdapter(Activity context, List<ItemModel> flatImageModels) {
        super(context, flatImageModels);
    }



    @Override
    public Object instantiateItem(ViewGroup container, final  int position) {
        itemView = mLayoutInflater.inflate(R.layout.time_pager_item, container, false);
        TextView tvText = (TextView) itemView.findViewById(R.id.tv_text);
        final ItemModel flatImageModel = flatImageModels.get(position);
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/kid1.ttf");
        tvText.setTypeface(custom_font);
        if(!flatImageModel.isLocked()){
            tvText.setText(flatImageModel.getHeading());
            container.addView(itemView);
        }else{
            tvText.setText("Please Subscribe");
            container.addView(itemView);
        }

        tvText.setOnClickListener(new View.OnClickListener() {
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
