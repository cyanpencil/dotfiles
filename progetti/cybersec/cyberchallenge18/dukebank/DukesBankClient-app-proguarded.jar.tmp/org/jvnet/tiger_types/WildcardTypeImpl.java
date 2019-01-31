// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WildcardTypeImpl.java

package org.jvnet.tiger_types;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

final class WildcardTypeImpl
    implements WildcardType
{

            public WildcardTypeImpl(Type atype[], Type atype1[])
            {
/*  55*/        ub = atype;
/*  56*/        lb = atype1;
            }

            public final Type[] getUpperBounds()
            {
/*  60*/        return ub;
            }

            public final Type[] getLowerBounds()
            {
/*  64*/        return lb;
            }

            public final int hashCode()
            {
/*  68*/        return Arrays.hashCode(lb) ^ Arrays.hashCode(ub);
            }

            public final boolean equals(Object obj)
            {
/*  72*/        if(obj instanceof WildcardType)
/*  73*/            return Arrays.equals(((WildcardType) (obj = (WildcardType)obj)).getLowerBounds(), lb) && Arrays.equals(((WildcardType) (obj)).getUpperBounds(), ub);
/*  77*/        else
/*  77*/            return false;
            }

            private final Type ub[];
            private final Type lb[];
}
