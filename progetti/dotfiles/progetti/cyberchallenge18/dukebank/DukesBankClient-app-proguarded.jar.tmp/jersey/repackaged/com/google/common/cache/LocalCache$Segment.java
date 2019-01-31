// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.util.concurrent.*;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            AbstractCache, CacheLoader, LocalCache, RemovalCause, 
//            RemovalNotification, Weigher

static class ngQueue extends ReentrantLock
{

            AtomicReferenceArray newEntryArray(int i)
            {
/*2116*/        return new AtomicReferenceArray(i);
            }

            void initTable(AtomicReferenceArray atomicreferencearray)
            {
/*2120*/        threshold = (atomicreferencearray.length() * 3) / 4;
/*2121*/        if(!map.customWeigher() && (long)threshold == maxSegmentWeight)
/*2123*/            threshold++;
/*2125*/        table = atomicreferencearray;
            }

            eEntry newEntry(Object obj, int i, eEntry eentry)
            {
/*2130*/        return map.entryFactory.newEntry(this, Preconditions.checkNotNull(obj), i, eentry);
            }

            eEntry copyEntry(eEntry eentry, eEntry eentry1)
            {
/*2139*/        if(eentry.getKey() == null)
/*2141*/            return null;
                erence erence;
                Object obj;
/*2144*/        if((obj = (erence = eentry.getValueReference()).get()) == null && erence.isActive())
                {
/*2148*/            return null;
                } else
                {
/*2151*/            (eentry = map.entryFactory.copyEntry(this, eentry, eentry1)).setValueReference(erence.copyFor(valueReferenceQueue, obj, eentry));
/*2153*/            return eentry;
                }
            }

            void setValue(eEntry eentry, Object obj, Object obj1, long l)
            {
/*2161*/        erence erence = eentry.getValueReference();
/*2162*/        Preconditions.checkState((obj = map.weigher.weigh(obj, obj1)) >= 0, "Weights must be non-negative");
/*2165*/        erence erence1 = map.valueStrength.referenceValue(this, eentry, obj1, ((int) (obj)));
/*2167*/        eentry.setValueReference(erence1);
/*2168*/        recordWrite(eentry, ((int) (obj)), l);
/*2169*/        erence.notifyNewValue(obj1);
            }

            Object get(Object obj, int i, CacheLoader cacheloader)
                throws ExecutionException
            {
/*2175*/        Preconditions.checkNotNull(obj);
/*2176*/        Preconditions.checkNotNull(cacheloader);
                Object obj1;
                Object obj2;
/*2178*/        if(count == 0 || (obj1 = getEntry(obj, i)) == null)
/*2182*/            break MISSING_BLOCK_LABEL_129;
/*2182*/        long l = map.ticker.read();
                Object obj3;
/*2183*/        if((obj3 = getLiveValue(((eEntry) (obj1)), l)) == null)
/*2185*/            break MISSING_BLOCK_LABEL_95;
/*2185*/        recordRead(((eEntry) (obj1)), l);
/*2186*/        statsCounter.recordHits(1);
/*2187*/        obj2 = scheduleRefresh(((eEntry) (obj1)), obj, i, obj3, l, cacheloader);
/*2207*/        postReadCleanup();
/*2207*/        return obj2;
                erence erence;
/*2189*/        if(!(erence = ((eEntry) (obj1)).getValueReference()).isLoading())
/*2191*/            break MISSING_BLOCK_LABEL_129;
/*2191*/        obj = waitForLoadingValue(((eEntry) (obj1)), obj, erence);
/*2207*/        postReadCleanup();
/*2207*/        return obj;
/*2197*/        obj1 = lockedGetOrLoad(obj, i, cacheloader);
/*2207*/        postReadCleanup();
/*2207*/        return obj1;
/*2198*/        JVM INSTR dup ;
/*2199*/        obj1;
/*2199*/        getCause();
/*2199*/        JVM INSTR dup ;
/*2200*/        obj;
/*2200*/        JVM INSTR instanceof #7   <Class Error>;
/*2200*/        JVM INSTR ifeq 171;
                   goto _L1 _L2
_L1:
/*2201*/        break MISSING_BLOCK_LABEL_159;
_L2:
/*2201*/        break MISSING_BLOCK_LABEL_171;
/*2201*/        throw new ExecutionError((Error)obj);
/*2202*/        if(obj instanceof RuntimeException)
/*2203*/            throw new UncheckedExecutionException(((Throwable) (obj)));
/*2205*/        else
/*2205*/            throw obj1;
/*2207*/        obj;
/*2207*/        postReadCleanup();
/*2207*/        throw obj;
            }

            Object lockedGetOrLoad(Object obj, int i, CacheLoader cacheloader)
                throws ExecutionException
            {
                alueReference aluereference;
                boolean flag;
/*2214*/        obj1 = null;
/*2215*/        aluereference = null;
/*2216*/        flag = true;
/*2218*/        lock();
                long l;
                int j;
                AtomicReferenceArray atomicreferencearray;
                int k;
                eEntry eentry;
                eEntry eentry1;
/*2221*/        l = map.ticker.read();
/*2222*/        preWriteCleanup(l);
/*2224*/        j = count - 1;
/*2225*/        atomicreferencearray = table;
/*2226*/        k = i & atomicreferencearray.length() - 1;
/*2227*/        eentry1 = eentry = (eEntry)atomicreferencearray.get(k);
_L6:
                Object obj2;
/*2229*/        if(eentry1 == null)
/*2230*/            break MISSING_BLOCK_LABEL_274;
/*2230*/        obj2 = eentry1.getKey();
/*2231*/        if(eentry1.getHash() != i || obj2 == null || !map.keyEquivalence.equivalent(obj, obj2))
/*2233*/            break MISSING_BLOCK_LABEL_262;
/*2233*/        if(((erence) (obj1 = eentry1.getValueReference())).isLoading())
                {
/*2235*/            flag = false;
/*2235*/            break MISSING_BLOCK_LABEL_274;
                }
                Object obj3;
/*2237*/        if((obj3 = ((erence) (obj1)).get()) != null) goto _L2; else goto _L1
_L1:
/*2239*/        enqueueNotification(obj2, i, ((erence) (obj1)), RemovalCause.COLLECTED);
                  goto _L3
_L2:
/*2240*/        if(!map.isExpired(eentry1, l)) goto _L5; else goto _L4
_L4:
/*2243*/        enqueueNotification(obj2, i, ((erence) (obj1)), RemovalCause.EXPIRED);
                  goto _L3
_L5:
/*2245*/        recordLockedRead(eentry1, l);
/*2246*/        statsCounter.recordHits(1);
/*2248*/        obj = obj3;
/*2272*/        unlock();
/*2273*/        postWriteCleanup();
/*2273*/        return obj;
_L3:
/*2252*/        writeQueue.remove(eentry1);
/*2253*/        accessQueue.remove(eentry1);
/*2254*/        count = j;
/*2256*/        break MISSING_BLOCK_LABEL_274;
/*2229*/        eentry1 = eentry1.getNext();
                  goto _L6
/*2260*/        if(flag)
                {
/*2261*/            aluereference = new alueReference();
/*2263*/            if(eentry1 == null)
                    {
/*2264*/                (eentry1 = newEntry(obj, i, eentry)).setValueReference(aluereference);
/*2266*/                atomicreferencearray.set(k, eentry1);
                    } else
                    {
/*2268*/                eentry1.setValueReference(aluereference);
                    }
                }
/*2272*/        unlock();
/*2273*/        postWriteCleanup();
/*2274*/        break MISSING_BLOCK_LABEL_354;
/*2272*/        obj;
/*2272*/        unlock();
/*2273*/        postWriteCleanup();
/*2273*/        throw obj;
/*2276*/        if(!flag)
/*2281*/            break MISSING_BLOCK_LABEL_409;
/*2281*/        synchronized(eentry1)
                {
/*2282*/            obj = loadSync(obj, i, aluereference, cacheloader);
                }
/*2285*/        statsCounter.recordMisses(1);
/*2285*/        return obj;
/*2285*/        obj;
/*2285*/        statsCounter.recordMisses(1);
/*2285*/        throw obj;
/*2289*/        return waitForLoadingValue(eentry1, obj, ((erence) (obj1)));
            }

            Object waitForLoadingValue(eEntry eentry, Object obj, erence erence)
                throws ExecutionException
            {
/*2295*/        if(!erence.isLoading())
/*2296*/            throw new AssertionError();
/*2299*/        Preconditions.checkState(!Thread.holdsLock(eentry), "Recursive load of: %s", new Object[] {
/*2299*/            obj
                });
/*2302*/        if((erence = ((erence) (erence.waitForValue()))) == null)
                {
/*2304*/            eentry = String.valueOf(String.valueOf(obj));
/*2304*/            throw new CacheLoadException((new StringBuilder(35 + eentry.length())).append("CacheLoader returned null for key ").append(eentry).append(".").toString());
                }
/*2307*/        long l = map.ticker.read();
/*2308*/        recordRead(eentry, l);
/*2309*/        eentry = erence;
/*2311*/        statsCounter.recordMisses(1);
/*2311*/        return eentry;
/*2311*/        eentry;
/*2311*/        statsCounter.recordMisses(1);
/*2311*/        throw eentry;
            }

            Object loadSync(Object obj, int i, alueReference aluereference, CacheLoader cacheloader)
                throws ExecutionException
            {
/*2319*/        cacheloader = aluereference.loadFuture(obj, cacheloader);
/*2320*/        return getAndRecordStats(obj, i, aluereference, cacheloader);
            }

            ListenableFuture loadAsync(final Object key, final int hash, final alueReference loadingValueReference, final CacheLoader loadingFuture)
            {
/*2325*/        (loadingFuture = loadingValueReference.loadFuture(key, loadingFuture)).addListener(new Runnable() {

                    public void run()
                    {
/*2331*/                try
                        {
/*2331*/                    getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
/*2335*/                    return;
                        }
/*2332*/                catch(Throwable throwable)
                        {
/*2333*/                    LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", throwable);
/*2334*/                    loadingValueReference.setException(throwable);
/*2336*/                    return;
                        }
                    }

                    final Object val$key;
                    final int val$hash;
                    final LocalCache.LoadingValueReference val$loadingValueReference;
                    final ListenableFuture val$loadingFuture;
                    final LocalCache.Segment this$0;

                    
                    {
/*2327*/                this$0 = LocalCache.Segment.this;
/*2327*/                key = obj;
/*2327*/                hash = i;
/*2327*/                loadingValueReference = loadingvaluereference;
/*2327*/                loadingFuture = listenablefuture;
/*2327*/                super();
                    }
        }, MoreExecutors.directExecutor());
/*2338*/        return loadingFuture;
            }

            Object getAndRecordStats(Object obj, int i, alueReference aluereference, ListenableFuture listenablefuture)
                throws ExecutionException
            {
/*2346*/        Object obj1 = null;
/*2348*/        if((obj1 = Uninterruptibles.getUninterruptibly(listenablefuture)) == null)
                {
/*2350*/            listenablefuture = String.valueOf(String.valueOf(obj));
/*2350*/            throw new CacheLoadException((new StringBuilder(35 + listenablefuture.length())).append("CacheLoader returned null for key ").append(listenablefuture).append(".").toString());
                }
/*2352*/        statsCounter.recordLoadSuccess(aluereference.elapsedNanos());
/*2353*/        storeLoadedValue(obj, i, aluereference, obj1);
/*2354*/        listenablefuture = ((ListenableFuture) (obj1));
/*2356*/        if(obj1 == null)
                {
/*2357*/            statsCounter.recordLoadException(aluereference.elapsedNanos());
/*2358*/            removeLoadingValue(obj, i, aluereference);
                }
/*2358*/        return listenablefuture;
/*2356*/        listenablefuture;
/*2356