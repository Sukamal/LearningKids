package com.suku.learningkids.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.models.ItemModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 30-01-2018.
 */

public class ButtomTextAdapter extends RecyclerView.Adapter<ButtomTextAdapter.ImageViewHolder>{

    private List<ItemModel> itemModels;
    private AdapterItemClickListener clickListener;


    public void setItemClickListner(AdapterItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public ButtomTextAdapter(List<ItemModel> itemModels){
        this.itemModels = itemModels;
    }

    public ButtomTextAdapter(List<ItemModel> itemModels, AdapterItemClickListener clickListener){
        this.itemModels = itemModels;
        this.clickListener = clickListener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buttom_text_list_item, parent, false);
        ButtomTextAdapter.ImageViewHolder holder = new ButtomTextAdapter.ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        ItemModel itemModel = itemModels.get(position);

        holder.tvText.setText(itemModel.getHeading().substring(0,3));
        holder.position = position;
        holder.itemModel = itemModel;


        if(itemModel.isLocked()){
            holder.ivImagesLock.setVisibility(View.VISIBLE);
        }else{
            holder.ivImagesLock.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if(itemModels != null)
            return itemModels.size();
        else
            return 0;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_text)
        TextView tvText;
        @BindView(R.id.iv_images_lock)
        ImageView ivImagesLock;

        int position;
        ItemModel itemModel;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener != null){
                        clickListener.onAdapterItemClick(v,position,itemModel);
                    }
                }
            });
        }
    }
}
