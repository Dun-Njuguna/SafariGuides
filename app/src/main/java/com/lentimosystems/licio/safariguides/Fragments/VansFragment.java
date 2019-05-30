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
import com.lentimosystems.licio.safariguides.VansDetailActivity;
import com.lentimosystems.licio.safariguides.ViewHolder.VansViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class VansFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference vansData;

    FirebaseRecyclerOptions<VansItem> options;
    FirebaseRecyclerAdapter<VansItem, VansViewHolder> adapter;

    RecyclerView recyclerView;


    private static VansFragment INSTANCE=null;


    public VansFragment() {
       database = FirebaseDatabase.getInstance();
       vansData = database.getReference(Common.STR_VANS_BACKGROUND);

       options = new FirebaseRecyclerOptions.Builder<VansItem>()
               .setQuery(vansData,VansItem.class)
               .build();

       adapter = new FirebaseRecyclerAdapter<VansItem, VansViewHolder>(options) {
           @Override
           protected void onBindViewHolder(@NonNull final VansViewHolder holder, int position, @NonNull final VansItem model) {
               Picasso.get()
                       .load(model.getCarImage())
                       .networkPolicy(NetworkPolicy.OFFLINE)
                       .into(holder.background_image, new Callback() {
                           @Override
                           public void onSuccess() {

                           }

                           @Override
                           public void onError(Exception e) {
                                Picasso.get()
                                        .load(model.getCarImage())
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
               holder.van_name.setText(model.getNumberPlate());

               holder.setItemClickListener(new ItemClickListener() {
                   @Override
                   public void onClick(View view, int position) {
                       Common.VAN_ID_SELECTED = adapter.getRef(position).getKey();
                       Common.VAN_SELECTED = model.getNumberPlate();
                       Intent intent = new Intent(getActivity(), VansDetailActivity.class);
                       startActivity(intent);
                   }
               });
           }

           @NonNull
           @Override
           public VansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.layout_van_item,parent,false);
               return new VansViewHolder(itemView);
           }
       };
    }

    public static VansFragment getInstance(){
        if (INSTANCE==null)
            INSTANCE = new VansFragment();
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
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_vans);
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
