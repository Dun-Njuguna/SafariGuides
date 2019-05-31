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

public class CruiserDetailActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference cruiserData;
    String cruiserId ="";
    VansItem currentCruiser;
    ImageView cruiserImage;
    TextView numberPlate;
    ImageView driverImage;
    TextView driverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruiser_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(Common.CRUISER_SELECTED);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        cruiserImage = (ImageView) findViewById(R.id.cruiserImage);
        numberPlate = (TextView) findViewById(R.id.numberPlate);
        driverImage = (ImageView) findViewById(R.id.driverImage);
        driverName = (TextView) findViewById(R.id.driverName);

        //firebase
        database = FirebaseDatabase.getInstance();
        cruiserData = database.getReference("cruisersData");

        //get van clicked id from intent
        if (getIntent() != null) {
            cruiserId = getIntent().getStringExtra("cruiserId");
        }

        loadCruisers(cruiserId);

    }

    private void loadCruisers(String cruiserId) {
        cruiserData.child(cruiserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentCruiser = dataSnapshot.getValue(VansItem.class);
                Picasso.get().load(currentCruiser.getCarImage()).into(cruiserImage);
                numberPlate.setText(currentCruiser.getNumberPlate());
                Picasso.get().load(currentCruiser.getDriverImage()).into(driverImage);
                driverName.setText(currentCruiser.getDriver());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
