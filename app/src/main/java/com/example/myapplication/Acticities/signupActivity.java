package com.example.myapplication.Acticities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapplication.Modal.UserModel;
import com.example.myapplication.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class signupActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ActivitySignupBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase rDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        rDB = FirebaseDatabase.getInstance();


        progressDialog = new ProgressDialog(this);

        binding.signIntxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(signupActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAuth();
            }
        });
    }

    private void performAuth() {
        String email = binding.emailTxt.getText().toString();
        String password = binding.passwordTxt.getText().toString();
        String username = binding.nameTxt.getText().toString();

        if (!email.matches(emailPattern)) {
            binding.emailTxt.setError("Please Enter Email");
            binding.emailTxt.requestFocus();
        } else if (password.length() < 6) {
            binding.passwordTxt.setError("Enter Password More Than 6 character");
            binding.passwordTxt.requestFocus();
        } else {
            progressDialog.setMessage("Please Wait Registration is Going On");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Intent homeIntent = new Intent(signupActivity.this, MainActivity.class);

                        //storing data of user in realtime database

                        UserModel userModel = new UserModel(username, email, password);
                        String id = task.getResult().getUser().getUid();
                        rDB.getReference().child("user").child(id).setValue(userModel);


                        startActivity(homeIntent);
                        signupActivity.this.finish();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(signupActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            Intent homeIntent = new Intent(signupActivity.this, MainActivity.class);
            startActivity(homeIntent);
            signupActivity.this.finish();

        }
    }
}