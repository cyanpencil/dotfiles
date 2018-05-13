// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.collect.*;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AsyncSettableFuture, ExecutionError, ListenableFuture, MoreExecutors, 
//            SerializingExecutor, UncheckedExecutionException, Uninterruptibles, AsyncFunction, 
//            CheckedFuture, FutureFallback, AbstractFuture, FutureCallback, 
//            AbstractCheckedFuture

public final class Futures
{
    static class MappingCheckedFuture extends AbstractCheckedFuture
    {

                final Function mapper;

                MappingCheckedFuture(ListenableFuture listenablefuture, Function function)
                {
/*1802*/            super(listenablefuture);
/*1804*/            mapper = (Function)Preconditions.checkNotNull(function);
                }
    }

    static class CombinedFuture extends AbstractFuture
    {

                protected void init(Executor executor)
                {
/*1637*/            addListener(new Runnable() {

                        public void run()
                        {
/*1641*/                    if(isCancelled())
                            {
                                ListenableFuture listenablefuture;
/*1642*/                        for(Iterator iterator1 = futures.iterator(); iterator1.hasNext(); (listenablefuture = (ListenableFuture)iterator1.next()).cancel(wasInterrupted()));
                            }
/*1648*/                    futures = null;
/*1652*/                    values = null;
/*1655*/                    combiner = null;
                        }

                        final CombinedFuture this$0;

                        
                        {
/*1637*/                    this$0 = CombinedFuture.this;
/*1637*/                    super();
                        }
            }, MoreExecutors.directExecutor());
/*1662*/            if(futures.isEmpty())
                    {
/*1663*/                set(combiner.combine(ImmutableList.of()));
/*1664*/                return;
                    }
/*1668*/            for(int i = 0; i < futures.size(); i++)
/*1669*/                values.add(null);

/*1680*/            int j = 0;
                    final ListenableFuture listenable;
                    final int index;
/*1681*/            for(Iterator iterator = futures.iterator(); iterator.hasNext(); listenable.addListener(new Runnable() {

                public void run()
                {
/*1686*/            setOneValue(index, listenable);
                }

                final int val$index;
                final ListenableFuture val$listenable;
                final CombinedFuture this$0;

                        
                        {
/*1683*/                    this$0 = CombinedFuture.this;
/*1683*/                    index = i;
/*1683*/                    listenable = listenablefuture;
/*1683*/                    super();
                        }
    }, executor))
                    {
/*1681*/                listenable = (ListenableFuture)iterator.next();
/*1682*/                index = j++;
                    }

                }

                private void setExceptionAndMaybeLog(Throwable throwable)
                {
/*1699*/            boolean flag = false;
/*1700*/            boolean flag1 = true;
/*1701*/            if(allMustSucceed)
                    {
/*1704*/                flag = super.setException(throwable);
/*1706*/                synchronized(seenExceptionsLock)
                        {
/*1707*/                    if(seenExceptions == null)
/*1708*/                        seenExceptions = Sets.newHashSet();
/*1710*/                    flag1 = seenExceptions.add(throwable);
                        }
                    }
/*1714*/            if((throwable instanceof Error) || allMustSucceed && !flag && flag1)
/*1716*/                logger.log(Level.SEVERE, "input future failed.", throwable);
                }

                private void setOneValue(int i, Future future)
                {
                    List list;
/*1724*/            list = values;
/*1732*/            if(isDone() || list == null)
/*1737*/                Preconditions.checkState(allMustSucceed || isCancelled(), "Future was done before all dependencies completed");
/*1742*/            Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
/*1744*/            future = ((Future) (Uninterruptibles.getUninterruptibly(future)));
/*1745*/            if(list != null)
/*1746*/                list.set(i, Optional.fromNullable(future));
/*1759*/            Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/            if(future == 0)
                    {
/*1762*/                if((i = combiner) != null && list != null)
                        {
/*1764*/                    set(i.combine(list));
/*1764*/                    break MISSING_BLOCK_LABEL_413;
                        }
/*1766*/                Preconditions.checkState(isDone());
                    }
/*1769*/            return;
/*1748*/            JVM INSTR pop ;
/*1749*/            if(allMustSucceed)
/*1752*/                cancel(false);
/*1759*/            Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/            if(future == 0)
                    {
/*1762*/                if((i = combiner) != null && list != null)
                        {
/*1764*/                    set(i.combine(list));
/*1764*/                    break MISSING_BLOCK_LABEL_413;
                        }
/*1766*/                Preconditions.checkState(isDone());
                    }
/*1769*/            return;
/*1754*/            future;
/*1755*/            setExceptionAndMaybeLog(future.getCause());
/*1759*/            Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/            if(future == 0)
                    {
/*1762*/                if((i = combiner) != null && list != null)
                        {
/*1764*/                    set(i.combine(list));
/*1764*/                    break MISSING_BLOCK_LABEL_413;
                        }
/*1766*/                Preconditions.checkState(isDone());
                    }
/*1769*/            return;
/*1756*/            future;
/*1757*/            setExceptionAndMaybeLog(future);
/*1759*/            Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/            if(future == 0)
                    {
/*1762*/                if((i = combiner) != null && list != null)
                        {
/*1764*/                    set(i.combine(list));
/*1764*/                    break MISSING_BLOCK_LABEL_413;
                        }
/*1766*/                Preconditions.checkState(isDone());
                    }
/*1769*/            return;
/*1759*/            i;
/*1759*/            Preconditions.checkState((future = remaining.decrementAndGet()) >= 0, "Less than 0 remaining futures");
/*1761*/            if(future == 0)
/*1762*/                if((future = combiner) != null && list != null)
/*1764*/                    set(future.combine(list));
/*1766*/                else
/*1766*/                    Preconditions.checkState(isDone());
/*1769*/            throw i;
                }

                private static final Logger logger = Logger.getLogger(jersey/repackaged/com/google/common/util/concurrent/Futures$CombinedFuture.getName());
                ImmutableCollection futures;
                final boolean allMustSucceed;
                final AtomicInteger remaining;
                FutureCombiner combiner;
                List values;
                final Object seenExceptionsLock = new Object();
                Set seenExceptions;



                CombinedFuture(ImmutableCollection immutablecollection, boolean flag, Executor executor, FutureCombiner futurecombiner)
                {
/*1624*/            futures = immutablecollection;
/*1625*/            allMustSucceed = flag;
/*1626*/            remaining = new AtomicInteger(immutablecollection.size());
/*1627*/            combiner = futurecombiner;
/*1628*/            values = Lists.newArrayListWithCapacity(immutablecollection.size());
/*1629*/            init(executor);
                }
    }

    static interface FutureCombiner
    {

        public abstract Object combine(List list);
    }

    static class NonCancellationPropagatingFuture extends AbstractFuture
    {

                NonCancellationPropagatingFuture(final ListenableFuture delegate)
                {
/*1100*/            Preconditions.checkNotNull(delegate);
/*1101*/            Futures.addCallback(delegate, new FutureCallback() {

                        public void onSuccess(Object obj)
                        {
/*1104*/                    set(obj);
                        }

                        public void onFailure(Throwable throwable)
                        {
/*1109*/                    if(delegate.isCancelled())
                            {
/*1110*/                        cancel(false);
/*1110*/                        return;
                            } else
                            {
/*1112*/                        setException(throwable);
/*1114*/                        return;
                            }
                        }

                        final ListenableFuture val$delegate;
                        final NonCancellationPropagatingFuture this$0;

                        
                        {
/*1101*/                    this$0 = NonCancellationPropagatingFuture.this;
/*1101*/                    delegate = listenablefuture;
/*1101*/                    super();
                        }
            }, MoreExecutors.directExecutor());
                }
    }

    static class ChainingListenableFuture extends AbstractFuture
        implements Runnable
    {

                public boolean cancel(boolean flag)
                {
/* 871*/            if(super.cancel(flag))
                    {
/* 874*/                cancel(((Future) (inputFuture)), flag);
/* 875*/                cancel(((Future) (outputFuture)), flag);
/* 876*/                return true;
                    } else
                    {
/* 878*/                return false;
                    }
                }

                private void cancel(Future future, boolean flag)
                {
/* 883*/            if(future != null)
/* 884*/                future.cancel(flag);
                }

                public void run()
                {
                    final ListenableFuture outputFuture;
/* 893*/            outputFuture = ((ListenableFuture) (Uninterruptibles.getUninterruptibly(inputFuture)));
/* 904*/            break MISSING_BLOCK_LABEL_50;
/* 894*/            JVM INSTR pop ;
/* 898*/            cancel(false);
/* 943*/            function = null;
/* 944*/            inputFuture = null;
/* 944*/            return;
/* 900*/            outputFuture;
/* 902*/            setException(outputFuture.getCause());
/* 943*/            function = null;
/* 944*/            inputFuture = null;
/* 944*/            return;
/* 906*/            outputFuture = this.outputFuture = (ListenableFuture)Preconditions.checkNotNull(function.apply(outputFuture), "AsyncFunction may not return null.");
/* 909*/            if(!isCancelled())
/* 910*/                break MISSING_BLOCK_LABEL_108;
/* 910*/            outputFuture.cancel(wasInterrupted());
/* 911*/            this.outputFuture = null;
/* 943*/            function = null;
/* 944*/            inputFuture = null;
/* 944*/            return;
/* 914*/            outputFuture.addListener(new Runnable() {

                        public void run()
                        {
/* 918*/                    set(Uninterruptibles.getUninterruptibly(outputFuture));
/* 930*/                    ChainingListenableFuture.this.outputFuture = null;
/* 931*/                    return;
/* 919*/                    JVM INSTR pop ;
/* 923*/                    cancel(false);
/* 930*/                    ChainingListenableFuture.this.outputFuture = null;
/* 930*/                    return;
                            Object obj1;
/* 925*/                    obj1;
/* 927*/                    setException(((ExecutionException) (obj1)).getCause());
/* 930*/                    ChainingListenableFuture.this.outputFuture = null;
/* 931*/                    return;
/* 930*/                    obj1;
/* 930*/                    ChainingListenableFuture.this.outputFuture = null;
/* 930*/                    throw obj1;
                        }

                        final ListenableFuture val$outputFuture;
                        final ChainingListenableFuture this$0;

                        
                        {
/* 914*/                    this$0 = ChainingListenableFuture.this;
/* 914*/                    outputFuture = listenablefuture;
/* 914*/                    super();
                        }
            }, MoreExecutors.directExecutor());
/* 943*/            function = null;
/* 944*/            inputFuture = null;
/* 945*/            return;
                    Object obj;
/* 934*/            obj;
/* 936*/            setException(((UndeclaredThrowableException) (obj)).getCause());
/* 943*/            function = null;
/* 944*/            inputFuture = null;
/* 945*/            return;
/* 937*/            obj;
/* 940*/            setException(((Throwable) (obj)));
/* 943*/            function = null;
/* 944*/            inputFuture = null;
/* 945*/            return;
/* 943*/            obj;
/* 943*/            function = null;
/* 944*/            inputFuture = null;
/* 944*/            throw obj;
                }

                private AsyncFunction function;
                private ListenableFuture inputFuture;
                private volatile ListenableFuture outputFuture;


                private ChainingListenableFuture(AsyncFunction asyncfunction, ListenableFuture listenablefuture)
                {
/* 861*/            function = (AsyncFunction)Preconditions.checkNotNull(asyncfunction);
/* 862*/            inputFuture = (ListenableFuture)Preconditions.checkNotNull(listenablefuture);
                }

    }

    static class FallbackFuture extends AbstractFuture
    {

                public boolean cancel(boolean flag)
                {
/* 500*/            if(super.cancel(flag))
                    {
/* 501*/                running.cancel(flag);
/* 502*/                return true;
                    } else
                    {
/* 504*/                return false;
                    }
                }

                private volatile ListenableFuture running;



                FallbackFuture(ListenableFuture listenablefuture, final FutureFallback fallback, Executor executor)
                {
/* 458*/            running = listenablefuture;
/* 459*/            Futures.addCallback(running, new FutureCallback() {

                        public void onSuccess(Object obj)
                        {
/* 462*/                    set(obj);
                        }

                        public void onFailure(Throwable throwable)
                        {
/* 467*/                    if(isCancelled())
/* 468*/                        return;
/* 471*/                    running = fallback.create(throwable);
/* 472*/                    if(isCancelled())
                            {
/* 473*/                        running.cancel(wasInterrupted());
/* 474*/                        return;
                            }
/* 476*/                    try
                            {
/* 476*/                        Futures.addCallback(running, new FutureCallback() {

                                    public void onSuccess(Object obj)
                                    {
/* 479*/                                set(obj);
                                    }

                                    public void onFailure(Throwable throwable1)
                                    {
/* 484*/                                if(running.isCancelled())
                                        {
/* 485*/                                    cancel(false);
/* 485*/                                    return;
                                        } else
                                        {
/* 487*/                                    setException(throwable1);
/* 489*/                                    return;
                                        }
                                    }

                                    final _cls1 this$1;

                                
                                {
/* 476*/                            this$1 = _cls1.this;
/* 476*/                            super();
                                }
                        }, MoreExecutors.directExecutor());
/* 493*/                        return;
                            }
                            // Misplaced declaration of an exception variable
/* 491*/                    catch(Throwable throwable)
                            {
/* 492*/                        setException(throwable);
                            }
/* 494*/                    return;
                        }

                        final FutureFallback val$fallback;
                        final FallbackFuture this$0;

                        
                        {
/* 459*/                    this$0 = FallbackFuture.this;
/* 459*/                    fallback = futurefallback;
/* 459*/                    super();
                        }
            }, executor);
                }
    }

    static class ImmediateFailedCheckedFuture extends ImmediateFuture
        implements CheckedFuture
    {

                public Object get()
                    throws ExecutionException
                {
/* 225*/            throw new ExecutionException(thrown);
                }

                private final Exception thrown;

                ImmediateFailedCheckedFuture(Exception exception)
                {
/* 220*/            thrown = exception;
                }
    }

    static class ImmediateCancelledFuture extends ImmediateFuture
    {

                public boolean isCancelled()
                {
/* 204*/            return true;
                }

                public Object get()
                {
/* 209*/            throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", thrown);
                }

                private final CancellationException thrown = new CancellationException("Immediate cancelled future.");

                ImmediateCancelledFuture()
                {
                }
    }

    static class ImmediateFailedFuture extends ImmediateFuture
    {

                public Object get()
                    throws ExecutionException
                {
/* 190*/            throw new ExecutionException(thrown);
                }

                private final Throwable thrown;

                ImmediateFailedFuture(Throwable throwable)
                {
/* 185*/            thrown = throwable;
                }
    }

    static class ImmediateSuccessfulCheckedFuture extends ImmediateFuture
        implements CheckedFuture
    {

                public Object get()
                {
/* 165*/            return value;
                }

                private final Object value;

                ImmediateSuccessfulCheckedFuture(Object obj)
                {
/* 160*/            value = obj;
                }
    }

    static class ImmediateSuccessfulFuture extends ImmediateFuture
    {

                public Object get()
                {
/* 150*/            return value;
                }

                private final Object value;

                ImmediateSuccessfulFuture(Object obj)
                {
/* 145*/            value = obj;
                }
    }

    static abstract class ImmediateFuture
        implements ListenableFuture
    {

                public void addListener(Runnable runnable, Executor executor)
                {
/* 103*/            Preconditions.checkNotNull(runnable, "Runnable was null.");
/* 104*/            Preconditions.checkNotNull(executor, "Executor was null.");
/* 106*/            try
                    {
/* 106*/                executor.execute(runnable);
/* 112*/                return;
                    }
/* 107*/            catch(RuntimeException runtimeexception)
                    {
/* 110*/                log.log(Level.SEVERE, (new StringBuilder(57 + (runnable = String.valueOf(String.valueOf(runnable))).length() + (executor = String.valueOf(String.valueOf(executor))).length())).append("RuntimeException while executing runnable ").append(runnable).append(" with executor ").append(executor).toString(), runtimeexception);
                    }
                }

                public boolean cancel(boolean flag)
                {
/* 117*/            return false;
                }

                public abstract Object get()
                    throws ExecutionException;

                public Object get(long l, TimeUnit timeunit)
                    throws ExecutionException
                {
/* 125*/            Preconditions.checkNotNull(timeunit);
/* 126*/            return get();
                }

                public boolean isCancelled()
                {
/* 131*/            return false;
                }

                public boolean isDone()
                {
/* 136*/            return true;
                }

                private static final Logger log = Logger.getLogger(jersey/repackaged/com/google/common/util/concurrent/Futures$ImmediateFuture.getName());


                private ImmediateFuture()
                {
                }

    }


            private Futures()
            {
            }

            public static CheckedFuture makeChecked(ListenableFuture listenablefuture, Function function)
            {
/*  92*/        return new MappingCheckedFuture((ListenableFuture)Preconditions.checkNotNull(listenablefuture), function);
            }

            public static ListenableFuture immediateFuture(Object obj)
            {
/* 247*/        return new ImmediateSuccessfulFuture(obj);
            }

            public static CheckedFuture immediateCheckedFuture(Object obj)
            {
/* 260*/        return new ImmediateSuccessfulCheckedFuture(obj);
            }

            public static ListenableFuture immediateFailedFuture(Throwable throwable)
            {
/* 274*/        Preconditions.checkNotNull(throwable);
/* 275*/        return new ImmediateFailedFuture(throwable);
            }

            public static ListenableFuture immediateCancelledFuture()
            {
/* 285*/        return new ImmediateCancelledFuture();
            }

            public static CheckedFuture immediateFailedCheckedFuture(Exception exception)
            {
/* 300*/        Preconditions.checkNotNull(exception);
/* 301*/        return new ImmediateFailedCheckedFuture(exception);
            }

            public static ListenableFuture withFallback(ListenableFuture listenablefuture, FutureFallback futurefallback)
            {
/* 379*/        return withFallback(listenablefuture, futurefallback, MoreExecutors.directExecutor());
            }

            public static ListenableFuture withFallback(ListenableFuture listenablefuture, FutureFallback futurefallback, Executor executor)
            {
/* 443*/        Preconditions.checkNotNull(futurefallback);
/* 444*/        return new FallbackFuture(listenablefuture, futurefallback, executor);
            }

            public static ListenableFuture transform(ListenableFuture listenablefuture, AsyncFunction asyncfunction)
            {
/* 565*/        asyncfunction = new ChainingListenableFuture(asyncfunction, listenablefuture);
/* 567*/        listenablefuture.addListener(asyncfunction, MoreExecutors.directExecutor());
/* 568*/        return asyncfunction;
            }

            public static ListenableFuture transform(ListenableFuture listenablefuture, AsyncFunction asyncfunction, Executor executor)
            {
/* 613*/        Preconditions.checkNotNull(executor);
/* 614*/        asyncfunction = new ChainingListenableFuture(asyncfunction, listenablefuture);
/* 616*/        listenablefuture.addListener(rejectionPropagatingRunnable(asyncfunction, asyncfunction, executor), MoreExecutors.directExecutor());
/* 617*/        return asyncfunction;
            }

            private static Runnable rejectionPropagatingRunnable(AbstractFuture abstractfuture, Runnable runnable, Executor executor)
            {
/* 628*/        return new Runnable(executor, runnable, abstractfuture) {

                    public final void run()
                    {
/* 630*/                final AtomicBoolean thrownFromDelegate = new AtomicBoolean(true);
/* 632*/                try
                        {
/* 632*/                    delegateExecutor.execute(new Runnable() {

                                public void run()
                                {
/* 634*/                            thrownFromDelegate.set(false);
/* 635*/                            delegateTask.run();
                                }

                                final AtomicBoolean val$thrownFromDelegate;
                                final _cls1 this$0;

                            
                            {
/* 632*/                        this$0 = _cls1.this;
/* 632*/                        thrownFromDelegate = atomicboolean;
/* 632*/                        super();
                            }
                    });
/* 645*/                    return;
                        }
/* 638*/                catch(RejectedExecutionException rejectedexecutionexception)
                        {
/* 639*/                    if(thrownFromDelegate.get())
/* 641*/                        outputFuture.setException(rejectedexecutionexception);
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
        };
            }

            public static ListenableFuture transform(ListenableFuture listenablefuture, Function function)
            {
/* 705*/        Preconditions.checkNotNull(function);
/* 706*/        function = new ChainingListenableFuture(asAsyncFunction(function), listenablefuture);
/* 708*/        listenablefuture.addListener(function, MoreExecutors.directExecutor());
/* 709*/        return function;
            }

            public static ListenableFuture transform(ListenableFuture listenablefuture, Function function, Executor executor)
            {
/* 751*/        Preconditions.checkNotNull(function);
/* 752*/        return transform(listenablefuture, asAsyncFunction(function), executor);
            }

            private static AsyncFunction asAsyncFunction(Function function)
            {
/* 758*/        return new AsyncFunction(function) {

                    public final ListenableFuture apply(Object obj)
                    {
/* 760*/                return Futures.immediateFuture(obj = function.apply(obj));
                    }

                    final Function val$function;

                    
                    {
/* 758*/                function = function1;
/* 758*/                super();
                    }
        };
            }

            public static Future lazyTransform(Future future, Function function)
            {
/* 791*/        Preconditions.checkNotNull(future);
/* 792*/        Preconditions.checkNotNull(function);
/* 793*/        return new Future(future, function) {

                    public final boolean cancel(boolean flag)
                    {
/* 797*/                return input.cancel(flag);
                    }

                    public final boolean isCancelled()
                    {
/* 802*/                return input.isCancelled();
                    }

                    public final boolean isDone()
                    {
/* 807*/                return input.isDone();
                    }

                    public final Object get()
                        throws InterruptedException, ExecutionException
                    {
/* 812*/                return applyTransformation(input.get());
                    }

                    public final Object get(long l, TimeUnit timeunit)
                        throws InterruptedException, ExecutionException, TimeoutException
                    {
/* 818*/                return applyTransformation(input.get(l, timeunit));
                    }

                    private Object applyTransformation(Object obj)
                        throws ExecutionException
                    {
/* 823*/                return function.apply(obj);
/* 824*/                obj;
/* 825*/                throw new ExecutionException(((Throwable) (obj)));
                    }

                    final Future val$input;
                    final Function val$function;

                    
                    {
/* 793*/                input = future;
/* 793*/                function = function1;
/* 793*/                super();
                    }
        };
            }

            public static ListenableFuture dereference(ListenableFuture listenablefuture)
            {
/* 973*/        return transform(listenablefuture, DEREFERENCER);
            }

            public static transient ListenableFuture allAsList(ListenableFuture alistenablefuture[])
            {
/*1005*/        return listFuture(ImmutableList.copyOf(alistenablefuture), true, MoreExecutors.directExecutor());
            }

            public static ListenableFuture allAsList(Iterable iterable)
            {
/*1027*/        return listFuture(ImmutableList.copyOf(iterable), true, MoreExecutors.directExecutor());
            }

            public static ListenableFuture nonCancellationPropagating(ListenableFuture listenablefuture)
            {
/*1091*/        return new NonCancellationPropagatingFuture(listenablefuture);
            }

            public static transient ListenableFuture successfulAsList(ListenableFuture alistenablefuture[])
            {
/*1137*/        return listFuture(ImmutableList.copyOf(alistenablefuture), false, MoreExecutors.directExecutor());
            }

            public static ListenableFuture successfulAsList(Iterable iterable)
            {
/*1158*/        return listFuture(ImmutableList.copyOf(iterable), false, MoreExecutors.directExecutor());
            }

            public static ImmutableList inCompletionOrder(Iterable iterable)
            {
/*1179*/        ConcurrentLinkedQueue concurrentlinkedqueue = Queues.newConcurrentLinkedQueue();
/*1181*/        jersey.repackaged.com.google.common.collect.ImmutableList.Builder builder = ImmutableList.builder();
/*1192*/        SerializingExecutor serializingexecutor = new SerializingExecutor(MoreExecutors.directExecutor());
                AsyncSettableFuture asyncsettablefuture;
/*1193*/        for(iterable = iterable.iterator(); iterable.hasNext(); builder.add(asyncsettablefuture))
                {
/*1193*/            ListenableFuture listenablefuture = (ListenableFuture)iterable.next();
/*1194*/            asyncsettablefuture = AsyncSettableFuture.create();
/*1196*/            concurrentlinkedqueue.add(asyncsettablefuture);
/*1197*/            listenablefuture.addListener(new Runnable(concurrentlinkedqueue, listenablefuture) {

                        public final void run()
                        {
/*1199*/                    ((AsyncSettableFuture)delegates.remove()).setFuture(future);
                        }

                        final ConcurrentLinkedQueue val$delegates;
                        final ListenableFuture val$future;

                    
                    {
/*1197*/                delegates = concurrentlinkedqueue;
/*1197*/                future = listenablefuture;
/*1197*/                super();
                    }
            }, serializingexecutor);
                }

/*1204*/        return builder.build();
            }

            public static void addCallback(ListenableFuture listenablefuture, FutureCallback futurecallback)
            {
/*1258*/        addCallback(listenablefuture, futurecallback, MoreExecutors.directExecutor());
            }

            public static void addCallback(ListenableFuture listenablefuture, FutureCallback futurecallback, Executor executor)
            {
/*1300*/        Preconditions.checkNotNull(futurecallback);
/*1301*/        futurecallback = new Runnable(listenablefuture, futurecallback) {

                    public final void run()
                    {
                        Object obj;
/*1308*/                try
                        {
/*1308*/                    obj = Uninterruptibles.getUninterruptibly(future);
                        }
                        // Misplaced declaration of an exception variable
/*1309*/                catch(Object obj)
                        {
/*1310*/                    callback.onFailure(((ExecutionException) (obj)).getCause());
/*1311*/                    return;
                        }
                        // Misplaced declaration of an exception variable
/*1312*/                catch(Object obj)
                        {
/*1313*/                    callback.onFailure(((Throwable) (obj)));
/*1314*/                    return;
                        }
                        // Misplaced declaration of an exception variable
/*1315*/                catch(Object obj)
                        {
/*1316*/                    callback.onFailure(((Throwable) (obj)));
/*1317*/                    return;
                        }
/*1319*/                callback.onSuccess(obj);
                    }

                    final ListenableFuture val$future;
                    final FutureCallback val$callback;

                    
                    {
/*1301*/                future = listenablefuture;
/*1301*/                callback = futurecallback;
/*1301*/                super();
                    }
        };
/*1322*/        listenablefuture.addListener(futurecallback, executor);
            }

            public static Object get(Future future, Class class1)
                throws Exception
            {
/*1374*/        Preconditions.checkNotNull(future);
/*1375*/        Preconditions.checkArgument(!java/lang/RuntimeException.isAssignableFrom(class1), "Futures.get exception type (%s) must not be a RuntimeException", new Object[] {
/*1375*/            class1
                });
/*1379*/        return future.get();
/*1380*/        future;
/*1381*/        Thread.currentThread().interrupt();
/*1382*/        throw newWithCause(class1, future);
/*1383*/        JVM INSTR dup ;
/*1384*/        future;
/*1384*/        getCause();
/*1384*/        class1;
/*1384*/        wrapAndThrowExceptionOrError();
/*1385*/        throw new AssertionError();
            }

            public static Object get(Future future, long l, TimeUnit timeunit, Class class1)
                throws Exception
            {
/*1440*/        Preconditions.checkNotNull(future);
/*1441*/        Preconditions.checkNotNull(timeunit);
/*1442*/        Preconditions.checkArgument(!java/lang/RuntimeException.isAssignableFrom(class1), "Futures.get exception type (%s) must not be a RuntimeException", new Object[] {
/*1442*/            class1
                });
/*1446*/        return future.get(l, timeunit);
/*1447*/        future;
/*1448*/        Thread.currentThread().interrupt();
/*1449*/        throw newWithCause(class1, future);
/*1450*/        future;
/*1451*/        throw newWithCause(class1, future);
/*1452*/        JVM INSTR dup ;
/*1453*/        future;
/*1453*/        getCause();
/*1453*/        class1;
/*1453*/        wrapAndThrowExceptionOrError();
/*1454*/        throw new AssertionError();
            }

            private static void wrapAndThrowExceptionOrError(Throwable throwable, Class class1)
                throws Exception
            {
/*1460*/        if(throwable instanceof Error)
/*1461*/            throw new ExecutionError((Error)throwable);
/*1463*/        if(throwable instanceof RuntimeException)
/*1464*/            throw new UncheckedExecutionException(throwable);
/*1466*/        else
/*1466*/            throw newWithCause(class1, throwable);
            }

            public static Object getUnchecked(Future future)
            {
/*1507*/        Preconditions.checkNotNull(future);
/*1509*/        return Uninterruptibles.getUninterruptibly(future);
/*1510*/        JVM INSTR dup ;
/*1511*/        future;
/*1511*/        getCause();
/*1511*/        wrapAndThrowUnchecked();
/*1512*/        throw new AssertionError();
            }

            private static void wrapAndThrowUnchecked(Throwable throwable)
            {
/*1517*/        if(throwable instanceof Error)
/*1518*/            throw new ExecutionError((Error)throwable);
/*1525*/        else
/*1525*/            throw new UncheckedExecutionException(throwable);
            }

            private static Exception newWithCause(Class class1, Throwable throwable)
            {
                Object obj;
                Object obj1;
/*1549*/        for(obj = preferringStrings(((List) (obj = Arrays.asList(class1.getConstructors())))).iterator(); ((Iterator) (obj)).hasNext();)
/*1551*/            if((obj1 = (Exception)newFromConstructor(((Constructor) (obj1 = (Constructor)((Iterator) (obj)).next())), throwable)) != null)
                    {
/*1554*/                if(((Exception) (obj1)).getCause() == null)
/*1555*/                    ((Exception) (obj1)).initCause(throwable);
/*1557*/                return ((Exception) (obj1));
                    }

/*1560*/        String s = String.valueOf(String.valueOf(class1));
/*1560*/        throw new IllegalArgumentException((new StringBuilder(82 + s.length())).append("No appropriate constructor for exception of type ").append(s).append(" in response to chained exception").toString(), throwable);
            }

            private static List preferringStrings(List list)
            {
/*1567*/        return WITH_STRING_PARAM_FIRST.sortedCopy(list);
            }

            private static Object newFromConstructor(Constructor constructor, Throwable throwable)
            {
                Object aobj[];
                Class aclass[];
/*1579*/        aobj = new Object[(aclass = constructor.getParameterTypes()).length];
/*1581*/        for(int i = 0; i < aclass.length; i++)
                {
                    Class class1;
/*1582*/            if((class1 = aclass[i]).equals(java/lang/String))
                    {
/*1584*/                aobj[i] = throwable.toString();
/*1584*/                continue;
                    }
/*1585*/            if(class1.equals(java/lang/Throwable))
/*1586*/                aobj[i] = throwable;
/*1588*/            else
/*1588*/                return null;
                }

/*1592*/        return constructor.newInstance(aobj);
/*1593*/        JVM INSTR pop ;
/*1594*/        return null;
/*1595*/        JVM INSTR pop ;
/*1596*/        return null;
/*1597*/        JVM INSTR pop ;
/*1598*/        return null;
/*1599*/        JVM INSTR pop ;
/*1600*/        return null;
            }

            private static ListenableFuture listFuture(ImmutableList immutablelist, boolean flag, Executor executor)
            {
/*1777*/        return new CombinedFuture(immutablelist, flag, executor, new FutureCombiner() {

                    public final List combine(List list)
                    {
/*1782*/                ArrayList arraylist = Lists.newArrayList();
                        Optional optional;
/*1783*/                for(list = list.iterator(); list.hasNext(); arraylist.add(optional == null ? null : optional.orNull()))
/*1783*/                    optional = (Optional)list.next();

/*1786*/                return Collections.unmodifiableList(arraylist);
                    }

                    public final volatile Object combine(List list)
                    {
/*1779*/                return combine(list);
                    }

        });
            }

            private static final AsyncFunction DEREFERENCER = new AsyncFunction() {

                public final ListenableFuture apply(ListenableFuture listenablefuture)
                {
/* 982*/            return listenablefuture;
                }

                public final volatile ListenableFuture apply(Object obj)
                    throws Exception
                {
/* 980*/            return apply((ListenableFuture)obj);
                }

    };
            private static final Ordering WITH_STRING_PARAM_FIRST = Ordering.natural().onResultOf(new Function() {

                public final Boolean apply(Constructor constructor)
                {
/*1573*/            return Boolean.valueOf(Arrays.asList(constructor.getParameterTypes()).contains(java/lang/String));
                }

                public final volatile Object apply(Object obj)
                {
/*1571*/            return apply((Constructor)obj);
                }

    }).reverse();

}
