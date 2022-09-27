package com.example.myapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Acticities.detailItemActivity;
import com.example.myapplication.Adapter.exploreAdapter;
import com.example.myapplication.Modal.exploreModal;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSearchBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class searchFragment extends Fragment {
 FragmentSearchBinding binding;
 // search arrayList Initializing
    ArrayList<exploreModal> exploreModalArrayList;
    com.example.myapplication.Adapter.exploreAdapter exploreAdapter;



//FirebaseFirestore Intialisation
    FirebaseFirestore db;

    public searchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false) ;
        binding.progressBar.setVisibility(View.VISIBLE);
      db =  FirebaseFirestore.getInstance();
        initsearch();
        return  binding.getRoot();
    }

    private void initsearch() {
        exploreModalArrayList =new ArrayList<>();
        exploreAdapter = new exploreAdapter(getContext(), exploreModalArrayList);
        binding.rcvExplore.setVisibility(View.GONE);
        binding.rcvExplore.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.rcvExplore.setAdapter(exploreAdapter);


        db.collection("Explore").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document: task.getResult()){
                        exploreModal exploreModal = document.toObject(com.example.myapplication.Modal.exploreModal.class);
                        exploreModalArrayList.add(exploreModal);
                        exploreAdapter.notifyDataSetChanged();
                        binding.progressBar.setVisibility(View.GONE);
                        binding.rcvExplore.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }
}