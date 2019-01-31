// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.Optional;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.collect.*;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractFuture, Futures, ListenableFuture, MoreExecutors, 
//            Uninterruptibles

static class init extends AbstractFuture
{

            protected void init(Executor executor)
            {
/*1637*/        addListener(new Runnable() {

                    public void run()
                    {
/*1641*/                if(isCancelled())
                        {
                            ListenableFuture listenablefuture;
/*1642*/                    for(Iterator iterator1 = futures.iterator(); iterator1.hasNext(); (listenablefuture = (ListenableFuture)iterator1.next()).cancel(wasInterrupted()));
                        }
/*1648*/                futures = null;
/*1652*/                values = null;
/*1655*/                combiner = null;
                    }

                    final Futures.CombinedFuture this$0;

                    
                    {
/*1637*/                this$0 = Futures.CombinedFuture.this;
/*1637*/                super();
                    }
        }, MoreExecutors.directExecutor());
/*1662*/        if(futures.isEmpty())
                {
/*1663*/            set(combiner.combine(ImmutableList.of()));
/*1664*/            return;
                }
/*1668*/        for(int i = 0; i < futures.size(); i++)
/*1669*/            values.add(null);

/*1680*/        int j = 0;
                final ListenableFuture listenable;
                final int index;
/*1681*/        for(Iterator iterator = futures.iterator(); iterator.hasNext(); listenable.addListener(new Runnable() {

                public void run()
                {
/*1686*/            setOneValue(index, listenable);
                }

                final int val$index;
                final ListenableFuture val$listenable;
                final Futures.CombinedFuture this$0;

                    
                    {
/*1683*/                this$0 = Futures.CombinedFuture.this;
/*1683*/                index = i;
/*1683*/                listenable = listenablefuture;
/*1683*/                super();
                    }
    }, executor))
                {
/*1681*/            listenable = (ListenableFuture)iterator.next();
/*1682*/            index = j++;
                }

            }

            private void setExceptionAndMaybeLog(Throwable throwable)
            {
/*1699*/        boolean flag = false;
/*1700*/        boolean flag1 = true;
/*1701*/        if(allMustSucceed)
                {
/*1704*/            flag = super.setException(throwable);
/*1706*/            synchronized(seenExceptionsLock)
                    {
/*1707*/                if(seenExceptions == null)
/*1708*/                    seenExceptions = Sets.newHashSet();
/*1710*/                flag1 = seenExceptions.add(throwable);
                    }
                }
/*1714*/        if((throwable instanceof Error) || allMustSucceed && !flag && flag1)
/*1716*/            logger.log(Level.SEVERE, "input future failed.", throwable);
            }

            private void setOneValue(int i, Future future)
            {
                List list;
/*1724*/        list = values;
/*1732*/        if(isDone() || list == null)
/*1737*/            Preconditions.checkState(allMustSucceed || isCancelled(), "Future was done before all dependencies completed");
/*1742*/        Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
/*1744*/        future = ((Future) (Uninterruptibles.getUninterruptibly(future)));
/*1745*/        if(list != null)
/*1746*/            list.set(i, Optional.fromNullable(future));
/*1759*/        Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/        if(future == 0)
                {
/*1762*/            if((i = combiner) != null && list != null)
                    {
/*1764*/                set(i.combine(list));
/*1764*/                break MISSING_BLOCK_LABEL_413;
                    }
/*1766*/            Preconditions.checkState(isDone());
                }
/*1769*/        return;
/*1748*/        JVM INSTR pop ;
/*1749*/        if(allMustSucceed)
/*1752*/            cancel(false);
/*1759*/        Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/        if(future == 0)
                {
/*1762*/            if((i = combiner) != null && list != null)
                    {
/*1764*/                set(i.combine(list));
/*1764*/                break MISSING_BLOCK_LABEL_413;
                    }
/*1766*/            Preconditions.checkState(isDone());
                }
/*1769*/        return;
/*1754*/        future;
/*1755*/        setExceptionAndMaybeLog(future.getCause());
/*1759*/        Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/        if(future == 0)
                {
/*1762*/            if((i = combiner) != null && list != null)
                    {
/*1764*/                set(i.combine(list));
/*1764*/                break MISSING_BLOCK_LABEL_413;
                    }
/*1766*/            Preconditions.checkState(isDone());
                }
/*1769*/        return;
/*1756*/        future;
/*1757*/        setExceptionAndMaybeLog(future);
/*1759*/        Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/        if(future == 0)
                {
/*1762*/            if((i = combiner) != null && list != null)
                    {
/*1764*/                set(i.combine(list));
/*1764*/                break MISSING_BLOCK_LABEL_413;
                    }
/*1766*/            Preconditions.checkState(isDone());
                }
/*1769*/        return;
/*1759*/        i;
/*1759*/        Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/        if(future == 0)
/*1762*/            if((future = combiner) != null && list != null)
/*1764*/                set(future.combine(list));
/*1766*/            else
/*1766*/                Preconditions.checkState(isDone());
/*1769*/        throw i;
            }

            private static final Logger logger = Logger.getLogger(jersey/repackaged/com/google/common/util/concurrent/Futures$CombinedFuture.getName());
            ImmutableCollection futures;
            final boolean allMustSucceed;
            final AtomicInteger remaining;
            isDone combiner;
            List values;
            final Object seenExceptionsLock = new Object();
            Set seenExceptions;



            _cls1.this._cls0(ImmutableCollection immutablecollection, boolean flag, Executor executor, _cls1.this._cls0 _pcls0)
            {
/*1624*/        futures = immutablecollection;
/*1625*/        allMustSucceed = flag;
/*1626*/        remaining = new AtomicInteger(immutablecollection.size());
/*1627*/        combiner = _pcls0;
/*1628*/        values = Lists.newArrayListWithCapacity(immutablecollection.size());
/*1629*/        init(executor);
            }
}
