package com.demo.structure.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.structure.databinding.SampleDatabindLayoutBinding;

import java.util.ArrayList;

public class SampleDatabindAdapter extends RecyclerView.Adapter<SampleDatabindAdapter.ViewHolder> {
    ArrayList<String> arrayList;
    OnClickHandler handler;
    public SampleDatabindAdapter(ArrayList<String> arrayList) {
        this.arrayList = new ArrayList<>();
        this.arrayList.add("");
    }

    @NonNull
    @Override
    public SampleDatabindAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SampleDatabindLayoutBinding binding = SampleDatabindLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleDatabindAdapter.ViewHolder holder, int position) {
        holder.setData(arrayList.get(position),handler,position);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.onClick(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SampleDatabindLayoutBinding binding;
        public ViewHolder(@NonNull SampleDatabindLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(String s, OnClickHandler handler, int position) {

        }
    }

    interface OnClickHandler{
        void onClick(String s);
    }

}
