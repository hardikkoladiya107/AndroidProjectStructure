package com.demo.structure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityDragAndDropBinding;
import com.demo.structure.other.Utils;

public class DragAndDropActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {


    ActivityDragAndDropBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drag_and_drop);
        initializeDragDrop();
    }

    private void initializeDragDrop() {

        binding.viewsample.setOnTouchListener(this);
        binding.viewsample.setOnDragListener(this);
        Log.e("TAG", "getScreenHeight: "+ Utils.getScreenHeight(this));
        Log.e("TAG", "getScreenWidth: "+ Utils.getScreenWidth(this));
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            MyDragShadowBuilder builder = new MyDragShadowBuilder(view);
            view.startDrag(null, builder, view, 10);
            return true;
        }
        return false;
    }

    @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_ENDED:
                Log.e("x", "ACTION_DRAG_ENDED --->X: " + dragEvent.getX());
                Log.e("x", "ACTION_DRAG_ENDED----->Y: " + dragEvent.getY());
                view.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.e("x", "ACTION_DRAG_EXITED: " + dragEvent.getX());
                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.e("x", "ACTION_DRAG_ENTERED: " + dragEvent.getX());
                return true;
            case DragEvent.ACTION_DRAG_STARTED:
                Log.e("x", "ACTION_DRAG_STARTED: " + dragEvent.getX());
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                Log.e("x", "ACTION_DRAG_LOCATION: " + dragEvent.getX());
                Log.e("y", "ACTION_DRAG_LOCATION: " + dragEvent.getX());
                return true;
            case DragEvent.ACTION_DROP:
                Log.e("x", "ACTION_DROP: " + dragEvent.getX());
                binding.viewsample.setX(dragEvent.getX());
                binding.viewsample.setY(dragEvent.getY());
                return true;
        }
        return false;
    }


    private class MyDragShadowBuilder extends View.DragShadowBuilder {

        private Drawable shadow;

        public MyDragShadowBuilder(View view) {
            super(view);
            shadow = new ColorDrawable(Color.BLACK);
        }

        @Override
        public void onProvideShadowMetrics(Point size, Point touch) {
            /**
             * first this method calls
             * */
            int width, height;
            // Set the width of the shadow to half the width of the original View.
            width = getView().getWidth();

            // Set the height of the shadow to half the height of the original View.
            height = getView().getHeight();

            shadow.setBounds(0, 0, width, height);

            size.set(width, height);

            touch.set(width / 2, height / 2);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            /**
             * second this method call
             * **/
            shadow.draw(canvas);
        }

    }


}