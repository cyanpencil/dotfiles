// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ListeningExecutorService.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ListenableFuture

public interface ListeningExecutorService
    extends ExecutorService
{

    public abstract ListenableFuture submit(Callable callable);
}
