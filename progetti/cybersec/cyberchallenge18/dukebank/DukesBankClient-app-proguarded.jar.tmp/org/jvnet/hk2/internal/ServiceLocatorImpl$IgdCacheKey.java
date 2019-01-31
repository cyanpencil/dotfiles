// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.Injectee;

// Referenced classes of package org.jvnet.hk2.internal:
//            CacheKey, ServiceLocatorImpl

static final class hashCode
{

            public final int hashCode()
            {
/*1107*/        return hashCode;
            }

            public final boolean equals(Object obj)
            {
/*1112*/        if(obj == null)
/*1113*/            return false;
/*1115*/        if(!(obj instanceof hashCode))
/*1116*/            return false;
/*1118*/        obj = (hashCode)obj;
/*1119*/        if(hashCode != ((hashCode) (obj)).hashCode)
/*1120*/            return false;
/*1122*/        return cacheKey != null ? cacheKey.equals(((cacheKey) (obj)).cacheKey) : ((cacheKey) (obj)).cacheKey == null;
            }

            private final CacheKey cacheKey;
            private final String name;
            private final Injectee onBehalfOf;
            private final Type contractOrImpl;
            private final Annotation qualifiers[];
            private final Filter filter;
            private final int hashCode;







            (CacheKey cachekey, String s, Injectee injectee, Type type, Class class1, Annotation aannotation[], Filter filter1)
            {
/*1092*/        cacheKey = cachekey;
/*1093*/        name = s;
/*1094*/        onBehalfOf = injectee;
/*1095*/        contractOrImpl = type;
/*1096*/        qualifiers = aannotation;
/*1097*/        filter = filter1;
/*1100*/        cachekey = 205 + cacheKey.hashCode();
/*1102*/        hashCode = cachekey;
            }
}
