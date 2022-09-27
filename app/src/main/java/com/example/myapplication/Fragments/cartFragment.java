package com.example.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Acticities.detailActivity;
import com.example.myapplication.Adapter.cartAdapter;
import com.example.myapplication.Modal.cartModal;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCartBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class cartFragment extends Fragment {
    FragmentCartBinding binding;
    ArrayList<cartModal> cartModalArrayList;
    com.example.myapplication.Adapter.cartAdapter cartAdapter;

    FirebaseFirestore db;
    FirebaseAuth auth;

    public cartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater,container,false);

        db = FirebaseFirestore.getInstance();
        auth    = FirebaseAuth.getInstance();
        binding.emptyText.setVisibility(View.VISIBLE);

        cartModalArrayList =  new ArrayList<>();
        cartAdapter =  new cartAdapter(getContext(),cartModalArrayList);

        binding.buyBtn.setVisibility(View.GONE);
        binding.rcvCart.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rcvCart.setAdapter(cartAdapter);

        db.collection("AddtoCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").orderBy("Order Time").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                            String documentId = documentSnapshot.getId();
                            cartModal cartModal = documentSnapshot.toObject(com.example.myapplication.Modal.cartModal.class);
                            cartModal.setDocumetID(documentId);
                            cartModalArrayList.add(cartModal);
                            cartAdapter.notifyDataSetChanged();

                            binding.emptyText.setVisibility(View.GONE);
                            binding.buyBtn.setVisibility(View.VISIBLE);
                        }
                    }
                    }
                });


        return binding.getRoot() ;
    }
}