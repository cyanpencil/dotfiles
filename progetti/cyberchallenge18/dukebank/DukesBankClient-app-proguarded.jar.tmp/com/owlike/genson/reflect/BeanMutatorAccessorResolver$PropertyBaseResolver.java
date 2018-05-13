// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanMutatorAccessorResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.Trilean;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanMutatorAccessorResolver

public static class 
    implements BeanMutatorAccessorResolver
{

            public Trilean isAccessor(Field field, Class class1)
            {
/*  64*/        return Trilean.UNKNOWN;
            }

            public Trilean isAccessor(Method method, Class class1)
            {
/*  69*/        return Trilean.UNKNOWN;
            }

            public Trilean isCreator(Constructor constructor, Class class1)
            {
/*  74*/        return Trilean.UNKNOWN;
            }

            public Trilean isCreator(Method method, Class class1)
            {
/*  79*/        return Trilean.UNKNOWN;
            }

            public Trilean isMutator(Field field, Class class1)
            {
/*  84*/        return Trilean.UNKNOWN;
            }

            public Trilean isMutator(Method method, Class class1)
            {
/*  89*/        return Trilean.UNKNOWN;
            }

            public ()
            {
            }
}
