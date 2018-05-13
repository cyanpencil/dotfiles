// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.io.Serializable;
import java.lang.ref.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.collect.*;
import jersey.repackaged.com.google.common.primitives.Ints;
import jersey.repackaged.com.google.common.util.concurrent.*;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            AbstractCache, CacheBuilder, CacheLoader, RemovalListener, 
//            RemovalNotification, Weigher, LoadingCache, Cache, 
//            CacheStats, RemovalCause

class LocalCache extends AbstractMap
    implements ConcurrentMap
{
    static class LocalLoadingCache extends LocalManualCache
        implements LoadingCache
    {

                public Object get(Object obj)
                    throws ExecutionException
                {
/*4824*/            return localCache.getOrLoad(obj);
                }

                public Object getUnchecked(Object obj)
                {
/*4830*/            return get(obj);
/*4831*/            obj;
/*4832*/            throw new UncheckedExecutionException(((ExecutionException) (obj)).getCause());
                }

                public ImmutableMap getAll(Iterable iterable)
                    throws ExecutionException
                {
/*4838*/            return localCache.getAll(iterable);
                }

                public void refresh(Object obj)
                {
/*4843*/            localCache.refresh(obj);
                }

                public final Object apply(Object obj)
                {
/*4848*/            return getUnchecked(obj);
                }

                LocalLoadingCache(CacheBuilder cachebuilder, CacheLoader cacheloader)
                {
/*4817*/            super(new LocalCache(cachebuilder, (CacheLoader)Preconditions.checkNotNull(cacheloader)));
                }
    }

    static class LocalManualCache
        implements Serializable, Cache
    {

                public Object getIfPresent(Object obj)
                {
/*4733*/            return localCache.getIfPresent(obj);
                }

                public Object get(Object obj, final Callable valueLoader)
                    throws ExecutionException
                {
/*4738*/            Preconditions.checkNotNull(valueLoader);
/*4739*/            return localCache.get(obj, new CacheLoader() {

                        public Object load(Object obj1)
                            throws Exception
                        {
/*4742*/                    return valueLoader.call();
                        }

                        final Callable val$valueLoader;
                        final LocalManualCache this$0;

                        
                        {
/*4739*/                    this$0 = LocalManualCache.this;
/*4739*/                    valueLoader = callable;
/*4739*/                    super();
                        }
            });
                }

                public ImmutableMap getAllPresent(Iterable iterable)
                {
/*4749*/            return localCache.getAllPresent(iterable);
                }

                public void put(Object obj, Object obj1)
                {
/*4754*/            localCache.put(obj, obj1);
                }

                public void putAll(Map map)
                {
/*4759*/            localCache.putAll(map);
                }

                public void invalidate(Object obj)
                {
/*4764*/            Preconditions.checkNotNull(obj);
/*4765*/            localCache.remove(obj);
                }

                public void invalidateAll(Iterable iterable)
                {
/*4770*/            localCache.invalidateAll(iterable);
                }

                public void invalidateAll()
                {
/*4775*/            localCache.clear();
                }

                public long size()
                {
/*4780*/            return localCache.longSize();
                }

                public ConcurrentMap asMap()
                {
/*4785*/            return localCache;
                }

                public CacheStats stats()
                {
                    AbstractCache.SimpleStatsCounter simplestatscounter;
/*4790*/            (simplestatscounter = new AbstractCache.SimpleStatsCounter()).incrementBy(localCache.globalStatsCounter);
                    Segment asegment[];
/*4792*/            int i = (asegment = localCache.segments).length;
/*4792*/            for(int j = 0; j < i; j++)
                    {
/*4792*/                Segment segment = asegment[j];
/*4793*/                simplestatscounter.incrementBy(segment.statsCounter);
                    }

/*4795*/            return simplestatscounter.snapshot();
                }

                public void cleanUp()
                {
/*4800*/            localCache.cleanUp();
                }

                final LocalCache localCache;

                LocalManualCache(CacheBuilder cachebuilder)
                {
/*4721*/            this(new LocalCache(cachebuilder, null));
                }

                private LocalManualCache(LocalCache localcache)
                {
/*4725*/            localCache = localcache;
                }

    }

    final class EntrySet extends AbstractCacheSet
    {

                public final Iterator iterator()
                {
/*4520*/            return new EntryIterator();
                }

                public final boolean contains(Object obj)
                {
/*4525*/            if(!(obj instanceof java.util.Map.Entry))
/*4526*/                return false;
                    Object obj1;
/*4528*/            if((obj1 = ((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey()) == null)
/*4531*/                return false;
/*4533*/            return (obj1 = get(obj1)) != null && valueEquivalence.equivalent(((java.util.Map.Entry) (obj)).getValue(), obj1);
                }

                public final boolean remove(Object obj)
                {
/*4540*/            if(!(obj instanceof java.util.Map.Entry))
/*4541*/                return false;
                    Object obj1;
/*4543*/            return (obj1 = ((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey()) != null && LocalCache.this.remove(obj1, ((java.util.Map.Entry) (obj)).getValue());
                }

                final LocalCache this$0;

                EntrySet(ConcurrentMap concurrentmap)
                {
/*4514*/            this$0 = LocalCache.this;
/*4515*/            super(concurrentmap);
                }
    }

    final class Values extends AbstractCollection
    {

                public final int size()
                {
/*4490*/            return map.size();
                }

                public final boolean isEmpty()
                {
/*4494*/            return map.isEmpty();
                }

                public final void clear()
                {
/*4498*/            map.clear();
                }

                public final Iterator iterator()
                {
/*4503*/            return new ValueIterator();
                }

                public final boolean contains(Object obj)
                {
/*4508*/            return map.containsValue(obj);
                }

                private final ConcurrentMap map;
                final LocalCache this$0;

                Values(ConcurrentMap concurrentmap)
                {
/*4485*/            this$0 = LocalCache.this;
/*4485*/            super();
/*4486*/            map = concurrentmap;
                }
    }

    final class KeySet extends AbstractCacheSet
    {

                public final Iterator iterator()
                {
/*4468*/            return new KeyIterator();
                }

                public final boolean contains(Object obj)
                {
/*4473*/            return map.containsKey(obj);
                }

                public final boolean remove(Object obj)
                {
/*4478*/            return map.remove(obj) != null;
                }

                final LocalCache this$0;

                KeySet(ConcurrentMap concurrentmap)
                {
/*4462*/            this$0 = LocalCache.this;
/*4463*/            super(concurrentmap);
                }
    }

    abstract class AbstractCacheSet extends AbstractSet
    {

                public int size()
                {
/*4446*/            return map.size();
                }

                public boolean isEmpty()
                {
/*4451*/            return map.isEmpty();
                }

                public void clear()
                {
/*4456*/            map.clear();
                }

                final ConcurrentMap map;
                final LocalCache this$0;

                AbstractCacheSet(ConcurrentMap concurrentmap)
                {
/*4440*/            this$0 = LocalCache.this;
/*4440*/            super();
/*4441*/            map = concurrentmap;
                }
    }

    final class EntryIterator extends HashIterator
    {

                public final java.util.Map.Entry next()
                {
/*4433*/            return nextEntry();
                }

                public final volatile Object next()
                {
/*4429*/            return next();
                }

                final LocalCache this$0;

                EntryIterator()
                {
/*4429*/            this$0 = LocalCache.this;
/*4429*/            super();
                }
    }

    final class WriteThroughEntry
        implements java.util.Map.Entry
    {

                public final Object getKey()
                {
/*4392*/            return key;
                }

                public final Object getValue()
                {
/*4397*/            return value;
                }

                public final boolean equals(Object obj)
                {
/*4403*/            if(obj instanceof java.util.Map.Entry)
                    {
/*4404*/                obj = (java.util.Map.Entry)obj;
/*4405*/                return key.equals(((java.util.Map.Entry) (obj)).getKey()) && value.equals(((java.util.Map.Entry) (obj)).getValue());
                    } else
                    {
/*4407*/                return false;
                    }
                }

                public final int hashCode()
                {
/*4413*/            return key.hashCode() ^ value.hashCode();
                }

                public final Object setValue(Object obj)
                {
/*4418*/            throw new UnsupportedOperationException();
                }

                public final String toString()
                {
/*4425*/            String s = String.valueOf(String.valueOf(getKey()));
/*4425*/            String s1 = String.valueOf(String.valueOf(getValue()));
/*4425*/            return (new StringBuilder(1 + s.length() + s1.length())).append(s).append("=").append(s1).toString();
                }

                final Object key;
                Object value;
                final LocalCache this$0;

                WriteThroughEntry(Object obj, Object obj1)
                {
/*4385*/            this$0 = LocalCache.this;
/*4385*/            super();
/*4386*/            key = obj;
/*4387*/            value = obj1;
                }
    }

    final class ValueIterator extends HashIterator
    {

                public final Object next()
                {
/*4373*/            return nextEntry().getValue();
                }

                final LocalCache this$0;

                ValueIterator()
                {
/*4369*/            this$0 = LocalCache.this;
/*4369*/            super();
                }
    }

    final class KeyIterator extends HashIterator
    {

                public final Object next()
                {
/*4365*/            return nextEntry().getKey();
                }

                final LocalCache this$0;

                KeyIterator()
                {
/*4361*/            this$0 = LocalCache.this;
/*4361*/            super();
                }
    }

    abstract class HashIterator
        implements Iterator
    {

                final void advance()
                {
/*4268*/            nextExternal = null;
/*4270*/            if(nextInChain())
/*4271*/                return;
/*4274*/            if(nextInTable())
/*4275*/                return;
/*4278*/            while(nextSegmentIndex >= 0) 
                    {
/*4279*/                currentSegment = segments[nextSegmentIndex--];
/*4280*/                if(currentSegment.count != 0)
                        {
/*4281*/                    currentTable = currentSegment.table;
/*4282*/                    nextTableIndex = currentTable.length() - 1;
/*4283*/                    if(nextInTable())
/*4284*/                        return;
                        }
                    }
                }

                boolean nextInChain()
                {
/*4294*/            if(nextEntry != null)
/*4295*/                for(nextEntry = nextEntry.getNext(); nextEntry != null; nextEntry = nextEntry.getNext())
/*4296*/                    if(advanceTo(nextEntry))
/*4297*/                        return true;

/*4301*/            return false;
                }

                boolean nextInTable()
                {
/*4308*/            while(nextTableIndex >= 0) 
/*4309*/                if((nextEntry = (ReferenceEntry)currentTable.get(nextTableIndex--)) != null && (advanceTo(nextEntry) || nextInChain()))
/*4311*/                    return true;
/*4315*/            return false;
                }

                boolean advanceTo(ReferenceEntry referenceentry)
                {
/*4324*/            long l = ticker.read();
/*4325*/            Object obj = referenceentry.getKey();
/*4326*/            if((referenceentry = ((ReferenceEntry) (getLiveValue(referenceentry, l)))) == null)
/*4328*/                break MISSING_BLOCK_LABEL_60;
/*4328*/            nextExternal = new WriteThroughEntry(obj, referenceentry);
/*4335*/            currentSegment.postReadCleanup();
/*4335*/            return true;
/*4335*/            currentSegment.postReadCleanup();
/*4335*/            return false;
/*4335*/            referenceentry;
/*4335*/            currentSegment.postReadCleanup();
/*4335*/            throw referenceentry;
                }

                public boolean hasNext()
                {
/*4341*/            return nextExternal != null;
                }

                WriteThroughEntry nextEntry()
                {
/*4345*/            if(nextExternal == null)
                    {
/*4346*/                throw new NoSuchElementException();
                    } else
                    {
/*4348*/                lastReturned = nextExternal;
/*4349*/                advance();
/*4350*/                return lastReturned;
                    }
                }

                public void remove()
                {
/*4355*/            Preconditions.checkState(lastReturned != null);
/*4356*/            LocalCache.this.remove(lastReturned.getKey());
/*4357*/            lastReturned = null;
                }

                int nextSegmentIndex;
                int nextTableIndex;
                Segment currentSegment;
                AtomicReferenceArray currentTable;
                ReferenceEntry nextEntry;
                WriteThroughEntry nextExternal;
                WriteThroughEntry lastReturned;
                final LocalCache this$0;

                HashIterator()
                {
/*4258*/            this$0 = LocalCache.this;
/*4258*/            super();
/*4259*/            nextSegmentIndex = segments.length - 1;
/*4260*/            nextTableIndex = -1;
/*4261*/            advance();
                }
    }

    static final class AccessQueue extends AbstractQueue
    {

                public final boolean offer(ReferenceEntry referenceentry)
                {
/*3772*/            LocalCache.connectAccessOrder(referenceentry.getPreviousInAccessQueue(), referenceentry.getNextInAccessQueue());
/*3775*/            LocalCache.connectAccessOrder(head.getPreviousInAccessQueue(), referenceentry);
/*3776*/            LocalCache.connectAccessOrder(referenceentry, head);
/*3778*/            return true;
                }

                public final ReferenceEntry peek()
                {
                    ReferenceEntry referenceentry;
/*3783*/            if((referenceentry = head.getNextInAccessQueue()) == head)
/*3784*/                return null;
/*3784*/            else
/*3784*/                return referenceentry;
                }

                public final ReferenceEntry poll()
                {
                    ReferenceEntry referenceentry;
/*3789*/            if((referenceentry = head.getNextInAccessQueue()) == head)
                    {
/*3791*/                return null;
                    } else
                    {
/*3794*/                remove(referenceentry);
/*3795*/                return referenceentry;
                    }
                }

                public final boolean remove(Object obj)
                {
/*3801*/            ReferenceEntry referenceentry = ((ReferenceEntry) (obj = (ReferenceEntry)obj)).getPreviousInAccessQueue();
/*3803*/            ReferenceEntry referenceentry1 = ((ReferenceEntry) (obj)).getNextInAccessQueue();
/*3804*/            LocalCache.connectAccessOrder(referenceentry, referenceentry1);
/*3805*/            LocalCache.nullifyAccessOrder(((ReferenceEntry) (obj)));
/*3807*/            return referenceentry1 != NullEntry.INSTANCE;
                }

                public final boolean contains(Object obj)
                {
/*3813*/            return ((ReferenceEntry) (obj = (ReferenceEntry)obj)).getNextInAccessQueue() != NullEntry.INSTANCE;
                }

                public final boolean isEmpty()
                {
/*3819*/            return head.getNextInAccessQueue() == head;
                }

                public final int size()
                {
/*3824*/            int i = 0;
/*3825*/            for(ReferenceEntry referenceentry = head.getNextInAccessQueue(); referenceentry != head; referenceentry = referenceentry.getNextInAccessQueue())
/*3827*/                i++;

/*3829*/            return i;
                }

                public final void clear()
                {
                    ReferenceEntry referenceentry1;
/*3834*/            for(ReferenceEntry referenceentry = head.getNextInAccessQueue(); referenceentry != head; referenceentry = referenceentry1)
                    {
/*3836*/                referenceentry1 = referenceentry.getNextInAccessQueue();
/*3837*/                LocalCache.nullifyAccessOrder(referenceentry);
                    }

/*3841*/            head.setNextInAccessQueue(head);
/*3842*/            head.setPreviousInAccessQueue(head);
                }

                public final Iterator iterator()
                {
/*3847*/            return new AbstractSequentialIterator(peek()) {

                        protected ReferenceEntry computeNext(ReferenceEntry referenceentry)
                        {
/*3850*/                    if((referenceentry = referenceentry.getNextInAccessQueue()) == head)
/*3851*/                        return null;
/*3851*/                    else
/*3851*/                        return referenceentry;
                        }

                        protected volatile Object computeNext(Object obj)
                        {
/*3847*/                    return computeNext((ReferenceEntry)obj);
                        }

                        final AccessQueue this$0;

                        
                        {
/*3847*/                    this$0 = AccessQueue.this;
/*3847*/                    super(referenceentry);
                        }
            };
                }

                public final volatile Object peek()
                {
/*3731*/            return peek();
                }

                public final volatile Object poll()
                {
/*3731*/            return poll();
                }

                public final volatile boolean offer(Object obj)
                {
/*3731*/            return offer((ReferenceEntry)obj);
                }

                final ReferenceEntry head = new AbstractReferenceEntry() {

                    public long getAccessTime()
                    {
/*3736*/                return 0x7fffffffffffffffL;
                    }

                    public void setAccessTime(long l)
                    {
                    }

                    public ReferenceEntry getNextInAccessQueue()
                    {
/*3746*/                return nextAccess;
                    }

                    public void setNextInAccessQueue(ReferenceEntry referenceentry)
                    {
/*3751*/                nextAccess = referenceentry;
                    }

                    public ReferenceEntry getPreviousInAccessQueue()
                    {
/*3758*/                return previousAccess;
                    }

                    public void setPreviousInAccessQueue(ReferenceEntry referenceentry)
                    {
/*3763*/                previousAccess = referenceentry;
                    }

                    ReferenceEntry nextAccess;
                    ReferenceEntry previousAccess;
                    final AccessQueue this$0;

                        
                        {
/*3732*/                    this$0 = AccessQueue.this;
/*3732*/                    super();
/*3742*/                    nextAccess = this;
/*3754*/                    previousAccess = this;
                        }
        };

                AccessQueue()
                {
                }
    }

    static final class WriteQueue extends AbstractQueue
    {

                public final boolean offer(ReferenceEntry referenceentry)
                {
/*3635*/            LocalCache.connectWriteOrder(referenceentry.getPreviousInWriteQueue(), referenceentry.getNextInWriteQueue());
/*3638*/            LocalCache.connectWriteOrder(head.getPreviousInWriteQueue(), referenceentry);
/*3639*/            LocalCache.connectWriteOrder(referenceentry, head);
/*3641*/            return true;
                }

                public final ReferenceEntry peek()
                {
                    ReferenceEntry referenceentry;
/*3646*/            if((referenceentry = head.getNextInWriteQueue()) == head)
/*3647*/                return null;
/*3647*/            else
/*3647*/                return referenceentry;
                }

                public final ReferenceEntry poll()
                {
                    ReferenceEntry referenceentry;
/*3652*/            if((referenceentry = head.getNextInWriteQueue()) == head)
                    {
/*3654*/                return null;
                    } else
                    {
/*3657*/                remove(referenceentry);
/*3658*/                return referenceentry;
                    }
                }

                public final boolean remove(Object obj)
                {
/*3664*/            ReferenceEntry referenceentry = ((ReferenceEntry) (obj = (ReferenceEntry)obj)).getPreviousInWriteQueue();
/*3666*/            ReferenceEntry referenceentry1 = ((ReferenceEntry) (obj)).getNextInWriteQueue();
/*3667*/            LocalCache.connectWriteOrder(referenceentry, referenceentry1);
/*3668*/            LocalCache.nullifyWriteOrder(((ReferenceEntry) (obj)));
/*3670*/            return referenceentry1 != NullEntry.INSTANCE;
                }

                public final boolean contains(Object obj)
                {
/*3676*/            return ((ReferenceEntry) (obj = (ReferenceEntry)obj)).getNextInWriteQueue() != NullEntry.INSTANCE;
                }

                public final boolean isEmpty()
                {
/*3682*/            return head.getNextInWriteQueue() == head;
                }

                public final int size()
                {
/*3687*/            int i = 0;
/*3688*/            for(ReferenceEntry referenceentry = head.getNextInWriteQueue(); referenceentry != head; referenceentry = referenceentry.getNextInWriteQueue())
/*3690*/                i++;

/*3692*/            return i;
                }

                public final void clear()
                {
                    ReferenceEntry referenceentry1;
/*3697*/            for(ReferenceEntry referenceentry = head.getNextInWriteQueue(); referenceentry != head; referenceentry = referenceentry1)
                    {
/*3699*/                referenceentry1 = referenceentry.getNextInWriteQueue();
/*3700*/                LocalCache.nullifyWriteOrder(referenceentry);
                    }

/*3704*/            head.setNextInWriteQueue(head);
/*3705*/            head.setPreviousInWriteQueue(head);
                }

                public final Iterator iterator()
                {
/*3710*/            return new AbstractSequentialIterator(peek()) {

                        protected ReferenceEntry computeNext(ReferenceEntry referenceentry)
                        {
/*3713*/                    if((referenceentry = referenceentry.getNextInWriteQueue()) == head)
/*3714*/                        return null;
/*3714*/                    else
/*3714*/                        return referenceentry;
                        }

                        protected volatile Object computeNext(Object obj)
                        {
/*3710*/                    return computeNext((ReferenceEntry)obj);
                        }

                        final WriteQueue this$0;

                        
                        {
/*3710*/                    this$0 = WriteQueue.this;
/*3710*/                    super(referenceentry);
                        }
            };
                }

                public final volatile Object peek()
                {
/*3594*/            return peek();
                }

                public final volatile Object poll()
                {
/*3594*/            return poll();
                }

                public final volatile boolean offer(Object obj)
                {
/*3594*/            return offer((ReferenceEntry)obj);
                }

                final ReferenceEntry head = new AbstractReferenceEntry() {

                    public long getWriteTime()
                    {
/*3599*/                return 0x7fffffffffffffffL;
                    }

                    public void setWriteTime(long l)
                    {
                    }

                    public ReferenceEntry getNextInWriteQueue()
                    {
/*3609*/                return nextWrite;
                    }

                    public void setNextInWriteQueue(ReferenceEntry referenceentry)
                    {
/*3614*/                nextWrite = referenceentry;
                    }

                    public ReferenceEntry getPreviousInWriteQueue()
                    {
/*3621*/                return previousWrite;
                    }

                    public void setPreviousInWriteQueue(ReferenceEntry referenceentry)
                    {
/*3626*/                previousWrite = referenceentry;
                    }

                    ReferenceEntry nextWrite;
                    ReferenceEntry previousWrite;
                    final WriteQueue this$0;

                        
                        {
/*3595*/                    this$0 = WriteQueue.this;
/*3595*/                    super();
/*3605*/                    nextWrite = this;
/*3617*/                    previousWrite = this;
                        }
        };

                WriteQueue()
                {
                }
    }

    static class LoadingValueReference
        implements ValueReference
    {

                public boolean isLoading()
                {
/*3483*/            return true;
                }

                public boolean isActive()
                {
/*3488*/            return oldValue.isActive();
                }

                public int getWeight()
                {
/*3493*/            return oldValue.getWeight();
                }

                public boolean set(Object obj)
                {
/*3497*/            return futureValue.set(obj);
                }

                public boolean setException(Throwable throwable)
                {
/*3501*/            return futureValue.setException(throwable);
                }

                private ListenableFuture fullyFailedFuture(Throwable throwable)
                {
/*3505*/            return Futures.immediateFailedFuture(throwable);
                }

                public void notifyNewValue(Object obj)
                {
/*3510*/            if(obj != null)
                    {
/*3513*/                set(obj);
/*3513*/                return;
                    } else
                    {
/*3516*/                oldValue = LocalCache.unset();
/*3520*/                return;
                    }
                }

                public ListenableFuture loadFuture(Object obj, CacheLoader cacheloader)
                {
                    Object obj1;
/*3523*/            stopwatch.start();
/*3524*/            obj1 = oldValue.get();
/*3526*/            if(obj1 != null)
/*3527*/                break MISSING_BLOCK_LABEL_46;
/*3527*/            obj = cacheloader.load(obj);
/*3528*/            if(set(obj))
/*3528*/                return futureValue;
/*3528*/            return Futures.immediateFuture(obj);
/*3530*/            if((obj = cacheloader.reload(obj, obj1)) == null)
/*3532*/                return Futures.immediateFuture(null);
/*3536*/            return Futures.transform(((ListenableFuture) (obj)), new Function() {

                        public Object apply(Object obj2)
                        {
/*3539*/                    set(obj2);
/*3540*/                    return obj2;
                        }

                        final LoadingValueReference this$0;

                        
                        {
/*3536*/                    this$0 = LoadingValueReference.this;
/*3536*/                    super();
                        }
            });
/*3543*/            JVM INSTR dup ;
/*3544*/            obj;
/*3544*/            JVM INSTR instanceof #1   <Class InterruptedException>;
/*3544*/            JVM INSTR ifeq 89;
                       goto _L1 _L2
_L1:
/*3545*/            break MISSING_BLOCK_LABEL_83;
_L2:
/*3545*/            break MISSING_BLOCK_LABEL_89;
/*3545*/            Thread.currentThread().interrupt();
/*3547*/            if(setException(((Throwable) (obj))))
/*3547*/                return futureValue;
/*3547*/            else
/*3547*/                return fullyFailedFuture(((Throwable) (obj)));
                }

                public long elapsedNanos()
                {
/*3552*/            return stopwatch.elapsed(TimeUnit.NANOSECONDS);
                }

                public Object waitForValue()
                    throws ExecutionException
                {
/*3557*/            return Uninterruptibles.getUninterruptibly(futureValue);
                }

                public Object get()
                {
/*3562*/            return oldValue.get();
                }

                public ValueReference getOldValue()
                {
/*3566*/            return oldValue;
                }

                public ReferenceEntry getEntry()
                {
/*3571*/            return null;
                }

                public ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*3577*/            return this;
                }

                volatile ValueReference oldValue;
                final SettableFuture futureValue;
                final Stopwatch stopwatch;

                public LoadingValueReference()
                {
/*3474*/            this(LocalCache.unset());
                }

                public LoadingValueReference(ValueReference valuereference)
                {
/*3470*/            futureValue = SettableFuture.create();
/*3471*/            stopwatch = Stopwatch.createUnstarted();
/*3478*/            oldValue = valuereference;
                }
    }

    static class Segment extends ReentrantLock
    {

                AtomicReferenceArray newEntryArray(int i)
                {
/*2116*/            return new AtomicReferenceArray(i);
                }

                void initTable(AtomicReferenceArray atomicreferencearray)
                {
/*2120*/            threshold = (atomicreferencearray.length() * 3) / 4;
/*2121*/            if(!map.customWeigher() && (long)threshold == maxSegmentWeight)
/*2123*/                threshold++;
/*2125*/            table = atomicreferencearray;
                }

                ReferenceEntry newEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/*2130*/            return map.entryFactory.newEntry(this, Preconditions.checkNotNull(obj), i, referenceentry);
                }

                ReferenceEntry copyEntry(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/*2139*/            if(referenceentry.getKey() == null)
/*2141*/                return null;
                    ValueReference valuereference;
                    Object obj;
/*2144*/            if((obj = (valuereference = referenceentry.getValueReference()).get()) == null && valuereference.isActive())
                    {
/*2148*/                return null;
                    } else
                    {
/*2151*/                (referenceentry = map.entryFactory.copyEntry(this, referenceentry, referenceentry1)).setValueReference(valuereference.copyFor(valueReferenceQueue, obj, referenceentry));
/*2153*/                return referenceentry;
                    }
                }

                void setValue(ReferenceEntry referenceentry, Object obj, Object obj1, long l)
                {
/*2161*/            ValueReference valuereference = referenceentry.getValueReference();
/*2162*/            Preconditions.checkState((obj = map.weigher.weigh(obj, obj1)) >= 0, "Weights must be non-negative");
/*2165*/            ValueReference valuereference1 = map.valueStrength.referenceValue(this, referenceentry, obj1, ((int) (obj)));
/*2167*/            referenceentry.setValueReference(valuereference1);
/*2168*/            recordWrite(referenceentry, ((int) (obj)), l);
/*2169*/            valuereference.notifyNewValue(obj1);
                }

                Object get(Object obj, int i, CacheLoader cacheloader)
                    throws ExecutionException
                {
/*2175*/            Preconditions.checkNotNull(obj);
/*2176*/            Preconditions.checkNotNull(cacheloader);
                    Object obj1;
                    Object obj2;
/*2178*/            if(count == 0 || (obj1 = getEntry(obj, i)) == null)
/*2182*/                break MISSING_BLOCK_LABEL_129;
/*2182*/            long l = map.ticker.read();
                    Object obj3;
/*2183*/            if((obj3 = getLiveValue(((ReferenceEntry) (obj1)), l)) == null)
/*2185*/                break MISSING_BLOCK_LABEL_95;
/*2185*/            recordRead(((ReferenceEntry) (obj1)), l);
/*2186*/            statsCounter.recordHits(1);
/*2187*/            obj2 = scheduleRefresh(((ReferenceEntry) (obj1)), obj, i, obj3, l, cacheloader);
/*2207*/            postReadCleanup();
/*2207*/            return obj2;
                    ValueReference valuereference;
/*2189*/            if(!(valuereference = ((ReferenceEntry) (obj1)).getValueReference()).isLoading())
/*2191*/                break MISSING_BLOCK_LABEL_129;
/*2191*/            obj = waitForLoadingValue(((ReferenceEntry) (obj1)), obj, valuereference);
/*2207*/            postReadCleanup();
/*2207*/            return obj;
/*2197*/            obj1 = lockedGetOrLoad(obj, i, cacheloader);
/*2207*/            postReadCleanup();
/*2207*/            return obj1;
/*2198*/            JVM INSTR dup ;
/*2199*/            obj1;
/*2199*/            getCause();
/*2199*/            JVM INSTR dup ;
/*2200*/            obj;
/*2200*/            JVM INSTR instanceof #7   <Class Error>;
/*2200*/            JVM INSTR ifeq 171;
                       goto _L1 _L2
_L1:
/*2201*/            break MISSING_BLOCK_LABEL_159;
_L2:
/*2201*/            break MISSING_BLOCK_LABEL_171;
/*2201*/            throw new ExecutionError((Error)obj);
/*2202*/            if(obj instanceof RuntimeException)
/*2203*/                throw new UncheckedExecutionException(((Throwable) (obj)));
/*2205*/            else
/*2205*/                throw obj1;
/*2207*/            obj;
/*2207*/            postReadCleanup();
/*2207*/            throw obj;
                }

                Object lockedGetOrLoad(Object obj, int i, CacheLoader cacheloader)
                    throws ExecutionException
                {
                    LoadingValueReference loadingvaluereference;
                    boolean flag;
/*2214*/            obj1 = null;
/*2215*/            loadingvaluereference = null;
/*2216*/            flag = true;
/*2218*/            lock();
                    long l;
                    int j;
                    AtomicReferenceArray atomicreferencearray;
                    int k;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*2221*/            l = map.ticker.read();
/*2222*/            preWriteCleanup(l);
/*2224*/            j = count - 1;
/*2225*/            atomicreferencearray = table;
/*2226*/            k = i & atomicreferencearray.length() - 1;
/*2227*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(k);
_L6:
                    Object obj2;
/*2229*/            if(referenceentry1 == null)
/*2230*/                break MISSING_BLOCK_LABEL_274;
/*2230*/            obj2 = referenceentry1.getKey();
/*2231*/            if(referenceentry1.getHash() != i || obj2 == null || !map.keyEquivalence.equivalent(obj, obj2))
/*2233*/                break MISSING_BLOCK_LABEL_262;
/*2233*/            if(((ValueReference) (obj1 = referenceentry1.getValueReference())).isLoading())
                    {
/*2235*/                flag = false;
/*2235*/                break MISSING_BLOCK_LABEL_274;
                    }
                    Object obj3;
/*2237*/            if((obj3 = ((ValueReference) (obj1)).get()) != null) goto _L2; else goto _L1
_L1:
/*2239*/            enqueueNotification(obj2, i, ((ValueReference) (obj1)), RemovalCause.COLLECTED);
                      goto _L3
_L2:
/*2240*/            if(!map.isExpired(referenceentry1, l)) goto _L5; else goto _L4
_L4:
/*2243*/            enqueueNotification(obj2, i, ((ValueReference) (obj1)), RemovalCause.EXPIRED);
                      goto _L3
_L5:
/*2245*/            recordLockedRead(referenceentry1, l);
/*2246*/            statsCounter.recordHits(1);
/*2248*/            obj = obj3;
/*2272*/            unlock();
/*2273*/            postWriteCleanup();
/*2273*/            return obj;
_L3:
/*2252*/            writeQueue.remove(referenceentry1);
/*2253*/            accessQueue.remove(referenceentry1);
/*2254*/            count = j;
/*2256*/            break MISSING_BLOCK_LABEL_274;
/*2229*/            referenceentry1 = referenceentry1.getNext();
                      goto _L6
/*2260*/            if(flag)
                    {
/*2261*/                loadingvaluereference = new LoadingValueReference();
/*2263*/                if(referenceentry1 == null)
                        {
/*2264*/                    (referenceentry1 = newEntry(obj, i, referenceentry)).setValueReference(loadingvaluereference);
/*2266*/                    atomicreferencearray.set(k, referenceentry1);
                        } else
                        {
/*2268*/                    referenceentry1.setValueReference(loadingvaluereference);
                        }
                    }
/*2272*/            unlock();
/*2273*/            postWriteCleanup();
/*2274*/            break MISSING_BLOCK_LABEL_354;
/*2272*/            obj;
/*2272*/            unlock();
/*2273*/            postWriteCleanup();
/*2273*/            throw obj;
/*2276*/            if(!flag)
/*2281*/                break MISSING_BLOCK_LABEL_409;
/*2281*/            synchronized(referenceentry1)
                    {
/*2282*/                obj = loadSync(obj, i, loadingvaluereference, cacheloader);
                    }
/*2285*/            statsCounter.recordMisses(1);
/*2285*/            return obj;
/*2285*/            obj;
/*2285*/            statsCounter.recordMisses(1);
/*2285*/            throw obj;
/*2289*/            return waitForLoadingValue(referenceentry1, obj, ((ValueReference) (obj1)));
                }

                Object waitForLoadingValue(ReferenceEntry referenceentry, Object obj, ValueReference valuereference)
                    throws ExecutionException
                {
/*2295*/            if(!valuereference.isLoading())
/*2296*/                throw new AssertionError();
/*2299*/            Preconditions.checkState(!Thread.holdsLock(referenceentry), "Recursive load of: %s", new Object[] {
/*2299*/                obj
                    });
/*2302*/            if((valuereference = ((ValueReference) (valuereference.waitForValue()))) == null)
                    {
/*2304*/                referenceentry = String.valueOf(String.valueOf(obj));
/*2304*/                throw new CacheLoader.InvalidCacheLoadException((new StringBuilder(35 + referenceentry.length())).append("CacheLoader returned null for key ").append(referenceentry).append(".").toString());
                    }
/*2307*/            long l = map.ticker.read();
/*2308*/            recordRead(referenceentry, l);
/*2309*/            referenceentry = valuereference;
/*2311*/            statsCounter.recordMisses(1);
/*2311*/            return referenceentry;
/*2311*/            referenceentry;
/*2311*/            statsCounter.recordMisses(1);
/*2311*/            throw referenceentry;
                }

                Object loadSync(Object obj, int i, LoadingValueReference loadingvaluereference, CacheLoader cacheloader)
                    throws ExecutionException
                {
/*2319*/            cacheloader = loadingvaluereference.loadFuture(obj, cacheloader);
/*2320*/            return getAndRecordStats(obj, i, loadingvaluereference, cacheloader);
                }

                ListenableFuture loadAsync(final Object key, final int hash, final LoadingValueReference loadingValueReference, final CacheLoader loadingFuture)
                {
/*2325*/            (loadingFuture = loadingValueReference.loadFuture(key, loadingFuture)).addListener(new Runnable() {

                        public void run()
                        {
/*2331*/                    try
                            {
/*2331*/                        getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
/*2335*/                        return;
                            }
/*2332*/                    catch(Throwable throwable)
                            {
/*2333*/                        LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", throwable);
/*2334*/                        loadingValueReference.setException(throwable);
/*2336*/                        return;
                            }
                        }

                        final Object val$key;
                        final int val$hash;
                        final LoadingValueReference val$loadingValueReference;
                        final ListenableFuture val$loadingFuture;
                        final Segment this$0;

                        
                        {
/*2327*/                    this$0 = Segment.this;
/*2327*/                    key = obj;
/*2327*/                    hash = i;
/*2327*/                    loadingValueReference = loadingvaluereference;
/*2327*/                    loadingFuture = listenablefuture;
/*2327*/                    super();
                        }
            }, MoreExecutors.directExecutor());
/*2338*/            return loadingFuture;
                }

                Object getAndRecordStats(Object obj, int i, LoadingValueReference loadingvaluereference, ListenableFuture listenablefuture)
                    throws ExecutionException
                {
/*2346*/            Object obj1 = null;
/*2348*/            if((obj1 = Uninterruptibles.getUninterruptibly(listenablefuture)) == null)
                    {
/*2350*/                listenablefuture = String.valueOf(String.valueOf(obj));
/*2350*/                throw new CacheLoader.InvalidCacheLoadException((new StringBuilder(35 + listenablefuture.length())).append("CacheLoader returned null for key ").append(listenablefuture).append(".").toString());
                    }
/*2352*/            statsCounter.recordLoadSuccess(loadingvaluereference.elapsedNanos());
/*2353*/            storeLoadedValue(obj, i, loadingvaluereference, obj1);
/*2354*/            listenablefuture = ((ListenableFuture) (obj1));
/*2356*/            if(obj1 == null)
                    {
/*2357*/                statsCounter.recordLoadException(loadingvaluereference.elapsedNanos());
/*2358*/                removeLoadingValue(obj, i, loadingvaluereference);
                    }
/*2358*/            return listenablefuture;
/*2356*/            listenablefuture;
/*2356*/            if(obj1 == null)
                    {
/*2357*/                statsCounter.recordLoadException(loadingvaluereference.elapsedNanos());
/*2358*/                removeLoadingValue(obj, i, loadingvaluereference);
                    }
/*2358*/            throw listenablefuture;
                }

                Object scheduleRefresh(ReferenceEntry referenceentry, Object obj, int i, Object obj1, long l, CacheLoader cacheloader)
                {
/*2365*/            if(map.refreshes() && l - referenceentry.getWriteTime() > map.refreshNanos && !referenceentry.getValueReference().isLoading() && (referenceentry = ((ReferenceEntry) (refresh(obj, i, cacheloader, true)))) != null)
/*2369*/                return referenceentry;
/*2372*/            else
/*2372*/                return obj1;
                }

                Object refresh(Object obj, int i, CacheLoader cacheloader, boolean flag)
                {
/*2383*/            if((flag = insertLoadingValueReference(obj, i, flag)) == null)
/*2386*/                return null;
/*2389*/            if(!((ListenableFuture) (obj = loadAsync(obj, i, flag, cacheloader))).isDone())
/*2392*/                break MISSING_BLOCK_LABEL_41;
/*2392*/            return Uninterruptibles.getUninterruptibly(((java.util.concurrent.Future) (obj)));
/*2393*/            JVM INSTR pop ;
/*2397*/            return null;
                }

                LoadingValueReference insertLoadingValueReference(Object obj, int i, boolean flag)
                {
/*2408*/            lock();
                    ReferenceEntry referenceentry;
                    long l;
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry2;
/*2410*/            l = map.ticker.read();
/*2411*/            preWriteCleanup(l);
/*2413*/            atomicreferencearray = table;
/*2414*/            j = i & atomicreferencearray.length() - 1;
/*2415*/            referenceentry = referenceentry2 = (ReferenceEntry)atomicreferencearray.get(j);
_L1:
/*2418*/            if(referenceentry == null)
/*2419*/                break MISSING_BLOCK_LABEL_203;
/*2419*/            Object obj1 = referenceentry.getKey();
/*2420*/            if(referenceentry.getHash() != i || obj1 == null || !map.keyEquivalence.equivalent(obj, obj1))
/*2424*/                break MISSING_BLOCK_LABEL_191;
/*2424*/            if(((ValueReference) (obj = referenceentry.getValueReference())).isLoading() || flag && l - referenceentry.getWriteTime() < map.refreshNanos)
                    {
/*2449*/                unlock();
/*2450*/                postWriteCleanup();
/*2450*/                return null;
                    }
/*2434*/            modCount++;
/*2435*/            obj = new LoadingValueReference(((ValueReference) (obj)));
/*2437*/            referenceentry.setValueReference(((ValueReference) (obj)));
/*2438*/            obj = obj;
/*2449*/            unlock();
/*2450*/            postWriteCleanup();
/*2450*/            return ((LoadingValueReference) (obj));
/*2418*/            referenceentry = referenceentry.getNext();
                      goto _L1
/*2442*/            modCount++;
/*2443*/            LoadingValueReference loadingvaluereference = new LoadingValueReference();
                    ReferenceEntry referenceentry1;
/*2444*/            (referenceentry1 = newEntry(obj, i, referenceentry2)).setValueReference(loadingvaluereference);
/*2446*/            atomicreferencearray.set(j, referenceentry1);
/*2447*/            obj = loadingvaluereference;
/*2449*/            unlock();
/*2450*/            postWriteCleanup();
/*2450*/            return ((LoadingValueReference) (obj));
/*2449*/            obj;
/*2449*/            unlock();
/*2450*/            postWriteCleanup();
/*2450*/            throw obj;
                }

                void tryDrainReferenceQueues()
                {
/*2460*/            if(!tryLock())
/*2462*/                break MISSING_BLOCK_LABEL_23;
/*2462*/            drainReferenceQueues();
/*2464*/            unlock();
/*2465*/            return;
                    Exception exception;
/*2464*/            exception;
/*2464*/            unlock();
/*2464*/            throw exception;
                }

                void drainReferenceQueues()
                {
/*2475*/            if(map.usesKeyReferences())
/*2476*/                drainKeyReferenceQueue();
/*2478*/            if(map.usesValueReferences())
/*2479*/                drainValueReferenceQueue();
                }

                void drainKeyReferenceQueue()
                {
/*2486*/            int i = 0;
/*2487*/            do
                    {
                        Object obj;
/*2487*/                if((obj = keyReferenceQueue.poll()) == null)
/*2489*/                    break;
/*2489*/                obj = (ReferenceEntry)obj;
/*2490*/                map.reclaimKey(((ReferenceEntry) (obj)));
                    } while(++i != 16);
                }

                void drainValueReferenceQueue()
                {
/*2500*/            int i = 0;
/*2501*/            do
                    {
                        Object obj;
/*2501*/                if((obj = valueReferenceQueue.poll()) == null)
/*2503*/                    break;
/*2503*/                obj = (ValueReference)obj;
/*2504*/                map.reclaimValue(((ValueReference) (obj)));
                    } while(++i != 16);
                }

                void clearReferenceQueues()
                {
/*2515*/            if(map.usesKeyReferences())
/*2516*/                clearKeyReferenceQueue();
/*2518*/            if(map.usesValueReferences())
/*2519*/                clearValueReferenceQueue();
                }

                void clearKeyReferenceQueue()
                {
/*2524*/            while(keyReferenceQueue.poll() != null) ;
                }

                void clearValueReferenceQueue()
                {
/*2528*/            while(valueReferenceQueue.poll() != null) ;
                }

                void recordRead(ReferenceEntry referenceentry, long l)
                {
/*2541*/            if(map.recordsAccess())
/*2542*/                referenceentry.setAccessTime(l);
/*2544*/            recencyQueue.add(referenceentry);
                }

                void recordLockedRead(ReferenceEntry referenceentry, long l)
                {
/*2556*/            if(map.recordsAccess())
/*2557*/                referenceentry.setAccessTime(l);
/*2559*/            accessQueue.add(referenceentry);
                }

                void recordWrite(ReferenceEntry referenceentry, int i, long l)
                {
/*2569*/            drainRecencyQueue();
/*2570*/            totalWeight += i;
/*2572*/            if(map.recordsAccess())
/*2573*/                referenceentry.setAccessTime(l);
/*2575*/            if(map.recordsWrite())
/*2576*/                referenceentry.setWriteTime(l);
/*2578*/            accessQueue.add(referenceentry);
/*2579*/            writeQueue.add(referenceentry);
                }

                void drainRecencyQueue()
                {
/*2591*/            do
                    {
                        ReferenceEntry referenceentry;
/*2591*/                if((referenceentry = (ReferenceEntry)recencyQueue.poll()) == null)
/*2596*/                    break;
/*2596*/                if(accessQueue.contains(referenceentry))
/*2597*/                    accessQueue.add(referenceentry);
                    } while(true);
                }

                void tryExpireEntries(long l)
                {
/*2608*/            if(!tryLock())
/*2610*/                break MISSING_BLOCK_LABEL_24;
/*2610*/            expireEntries(l);
/*2612*/            unlock();
/*2614*/            return;
/*2612*/            l;
/*2612*/            unlock();
/*2612*/            throw l;
                }

                void expireEntries(long l)
                {
/*2620*/            drainRecencyQueue();
                    ReferenceEntry referenceentry;
/*2623*/            while((referenceentry = (ReferenceEntry)writeQueue.peek()) != null && map.isExpired(referenceentry, l)) 
/*2624*/                if(!removeEntry(referenceentry, referenceentry.getHash(), RemovalCause.EXPIRED))
/*2625*/                    throw new AssertionError();
/*2628*/            while((referenceentry = (ReferenceEntry)accessQueue.peek()) != null && map.isExpired(referenceentry, l)) 
/*2629*/                if(!removeEntry(referenceentry, referenceentry.getHash(), RemovalCause.EXPIRED))
/*2630*/                    throw new AssertionError();
                }

                void enqueueNotification(ReferenceEntry referenceentry, RemovalCause removalcause)
                {
/*2639*/            enqueueNotification(referenceentry.getKey(), referenceentry.getHash(), referenceentry.getValueReference(), removalcause);
                }

                void enqueueNotification(Object obj, int i, ValueReference valuereference, RemovalCause removalcause)
                {
/*2645*/            totalWeight -= valuereference.getWeight();
/*2646*/            if(removalcause.wasEvicted())
/*2647*/                statsCounter.recordEviction();
/*2649*/            if(map.removalNotificationQueue != LocalCache.DISCARDING_QUEUE)
                    {
/*2650*/                i = ((int) (valuereference.get()));
/*2651*/                obj = new RemovalNotification(obj, i, removalcause);
/*2652*/                map.removalNotificationQueue.offer(obj);
                    }
                }

                void evictEntries()
                {
/*2662*/            if(!map.evictsBySize())
/*2663*/                return;
/*2666*/            drainRecencyQueue();
/*2667*/            while(totalWeight > maxSegmentWeight) 
                    {
/*2668*/                ReferenceEntry referenceentry = getNextEvictable();
/*2669*/                if(!removeEntry(referenceentry, referenceentry.getHash(), RemovalCause.SIZE))
/*2670*/                    throw new AssertionError();
                    }
                }

                ReferenceEntry getNextEvictable()
                {
                    ReferenceEntry referenceentry;
                    int i;
/*2678*/            for(Iterator iterator = accessQueue.iterator(); iterator.hasNext();)
/*2678*/                if((i = (referenceentry = (ReferenceEntry)iterator.next()).getValueReference().getWeight()) > 0)
/*2681*/                    return referenceentry;

/*2684*/            throw new AssertionError();
                }

                ReferenceEntry getFirst(int i)
                {
                    AtomicReferenceArray atomicreferencearray;
/*2692*/            return (ReferenceEntry)(atomicreferencearray = table).get(i & atomicreferencearray.length() - 1);
                }

                ReferenceEntry getEntry(Object obj, int i)
                {
/*2700*/            for(ReferenceEntry referenceentry = getFirst(i); referenceentry != null; referenceentry = referenceentry.getNext())
                    {
/*2701*/                if(referenceentry.getHash() != i)
/*2705*/                    continue;
                        Object obj1;
/*2705*/                if((obj1 = referenceentry.getKey()) == null)
                        {
/*2707*/                    tryDrainReferenceQueues();
/*2708*/                    continue;
                        }
/*2711*/                if(map.keyEquivalence.equivalent(obj, obj1))
/*2712*/                    return referenceentry;
                    }

/*2716*/            return null;
                }

                ReferenceEntry getLiveEntry(Object obj, int i, long l)
                {
/*2721*/            if((obj = getEntry(obj, i)) == null)
/*2723*/                return null;
/*2724*/            if(map.isExpired(((ReferenceEntry) (obj)), l))
                    {
/*2725*/                tryExpireEntries(l);
/*2726*/                return null;
                    } else
                    {
/*2728*/                return ((ReferenceEntry) (obj));
                    }
                }

                Object getLiveValue(ReferenceEntry referenceentry, long l)
                {
/*2736*/            if(referenceentry.getKey() == null)
                    {
/*2737*/                tryDrainReferenceQueues();
/*2738*/                return null;
                    }
                    Object obj;
/*2740*/            if((obj = referenceentry.getValueReference().get()) == null)
                    {
/*2742*/                tryDrainReferenceQueues();
/*2743*/                return null;
                    }
/*2746*/            if(map.isExpired(referenceentry, l))
                    {
/*2747*/                tryExpireEntries(l);
/*2748*/                return null;
                    } else
                    {
/*2750*/                return obj;
                    }
                }

                Object get(Object obj, int i)
                {
                    long l;
/*2756*/            if(count == 0)
/*2757*/                break MISSING_BLOCK_LABEL_92;
/*2757*/            l = map.ticker.read();
/*2758*/            if((obj = getLiveEntry(obj, i, l)) == null)
                    {
/*2772*/                postReadCleanup();
/*2772*/                return null;
                    }
                    Object obj1;
/*2763*/            if((obj1 = ((ReferenceEntry) (obj)).getValueReference().get()) == null)
/*2765*/                break MISSING_BLOCK_LABEL_88;
/*2765*/            recordRead(((ReferenceEntry) (obj)), l);
/*2766*/            obj = scheduleRefresh(((ReferenceEntry) (obj)), ((ReferenceEntry) (obj)).getKey(), i, obj1, l, map.defaultLoader);
/*2772*/            postReadCleanup();
/*2772*/            return obj;
/*2768*/            tryDrainReferenceQueues();
/*2772*/            postReadCleanup();
/*2772*/            return null;
/*2772*/            obj;
/*2772*/            postReadCleanup();
/*2772*/            throw obj;
                }

                boolean containsKey(Object obj, int i)
                {
                    long l;
/*2778*/            if(count == 0)
/*2779*/                break MISSING_BLOCK_LABEL_62;
/*2779*/            l = map.ticker.read();
/*2780*/            if((obj = getLiveEntry(obj, i, l)) == null)
                    {
/*2789*/                postReadCleanup();
/*2789*/                return false;
                    }
/*2784*/            obj = ((ReferenceEntry) (obj)).getValueReference().get() == null ? 0 : 1;
/*2789*/            postReadCleanup();
/*2789*/            return ((boolean) (obj));
/*2789*/            postReadCleanup();
/*2789*/            return false;
/*2789*/            obj;
/*2789*/            postReadCleanup();
/*2789*/            throw obj;
                }

                Object put(Object obj, int i, Object obj1, boolean flag)
                {
/*2825*/            lock();
                    long l;
                    ValueReference valuereference;
                    int i1;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*2827*/            l = map.ticker.read();
/*2828*/            preWriteCleanup(l);
                    int j;
/*2830*/            if((j = count + 1) > threshold)
                    {
/*2832*/                expand();
/*2833*/                count;
                    }
/*2836*/            valuereference = table;
/*2837*/            i1 = i & valuereference.length() - 1;
/*2838*/            referenceentry1 = referenceentry = (ReferenceEntry)valuereference.get(i1);
_L3:
/*2841*/            if(referenceentry1 == null)
/*2842*/                break MISSING_BLOCK_LABEL_320;
/*2842*/            Object obj2 = referenceentry1.getKey();
/*2843*/            if(referenceentry1.getHash() != i || obj2 == null || !map.keyEquivalence.equivalent(obj, obj2))
/*2847*/                break MISSING_BLOCK_LABEL_308;
/*2847*/            if((i1 = ((int) ((valuereference = referenceentry1.getValueReference()).get()))) != null) goto _L2; else goto _L1
_L1:
/*2851*/            modCount++;
/*2852*/            if(valuereference.isActive())
                    {
/*2853*/                enqueueNotification(obj, i, valuereference, RemovalCause.COLLECTED);
/*2854*/                setValue(referenceentry1, obj, obj1, l);
/*2855*/                valuereference = count;
                    } else
                    {
/*2857*/                setValue(referenceentry1, obj, obj1, l);
/*2858*/                valuereference = count + 1;
                    }
/*2860*/            count = valuereference;
/*2861*/            evictEntries();
/*2890*/            unlock();
/*2891*/            postWriteCleanup();
/*2891*/            return null;
_L2:
/*2863*/            if(!flag)
/*2867*/                break MISSING_BLOCK_LABEL_260;
/*2867*/            recordLockedRead(referenceentry1, l);
/*2868*/            obj = i1;
/*2890*/            unlock();
/*2891*/            postWriteCleanup();
/*2891*/            return obj;
/*2871*/            modCount++;
/*2872*/            enqueueNotification(obj, i, valuereference, RemovalCause.REPLACED);
/*2873*/            setValue(referenceentry1, obj, obj1, l);
/*2874*/            evictEntries();
/*2875*/            obj = i1;
/*2890*/            unlock();
/*2891*/            postWriteCleanup();
/*2891*/            return obj;
/*2841*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*2881*/            modCount++;
/*2882*/            ReferenceEntry referenceentry2 = newEntry(obj, i, referenceentry);
/*2883*/            setValue(referenceentry2, obj, obj1, l);
/*2884*/            valuereference.set(i1, referenceentry2);
/*2885*/            int k = count + 1;
/*2886*/            count = k;
/*2887*/            evictEntries();
/*2890*/            unlock();
/*2891*/            postWriteCleanup();
/*2891*/            return null;
/*2890*/            obj;
/*2890*/            unlock();
/*2891*/            postWriteCleanup();
/*2891*/            throw obj;
                }

                void expand()
                {
                    AtomicReferenceArray atomicreferencearray;
                    int i;
/*2900*/            if((i = (atomicreferencearray = table).length()) >= 0x40000000)
/*2903*/                return;
/*2916*/            int j = count;
/*2917*/            AtomicReferenceArray atomicreferencearray1 = newEntryArray(i << 1);
/*2918*/            threshold = (atomicreferencearray1.length() * 3) / 4;
/*2919*/            int k = atomicreferencearray1.length() - 1;
/*2920*/            for(int l = 0; l < i; l++)
                    {
                        ReferenceEntry referenceentry;
/*2923*/                if((referenceentry = (ReferenceEntry)atomicreferencearray.get(l)) == null)
/*2926*/                    continue;
/*2926*/                ReferenceEntry referenceentry2 = referenceentry.getNext();
/*2927*/                int i1 = referenceentry.getHash() & k;
/*2930*/                if(referenceentry2 == null)
                        {
/*2931*/                    atomicreferencearray1.set(i1, referenceentry);
/*2931*/                    continue;
                        }
/*2936*/                ReferenceEntry referenceentry4 = referenceentry;
/*2937*/                i1 = i1;
/*2938*/                for(referenceentry2 = referenceentry2; referenceentry2 != null; referenceentry2 = referenceentry2.getNext())
                        {
                            int j1;
/*2939*/                    if((j1 = referenceentry2.getHash() & k) != i1)
                            {
/*2942*/                        i1 = j1;
/*2943*/                        referenceentry4 = referenceentry2;
                            }
                        }

/*2946*/                atomicreferencearray1.set(i1, referenceentry4);
/*2949*/                for(ReferenceEntry referenceentry3 = referenceentry; referenceentry3 != referenceentry4; referenceentry3 = referenceentry3.getNext())
                        {
/*2950*/                    int k1 = referenceentry3.getHash() & k;
/*2951*/                    ReferenceEntry referenceentry1 = (ReferenceEntry)atomicreferencearray1.get(k1);
/*2952*/                    if((referenceentry1 = copyEntry(referenceentry3, referenceentry1)) != null)
                            {
/*2954*/                        atomicreferencearray1.set(k1, referenceentry1);
                            } else
                            {
/*2956*/                        removeCollectedEntry(referenceentry3);
/*2957*/                        j--;
                            }
                        }

                    }

/*2963*/            table = atomicreferencearray1;
/*2964*/            count = j;
                }

                boolean replace(Object obj, int i, Object obj1, Object obj2)
                {
/*2968*/            lock();
                    long l;
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*2970*/            l = map.ticker.read();
/*2971*/            preWriteCleanup(l);
/*2973*/            atomicreferencearray = table;
/*2974*/            j = i & atomicreferencearray.length() - 1;
/*2975*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L3:
                    Object obj3;
/*2977*/            if(referenceentry1 == null)
/*2978*/                break MISSING_BLOCK_LABEL_285;
/*2978*/            obj3 = referenceentry1.getKey();
/*2979*/            if(referenceentry1.getHash() != i || obj3 == null || !map.keyEquivalence.equivalent(obj, obj3))
/*2981*/                break MISSING_BLOCK_LABEL_273;
                    ValueReference valuereference;
                    Object obj4;
/*2981*/            if((obj4 = (valuereference = referenceentry1.getValueReference()).get()) != null) goto _L2; else goto _L1
_L1:
/*2984*/            if(valuereference.isActive())
                    {
/*2986*/                count;
/*2987*/                modCount++;
/*2988*/                i = removeValueFromChain(referenceentry, referenceentry1, obj3, i, valuereference, RemovalCause.COLLECTED);
/*2990*/                obj = count - 1;
/*2991*/                atomicreferencearray.set(j, i);
/*2992*/                count = ((int) (obj));
                    }
/*3014*/            unlock();
/*3015*/            postWriteCleanup();
/*3015*/            return false;
_L2:
/*2997*/            if(!map.valueEquivalence.equivalent(obj1, obj4))
/*2998*/                break MISSING_BLOCK_LABEL_255;
/*2998*/            modCount++;
/*2999*/            enqueueNotification(obj, i, valuereference, RemovalCause.REPLACED);
/*3000*/            setValue(referenceentry1, obj, obj2, l);
/*3001*/            evictEntries();
/*3014*/            unlock();
/*3015*/            postWriteCleanup();
/*3015*/            return true;
/*3006*/            recordLockedRead(referenceentry1, l);
/*3014*/            unlock();
/*3015*/            postWriteCleanup();
/*3015*/            return false;
/*2977*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*3014*/            unlock();
/*3015*/            postWriteCleanup();
/*3015*/            return false;
/*3014*/            obj;
/*3014*/            unlock();
/*3015*/            postWriteCleanup();
/*3015*/            throw obj;
                }

                Object replace(Object obj, int i, Object obj1)
                {
/*3021*/            lock();
                    long l;
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*3023*/            l = map.ticker.read();
/*3024*/            preWriteCleanup(l);
/*3026*/            atomicreferencearray = table;
/*3027*/            j = i & atomicreferencearray.length() - 1;
/*3028*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L3:
                    Object obj2;
/*3030*/            if(referenceentry1 == null)
/*3031*/                break MISSING_BLOCK_LABEL_253;
/*3031*/            obj2 = referenceentry1.getKey();
/*3032*/            if(referenceentry1.getHash() != i || obj2 == null || !map.keyEquivalence.equivalent(obj, obj2))
/*3034*/                break MISSING_BLOCK_LABEL_241;
                    ValueReference valuereference;
                    Object obj3;
/*3034*/            if((obj3 = (valuereference = referenceentry1.getValueReference()).get()) != null) goto _L2; else goto _L1
_L1:
/*3037*/            if(valuereference.isActive())
                    {
/*3039*/                count;
/*3040*/                modCount++;
/*3041*/                i = removeValueFromChain(referenceentry, referenceentry1, obj2, i, valuereference, RemovalCause.COLLECTED);
/*3043*/                obj = count - 1;
/*3044*/                atomicreferencearray.set(j, i);
/*3045*/                count = ((int) (obj));
                    }
/*3060*/            unlock();
/*3061*/            postWriteCleanup();
/*3061*/            return null;
_L2:
/*3050*/            modCount++;
/*3051*/            enqueueNotification(obj, i, valuereference, RemovalCause.REPLACED);
/*3052*/            setValue(referenceentry1, obj, obj1, l);
/*3053*/            evictEntries();
/*3054*/            obj = obj3;
/*3060*/            unlock();
/*3061*/            postWriteCleanup();
/*3061*/            return obj;
/*3030*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*3060*/            unlock();
/*3061*/            postWriteCleanup();
/*3061*/            return null;
/*3060*/            obj;
/*3060*/            unlock();
/*3061*/            postWriteCleanup();
/*3061*/            throw obj;
                }

                Object remove(Object obj, int i)
                {
/*3067*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*3069*/            long l = map.ticker.read();
/*3070*/            preWriteCleanup(l);
/*3072*/            count;
/*3073*/            atomicreferencearray = table;
/*3074*/            j = i & atomicreferencearray.length() - 1;
/*3075*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L6:
                    Object obj1;
/*3077*/            if(referenceentry1 == null)
/*3078*/                break MISSING_BLOCK_LABEL_224;
/*3078*/            obj1 = referenceentry1.getKey();
/*3079*/            if(referenceentry1.getHash() != i || obj1 == null || !map.keyEquivalence.equivalent(obj, obj1))
/*3081*/                break MISSING_BLOCK_LABEL_212;
                    Object obj2;
/*3081*/            if((obj2 = ((ValueReference) (obj = referenceentry1.getValueReference())).get()) == null) goto _L2; else goto _L1
_L1:
/*3086*/            RemovalCause removalcause = RemovalCause.EXPLICIT;
                      goto _L3
_L2:
/*3087*/            if(!((ValueReference) (obj)).isActive()) goto _L5; else goto _L4
_L4:
/*3088*/            removalcause = RemovalCause.COLLECTED;
                      goto _L3
_L5:
/*3106*/            unlock();
/*3107*/            postWriteCleanup();
/*3107*/            return null;
_L3:
/*3094*/            modCount++;
/*3095*/            i = removeValueFromChain(referenceentry, referenceentry1, obj1, i, ((ValueReference) (obj)), removalcause);
/*3097*/            obj = count - 1;
/*3098*/            atomicreferencearray.set(j, i);
/*3099*/            count = ((int) (obj));
/*3100*/            obj = obj2;
/*3106*/            unlock();
/*3107*/            postWriteCleanup();
/*3107*/            return obj;
/*3077*/            referenceentry1 = referenceentry1.getNext();
                      goto _L6
/*3106*/            unlock();
/*3107*/            postWriteCleanup();
/*3107*/            return null;
/*3106*/            obj;
/*3106*/            unlock();
/*3107*/            postWriteCleanup();
/*3107*/            throw obj;
                }

                boolean storeLoadedValue(Object obj, int i, LoadingValueReference loadingvaluereference, Object obj1)
                {
/*3113*/            lock();
                    long l;
                    int j;
                    Object obj2;
                    int k;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*3115*/            l = map.ticker.read();
/*3116*/            preWriteCleanup(l);
/*3118*/            if((j = count + 1) > threshold)
                    {
/*3120*/                expand();
/*3121*/                j = count + 1;
                    }
/*3124*/            obj2 = table;
/*3125*/            k = i & ((AtomicReferenceArray) (obj2)).length() - 1;
/*3126*/            referenceentry1 = referenceentry = (ReferenceEntry)((AtomicReferenceArray) (obj2)).get(k);
_L3:
/*3128*/            if(referenceentry1 == null)
/*3129*/                break MISSING_BLOCK_LABEL_285;
/*3129*/            Object obj3 = referenceentry1.getKey();
/*3130*/            if(referenceentry1.getHash() != i || obj3 == null || !map.keyEquivalence.equivalent(obj, obj3))
/*3132*/                break MISSING_BLOCK_LABEL_273;
/*3132*/            k = ((int) (((ValueReference) (obj2 = referenceentry1.getValueReference())).get()));
/*3136*/            if(loadingvaluereference != obj2 && (k != null || obj2 == LocalCache.UNSET)) goto _L2; else goto _L1
_L1:
/*3138*/            modCount++;
/*3139*/            if(loadingvaluereference.isActive())
                    {
/*3140*/                obj2 = k != null ? ((Object) (RemovalCause.REPLACED)) : ((Object) (RemovalCause.COLLECTED));
/*3142*/                enqueueNotification(obj, i, loadingvaluereference, ((RemovalCause) (obj2)));
/*3143*/                j--;
                    }
/*3145*/            setValue(referenceentry1, obj, obj1, l);
/*3146*/            count = j;
/*3147*/            evictEntries();
/*3166*/            unlock();
/*3167*/            postWriteCleanup();
/*3167*/            return true;
_L2:
/*3152*/            obj2 = new WeightedStrongValueReference(obj1, 0);
/*3153*/            enqueueNotification(obj, i, ((ValueReference) (obj2)), RemovalCause.REPLACED);
/*3166*/            unlock();
/*3167*/            postWriteCleanup();
/*3167*/            return false;
/*3128*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*3158*/            modCount++;
/*3159*/            ReferenceEntry referenceentry2 = newEntry(obj, i, referenceentry);
/*3160*/            setValue(referenceentry2, obj, obj1, l);
/*3161*/            ((AtomicReferenceArray) (obj2)).set(k, referenceentry2);
/*3162*/            count = j;
/*3163*/            evictEntries();
/*3166*/            unlock();
/*3167*/            postWriteCleanup();
/*3167*/            return true;
/*3166*/            obj;
/*3166*/            unlock();
/*3167*/            postWriteCleanup();
/*3167*/            throw obj;
                }

                boolean remove(Object obj, int i, Object obj1)
                {
/*3172*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*3174*/            long l = map.ticker.read();
/*3175*/            preWriteCleanup(l);
/*3177*/            count;
/*3178*/            atomicreferencearray = table;
/*3179*/            j = i & atomicreferencearray.length() - 1;
/*3180*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L6:
                    Object obj2;
                    Object obj3;
/*3182*/            if(referenceentry1 == null)
/*3183*/                break MISSING_BLOCK_LABEL_254;
/*3183*/            obj2 = referenceentry1.getKey();
/*3184*/            if(referenceentry1.getHash() != i || obj2 == null || !map.keyEquivalence.equivalent(obj, obj2))
/*3186*/                break MISSING_BLOCK_LABEL_242;
/*3186*/            obj3 = ((ValueReference) (obj = referenceentry1.getValueReference())).get();
/*3190*/            if(!map.valueEquivalence.equivalent(obj1, obj3)) goto _L2; else goto _L1
_L1:
/*3191*/            obj1 = RemovalCause.EXPLICIT;
                      goto _L3
_L2:
/*3192*/            if(obj3 != null || !((ValueReference) (obj)).isActive()) goto _L5; else goto _L4
_L4:
/*3193*/            obj1 = RemovalCause.COLLECTED;
                      goto _L3
_L5:
/*3211*/            unlock();
/*3212*/            postWriteCleanup();
/*3212*/            return false;
_L3:
/*3199*/            modCount++;
/*3200*/            i = removeValueFromChain(referenceentry, referenceentry1, obj2, i, ((ValueReference) (obj)), ((RemovalCause) (obj1)));
/*3202*/            obj = count - 1;
/*3203*/            atomicreferencearray.set(j, i);
/*3204*/            count = ((int) (obj));
/*3205*/            obj = obj1 != RemovalCause.EXPLICIT ? 0 : 1;
/*3211*/            unlock();
/*3212*/            postWriteCleanup();
/*3212*/            return ((boolean) (obj));
/*3182*/            referenceentry1 = referenceentry1.getNext();
                      goto _L6
/*3211*/            unlock();
/*3212*/            postWriteCleanup();
/*3212*/            return false;
/*3211*/            obj;
/*3211*/            unlock();
/*3212*/            postWriteCleanup();
/*3212*/            throw obj;
                }

                void clear()
                {
/*3217*/            if(count == 0)
/*3218*/                break MISSING_BLOCK_LABEL_164;
/*3218*/            lock();
/*3220*/            AtomicReferenceArray atomicreferencearray = table;
/*3221*/            for(int i = 0; i < atomicreferencearray.length(); i++)
                    {
/*3222*/                for(ReferenceEntry referenceentry = (ReferenceEntry)atomicreferencearray.get(i); referenceentry != null; referenceentry = referenceentry.getNext())
/*3224*/                    if(referenceentry.getValueReference().isActive())
/*3225*/                        enqueueNotification(referenceentry, RemovalCause.EXPLICIT);

                    }

/*3229*/            for(int j = 0; j < atomicreferencearray.length(); j++)
/*3230*/                atomicreferencearray.set(j, null);

/*3232*/            clearReferenceQueues();
/*3233*/            writeQueue.clear();
/*3234*/            accessQueue.clear();
/*3235*/            readCount.set(0);
/*3237*/            modCount++;
/*3238*/            count = 0;
/*3240*/            unlock();
/*3241*/            postWriteCleanup();
/*3242*/            return;
                    Exception exception;
/*3240*/            exception;
/*3240*/            unlock();
/*3241*/            postWriteCleanup();
/*3241*/            throw exception;
                }

                ReferenceEntry removeValueFromChain(ReferenceEntry referenceentry, ReferenceEntry referenceentry1, Object obj, int i, ValueReference valuereference, RemovalCause removalcause)
                {
/*3251*/            enqueueNotification(obj, i, valuereference, removalcause);
/*3252*/            writeQueue.remove(referenceentry1);
/*3253*/            accessQueue.remove(referenceentry1);
/*3255*/            if(valuereference.isLoading())
                    {
/*3256*/                valuereference.notifyNewValue(null);
/*3257*/                return referenceentry;
                    } else
                    {
/*3259*/                return removeEntryFromChain(referenceentry, referenceentry1);
                    }
                }

                ReferenceEntry removeEntryFromChain(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/*3267*/            int i = count;
/*3268*/            ReferenceEntry referenceentry2 = referenceentry1.getNext();
/*3269*/            for(referenceentry = referenceentry; referenceentry != referenceentry1; referenceentry = referenceentry.getNext())
                    {
                        ReferenceEntry referenceentry3;
/*3270*/                if((referenceentry3 = copyEntry(referenceentry, referenceentry2)) != null)
                        {
/*3272*/                    referenceentry2 = referenceentry3;
                        } else
                        {
/*3274*/                    removeCollectedEntry(referenceentry);
/*3275*/                    i--;
                        }
                    }

/*3278*/            count = i;
/*3279*/            return referenceentry2;
                }

                void removeCollectedEntry(ReferenceEntry referenceentry)
                {
/*3284*/            enqueueNotification(referenceentry, RemovalCause.COLLECTED);
/*3285*/            writeQueue.remove(referenceentry);
/*3286*/            accessQueue.remove(referenceentry);
                }

                boolean reclaimKey(ReferenceEntry referenceentry, int i)
                {
/*3293*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry1;
                    ReferenceEntry referenceentry2;
/*3295*/            count;
/*3296*/            atomicreferencearray = table;
/*3297*/            j = i & atomicreferencearray.length() - 1;
/*3298*/            referenceentry2 = referenceentry1 = (ReferenceEntry)atomicreferencearray.get(j);
_L1:
/*3300*/            if(referenceentry2 == null)
/*3301*/                break MISSING_BLOCK_LABEL_127;
/*3301*/            if(referenceentry2 != referenceentry)
/*3302*/                break MISSING_BLOCK_LABEL_115;
/*3302*/            modCount++;
/*3303*/            i = removeValueFromChain(referenceentry1, referenceentry2, referenceentry2.getKey(), i, referenceentry2.getValueReference(), RemovalCause.COLLECTED);
/*3305*/            referenceentry = count - 1;
/*3306*/            atomicreferencearray.set(j, i);
/*3307*/            count = referenceentry;
/*3314*/            unlock();
/*3315*/            postWriteCleanup();
/*3315*/            return true;
/*3300*/            referenceentry2 = referenceentry2.getNext();
                      goto _L1
/*3314*/            unlock();
/*3315*/            postWriteCleanup();
/*3315*/            return false;
/*3314*/            referenceentry;
/*3314*/            unlock();
/*3315*/            postWriteCleanup();
/*3315*/            throw referenceentry;
                }

                boolean reclaimValue(Object obj, int i, ValueReference valuereference)
                {
/*3323*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*3325*/            count;
/*3326*/            atomicreferencearray = table;
/*3327*/            j = i & atomicreferencearray.length() - 1;
/*3328*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L3:
                    Object obj1;
/*3330*/            if(referenceentry1 == null)
/*3331*/                break MISSING_BLOCK_LABEL_192;
/*3331*/            obj1 = referenceentry1.getKey();
/*3332*/            if(referenceentry1.getHash() != i || obj1 == null || !map.keyEquivalence.equivalent(obj, obj1))
/*3334*/                break MISSING_BLOCK_LABEL_180;
/*3334*/            if((obj = referenceentry1.getValueReference()) != valuereference) goto _L2; else goto _L1
_L1:
/*3336*/            modCount++;
/*3337*/            i = removeValueFromChain(referenceentry, referenceentry1, obj1, i, valuereference, RemovalCause.COLLECTED);
/*3339*/            obj = count - 1;
/*3340*/            atomicreferencearray.set(j, i);
/*3341*/            count = ((int) (obj));
/*3350*/            unlock();
/*3351*/            if(!isHeldByCurrentThread())
/*3352*/                postWriteCleanup();
/*3352*/            return true;
_L2:
/*3350*/            unlock();
/*3351*/            if(!isHeldByCurrentThread())
/*3352*/                postWriteCleanup();
/*3352*/            return false;
/*3330*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*3350*/            unlock();
/*3351*/            if(!isHeldByCurrentThread())
/*3352*/                postWriteCleanup();
/*3352*/            return false;
/*3350*/            obj;
/*3350*/            unlock();
/*3351*/            if(!isHeldByCurrentThread())
/*3352*/                postWriteCleanup();
/*3352*/            throw obj;
                }

                boolean removeLoadingValue(Object obj, int i, LoadingValueReference loadingvaluereference)
                {
/*3358*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*3360*/            atomicreferencearray = table;
/*3361*/            j = i & atomicreferencearray.length() - 1;
/*3362*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L3:
/*3364*/            if(referenceentry1 == null)
/*3365*/                break MISSING_BLOCK_LABEL_165;
/*3365*/            Object obj1 = referenceentry1.getKey();
/*3366*/            if(referenceentry1.getHash() != i || obj1 == null || !map.keyEquivalence.equivalent(obj, obj1))
/*3368*/                break MISSING_BLOCK_LABEL_153;
/*3368*/            if((obj = referenceentry1.getValueReference()) != loadingvaluereference) goto _L2; else goto _L1
_L1:
/*3370*/            if(loadingvaluereference.isActive())
                    {
/*3371*/                referenceentry1.setValueReference(loadingvaluereference.getOldValue());
                    } else
                    {
/*3373*/                obj = removeEntryFromChain(referenceentry, referenceentry1);
/*3374*/                atomicreferencearray.set(j, obj);
                    }
/*3384*/            unlock();
/*3385*/            postWriteCleanup();
/*3385*/            return true;
_L2:
/*3384*/            unlock();
/*3385*/            postWriteCleanup();
/*3385*/            return false;
/*3364*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*3384*/            unlock();
/*3385*/            postWriteCleanup();
/*3385*/            return false;
/*3384*/            obj;
/*3384*/            unlock();
/*3385*/            postWriteCleanup();
/*3385*/            throw obj;
                }

                boolean removeEntry(ReferenceEntry referenceentry, int i, RemovalCause removalcause)
                {
/*3391*/            int _tmp = count;
/*3392*/            AtomicReferenceArray atomicreferencearray = table;
/*3393*/            int j = i & atomicreferencearray.length() - 1;
                    ReferenceEntry referenceentry1;
/*3394*/            for(ReferenceEntry referenceentry2 = referenceentry1 = (ReferenceEntry)atomicreferencearray.get(j); referenceentry2 != null; referenceentry2 = referenceentry2.getNext())
/*3397*/                if(referenceentry2 == referenceentry)
                        {
/*3398*/                    modCount++;
/*3399*/                    i = removeValueFromChain(referenceentry1, referenceentry2, referenceentry2.getKey(), i, referenceentry2.getValueReference(), removalcause);
/*3401*/                    referenceentry = count - 1;
/*3402*/                    atomicreferencearray.set(j, i);
/*3403*/                    count = referenceentry;
/*3404*/                    return true;
                        }

/*3408*/            return false;
                }

                void postReadCleanup()
                {
/*3416*/            if((readCount.incrementAndGet() & 0x3f) == 0)
/*3417*/                cleanUp();
                }

                void preWriteCleanup(long l)
                {
/*3429*/            runLockedCleanup(l);
                }

                void postWriteCleanup()
                {
/*3436*/            runUnlockedCleanup();
                }

                void cleanUp()
                {
/*3440*/            long l = map.ticker.read();
/*3441*/            runLockedCleanup(l);
/*3442*/            runUnlockedCleanup();
                }

                void runLockedCleanup(long l)
                {
/*3446*/            if(!tryLock())
/*3448*/                break MISSING_BLOCK_LABEL_36;
/*3448*/            drainReferenceQueues();
/*3449*/            expireEntries(l);
/*3450*/            readCount.set(0);
/*3452*/            unlock();
/*3453*/            return;
/*3452*/            l;
/*3452*/            unlock();
/*3452*/            throw l;
                }

                void runUnlockedCleanup()
                {
/*3459*/            if(!isHeldByCurrentThread())
/*3460*/                map.processPendingNotifications();
                }

                final LocalCache map;
                volatile int count;
                long totalWeight;
                int modCount;
                int threshold;
                volatile AtomicReferenceArray table;
                final long maxSegmentWeight;
                final ReferenceQueue keyReferenceQueue;
                final ReferenceQueue valueReferenceQueue;
                final Queue recencyQueue;
                final AtomicInteger readCount = new AtomicInteger();
                final Queue writeQueue;
                final Queue accessQueue;
                final AbstractCache.StatsCounter statsCounter;

                Segment(LocalCache localcache, int i, long l, AbstractCache.StatsCounter statscounter)
                {
/*2091*/            map = localcache;
/*2092*/            maxSegmentWeight = l;
/*2093*/            statsCounter = (AbstractCache.StatsCounter)Preconditions.checkNotNull(statscounter);
/*2094*/            initTable(newEntryArray(i));
/*2096*/            keyReferenceQueue = localcache.usesKeyReferences() ? new ReferenceQueue() : null;
/*2099*/            valueReferenceQueue = localcache.usesValueReferences() ? new ReferenceQueue() : null;
/*2102*/            recencyQueue = ((Queue) (localcache.usesAccessQueue() ? ((Queue) (new ConcurrentLinkedQueue())) : LocalCache.discardingQueue()));
/*2106*/            writeQueue = ((Queue) (localcache.usesWriteQueue() ? ((Queue) (new WriteQueue())) : LocalCache.discardingQueue()));
/*2110*/            accessQueue = ((Queue) (localcache.usesAccessQueue() ? ((Queue) (new AccessQueue())) : LocalCache.discardingQueue()));
                }
    }

    static final class WeightedStrongValueReference extends StrongValueReference
    {

                public final int getWeight()
                {
/*1780*/            return weight;
                }

                final int weight;

                WeightedStrongValueReference(Object obj, int i)
                {
/*1774*/            super(obj);
/*1775*/            weight = i;
                }
    }

    static final class WeightedSoftValueReference extends SoftValueReference
    {

                public final int getWeight()
                {
/*1757*/            return weight;
                }

                public final ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1762*/            return new WeightedSoftValueReference(referencequeue, obj, referenceentry, weight);
                }

                final int weight;

                WeightedSoftValueReference(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry, int i)
                {
/*1751*/            super(referencequeue, obj, referenceentry);
/*1752*/            weight = i;
                }
    }

    static final class WeightedWeakValueReference extends WeakValueReference
    {

                public final int getWeight()
                {
/*1733*/            return weight;
                }

                public final ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1739*/            return new WeightedWeakValueReference(referencequeue, obj, referenceentry, weight);
                }

                final int weight;

                WeightedWeakValueReference(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry, int i)
                {
/*1727*/            super(referencequeue, obj, referenceentry);
/*1728*/            weight = i;
                }
    }

    static class StrongValueReference
        implements ValueReference
    {

                public Object get()
                {
/*1681*/            return referent;
                }

                public int getWeight()
                {
/*1686*/            return 1;
                }

                public ReferenceEntry getEntry()
                {
/*1691*/            return null;
                }

                public ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1697*/            return this;
                }

                public boolean isLoading()
                {
/*1702*/            return false;
                }

                public boolean isActive()
                {
/*1707*/            return true;
                }

                public Object waitForValue()
                {
/*1712*/            return get();
                }

                public void notifyNewValue(Object obj)
                {
                }

                final Object referent;

                StrongValueReference(Object obj)
                {
/*1676*/            referent = obj;
                }
    }

    static class SoftValueReference extends SoftReference
        implements ValueReference
    {

                public int getWeight()
                {
/*1636*/            return 1;
                }

                public ReferenceEntry getEntry()
                {
/*1641*/            return entry;
                }

                public void notifyNewValue(Object obj)
                {
                }

                public ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1650*/            return new SoftValueReference(referencequeue, obj, referenceentry);
                }

                public boolean isLoading()
                {
/*1655*/            return false;
                }

                public boolean isActive()
                {
/*1660*/            return true;
                }

                public Object waitForValue()
                {
/*1665*/            return get();
                }

                final ReferenceEntry entry;

                SoftValueReference(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1630*/            super(obj, referencequeue);
/*1631*/            entry = referenceentry;
                }
    }

    static class WeakValueReference extends WeakReference
        implements ValueReference
    {

                public int getWeight()
                {
/*1589*/            return 1;
                }

                public ReferenceEntry getEntry()
                {
/*1594*/            return entry;
                }

                public void notifyNewValue(Object obj)
                {
                }

                public ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1603*/            return new WeakValueReference(referencequeue, obj, referenceentry);
                }

                public boolean isLoading()
                {
/*1608*/            return false;
                }

                public boolean isActive()
                {
/*1613*/            return true;
                }

                public Object waitForValue()
                {
/*1618*/            return get();
                }

                final ReferenceEntry entry;

                WeakValueReference(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1583*/            super(obj, referencequeue);
/*1584*/            entry = referenceentry;
                }
    }

    static final class WeakAccessWriteEntry extends WeakEntry
    {

                public final long getAccessTime()
                {
/*1500*/            return accessTime;
                }

                public final void setAccessTime(long l)
                {
/*1505*/            accessTime = l;
                }

                public final ReferenceEntry getNextInAccessQueue()
                {
/*1513*/            return nextAccess;
                }

                public final void setNextInAccessQueue(ReferenceEntry referenceentry)
                {
/*1518*/            nextAccess = referenceentry;
                }

                public final ReferenceEntry getPreviousInAccessQueue()
                {
/*1526*/            return previousAccess;
                }

                public final void setPreviousInAccessQueue(ReferenceEntry referenceentry)
                {
/*1531*/            previousAccess = referenceentry;
                }

                public final long getWriteTime()
                {
/*1540*/            return writeTime;
                }

                public final void setWriteTime(long l)
                {
/*1545*/            writeTime = l;
                }

                public final ReferenceEntry getNextInWriteQueue()
                {
/*1553*/            return nextWrite;
                }

                public final void setNextInWriteQueue(ReferenceEntry referenceentry)
                {
/*1558*/            nextWrite = referenceentry;
                }

                public final ReferenceEntry getPreviousInWriteQueue()
                {
/*1566*/            return previousWrite;
                }

                public final void setPreviousInWriteQueue(ReferenceEntry referenceentry)
                {
/*1571*/            previousWrite = referenceentry;
                }

                volatile long accessTime;
                ReferenceEntry nextAccess;
                ReferenceEntry previousAccess;
                volatile long writeTime;
                ReferenceEntry nextWrite;
                ReferenceEntry previousWrite;

                WeakAccessWriteEntry(ReferenceQueue referencequeue, Object obj, int i, ReferenceEntry referenceentry)
                {
/*1491*/            super(referencequeue, obj, i, referenceentry);
/*1496*/            accessTime = 0x7fffffffffffffffL;
/*1509*/            nextAccess = LocalCache.nullEntry();
/*1522*/            previousAccess = LocalCache.nullEntry();
/*1536*/            writeTime = 0x7fffffffffffffffL;
/*1549*/            nextWrite = LocalCache.nullEntry();
/*1562*/            previousWrite = LocalCache.nullEntry();
                }
    }

    static final class WeakWriteEntry extends WeakEntry
    {

                public final long getWriteTime()
                {
/*1453*/            return writeTime;
                }

                public final void setWriteTime(long l)
                {
/*1458*/            writeTime = l;
                }

                public final ReferenceEntry getNextInWriteQueue()
                {
/*1466*/            return nextWrite;
                }

                public final void setNextInWriteQueue(ReferenceEntry referenceentry)
                {
/*1471*/            nextWrite = referenceentry;
                }

                public final ReferenceEntry getPreviousInWriteQueue()
                {
/*1479*/            return previousWrite;
                }

                public final void setPreviousInWriteQueue(ReferenceEntry referenceentry)
                {
/*1484*/            previousWrite = referenceentry;
                }

                volatile long writeTime;
                ReferenceEntry nextWrite;
                ReferenceEntry previousWrite;

                WeakWriteEntry(ReferenceQueue referencequeue, Object obj, int i, ReferenceEntry referenceentry)
                {
/*1444*/            super(referencequeue, obj, i, referenceentry);
/*1449*/            writeTime = 0x7fffffffffffffffL;
/*1462*/            nextWrite = LocalCache.nullEntry();
/*1475*/            previousWrite = LocalCache.nullEntry();
                }
    }

    static final class WeakAccessEntry extends WeakEntry
    {

                public final long getAccessTime()
                {
/*1406*/            return accessTime;
                }

                public final void setAccessTime(long l)
                {
/*1411*/            accessTime = l;
                }

                public final ReferenceEntry getNextInAccessQueue()
                {
/*1419*/            return nextAccess;
                }

                public final void setNextInAccessQueue(ReferenceEntry referenceentry)
                {
/*1424*/            nextAccess = referenceentry;
                }

                public final ReferenceEntry getPreviousInAccessQueue()
                {
/*1432*/            return previousAccess;
                }

                public final void setPreviousInAccessQueue(ReferenceEntry referenceentry)
                {
/*1437*/            previousAccess = referenceentry;
                }

                volatile long accessTime;
                ReferenceEntry nextAccess;
                ReferenceEntry previousAccess;

                WeakAccessEntry(ReferenceQueue referencequeue, Object obj, int i, ReferenceEntry referenceentry)
                {
/*1397*/            super(referencequeue, obj, i, referenceentry);
/*1402*/            accessTime = 0x7fffffffffffffffL;
/*1415*/            nextAccess = LocalCache.nullEntry();
/*1428*/            previousAccess = LocalCache.nullEntry();
                }
    }

    static class WeakEntry extends WeakReference
        implements ReferenceEntry
    {

                public Object getKey()
                {
/*1295*/            return get();
                }

                public long getAccessTime()
                {
/*1307*/            throw new UnsupportedOperationException();
                }

                public void setAccessTime(long l)
                {
/*1312*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextInAccessQueue()
                {
/*1317*/            throw new UnsupportedOperationException();
                }

                public void setNextInAccessQueue(ReferenceEntry referenceentry)
                {
/*1322*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousInAccessQueue()
                {
/*1327*/            throw new UnsupportedOperationException();
                }

                public void setPreviousInAccessQueue(ReferenceEntry referenceentry)
                {
/*1332*/            throw new UnsupportedOperationException();
                }

                public long getWriteTime()
                {
/*1339*/            throw new UnsupportedOperationException();
                }

                public void setWriteTime(long l)
                {
/*1344*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextInWriteQueue()
                {
/*1349*/            throw new UnsupportedOperationException();
                }

                public void setNextInWriteQueue(ReferenceEntry referenceentry)
                {
/*1354*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousInWriteQueue()
                {
/*1359*/            throw new UnsupportedOperationException();
                }

                public void setPreviousInWriteQueue(ReferenceEntry referenceentry)
                {
/*1364*/            throw new UnsupportedOperationException();
                }

                public ValueReference getValueReference()
                {
/*1375*/            return valueReference;
                }

                public void setValueReference(ValueReference valuereference)
                {
/*1380*/            valueReference = valuereference;
                }

                public int getHash()
                {
/*1385*/            return hash;
                }

                public ReferenceEntry getNext()
                {
/*1390*/            return next;
                }

                final int hash;
                final ReferenceEntry next;
                volatile ValueReference valueReference;

                WeakEntry(ReferenceQueue referencequeue, Object obj, int i, ReferenceEntry referenceentry)
                {
/*1288*/            super(obj, referencequeue);
/*1371*/            valueReference = LocalCache.unset();
/*1289*/            hash = i;
/*1290*/            next = referenceentry;
                }
    }

    static final class StrongAccessWriteEntry extends StrongEntry
    {

                public final long getAccessTime()
                {
/*1208*/            return accessTime;
                }

                public final void setAccessTime(long l)
                {
/*1213*/            accessTime = l;
                }

                public final ReferenceEntry getNextInAccessQueue()
                {
/*1221*/            return nextAccess;
                }

                public final void setNextInAccessQueue(ReferenceEntry referenceentry)
                {
/*1226*/            nextAccess = referenceentry;
                }

                public final ReferenceEntry getPreviousInAccessQueue()
                {
/*1234*/            return previousAccess;
                }

                public final void setPreviousInAccessQueue(ReferenceEntry referenceentry)
                {
/*1239*/            previousAccess = referenceentry;
                }

                public final long getWriteTime()
                {
/*1248*/            return writeTime;
                }

                public final void setWriteTime(long l)
                {
/*1253*/            writeTime = l;
                }

                public final ReferenceEntry getNextInWriteQueue()
                {
/*1261*/            return nextWrite;
                }

                public final void setNextInWriteQueue(ReferenceEntry referenceentry)
                {
/*1266*/            nextWrite = referenceentry;
                }

                public final ReferenceEntry getPreviousInWriteQueue()
                {
/*1274*/            return previousWrite;
                }

                public final void setPreviousInWriteQueue(ReferenceEntry referenceentry)
                {
/*1279*/            previousWrite = referenceentry;
                }

                volatile long accessTime;
                ReferenceEntry nextAccess;
                ReferenceEntry previousAccess;
                volatile long writeTime;
                ReferenceEntry nextWrite;
                ReferenceEntry previousWrite;

                StrongAccessWriteEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/*1199*/            super(obj, i, referenceentry);
/*1204*/            accessTime = 0x7fffffffffffffffL;
/*1217*/            nextAccess = LocalCache.nullEntry();
/*1230*/            previousAccess = LocalCache.nullEntry();
/*1244*/            writeTime = 0x7fffffffffffffffL;
/*1257*/            nextWrite = LocalCache.nullEntry();
/*1270*/            previousWrite = LocalCache.nullEntry();
                }
    }

    static final class StrongWriteEntry extends StrongEntry
    {

                public final long getWriteTime()
                {
/*1162*/            return writeTime;
                }

                public final void setWriteTime(long l)
                {
/*1167*/            writeTime = l;
                }

                public final ReferenceEntry getNextInWriteQueue()
                {
/*1175*/            return nextWrite;
                }

                public final void setNextInWriteQueue(ReferenceEntry referenceentry)
                {
/*1180*/            nextWrite = referenceentry;
                }

                public final ReferenceEntry getPreviousInWriteQueue()
                {
/*1188*/            return previousWrite;
                }

                public final void setPreviousInWriteQueue(ReferenceEntry referenceentry)
                {
/*1193*/            previousWrite = referenceentry;
                }

                volatile long writeTime;
                ReferenceEntry nextWrite;
                ReferenceEntry previousWrite;

                StrongWriteEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/*1153*/            super(obj, i, referenceentry);
/*1158*/            writeTime = 0x7fffffffffffffffL;
/*1171*/            nextWrite = LocalCache.nullEntry();
/*1184*/            previousWrite = LocalCache.nullEntry();
                }
    }

    static final class StrongAccessEntry extends StrongEntry
    {

                public final long getAccessTime()
                {
/*1116*/            return accessTime;
                }

                public final void setAccessTime(long l)
                {
/*1121*/            accessTime = l;
                }

                public final ReferenceEntry getNextInAccessQueue()
                {
/*1129*/            return nextAccess;
                }

                public final void setNextInAccessQueue(ReferenceEntry referenceentry)
                {
/*1134*/            nextAccess = referenceentry;
                }

                public final ReferenceEntry getPreviousInAccessQueue()
                {
/*1142*/            return previousAccess;
                }

                public final void setPreviousInAccessQueue(ReferenceEntry referenceentry)
                {
/*1147*/            previousAccess = referenceentry;
                }

                volatile long accessTime;
                ReferenceEntry nextAccess;
                ReferenceEntry previousAccess;

                StrongAccessEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/*1107*/            super(obj, i, referenceentry);
/*1112*/            accessTime = 0x7fffffffffffffffL;
/*1125*/            nextAccess = LocalCache.nullEntry();
/*1138*/            previousAccess = LocalCache.nullEntry();
                }
    }

    static class StrongEntry extends AbstractReferenceEntry
    {

                public Object getKey()
                {
/*1075*/            return key;
                }

                public ValueReference getValueReference()
                {
/*1086*/            return valueReference;
                }

                public void setValueReference(ValueReference valuereference)
                {
/*1091*/            valueReference = valuereference;
                }

                public int getHash()
                {
/*1096*/            return hash;
                }

                public ReferenceEntry getNext()
                {
/*1101*/            return next;
                }

                final Object key;
                final int hash;
                final ReferenceEntry next;
                volatile ValueReference valueReference;

                StrongEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/*1082*/            valueReference = LocalCache.unset();
/*1068*/            key = obj;
/*1069*/            hash = i;
/*1070*/            next = referenceentry;
                }
    }

    static abstract class AbstractReferenceEntry
        implements ReferenceEntry
    {

                public ValueReference getValueReference()
                {
/* 929*/            throw new UnsupportedOperationException();
                }

                public void setValueReference(ValueReference valuereference)
                {
/* 934*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNext()
                {
/* 939*/            throw new UnsupportedOperationException();
                }

                public int getHash()
                {
/* 944*/            throw new UnsupportedOperationException();
                }

                public Object getKey()
                {
/* 949*/            throw new UnsupportedOperationException();
                }

                public long getAccessTime()
                {
/* 954*/            throw new UnsupportedOperationException();
                }

                public void setAccessTime(long l)
                {
/* 959*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextInAccessQueue()
                {
/* 964*/            throw new UnsupportedOperationException();
                }

                public void setNextInAccessQueue(ReferenceEntry referenceentry)
                {
/* 969*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousInAccessQueue()
                {
/* 974*/            throw new UnsupportedOperationException();
                }

                public void setPreviousInAccessQueue(ReferenceEntry referenceentry)
                {
/* 979*/            throw new UnsupportedOperationException();
                }

                public long getWriteTime()
                {
/* 984*/            throw new UnsupportedOperationException();
                }

                public void setWriteTime(long l)
                {
/* 989*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextInWriteQueue()
                {
/* 994*/            throw new UnsupportedOperationException();
                }

                public void setNextInWriteQueue(ReferenceEntry referenceentry)
                {
/* 999*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousInWriteQueue()
                {
/*1004*/            throw new UnsupportedOperationException();
                }

                public void setPreviousInWriteQueue(ReferenceEntry referenceentry)
                {
/*1009*/            throw new UnsupportedOperationException();
                }

                AbstractReferenceEntry()
                {
                }
    }

    static final class NullEntry extends Enum
        implements ReferenceEntry
    {

                public final ValueReference getValueReference()
                {
/* 856*/            return null;
                }

                public final void setValueReference(ValueReference valuereference)
                {
                }

                public final ReferenceEntry getNext()
                {
/* 864*/            return null;
                }

                public final int getHash()
                {
/* 869*/            return 0;
                }

                public final Object getKey()
                {
/* 874*/            return null;
                }

                public final long getAccessTime()
                {
/* 879*/            return 0L;
                }

                public final void setAccessTime(long l)
                {
                }

                public final ReferenceEntry getNextInAccessQueue()
                {
/* 887*/            return this;
                }

                public final void setNextInAccessQueue(ReferenceEntry referenceentry)
                {
                }

                public final ReferenceEntry getPreviousInAccessQueue()
                {
/* 895*/            return this;
                }

                public final void setPreviousInAccessQueue(ReferenceEntry referenceentry)
                {
                }

                public final long getWriteTime()
                {
/* 903*/            return 0L;
                }

                public final void setWriteTime(long l)
                {
                }

                public final ReferenceEntry getNextInWriteQueue()
                {
/* 911*/            return this;
                }

                public final void setNextInWriteQueue(ReferenceEntry referenceentry)
                {
                }

                public final ReferenceEntry getPreviousInWriteQueue()
                {
/* 919*/            return this;
                }

                public final void setPreviousInWriteQueue(ReferenceEntry referenceentry)
                {
                }

                public static final NullEntry INSTANCE;
                private static final NullEntry $VALUES[];

                static 
                {
/* 852*/            INSTANCE = new NullEntry("INSTANCE", 0);
/* 851*/            $VALUES = (new NullEntry[] {
/* 851*/                INSTANCE
                    });
                }

                private NullEntry(String s, int i)
                {
/* 851*/            super(s, i);
                }
    }

    static interface ReferenceEntry
    {

        public abstract ValueReference getValueReference();

        public abstract void setValueReference(ValueReference valuereference);

        public abstract ReferenceEntry getNext();

        public abstract int getHash();

        public abstract Object getKey();

        public abstract long getAccessTime();

        public abstract void setAccessTime(long l);

        public abstract ReferenceEntry getNextInAccessQueue();

        public abstract void setNextInAccessQueue(ReferenceEntry referenceentry);

        public abstract ReferenceEntry getPreviousInAccessQueue();

        public abstract void setPreviousInAccessQueue(ReferenceEntry referenceentry);

        public abstract long getWriteTime();

        public abstract void setWriteTime(long l);

        public abstract ReferenceEntry getNextInWriteQueue();

        public abstract void setNextInWriteQueue(ReferenceEntry referenceentry);

        public abstract ReferenceEntry getPreviousInWriteQueue();

        public abstract void setPreviousInWriteQueue(ReferenceEntry referenceentry);
    }

    static interface ValueReference
    {

        public abstract Object get();

        public abstract Object waitForValue()
            throws ExecutionException;

        public abstract int getWeight();

        public abstract ReferenceEntry getEntry();

        public abstract ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry);

        public abstract void notifyNewValue(Object obj);

        public abstract boolean isLoading();

        public abstract boolean isActive();
    }

    static abstract class EntryFactory extends Enum
    {

                static EntryFactory getFactory(Strength strength, boolean flag, boolean flag1)
                {
/* 568*/            strength = (strength != Strength.WEAK ? 0 : 4) | (flag ? 1 : 0) | (flag1 ? 2 : 0);
/* 571*/            return factories[strength];
                }

                abstract ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry);

                ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/* 594*/            return newEntry(segment, referenceentry.getKey(), referenceentry.getHash(), referenceentry1);
                }

                void copyAccessEntry(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/* 601*/            referenceentry1.setAccessTime(referenceentry.getAccessTime());
/* 603*/            LocalCache.connectAccessOrder(referenceentry.getPreviousInAccessQueue(), referenceentry1);
/* 604*/            LocalCache.connectAccessOrder(referenceentry1, referenceentry.getNextInAccessQueue());
/* 606*/            LocalCache.nullifyAccessOrder(referenceentry);
                }

                void copyWriteEntry(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/* 613*/            referenceentry1.setWriteTime(referenceentry.getWriteTime());
/* 615*/            LocalCache.connectWriteOrder(referenceentry.getPreviousInWriteQueue(), referenceentry1);
/* 616*/            LocalCache.connectWriteOrder(referenceentry1, referenceentry.getNextInWriteQueue());
/* 618*/            LocalCache.nullifyWriteOrder(referenceentry);
                }

                public static final EntryFactory STRONG;
                public static final EntryFactory STRONG_ACCESS;
                public static final EntryFactory STRONG_WRITE;
                public static final EntryFactory STRONG_ACCESS_WRITE;
                public static final EntryFactory WEAK;
                public static final EntryFactory WEAK_ACCESS;
                public static final EntryFactory WEAK_WRITE;
                public static final EntryFactory WEAK_ACCESS_WRITE;
                static final EntryFactory factories[];
                private static final EntryFactory $VALUES[];

                static 
                {
/* 443*/            STRONG = new EntryFactory("STRONG", 0) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 447*/                    return new StrongEntry(obj, i, referenceentry);
                        }

            };
/* 450*/            STRONG_ACCESS = new EntryFactory("STRONG_ACCESS", 1) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 454*/                    return new StrongAccessEntry(obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 460*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 461*/                    copyAccessEntry(referenceentry, segment);
/* 462*/                    return segment;
                        }

            };
/* 465*/            STRONG_WRITE = new EntryFactory("STRONG_WRITE", 2) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 469*/                    return new StrongWriteEntry(obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 475*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 476*/                    copyWriteEntry(referenceentry, segment);
/* 477*/                    return segment;
                        }

            };
/* 480*/            STRONG_ACCESS_WRITE = new EntryFactory("STRONG_ACCESS_WRITE", 3) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 484*/                    return new StrongAccessWriteEntry(obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 490*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 491*/                    copyAccessEntry(referenceentry, segment);
/* 492*/                    copyWriteEntry(referenceentry, segment);
/* 493*/                    return segment;
                        }

            };
/* 497*/            WEAK = new EntryFactory("WEAK", 4) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 501*/                    return new WeakEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                        }

            };
/* 504*/            WEAK_ACCESS = new EntryFactory("WEAK_ACCESS", 5) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 508*/                    return new WeakAccessEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 514*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 515*/                    copyAccessEntry(referenceentry, segment);
/* 516*/                    return segment;
                        }

            };
/* 519*/            WEAK_WRITE = new EntryFactory("WEAK_WRITE", 6) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 523*/                    return new WeakWriteEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 529*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 530*/                    copyWriteEntry(referenceentry, segment);
/* 531*/                    return segment;
                        }

            };
/* 534*/            WEAK_ACCESS_WRITE = new EntryFactory("WEAK_ACCESS_WRITE", 7) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 538*/                    return new WeakAccessWriteEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 544*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 545*/                    copyAccessEntry(referenceentry, segment);
/* 546*/                    copyWriteEntry(referenceentry, segment);
/* 547*/                    return segment;
                        }

            };
/* 442*/            $VALUES = (new EntryFactory[] {
/* 442*/                STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE
                    });
/* 561*/            factories = (new EntryFactory[] {
/* 561*/                STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE
                    });
                }

                private EntryFactory(String s, int i)
                {
/* 442*/            super(s, i);
                }

    }

    static abstract class Strength extends Enum
    {

                public static Strength[] values()
                {
/* 372*/            return (Strength[])$VALUES.clone();
                }

                abstract ValueReference referenceValue(Segment segment, ReferenceEntry referenceentry, Object obj, int i);

                abstract Equivalence defaultEquivalence();

                public static final Strength STRONG;
                public static final Strength SOFT;
                public static final Strength WEAK;
                private static final Strength $VALUES[];

                static 
                {
/* 378*/            STRONG = new Strength("STRONG", 0) {

                        final ValueReference referenceValue(Segment segment, ReferenceEntry referenceentry, Object obj, int i)
                        {
/* 382*/                    if(i == 1)
/* 382*/                        return new StrongValueReference(obj);
/* 382*/                    else
/* 382*/                        return new WeightedStrongValueReference(obj, i);
                        }

                        final Equivalence defaultEquivalence()
                        {
/* 389*/                    return Equivalence.equals();
                        }

            };
/* 393*/            SOFT = new Strength("SOFT", 1) {

                        final ValueReference referenceValue(Segment segment, ReferenceEntry referenceentry, Object obj, int i)
                        {
/* 397*/                    if(i == 1)
/* 397*/                        return new SoftValueReference(segment.valueReferenceQueue, obj, referenceentry);
/* 397*/                    else
/* 397*/                        return new WeightedSoftValueReference(segment.valueReferenceQueue, obj, referenceentry, i);
                        }

                        final Equivalence defaultEquivalence()
                        {
/* 405*/                    return Equivalence.identity();
                        }

            };
/* 409*/            WEAK = new Strength("WEAK", 2) {

                        final ValueReference referenceValue(Segment segment, ReferenceEntry referenceentry, Object obj, int i)
                        {
/* 413*/                    if(i == 1)
/* 413*/                        return new WeakValueReference(segment.valueReferenceQueue, obj, referenceentry);
/* 413*/                    else
/* 413*/                        return new WeightedWeakValueReference(segment.valueReferenceQueue, obj, referenceentry, i);
                        }

                        final Equivalence defaultEquivalence()
                        {
/* 421*/                    return Equivalence.identity();
                        }

            };
/* 372*/            $VALUES = (new Strength[] {
/* 372*/                STRONG, SOFT, WEAK
                    });
                }

                private Strength(String s, int i)
                {
/* 372*/            super(s, i);
                }

    }


            LocalCache(CacheBuilder cachebuilder, CacheLoader cacheloader)
            {
/* 237*/        concurrencyLevel = Math.min(cachebuilder.getConcurrencyLevel(), 0x10000);
/* 239*/        keyStrength = cachebuilder.getKeyStrength();
/* 240*/        valueStrength = cachebuilder.getValueStrength();
/* 242*/        keyEquivalence = cachebuilder.getKeyEquivalence();
/* 243*/        valueEquivalence = cachebuilder.getValueEquivalence();
/* 245*/        maxWeight = cachebuilder.getMaximumWeight();
/* 246*/        weigher = cachebuilder.getWeigher();
/* 247*/        expireAfterAccessNanos = cachebuilder.getExpireAfterAccessNanos();
/* 248*/        expireAfterWriteNanos = cachebuilder.getExpireAfterWriteNanos();
/* 249*/        refreshNanos = cachebuilder.getRefreshNanos();
/* 251*/        removalListener = cachebuilder.getRemovalListener();
/* 252*/        removalNotificationQueue = ((Queue) (removalListener != CacheBuilder.NullListener.INSTANCE ? ((Queue) (new ConcurrentLinkedQueue())) : discardingQueue()));
/* 256*/        ticker = cachebuilder.getTicker(recordsTime());
/* 257*/        entryFactory = EntryFactory.getFactory(keyStrength, usesAccessEntries(), usesWriteEntries());
/* 258*/        globalStatsCounter = (AbstractCache.StatsCounter)cachebuilder.getStatsCounterSupplier().get();
/* 259*/        defaultLoader = cacheloader;
/* 261*/        cacheloader = Math.min(cachebuilder.getInitialCapacity(), 0x40000000);
/* 262*/        if(evictsBySize() && !customWeigher())
/* 263*/            cacheloader = Math.min(cacheloader, (int)maxWeight);
/* 271*/        int i = 0;
                int l;
/* 272*/        for(l = 1; l < concurrencyLevel && (!evictsBySize() || (long)(l * 20) <= maxWeight); l <<= 1)
/* 275*/            i++;

/* 278*/        segmentShift = 32 - i;
/* 279*/        segmentMask = l - 1;
/* 281*/        segments = newSegmentArray(l);
/* 283*/        if((i = cacheloader / l) * l < cacheloader)
/* 285*/            i++;
/* 288*/        for(cacheloader = 1; cacheloader < i; cacheloader <<= 1);
/* 293*/        if(evictsBySize())
                {
/* 295*/            long l1 = maxWeight / (long)l + 1L;
/* 296*/            long l2 = maxWeight % (long)l;
/* 297*/            for(int j = 0; j < segments.length; j++)
                    {
/* 298*/                if((long)j == l2)
/* 299*/                    l1--;
/* 301*/                segments[j] = createSegment(cacheloader, l1, (AbstractCache.StatsCounter)cachebuilder.getStatsCounterSupplier().get());
                    }

/* 304*/            return;
                }
/* 305*/        for(int k = 0; k < segments.length; k++)
/* 306*/            segments[k] = createSegment(cacheloader, -1L, (AbstractCache.StatsCounter)cachebuilder.getStatsCounterSupplier().get());

            }

            boolean evictsBySize()
            {
/* 313*/        return maxWeight >= 0L;
            }

            boolean customWeigher()
            {
/* 317*/        return weigher != CacheBuilder.OneWeigher.INSTANCE;
            }

            boolean expiresAfterWrite()
            {
/* 325*/        return expireAfterWriteNanos > 0L;
            }

            boolean expiresAfterAccess()
            {
/* 329*/        return expireAfterAccessNanos > 0L;
            }

            boolean refreshes()
            {
/* 333*/        return refreshNanos > 0L;
            }

            boolean usesAccessQueue()
            {
/* 337*/        return expiresAfterAccess() || evictsBySize();
            }

            boolean usesWriteQueue()
            {
/* 341*/        return expiresAfterWrite();
            }

            boolean recordsWrite()
            {
/* 345*/        return expiresAfterWrite() || refreshes();
            }

            boolean recordsAccess()
            {
/* 349*/        return expiresAfterAccess();
            }

            boolean recordsTime()
            {
/* 353*/        return recordsWrite() || recordsAccess();
            }

            boolean usesWriteEntries()
            {
/* 357*/        return usesWriteQueue() || recordsWrite();
            }

            boolean usesAccessEntries()
            {
/* 361*/        return usesAccessQueue() || recordsAccess();
            }

            boolean usesKeyReferences()
            {
/* 365*/        return keyStrength != Strength.STRONG;
            }

            boolean usesValueReferences()
            {
/* 369*/        return valueStrength != Strength.STRONG;
            }

            static ValueReference unset()
            {
/* 733*/        return UNSET;
            }

            static ReferenceEntry nullEntry()
            {
/*1015*/        return NullEntry.INSTANCE;
            }

            static Queue discardingQueue()
            {
/*1050*/        return DISCARDING_QUEUE;
            }

            static int rehash(int i)
            {
/*1796*/        return (i = (i = (i = (i = (i += i << 15 ^ 0xffffcd7d) ^ i >>> 10) + (i << 3)) ^ i >>> 6) + ((i << 2) + (i << 14))) ^ i >>> 16;
            }

            int hash(Object obj)
            {
/*1839*/        return rehash(((int) (obj = keyEquivalence.hash(obj))));
            }

            void reclaimValue(ValueReference valuereference)
            {
                ReferenceEntry referenceentry;
/*1844*/        int i = (referenceentry = valuereference.getEntry()).getHash();
/*1846*/        segmentFor(i).reclaimValue(referenceentry.getKey(), i, valuereference);
            }

            void reclaimKey(ReferenceEntry referenceentry)
            {
/*1850*/        int i = referenceentry.getHash();
/*1851*/        segmentFor(i).reclaimKey(referenceentry, i);
            }

            Segment segmentFor(int i)
            {
/*1871*/        return segments[i >>> segmentShift & segmentMask];
            }

            Segment createSegment(int i, long l, AbstractCache.StatsCounter statscounter)
            {
/*1876*/        return new Segment(this, i, l, statscounter);
            }

            Object getLiveValue(ReferenceEntry referenceentry, long l)
            {
/*1887*/        if(referenceentry.getKey() == null)
/*1888*/            return null;
                Object obj;
/*1890*/        if((obj = referenceentry.getValueReference().get()) == null)
/*1892*/            return null;
/*1895*/        if(isExpired(referenceentry, l))
/*1896*/            return null;
/*1898*/        else
/*1898*/            return obj;
            }

            boolean isExpired(ReferenceEntry referenceentry, long l)
            {
/*1907*/        Preconditions.checkNotNull(referenceentry);
/*1908*/        if(expiresAfterAccess() && l - referenceentry.getAccessTime() >= expireAfterAccessNanos)
/*1910*/            return true;
/*1912*/        return expiresAfterWrite() && l - referenceentry.getWriteTime() >= expireAfterWriteNanos;
            }

            static void connectAccessOrder(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
            {
/*1923*/        referenceentry.setNextInAccessQueue(referenceentry1);
/*1924*/        referenceentry1.setPreviousInAccessQueue(referenceentry);
            }

            static void nullifyAccessOrder(ReferenceEntry referenceentry)
            {
/*1929*/        ReferenceEntry referenceentry1 = nullEntry();
/*1930*/        referenceentry.setNextInAccessQueue(referenceentry1);
/*1931*/        referenceentry.setPreviousInAccessQueue(referenceentry1);
            }

            static void connectWriteOrder(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
            {
/*1936*/        referenceentry.setNextInWriteQueue(referenceentry1);
/*1937*/        referenceentry1.setPreviousInWriteQueue(referenceentry);
            }

            static void nullifyWriteOrder(ReferenceEntry referenceentry)
            {
/*1942*/        ReferenceEntry referenceentry1 = nullEntry();
/*1943*/        referenceentry.setNextInWriteQueue(referenceentry1);
/*1944*/        referenceentry.setPreviousInWriteQueue(referenceentry1);
            }

            void processPendingNotifications()
            {
                RemovalNotification removalnotification;
/*1954*/        while((removalnotification = (RemovalNotification)removalNotificationQueue.poll()) != null) 
/*1956*/            try
                    {
/*1956*/                removalListener.onRemoval(removalnotification);
                    }
/*1957*/            catch(Throwable throwable)
                    {
/*1958*/                logger.log(Level.WARNING, "Exception thrown by removal listener", throwable);
                    }
            }

            final Segment[] newSegmentArray(int i)
            {
/*1965*/        return new Segment[i];
            }

            public void cleanUp()
            {
                Segment asegment[];
/*3860*/        int i = (asegment = segments).length;
/*3860*/        for(int j = 0; j < i; j++)
                {
                    Segment segment;
/*3860*/            (segment = asegment[j]).cleanUp();
                }

            }

            public boolean isEmpty()
            {
/*3876*/        long l = 0L;
/*3877*/        Segment asegment[] = segments;
/*3878*/        for(int i = 0; i < asegment.length; i++)
                {
/*3879*/            if(asegment[i].count != 0)
/*3880*/                return false;
/*3882*/            l += asegment[i].modCount;
                }

/*3885*/        if(l != 0L)
                {
/*3886*/            for(int j = 0; j < asegment.length; j++)
                    {
/*3887*/                if(asegment[j].count != 0)
/*3888*/                    return false;
/*3890*/                l -= asegment[j].modCount;
                    }

/*3892*/            if(l != 0L)
/*3893*/                return false;
                }
/*3896*/        return true;
            }

            long longSize()
            {
/*3900*/        Segment asegment[] = segments;
/*3901*/        long l = 0L;
/*3902*/        for(int i = 0; i < asegment.length; i++)
/*3903*/            l += asegment[i].count;

/*3905*/        return l;
            }

            public int size()
            {
/*3910*/        return Ints.saturatedCast(longSize());
            }

            public Object get(Object obj)
            {
/*3916*/        if(obj == null)
                {
/*3917*/            return null;
                } else
                {
/*3919*/            int i = hash(obj);
/*3920*/            return segmentFor(i).get(obj, i);
                }
            }

            public Object getIfPresent(Object obj)
            {
/*3925*/        int i = hash(Preconditions.checkNotNull(obj));
/*3926*/        if((obj = segmentFor(i).get(obj, i)) == null)
/*3928*/            globalStatsCounter.recordMisses(1);
/*3930*/        else
/*3930*/            globalStatsCounter.recordHits(1);
/*3932*/        return obj;
            }

            Object get(Object obj, CacheLoader cacheloader)
                throws ExecutionException
            {
/*3936*/        int i = hash(Preconditions.checkNotNull(obj));
/*3937*/ 