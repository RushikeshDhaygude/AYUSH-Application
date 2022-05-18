package com.example.ayush1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Dashboard extends AppCompatActivity {

    CardView cv1,cv2,cv3,cv4,cv5;
    ImageView hm1;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        cv1=findViewById(R.id.crd1);
        cv5=findViewById(R.id.crd5);
        hm1=findViewById(R.id.hamburger);
        name = findViewById(R.id.name);
        String name1="Hello ";

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            name.setText(String.format("%s%s", name1, signInAccount.getDisplayName()));
        }

        hm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,Profile.class);
                startActivity(intent);

            }
        });
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashboard.this, "Hospital Selected", Toast.LENGTH_SHORT).show();
            }
        });
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,HospitalReg.class);
                startActivity(intent);
            }
        });
    }
}