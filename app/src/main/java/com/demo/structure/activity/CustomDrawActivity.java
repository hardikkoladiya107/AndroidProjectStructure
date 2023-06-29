package com.demo.structure.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityCustomDrawBinding;

public class CustomDrawActivity extends AppCompatActivity {

    ActivityCustomDrawBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_draw);
        initViews();
    }

    private void initViews() {


    }

}