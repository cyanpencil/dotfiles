// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExecutionList.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.Preconditions;

public final class ExecutionList
{
    static final class RunnableExecutorPair
    {

                final Runnable runnable;
                final Executor executor;
                RunnableExecutorPair next;

                RunnableExecutorPair(Runnable runnable1, Executor executor1, RunnableExecutorPair runnableexecutorpair)
                {
/* 172*/            runnable = runnable1;
/* 173*/            executor = executor1;
/* 174*/            next = runnableexecutorpair;
                }
    }


            public ExecutionList()
            {
            }

            public final void add(Runnable runnable, Executor executor)
            {
/*  85*/label0:
                {
/*  85*/            Preconditions.checkNotNull(runnable, "Runnable was null.");
/*  86*/            Preconditions.checkNotNull(executor, "Executor was null.");
/*  91*/            synchronized(this)
                    {
/*  92*/                if(executed)
/*  93*/                    break label0;
/*  93*/                runnables = new RunnableExecutorPair(runnable, executor, runnables);
                    }
/*  94*/            return;
                }
/*  96*/        executionlist;
/*  96*/        JVM INSTR monitorexit ;
                  goto _L1
/*  96*/        runnable;
/*  96*/        throw runnable;
_L1:
/* 101*/        executeListener(runnable, executor);
/* 102*/        return;
            }

            public final void execute()
            {
/* 120*/label0:
                {
/* 120*/            synchronized(this)
                    {
/* 121*/                if(!executed)
/* 122*/                    break label0;
                    }
/* 122*/            return;
                }
                RunnableExecutorPair runnableexecutorpair1;
/* 124*/        executed = true;
/* 125*/        runnableexecutorpair1 = runnables;
/* 126*/        runnables = null;
/* 127*/        executionlist;
/* 127*/        JVM INSTR monitorexit ;
                  goto _L1
/* 127*/        exception;
/* 127*/        throw exception;
_L1:
                RunnableExecutorPair runnableexecutorpair;
                RunnableExecutorPair runnableexecutorpair2;
/* 137*/        for(runnableexecutorpair = null; runnableexecutorpair1 != null; runnableexecutorpair = runnableexecutorpair2)
                {
/* 139*/            runnableexecutorpair2 = runnableexecutorpair1;
/* 140*/            runnableexecutorpair1 = runnableexecutorpair1.next;
/* 141*/            runnableexecutorpair2.next = runnableexecutorpair;
                }

/* 144*/        for(; runnableexecutorpair != null; runnableexecutorpair = runnableexecutorpair.next)
/* 145*/            executeListener(runnableexecutorpair.runnable, runnableexecutorpair.executor);

/* 148*/        return;
            }

            private static void executeListener(Runnable runnable, Executor executor)
            {
/* 156*/        try
                {
/* 156*/            executor.execute(runnable);
/* 163*/            return;
                }
/* 157*/        catch(RuntimeException runtimeexception)
                {
/* 161*/            log.log(Level.SEVERE, (new StringBuilder(57 + (runnable = String.valueOf(String.valueOf(runnable))).length() + (executor = String.valueOf(String.valueOf(executor))).length())).append("RuntimeException while executing runnable ").append(runnable).append(" with executor ").append(executor).toString(), runtimeexception);
                }
            }

            static final Logger log = Logger.getLogger(jersey/repackaged/com/google/common/util/concurrent/ExecutionList.getName());
            private RunnableExecutorPair runnables;
            private boolean executed;

}
