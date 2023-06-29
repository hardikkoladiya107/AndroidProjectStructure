package com.demo.structure.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.structure.application.MyApplication;


/**
 * Created By Hardik Koladiya,Android,UNIKWORK LLP
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) this.getApplication()).applicationComponent().inject(this);

    }

}
