package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Acticities.detailActivity;
import com.example.myapplication.Modal.categoryModal;
import com.example.myapplication.Modal.detailItemModal;
import com.example.myapplication.Modal.sellingModal;
import com.example.myapplication.R;
import com.example.myapplication.databinding.CategoryViewholderBinding;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {
    Context context;
    ArrayList<categoryModal> categoryModalArrayList;

    public categoryAdapter(Context context, ArrayList<categoryModal> categoryModalArrayList) {
        this.context = context;
        this.categoryModalArrayList = categoryModalArrayList;
    }

    @NonNull
    @Override
    public categoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_viewholder,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.ViewHolder holder, int position) {
        categoryModal categoryModal = categoryModalArrayList.get(position);
        Glide.with(context)
                .load(categoryModal.getImage())
                .into(holder.binding.image);
        holder.binding.name.setText(categoryModal.getName());
        holder.binding.price.setText("₹" + categoryModal.getPrice() + "/kg");
        holder.binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detailActivity.class);
                intent.putExtra("name", categoryModal.getName());
                intent.putExtra("image", categoryModal.getImage());
                intent.putExtra("description",categoryModal.getDescription());
                intent.putExtra("price",categoryModal.getPrice());

                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return categoryModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CategoryViewholderBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CategoryViewholderBinding.bind(itemView);
        }
    }
}
