package com.lentimosystems.licio.safariguides;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

public class TrucksDetailActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference trucksData;
    String truckId ="";
    VansItem currentTruck;
    ImageView truckImage;
    TextView numberPlate;
    ImageView driverImage;
    TextView driverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trucks_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(Common.TRUCK_SELECTED);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        truckImage = (ImageView) findViewById(R.id.truckImage);
        numberPlate = (TextView) findViewById(R.id.numberPlate);
        driverImage = (ImageView) findViewById(R.id.driverImage);
        driverName = (TextView) findViewById(R.id.driverName);

        //firebase
        database = FirebaseDatabase.getInstance();
        trucksData = database.getReference("trucksData");

        //get van clicked id from intent
        if (getIntent() != null) {
            truckId = getIntent().getStringExtra("truckId");
        }

        loadTrucks(truckId);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(TrucksDetailActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadTrucks(String truckId) {
        trucksData.child(truckId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentTruck = dataSnapshot.getValue(VansItem.class);
                Picasso.get().load(currentTruck.getCar_image()).into(truckImage);
                numberPlate.setText(currentTruck.getNumber_plate());
              //  Picasso.get().load(currentTruck.getDriverImage()).into(driverImage);
                driverName.setText(currentTruck.getDriver());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
