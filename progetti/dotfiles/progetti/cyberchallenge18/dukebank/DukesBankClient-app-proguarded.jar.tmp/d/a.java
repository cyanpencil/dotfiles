// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericParameterizedType.java

package d;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public final class a
    implements ParameterizedType
{

            public a(Class class1)
            {
/*  20*/        a = class1;
            }

            public final Type[] getActualTypeArguments()
            {
/*  25*/        return (new Type[] {
/*  25*/            a
                });
            }

            public final Type getRawType()
            {
/*  30*/        return java/util/List;
            }

            public final Type getOwnerType()
            {
/*  35*/        return java/util/List;
            }

            private final Class a;
}
