// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractFuture.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractFuture

static final class  extends AbstractQueuedSynchronizer
{

            protected final int tryAcquireShared(int i)
            {
/* 243*/        return !isDone() ? -1 : 1;
            }

            protected final boolean tryReleaseShared(int i)
            {
/* 255*/        setState(i);
/* 256*/        return true;
            }

            final Object get(long l)
                throws TimeoutException, CancellationException, ExecutionException, InterruptedException
            {
/* 268*/        if(!tryAcquireSharedNanos(-1, l))
/* 269*/            throw new TimeoutException("Timeout waiting for task.");
/* 272*/        else
/* 272*/            return getValue();
            }

            final Object get()
                throws CancellationException, ExecutionException, InterruptedException
            {
/* 285*/        acquireSharedInterruptibly(-1);
/* 286*/        return getValue();
            }

            private Object getValue()
                throws CancellationException, ExecutionException
            {
                int i;
/* 295*/        switch(i = getState())
                {
/* 298*/        case 2: // '\002'
/* 298*/            if(exception != null)
/* 299*/                throw new ExecutionException(exception);
/* 301*/            else
/* 301*/                return value;

/* 306*/        case 4: // '\004'
/* 306*/        case 8: // '\b'
/* 306*/            throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", exception);
                }
/* 310*/        throw new IllegalStateException((new StringBuilder(49)).append("Error, synchronizer in invalid state: ").append(i).toString());
            }

            final boolean isDone()
            {
/* 320*/        return (getState() & 0xe) != 0;
            }

            final boolean isCancelled()
            {
/* 327*/        return (getState() & 0xc) != 0;
            }

            final boolean wasInterrupted()
            {
/* 334*/        return getState() == 8;
            }

            final boolean set(Object obj)
            {
/* 341*/        return complete(obj, null, 2);
            }

            final boolean setException(Throwable throwable)
            {
/* 348*/        return complete(null, throwable, 2);
            }

            final boolean cancel(boolean flag)
            {
/* 355*/        return complete(null, null, flag ? 8 : 4);
            }

            private boolean complete(Object obj, Throwable throwable, int i)
            {
                boolean flag;
/* 372*/        if(flag = compareAndSetState(0, 1))
                {
/* 376*/            value = obj;
/* 378*/            exception = ((Throwable) ((i & 0xc) == 0 ? throwable : ((Throwable) (new CancellationException("Future.cancel() was called.")))));
/* 380*/            releaseShared(i);
                } else
/* 381*/        if(getState() == 1)
/* 384*/            acquireShared(-1);
/* 386*/        return flag;
            }

            private Object value;
            private Throwable exception;

            ()
            {
            }
}
