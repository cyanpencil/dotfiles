// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanCreator.java

package com.owlike.genson.reflect;

import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanCreator

public static class _creator extends BeanCreator
{

            public transient Object create(Object aobj[])
            {
/* 132*/        return ofClass.cast(_creator.invoke(null, aobj));
/* 133*/        aobj;
/* 134*/        throw couldNotCreate(((Exception) (aobj)));
/* 135*/        aobj;
/* 136*/        throw couldNotCreate(((Exception) (aobj)));
/* 137*/        aobj;
/* 138*/        throw couldNotCreate(((Exception) (aobj)));
            }

            protected String signature()
            {
/* 144*/        return _creator.toGenericString();
            }

            public int priority()
            {
/* 149*/        return 100;
            }

            public int getModifiers()
            {
/* 154*/        return _creator.getModifiers();
            }

            public volatile int compareTo(Object obj)
            {
/* 113*/        return super.compareTo((BeanCreator)obj);
            }

            protected final Method _creator;

            public (Method method, String as[], Type atype[], Class class1)
            {
/* 118*/        super(method.getReturnType(), method.getDeclaringClass(), class1, as, atype, method.getParameterAnnotations());
/* 120*/        if(!Modifier.isStatic(method.getModifiers()))
/* 121*/            throw new IllegalStateException("Only static methods can be used as creators!");
/* 122*/        _creator = method;
/* 123*/        if(!_creator.isAccessible())
/* 124*/            _creator.setAccessible(true);
/* 126*/        decorate(_creator);
            }
}
