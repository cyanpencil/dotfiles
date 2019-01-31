// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanCreator.java

package com.owlike.genson.reflect;

import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanCreator

public static class decorate extends BeanCreator
{

            public transient Object create(Object aobj[])
            {
/*  85*/        return constructor.newInstance(aobj);
/*  86*/        aobj;
/*  87*/        throw couldNotCreate(((Exception) (aobj)));
/*  88*/        aobj;
/*  89*/        throw couldNotCreate(((Exception) (aobj)));
/*  90*/        aobj;
/*  91*/        throw couldNotCreate(((Exception) (aobj)));
/*  92*/        aobj;
/*  93*/        throw couldNotCreate(((Exception) (aobj)));
            }

            protected String signature()
            {
/*  99*/        return constructor.toGenericString();
            }

            public int priority()
            {
/* 104*/        return 50;
            }

            public int getModifiers()
            {
/* 109*/        return constructor.getModifiers();
            }

            public volatile int compareTo(Object obj)
            {
/*  69*/        return super.compareTo((BeanCreator)obj);
            }

            protected final Constructor constructor;

            public (Class class1, Constructor constructor1, String as[], Type atype[])
            {
/*  75*/        super(class1, class1, class1, as, atype, constructor1.getParameterAnnotations());
/*  76*/        constructor = constructor1;
/*  77*/        if(!constructor1.isAccessible())
/*  78*/            constructor1.setAccessible(true);
/*  80*/        decorate(constructor1);
            }
}
