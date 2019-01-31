// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lister.java

package org.jvnet.tiger_types;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Collection;

// Referenced classes of package org.jvnet.tiger_types:
//            Lister

static class nit> extends Lister
{

            public final Object toCollection()
            {
/* 109*/        return ((Object) (r.toArray((Object[])Array.newInstance(itemType, r.size()))));
            }

            (Class class1, Type type)
            {
/* 107*/        super(class1, type);
            }
}
