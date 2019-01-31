// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Queues.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import java.util.concurrent.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, Iterables, Synchronized

public final class Queues
{

            private Queues()
            {
            }

            public static ArrayBlockingQueue newArrayBlockingQueue(int i)
            {
/*  51*/        return new ArrayBlockingQueue(i);
            }

            public static ArrayDeque newArrayDeque()
            {
/*  62*/        return new ArrayDeque();
            }

            public static ArrayDeque newArrayDeque(Iterable iterable)
            {
/*  72*/        if(iterable instanceof Collection)
                {
/*  73*/            return new ArrayDeque(Collections2.cast(iterable));
                } else
                {
                    ArrayDeque arraydeque;
/*  75*/            Iterables.addAll(arraydeque = new ArrayDeque(), iterable);
/*  77*/            return arraydeque;
                }
            }

            public static ConcurrentLinkedQueue newConcurrentLinkedQueue()
            {
/*  86*/        return new ConcurrentLinkedQueue();
            }

            public static ConcurrentLinkedQueue newConcurrentLinkedQueue(Iterable iterable)
            {
/*  95*/        if(iterable instanceof Collection)
                {
/*  96*/            return new ConcurrentLinkedQueue(Collections2.cast(iterable));
                } else
                {
                    ConcurrentLinkedQueue concurrentlinkedqueue;
/*  98*/            Iterables.addAll(concurrentlinkedqueue = new ConcurrentLinkedQueue(), iterable);
/* 100*/            return concurrentlinkedqueue;
                }
            }

            public static LinkedBlockingDeque newLinkedBlockingDeque()
            {
/* 111*/        return new LinkedBlockingDeque();
            }

            public static LinkedBlockingDeque newLinkedBlockingDeque(int i)
            {
/* 121*/        return new LinkedBlockingDeque(i);
            }

            public static LinkedBlockingDeque newLinkedBlockingDeque(Iterable iterable)
            {
/* 132*/        if(iterable instanceof Collection)
                {
/* 133*/            return new LinkedBlockingDeque(Collections2.cast(iterable));
                } else
                {
                    LinkedBlockingDeque linkedblockingdeque;
/* 135*/            Iterables.addAll(linkedblockingdeque = new LinkedBlockingDeque(), iterable);
/* 137*/            return linkedblockingdeque;
                }
            }

            public static LinkedBlockingQueue newLinkedBlockingQueue()
            {
/* 146*/        return new LinkedBlockingQueue();
            }

            public static LinkedBlockingQueue newLinkedBlockingQueue(int i)
            {
/* 155*/        return new LinkedBlockingQueue(i);
            }

            public static LinkedBlockingQueue newLinkedBlockingQueue(Iterable iterable)
            {
/* 167*/        if(iterable instanceof Collection)
                {
/* 168*/            return new LinkedBlockingQueue(Collections2.cast(iterable));
                } else
                {
                    LinkedBlockingQueue linkedblockingqueue;
/* 170*/            Iterables.addAll(linkedblockingqueue = new LinkedBlockingQueue(), iterable);
/* 172*/            return linkedblockingqueue;
                }
            }

            public static PriorityBlockingQueue newPriorityBlockingQueue()
            {
/* 186*/        return new PriorityBlockingQueue();
            }

            public static PriorityBlockingQueue newPriorityBlockingQueue(Iterable iterable)
            {
/* 199*/        if(iterable instanceof Collection)
                {
/* 200*/            return new PriorityBlockingQueue(Collections2.cast(iterable));
                } else
                {
                    PriorityBlockingQueue priorityblockingqueue;
/* 202*/            Iterables.addAll(priorityblockingqueue = new PriorityBlockingQueue(), iterable);
/* 204*/            return priorityblockingqueue;
                }
            }

            public static PriorityQueue newPriorityQueue()
            {
/* 216*/        return new PriorityQueue();
            }

            public static PriorityQueue newPriorityQueue(Iterable iterable)
            {
/* 229*/        if(iterable instanceof Collection)
                {
/* 230*/            return new PriorityQueue(Collections2.cast(iterable));
                } else
                {
                    PriorityQueue priorityqueue;
/* 232*/            Iterables.addAll(priorityqueue = new PriorityQueue(), iterable);
/* 234*/            return priorityqueue;
                }
            }

            public static SynchronousQueue newSynchronousQueue()
            {
/* 243*/        return new SynchronousQueue();
            }

            public static int drain(BlockingQueue blockingqueue, Collection collection, int i, long l, TimeUnit timeunit)
                throws InterruptedException
            {
/* 262*/        Preconditions.checkNotNull(collection);
/* 268*/        long l1 = System.nanoTime() + timeunit.toNanos(l);
/* 269*/        l = 0;
/* 270*/        do
                {
/* 270*/            if(l >= i)
/* 273*/                break;
/* 273*/            if((l += blockingqueue.drainTo(collection, i - l)) >= i)
/* 275*/                continue;
/* 275*/            if((obj = blockingqueue.poll(l1 - System.nanoTime(), TimeUnit.NANOSECONDS)) == null)
/* 279*/                break;
/* 279*/            collection.add(obj);
/* 280*/            l++;
                } while(true);
/* 283*/        return l;
            }

            public static int drainUninterruptibly(BlockingQueue blockingqueue, Collection collection, int i, long l, TimeUnit timeunit)
            {
                long l1;
/* 302*/        Preconditions.checkNotNull(collection);
/* 303*/        l1 = System.nanoTime() + timeunit.toNanos(l);
/* 304*/        l = 0;
/* 305*/        flag = false;
/* 307*/        do
                {
/* 307*/            if(l >= i)
/* 310*/                break;
/* 310*/            if((l += blockingqueue.drainTo(collection, i - l)) >= i)
/* 315*/                continue;
/* 315*/            do
/* 315*/                try
                        {
/* 315*/                    timeunit = ((TimeUnit) (blockingqueue.poll(l1 - System.nanoTime(), TimeUnit.NANOSECONDS)));
/* 316*/                    break;
                        }
/* 317*/                catch(InterruptedException _ex)
                        {
/* 318*/                    flag = true;
                        }
/* 318*/            while(true);
/* 321*/            if(timeunit == null)
/* 324*/                break;
/* 324*/            collection.add(timeunit);
/* 325*/            l++;
                } while(true);
/* 329*/        if(flag)
/* 330*/            Thread.currentThread().interrupt();
/* 330*/        break MISSING_BLOCK_LABEL_120;
/* 329*/        blockingqueue;
/* 329*/        if(flag)
/* 330*/            Thread.currentThread().interrupt();
/* 330*/        throw blockingqueue;
/* 333*/        return l;
            }

            public static Queue synchronizedQueue(Queue queue)
            {
/* 364*/        return Synchronized.queue(queue, null);
            }

            public static Deque synchronizedDeque(Deque deque)
            {
/* 395*/        return Synchronized.deque(deque, null);
            }
}
