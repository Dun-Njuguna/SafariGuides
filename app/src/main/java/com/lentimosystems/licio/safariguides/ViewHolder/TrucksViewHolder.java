package com.lentimosystems.licio.safariguides.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lentimosystems.licio.safariguides.Interface.ItemClickListener;
import com.lentimosystems.licio.safariguides.R;

public class TrucksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView truck_name;
    public ImageView background_image;

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public TrucksViewHolder(@NonNull View itemView) {
        super(itemView);
        background_image = (ImageView)itemView.findViewById(R.id.image);
        truck_name = (TextView)itemView.findViewById(R.id.name);

        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onclick(v, getAdapterPosition(),false);
    }
}
