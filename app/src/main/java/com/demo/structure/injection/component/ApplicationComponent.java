package com.demo.structure.injection.component;


import com.demo.structure.activity.SplashActivity;
import com.demo.structure.application.MyApplication;
import com.demo.structure.injection.module.AndroidModule;
import com.demo.structure.injection.module.DataBaseModule;
import com.demo.structure.injection.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


/**
 * Created By Hardik Koladiya,Android,UNIKWORD LLP
 */

@Singleton
@Component(modules = {AndroidModule.class, NetworkModule.class, DataBaseModule.class})
public interface ApplicationComponent {

    void inject(MyApplication application);
    void inject(SplashActivity splash);


    SubAppComponent.Factory getSubAppComponent();
    @Component.Factory
    interface Factory{
        ApplicationComponent create(@BindsInstance MyApplication application);
    }

}
