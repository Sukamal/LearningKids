package com.suku.learningkids.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 30-01-2018.
 */

public class NumberImageAdapter extends RecyclerView.Adapter<NumberImageAdapter.ImageViewHolder>{

    private int imageCount;
    private int image;

    private AdapterItemClickListener clickListener;

    public interface ClickListener {
        void onAdapterItemClick(View view, int position, Object selectedItem);
    }

    public void setItemClickListner(AdapterItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public NumberImageAdapter(int imageCount,int image){
        this.imageCount = imageCount;
        this.image = image;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.number_image_list_item, parent, false);
        NumberImageAdapter.ImageViewHolder holder = new NumberImageAdapter.ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        holder.ivImages.setImageResource(image);
        holder.tvImagecount.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return imageCount;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_images)
        ImageView ivImages;
        @BindView(R.id.tv_imagecount)
        TextView tvImagecount;


        int position;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
