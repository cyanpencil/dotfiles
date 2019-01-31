// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceFinder.java

package org.glassfish.jersey.internal;

import java.lang.reflect.ReflectPermission;
import java.util.Iterator;

// Referenced classes of package org.glassfish.jersey.internal:
//            ServiceFinder

public static abstract class ovider
{

            private static ovider getInstance()
            {
/* 827*/        if((obj = sip) == null)
/* 829*/            synchronized(sipLock)
                    {
/* 830*/                if((obj = sip) == null)
/* 832*/                    sip = ((sip) (obj = new ovider()));
                    }
/* 836*/        return ((ovider) (obj));
            }

            private static void setInstance(ovider ovider)
                throws SecurityException
            {
                SecurityManager securitymanager;
/* 840*/        if((securitymanager = System.getSecurityManager()) != null)
                {
/* 842*/            ReflectPermission reflectpermission = new ReflectPermission("suppressAccessChecks");
/* 843*/            securitymanager.checkPermission(reflectpermission);
                }
/* 845*/        synchronized(sipLock)
                {
/* 846*/            sip = ovider;
                }
            }

            public abstract Iterator createIterator(Class class1, String s, ClassLoader classloader, boolean flag);

            public abstract Iterator createClassIterator(Class class1, String s, ClassLoader classloader, boolean flag);

            private static volatile sip sip;
            private static final Object sipLock = new Object();




            public ovider()
            {
            }
}
