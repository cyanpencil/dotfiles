// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyNameResolver.java

package com.owlike.genson.reflect;

import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyNameResolver

public static class components
    implements PropertyNameResolver
{

            public transient components add(PropertyNameResolver apropertynameresolver[])
            {
/*  74*/        components.addAll(0, Arrays.asList(apropertynameresolver));
/*  75*/        return this;
            }

            public String resolve(int i, Constructor constructor)
            {
/*  79*/        String s = null;
/*  80*/        for(Iterator iterator = components.iterator(); s == null && iterator.hasNext(); s = ((PropertyNameResolver)iterator.next()).resolve(i, constructor));
/*  84*/        return s;
            }

            public String resolve(int i, Method method)
            {
/*  88*/        String s = null;
/*  89*/        for(Iterator iterator = components.iterator(); s == null && iterator.hasNext(); s = ((PropertyNameResolver)iterator.next()).resolve(i, method));
/*  93*/        return s;
            }

            public String resolve(Field field)
            {
/*  97*/        String s = null;
/*  98*/        for(Iterator iterator = components.iterator(); s == null && iterator.hasNext(); s = ((PropertyNameResolver)iterator.next()).resolve(field));
/* 102*/        return s;
            }

            public String resolve(Method method)
            {
/* 106*/        String s = null;
/* 107*/        for(Iterator iterator = components.iterator(); s == null && iterator.hasNext(); s = ((PropertyNameResolver)iterator.next()).resolve(method));
/* 111*/        return s;
            }

            private List components;

            public (List list)
            {
/*  65*/        if(list == null || list.isEmpty())
                {
/*  66*/            throw new IllegalArgumentException("The composite resolver must have at least one resolver as component!");
                } else
                {
/*  69*/            components = new LinkedList(list);
/*  70*/            return;
                }
            }
}
