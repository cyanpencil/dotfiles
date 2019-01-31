// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Callables.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.base.Supplier;

public final class Callables
{

            static Callable threadRenaming(Callable callable, Supplier supplier)
            {
/*  59*/        Preconditions.checkNotNull(supplier);
/*  60*/        Preconditions.checkNotNull(callable);
/*  61*/        return new Callable(supplier, callable) {

                    public final Object call()
                        throws Exception
                    {
                        Thread thread;
                        String s;
                        boolean flag;
/*  63*/                s = (thread = Thread.currentThread()).getName();
/*  65*/                flag = Callables.trySetName((String)nameSupplier.get(), thread);
/*  67*/                Object obj = callable.call();
/*  69*/                if(flag)
/*  70*/                    Callables.trySetName(s, thread);
/*  70*/                return obj;
                        Exception exception;
/*  69*/                exception;
/*  69*/                if(flag)
/*  70*/                    Callables.trySetName(s, thread);
/*  70*/                throw exception;
                    }

                    final Supplier val$nameSupplier;
                    final Callable val$callable;

                    
                    {
/*  61*/                nameSupplier = supplier;
/*  61*/                callable = callable1;
/*  61*/                super();
                    }
        };
            }

            static Runnable threadRenaming(Runnable runnable, Supplier supplier)
            {
/*  87*/        Preconditions.checkNotNull(supplier);
/*  88*/        Preconditions.checkNotNull(runnable);
/*  89*/        return new Runnable(supplier, runnable) {

                    public final void run()
                    {
                        Thread thread;
                        String s;
                        boolean flag;
/*  91*/                s = (thread = Thread.currentThread()).getName();
/*  93*/                flag = Callables.trySetName((String)nameSupplier.get(), thread);
/*  95*/                task.run();
                        Exception exception;
/*  97*/                if(flag)
                        {
/*  98*/                    Callables.trySetName(s, thread);
/*  98*/                    return;
                        } else
                        {
/* 101*/                    return;
                        }
/*  97*/                exception;
/*  97*/                if(flag)
/*  98*/                    Callables.trySetName(s, thread);
/*  98*/                throw exception;
                    }

                    final Supplier val$nameSupplier;
                    final Runnable val$task;

                    
                    {
/*  89*/                nameSupplier = supplier;
/*  89*/                task = runnable;
/*  89*/                super();
                    }
        };
            }

            private static boolean trySetName(String s, Thread thread)
            {
/* 111*/        thread.setName(s);
/* 112*/        return true;
/* 113*/        JVM INSTR pop ;
/* 114*/        return false;
            }

}
