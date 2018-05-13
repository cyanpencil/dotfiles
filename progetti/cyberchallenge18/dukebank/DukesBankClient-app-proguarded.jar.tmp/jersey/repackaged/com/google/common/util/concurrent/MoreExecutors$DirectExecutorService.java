// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractListeningExecutorService, MoreExecutors, ListenableFuture

static class shutdown extends AbstractListeningExecutorService
{

            public void execute(Runnable runnable)
            {
/* 297*/        startTask();
/* 299*/        runnable.run();
/* 301*/        endTask();
/* 302*/        return;
/* 301*/        runnable;
/* 301*/        endTask();
/* 301*/        throw runnable;
            }

            public boolean isShutdown()
            {
/* 307*/        lock.lock();
/* 309*/        boolean flag = shutdown;
/* 311*/        lock.unlock();
/* 311*/        return flag;
                Exception exception;
/* 311*/        exception;
/* 311*/        lock.unlock();
/* 311*/        throw exception;
            }

            public void shutdown()
            {
/* 317*/        lock.lock();
/* 319*/        shutdown = true;
/* 321*/        lock.unlock();
/* 322*/        return;
                Exception exception;
/* 321*/        exception;
/* 321*/        lock.unlock();
/* 321*/        throw exception;
            }

            public List shutdownNow()
            {
/* 328*/        shutdown();
/* 329*/        return Collections.emptyList();
            }

            public boolean isTerminated()
            {
/* 334*/        lock.lock();
/* 336*/        boolean flag = shutdown && runningTasks == 0;
/* 338*/        lock.unlock();
/* 338*/        return flag;
                Exception exception;
/* 338*/        exception;
/* 338*/        lock.unlock();
/* 338*/        throw exception;
            }

            public boolean awaitTermination(long l, TimeUnit timeunit)
                throws InterruptedException
            {
                long l1;
/* 345*/        l1 = timeunit.toNanos(l);
/* 346*/        lock.lock();
/* 349*/        do
                {
/* 349*/            if(isTerminated())
                    {
/* 358*/                lock.unlock();
/* 358*/                return true;
                    }
/* 351*/            if(l1 <= 0L)
                    {
/* 358*/                lock.unlock();
/* 358*/                return false;
                    }
/* 354*/            l1 = termination.awaitNanos(l1);
                } while(true);
/* 358*/        l;
/* 358*/        lock.unlock();
/* 358*/        throw l;
            }

            private void startTask()
            {
/* 370*/        lock.lock();
/* 372*/        if(isShutdown())
/* 373*/            throw new RejectedExecutionException("Executor already shutdown");
/* 375*/        runningTasks++;
/* 377*/        lock.unlock();
/* 378*/        return;
                Exception exception;
/* 377*/        exception;
/* 377*/        lock.unlock();
/* 377*/        throw exception;
            }

            private void endTask()
            {
/* 385*/        lock.lock();
/* 387*/        runningTasks--;
/* 388*/        if(isTerminated())
/* 389*/            termination.signalAll();
/* 392*/        lock.unlock();
/* 393*/        return;
                Exception exception;
/* 392*/        exception;
/* 392*/        lock.unlock();
/* 392*/        throw exception;
            }

            private final Lock lock;
            private final Condition termination;
            private int runningTasks;
            private boolean shutdown;

            private ()
            {
/* 280*/        lock = new ReentrantLock();
/* 283*/        termination = lock.newCondition();
/* 292*/        runningTasks = 0;
/* 293*/        shutdown = false;
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/util/concurrent/MoreExecutors$1

/* anonymous class */
    static class MoreExecutors._cls1
        implements Runnable
    {

                public final void run()
                {
/* 751*/            queue.add(future);
                }

                final BlockingQueue val$queue;
                final ListenableFuture val$future;

                    
                    {
/* 749*/                queue = blockingqueue;
/* 749*/                future = listenablefuture;
/* 749*/                super();
                    }
    }

}
