// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateHelper.java

package org.glassfish.hk2.internal;

import java.util.concurrent.ThreadFactory;

// Referenced classes of package org.glassfish.hk2.internal:
//            ImmediateHelper

static class <init>
    implements ThreadFactory
{

            public Thread newThread(Runnable runnable)
            {
/* 365*/        return runnable = new <init>(runnable, null);
            }

            private ()
            {
            }

            ( )
            {
/* 362*/        this();
            }
}
