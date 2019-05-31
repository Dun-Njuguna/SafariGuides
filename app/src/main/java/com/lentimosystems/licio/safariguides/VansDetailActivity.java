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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lentimosystems.licio.safariguides.Common.Common;
import com.lentimosystems.licio.safariguides.Interface.ItemClickListener;
import com.lentimosystems.licio.safariguides.Models.VansItem;
import com.lentimosystems.licio.safariguides.ViewHolder.VansDetailViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class VansDetailActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference vansData;
    String vanId ="";
    VansItem currentVan;
    ImageView vanImage;
    TextView numberPlate;
    ImageView driverImage;
    TextView driverName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vans_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(Common.VAN_SELECTED);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        vanImage = (ImageView) findViewById(R.id.vanImage);
        numberPlate = (TextView) findViewById(R.id.numberPlate);
        driverImage = (ImageView) findViewById(R.id.driverImage);
        driverName = (TextView) findViewById(R.id.driverName);




        //firebase
        database = FirebaseDatabase.getInstance();
        vansData = database.getReference("vansData");

        //get van clicked id from intent
        if (getIntent() != null) {
            vanId = getIntent().getStringExtra("vanId");
        }
        
        loadVans(vanId);
    }

    private void loadVans(String vanId) {
        vansData.child(vanId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentVan = dataSnapshot.getValue(VansItem.class);
                Picasso.get().load(currentVan.getCarImage()).into(vanImage);
                numberPlate.setText(currentVan.getNumberPlate());
                Picasso.get().load(currentVan.getDriverImage()).into(driverImage);
                driverName.setText(currentVan.getDriver());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
