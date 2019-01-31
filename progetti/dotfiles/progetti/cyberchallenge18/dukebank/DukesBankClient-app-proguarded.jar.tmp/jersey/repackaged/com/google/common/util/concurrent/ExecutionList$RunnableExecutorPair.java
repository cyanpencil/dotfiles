// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExecutionList.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Executor;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ExecutionList

static final class next
{

            final Runnable runnable;
            final Executor executor;
            next next;

            (Runnable runnable1, Executor executor1,  )
            {
/* 172*/        runnable = runnable1;
/* 173*/        executor = executor1;
/* 174*/        next = ;
            }
}
