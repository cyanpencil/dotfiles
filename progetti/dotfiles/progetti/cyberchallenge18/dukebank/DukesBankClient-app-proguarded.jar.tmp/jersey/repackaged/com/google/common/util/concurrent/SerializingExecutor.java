// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SerializingExecutor.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.Preconditions;

final class SerializingExecutor
    implements Executor
{
    class TaskRunner
        implements Runnable
    {

                public void run()
                {
/* 132*/            boolean flag = true;
_L2:
/* 135*/label0:
                    {
/* 135*/                Preconditions.checkState(isThreadScheduled);
/* 137*/                synchronized(internalLock)
                        {
/* 138*/                    if((obj2 = (Runnable)waitQueue.poll()) != null)
/* 140*/                        break label0;
/* 140*/                    isThreadScheduled = false;
/* 141*/                    flag = false;
                        }
/* 142*/                break; /* Loop/switch isn't completed */
                    }
/* 144*/            obj1;
/* 144*/            JVM INSTR monitorexit ;
                    String s;
/* 148*/            try
                    {
/* 148*/                ((Runnable) (obj2)).run();
                    }
/* 149*/            catch(RuntimeException runtimeexception)
                    {
/* 151*/                SerializingExecutor.log.log(Level.SEVERE, (new StringBuilder(35 + (s = String.valueOf(String.valueOf(obj2))).length())).append("Exception while executing runnable ").append(s).toString(), runtimeexception);
                    }
/* 154*/            if(true) goto _L2; else goto _L1
/* 156*/            exception;
/* 156*/            if(flag)
/* 160*/                synchronized(internalLock)
                        {
/* 161*/                    isThreadScheduled = false;
                        }
/* 162*/            throw exception;
_L1:
                }

                final SerializingExecutor this$0;

                private TaskRunner()
                {
/* 129*/            this$0 = SerializingExecutor.this;
/* 129*/            super();
                }

    }


            public SerializingExecutor(Executor executor1)
            {
/*  65*/        isThreadScheduled = false;
/*  77*/        Preconditions.checkNotNull(executor1, "'executor' must not be null.");
/*  78*/        executor = executor1;
            }

            public final void execute(Runnable runnable)
            {
/*  93*/        Preconditions.checkNotNull(runnable, "'r' must not be null.");
/*  94*/        boolean flag = false;
/*  95*/        synchronized(internalLock)
                {
/*  96*/            waitQueue.add(runnable);
/*  98*/            if(!isThreadScheduled)
                    {
/*  99*/                isThreadScheduled = true;
/* 100*/                flag = true;
                    }
                }
/* 103*/        if(!flag)
/* 106*/            break MISSING_BLOCK_LABEL_94;
/* 106*/        executor.execute(taskRunner);
/* 109*/        return;
/* 109*/        runnable;
/* 110*/        synchronized(internalLock)
                {
/* 115*/            isThreadScheduled = false;
                }
/* 116*/        throw runnable;
            }

            private static final Logger log = Logger.getLogger(jersey/repackaged/com/google/common/util/concurrent/SerializingExecutor.getName());
            private final Executor executor;
            private final Queue waitQueue = new ArrayDeque();
            private boolean isThreadScheduled;
            private final TaskRunner taskRunner = new TaskRunner();
            private final Object internalLock = new Object() {

                public String toString()
                {
/*  83*/            "SerializingExecutor lock: ";
/*  83*/            String s = String.valueOf(super.toString());
/*  83*/            s;
/*  83*/            if(s.length() == 0) goto _L2; else goto _L1
_L1:
/*  83*/            concat();
/*  83*/            return;
_L2:
/*  83*/            JVM INSTR pop ;
/*  83*/            JVM INSTR new #3   <Class String>;
/*  83*/            JVM INSTR dup_x1 ;
/*  83*/            JVM INSTR swap ;
/*  83*/            String();
/*  83*/            return;
                }

                final SerializingExecutor this$0;

                    
                    {
/*  81*/                this$0 = SerializingExecutor.this;
/*  81*/                super();
                    }
    };






}
