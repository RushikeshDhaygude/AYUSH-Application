package com.example.ayush1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class HospitalLog extends AppCompatActivity {

    AwesomeValidation awesomeValidation;
    EditText email,password;
    Button b2;
    TextView forget;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_hospital_log);

        email= findViewById(R.id.editTextAyushID);
        password = findViewById(R.id.editTextPassword);
        b2 = findViewById(R.id.cirLoginButton);
        mAuth= FirebaseAuth.getInstance();
        forget=findViewById(R.id.ForgetPass);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.editTextAyushID,
                RegexTemplate.NOT_EMPTY, R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.editTextPassword,
                RegexTemplate.NOT_EMPTY, R.string.passwordmatch);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    hospitalLogin();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Failed, Please enter the correct credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendForgotPassLink();

            }
        });


    }

    private void sendForgotPassLink() {
        if (!TextUtils.isEmpty(email.getText().toString())) {
            mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Reset link sent to "+ email.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_LONG).show();
        }

    }


    private void hospitalLogin() {
        String email1=email.getText().toString().trim();
        String password1=password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(HospitalLog.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(HospitalLog.this,HospDash.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(HospitalLog.this, "Wrong Credentials! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,HospitalReg.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

    }
}
