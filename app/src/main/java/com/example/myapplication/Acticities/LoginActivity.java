package com.example.myapplication.Acticities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
FirebaseAuth mAuth;
String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
FirebaseUser mUser;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.loginIntxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent =  new Intent(LoginActivity.this, signupActivity.class);
                startActivity(signupIntent);
                finish();
            }
        });
         String email = binding.emailTxt.getText().toString();
         String password = binding.passwordTxt.getText().toString();
         progressDialog = new ProgressDialog(this);

         mAuth = FirebaseAuth.getInstance();
         mUser = mAuth.getCurrentUser();


         binding.loginBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 performlogin();

             }
         });


    }

    private void performlogin() {
        String email = binding.emailTxt.getText().toString();
        String password = binding.passwordTxt.getText().toString();
        if (!email.matches(emailPattern)){
            binding.emailTxt.setError("Enter Valid Email");
        }else if (password.length()<6||password.isEmpty()){
            binding.passwordTxt.setError("Wrong Password");
        }else {
            progressDialog.setMessage("Please Wait While Login ....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            mAuth.signInWithEmailAndPassword(email ,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        Intent login = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity (login);
                       finish();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

    }

    @Override
    protected void onStart() {
            super.onStart();
            if (mAuth.getCurrentUser()!=null){
                Intent login = new Intent(LoginActivity.this, MainActivity.class);
                startActivity (login);
                finish();

            }
    }
}