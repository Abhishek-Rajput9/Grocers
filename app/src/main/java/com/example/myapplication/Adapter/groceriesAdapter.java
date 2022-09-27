package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Acticities.detailItemActivity;
import com.example.myapplication.Fragments.homeFragment;
import com.example.myapplication.Modal.groceriesModal;
import com.example.myapplication.R;
import com.example.myapplication.databinding.GroceryViewholderBinding;

import java.util.ArrayList;

public class groceriesAdapter extends RecyclerView.Adapter<groceriesAdapter.ViewHolder> {
    Context context;
    ArrayList<groceriesModal> groceriesModalArrayList;

    public groceriesAdapter(Context context, ArrayList<groceriesModal> groceriesModalArrayList) {
        this.context = context;
        this.groceriesModalArrayList = groceriesModalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grocery_viewholder,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        groceriesModal groceriesModal = groceriesModalArrayList.get(position);
        Glide.with(context)
                .load(groceriesModal.getImage())
                .into(holder.binding.image);
        holder.binding.name.setText(groceriesModal.getName());
        holder.binding.cardview.setCardBackgroundColor(Color.parseColor(groceriesModal.getColor()));
        holder.binding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(context, detailItemActivity.class );
                intent.putExtra("type",groceriesModal.getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return groceriesModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        GroceryViewholderBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = GroceryViewholderBinding.bind(itemView);
        }
    }
}
