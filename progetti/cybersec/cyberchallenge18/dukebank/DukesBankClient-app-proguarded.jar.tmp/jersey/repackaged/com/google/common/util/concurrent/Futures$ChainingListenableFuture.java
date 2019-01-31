// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractFuture, AsyncFunction, Futures, ListenableFuture, 
//            MoreExecutors, Uninterruptibles

static class inputFuture extends AbstractFuture
    implements Runnable
{

            public boolean cancel(boolean flag)
            {
/* 871*/        if(super.cancel(flag))
                {
/* 874*/            cancel(((Future) (inputFuture)), flag);
/* 875*/            cancel(((Future) (outputFuture)), flag);
/* 876*/            return true;
                } else
                {
/* 878*/            return false;
                }
            }

            private void cancel(Future future, boolean flag)
            {
/* 883*/        if(future != null)
/* 884*/            future.cancel(flag);
            }

            public void run()
            {
                final ListenableFuture outputFuture;
/* 893*/        outputFuture = ((ListenableFuture) (Uninterruptibles.getUninterruptibly(inputFuture)));
/* 904*/        break MISSING_BLOCK_LABEL_50;
/* 894*/        JVM INSTR pop ;
/* 898*/        cancel(false);
/* 943*/        function = null;
/* 944*/        inputFuture = null;
/* 944*/        return;
/* 900*/        outputFuture;
/* 902*/        setException(outputFuture.getCause());
/* 943*/        function = null;
/* 944*/        inputFuture = null;
/* 944*/        return;
/* 906*/        outputFuture = this.outputFuture = (ListenableFuture)Preconditions.checkNotNull(function.apply(outputFuture), "AsyncFunction may not return null.");
/* 909*/        if(!isCancelled())
/* 910*/            break MISSING_BLOCK_LABEL_108;
/* 910*/        outputFuture.cancel(wasInterrupted());
/* 911*/        this.outputFuture = null;
/* 943*/        function = null;
/* 944*/        inputFuture = null;
/* 944*/        return;
/* 914*/        outputFuture.addListener(new Runnable() {

                    public void run()
                    {
/* 918*/                set(Uninterruptibles.getUninterruptibly(outputFuture));
/* 930*/                Futures.ChainingListenableFuture.this.outputFuture = null;
/* 931*/                return;
/* 919*/                JVM INSTR pop ;
/* 923*/                cancel(false);
/* 930*/                Futures.ChainingListenableFuture.this.outputFuture = null;
/* 930*/                return;
                        Object obj1;
/* 925*/                obj1;
/* 927*/                setException(((ExecutionException) (obj1)).getCause());
/* 930*/                Futures.ChainingListenableFuture.this.outputFuture = null;
/* 931*/                return;
/* 930*/                obj1;
/* 930*/                Futures.ChainingListenableFuture.this.outputFuture = null;
/* 930*/                throw obj1;
                    }

                    final ListenableFuture val$outputFuture;
                    final Futures.ChainingListenableFuture this$0;

                    
                    {
/* 914*/                this$0 = Futures.ChainingListenableFuture.this;
/* 914*/                outputFuture = listenablefuture;
/* 914*/                super();
                    }
        }, MoreExecutors.directExecutor());
/* 943*/        function = null;
/* 944*/        inputFuture = null;
/* 945*/        return;
                Object obj;
/* 934*/        obj;
/* 936*/        setException(((UndeclaredThrowableException) (obj)).getCause());
/* 943*/        function = null;
/* 944*/        inputFuture = null;
/* 945*/        return;
/* 937*/        obj;
/* 940*/        setException(((Throwable) (obj)));
/* 943*/        function = null;
/* 944*/        inputFuture = null;
/* 945*/        return;
/* 943*/        obj;
/* 943*/        function = null;
/* 944*/        inputFuture = null;
/* 944*/        throw obj;
            }

            private AsyncFunction function;
            private ListenableFuture inputFuture;
            private volatile ListenableFuture outputFuture;


            private e(AsyncFunction asyncfunction, ListenableFuture listenablefuture)
            {
/* 861*/        function = (AsyncFunction)Preconditions.checkNotNull(asyncfunction);
/* 862*/        inputFuture = (ListenableFuture)Preconditions.checkNotNull(listenablefuture);
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
