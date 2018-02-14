package com.suku.learningkids.features.color;

import android.content.Context;
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

public class ColorListAdapter extends RecyclerView.Adapter<ColorListAdapter.AlphabetViewHolder>{

    private List<ItemModel> alphabetList;
    private AdapterItemClickListener clickListener;
    private Context context;

    public interface ClickListener {
        void onAdapterItemClick(View view, int position, Object selectedItem);
    }

    public ColorListAdapter(List<ItemModel> alphabetList){
        this.alphabetList = alphabetList;
    }

    public ColorListAdapter(List<ItemModel> alphabetList, AdapterItemClickListener clickListener){
        this.alphabetList = alphabetList;
        this.clickListener = clickListener;
    }

    public void setItemClickListner(AdapterItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public AlphabetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.color_list_item, parent, false);
        AlphabetViewHolder holder = new AlphabetViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(AlphabetViewHolder holder, int position) {
        ItemModel itemModel = alphabetList.get(position);
        holder.tvColor.setText(itemModel.getSubheading2());
        holder.tvColor.setTextColor(context.getResources().getColor(itemModel.getTextColor()));
        holder.position = position;

        if(itemModel.isLocked()){
            holder.ivImagesLock.setVisibility(View.VISIBLE);
            holder.tvColor.setBackgroundResource(R.color.color_white);
        }else{
            holder.ivImagesLock.setVisibility(View.GONE);
            holder.tvColor.setBackgroundResource(itemModel.getImage());
        }

    }

    @Override
    public int getItemCount() {
        if(alphabetList != null)
            return alphabetList.size();
        else
            return 0;
    }

    public class AlphabetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_color)
        TextView tvColor;

        @BindView(R.id.iv_images_lock)
        ImageView ivImagesLock;

        int position;

        public AlphabetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                ItemModel alphabetModel = alphabetList.get(position);
                clickListener.onAdapterItemClick(v,position,alphabetModel);
            }
        }
    }
}
