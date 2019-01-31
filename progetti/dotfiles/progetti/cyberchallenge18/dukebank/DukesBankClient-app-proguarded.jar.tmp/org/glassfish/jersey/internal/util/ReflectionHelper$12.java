// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.Type;
import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.jersey.internal.util.collection.ClassTypePair;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class Pair
    implements Function
{

            public final ClassTypePair apply(Type type)
            {
/* 556*/        return ClassTypePair.of(ReflectionHelper.erasure(type), type);
            }

            public final volatile Object apply(Object obj)
            {
/* 552*/        return apply((Type)obj);
            }

            Pair()
            {
            }
}
