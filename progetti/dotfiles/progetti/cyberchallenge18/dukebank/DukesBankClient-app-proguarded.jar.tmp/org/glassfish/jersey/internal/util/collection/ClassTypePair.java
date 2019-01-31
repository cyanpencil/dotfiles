// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassTypePair.java

package org.glassfish.jersey.internal.util.collection;

import java.lang.reflect.Type;

public final class ClassTypePair
{

            private ClassTypePair(Class class1, Type type1)
            {
/*  55*/        type = type1;
/*  56*/        rawClass = class1;
            }

            public final Class rawClass()
            {
/*  65*/        return rawClass;
            }

            public final Type type()
            {
/*  74*/        return type;
            }

            public static ClassTypePair of(Class class1)
            {
/*  85*/        return new ClassTypePair(class1, class1);
            }

            public static ClassTypePair of(Class class1, Type type1)
            {
/*  97*/        return new ClassTypePair(class1, type1);
            }

            private final Type type;
            private final Class rawClass;
}
