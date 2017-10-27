package com.wenjiang;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by wenbiao on 2017/10/27.
 */

@SupportedAnnotationTypes("com.wenjiang.wenbiao.fategrandorder.fragment.Args")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class FragmentArgsProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Class clazz = null;
        try {
            clazz = Class.forName("com.wenjiang.wenbiao.fategrandorder.fragment.BaseFragment");
        } catch (ClassNotFoundException e) {
            System.out.print(e.toString());
        }

        if(clazz != null) {
            for (Object element : roundEnvironment.getElementsAnnotatedWith(clazz)) {

            }
        }
        return true;
    }
}
