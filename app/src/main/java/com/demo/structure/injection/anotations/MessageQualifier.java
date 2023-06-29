package com.demo.structure.injection.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created By Hardik Koladiya,Android,UNIKWORK LLP
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageQualifier {
    /**
     * instead using @named anotations we can create qualifier(anotations) to use both side
     * */
}
