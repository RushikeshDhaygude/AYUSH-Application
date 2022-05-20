package com.example.ayush1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HospDash extends AppCompatActivity {
    CardView cv1,cv2,cv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosp_dash);
        cv1=findViewById(R.id.crd1);
        cv2=findViewById(R.id.crd4);
        cv3=findViewById(R.id.crd5);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HospDash.this, "Hello", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HospDash.this,AddHospital.class);
                startActivity(intent);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HospDash.this,AddSpecialist.class);
                startActivity(intent);
            }
        });
    }
}