// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractFuture, Futures

static class e extends e
{

            public boolean isCancelled()
            {
/* 204*/        return true;
            }

            public Object get()
            {
/* 209*/        throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", thrown);
            }

            private final CancellationException thrown = new CancellationException("Immediate cancelled future.");

            e()
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
