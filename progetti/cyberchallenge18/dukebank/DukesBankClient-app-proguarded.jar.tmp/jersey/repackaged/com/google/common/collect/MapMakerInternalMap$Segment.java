// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import jersey.repackaged.com.google.common.base.Equivalence;
import jersey.repackaged.com.google.common.base.Ticker;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMaker, MapMakerInternalMap

static class ngQueue extends ReentrantLock
{

            AtomicReferenceArray newEntryArray(int i)
            {
/*2115*/        return new AtomicReferenceArray(i);
            }

            void initTable(AtomicReferenceArray atomicreferencearray)
            {
/*2119*/        threshold = (atomicreferencearray.length() * 3) / 4;
/*2120*/        if(threshold == maxSegmentSize)
/*2122*/            threshold++;
/*2124*/        table = atomicreferencearray;
            }

            eEntry newEntry(Object obj, int i, eEntry eentry)
            {
/*2129*/        return map.entryFactory.newEntry(this, obj, i, eentry);
            }

            eEntry copyEntry(eEntry eentry, eEntry eentry1)
            {
/*2138*/        if(eentry.getKey() == null)
/*2140*/            return null;
                erence erence;
                Object obj;
/*2143*/        if((obj = (erence = eentry.getValueReference()).get()) == null && !erence.isComputingReference())
                {
/*2147*/            return null;
                } else
                {
/*2150*/            (eentry = map.entryFactory.copyEntry(this, eentry, eentry1)).setValueReference(erence.copyFor(valueReferenceQueue, obj, eentry));
/*2152*/            return eentry;
                }
            }

            void setValue(eEntry eentry, Object obj)
            {
/*2160*/        obj = map.valueStrength.referenceValue(this, eentry, obj);
/*2161*/        eentry.setValueReference(((erence) (obj)));
/*2162*/        recordWrite(eentry);
            }

            void tryDrainReferenceQueues()
            {
/*2171*/        if(!tryLock())
/*2173*/            break MISSING_BLOCK_LABEL_23;
/*2173*/        drainReferenceQueues();
/*2175*/        unlock();
/*2176*/        return;
                Exception exception;
/*2175*/        exception;
/*2175*/        unlock();
/*2175*/        throw exception;
            }

            void drainReferenceQueues()
            {
/*2186*/        if(map.usesKeyReferences())
/*2187*/            drainKeyReferenceQueue();
/*2189*/        if(map.usesValueReferences())
/*2190*/            drainValueReferenceQueue();
            }

            void drainKeyReferenceQueue()
            {
/*2197*/        int i = 0;
/*2198*/        do
                {
                    Object obj;
/*2198*/            if((obj = keyReferenceQueue.poll()) == null)
/*2200*/                break;
/*2200*/            obj = (eEntry)obj;
/*2201*/            map.reclaimKey(((eEntry) (obj)));
                } while(++i != 16);
            }

            void drainValueReferenceQueue()
            {
/*2211*/        int i = 0;
/*2212*/        do
                {
                    Object obj;
/*2212*/            if((obj = valueReferenceQueue.poll()) == null)
/*2214*/                break;
/*2214*/            obj = (erence)obj;
/*2215*/            map.reclaimValue(((erence) (obj)));
                } while(++i != 16);
            }

            void clearReferenceQueues()
            {
/*2226*/        if(map.usesKeyReferences())
/*2227*/            clearKeyReferenceQueue();
/*2229*/        if(map.usesValueReferences())
/*2230*/            clearValueReferenceQueue();
            }

            void clearKeyReferenceQueue()
            {
/*2235*/        while(keyReferenceQueue.poll() != null) ;
            }

            void clearValueReferenceQueue()
            {
/*2239*/        while(valueReferenceQueue.poll() != null) ;
            }

            void recordRead(eEntry eentry)
            {
/*2252*/        if(map.expiresAfterAccess())
/*2253*/            recordExpirationTime(eentry, map.expireAfterAccessNanos);
/*2255*/        recencyQueue.add(eentry);
            }

            void recordLockedRead(eEntry eentry)
            {
/*2267*/        evictionQueue.add(eentry);
/*2268*/        if(map.expiresAfterAccess())
                {
/*2269*/            recordExpirationTime(eentry, map.expireAfterAccessNanos);
/*2270*/            expirationQueue.add(eentry);
                }
            }

            void recordWrite(eEntry eentry)
            {
/*2281*/        drainRecencyQueue();
/*2282*/        evictionQueue.add(eentry);
/*2283*/        if(map.expires())
                {
/*2286*/            long l = map.expiresAfterAccess() ? map.expireAfterAccessNanos : map.expireAfterWriteNanos;
/*2289*/            recordExpirationTime(eentry, l);
/*2290*/            expirationQueue.add(eentry);
                }
            }

            void drainRecencyQueue()
            {
/*2303*/        do
                {
                    eEntry eentry;
/*2303*/            if((eentry = (eEntry)recencyQueue.poll()) == null)
/*2308*/                break;
/*2308*/            if(evictionQueue.contains(eentry))
/*2309*/                evictionQueue.add(eentry);
/*2311*/            if(map.expiresAfterAccess() && expirationQueue.contains(eentry))
/*2312*/                expirationQueue.add(eentry);
                } while(true);
            }

            void recordExpirationTime(eEntry eentry, long l)
            {
/*2321*/        eentry.setExpirationTime(map.ticker.read() + l);
            }

            void tryExpireEntries()
            {
/*2328*/        if(!tryLock())
/*2330*/            break MISSING_BLOCK_LABEL_23;
/*2330*/        expireEntries();
/*2332*/        unlock();
/*2334*/        return;
                Exception exception;
/*2332*/        exception;
/*2332*/        unlock();
/*2332*/        throw exception;
            }

            void expireEntries()
            {
/*2340*/        drainRecencyQueue();
/*2342*/        if(expirationQueue.isEmpty())
/*2345*/            return;
                eEntry eentry;
/*2347*/        for(long l = map.ticker.read(); (eentry = (eEntry)expirationQueue.peek()) != null && map.isExpired(eentry, l);)
/*2350*/            if(!removeEntry(eentry, eentry.getHash(), D))
/*2351*/                throw new AssertionError();

            }

            void enqueueNotification(eEntry eentry, eEntry eentry1)
            {
/*2359*/        enqueueNotification(eentry.getKey(), eentry.getHash(), eentry.getValueReference().get(), eentry1);
            }

            void enqueueNotification(Object obj, int i, Object obj1, erence.get get1)
            {
/*2363*/        if(map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE)
                {
/*2364*/            obj = new (obj, obj1, get1);
/*2365*/            map.removalNotificationQueue.offer(obj);
                }
            }

            boolean evictEntries()
            {
/*2377*/        if(map.evictsBySize() && count >= maxSegmentSize)
                {
/*2378*/            drainRecencyQueue();
/*2380*/            eEntry eentry = (eEntry)evictionQueue.remove();
/*2381*/            if(!removeEntry(eentry, eentry.getHash(), eEntry.getHash))
/*2382*/                throw new AssertionError();
/*2384*/            else
/*2384*/                return true;
                } else
                {
/*2386*/            return false;
                }
            }

            eEntry getFirst(int i)
            {
                AtomicReferenceArray atomicreferencearray;
/*2394*/        return (eEntry)(atomicreferencearray = table).get(i & atomicreferencearray.length() - 1);
            }

            eEntry getEntry(Object obj, int i)
            {
/*2401*/        if(count != 0)
                {
/*2402*/            for(eEntry eentry = getFirst(i); eentry != null; eentry = eentry.getNext())
                    {
/*2403*/                if(eentry.getHash() != i)
/*2407*/                    continue;
                        Object obj1;
/*2407*/                if((obj1 = eentry.getKey()) == null)
                        {
/*2409*/                    tryDrainReferenceQueues();
/*2410*/                    continue;
                        }
/*2413*/                if(map.keyEquivalence.equivalent(obj, obj1))
/*2414*/                    return eentry;
                    }

                }
/*2419*/        return null;
            }

            eEntry getLiveEntry(Object obj, int i)
            {
/*2423*/        if((obj = getEntry(obj, i)) == null)
/*2425*/            return null;
/*2426*/        if(map.expires() && map.isExpired(((eEntry) (obj))))
                {
/*2427*/            tryExpireEntries();
/*2428*/            return null;
                } else
                {
/*2430*/            return ((eEntry) (obj));
                }
            }

            Object get(Object obj, int i)
            {
/*2435*/        if((obj = getLiveEntry(obj, i)) == null)
                {
/*2448*/            postReadCleanup();
/*2448*/            return null;
                }
/*2440*/        if((i = ((int) (((eEntry) (obj)).getValueReference().get()))) != null)
/*2442*/            recordRead(((eEntry) (obj)));
/*2444*/        else
/*2444*/            tryDrainReferenceQueues();
/*2446*/        obj = i;
/*2448*/        postReadCleanup();
/*2448*/        return obj;
/*2448*/        obj;
/*2448*/        postReadCleanup();
/*2448*/        throw obj;
            }

            boolean containsKey(Object obj, int i)
            {
/*2454*/        if(count == 0)
/*2455*/            break MISSING_BLOCK_LABEL_50;
/*2455*/        if((obj = getLiveEntry(obj, i)) == null)
                {
/*2464*/            postReadCleanup();
/*2464*/            return false;
                }
/*2459*/        obj = ((eEntry) (obj)).getValueReference().get() == null ? 0 : 1;
/*2464*/        postReadCleanup();
/*2464*/        return ((boolean) (obj));
/*2464*/        postReadCleanup();
/*2464*/        return false;
/*2464*/        obj;
/*2464*/        postReadCleanup();
/*2464*/        throw obj;
            }

            Object put(Object obj, int i, Object obj1, boolean flag)
            {
/*2498*/        lock();
                int j;
                Object obj2;
                int k;
                eEntry eentry;
                eEntry eentry1;
/*2500*/        preWriteCleanup();
/*2502*/        if((j = count + 1) > threshold)
                {
/*2504*/            expand();
/*2505*/            j = count + 1;
                }
/*2508*/        obj2 = table;
/*2509*/        k = i & ((AtomicReferenceArray) (obj2)).length() - 1;
/*2510*/        eentry1 = eentry = (eEntry)((AtomicReferenceArray) (obj2)).get(k);
_L3:
/*2513*/        if(eentry1 == null)
/*2514*/            break MISSING_BLOCK_LABEL_290;
/*2514*/        Object obj3 = eentry1.getKey();
/*2515*/        if(eentry1.getHash() != i || obj3 == null || !map.keyEquivalence.equivalent(obj, obj3))
/*2519*/            break MISSING_BLOCK_LABEL_278;
/*2519*/        if((k = ((int) (((erence) (obj2 = eentry1.getValueReference())).get()))) != null) goto _L2; else goto _L1
_L1:
/*2523*/        modCount++;
/*2524*/        setValue(eentry1, obj1);
/*2525*/        if(!((erence) (obj2)).isComputingReference())
                {
/*2526*/            enqueueNotification(obj, i, k, TED);
/*2527*/            j = count;
                } else
/*2528*/        if(evictEntries())
/*2529*/            j = count + 1;
/*2531*/        count = j;
/*2560*/        unlock();
/*2561*/        postWriteCleanup();
/*2561*/        return null;
_L2:
/*2533*/        if(!flag)
/*2537*/            break MISSING_BLOCK_LABEL_237;
/*2537*/        recordLockedRead(eentry1);
/*2538*/        obj = k;
/*2560*/        unlock();
/*2561*/        postWriteCleanup();
/*2561*/        return obj;
/*2541*/        modCount++;
/*2542*/        enqueueNotification(obj, i, k, ED);
/*2543*/        setValue(eentry1, obj1);
/*2544*/        obj = k;
/*2560*/        unlock();
/*2561*/        postWriteCleanup();
/*2561*/        return obj;
/*2513*/        eentry1 = eentry1.getNext();
                  goto _L3
/*2550*/        modCount++;
/*2551*/        eEntry eentry2 = newEntry(obj, i, eentry);
/*2552*/        setValue(eentry2, obj1);
/*2553*/        ((AtomicReferenceArray) (obj2)).set(k, eentry2);
/*2554*/        if(evictEntries())
/*2555*/            j = count + 1;
/*2557*/        count = j;
/*2560*/        unlock();
/*2561*/        postWriteCleanup();
/*2561*/        return null;
/*2560*/        obj;
/*2560*/        unlock();
/*2561*/        postWriteCleanup();
/*2561*/        throw obj;
            }

            void expand()
            {
                AtomicReferenceArray atomicreferencearray;
                int i;
/*2570*/        if((i = (atomicreferencearray = table).length()) >= 0x40000000)
/*2573*/            return;
/*2586*/        int j = count;
/*2587*/        AtomicReferenceArray atomicreferencearray1 = newEntryArray(i << 1);
/*2588*/        threshold = (atomicreferencearray1.length() * 3) / 4;
/*2589*/        int k = atomicreferencearray1.length() - 1;
/*2590*/        for(int l = 0; l < i; l++)
                {
                    eEntry eentry;
/*2593*/            if((eentry = (eEntry)atomicreferencearray.get(l)) == null)
/*2596*/                continue;
/*2596*/            eEntry eentry2 = eentry.getNext();
/*2597*/            int i1 = eentry.getHash() & k;
/*2600*/            if(eentry2 == null)
                    {
/*2601*/                atomicreferencearray1.set(i1, eentry);
/*2601*/                continue;
                    }
/*2606*/            eEntry eentry4 = eentry;
/*2607*/            i1 = i1;
/*2608*/            for(eentry2 = eentry2; eentry2 != null; eentry2 = eentry2.getNext())
                    {
                        int j1;
/*2609*/                if((j1 = eentry2.getHash() & k) != i1)
                        {
/*2612*/                    i1 = j1;
/*2613*/                    eentry4 = eentry2;
                        }
                    }

/*2616*/            atomicreferencearray1.set(i1, eentry4);
/*2619*/            for(eEntry eentry3 = eentry; eentry3 != eentry4; eentry3 = eentry3.getNext())
                    {
/*2620*/                int k1 = eentry3.getHash() & k;
/*2621*/                eEntry eentry1 = (eEntry)atomicreferencearray1.get(k1);
/*2622*/                if((eentry1 = copyEntry(eentry3, eentry1)) != null)
                        {
/*2624*/                    atomicreferencearray1.set(k1, eentry1);
                        } else
                        {
/*2626*/                    removeCollectedEntry(eentry3);
/*2627*/                    j--;
                        }
                    }

                }

/*2633*/        table = atomicreferencearray1;
/*2634*/        count = j;
            }

            boolean replace(Object obj, int i, Object obj1, Object obj2)
            {
/*2638*/        lock();
                AtomicReferenceArray atomicreferencearray;
                int j;
                eEntry eentry;
                eEntry eentry1;
/*2640*/        preWriteCleanup();
/*2642*/        atomicreferencearray = table;
/*2643*/        j = i & atomicreferencearray.length() - 1;
/*2644*/        eentry1 = eentry = (eEntry)atomicreferencearray.get(j);
_L3:
                Object obj3;
/*2646*/        if(eentry1 == null)
/*2647*/            break MISSING_BLOCK_LABEL_265;
/*2647*/        obj3 = eentry1.getKey();
/*2648*/        if(eentry1.getHash() != i || obj3 == null || !map.keyEquivalence.equivalent(obj, obj3))
/*2652*/            break MISSING_BLOCK_LABEL_253;
                erence erence;
                Object obj4;
/*2652*/        if((obj4 = (erence = eentry1.getValueReference()).get()) != null) goto _L2; else goto _L1
_L1:
/*2655*/        if(isCollected(erence))
                {
/*2656*/            count;
/*2657*/            modCount++;
/*2658*/            enqueueNotification(obj3, i, obj4, TED);
/*2659*/            i = removeFromChain(eentry, eentry1);
/*2660*/            obj = count - 1;
/*2661*/            atomicreferencearray.set(j, i);
/*2662*/            count = ((int) (obj));
                }
/*2683*/        unlock();
/*2684*/        postWriteCleanup();
/*2684*/        return false;
_L2:
/*2667*/        if(!map.valueEquivalence.equivalent(obj1, obj4))
/*2668*/            break MISSING_BLOCK_LABEL_237;
/*2668*/        modCount++;
/*2669*/        enqueueNotification(obj, i, obj4, ED);
/*2670*/        setValue(eentry1, obj2);
/*2683*/        unlock();
/*2684*/        postWriteCleanup();
/*2684*/        return true;
/*2675*/        recordLockedRead(eentry1);
/*2683*/        unlock();
/*2684*/        postWriteCleanup();
/*2684*/        return false;
/*2646*/        eentry1 = eentry1.getNext();
                  goto _L3
/*2683*/        unlock();
/*2684*/        postWriteCleanup();
/*2684*/        return false;
/*2683*/        obj;
/*2683*/        unlock();
/*2684*/        postWriteCleanup();
/*2684*/        throw obj;
            }

            Object replace(Object obj, int i, Object obj1)
            {
/*2689*/        lock();
                AtomicReferenceArray atomicreferencearray;
                int j;
                eEntry eentry;
                eEntry eentry1;
/*2691*/        preWriteCleanup();
/*2693*/        atomicreferencearray = table;
/*2694*/        j = i & atomicreferencearray.length() - 1;
/*2695*/        eentry1 = eentry = (eEntry)atomicreferencearray.get(j);
_L3:
                Object obj2;
/*2697*/        if(eentry1 == null)
/*2698*/            break MISSING_BLOCK_LABEL_235;
/*2698*/        obj2 = eentry1.getKey();
/*2699*/        if(eentry1.getHash() != i || obj2 == null || !map.keyEquivalence.equivalent(obj, obj2))
/*2703*/            break MISSING_BLOCK_LABEL_223;
                erence erence;
                Object obj3;
/*2703*/        if((obj3 = (erence = eentry1.getValueReference()).get()) != null) goto _L2; else goto _L1
_L1:
/*2706*/        if(isCollected(erence))
                {
/*2707*/            count;
/*2708*/            modCount++;
/*2709*/            enqueueNotification(obj2, i, obj3, TED);
/*2710*/            i = removeFromChain(eentry, eentry1);
/*2711*/            obj = count - 1;
/*2712*/            atomicreferencearray.set(j, i);
/*2713*/            count = ((int) (obj));
                }
/*2727*/        unlock();
/*2728*/        postWriteCleanup();
/*2728*/        return null;
_L2:
/*2718*/        modCount++;
/*2719*/        enqueueNotification(obj, i, obj3, ED);
/*2720*/        setValue(eentry1, obj1);
/*2721*/        obj = obj3;
/*2727*/        unlock();
/*2728*/        postWriteCleanup();
/*2728*/        return obj;
/*2697*/        eentry1 = eentry1.getNext();
                  goto _L3
/*2727*/        unlock();
/*2728*/        postWriteCleanup();
/*2728*/        return null;
/*2727*/        obj;
/*2727*/        unlock();
/*2728*/        postWriteCleanup();
/*2728*/        throw obj;
            }

            Object remove(Object obj, int i)
            {
/*2733*/        lock();
                AtomicReferenceArray atomicreferencearray;
                int j;
                eEntry eentry;
                eEntry eentry1;
/*2735*/        preWriteCleanup();
/*2737*/        count;
/*2738*/        atomicreferencearray = table;
/*2739*/        j = i & atomicreferencearray.length() - 1;
/*2740*/        eentry1 = eentry = (eEntry)atomicreferencearray.get(j);
_L6:
                Object obj1;
/*2742*/        if(eentry1 == null)
/*2743*/            break MISSING_BLOCK_LABEL_213;
/*2743*/        obj1 = eentry1.getKey();
/*2744*/        if(eentry1.getHash() != i || obj1 == null || !map.keyEquivalence.equivalent(obj, obj1))
/*2746*/            break MISSING_BLOCK_LABEL_201;
                Object obj2;
/*2746*/        if((obj2 = ((erence) (obj = eentry1.getValueReference())).get()) == null) goto _L2; else goto _L1
_L1:
/*2751*/        obj = IT;
                  goto _L3
_L2:
/*2752*/        if(!isCollected(((erence) (obj)))) goto _L5; else goto _L4
_L4:
/*2753*/        obj = TED;
                  goto _L3
_L5:
/*2770*/        unlock();
/*2771*/        postWriteCleanup();
/*2771*/        return null;
_L3:
/*2758*/        modCount++;
/*2759*/        enqueueNotification(obj1, i, obj2, ((enqueueNotification) (obj)));
/*2760*/        i = removeFromChain(eentry, eentry1);
/*2761*/        obj = count - 1;
/*2762*/        atomicreferencearray.set(j, i);
/*2763*/        count = ((int) (obj));
/*2764*/        obj = obj2;
/*2770*/        unlock();
/*2771*/        postWriteCleanup();
/*2771*/        return obj;
/*2742*/        eentry1 = eentry1.getNext();
                  goto _L6
/*2770*/        unlock();
/*2771*/        postWriteCleanup();
/*2771*/        return null;
/*2770*/        obj;
/*2770*/        unlock();
/*2771*/        postWriteCleanup();
/*2771*/        throw obj;
            }

            boolean remove(Object obj, int i, Object obj1)
            {
/*2776*/        lock();
                AtomicReferenceArray atomicreferencearray;
                int j;
                eEntry eentry;
                eEntry eentry1;
/*2778*/        preWriteCleanup();
/*2780*/        count;
/*2781*/        atomicreferencearray = table;
/*2782*/        j = i & atomicreferencearray.length() - 1;
/*2783*/        eentry1 = eentry = (eEntry)atomicreferencearray.get(j);
_L6:
                Object obj2;
                Object obj3;
/*2785*/        if(eentry1 == null)
/*2786*/            break MISSING_BLOCK_LABEL_239;
/*2786*/        obj2 = eentry1.getKey();
/*2787*/        if(eentry1.getHash() != i || obj2 == null || !map.keyEquivalence.equivalent(obj, obj2))
/*2789*/            break MISSING_BLOCK_LABEL_227;
/*2789*/        obj3 = ((erence) (obj = eentry1.getValueReference())).get();
/*2793*/        if(!map.valueEquivalence.equivalent(obj1, obj3)) goto _L2; else goto _L1
_L1:
/*2794*/        obj1 = IT;
                  goto _L3
_L2:
/*2795*/        if(!isCollected(((erence) (obj)))) goto _L5; else goto _L4
_L4:
/*2796*/        obj1 = TED;
                  goto _L3
_L5:
/*2813*/        unlock();
/*2814*/        postWriteCleanup();
/*2814*/        return false;
_L3:
/*2801*/        modCount++;
/*2802*/        enqueueNotification(obj2, i, obj3, ((enqueueNotification) (obj1)));
/*2803*/        i = removeFromChain(eentry, eentry1);
/*2804*/        obj = count - 1;
/*2805*/        atomicreferencearray.set(j, i);
/*2806*/        count = ((int) (obj));
/*2807*/        obj = obj1 != IT ? 0 : 1;
/*2813*/        unlock();
/*2814*/        postWriteCleanup();
/*2814*/        return ((boolean) (obj));
/*2785*/        eentry1 = eentry1.getNext();
                  goto _L6
/*2813*/        unlock();
/*2814*/        postWriteCleanup();
/*2814*/        return false;
/*2813*/        obj;
/*2813*/        unlock();
/*2814*/        postWriteCleanup();
/*2814*/        throw obj;
            }

            void clear()
            {
/*2819*/        if(count == 0)
/*2820*/            break MISSING_BLOCK_LABEL_177;
/*2820*/        lock();
/*2822*/        AtomicReferenceArray atomicreferencearray = table;
/*2823*/        if(map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE)
                {
/*2824*/            for(int i = 0; i < atomicreferencearray.length(); i++)
                    {
/*2825*/                for(eEntry eentry = (eEntry)atomicreferencearray.get(i); eentry != null; eentry = eentry.getNext())
/*2827*/                    if(!eentry.getValueReference().isComputingReference())
/*2828*/                        enqueueNotification(eentry, IT);

                    }

                }
/*2833*/        for(int j = 0; j < atomicreferencearray.length(); j++)
/*2834*/            atomicreferencearray.set(j, null);

/*2836*/        clearReferenceQueues();
/*2837*/        evictionQueue.clear();
/*2838*/        expirationQueue.clear();
/*2839*/        readCount.set(0);
/*2841*/        modCount++;
/*2842*/        count = 0;
/*2844*/        unlock();
/*2845*/        postWriteCleanup();
/*2846*/        return;
                Exception exception;
/*2844*/        exception;
/*2844*/        unlock();
/*2845*/        postWriteCleanup();
/*2845*/        throw exception;
            }

            eEntry removeFromChain(eEntry eentry, eEntry eentry1)
            {
/*2864*/        evictionQueue.remove(eentry1);
/*2865*/        expirationQueue.remove(eentry1);
/*2867*/        int i = count;
/*2868*/        eEntry eentry2 = eentry1.getNext();
/*2869*/        for(eentry = eentry; eentry != eentry1; eentry = eentry.getNext())
                {
                    eEntry eentry3;
/*2870*/            if((eentry3 = copyEntry(eentry, eentry2)) != null)
                    {
/*2872*/                eentry2 = eentry3;
                    } else
                    {
/*2874*/                removeCollectedEntry(eentry);
/*2875*/                i--;
                    }
                }

/*2878*/        count = i;
/*2879*/        return eentry2;
            }

            void removeCollectedEntry(eEntry eentry)
            {
/*2883*/        enqueueNotification(eentry, TED);
/*2884*/        evictionQueue.remove(eentry);
/*2885*/        expirationQueue.remove(eentry);
            }

            boolean reclaimKey(eEntry eentry, int i)
            {
/*2892*/        lock();
                AtomicReferenceArray atomicreferencearray;
                int j;
                eEntry eentry1;
                eEntry eentry2;
/*2894*/        count;
/*2895*/        atomicreferencearray = table;
/*2896*/        j = i & atomicreferencearray.length() - 1;
/*2897*/        eentry2 = eentry1 = (eEntry)atomicreferencearray.get(j);
_L1:
/*2899*/        if(eentry2 == null)
/*2900*/            break MISSING_BLOCK_LABEL_136;
/*2900*/        if(eentry2 != eentry)
/*2901*/            break MISSING_BLOCK_LABEL_124;
/*2901*/        modCount++;
/*2902*/        enqueueNotification(eentry2.getKey(), i, eentry2.getValueReference().get(), TED);
/*2904*/        i = removeFromChain(eentry1, eentry2);
/*2905*/        eentry = count - 1;
/*2906*/        atomicreferencearray.set(j, i);
/*2907*/        count = eentry;
/*2914*/        unlock();
/*2915*/        postWriteCleanup();
/*2915*/        return true;
/*2899*/        eentry2 = eentry2.getNext();
                  goto _L1
/*2914*/        unlock();
/*2915*/        postWriteCleanup();
/*2915*/        return false;
/*2914*/        eentry;
/*2914*/        unlock();
/*2915*/        postWriteCleanup();
/*2915*/        throw eentry;
            }

            boolean reclaimValue(Object obj, int i, erence erence)
            {
/*2923*/        lock();
                AtomicReferenceArray atomicreferencearray;
                int j;
                eEntry eentry;
                eEntry eentry1;
/*2925*/        count;
/*2926*/        atomicreferencearray = table;
/*2927*/        j = i & atomicreferencearray.length() - 1;
/*2928*/        eentry1 = eentry = (eEntry)atomicreferencearray.get(j);
_L3:
/*2930*/        if(eentry1 == null)
/*2931*/            break MISSING_BLOCK_LABEL_201;
/*2931*/        Object obj1 = eentry1.getKey();
/*2932*/        if(eentry1.getHash() != i || obj1 == null || !map.keyEquivalence.equivalent(obj, obj1))
/*2934*/            break MISSING_BLOCK_LABEL_189;
                erence erence1;
/*2934*/        if((erence1 = eentry1.getValueReference()) != erence) goto _L2; else goto _L1
_L1:
/*2936*/        modCount++;
/*2937*/        enqueueNotification(obj, i, erence.get(), TED);
/*2938*/        i = removeFromChain(eentry, eentry1);
/*2939*/        obj = count - 1;
/*2940*/        atomicreferencearray.set(j, i);
/*2941*/        count = ((int) (obj));
/*2950*/        unlock();
/*2951*/        if(!isHeldByCurrentThread())
/*2952*/            postWriteCleanup();
/*2952*/        return true;
_L2:
/*2950*/        unlock();
/*2951*/        if(!isHeldByCurrentThread())
/*2952*/            postWriteCleanup();
/*2952*/        return false;
/*2930*/        eentry1 = eentry1.getNext();
                  goto _L3
/*2950*/        unlock();
/*2951*/        if(!isHeldByCurrentThread())
/*2952*/            postWriteCleanup();
/*2952*/        return false;
/*2950*/        obj;
/*2950*/        unlock();
/*2951*/        if(!isHeldByCurrentThread())
/*2952*/            postWriteCleanup();
/*2952*/        throw obj;
            }

            boolean removeEntry(eEntry eentry, int i, eEntry eentry1)
            {
/*2990*/        int _tmp = count;
/*2991*/        AtomicReferenceArray atomicreferencearray = table;
/*2992*/        int j = i & atomicreferencearray.length() - 1;
                eEntry eentry2;
/*2993*/        for(eEntry eentry3 = eentry2 = (eEntry)atomicreferencearray.get(j); eentry3 != null; eentry3 = eentry3.getNext())
/*2996*/            if(eentry3 == eentry)
                    {
/*2997*/                modCount++;
/*2998*/                enqueueNotification(eentry3.getKey(), i, eentry3.getValueReference().get(), eentry1);
/*2999*/                i = removeFromChain(eentry2, eentry3);
/*3000*/                eentry = count - 1;
/*3001*/                atomicreferencearray.set(j, i);
/*3002*/                count = eentry;
/*3003*/                return true;
                    }

/*3007*/        return false;
            }

            boolean isCollected(erence erence)
            {
/*3015*/        if(erence.isComputingReference())
/*3016*/            return false;
/*3018*/        return erence.get() == null;
            }

            Object getLiveValue(eEntry eentry)
            {
/*3026*/        if(eentry.getKey() == null)
                {
/*3027*/            tryDrainReferenceQueues();
/*3028*/            return null;
                }
                Object obj;
/*3030*/        if((obj = eentry.getValueReference().get()) == null)
                {
/*3032*/            tryDrainReferenceQueues();
/*3033*/            return null;
                }
/*3036*/        if(map.expires() && map.isExpired(eentry))
                {
/*3037*/            tryExpireEntries();
/*3038*/            return null;
                } else
                {
/*3040*/            return obj;
                }
            }

            void postReadCleanup()
            {
/*3049*/        if((readCount.incrementAndGet() & 0x3f) == 0)
/*3050*/            runCleanup();
            }

            void preWriteCleanup()
            {
/*3062*/        runLockedCleanup();
            }

            void postWriteCleanup()
            {
/*3069*/        runUnlockedCleanup();
            }

            void runCleanup()
            {
/*3073*/        runLockedCleanup();
/*3074*/        runUnlockedCleanup();
            }

            void runLockedCleanup()
            {
/*3078*/        if(!tryLock())
/*3080*/            break MISSING_BLOCK_LABEL_35;
/*3080*/        drainReferenceQueues();
/*3081*/        expireEntries();
/*3082*/        readCount.set(0);
/*3084*/        unlock();
/*3085*/        return;
                Exception exception;
/*3084*/        exception;
/*3084*/        unlock();
/*3084*/        throw exception;
            }

            void runUnlockedCleanup()
            {
/*3091*/        if(!isHeldByCurrentThread())
/*3092*/            map.processPendingNotifications();
            }

            final MapMakerInternalMap map;
            volatile int count;
            int modCount;
            int threshold;
            volatile AtomicReferenceArray table;
            final int maxSegmentSize;
            final ReferenceQueue keyReferenceQueue;
            final ReferenceQueue valueReferenceQueue;
            final Queue recencyQueue;
            final AtomicInteger readCount = new AtomicInteger();
            final Queue evictionQueue;
            final Queue expirationQueue;

            eEntry(MapMakerInternalMap mapmakerinternalmap, int i, int j)
            {
/*2091*/        map = mapmakerinternalmap;
/*2092*/        maxSegmentSize = j;
/*2093*/        initTable(newEntryArray(i));
/*2095*/        keyReferenceQueue = mapmakerinternalmap.usesKeyReferences() ? new ReferenceQueue() : null;
/*2098*/        valueReferenceQueue = mapmakerinternalmap.usesValueReferences() ? new ReferenceQueue() : null;
/*2101*/        recencyQueue = ((Queue) (!mapmakerinternalmap.evictsBySize() && !mapmakerinternalmap.expiresAfterAccess() ? MapMakerInternalMap.discardingQueue() : ((Queue) (new ConcurrentLinkedQueue()))));
/*2105*/        evictionQueue = ((Queue) (mapmakerinternalmap.evictsBySize() ? ((Queue) (new Queue())) : MapMakerInternalMap.discardingQueue()));
/*2109*/        expirationQueue = ((Queue) (mapmakerinternalmap.expires() ? ((Queue) (new onQueue())) : MapMakerInternalMap.discardingQueue()));
            }
}
