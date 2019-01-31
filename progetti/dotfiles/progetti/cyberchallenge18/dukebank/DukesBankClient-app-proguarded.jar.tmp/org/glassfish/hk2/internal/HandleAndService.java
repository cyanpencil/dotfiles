// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HandleAndService.java

package org.glassfish.hk2.internal;

import org.glassfish.hk2.api.ServiceHandle;

public class HandleAndService
{

            public HandleAndService(ServiceHandle servicehandle, Object obj)
            {
/*  54*/        handle = servicehandle;
/*  55*/        service = obj;
            }

            public ServiceHandle getHandle()
            {
/*  59*/        return handle;
            }

            public Object getService()
            {
/*  63*/        return service;
            }

            private final ServiceHandle handle;
            private final Object service;
}
