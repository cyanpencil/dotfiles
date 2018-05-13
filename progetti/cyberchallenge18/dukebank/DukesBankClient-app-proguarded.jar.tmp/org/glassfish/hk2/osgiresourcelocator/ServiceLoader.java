// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLoader.java

package org.glassfish.hk2.osgiresourcelocator;


public abstract class ServiceLoader
{
    public static interface ProviderFactory
    {

        public abstract Object make(Class class1, Class class2)
            throws Exception;
    }


            ServiceLoader()
            {
            }

            public static synchronized void initialize(ServiceLoader serviceloader)
            {
/*  63*/        if(serviceloader == null)
/*  63*/            throw new NullPointerException("Did you intend to call reset()?");
/*  64*/        if(_me != null)
                {
/*  64*/            throw new IllegalStateException((new StringBuilder("Already initialzed with [")).append(_me).append("]").toString());
                } else
                {
/*  65*/            _me = serviceloader;
/*  66*/            return;
                }
            }

            public static synchronized void reset()
            {
/*  69*/        if(_me == null)
                {
/*  70*/            throw new IllegalStateException("Not yet initialized");
                } else
                {
/*  72*/            _me = null;
/*  73*/            return;
                }
            }

            public static Iterable lookupProviderInstances(Class class1)
            {
/*  86*/        return lookupProviderInstances(class1, null);
            }

            public static Iterable lookupProviderInstances(Class class1, ProviderFactory providerfactory)
            {
/*  97*/        if(_me == null)
/*  97*/            return null;
/*  98*/        else
/*  98*/            return _me.lookupProviderInstances1(class1, providerfactory);
            }

            public static Iterable lookupProviderClasses(Class class1)
            {
/* 129*/        return _me.lookupProviderClasses1(class1);
            }

            abstract Iterable lookupProviderInstances1(Class class1, ProviderFactory providerfactory);

            abstract Iterable lookupProviderClasses1(Class class1);

            private static ServiceLoader _me;
}
