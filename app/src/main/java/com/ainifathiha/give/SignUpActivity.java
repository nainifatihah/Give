package com.ainifathiha.give;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    //private EditText etUsername;
    //private EditText etFullname;
    //private EditText etLocation;
    private EditText etEmail;
    private EditText etPassword;
    private Button bSignUp;
    private FirebaseAuth firebaseAuth2;
    private ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth2 = FirebaseAuth.getInstance();
        progressDialog2 = new ProgressDialog(this);

        //etUsername = (EditText) findViewById(R.id.etUsername);
        //etFullname = (EditText) findViewById(R.id.etFullname);
        //etLocation = (EditText) findViewById(R.id.etLocation);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bSignUp = (Button) findViewById(R.id.bSignUp);

        bSignUp.setOnClickListener(this);
    }

    private void signUp() {
        String email2 = etEmail.getText().toString().trim();
        String password2 = etPassword.getText().toString().trim();

        //checking if et empty
        if(TextUtils.isEmpty(email2)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password2)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog2.setMessage("Registering user ...");
        progressDialog2.show();

        firebaseAuth2.createUserWithEmailAndPassword(email2, password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressDialog.dismiss();

                        if(task.isSuccessful()){
                            //finish();
                            //this is how to call activity if in OnCompleteListener
                            Toast.makeText(SignUpActivity.this, "Registered Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Cannot Register. Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if(v == bSignUp){
            signUp();
        }
    }
}