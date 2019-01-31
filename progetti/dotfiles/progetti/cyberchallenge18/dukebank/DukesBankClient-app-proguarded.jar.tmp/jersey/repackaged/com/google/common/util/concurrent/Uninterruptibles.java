// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Uninterruptibles.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class Uninterruptibles
{

            public static Object getUninterruptibly(Future future)
                throws ExecutionException
            {
/* 133*/        boolean flag = false;
_L2:
/* 137*/        Object obj = future.get();
/* 143*/        if(flag)
/* 144*/            Thread.currentThread().interrupt();
/* 144*/        return obj;
/* 138*/        JVM INSTR pop ;
/* 139*/        flag = true;
/* 140*/        if(true) goto _L2; else goto _L1
_L1:
/* 143*/        future;
/* 143*/        if(flag)
/* 144*/            Thread.currentThread().interrupt();
/* 144*/        throw future;
            }
}
