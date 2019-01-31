// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HK2LoaderImpl.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.MultiException;

public class HK2LoaderImpl
    implements HK2Loader
{

            public HK2LoaderImpl()
            {
/*  58*/        this(ClassLoader.getSystemClassLoader());
            }

            public HK2LoaderImpl(ClassLoader classloader)
            {
/*  68*/        if(classloader == null)
                {
/*  68*/            throw new IllegalArgumentException();
                } else
                {
/*  70*/            loader = classloader;
/*  71*/            return;
                }
            }

            public Class loadClass(String s)
                throws MultiException
            {
/*  79*/        return loader.loadClass(s);
/*  81*/        s;
/*  82*/        throw new MultiException(s);
            }

            public String toString()
            {
/*  87*/        return (new StringBuilder("HK2LoaderImpl(")).append(loader).append(")").toString();
            }

            private final ClassLoader loader;
}
