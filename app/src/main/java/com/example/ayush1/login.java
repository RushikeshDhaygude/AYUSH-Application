package com.example.ayush1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class login extends AppCompatActivity {

    AwesomeValidation awesomeValidation;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    EditText logemail,logpassword;
    TextView forget;
    Button b2;
    ImageView gSign,fSign;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

        logemail = findViewById(R.id.editTextEmailID);
        logpassword = findViewById(R.id.editTextPassword);
        gSign=findViewById(R.id.googleSign);
        fSign=findViewById(R.id.facebookSign);
        b2 = findViewById(R.id.cirLoginButton);
        forget=findViewById(R.id.ForgetPass);
        mAuth= FirebaseAuth.getInstance();
        createRequest();
        gSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignin();

            }
        });
        fSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "Hello6", Toast.LENGTH_SHORT).show();
            }
        });




        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendForgotPassLink();

            }
        });


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.editTextEmailID,
                RegexTemplate.NOT_EMPTY,R.string.invalid_ayushid);
        awesomeValidation.addValidation(this,R.id.editTextPassword,
                RegexTemplate.NOT_EMPTY,R.string.passwordmatch);

        b2.setOnClickListener(view -> {
//            String email1=email.getText().toString().trim();
//            if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
//                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
//            }
            if(awesomeValidation.validate()){
                userLogin();
//                Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Login Failed, Please enter the correct credentials",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void googleSignin() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this,"Some error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(login.this, "Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(login.this,Dashboard.class);
                            startActivity(intent);
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
//                            Intent intent = new Intent(getApplicationContext(),Profile.class);
//                            startActivity(intent);

                        } else {
                            Toast.makeText(login.this, "Sorry auth failed", Toast.LENGTH_SHORT).show();

                        }


                        // ...
                    }
                });
    }




    private void sendForgotPassLink() {
        if (!TextUtils.isEmpty(logemail.getText().toString())) {
            mAuth.sendPasswordResetEmail(logemail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Reset link sent to "+ logemail.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_LONG).show();
        }

    }



    public void userLogin() {


        String email=logemail.getText().toString().trim();
        String password=logpassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(login.this,Dashboard.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(login.this, "Failed to login check credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,register.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}