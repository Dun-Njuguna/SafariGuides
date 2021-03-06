package com.lentimosystems.licio.safariguides.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lentimosystems.licio.safariguides.Common.Common;
import com.lentimosystems.licio.safariguides.Interface.ItemClickListener;
import com.lentimosystems.licio.safariguides.Models.VansItem;
import com.lentimosystems.licio.safariguides.R;
import com.lentimosystems.licio.safariguides.TrucksDetailActivity;
import com.lentimosystems.licio.safariguides.ViewHolder.CruisersViewHolder;
import com.lentimosystems.licio.safariguides.ViewHolder.TrucksViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrucksFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference trucksData;

    FirebaseRecyclerOptions<VansItem> options;
    FirebaseRecyclerAdapter<VansItem, TrucksViewHolder> adapter;

    RecyclerView recyclerView;
    private static TrucksFragment INSTANCE=null;


    public TrucksFragment() {
        database = FirebaseDatabase.getInstance();
        trucksData = database.getReference(Common.STR_TRUCKS_BACKGROUND);

        options = new FirebaseRecyclerOptions.Builder<VansItem>()
                .setQuery(trucksData,VansItem.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<VansItem, TrucksViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final TrucksViewHolder holder, int position, @NonNull final VansItem model) {
                Picasso.get()
                        .load(model.getCar_image())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.background_image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                Picasso.get()
                                        .load(model.getCar_image())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.background_image, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError(Exception e) {
                                                Log.e("Error","Couldn't fetch image");
                                            }
                                        });
                            }
                        });
                holder.truck_name.setText(model.getNumber_plate());

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onclick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(getActivity(), TrucksDetailActivity.class);
                        intent.putExtra("truckId", adapter.getRef(position).getKey());
                        startActivity(intent);

                    }
                });
            }
            @NonNull
            @Override
            public TrucksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_van_item,parent,false);
                return new TrucksViewHolder(itemView);
            }
        };
    }

    public static TrucksFragment getInstance(){
        if (INSTANCE==null)
            INSTANCE = new TrucksFragment();
        return INSTANCE;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_trucks, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_trucks);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        setVans();

        return view;
    }
    private void setVans() {
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter!=null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        if (adapter!=null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter!=null)
            adapter.startListening();
    }

}
