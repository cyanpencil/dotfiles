// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceFinder.java

package org.glassfish.jersey.internal;

import java.util.Iterator;

// Referenced classes of package org.glassfish.jersey.internal:
//            ServiceFinder

public static final class  extends 
{

            public final Iterator createIterator(Class class1, String s, ClassLoader classloader, boolean flag)
            {
/* 897*/        return new (class1, s, classloader, flag);
            }

            public final Iterator createClassIterator(Class class1, String s, ClassLoader classloader, boolean flag)
            {
/* 903*/        return new (class1, s, classloader, flag);
            }

            public ()
            {
            }
}
