// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.pt
    implements ParameterizedType
{

            public final Type[] getActualTypeArguments()
            {
/*1183*/        return (Type[])val$ptts.clone();
            }

            public final Type getRawType()
            {
/*1188*/        return val$pt.getRawType();
            }

            public final Type getOwnerType()
            {
/*1193*/        return val$pt.getOwnerType();
            }

            final Type val$ptts[];
            final ParameterizedType val$pt;

            (Type atype[], ParameterizedType parameterizedtype)
            {
/*1179*/        val$ptts = atype;
/*1179*/        val$pt = parameterizedtype;
/*1179*/        super();
            }
}
