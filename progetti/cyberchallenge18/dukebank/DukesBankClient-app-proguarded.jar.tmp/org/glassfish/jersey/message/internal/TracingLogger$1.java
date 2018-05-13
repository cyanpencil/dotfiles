// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TracingLogger.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.MultivaluedMap;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            TracingLogger

static class ent extends TracingLogger
{

            public final boolean isLogEnabled(ent ent)
            {
/* 107*/        return false;
            }

            public final transient void log(ent ent, Object aobj[])
            {
            }

            public final transient void logDuration(ent ent, long l, Object aobj[])
            {
            }

            public final long timestamp(ent ent)
            {
/* 122*/        return -1L;
            }

            public final void flush(MultivaluedMap multivaluedmap)
            {
            }

            ent()
            {
            }
}
