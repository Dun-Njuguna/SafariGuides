package com.lentimosystems.licio.safariguides;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    VansItem currentVan;
    ImageView vanImage;
    TextView numberPlate,ratingTextView;
    ImageView driverImage;
    TextView driverName;
    Button btnRate;

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
        btnRate = (Button)findViewById(R.id.btnRate);
        ratingTextView = (TextView)findViewById(R.id.ratingTextView);

//        ratingTextView.setText(currentVan.getRates());

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VansDetailActivity.this,RatingActivity.class);
                startActivity(intent);
            }
        });

        //firebase
        database = FirebaseDatabase.getInstance();
        vansData = database.getReference("vansData");

        //get van clicked id from intent
        if (getIntent() != null) {
            Common.vanId = getIntent().getStringExtra("vanId");
            Log.d("vaidid", Common.vanId);
        }

        loadVans(Common.vanId);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(VansDetailActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadVans(String vanId) {
        vansData.child(vanId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentVan = dataSnapshot.getValue(VansItem.class);
                Picasso.get().load(currentVan.getCar_image()).into(vanImage);
                numberPlate.setText(currentVan.getNumber_plate());
                Picasso.get().load(currentVan.getDriver_image()).into(driverImage);
                driverName.setText(currentVan.getDriver());
                if (currentVan.getRates().equals("NaN"))
                    ratingTextView.setText("No rates yet");
                else
                    ratingTextView.setText(currentVan.getRates());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
