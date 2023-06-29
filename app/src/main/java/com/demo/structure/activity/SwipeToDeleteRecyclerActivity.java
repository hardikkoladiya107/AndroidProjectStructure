package com.demo.structure.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.structure.R;
import com.demo.structure.adapter.GridViewWithDividerAdapter;
import com.demo.structure.adapter.SwipeToDeleteAdapter;
import com.demo.structure.databinding.ActivitySwipeToDeleteRecyclerBinding;

import java.util.ArrayList;

public class SwipeToDeleteRecyclerActivity extends AppCompatActivity {

    ActivitySwipeToDeleteRecyclerBinding binding;
    SwipeToDeleteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_swipe_to_delete_recycler);
        initViews();
    }

    private void initViews() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("kishan");
        arr.add("kishan");
        arr.add("kishan");
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                Toast.makeText(SwipeToDeleteRecyclerActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(SwipeToDeleteRecyclerActivity.this, "on Swiped ", Toast.LENGTH_SHORT).show();
            }
        };
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SwipeToDeleteAdapter(arr);
        binding.recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);


    }
}