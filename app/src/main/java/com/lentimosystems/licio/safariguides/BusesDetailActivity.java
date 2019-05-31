package com.lentimosystems.licio.safariguides;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lentimosystems.licio.safariguides.Common.Common;
import com.lentimosystems.licio.safariguides.Models.VansItem;
import com.squareup.picasso.Picasso;

public class BusesDetailActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference busesData;
    String busId ="";
    VansItem currentBus;
    ImageView busImage;
    TextView numberPlate;
    ImageView driverImage;
    TextView driverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buses_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(Common.BUS_SELECTED);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        busImage = (ImageView) findViewById(R.id.busImage);
        numberPlate = (TextView) findViewById(R.id.numberPlate);
        driverImage = (ImageView) findViewById(R.id.driverImage);
        driverName = (TextView) findViewById(R.id.driverName);

        //firebase
        database = FirebaseDatabase.getInstance();
        busesData = database.getReference("busesData");

        //get van clicked id from intent
        if (getIntent() != null) {
            busId = getIntent().getStringExtra("busId");
        }

        loadBuses(busId);
    }

    private void loadBuses(String busId) {
        busesData.child(busId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentBus = dataSnapshot.getValue(VansItem.class);
                Picasso.get().load(currentBus.getCarImage()).into(busImage);
                numberPlate.setText(currentBus.getNumberPlate());
               // Picasso.get().load(currentBus.getDriverImage()).into(driverImage);
                driverName.setText(currentBus.getDriver());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
