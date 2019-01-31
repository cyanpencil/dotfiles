// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Stopwatch;
import jersey.repackaged.com.google.common.util.concurrent.*;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheLoader, LocalCache

static class oldValue
    implements oldValue
{

            public boolean isLoading()
            {
/*3483*/        return true;
            }

            public boolean isActive()
            {
/*3488*/        return oldValue.e();
            }

            public int getWeight()
            {
/*3493*/        return oldValue.ht();
            }

            public boolean set(Object obj)
            {
/*3497*/        return futureValue.set(obj);
            }

            public boolean setException(Throwable throwable)
            {
/*3501*/        return futureValue.setException(throwable);
            }

            private ListenableFuture fullyFailedFuture(Throwable throwable)
            {
/*3505*/        return Futures.immediateFailedFuture(throwable);
            }

            public void notifyNewValue(Object obj)
            {
/*3510*/        if(obj != null)
                {
/*3513*/            set(obj);
/*3513*/            return;
                } else
                {
/*3516*/            oldValue = LocalCache.unset();
/*3520*/            return;
                }
            }

            public ListenableFuture loadFuture(Object obj, CacheLoader cacheloader)
            {
                Object obj1;
/*3523*/        stopwatch.start();
/*3524*/        obj1 = oldValue.oldValue();
/*3526*/        if(obj1 != null)
/*3527*/            break MISSING_BLOCK_LABEL_46;
/*3527*/        obj = cacheloader.load(obj);
/*3528*/        if(set(obj))
/*3528*/            return futureValue;
/*3528*/        return Futures.immediateFuture(obj);
/*3530*/        if((obj = cacheloader.reload(obj, obj1)) == null)
/*3532*/            return Futures.immediateFuture(null);
/*3536*/        return Futures.transform(((ListenableFuture) (obj)), new Function() {

                    public Object apply(Object obj2)
                    {
/*3539*/                set(obj2);
/*3540*/                return obj2;
                    }

                    final LocalCache.LoadingValueReference this$0;

                    
                    {
/*3536*/                this$0 = LocalCache.LoadingValueReference.this;
/*3536*/                super();
                    }
        });
/*3543*/        JVM INSTR dup ;
/*3544*/        obj;
/*3544*/        JVM INSTR instanceof #1   <Class InterruptedException>;
/*3544*/        JVM INSTR ifeq 89;
                   goto _L1 _L2
_L1:
/*3545*/        break MISSING_BLOCK_LABEL_83;
_L2:
/*3545*/        break MISSING_BLOCK_LABEL_89;
/*3545*/        Thread.currentThread().interrupt();
/*3547*/        if(setException(((Throwable) (obj))))
/*3547*/            return futureValue;
/*3547*/        else
/*3547*/            return fullyFailedFuture(((Throwable) (obj)));
            }

            public long elapsedNanos()
            {
/*3552*/        return stopwatch.elapsed(TimeUnit.NANOSECONDS);
            }

            public Object waitForValue()
                throws ExecutionException
            {
/*3557*/        return Uninterruptibles.getUninterruptibly(futureValue);
            }

            public Object get()
            {
/*3562*/        return oldValue.oldValue();
            }

            public oldValue getOldValue()
            {
/*3566*/        return oldValue;
            }

            public oldValue getEntry()
            {
/*3571*/        return null;
            }

            public oldValue copyFor(ReferenceQueue referencequeue, Object obj, oldValue oldvalue)
            {
/*3577*/        return this;
            }

            volatile oldValue oldValue;
            final SettableFuture futureValue;
            final Stopwatch stopwatch;

            public _cls1.this._cls0()
            {
/*3474*/        this(LocalCache.unset());
            }

            public <init>(<init> <init>1)
            {
/*3470*/        futureValue = SettableFuture.create();
/*3471*/        stopwatch = Stopwatch.createUnstarted();
/*3478*/        oldValue = <init>1;
            }
}
