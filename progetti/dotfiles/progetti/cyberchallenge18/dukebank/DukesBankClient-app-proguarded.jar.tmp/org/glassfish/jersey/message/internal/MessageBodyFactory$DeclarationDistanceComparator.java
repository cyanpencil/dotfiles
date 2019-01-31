// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import java.util.*;
import org.glassfish.jersey.internal.util.ReflectionHelper;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MessageBodyFactory

static class declared
    implements Comparator
{

            public int compare(Object obj, Object obj1)
            {
/* 293*/        obj = getDistance(obj);
/* 294*/        return (obj1 = getDistance(obj1)) - obj;
            }

            private int getDistance(Object obj)
            {
                Object obj1;
/* 299*/        if((obj1 = (Integer)distanceMap.get(obj.getClass())) != null)
/* 301*/            return ((Integer) (obj1)).intValue();
                Class aclass[];
/* 304*/        Class class1 = (aclass = ReflectionHelper.getParameterizedClassArguments(((org.glassfish.jersey.internal.util.or.distanceMap) (obj1 = ReflectionHelper.getClass(obj.getClass(), declared))))) == null ? null : aclass[0];
/* 309*/        aclass = Integer.valueOf(0);
/* 310*/        for(; class1 != null && class1 != java/lang/Object; class1 = class1.getSuperclass())
/* 311*/            aclass = Integer.valueOf(aclass.intValue() + 1);

/* 315*/        distanceMap.put(obj.getClass(), aclass);
/* 316*/        return aclass.intValue();
            }

            private final Class declared;
            private final Map distanceMap = new HashMap();

            (Class class1)
            {
/* 288*/        declared = class1;
            }
}
