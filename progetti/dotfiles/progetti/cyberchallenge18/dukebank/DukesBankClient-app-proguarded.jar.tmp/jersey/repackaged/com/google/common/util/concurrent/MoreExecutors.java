// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.collect.Lists;
import jersey.repackaged.com.google.common.collect.Queues;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            ListenableFuture, ListeningExecutorService, ListeningScheduledExecutorService, ThreadFactoryBuilder, 
//            ListenableFutureTask, ListenableScheduledFuture, AbstractListeningExecutorService, AbstractFuture, 
//            ForwardingListenableFuture, WrappingScheduledExecutorService, Callables, WrappingExecutorService

public final class MoreExecutors
{
    static class ScheduledListeningDecorator extends ListeningDecorator
        implements ListeningScheduledExecutorService
    {
        static final class NeverSuccessfulListenableFutureTask extends AbstractFuture
            implements Runnable
        {

                    public final void run()
                    {
/* 648*/                try
                        {
/* 648*/                    _flddelegate.run();
/* 652*/                    return;
                        }
/* 649*/                catch(Throwable throwable)
                        {
/* 650*/                    setException(throwable);
/* 651*/                    throw Throwables.propagate(throwable);
                        }
                    }

                    private final Runnable _flddelegate;

                    public NeverSuccessfulListenableFutureTask(Runnable runnable)
                    {
/* 643*/                _flddelegate = (Runnable)Preconditions.checkNotNull(runnable);
                    }
        }

        static final class ListenableScheduledTask extends ForwardingListenableFuture.SimpleForwardingListenableFuture
            implements ListenableScheduledFuture
        {

                    public final boolean cancel(boolean flag)
                    {
                        boolean flag1;
/* 616*/                if(flag1 = super.cancel(flag))
/* 619*/                    scheduledDelegate.cancel(flag);
/* 623*/                return flag1;
                    }

                    public final long getDelay(TimeUnit timeunit)
                    {
/* 628*/                return scheduledDelegate.getDelay(timeunit);
                    }

                    public final int compareTo(Delayed delayed)
                    {
/* 633*/                return scheduledDelegate.compareTo(delayed);
                    }

                    public final volatile int compareTo(Object obj)
                    {
/* 601*/                return compareTo((Delayed)obj);
                    }

                    private final ScheduledFuture scheduledDelegate;

                    public ListenableScheduledTask(ListenableFuture listenablefuture, ScheduledFuture scheduledfuture)
                    {
/* 610*/                super(listenablefuture);
/* 611*/                scheduledDelegate = scheduledfuture;
                    }
        }


                public ListenableScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
                {
/* 567*/            runnable = ListenableFutureTask.create(runnable, null);
/* 569*/            l = _flddelegate.schedule(runnable, l, timeunit);
/* 570*/            return new ListenableScheduledTask(runnable, l);
                }

                public ListenableScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
                {
/* 576*/            callable = ListenableFutureTask.create(callable);
/* 577*/            l = _flddelegate.schedule(callable, l, timeunit);
/* 578*/            return new ListenableScheduledTask(callable, l);
                }

                public ListenableScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
                {
/* 584*/            runnable = new NeverSuccessfulListenableFutureTask(runnable);
/* 586*/            l = _flddelegate.scheduleAtFixedRate(runnable, l, l1, timeunit);
/* 588*/            return new ListenableScheduledTask(runnable, l);
                }

                public ListenableScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
                {
/* 594*/            runnable = new NeverSuccessfulListenableFutureTask(runnable);
/* 596*/            l = _flddelegate.scheduleWithFixedDelay(runnable, l, l1, timeunit);
/* 598*/            return new ListenableScheduledTask(runnable, l);
                }

                public volatile ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeunit)
                {
/* 554*/            return scheduleWithFixedDelay(runnable, l, l1, timeunit);
                }

                public volatile ScheduledFuture scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeunit)
                {
/* 554*/            return scheduleAtFixedRate(runnable, l, l1, timeunit);
                }

                public volatile ScheduledFuture schedule(Callable callable, long l, TimeUnit timeunit)
                {
/* 554*/            return schedule(callable, l, timeunit);
                }

                public volatile ScheduledFuture schedule(Runnable runnable, long l, TimeUnit timeunit)
                {
/* 554*/            return schedule(runnable, l, timeunit);
                }

                final ScheduledExecutorService _flddelegate;

                ScheduledListeningDecorator(ScheduledExecutorService scheduledexecutorservice)
                {
/* 560*/            super(scheduledexecutorservice);
/* 561*/            _flddelegate = (ScheduledExecutorService)Preconditions.checkNotNull(scheduledexecutorservice);
                }
    }

    static class ListeningDecorator extends AbstractListeningExecutorService
    {

                public boolean awaitTermination(long l, TimeUnit timeunit)
                    throws InterruptedException
                {
/* 525*/            return _flddelegate.awaitTermination(l, timeunit);
                }

                public boolean isShutdown()
                {
/* 530*/            return _flddelegate.isShutdown();
                }

                public boolean isTerminated()
                {
/* 535*/            return _flddelegate.isTerminated();
                }

                public void shutdown()
                {
/* 540*/            _flddelegate.shutdown();
                }

                public List shutdownNow()
                {
/* 545*/            return _flddelegate.shutdownNow();
                }

                public void execute(Runnable runnable)
                {
/* 550*/            _flddelegate.execute(runnable);
                }

                private final ExecutorService _flddelegate;

                ListeningDecorator(ExecutorService executorservice)
                {
/* 519*/            _flddelegate = (ExecutorService)Preconditions.checkNotNull(executorservice);
                }
    }

    static final class DirectExecutor extends Enum
        implements Executor
    {

                public final void execute(Runnable runnable)
                {
/* 457*/            runnable.run();
                }

                public static final DirectExecutor INSTANCE;
                private static final DirectExecutor $VALUES[];

                static 
                {
/* 455*/            INSTANCE = new DirectExecutor("INSTANCE", 0);
/* 454*/            $VALUES = (new DirectExecutor[] {
/* 454*/                INSTANCE
                    });
                }

                private DirectExecutor(String s, int i)
                {
/* 454*/            super(s, i);
                }
    }

    static class DirectExecutorService extends AbstractListeningExecutorService
    {

                public void execute(Runnable runnable)
                {
/* 297*/            startTask();
/* 299*/            runnable.run();
/* 301*/            endTask();
/* 302*/            return;
/* 301*/            runnable;
/* 301*/            endTask();
/* 301*/            throw runnable;
                }

                public boolean isShutdown()
                {
/* 307*/            lock.lock();
/* 309*/            boolean flag = shutdown;
/* 311*/            lock.unlock();
/* 311*/            return flag;
                    Exception exception;
/* 311*/            exception;
/* 311*/            lock.unlock();
/* 311*/            throw exception;
                }

                public void shutdown()
                {
/* 317*/            lock.lock();
/* 319*/            shutdown = true;
/* 321*/            lock.unlock();
/* 322*/            return;
                    Exception exception;
/* 321*/            exception;
/* 321*/            lock.unlock();
/* 321*/            throw exception;
                }

                public List shutdownNow()
                {
/* 328*/            shutdown();
/* 329*/            return Collections.emptyList();
                }

                public boolean isTerminated()
                {
/* 334*/            lock.lock();
/* 336*/            boolean flag = shutdown && runningTasks == 0;
/* 338*/            lock.unlock();
/* 338*/            return flag;
                    Exception exception;
/* 338*/            exception;
/* 338*/            lock.unlock();
/* 338*/            throw exception;
                }

                public boolean awaitTermination(long l, TimeUnit timeunit)
                    throws InterruptedException
                {
                    long l1;
/* 345*/            l1 = timeunit.toNanos(l);
/* 346*/            lock.lock();
/* 349*/            do
                    {
/* 349*/                if(isTerminated())
                        {
/* 358*/                    lock.unlock();
/* 358*/                    return true;
                        }
/* 351*/                if(l1 <= 0L)
                        {
/* 358*/                    lock.unlock();
/* 358*/                    return false;
                        }
/* 354*/                l1 = termination.awaitNanos(l1);
                    } while(true);
/* 358*/            l;
/* 358*/            lock.unlock();
/* 358*/            throw l;
                }

                private void startTask()
                {
/* 370*/            lock.lock();
/* 372*/            if(isShutdown())
/* 373*/                throw new RejectedExecutionException("Executor already shutdown");
/* 375*/            runningTasks++;
/* 377*/            lock.unlock();
/* 378*/            return;
                    Exception exception;
/* 377*/            exception;
/* 377*/            lock.unlock();
/* 377*/            throw exception;
                }

                private void endTask()
                {
/* 385*/            lock.lock();
/* 387*/            runningTasks--;
/* 388*/            if(isTerminated())
/* 389*/                termination.signalAll();
/* 392*/            lock.unlock();
/* 393*/            return;
                    Exception exception;
/* 392*/            exception;
/* 392*/            lock.unlock();
/* 392*/            throw exception;
                }

                private final Lock lock;
                private final Condition termination;
                private int runningTasks;
                private boolean shutdown;

                private DirectExecutorService()
                {
/* 280*/            lock = new ReentrantLock();
/* 283*/            termination = lock.newCondition();
/* 292*/            runningTasks = 0;
/* 293*/            shutdown = false;
                }

    }

    static class Application
    {

                final ExecutorService getExitingExecutorService(ThreadPoolExecutor threadpoolexecutor, long l, TimeUnit timeunit)
                {
/* 177*/            MoreExecutors.useDaemonThreadFactory(threadpoolexecutor);
/* 178*/            threadpoolexecutor = Executors.unconfigurableExecutorService(threadpoolexecutor);
/* 179*/            addDelayedShutdownHook(threadpoolexecutor, l, timeunit);
/* 180*/            return threadpoolexecutor;
                }

                final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledthreadpoolexecutor, long l, TimeUnit timeunit)
                {
/* 185*/            MoreExecutors.useDaemonThreadFactory(scheduledthreadpoolexecutor);
/* 186*/            scheduledthreadpoolexecutor = Executors.unconfigurableScheduledExecutorService(scheduledthreadpoolexecutor);
/* 187*/            addDelayedShutdownHook(scheduledthreadpoolexecutor, l, timeunit);
/* 188*/            return scheduledthreadpoolexecutor;
                }

                final void addDelayedShutdownHook(final ExecutorService service, final long terminationTimeout, final TimeUnit timeUnit)
                {
/* 193*/            Preconditions.checkNotNull(service);
/* 194*/            Preconditions.checkNotNull(timeUnit);
                    String s;
/* 195*/            addShutdownHook(MoreExecutors.newThread((new StringBuilder(24 + (s = String.valueOf(String.valueOf(service))).length())).append("DelayedShutdownHook-for-").append(s).toString(), new Runnable() {

                        public void run()
                        {
/* 204*/                    try
                            {
/* 204*/                        service.shutdown();
/* 205*/                        service.awaitTermination(terminationTimeout, timeUnit);
/* 208*/                        return;
                            }
/* 206*/                    catch(InterruptedException _ex)
                            {
/* 209*/                        return;
                            }
                        }

                        final ExecutorService val$service;
                        final long val$terminationTimeout;
                        final TimeUnit val$timeUnit;
                        final Application this$0;

                        
                        {
/* 195*/                    this$0 = Application.this;
/* 195*/                    service = executorservice;
/* 195*/                    terminationTimeout = l;
/* 195*/                    timeUnit = timeunit;
/* 195*/                    super();
                        }
            }));
                }

                final ExecutorService getExitingExecutorService(ThreadPoolExecutor threadpoolexecutor)
                {
/* 214*/            return getExitingExecutorService(threadpoolexecutor, 120L, TimeUnit.SECONDS);
                }

                final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledthreadpoolexecutor)
                {
/* 219*/            return getExitingScheduledExecutorService(scheduledthreadpoolexecutor, 120L, TimeUnit.SECONDS);
                }

                void addShutdownHook(Thread thread)
                {
/* 223*/            Runtime.getRuntime().addShutdownHook(thread);
                }

                Application()
                {
                }
    }


            private MoreExecutors()
            {
            }

            public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadpoolexecutor, long l, TimeUnit timeunit)
            {
/*  86*/        return (new Application()).getExitingExecutorService(threadpoolexecutor, l, timeunit);
            }

            public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledthreadpoolexecutor, long l, TimeUnit timeunit)
            {
/* 109*/        return (new Application()).getExitingScheduledExecutorService(scheduledthreadpoolexecutor, l, timeunit);
            }

            public static void addDelayedShutdownHook(ExecutorService executorservice, long l, TimeUnit timeunit)
            {
/* 127*/        (new Application()).addDelayedShutdownHook(executorservice, l, timeunit);
            }

            public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadpoolexecutor)
            {
/* 148*/        return (new Application()).getExitingExecutorService(threadpoolexecutor);
            }

            public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledthreadpoolexecutor)
            {
/* 169*/        return (new Application()).getExitingScheduledExecutorService(scheduledthreadpoolexecutor);
            }

            private static void useDaemonThreadFactory(ThreadPoolExecutor threadpoolexecutor)
            {
/* 228*/        threadpoolexecutor.setThreadFactory((new ThreadFactoryBuilder()).setDaemon(true).setThreadFactory(threadpoolexecutor.getThreadFactory()).build());
            }

            /**
             * @deprecated Method sameThreadExecutor is deprecated
             */

            public static ListeningExecutorService sameThreadExecutor()
            {
/* 270*/        return new DirectExecutorService();
            }

            public static ListeningExecutorService newDirectExecutorService()
            {
/* 430*/        return new DirectExecutorService();
            }

            public static Executor directExecutor()
            {
/* 450*/        return DirectExecutor.INSTANCE;
            }

            public static ListeningExecutorService listeningDecorator(ExecutorService executorservice)
            {
/* 481*/        if(executorservice instanceof ListeningExecutorService)
/* 481*/            return (ListeningExecutorService)executorservice;
/* 481*/        if(executorservice instanceof ScheduledExecutorService)
/* 481*/            return new ScheduledListeningDecorator((ScheduledExecutorService)executorservice);
/* 481*/        else
/* 481*/            return new ListeningDecorator(executorservice);
            }

            public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService scheduledexecutorservice)
            {
/* 509*/        if(scheduledexecutorservice instanceof ListeningScheduledExecutorService)
/* 509*/            return (ListeningScheduledExecutorService)scheduledexecutorservice;
/* 509*/        else
/* 509*/            return new ScheduledListeningDecorator(scheduledexecutorservice);
            }

            static Object invokeAnyImpl(ListeningExecutorService listeningexecutorservice, Collection collection, boolean flag, long l)
                throws InterruptedException, ExecutionException, TimeoutException
            {
                int i;
                ArrayList arraylist;
                LinkedBlockingQueue linkedblockingqueue;
/* 675*/        Preconditions.checkNotNull(listeningexecutorservice);
/* 676*/        Preconditions.checkArgument((i = collection.size()) > 0);
/* 678*/        arraylist = Lists.newArrayListWithCapacity(i);
/* 679*/        linkedblockingqueue = Queues.newLinkedBlockingQueue();
                Object obj;
                long l1;
                int j;
/* 690*/        obj = null;
/* 691*/        l1 = flag ? System.nanoTime() : 0L;
/* 692*/        collection = collection.iterator();
/* 694*/        arraylist.add(submitAndAddQueueListener(listeningexecutorservice, (Callable)collection.next(), linkedblockingqueue));
/* 695*/        i--;
/* 696*/        j = 1;
_L1:
                Future future;
/* 699*/        do
/* 699*/            if((future = (Future)linkedblockingqueue.poll()) == null)
/* 701*/                if(i > 0)
                        {
/* 702*/                    i--;
/* 703*/                    arraylist.add(submitAndAddQueueListener(listeningexecutorservice, (Callable)collection.next(), linkedblockingqueue));
/* 704*/                    j++;
                        } else
                        {
/* 705*/                    if(j == 0)
/* 707*/                        break MISSING_BLOCK_LABEL_293;
/* 707*/                    if(flag)
                            {
/* 708*/                        if((future = (Future)linkedblockingqueue.poll(l, TimeUnit.NANOSECONDS)) == null)
/* 710*/                            throw new TimeoutException();
/* 712*/                        long l2 = System.nanoTime();
/* 713*/                        l -= l2 - l1;
/* 714*/                        l1 = l2;
                            } else
                            {
/* 716*/                        future = (Future)linkedblockingqueue.take();
                            }
                        }
/* 719*/        while(future == null);
/* 720*/        j--;
/* 722*/        obj = future.get();
/* 736*/        for(listeningexecutorservice = arraylist.iterator(); listeningexecutorservice.hasNext(); (collection = (Future)listeningexecutorservice.next()).cancel(true));
/* 738*/        return obj;
/* 723*/        JVM INSTR dup ;
/* 724*/        obj;
/* 724*/        obj;
                  goto _L1
/* 725*/        obj;
/* 726*/        obj = new ExecutionException(((Throwable) (obj)));
                  goto _L1
/* 731*/        if(obj == null)
/* 732*/            obj = new ExecutionException(null);
/* 734*/        throw obj;
/* 736*/        listeningexecutorservice;
/* 736*/        for(collection = arraylist.iterator(); collection.hasNext(); (flag = (Future)collection.next()).cancel(true));
/* 738*/        throw listeningexecutorservice;
            }

            private static ListenableFuture submitAndAddQueueListener(ListeningExecutorService listeningexecutorservice, Callable callable, BlockingQueue blockingqueue)
            {
/* 748*/        (listeningexecutorservice = listeningexecutorservice.submit(callable)).addListener(new Runnable(blockingqueue, listeningexecutorservice) {

                    public final void run()
                    {
/* 751*/                queue.add(future);
                    }

                    final BlockingQueue val$queue;
                    final ListenableFuture val$future;

                    
                    {
/* 749*/                queue = blockingqueue;
/* 749*/                future = listenablefuture;
/* 749*/                super();
                    }
        }, directExecutor());
/* 754*/        return listeningexecutorservice;
            }

            public static ThreadFactory platformThreadFactory()
            {
/* 767*/        if(!isAppEngine())
/* 768*/            return Executors.defaultThreadFactory();
/* 771*/        return (ThreadFactory)Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke(null, new Object[0]);
                Object obj;
/* 774*/        obj;
/* 775*/        throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", ((Throwable) (obj)));
/* 776*/        obj;
/* 777*/        throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", ((Throwable) (obj)));
/* 778*/        obj;
/* 779*/        throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", ((Throwable) (obj)));
/* 780*/        JVM INSTR dup ;
                InvocationTargetException invocationtargetexception;
/* 781*/        invocationtargetexception;
/* 781*/        getCause();
/* 781*/        Throwables.propagate();
/* 781*/        throw ;
            }

            private static boolean isAppEngine()
            {
/* 786*/        if(System.getProperty("com.google.appengine.runtime.environment") == null)
/* 787*/            return false;
/* 791*/        if(Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke(null, new Object[0]) != null)
/* 791*/            return true;
/* 791*/        return false;
/* 794*/        JVM INSTR pop ;
/* 796*/        return false;
/* 797*/        JVM INSTR pop ;
/* 799*/        return false;
/* 800*/        JVM INSTR pop ;
/* 802*/        return false;
/* 803*/        JVM INSTR pop ;
/* 805*/        return false;
            }

            static Thread newThread(String s, Runnable runnable)
            {
/* 814*/        Preconditions.checkNotNull(s);
/* 815*/        Preconditions.checkNotNull(runnable);
/* 816*/        runnable = platformThreadFactory().newThread(runnable);
/* 818*/        try
                {
/* 818*/            runnable.setName(s);
                }
/* 819*/        catch(SecurityException _ex) { }
/* 822*/        return runnable;
            }

            static Executor renamingDecorator(Executor executor, Supplier supplier)
            {
/* 841*/        Preconditions.checkNotNull(executor);
/* 842*/        Preconditions.checkNotNull(supplier);
/* 843*/        if(isAppEngine())
/* 845*/            return executor;
/* 847*/        else
/* 847*/            return new Executor(executor, supplier) {

                        public final void execute(Runnable runnable)
                        {
/* 849*/                    executor.execute(Callables.threadRenaming(runnable, nameSupplier));
                        }

                        final Executor val$executor;
                        final Supplier val$nameSupplier;

                    
                    {
/* 847*/                executor = executor1;
/* 847*/                nameSupplier = supplier;
/* 847*/                super();
                    }
            };
            }

            static ExecutorService renamingDecorator(ExecutorService executorservice, Supplier supplier)
            {
/* 868*/        Preconditions.checkNotNull(executorservice);
/* 869*/        Preconditions.checkNotNull(supplier);
/* 870*/        if(isAppEngine())
/* 872*/            return executorservice;
/* 874*/        else
/* 874*/            return new WrappingExecutorService(executorservice, supplier) {

                        protected final Callable wrapTask(Callable callable)
                        {
/* 876*/                    return Callables.threadRenaming(callable, nameSupplier);
                        }

                        protected final Runnable wrapTask(Runnable runnable)
                        {
/* 879*/                    return Callables.threadRenaming(runnable, nameSupplier);
                        }

                        final Supplier val$nameSupplier;

                    
                    {
/* 874*/                nameSupplier = supplier;
/* 874*/                super(executorservice);
                    }
            };
            }

            static ScheduledExecutorService renamingDecorator(ScheduledExecutorService scheduledexecutorservice, Supplier supplier)
            {
/* 898*/        Preconditions.checkNotNull(scheduledexecutorservice);
/* 899*/        Preconditions.checkNotNull(supplier);
/* 900*/        if(isAppEngine())
/* 902*/            return scheduledexecutorservice;
/* 904*/        else
/* 904*/            return new WrappingScheduledExecutorService(scheduledexecutorservice, supplier) {

                        protected final Callable wrapTask(Callable callable)
                        {
/* 906*/                    return Callables.threadRenaming(callable, nameSupplier);
                        }

                        protected final Runnable wrapTask(Runnable runnable)
                        {
/* 909*/                    return Callables.threadRenaming(runnable, nameSupplier);
                        }

                        final Supplier val$nameSupplier;

                    
                    {
/* 904*/                nameSupplier = supplier;
/* 904*/                super(scheduledexecutorservice);
                    }
            };
            }

            public static boolean shutdownAndAwaitTermination(ExecutorService executorservice, long l, TimeUnit timeunit)
            {
/* 942*/        Preconditions.checkNotNull(timeunit);
/* 944*/        executorservice.shutdown();
/* 946*/        try
                {
/* 946*/            long l1 = TimeUnit.NANOSECONDS.convert(l, timeunit) / 2L;
/* 948*/            if(!executorservice.awaitTermination(l1, TimeUnit.NANOSECONDS))
                    {
/* 950*/                executorservice.shutdownNow();
/* 952*/                executorservice.awaitTermination(l1, TimeUnit.NANOSECONDS);
                    }
                }
/* 954*/        catch(InterruptedException _ex)
                {
/* 956*/            Thread.currentThread().interrupt();
/* 958*/            executorservice.shutdownNow();
                }
/* 960*/        return executorservice.isTerminated();
            }

}
