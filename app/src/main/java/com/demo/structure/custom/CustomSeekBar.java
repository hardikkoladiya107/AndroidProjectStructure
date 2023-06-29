package com.demo.structure.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CustomSeekBar extends View implements View.OnDragListener, View.OnTouchListener {

    public CustomSeekBar(Context context) {
        super(context);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("TAG", "widthMeasureSpec: " + widthMeasureSpec);
        Log.e("TAG", "heightMeasureSpec: " + heightMeasureSpec);
    }

}
