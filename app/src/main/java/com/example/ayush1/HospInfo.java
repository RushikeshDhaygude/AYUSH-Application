package com.example.ayush1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HospInfo extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosp_info);

        Button loc = (Button) findViewById(R.id.location);
        Button contactus = (Button) findViewById(R.id.contact);
        TextView hospname = (TextView) findViewById(R.id.hospname);
        TextView hospaddr = (TextView) findViewById(R.id.hospaddr);
        TextView phone2 = (TextView) findViewById(R.id.hospnum2);
        TextView email = (TextView) findViewById(R.id.hospemail);
        TextView bedcount = (TextView) findViewById(R.id.bedcount);
        TextView specialization = (TextView) findViewById(R.id.specialization);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);

        Intent intent = getIntent();
        String hospnamefromdb = (String) intent.getSerializableExtra("hosp");

        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("Hospital");

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Hospitals hosp = snapshot.child(hospnamefromdb).getValue(Hospitals.class);
                if(hosp!=null) {

                    String addr = hosp.address;
                    String phone2db = hosp.phone_no2;
                    String emaildb = hosp.email;
                    String bedcountdb = hosp.bed_count;
                    String specdb = hosp.specialization;
//                    Long ratingdb = (Long) hosp.rating;


                    System.out.println(specdb + "\n" + bedcountdb);

                    hospname.setText(hospnamefromdb);
                    hospaddr.setText(addr);
                    phone2.setText(phone2db);
                    email.setText(emaildb);
                    bedcount.setText(bedcountdb);
                    specialization.setText(specdb);
//                  rating.setRating(ratingdb);

                    contactus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri u = Uri.parse("tel:" + phone2db);
                            Intent i = new Intent(Intent.ACTION_DIAL, u);
                            startActivity(i);
                        }
                    });

                    loc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                            i.putExtra("hosp",hospnamefromdb);
                            i.putExtra("lat",hosp.latitude);
                            i.putExtra("long",hosp.longitude);
//                            i.setClass();
                            startActivity(i);
//                            Intent intent = new Intent();
//                            intent.putExtra("hosp",hospnamefromdb);
//                            intent.setClass(getApplicationContext(),MapsActivity.class);
//                            startActivity(intent);
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });



//                Hospitals hosp = dataSnapshot.getValue(Hospitals.class);
//                if(hosp!=null) {
//                if(true) {
//                    for (DataSnapshot ds : dataSnapshot.getChildren()){
//                        Hospitals hosp = ds.getValue(Hospitals.class);
//                        String addr = hosp.address;
//                        String phone2db = hosp.phone_no2;
//                        String emaildb = hosp.email;
//                        String bedcountdb = hosp.bed_count;
//                        String specdb = hosp.specialization;
////                    Long ratingdb = (Long) hosp.rating;
//
//
//                        System.out.println(specdb + "\n" + bedcountdb);
//
//                        hospname.setText(hospnamefromdb);
//                        hospaddr.setText(addr);
//                        phone2.setText(phone2db);
//                        email.setText(emaildb);
//                        bedcount.setText(bedcountdb);
//                        specialization.setText(specdb);
////                  rating.setRating(ratingdb);
//
//                        contactus.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Uri u = Uri.parse("tel:" + phone2db);
//                                Intent i = new Intent(Intent.ACTION_DIAL, u);
//                                startActivity(i);
//                            }
//                        });
//
//                        loc.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
//                                i.putExtra("hosp",hospnamefromdb);
////                                i.putExtra("lat",hosp.getLat());
////                                i.putExtra("long",hosp.getLongit());
////                            i.setClass();
//                                startActivity(i);
////                            Intent intent = new Intent();
////                            intent.putExtra("hosp",hospnamefromdb);
////                            intent.setClass(getApplicationContext(),MapsActivity.class);
////                            startActivity(intent);
//                            }
//                        });
//                    }





    }
}