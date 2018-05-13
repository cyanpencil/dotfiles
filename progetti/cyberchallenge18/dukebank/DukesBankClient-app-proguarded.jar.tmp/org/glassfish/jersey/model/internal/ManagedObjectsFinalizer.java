// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ManagedObjectsFinalizer.java

package org.glassfish.jersey.model.internal;

import java.util.*;
import org.glassfish.hk2.api.ServiceLocator;

public class ManagedObjectsFinalizer
{

            public ManagedObjectsFinalizer()
            {
            }

            public void registerForPreDestroyCall(Object obj)
            {
/*  80*/        managedObjects.add(obj);
            }

            public void preDestroy()
            {
                Object obj;
/*  86*/        for(Iterator iterator = managedObjects.iterator(); iterator.hasNext(); serviceLocator.preDestroy(obj))
/*  86*/            obj = iterator.next();

/*  91*/        managedObjects.clear();
/*  92*/        return;
                Exception exception;
/*  91*/        exception;
/*  91*/        managedObjects.clear();
/*  91*/        throw exception;
            }

            private ServiceLocator serviceLocator;
            private final Set managedObjects = new HashSet();
}
