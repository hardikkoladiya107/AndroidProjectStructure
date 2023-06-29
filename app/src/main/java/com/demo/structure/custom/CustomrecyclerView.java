package com.demo.structure.custom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class CustomrecyclerView extends RecyclerView {
    private Context context;

    public CustomrecyclerView(@NonNull Context context) {
        super(context);
        this.context = context;
        initViews();
    }

    public CustomrecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initViews();
    }

    public CustomrecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initViews();
    }

    private void initViews() {

    }


}
