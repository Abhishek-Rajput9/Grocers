package com.example.myapplication.Acticities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.Fragments.cartFragment;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDetailBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class detailActivity extends AppCompatActivity {
ActivityDetailBinding binding;
        int totalQuantity = 1;
        double totalPrice = 0;
            FirebaseFirestore db;
            FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       db = FirebaseFirestore.getInstance();
        auth     = FirebaseAuth.getInstance();
        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        String description =  getIntent().getStringExtra("description");
        double price = getIntent().getDoubleExtra("price",0);
        Glide.with(this)
                        .load(image)
                                .into(binding.image);
        binding.name.setText(name);
        binding.description.setText(description);
        binding.price.setText("₹"+price+"");
        totalPrice = price*totalQuantity;

    binding.plus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (totalQuantity <20){
                totalQuantity++;
                binding.quantity.setText(String.valueOf(totalQuantity));
                totalPrice = price*totalQuantity;
            }
        }
    });

    binding.minus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (totalQuantity>1){
                totalQuantity--;
                binding.quantity.setText(String.valueOf(totalQuantity));
                totalPrice = price*totalQuantity;
            }
        }
    });

    binding.addtocartBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String saveCurrentTime,saveCurrentDate;
            Calendar calendar= Calendar.getInstance();

                @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());
                @SuppressLint("SimpleDateFormat") SimpleDateFormat currentTime =  new SimpleDateFormat("HH, mm:ss a ");
                saveCurrentTime = currentTime.format(calendar.getTime());

                final HashMap<String,Object> cartMap = new HashMap<>();
                cartMap.put("productName",name);
                cartMap.put("productImage",image);
                cartMap.put("productPrice",price);
                cartMap.put("Oder Date", saveCurrentDate);
                cartMap.put("Order Time", saveCurrentTime);
                cartMap.put("totalQuantity", binding.quantity.getText().toString());
                cartMap.put("totalPrice", totalPrice);

            db.collection("AddtoCart").document(auth.getCurrentUser().getUid())
                    .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(detailActivity.this,"Your Product Added To cart ",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

        }
    });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }
}