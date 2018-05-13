// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanMutatorAccessorResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.Trilean;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanMutatorAccessorResolver

public static class components
    implements BeanMutatorAccessorResolver
{

            public transient components add(BeanMutatorAccessorResolver abeanmutatoraccessorresolver[])
            {
/* 105*/        components.addAll(0, Arrays.asList(abeanmutatoraccessorresolver));
/* 106*/        return this;
            }

            public Trilean isAccessor(Field field, Class class1)
            {
/* 111*/        Trilean trilean = Trilean.UNKNOWN;
/* 112*/        for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isAccessor(field, class1));
/* 116*/        return trilean;
            }

            public Trilean isAccessor(Method method, Class class1)
            {
/* 121*/        Trilean trilean = Trilean.UNKNOWN;
/* 122*/        for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isAccessor(method, class1));
/* 126*/        return trilean;
            }

            public Trilean isCreator(Constructor constructor, Class class1)
            {
/* 131*/        Trilean trilean = Trilean.UNKNOWN;
/* 132*/        for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isCreator(constructor, class1));
/* 136*/        return trilean;
            }

            public Trilean isCreator(Method method, Class class1)
            {
/* 141*/        Trilean trilean = Trilean.UNKNOWN;
/* 142*/        for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isCreator(method, class1));
/* 146*/        return trilean;
            }

            public Trilean isMutator(Field field, Class class1)
            {
/* 151*/        Trilean trilean = Trilean.UNKNOWN;
/* 152*/        for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isMutator(field, class1));
/* 156*/        return trilean;
            }

            public Trilean isMutator(Method method, Class class1)
            {
/* 161*/        Trilean trilean = Trilean.UNKNOWN;
/* 162*/        for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isMutator(method, class1));
/* 166*/        return trilean;
            }

            private List components;

            public (List list)
            {
/*  97*/        if(list == null || list.isEmpty())
                {
/*  98*/            throw new IllegalArgumentException("The composite resolver must have at least one resolver as component!");
                } else
                {
/* 101*/            components = new LinkedList(list);
/* 102*/            return;
                }
            }
}
