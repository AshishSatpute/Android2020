package com.ash.rxandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {


    List<Pojo> pojos = new ArrayList<>();
    Context context;

    public ItemAdapter(List<Pojo> pojos, Context context) {
        this.pojos = pojos;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final Pojo pojo = pojos.get(i);
        holder.userId.setText(String.valueOf(pojo.getUserId()));
        holder.title.setText(pojo.getTitle());
    }

    @Override
    public int getItemCount() {
        return pojos.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView userId, title;
        View view;

        public Holder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            userId = view.findViewById(R.id.userId);
            title = view.findViewById(R.id.title);

        }
    }
}
