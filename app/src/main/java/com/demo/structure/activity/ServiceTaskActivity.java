package com.demo.structure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityServiceTaskBinding;

public class ServiceTaskActivity extends AppCompatActivity {
    ActivityServiceTaskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service_task);
        initViews();
    }

    private void initViews() {

    }
}