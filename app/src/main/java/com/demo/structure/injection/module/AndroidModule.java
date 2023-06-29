package com.demo.structure.injection.module;

import android.content.Context;

import com.demo.structure.application.MyApplication;
import com.demo.structure.injection.anotations.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created By Hardik Koladiya,Android,UNIKWORD LLP
 */

/**
 * this module is created for get application context
 */
@Module
public class AndroidModule {

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext(MyApplication application) {
        return application;
    }
}
