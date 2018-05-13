// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Wrapper.java

package com.owlike.genson;

import com.owlike.genson.reflect.TypeUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

// Referenced classes of package com.owlike.genson:
//            Operations

public abstract class Wrapper
    implements AnnotatedElement
{

            protected Wrapper()
            {
            }

            protected Wrapper(Object obj)
            {
/*  32*/        if(obj == null)
                {
/*  33*/            throw new IllegalArgumentException("Null not allowed!");
                } else
                {
/*  34*/            decorate(obj);
/*  35*/            return;
                }
            }

            public Annotation[] getAnnotations()
            {
/*  38*/        return (Annotation[])Operations.union([Ljava/lang/annotation/Annotation;, new Annotation[][] {
/*  38*/            wrappedElement.getAnnotations(), getClass().getAnnotations()
                });
            }

            public Annotation getAnnotation(Class class1)
            {
                Annotation annotation;
/*  43*/        if((annotation = wrappedElement.getAnnotation(class1)) == null)
/*  44*/            return getClass().getAnnotation(class1);
/*  44*/        else
/*  44*/            return annotation;
            }

            public Annotation[] getDeclaredAnnotations()
            {
/*  48*/        return (Annotation[])Operations.union([Ljava/lang/annotation/Annotation;, new Annotation[][] {
/*  48*/            wrappedElement.getDeclaredAnnotations(), getClass().getDeclaredAnnotations()
                });
            }

            public boolean isAnnotationPresent(Class class1)
            {
/*  53*/        return wrappedElement.isAnnotationPresent(class1) || getClass().isAnnotationPresent(class1);
            }

            protected void decorate(Object obj)
            {
/*  59*/        if(wrappedElement != null)
/*  60*/            throw new IllegalStateException("An object is already wrapped!");
/*  61*/        if(obj instanceof AnnotatedElement)
/*  62*/            wrappedElement = (AnnotatedElement)obj;
/*  64*/        else
/*  64*/            wrappedElement = obj.getClass();
/*  65*/        wrapped = obj;
            }

            public Object unwrap()
            {
/*  69*/        return wrapped;
            }

            public static AnnotatedElement toAnnotatedElement(Object obj)
            {
/*  83*/        if(obj == null)
/*  84*/            return null;
/*  85*/        if(isWrapped(obj))
/*  86*/            return (AnnotatedElement)obj;
/*  88*/        else
/*  88*/            return obj.getClass();
            }

            public static boolean isWrapped(Object obj)
            {
/*  92*/        return obj instanceof Wrapper;
            }

            public static boolean isOfType(Object obj, Class class1)
            {
/*  99*/        return TypeUtil.match(obj.getClass(), class1, false) || isWrapped(obj) && isOfType(((Wrapper)obj).unwrap(), class1);
            }

            private AnnotatedElement wrappedElement;
            protected Object wrapped;
}
