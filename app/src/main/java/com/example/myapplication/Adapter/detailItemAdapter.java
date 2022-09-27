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
import com.example.myapplication.Modal.detailItemModal;
import com.example.myapplication.R;
import com.example.myapplication.databinding.DetailitemViewholderBinding;

import java.util.ArrayList;

public class detailItemAdapter extends RecyclerView.Adapter<detailItemAdapter.ViewHolder> {

    Context context;
    ArrayList<detailItemModal> detailItemModalArrayList;

    public detailItemAdapter(Context context, ArrayList<detailItemModal> detailItemModalArrayList) {
        this.context = context;
        this.detailItemModalArrayList = detailItemModalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detailitem_viewholder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        detailItemModal detailItemModal = detailItemModalArrayList.get(position);
        Glide.with(context)
                .load(detailItemModal.getImage())
                .into(holder.binding.image);

        holder.binding.name.setText(detailItemModal.getName());
        holder.binding.price.setText("₹ "+detailItemModal.getPrice()+"/kg");
        if (detailItemModal.getType().equals("oil and ghee")){
            holder.binding.price.setText("₹  "+detailItemModal.getPrice()+"/L");
        }
        else if (detailItemModal.getType().equals("bakery")){
            holder.binding.price.setText("₹ "+detailItemModal.getPrice()+"/pc");
        }

        holder.binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detailActivity.class);
                intent.putExtra("name",detailItemModal.getName());
                intent.putExtra("image",detailItemModal.getImage());
                intent.putExtra("price",detailItemModal.getPrice());
                intent.putExtra("description",detailItemModal.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailItemModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DetailitemViewholderBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DetailitemViewholderBinding.bind(itemView);
        }
    }
}
