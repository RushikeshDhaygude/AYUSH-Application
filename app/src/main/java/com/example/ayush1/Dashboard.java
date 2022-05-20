package com.example.ayush1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    CardView cv1,cv2,cv3,cv4,cv5;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    FirebaseAuth fAuth;
    FirebaseUser user;
    DatabaseReference reference;
    private String userId;
    Button logout;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        View headerView=navigationView.getHeaderView(0);
        TextView fullname=(TextView)headerView.findViewById(R.id.ProfileName);
        TextView mail=(TextView)headerView.findViewById(R.id.ProfileEmail);
        Button button=(Button)headerView.findViewById(R.id.logout);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        try {
            userId=user.getUid();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logout = findViewById(R.id.logout);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        cv1=findViewById(R.id.crd1);
        cv2=findViewById(R.id.crd2);
        cv4=findViewById(R.id.crd4);
        cv5=findViewById(R.id.crd5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);

            }
        });

        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);







try {
    reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            User userProfile= snapshot.getValue(User.class);
            if(userProfile!=null){
                String name=userProfile.name;
//                  String mobile=userProfile.mobile;
                String email=userProfile.email;
                fullname.setText(name);
                mail.setText(email);

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(Dashboard.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

        }
    });

}
catch (Exception e) {
    e.printStackTrace();
}

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            fullname.setText(signInAccount.getDisplayName());
            mail.setText(signInAccount.getEmail());
        }


//        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                    {
                        Intent intent=new Intent(Dashboard.this,Dashboard.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.login:
                    {
                        Intent intent=new Intent(Dashboard.this,login.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.ratings:
                    {
                        Toast.makeText(Dashboard.this, "Ratings Selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.about:
                    {
                        Toast.makeText(Dashboard.this, "About Selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                return false;
            }
        });





//        name = findViewById(R.id.name);
//        String name1="Hello ";
//
//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if(signInAccount != null){
//            name.setText(String.format("%s%s", name1, signInAccount.getDisplayName()));
//        }

//        hm1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Dashboard.this,Profile.class);
//                startActivity(intent);
//
//            }
//        });
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashboard.this, "Hospital Selected", Toast.LENGTH_SHORT).show();
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashboard.this, "Chat", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Dashboard.this,ChatBot.class);
                startActivity(intent);
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dashboard.this, "Hospitals in Pune", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Dashboard.this,HospitalList.class);
                startActivity(intent);
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

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}