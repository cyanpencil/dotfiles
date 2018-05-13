// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateHelper.java

package org.glassfish.hk2.internal;


// Referenced classes of package org.glassfish.hk2.internal:
//            ImmediateHelper

static class <init> extends Thread
{

            private (Runnable runnable)
            {
/* 355*/        super(runnable);
/* 356*/        setDaemon(true);
/* 357*/        setName((new StringBuilder()).append(getClass().getSimpleName()).append("-").append(System.currentTimeMillis()).toString());
            }

            setName(Runnable runnable, setName setname)
            {
/* 353*/        this(runnable);
            }
}
