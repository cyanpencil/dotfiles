// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanCreator.java

package com.owlike.genson.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyMutator, BeanCreator

public static class doThrowMutateException extends PropertyMutator
{

            public int getIndex()
            {
/* 180*/        return index;
            }

            public Annotation[] getAnnotations()
            {
/* 184*/        return annotations;
            }

            public int priority()
            {
/* 189*/        return -1000;
            }

            public String signature()
            {
/* 194*/        return (new StringBuilder(type.toString())).append(' ').append(name).append(" from ").append(creator.signature()).toString();
            }

            public int getModifiers()
            {
/* 200*/        return creator.getModifiers();
            }

            public void mutate(Object obj, Object obj1)
            {
/* 205*/        if(doThrowMutateException)
/* 206*/            throw new IllegalStateException((new StringBuilder("Method mutate should not be called on a mutator of type ")).append(getClass().getName()).append(", this property exists only as constructor parameter!").toString());
/* 211*/        else
/* 211*/            return;
            }

            protected final int index;
            protected final Annotation annotations[];
            protected final BeanCreator creator;
            protected final boolean doThrowMutateException;

            protected (String s, Type type, int i, Annotation aannotation[], Class class1, Class class2, BeanCreator beancreator)
            {
/* 166*/        this(s, type, i, aannotation, class1, class2, beancreator, false);
            }

            protected <init>(String s, Type type, int i, Annotation aannotation[], Class class1, Class class2, BeanCreator beancreator, 
                    boolean flag)
            {
/* 172*/        super(s, type, class1, class2, aannotation, 0);
/* 173*/        index = i;
/* 174*/        annotations = aannotation;
/* 175*/        creator = beancreator;
/* 176*/        doThrowMutateException = flag;
            }
}
