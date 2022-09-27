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
import com.example.myapplication.Modal.sellingModal;
import com.example.myapplication.R;
import com.example.myapplication.databinding.SelingViewholderBinding;

import java.util.ArrayList;

public class sellingAdapter extends RecyclerView.Adapter<sellingAdapter.ViewHolder> {

    Context context;
    ArrayList<sellingModal>sellingModalArrayList;

    public sellingAdapter(Context context, ArrayList<sellingModal> sellingModalArrayList) {
        this.context = context;
        this.sellingModalArrayList = sellingModalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seling_viewholder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    sellingModal sellingModal = sellingModalArrayList.get(position);
        Glide.with(context)
                .load(sellingModal.getImage())
                .into(holder.binding.image);
        holder.binding.name.setText(sellingModal.getName());
        holder.binding.price.setText("₹  "+ sellingModal.getPrice()+"/kg");
        holder.binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detailActivity.class);
                intent.putExtra("name", sellingModal.getName());
                intent.putExtra("image", sellingModal.getImage());
                intent.putExtra("description",sellingModal.getDescription());
                intent.putExtra("price",sellingModal.getPrice());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sellingModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SelingViewholderBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SelingViewholderBinding.bind(itemView);
        }
    }
}
