package com.example.myapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Adapter.categoryAdapter;
import com.example.myapplication.Adapter.groceriesAdapter;
import com.example.myapplication.Adapter.sellingAdapter;
import com.example.myapplication.Modal.categoryModal;
import com.example.myapplication.Modal.groceriesModal;
import com.example.myapplication.Modal.sellingModal;
import com.example.myapplication.Modal.sliderModal;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.slider.Slider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment {

    FirebaseFirestore db ;
    FragmentHomeBinding binding;

    ArrayList<categoryModal> categoryModalArrayList;
    categoryAdapter categoryAdapter;


    ArrayList<sellingModal> sellingModalArrayList;
    com.example.myapplication.Adapter.sellingAdapter sellingAdapter;

    ArrayList<groceriesModal> groceriesModalArrayList;
    com.example.myapplication.Adapter.groceriesAdapter groceriesAdapter;


    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
            binding.progressBar.setVisibility(View.VISIBLE);


        db = FirebaseFirestore.getInstance();
        initcategory();
        initselling();
        initgroceries();

 binding.carousel.addData(new CarouselItem("https://img.freepik.com/premium-psd/super-grocery-sale-web-banner_120329-268.jpg?size=626&ext=jpg&ga=GA1.2.1605295990.1660994075","Grocery Banner"));
  binding.carousel.addData(new CarouselItem("https://firebasestorage.googleapis.com/v0/b/grocers-27dd6.appspot.com/o/slider3.jpg?alt=media&token=7ca47a15-451b-4abd-a7a0-9c9d3b71f56a"));
    binding.carousel.addData(new CarouselItem("https://firebasestorage.googleapis.com/v0/b/grocers-27dd6.appspot.com/o/slider2.jpg?alt=media&token=f8e5e1a6-f273-43f1-973b-dfd855744a66"));
        return binding.getRoot();

    }

    private void initgroceries() {
        groceriesModalArrayList = new ArrayList<>();
        groceriesAdapter = new groceriesAdapter(getContext(), groceriesModalArrayList);
        binding.rcvGrocery.setVisibility(View.GONE);
        binding.rcvGrocery.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rcvGrocery.setAdapter(groceriesAdapter);
        db.collection("Groceries").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        groceriesModal groceriesModal = document.toObject(com.example.myapplication.Modal.groceriesModal.class);
                        groceriesModalArrayList.add(groceriesModal);
                        groceriesAdapter.notifyDataSetChanged();
                        binding.progressBar.setVisibility(View.GONE);
                        binding.rcvGrocery.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }


    private void initselling() {
        sellingModalArrayList = new ArrayList<>();
        sellingAdapter = new sellingAdapter(getActivity(),sellingModalArrayList);
        binding.rcvSelling.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.rcvSelling.setAdapter(sellingAdapter);

        db.collection("Best Selling").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document :task.getResult()){
                        sellingModal sellingModal = document.toObject(com.example.myapplication.Modal.sellingModal.class);
                        sellingModalArrayList.add(sellingModal);
                        sellingAdapter.notifyDataSetChanged();

                    }
                }else{
                    Toast.makeText(getContext(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initcategory() {
        categoryModalArrayList = new ArrayList<>();
        categoryAdapter = new categoryAdapter(getActivity(), categoryModalArrayList);
        binding.rcvCategory.setAdapter(categoryAdapter);
        binding.rcvCategory.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


        db.collection("category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                categoryModal  categoryModal = document.toObject(categoryModal.class);
                                categoryModalArrayList.add(categoryModal);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}