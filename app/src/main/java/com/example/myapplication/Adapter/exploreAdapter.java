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
import com.example.myapplication.Modal.exploreModal;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ExloreViewholderBinding;

import java.util.ArrayList;

public class exploreAdapter extends RecyclerView.Adapter<exploreAdapter.ViewHolder> {

    Context context;
    ArrayList<exploreModal> exploreModalArrayList;

    public exploreAdapter(Context context, ArrayList<exploreModal> exploreModalArrayList) {
        this.context = context;
        this.exploreModalArrayList = exploreModalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exlore_viewholder,parent,false);
        return new ViewHolder(view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        exploreModal exploreModal = exploreModalArrayList.get(position) ;
        Glide.with(context)
                .load(exploreModal.getImage())
                .into(holder.binding.image);

        holder.binding.name.setText(exploreModal.getName());
        holder.binding.card.setCardBackgroundColor(Color.parseColor(exploreModal.getColor()));
        holder.binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detailItemActivity.class);
                intent.putExtra("type",exploreModal.getType());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return exploreModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ExloreViewholderBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ExloreViewholderBinding.bind(itemView);
        }
    }
}
