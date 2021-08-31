package com.example.app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecyclerview extends RecyclerView.Adapter<AdapterRecyclerview.ViewHoder> implements View.OnClickListener, View.OnLongClickListener{

    List<GameInfo> gameInfoList;
    Context context;
    private ItemClickListener mItemListener;

    public AdapterRecyclerview(List<GameInfo> gameInfoList,ItemClickListener itemClickListener) {
        this.gameInfoList = gameInfoList;
        this.mItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recyclerview, parent, false);
        ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        GameInfo gameInfo = gameInfoList.get(position);

        holder.tvPrice.setText(gameInfo.getPrice());
        holder.tvName.setText(gameInfo.getName());
        holder.tvContent.setText(gameInfo.getContent());
        Picasso.get().load(gameInfo.getThumbnail()).into(holder.img);

        holder.itemView.setOnClickListener(v -> {
            mItemListener.onItemClick(gameInfoList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return gameInfoList.size();
    }

    public interface ItemClickListener {
        void onItemClick(GameInfo details);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    public class ViewHoder extends RecyclerView.ViewHolder{
        TextView tvName, tvContent, tvPrice;
        ImageView img;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tvPriceRec);
            tvName = itemView.findViewById(R.id.tvNameRec);
            tvContent = itemView.findViewById(R.id.tvContentRec);
            img = itemView.findViewById(R.id.img);

        }

    }

}
