package com.lentimosystems.licio.safariguides.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lentimosystems.licio.safariguides.Interface.ItemClickListener;
import com.lentimosystems.licio.safariguides.R;

public class BusesDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ItemClickListener itemClickListener;

    public ImageView busImage, driverImage;
    public TextView numberPlate, ratingTextView, driverName;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public BusesDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        busImage = (ImageView) itemView.findViewById(R.id.busImage);
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
