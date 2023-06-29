package com.demo.structure.application;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.structure.service.BackgroundService;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, BackgroundService.class));
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                Log.e("TAG", "onActivityCreated: ");
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Log.e("TAG", "onActivityStarted: ");
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Log.e("TAG", "onActivityResumed: ");
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Log.e("TAG", "onActivityPaused: ");
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Log.e("TAG", "onActivityStopped: ");
                Intent serviceIntent = new Intent(getApplicationContext(), BackgroundService.class);
                serviceIntent.putExtra("MakeApiCall", true);
                startService(serviceIntent);
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
                Log.e("TAG", "onActivitySaveInstanceState: ");
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.e("TAG", "onActivityDestroyed: ");
            }
        });
    }
}
