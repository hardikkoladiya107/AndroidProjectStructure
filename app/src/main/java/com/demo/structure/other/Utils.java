package com.demo.structure.other;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import androidx.fragment.app.FragmentActivity;

public class Utils {

    public static int getScreenWidth(FragmentActivity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(FragmentActivity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }



}
