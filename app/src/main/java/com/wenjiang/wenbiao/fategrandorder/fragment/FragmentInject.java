package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by wenbiao on 2017/10/27.
 */

public final class FragmentInject {

    private FragmentInject() {
    }

    public static FragmentInject newInstance() {
        return new FragmentInject();
    }

    public void injectValue(Fragment fragment) {
        Bundle data = fragment.getArguments();
        Field[] fields = fragment.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Args.class)) {
                field.setAccessible(true);
                try {
                    field.set(fragment, data.get(field.getName()));
                } catch (IllegalAccessException e) {

                }
            }
        }
    }

    public void injectView(View rootView, Fragment fragment) {
        Field[] fields = fragment.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindView.class)) {
                field.setAccessible(true);
                try {
                    BindView bindView = field.getAnnotation(BindView.class);
                    View view = rootView.findViewById(bindView.id());
                    field.set(fragment, view);
                } catch (IllegalAccessException e) {

                }
            }
        }
    }
}
