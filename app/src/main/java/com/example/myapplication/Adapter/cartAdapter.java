package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Modal.cartModal;
import com.example.myapplication.R;
import com.example.myapplication.databinding.CartViewholderBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder> {
    FirebaseFirestore db;
    FirebaseAuth auth;

    Context context;
    ArrayList<cartModal>     cartModalArrayList;

    public cartAdapter(Context context, ArrayList<cartModal> cartModalArrayList) {
        this.context = context;
        this.cartModalArrayList = cartModalArrayList;
        db =FirebaseFirestore.getInstance();
        auth    = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_viewholder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    cartModal cartModal = cartModalArrayList.get(position);

    Glide.with(context).load(cartModal.getProductImage()).into(holder.binding.image);
    holder.binding.name.setText(cartModal.getProductName());
    holder.binding.price.setText("₹"+ cartModal.getTotalPrice());
    holder.binding.quantity.setText("Item : "+cartModal.getTotalQuantity());
    holder.binding.plus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            db.collection("AddtoCart").document(auth.getCurrentUser().getUid())
                    .collection("CurrentUser").document(cartModal.getDocumetID()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            cartModalArrayList.remove(cartModalArrayList.get(position));
                            notifyDataSetChanged();
                            holder.binding.plus.setVisibility(View.GONE);
                        }
                    });
        }
    });

    }

    @Override
    public int getItemCount() {
        return cartModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CartViewholderBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartViewholderBinding.bind(itemView);
        }
    }
}
