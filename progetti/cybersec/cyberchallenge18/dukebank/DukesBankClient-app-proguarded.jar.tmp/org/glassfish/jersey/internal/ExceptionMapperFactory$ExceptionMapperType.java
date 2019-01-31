// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionMapperFactory.java

package org.glassfish.jersey.internal;

import org.glassfish.hk2.api.ServiceHandle;

// Referenced classes of package org.glassfish.jersey.internal:
//            ExceptionMapperFactory

static class exceptionType
{

            ServiceHandle mapper;
            Class exceptionType;

            public (ServiceHandle servicehandle, Class class1)
            {
/* 103*/        mapper = servicehandle;
/* 104*/        exceptionType = class1;
            }
}
