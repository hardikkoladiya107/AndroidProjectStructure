package com.demo.structure.injection.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created By Hardik Koladiya,Android,UNIKWORK LLP
 */

@Subcomponent
public interface SubAppComponent {

    @Subcomponent.Factory
    interface Factory{
        SubAppComponent create(@BindsInstance int s);
    }
}
