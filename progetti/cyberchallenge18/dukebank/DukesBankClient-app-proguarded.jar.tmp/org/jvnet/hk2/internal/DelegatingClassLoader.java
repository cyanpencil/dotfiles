// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DelegatingClassLoader.java

package org.jvnet.hk2.internal;

import java.net.URL;
import org.glassfish.hk2.utilities.reflection.Pretty;

class DelegatingClassLoader extends ClassLoader
{

            transient DelegatingClassLoader(ClassLoader classloader, ClassLoader aclassloader[])
            {
/*  60*/        super(classloader);
/*  61*/        delegates = aclassloader;
            }

            public Class loadClass(String s)
                throws ClassNotFoundException
            {
/*  68*/        if(getParent() == null)
/*  70*/            break MISSING_BLOCK_LABEL_17;
/*  70*/        return getParent().loadClass(s);
/*  71*/        JVM INSTR pop ;
                ClassNotFoundException classnotfoundexception;
                ClassLoader aclassloader[];
                int i;
                int j;
/*  74*/        classnotfoundexception = null;
/*  75*/        i = (aclassloader = delegates).length;
/*  75*/        j = 0;
_L3:
/*  75*/        if(j >= i) goto _L2; else goto _L1
_L1:
/*  75*/        ClassLoader classloader = aclassloader[j];
/*  77*/        return classloader.loadClass(s);
                ClassNotFoundException classnotfoundexception1;
/*  79*/        classnotfoundexception1;
/*  80*/        if(classnotfoundexception == null)
/*  80*/            classnotfoundexception = classnotfoundexception1;
/*  75*/        j++;
                  goto _L3
_L2:
/*  84*/        if(classnotfoundexception != null)
/*  84*/            throw classnotfoundexception;
/*  85*/        else
/*  85*/            throw new ClassNotFoundException((new StringBuilder("Could not find ")).append(s).toString());
            }

            public URL getResource(String s)
            {
                URL url;
/*  90*/        if(getParent() != null && (url = getParent().getResource(s)) != null)
/*  94*/            return url;
                ClassLoader aclassloader[];
/*  98*/        int i = (aclassloader = delegates).length;
/*  98*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*  98*/            if((obj = ((ClassLoader) (obj = aclassloader[j])).getResource(s)) != null)
/* 101*/                return ((URL) (obj));
                }

/* 104*/        return null;
            }

            public String toString()
            {
/* 109*/        return (new StringBuilder("DelegatingClassLoader(")).append(getParent()).append(",").append(Pretty.array(delegates)).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final ClassLoader delegates[];
}
