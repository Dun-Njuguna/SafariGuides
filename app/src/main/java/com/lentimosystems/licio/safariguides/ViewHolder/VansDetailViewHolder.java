package com.lentimosystems.licio.safariguides.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lentimosystems.licio.safariguides.Interface.ItemClickListener;
import com.lentimosystems.licio.safariguides.R;


public class VansDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemClickListener itemClickListener;

    public ImageView vanImage, driverImage;
    public TextView numberPlate, ratingTextView, driverName;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public VansDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        vanImage = (ImageView) itemView.findViewById(R.id.vanImage);
        driverImage = (ImageView)itemView.findViewById(R.id.driverImage);
        numberPlate = (TextView)itemView.findViewById(R.id.numberPlate);
        ratingTextView = (TextView)itemView.findViewById(R.id.ratingTextView);
        driverName = (TextView)itemView.findViewById(R.id.driverName);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onclick(v, getAdapterPosition(),false);
    }
}
