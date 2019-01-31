// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            Futures, ListenableFuture, AbstractFuture

static abstract class re
    implements ListenableFuture
{

            public void addListener(Runnable runnable, Executor executor)
            {
/* 103*/        Preconditions.checkNotNull(runnable, "Runnable was null.");
/* 104*/        Preconditions.checkNotNull(executor, "Executor was null.");
/* 106*/        try
                {
/* 106*/            executor.execute(runnable);
/* 112*/            return;
                }
/* 107*/        catch(RuntimeException runtimeexception)
                {
/* 110*/            log.log(Level.SEVERE, (new StringBuilder(57 + (runnable = String.valueOf(String.valueOf(runnable))).length() + (executor = String.valueOf(String.valueOf(executor))).length())).append("RuntimeException while executing runnable ").append(runnable).append(" with executor ").append(executor).toString(), runtimeexception);
                }
            }

            public boolean cancel(boolean flag)
            {
/* 117*/        return false;
            }

            public abstract Object get()
                throws ExecutionException;

            public Object get(long l, TimeUnit timeunit)
                throws ExecutionException
            {
/* 125*/        Preconditions.checkNotNull(timeunit);
/* 126*/        return get();
            }

            public boolean isCancelled()
            {
/* 131*/        return false;
            }

            public boolean isDone()
            {
/* 136*/        return true;
            }

            private static final Logger log = Logger.getLogger(jersey/repackaged/com/google/common/util/concurrent/Futures$ImmediateFuture.getName());


            private re()
            {
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/util/concurrent/Futures$1

/* anonymous class */
    static class Futures._cls1
        implements Runnable
    {

                public final void run()
                {
/* 630*/            final AtomicBoolean thrownFromDelegate = new AtomicBoolean(true);
/* 632*/            try
                    {
/* 632*/                delegateExecutor.execute(new Futures._cls1._cls1());
/* 645*/                return;
                    }
/* 638*/            catch(RejectedExecutionException rejectedexecutionexception)
                    {
/* 639*/                if(thrownFromDelegate.get())
/* 641*/                    outputFuture.setException(rejectedexecutionexception);
                    }
                }

                final Executor val$delegateExecutor;
                final Runnable val$delegateTask;
                final AbstractFuture val$outputFuture;

                    
                    {
/* 628*/                delegateExecutor = executor;
/* 628*/                delegateTask = runnable;
/* 628*/                outputFuture = abstractfuture;
/* 628*/                super();
                    }

                // Unreferenced inner class jersey/repackaged/com/google/common/util/concurrent/Futures$1$1

/* anonymous class */
        class Futures._cls1._cls1
            implements Runnable
        {

                    public void run()
                    {
/* 634*/                thrownFromDelegate.set(false);
/* 635*/                delegateTask.run();
                    }

                    final AtomicBoolean val$thrownFromDelegate;
                    final Futures._cls1 this$0;

                            
                            {
/* 632*/                        this$0 = Futures._cls1.this;
/* 632*/                        thrownFromDelegate = atomicboolean;
/* 632*/                        super();
                            }
        }

    }

}
