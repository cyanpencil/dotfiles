// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorUtilities.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.MultiException;

// Referenced classes of package org.glassfish.hk2.utilities:
//            ServiceLocatorUtilities

static class val.binderClassLoader
    implements HK2Loader
{

            public final Class loadClass(String s)
                throws MultiException
            {
/* 167*/        return val$binderClassLoader.loadClass(s);
/* 168*/        s;
/* 169*/        throw new MultiException(s);
            }

            final ClassLoader val$binderClassLoader;

            (ClassLoader classloader)
            {
/* 163*/        val$binderClassLoader = classloader;
/* 163*/        super();
            }
}
