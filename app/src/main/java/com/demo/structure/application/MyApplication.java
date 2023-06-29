package com.demo.structure.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.structure.injection.component.DaggerApplicationComponent;
import com.demo.structure.injection.component.SubAppComponent;
import com.demo.structure.injection.module.AndroidModule;

import com.demo.structure.injection.component.ApplicationComponent;
import com.demo.structure.service.BackgroundService;
import com.google.android.gms.ads.MobileAds;

import java.util.List;


/**
 * Created By Hardik Koladiya,Android,UNIKWORD LLP
 */

public class MyApplication extends Application {
    ApplicationComponent applicationComponent;
    SubAppComponent subAppComponent;
    Boolean value = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Intent[] POWERMANAGER_INTENTS = {
                new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
                new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
                new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity")),
                new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
                new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")),
                new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
                new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
                new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
                new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
                new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
                new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
                new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")),
                new Intent().setComponent(new ComponentName("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity")),
                new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.MainActivity"))
        };

        for (Intent intent : POWERMANAGER_INTENTS)
            if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                // show dialog to ask user action
                break;
            }

        startService(new Intent(this, BackgroundService.class));

      /*  registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
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
                serviceIntent.putExtra("MakeApiCall", value);
                startService(serviceIntent);
                if (value) {
                    value = false;
                } else {
                    value = true;
                }
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
                Log.e("TAG", "onActivitySaveInstanceState: ");
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.e("TAG", "onActivityDestroyed: ");
            }
        });*/


        /**
         * `RULES` :-
         * 1) @Component defines with @Singleton here creates Application level @Singleton
         * 2) make sure to give @Singleton tag to @Component defined here to make application level @Singleton
         * 3) all rules applicable to @SubComponent
         * 4) is you crate @Component or @SubComponent object in activity is activity wide @Singleton if class declared @Singleton
         * 5) `factory().create()` is method for pass value in component or component throught module
         * 6) do not create @SubComponent @Singleton if parent @Compoentn is @Singleton
         * 7)
         * */
        /**
         * initialise @Component object **/
        applicationComponent = DaggerApplicationComponent.factory().create(this);

        /**
         * `NOTE`:
         * 1) inject this application class in component thats why we use any object of component and module in this class
         * */
        applicationComponent.inject(this);
        /***
         * initialise @SubComponent object */
        subAppComponent = applicationComponent.getSubAppComponent().create(2);


    }

    /**
     * `NOTES`:-
     * get @@@SubComponent from here
     * 1) I can get all dependencies of @Component from this @SubComponent
     * 2) i can't get dependencies of @SubComponent from @Component
     * */
    /**
     * get @@@Component from here
     */
    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

    /**
     * get @@@SubComponent from here
     **/
    public SubAppComponent subAppComponent() {
        return subAppComponent;
    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        if (services != null) {
            for (int i = 0; i < services.size(); i++) {
                if ((serviceClass.getName()).equals(services.get(i).service.getClassName()) && services.get(i).pid != 0) {
                    return true;
                }
            }
        }
        return false;
    }


}
