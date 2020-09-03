package com.example.lib_mvp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/8/27</p>
 * <p>@for : 用于在View层示例中标记Presenter字段</p>
 * <p>@for : 有此注解的filed,才会被拿来用</p>
 * <p>@for : 必须调用{@link com.example.lib_mvp.api.MVP#createPresenter(Object)}进行初始化</p>
 * <p></p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MVPPresenter {

}
