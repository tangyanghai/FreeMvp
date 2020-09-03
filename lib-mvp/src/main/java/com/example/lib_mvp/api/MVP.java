package com.example.lib_mvp.api;

import com.example.lib_mvp.IBaseMVPPresenter;
import com.example.lib_mvp.IBaseMvpView;
import com.example.lib_mvp.MvpReflectCache;
import com.example.lib_mvp.annotation.MVPPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/8/31</p>
 * <p>@for : </p>
 * <p></p>
 */
public class MVP {
    /**
     * @param view
     */
    public static void createPresenter(Object view) {
        Class<?> clz = view.getClass();
        try {
            List<Field> declaredFields = getPresenterFields(clz);
            for (Field f : declaredFields) {
                Object presenter = initPresenter(view, f, f.getType());
                ((IBaseMVPPresenter) presenter).attachView(view);
                //加入生命周期管理
                if (view instanceof LifecycleOwner) {
                    ((LifecycleOwner) view).getLifecycle().addObserver((LifecycleObserver) presenter);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static List<Field> getPresenterFields(Class<?> clz) {
        List<Field> presenterFields = MvpReflectCache.getInstance().getPresenterFieldsCache(clz);
        if (presenterFields != null) {
            return presenterFields;
        }
        presenterFields = new ArrayList<>();
        Class tempClass = clz;
        MVPPresenter annotation = null;
        while (tempClass != null//当父类为null的时候说明到达了最上层的父类(Object类).
                && IBaseMvpView.class.isAssignableFrom(tempClass)) //只对IBaseMvpView的子类做检查
        {
            List<Field> fields = Arrays.asList(tempClass.getDeclaredFields());
            for (Field field : fields) {
                if (field.getClass().isPrimitive()) {//是基础数据类型
                    continue;
                }
                annotation = field.getAnnotation(MVPPresenter.class);
                if (annotation != null) {
                    presenterFields.add(field);
                }
            }
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        if (presenterFields.size() > 0) {
            MvpReflectCache.getInstance().addPresenterFieldsCache(clz, presenterFields);
        }
        return presenterFields;
    }

    private static Object initPresenter(Object obj, Field f, Class c) throws IllegalAccessException, InstantiationException {
        f.setAccessible(true);
        Object presenter = c.newInstance();
        f.set(obj, presenter);
        return presenter;
    }

    /**
     * 手动清除presenter
     * 实现了LifecycleOwner的View层不需要调用
     */
    public static void destroyPresenter(Object view) {
        List<Field> presenterFields = MvpReflectCache.getInstance().getPresenterFieldsCache(view.getClass());
        if (presenterFields == null) {
            return;
        }

        try {
            for (Field f : presenterFields) {
                ((IBaseMVPPresenter) (f.get(view))).detachView();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
