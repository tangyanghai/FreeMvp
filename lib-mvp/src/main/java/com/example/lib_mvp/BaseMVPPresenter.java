package com.example.lib_mvp;

import com.example.lib_mvp.annotation.MVPModel;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/8/27</p>
 * <p>@for : </p>
 * <p></p>
 */
public class BaseMVPPresenter<MvpView extends IBaseMvpView> implements IBaseMVPPresenter<MvpView> {
    /**
     * view代理对象
     */
    MvpView viewProxy;
    /**
     * view代理对象InvocationHandler
     * 实际持有view
     * 防止{@link #getView()}造成空指针
     */
    private ViewInvocationHandler vHandler;
    private List<IBaseMVPModel> models;

    @Override
    public boolean isViewAttached() {
        if (vHandler == null) {
            return false;
        }
        return vHandler.isViewAttached();
    }

    @Override
    public MvpView getView() {
        return viewProxy;
    }

    @Override
    public void attachView(Object v) {
        vHandler = new ViewInvocationHandler((MvpView) v);
        viewProxy = (MvpView) Proxy.newProxyInstance(v.getClass().getClassLoader(), getInterfaces(v), vHandler);
        createModel();
    }

    private void createModel() {
        List<Field> fields = getModelFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                Object model = f.getType().newInstance();
                f.set(this, model);
                addModel((IBaseMVPModel) model);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private List<Field> getModelFields() {
        Class<?> clz = getClass();
        List<Field> fields = MvpReflectCache.getInstance().getModelFieldsCache(clz);
        if (fields != null) {
            return fields;
        }

        fields = new ArrayList<>();
        for (Field f : clz.getDeclaredFields()) {
            Class c = f.getType();
            if (c.isPrimitive()) {//是基础数据类型
                continue;
            }
            MVPModel annotation = f.getAnnotation(MVPModel.class);
            if (annotation != null) {
                fields.add(f);
            }
        }

        if (fields.size() > 0) {
            MvpReflectCache.getInstance().addModelFieldsCache(clz, fields);
        }
        return fields;
    }

    private synchronized void addModel(IBaseMVPModel model) {
        if (models == null) {
            models = new ArrayList<>();
        }
        models.add(model);
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        //清除view
        detachView();
    }

    @Override
    public void detachView() {
        //清除model
        if (models != null) {
            for (IBaseMVPModel model : models) {
                model.onDestroy();
            }
            models.clear();
            models = null;
        }

        //清除view
        if (vHandler != null && vHandler.isViewAttached()) {
            vHandler.detachView();
            vHandler = null;
        }
    }

    /**
     * @param v View实例
     * @return View所有实现的IBaseMvpView接口
     */
    Class<?>[] getInterfaces(Object v) {
        Class<?> temp = v.getClass();
        Class<?>[] cacheInterfaces = MvpReflectCache.getInstance().getViewCache(temp);
        if (cacheInterfaces != null) {
            return cacheInterfaces;
        }

        List<Class<?>> clzList = new ArrayList<>();

        while (temp != null && IBaseMvpView.class.isAssignableFrom(temp)) {
            Class<?>[] interfaces = temp.getInterfaces();
            clzList.addAll(Arrays.asList(interfaces));
            temp = temp.getSuperclass();
        }

        cacheInterfaces = new Class[clzList.size()];
        for (int i = 0; i < clzList.size(); i++) {
            cacheInterfaces[i] = clzList.get(i);
        }

        MvpReflectCache.getInstance().addViewCache(v.getClass(), cacheInterfaces);
        return cacheInterfaces;
    }

    /**
     * MvpView实际持有类
     */
    private class ViewInvocationHandler implements InvocationHandler {
        WeakReference<MvpView> view;

        ViewInvocationHandler(MvpView v) {
            this.view = new WeakReference<>(v);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果V层没被销毁, 执行V层的方法.
            if (isViewAttached()) {
                return method.invoke(view.get(), args);
            }
            //P层不需要关注V层的返回值
            return null;
        }

        public boolean isViewAttached() {
            return view != null && view.get() != null;
        }

        void detachView() {
            if (isViewAttached()) {
                view.clear();
            }
            if (view != null) {
                view = null;
            }
        }
    }
}
