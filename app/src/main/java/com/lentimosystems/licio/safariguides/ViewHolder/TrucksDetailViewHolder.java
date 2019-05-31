package com.lentimosystems.licio.safariguides.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lentimosystems.licio.safariguides.Interface.ItemClickListener;
import com.lentimosystems.licio.safariguides.R;

public class TrucksDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemClickListener itemClickListener;

    public ImageView truckImage, driverImage;
    public TextView numberPlate, ratingTextView, driverName;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public TrucksDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        truckImage = (ImageView) itemView.findViewById(R.id.truckImage);
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
