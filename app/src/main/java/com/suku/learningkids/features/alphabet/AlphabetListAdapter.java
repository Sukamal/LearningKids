package com.suku.learningkids.features.alphabet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suku.learningkids.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 30-01-2018.
 */

public class AlphabetListAdapter extends RecyclerView.Adapter<AlphabetListAdapter.AlphabetViewHolder>{

    private List<AlphabetModel> alphabetList;
    private ClickListener clickListener;

    public interface ClickListener {
        void onAdapterItemClick(View view, int position, Object selectedItem);
    }

    public AlphabetListAdapter(List<AlphabetModel> alphabetList){
        this.alphabetList = alphabetList;
    }

    public AlphabetListAdapter(List<AlphabetModel> alphabetList,ClickListener clickListener){
        this.alphabetList = alphabetList;
        this.clickListener = clickListener;
    }

    public void setItemClickListner(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public AlphabetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alphabet_list_item, parent, false);
        AlphabetViewHolder holder = new AlphabetViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlphabetViewHolder holder, int position) {
        AlphabetModel alphabetModel = alphabetList.get(position);
        holder.tvAlphabet.setText(alphabetModel.getAlphabetCaps());
        holder.position = position;

        if(position == 0){
            holder.tvAlphabet.setBackgroundResource(R.color.color1);
        }else{

            int reminder = position % 5;
            switch (reminder){
                case 1:
                    holder.tvAlphabet.setBackgroundResource(R.color.color2);
                    break;
                case 2:
                    holder.tvAlphabet.setBackgroundResource(R.color.color3);
                    break;
                case 3:
                    holder.tvAlphabet.setBackgroundResource(R.color.color4);
                    break;
                case 4:
                    holder.tvAlphabet.setBackgroundResource(R.color.color5);
                    break;
                case 0:
                    holder.tvAlphabet.setBackgroundResource(R.color.color6);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return alphabetList.size();
    }

    public class AlphabetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_alphabet)
        TextView tvAlphabet;
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