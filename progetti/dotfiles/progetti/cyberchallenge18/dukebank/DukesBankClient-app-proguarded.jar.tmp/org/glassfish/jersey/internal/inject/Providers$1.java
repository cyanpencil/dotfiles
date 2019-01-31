// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Providers.java

package org.glassfish.jersey.internal.inject;

import org.glassfish.hk2.api.Factory;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            Providers

static class val.instance
    implements Factory
{

            public final Object provide()
            {
/* 167*/        return val$instance;
            }

            public final void dispose(Object obj)
            {
            }

            final Object val$instance;

            (Object obj)
            {
/* 163*/        val$instance = obj;
/* 163*/        super();
            }
}
