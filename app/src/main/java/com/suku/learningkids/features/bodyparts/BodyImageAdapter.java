package com.suku.learningkids.features.bodyparts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.suku.learningkids.R;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.models.ItemModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 30-01-2018.
 */

public class BodyImageAdapter extends RecyclerView.Adapter<BodyImageAdapter.ImageViewHolder>{

    private List<BodyPartsModel> itemModels;
    private AdapterItemClickListener clickListener;


    public void setItemClickListner(AdapterItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public BodyImageAdapter(List<BodyPartsModel> itemModels){
        this.itemModels = itemModels;
    }

    public BodyImageAdapter(List<BodyPartsModel> itemModels, AdapterItemClickListener clickListener){
        this.itemModels = itemModels;
        this.clickListener = clickListener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list_item, parent, false);
        BodyImageAdapter.ImageViewHolder holder = new BodyImageAdapter.ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        BodyPartsModel itemModel = itemModels.get(position);
        holder.ivImages.setImageResource(itemModel.getPartsImage());

        holder.position = position;
        holder.itemModel = itemModel;

        if(itemModel.getIsLocked()){
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

        @BindView(R.id.iv_images)
        ImageView ivImages;
        @BindView(R.id.iv_images_lock)
        ImageView ivImagesLock;

        int position;
        BodyPartsModel itemModel;

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
