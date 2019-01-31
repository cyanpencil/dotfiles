// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.Type;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class 
    implements Function
{

            public final Class apply(Type type)
            {
/* 518*/        return ReflectionHelper.erasure(type);
            }

            public final volatile Object apply(Object obj)
            {
/* 514*/        return apply((Type)obj);
            }

            ()
            {
            }
}
