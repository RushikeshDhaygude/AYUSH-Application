package com.example.ayush1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;


    private  static int SPLASH_SCREEN= 5000;
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image=findViewById(R.id.imageView);
        logo=findViewById(R.id.textView);
        name=findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        name.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkLoginI();
                finish();
            }
        },SPLASH_SCREEN);
    }

    private void checkLoginI() {
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser=mAuth.getCurrentUser();
        if(mFirebaseUser!=null){
            Intent intent=new Intent(MainActivity.this,Dashboard.class);
            startActivity(intent);


        }
        else{
            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);


        }

    }

}