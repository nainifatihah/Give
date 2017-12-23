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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail;
    private EditText etPassword;
    private TextView tvSignUp;
    private Button bSignIn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        //check if user already log in
        /*if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(this, HomeActivity.class));
        }*/

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        bSignIn = (Button) findViewById(R.id.bSignIn);

        progressDialog = new ProgressDialog(this);

        tvSignUp.setOnClickListener(this);
        bSignIn.setOnClickListener(this);
    }


    private void signIn() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        //checking if et empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Logging in ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            finish();
                            //this is how to call activity if in OnCompleteListener
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Toast.makeText(LogInActivity.this, "Cannot Log In", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if(v == tvSignUp){
            finish();
            startActivity(new Intent(this, SignUpActivity.class));
        }
        if(v == bSignIn){
            signIn();
            //if authenticate, allow this:
            //startActivity(new Intent(this, HomeActivity.class));
        }

    }
}
