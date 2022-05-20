//package com.example.ayush1;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//
//public class Profile extends AppCompatActivity {
//
//
//    TextView fullname, mail,phone;
//    FirebaseAuth fAuth;
//    FirebaseUser user;
//    DatabaseReference reference;
//    private String userId;
//    Button logout;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.profile);
//
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        reference= FirebaseDatabase.getInstance().getReference("Users");
//        userId=user.getUid();
//        logout = findViewById(R.id.logout);
//        fullname = findViewById(R.id.proname);
//        mail = findViewById(R.id.mail);
//        phone=findViewById(R.id.phone);
//
//        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User userProfile= snapshot.getValue(User.class);
//                if(userProfile!=null){
//                    String name=userProfile.name;
//                    String mobile=userProfile.mobile;
//                    String email=userProfile.email;
//
//                    fullname.setText(name);
//                    phone.setText(mobile);
//                    mail.setText(email);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Profile.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if(signInAccount != null){
//            fullname.setText(signInAccount.getDisplayName());
//            mail.setText(signInAccount.getEmail());
//        }
//
//
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
//                startActivity(intent);
//            }
//        });
//
//
//    }
//}