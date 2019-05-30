package com.lentimosystems.licio.safariguides.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lentimosystems.licio.safariguides.Interface.ItemClickListener;
import com.lentimosystems.licio.safariguides.R;

public class BusesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView bus_name;
    public ImageView background_image;

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public BusesViewHolder(@NonNull View itemView) {
        super(itemView);
        background_image = (ImageView)itemView.findViewById(R.id.image);
        bus_name = (TextView)itemView.findViewById(R.id.name);

        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}
