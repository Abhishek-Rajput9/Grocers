package com.example.myapplication.Acticities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.myapplication.Adapter.categoryAdapter;
import com.example.myapplication.Adapter.detailItemAdapter;
import com.example.myapplication.Modal.categoryModal;
import com.example.myapplication.Modal.detailItemModal;
import com.example.myapplication.databinding.ActivityDetailItemBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class detailItemActivity extends AppCompatActivity {
ActivityDetailItemBinding binding;
    FirebaseFirestore db;
    ArrayList<detailItemModal> detailItemModalArrayList;
    com.example.myapplication.Adapter.detailItemAdapter detailItemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailItemBinding.inflate(getLayoutInflater());

        db = FirebaseFirestore.getInstance();
        setContentView(binding.getRoot());
        initItem();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Products");

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }

    private void initItem() {
        detailItemModalArrayList = new ArrayList<>();
        detailItemAdapter = new detailItemAdapter(this, detailItemModalArrayList);
        binding.rcvItem.setLayoutManager(new GridLayoutManager(this,2));
        binding.rcvItem.setAdapter(detailItemAdapter);
        String type = getIntent().getStringExtra("type");

        if (type!= null && type.equalsIgnoreCase("fruit and vegetable")){
            db.collection("products").whereEqualTo("type", "fruit and vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()){
                            detailItemModal detailItemModal = document.toObject(com.example.myapplication.Modal.detailItemModal.class);
                            detailItemModalArrayList.add(detailItemModal);
                            detailItemAdapter.notifyDataSetChanged();

                        }
                    }
                }
            });



        }

        if (type!= null && type.equalsIgnoreCase("meat and fish")){
            db.collection("products").whereEqualTo("type", "meat and fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()){
                            detailItemModal detailItemModal = document.toObject(com.example.myapplication.Modal.detailItemModal.class);
                            detailItemModalArrayList.add(detailItemModal);
                            detailItemAdapter.notifyDataSetChanged();

                        }
                    }
                }
            });



        }


        if (type!= null && type.equalsIgnoreCase("oil and ghee")){
            db.collection("products").whereEqualTo("type", "oil and ghee").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()){
                            detailItemModal detailItemModal = document.toObject(com.example.myapplication.Modal.detailItemModal.class);
                            detailItemModalArrayList.add(detailItemModal);
                            detailItemAdapter.notifyDataSetChanged();

                        }
                    }
                }
            });



        }

        if (type!= null && type.equalsIgnoreCase("bakery")){
            db.collection("products").whereEqualTo("type", "bakery").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()){
                            detailItemModal detailItemModal = document.toObject(com.example.myapplication.Modal.detailItemModal.class);
                            detailItemModalArrayList.add(detailItemModal);
                            detailItemAdapter.notifyDataSetChanged();

                        }
                    }
                }
            });



        }




    }
}
