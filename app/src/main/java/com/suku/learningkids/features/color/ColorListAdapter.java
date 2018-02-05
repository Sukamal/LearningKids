package com.suku.learningkids.features.color;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.commonInterface.AdapterItemClickListener;
import com.suku.learningkids.features.alphabet.AlphabetModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 30-01-2018.
 */

public class ColorListAdapter extends RecyclerView.Adapter<ColorListAdapter.AlphabetViewHolder>{

    private List<AlphabetModel> alphabetList;
    private AdapterItemClickListener clickListener;

    public interface ClickListener {
        void onAdapterItemClick(View view, int position, Object selectedItem);
    }

    public ColorListAdapter(List<AlphabetModel> alphabetList){
        this.alphabetList = alphabetList;
    }

    public ColorListAdapter(List<AlphabetModel> alphabetList, AdapterItemClickListener clickListener){
        this.alphabetList = alphabetList;
        this.clickListener = clickListener;
    }

    public void setItemClickListner(AdapterItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public AlphabetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.color_list_item, parent, false);
        AlphabetViewHolder holder = new AlphabetViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlphabetViewHolder holder, int position) {
        AlphabetModel alphabetModel = alphabetList.get(position);
        holder.tvColor.setText(alphabetModel.getWord());
        holder.tvColor.setBackgroundResource(alphabetModel.getImage());
        holder.tvColor.setTextColor(alphabetModel.getTextColor());
        holder.position = position;

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
        int position;

        public AlphabetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                AlphabetModel alphabetModel = alphabetList.get(position);
                clickListener.onAdapterItemClick(v,position,alphabetModel);
            }
        }
    }
}
