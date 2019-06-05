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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lentimosystems.licio.safariguides.Common.Common;
import com.lentimosystems.licio.safariguides.Interface.ItemClickListener;
import com.lentimosystems.licio.safariguides.Models.Comment;
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
    TextView numberPlate,ratingTextView;
    ImageView driverImage;
    TextView driverName;
    Button btnRate,btnAddReview;
    AppCompatRatingBar ratingBar;
    EditText edtReview;
    FirebaseUser firebaseUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vans_detail);

        btnRate = findViewById(R.id.btnRate);
        ratingTextView = findViewById(R.id.ratingTextView);
        ratingBar = findViewById(R.id.rate);
        edtReview = findViewById(R.id.edtReview);
        btnAddReview = findViewById(R.id.btnAddReview);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingTextView.setText("Rating: "+rating);
            }
        });
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(VansDetailActivity.this, "Rated"+ratingBar, Toast.LENGTH_SHORT).show();
                //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            }
        });

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

//        btnAddReview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnAddReview.setVisibility(View.INVISIBLE);
//                DatabaseReference vansData = database.getReference("Comment").child(vanId).push();
//                String comment_content = edtReview.getText().toString();
//                String uid = firebaseUser.getUid();
//                String uname = firebaseUser.getDisplayName();
//                Comment comment = new Comment(comment_content,uid,uname);
//            }
//        });
//
//        vansData.setValue(currentVan).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                showMessage("comment added");
//                edtReview.setText("");
//                btnAddReview.setVisibility(View.VISIBLE);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                showMessage("failed to add comment : "+e.getMessage());
//            }
//        });


        loadVans(vanId);
    }
//    private void showMessage(String message) {
//
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
//
//    }
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
