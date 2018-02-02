package com.suku.learningkids.features.flatimages;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.suku.learningkids.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 30-01-2018.
 */

public class ButtomImageAdapter extends RecyclerView.Adapter<ButtomImageAdapter.ImageViewHolder>{

    private List<FlatImageModel> flatImageModels;

    private ClickListener clickListener;

    public interface ClickListener {
        void onAdapterItemClick(View view, int position, Object selectedItem);
    }

    public void setItemClickListner(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public ButtomImageAdapter(List<FlatImageModel> flatImageModels){
        this.flatImageModels = flatImageModels;
    }

    public ButtomImageAdapter(List<FlatImageModel> flatImageModels,ClickListener clickListener){
        this.flatImageModels = flatImageModels;
        this.clickListener = clickListener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list_item, parent, false);
        ButtomImageAdapter.ImageViewHolder holder = new ButtomImageAdapter.ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        FlatImageModel flatImageModel = flatImageModels.get(position);
        holder.ivImages.setImageResource(flatImageModel.getImage());
        holder.position = position;
        holder.flatImageModel = flatImageModel;

    }

    @Override
    public int getItemCount() {
        return flatImageModels.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_images)
        ImageView ivImages;
        int position;
        FlatImageModel flatImageModel;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener != null){
                        clickListener.onAdapterItemClick(v,position,flatImageModel);
                    }
                }
            });
        }
    }
}
