package com.example.ayush1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AddHospital extends AppCompatActivity {

    RatingBar ratingBar;
    EditText hospname,hospemail,hosparea,hospphone,hospbedcount,hospspec;
    Button btn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);
        hospname=findViewById(R.id.HospName);
        hospemail=findViewById(R.id.HospEmail);
        hosparea=findViewById(R.id.HospArea);
        ratingBar=findViewById(R.id.ratings);
        hospphone=findViewById(R.id.HospPhone);
        hospspec=findViewById(R.id.HospSpec);
        hospbedcount=findViewById(R.id.HospBedCount);

        ratingBar= findViewById(R.id.ratings);
        btn=(Button) findViewById(R.id.HospButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),s+"stars",Toast.LENGTH_SHORT).show();
                addData();
            }
        });

    }

    private void addData() {
        String hname=hospname.getText().toString().trim();
        String hemail1=hospemail.getText().toString().trim();
        String hphone=hospphone.getText().toString().trim();
        String harea=hosparea.getText().toString().trim();
        String hbedcount=hospbedcount.getText().toString().trim();
        String hratings = String.valueOf(ratingBar.getRating());
        String hspec=hospspec.getText().toString().trim();
        String lat=hosparea.getText().toString().trim();
        String lon=hosparea.getText().toString().trim();



//            mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()){
//                        FirebaseUser userVerify= FirebaseAuth.getInstance().getCurrentUser();
//                        userVerify.sendEmailVerification();
        Hospitals hosp=new Hospitals(hname,hemail1,harea,hphone,hbedcount,hratings,hspec,lat,lon);
        FirebaseDatabase.getInstance("https://lucid-course-346310-default-rtdb.firebaseio.com/").getReference("Hospital")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(hosp).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddHospital.this, "Data Added Successfully!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddHospital.this, "Error!Try Again", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
