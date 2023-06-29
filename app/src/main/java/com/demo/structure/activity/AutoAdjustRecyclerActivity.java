package com.demo.structure.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.structure.R;
import com.demo.structure.adapter.SwipeToDeleteAdapter;
import com.demo.structure.databinding.ActivityAutoAdjustRecyclerBinding;

import java.util.ArrayList;

public class AutoAdjustRecyclerActivity extends AppCompatActivity {

    ActivityAutoAdjustRecyclerBinding binding;
    SwipeToDeleteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auto_adjust_recycler);
        initViews();
    }

    private void initViews() {
        ArrayList<String> arr = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SwipeToDeleteAdapter(arr);
        binding.recyclerView.setAdapter(adapter);

        binding.additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arr.add("new item");
                if (arr.size() < 4) {
                    View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.swipe_delete_layout, (ViewGroup) binding.getRoot(), false);
                    int height = getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._67sdp) * arr.size();
                    ViewGroup.LayoutParams params = binding.recyclerView.getLayoutParams();
                    params.height = height;
                    //params.height = v.getHeight();
                    binding.recyclerView.setLayoutParams(params);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


}