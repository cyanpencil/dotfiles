// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InterceptorExecutor.java

package org.glassfish.jersey.message.internal;


// Referenced classes of package org.glassfish.jersey.message.internal:
//            InterceptorExecutor

static class <init>
{

            private Object getInterceptor()
            {
/*  85*/        return interceptor;
            }

            private long getTimestamp()
            {
/*  89*/        return timestamp;
            }

            private final Object interceptor;
            private final long timestamp;



            private (Object obj, long l)
            {
/*  80*/        interceptor = obj;
/*  81*/        timestamp = l;
            }

            timestamp(Object obj, long l, timestamp timestamp1)
            {
/*  74*/        this(obj, l);
            }
}
