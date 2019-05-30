package com.lentimosystems.licio.safariguides;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.lentimosystems.licio.safariguides.Common.Common;
import com.lentimosystems.licio.safariguides.Interface.ItemClickListener;
import com.lentimosystems.licio.safariguides.Models.VansItem;
import com.lentimosystems.licio.safariguides.ViewHolder.VansDetailViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class VansDetailActivity extends AppCompatActivity {

    Query query;
    FirebaseRecyclerOptions<VansItem> options;
    FirebaseRecyclerAdapter<VansItem, VansDetailViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference vansData;

    RecyclerView recyclerView;
   // RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vans_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(Common.VAN_SELECTED);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        recyclerView = (RecyclerView)findViewById(R.id.recycler_vans_detail);
//        recyclerView.setHasFixedSize(true);

        //relativeLayout = (RelativeLayout)findViewById(R.id.relative_vans_detail);
        
        loadVans();
    }

    private void loadVans() {
        database = FirebaseDatabase.getInstance();
        vansData = database.getReference(Common.STR_VANS_BACKGROUND);

        query = FirebaseDatabase.getInstance().getReference(Common.STR_VANS_BACKGROUND)
                .orderByChild("1").equalTo("numberPlate");
        options = new FirebaseRecyclerOptions.Builder<VansItem>()
                .setQuery(query,VansItem.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<VansItem, VansDetailViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final VansDetailViewHolder holder, int position, @NonNull final VansItem model) {
                Picasso.get()
                        .load(model.getCarImage())
                        .into(holder.vanImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                Picasso.get()
                                        .load(model.getCarImage())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.vanImage, new Callback() {
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

                Picasso.get()
                        .load(model.getDriverImage())
                        .into(holder.driverImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                Picasso.get()
                                        .load(model.getDriverImage())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.driverImage, new Callback() {
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

                holder.driverName.setText(model.getDriver());
                holder.numberPlate.setText(model.getNumberPlate());

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }
                });
            }

            @NonNull
            @Override
            public VansDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_driver_info,parent,false);

                return new VansDetailViewHolder(itemView);
            }
        };

        adapter.startListening();
//        recyclerView.setAdapter(adapter);
       // relativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        if (adapter!=null)
            adapter.stopListening();
        super.onStop();
    }
}
