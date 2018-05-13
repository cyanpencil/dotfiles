// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ListenableFutureTask.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.*;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ExecutionList, ListenableFuture

public class ListenableFutureTask extends FutureTask
    implements ListenableFuture
{

            public static ListenableFutureTask create(Callable callable)
            {
/*  53*/        return new ListenableFutureTask(callable);
            }

            public static ListenableFutureTask create(Runnable runnable, Object obj)
            {
/*  70*/        return new ListenableFutureTask(runnable, obj);
            }

            ListenableFutureTask(Callable callable)
            {
/*  74*/        super(callable);
/*  43*/        executionList = new ExecutionList();
            }

            ListenableFutureTask(Runnable runnable, Object obj)
            {
/*  78*/        super(runnable, obj);
/*  43*/        executionList = new ExecutionList();
            }

            public void addListener(Runnable runnable, Executor executor)
            {
/*  83*/        executionList.add(runnable, executor);
            }

            protected void done()
            {
/*  91*/        executionList.execute();
            }

            private final ExecutionList executionList;
}
