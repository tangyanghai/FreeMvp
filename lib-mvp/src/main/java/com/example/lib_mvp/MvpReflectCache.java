package com.example.lib_mvp;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/3</p>
 * <p>@for : 反射调用数据的缓存</p>
 * <p>@for : 需重复使用的数据缓存起来</p>
 * <p></p>
 */
public class MvpReflectCache {
    private static MvpReflectCache instance = new MvpReflectCache();

    public static MvpReflectCache getInstance() {
        return instance;
    }

    private MvpReflectCache() {

    }

    /**
     * view层接口缓存
     * 存储一个类及其父类实现的所有{@link IBaseMvpView}接口
     */
    private HashMap<Class<?>, Class<?>[]> viewInterfacesCache;
    /**
     * view层中Presenter字段缓存
     * 存储一个类及其父类所有的{@link IBaseMVPPresenter}类型的字段
     */
    private HashMap<Class<?>, List<Field>> presenterFieldsCache;
    /**
     * presenter层中model字段缓存
     * 存储一个类及其父类所有{@link IBaseMVPModel}类型的字段
     */
    private HashMap<Class<?>, List<Field>> modelFieldsCache;

    public synchronized Class<?>[] getViewCache(Class<?> clz) {
        if (viewInterfacesCache != null) {
            return viewInterfacesCache.get(clz);
        }
        return null;
    }

    public synchronized void addViewCache(Class<?> clz, Class<?>[] interfaces) {
        if (viewInterfacesCache == null) {
            viewInterfacesCache = new HashMap<>();
        }

        viewInterfacesCache.put(clz, interfaces);
    }

    public synchronized List<Field> getPresenterFieldsCache(Class<?> clz) {
        if (presenterFieldsCache != null) {
            return presenterFieldsCache.get(clz);
        }

        return null;
    }

    public synchronized void addPresenterFieldsCache(Class<?> clz, List<Field> fields) {
        if (presenterFieldsCache == null) {
            presenterFieldsCache = new HashMap<>();
        }

        presenterFieldsCache.put(clz, fields);
    }

    public synchronized List<Field> getModelFieldsCache(Class<?> clz) {
        if (modelFieldsCache != null) {
            return modelFieldsCache.get(clz);
        }

        return null;
    }

    public synchronized void addModelFieldsCache(Class<?> clz, List<Field> fields) {
        if (modelFieldsCache == null) {
            modelFieldsCache = new HashMap<>();
        }

        modelFieldsCache.put(clz, fields);
    }

}
