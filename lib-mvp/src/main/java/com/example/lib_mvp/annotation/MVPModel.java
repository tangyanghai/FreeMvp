package com.example.lib_mvp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/2</p>
 * <p>@for : model标识</p>
 * <p>@for : 用于在presenter中标记model字段</p>
 * <p></p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MVPModel {

}
