package com.demo.structure.injection.anotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

/**
 * Created By Hardik Koladiya,Android,UNIKWORD LLP
 */

@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}
