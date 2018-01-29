package com.suku.learningkids.features.home;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.suku.learningkids.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.HomeViewHolder>{

    private List<MenuModel> menuModels;
    private ClickListener clickListener;
    private Context context;


    public interface ClickListener {
        void onAdapterItemClick(View view, int position, Object selectedItem);
    }

    public HomeMenuAdapter(List<MenuModel> menuModels){
        this.menuModels = menuModels;
    }

    public HomeMenuAdapter(List<MenuModel> menuModels, ClickListener clickListener){
        this.menuModels = menuModels;
        this.clickListener = clickListener;
    }

    public void setItemClickListner(ClickListener clickListener){
        this.clickListener = clickListener;
    }


    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_home_item, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

        MenuModel menuModel = menuModels.get(position);

        holder.flBackground.setBackgroundResource(menuModel.getBackgroundColor());
        holder.ivMenuImage.setImageResource(menuModel.getImage());
        holder.tvMenuTitle.setText(menuModel.getMenuTitle());
        holder.tvMenuTitle.setTextColor(context.getResources().getColor(menuModel.getTextColor()));
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        if(menuModels != null){
            return menuModels.size();
        }else{
            return 0;
        }

    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.fl_background)
        FrameLayout flBackground;
        @BindView(R.id.iv_menu_image)
        ImageView ivMenuImage;
        @BindView(R.id.tv_menu_title)
        TextView tvMenuTitle;

        int position;



        public HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                MenuModel menuModel = menuModels.get(position);
                clickListener.onAdapterItemClick(v,position,menuModel);
            }
        }
    }
}
