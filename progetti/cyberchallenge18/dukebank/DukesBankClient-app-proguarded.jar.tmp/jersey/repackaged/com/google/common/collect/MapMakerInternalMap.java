// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.lang.ref.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.primitives.Ints;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            GenericMapMaker, MapMaker, CollectPreconditions, AbstractSequentialIterator, 
//            AbstractMapEntry, Iterators

class MapMakerInternalMap extends AbstractMap
    implements Serializable, ConcurrentMap
{
    final class EntrySet extends AbstractSet
    {

                public final Iterator iterator()
                {
/*3838*/            return new EntryIterator();
                }

                public final boolean contains(Object obj)
                {
/*3843*/            if(!(obj instanceof java.util.Map.Entry))
/*3844*/                return false;
                    Object obj1;
/*3846*/            if((obj1 = ((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey()) == null)
/*3849*/                return false;
/*3851*/            return (obj1 = get(obj1)) != null && valueEquivalence.equivalent(((java.util.Map.Entry) (obj)).getValue(), obj1);
                }

                public final boolean remove(Object obj)
                {
/*3858*/            if(!(obj instanceof java.util.Map.Entry))
/*3859*/                return false;
                    Object obj1;
/*3861*/            return (obj1 = ((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey()) != null && MapMakerInternalMap.this.remove(obj1, ((java.util.Map.Entry) (obj)).getValue());
                }

                public final int size()
                {
/*3868*/            return MapMakerInternalMap.this.size();
                }

                public final boolean isEmpty()
                {
/*3873*/            return MapMakerInternalMap.this.isEmpty();
                }

                public final void clear()
                {
/*3878*/            MapMakerInternalMap.this.clear();
                }

                final MapMakerInternalMap this$0;

                EntrySet()
                {
/*3834*/            this$0 = MapMakerInternalMap.this;
/*3834*/            super();
                }
    }

    final class Values extends AbstractCollection
    {

                public final Iterator iterator()
                {
/*3810*/            return new ValueIterator();
                }

                public final int size()
                {
/*3815*/            return MapMakerInternalMap.this.size();
                }

                public final boolean isEmpty()
                {
/*3820*/            return MapMakerInternalMap.this.isEmpty();
                }

                public final boolean contains(Object obj)
                {
/*3825*/            return containsValue(obj);
                }

                public final void clear()
                {
/*3830*/            MapMakerInternalMap.this.clear();
                }

                final MapMakerInternalMap this$0;

                Values()
                {
/*3806*/            this$0 = MapMakerInternalMap.this;
/*3806*/            super();
                }
    }

    final class KeySet extends AbstractSet
    {

                public final Iterator iterator()
                {
/*3777*/            return new KeyIterator();
                }

                public final int size()
                {
/*3782*/            return MapMakerInternalMap.this.size();
                }

                public final boolean isEmpty()
                {
/*3787*/            return MapMakerInternalMap.this.isEmpty();
                }

                public final boolean contains(Object obj)
                {
/*3792*/            return containsKey(obj);
                }

                public final boolean remove(Object obj)
                {
/*3797*/            return MapMakerInternalMap.this.remove(obj) != null;
                }

                public final void clear()
                {
/*3802*/            MapMakerInternalMap.this.clear();
                }

                final MapMakerInternalMap this$0;

                KeySet()
                {
/*3773*/            this$0 = MapMakerInternalMap.this;
/*3773*/            super();
                }
    }

    final class EntryIterator extends HashIterator
    {

                public final java.util.Map.Entry next()
                {
/*3769*/            return nextEntry();
                }

                public final volatile Object next()
                {
/*3765*/            return next();
                }

                final MapMakerInternalMap this$0;

                EntryIterator()
                {
/*3765*/            this$0 = MapMakerInternalMap.this;
/*3765*/            super();
                }
    }

    final class WriteThroughEntry extends AbstractMapEntry
    {

                public final Object getKey()
                {
/*3733*/            return key;
                }

                public final Object getValue()
                {
/*3738*/            return value;
                }

                public final boolean equals(Object obj)
                {
/*3744*/            if(obj instanceof java.util.Map.Entry)
                    {
/*3745*/                obj = (java.util.Map.Entry)obj;
/*3746*/                return key.equals(((java.util.Map.Entry) (obj)).getKey()) && value.equals(((java.util.Map.Entry) (obj)).getValue());
                    } else
                    {
/*3748*/                return false;
                    }
                }

                public final int hashCode()
                {
/*3754*/            return key.hashCode() ^ value.hashCode();
                }

                public final Object setValue(Object obj)
                {
/*3759*/            Object obj1 = put(key, obj);
/*3760*/            value = obj;
/*3761*/            return obj1;
                }

                final Object key;
                Object value;
                final MapMakerInternalMap this$0;

                WriteThroughEntry(Object obj, Object obj1)
                {
/*3726*/            this$0 = MapMakerInternalMap.this;
/*3726*/            super();
/*3727*/            key = obj;
/*3728*/            value = obj1;
                }
    }

    final class ValueIterator extends HashIterator
    {

                public final Object next()
                {
/*3714*/            return nextEntry().getValue();
                }

                final MapMakerInternalMap this$0;

                ValueIterator()
                {
/*3710*/            this$0 = MapMakerInternalMap.this;
/*3710*/            super();
                }
    }

    final class KeyIterator extends HashIterator
    {

                public final Object next()
                {
/*3706*/            return nextEntry().getKey();
                }

                final MapMakerInternalMap this$0;

                KeyIterator()
                {
/*3702*/            this$0 = MapMakerInternalMap.this;
/*3702*/            super();
                }
    }

    abstract class HashIterator
        implements Iterator
    {

                final void advance()
                {
/*3610*/            nextExternal = null;
/*3612*/            if(nextInChain())
/*3613*/                return;
/*3616*/            if(nextInTable())
/*3617*/                return;
/*3620*/            while(nextSegmentIndex >= 0) 
                    {
/*3621*/                currentSegment = segments[nextSegmentIndex--];
/*3622*/                if(currentSegment.count != 0)
                        {
/*3623*/                    currentTable = currentSegment.table;
/*3624*/                    nextTableIndex = currentTable.length() - 1;
/*3625*/                    if(nextInTable())
/*3626*/                        return;
                        }
                    }
                }

                boolean nextInChain()
                {
/*3636*/            if(nextEntry != null)
/*3637*/                for(nextEntry = nextEntry.getNext(); nextEntry != null; nextEntry = nextEntry.getNext())
/*3638*/                    if(advanceTo(nextEntry))
/*3639*/                        return true;

/*3643*/            return false;
                }

                boolean nextInTable()
                {
/*3650*/            while(nextTableIndex >= 0) 
/*3651*/                if((nextEntry = (ReferenceEntry)currentTable.get(nextTableIndex--)) != null && (advanceTo(nextEntry) || nextInChain()))
/*3653*/                    return true;
/*3657*/            return false;
                }

                boolean advanceTo(ReferenceEntry referenceentry)
                {
/*3666*/            Object obj = referenceentry.getKey();
/*3667*/            if((referenceentry = ((ReferenceEntry) (getLiveValue(referenceentry)))) == null)
/*3669*/                break MISSING_BLOCK_LABEL_46;
/*3669*/            nextExternal = new WriteThroughEntry(obj, referenceentry);
/*3676*/            currentSegment.postReadCleanup();
/*3676*/            return true;
/*3676*/            currentSegment.postReadCleanup();
/*3676*/            return false;
/*3676*/            referenceentry;
/*3676*/            currentSegment.postReadCleanup();
/*3676*/            throw referenceentry;
                }

                public boolean hasNext()
                {
/*3682*/            return nextExternal != null;
                }

                WriteThroughEntry nextEntry()
                {
/*3686*/            if(nextExternal == null)
                    {
/*3687*/                throw new NoSuchElementException();
                    } else
                    {
/*3689*/                lastReturned = nextExternal;
/*3690*/                advance();
/*3691*/                return lastReturned;
                    }
                }

                public void remove()
                {
/*3696*/            CollectPreconditions.checkRemove(lastReturned != null);
/*3697*/            MapMakerInternalMap.this.remove(lastReturned.getKey());
/*3698*/            lastReturned = null;
                }

                int nextSegmentIndex;
                int nextTableIndex;
                Segment currentSegment;
                AtomicReferenceArray currentTable;
                ReferenceEntry nextEntry;
                WriteThroughEntry nextExternal;
                WriteThroughEntry lastReturned;
                final MapMakerInternalMap this$0;

                HashIterator()
                {
/*3600*/            this$0 = MapMakerInternalMap.this;
/*3600*/            super();
/*3601*/            nextSegmentIndex = segments.length - 1;
/*3602*/            nextTableIndex = -1;
/*3603*/            advance();
                }
    }

    static final class ExpirationQueue extends AbstractQueue
    {

                public final boolean offer(ReferenceEntry referenceentry)
                {
/*3280*/            MapMakerInternalMap.connectExpirables(referenceentry.getPreviousExpirable(), referenceentry.getNextExpirable());
/*3283*/            MapMakerInternalMap.connectExpirables(head.getPreviousExpirable(), referenceentry);
/*3284*/            MapMakerInternalMap.connectExpirables(referenceentry, head);
/*3286*/            return true;
                }

                public final ReferenceEntry peek()
                {
                    ReferenceEntry referenceentry;
/*3291*/            if((referenceentry = head.getNextExpirable()) == head)
/*3292*/                return null;
/*3292*/            else
/*3292*/                return referenceentry;
                }

                public final ReferenceEntry poll()
                {
                    ReferenceEntry referenceentry;
/*3297*/            if((referenceentry = head.getNextExpirable()) == head)
                    {
/*3299*/                return null;
                    } else
                    {
/*3302*/                remove(referenceentry);
/*3303*/                return referenceentry;
                    }
                }

                public final boolean remove(Object obj)
                {
/*3309*/            ReferenceEntry referenceentry = ((ReferenceEntry) (obj = (ReferenceEntry)obj)).getPreviousExpirable();
/*3311*/            ReferenceEntry referenceentry1 = ((ReferenceEntry) (obj)).getNextExpirable();
/*3312*/            MapMakerInternalMap.connectExpirables(referenceentry, referenceentry1);
/*3313*/            MapMakerInternalMap.nullifyExpirable(((ReferenceEntry) (obj)));
/*3315*/            return referenceentry1 != NullEntry.INSTANCE;
                }

                public final boolean contains(Object obj)
                {
/*3321*/            return ((ReferenceEntry) (obj = (ReferenceEntry)obj)).getNextExpirable() != NullEntry.INSTANCE;
                }

                public final boolean isEmpty()
                {
/*3327*/            return head.getNextExpirable() == head;
                }

                public final int size()
                {
/*3332*/            int i = 0;
/*3333*/            for(ReferenceEntry referenceentry = head.getNextExpirable(); referenceentry != head; referenceentry = referenceentry.getNextExpirable())
/*3334*/                i++;

/*3336*/            return i;
                }

                public final void clear()
                {
                    ReferenceEntry referenceentry1;
/*3341*/            for(ReferenceEntry referenceentry = head.getNextExpirable(); referenceentry != head; referenceentry = referenceentry1)
                    {
/*3343*/                referenceentry1 = referenceentry.getNextExpirable();
/*3344*/                MapMakerInternalMap.nullifyExpirable(referenceentry);
                    }

/*3348*/            head.setNextExpirable(head);
/*3349*/            head.setPreviousExpirable(head);
                }

                public final Iterator iterator()
                {
/*3354*/            return new AbstractSequentialIterator(peek()) {

                        protected ReferenceEntry computeNext(ReferenceEntry referenceentry)
                        {
/*3357*/                    if((referenceentry = referenceentry.getNextExpirable()) == head)
/*3358*/                        return null;
/*3358*/                    else
/*3358*/                        return referenceentry;
                        }

                        protected volatile Object computeNext(Object obj)
                        {
/*3354*/                    return computeNext((ReferenceEntry)obj);
                        }

                        final ExpirationQueue this$0;

                        
                        {
/*3354*/                    this$0 = ExpirationQueue.this;
/*3354*/                    super(referenceentry);
                        }
            };
                }

                public final volatile Object peek()
                {
/*3239*/            return peek();
                }

                public final volatile Object poll()
                {
/*3239*/            return poll();
                }

                public final volatile boolean offer(Object obj)
                {
/*3239*/            return offer((ReferenceEntry)obj);
                }

                final ReferenceEntry head = new AbstractReferenceEntry() {

                    public long getExpirationTime()
                    {
/*3244*/                return 0x7fffffffffffffffL;
                    }

                    public void setExpirationTime(long l)
                    {
                    }

                    public ReferenceEntry getNextExpirable()
                    {
/*3254*/                return nextExpirable;
                    }

                    public void setNextExpirable(ReferenceEntry referenceentry)
                    {
/*3259*/                nextExpirable = referenceentry;
                    }

                    public ReferenceEntry getPreviousExpirable()
                    {
/*3266*/                return previousExpirable;
                    }

                    public void setPreviousExpirable(ReferenceEntry referenceentry)
                    {
/*3271*/                previousExpirable = referenceentry;
                    }

                    ReferenceEntry nextExpirable;
                    ReferenceEntry previousExpirable;
                    final ExpirationQueue this$0;

                        
                        {
/*3240*/                    this$0 = ExpirationQueue.this;
/*3240*/                    super();
/*3250*/                    nextExpirable = this;
/*3262*/                    previousExpirable = this;
                        }
        };

                ExpirationQueue()
                {
                }
    }

    static final class EvictionQueue extends AbstractQueue
    {

                public final boolean offer(ReferenceEntry referenceentry)
                {
/*3144*/            MapMakerInternalMap.connectEvictables(referenceentry.getPreviousEvictable(), referenceentry.getNextEvictable());
/*3147*/            MapMakerInternalMap.connectEvictables(head.getPreviousEvictable(), referenceentry);
/*3148*/            MapMakerInternalMap.connectEvictables(referenceentry, head);
/*3150*/            return true;
                }

                public final ReferenceEntry peek()
                {
                    ReferenceEntry referenceentry;
/*3155*/            if((referenceentry = head.getNextEvictable()) == head)
/*3156*/                return null;
/*3156*/            else
/*3156*/                return referenceentry;
                }

                public final ReferenceEntry poll()
                {
                    ReferenceEntry referenceentry;
/*3161*/            if((referenceentry = head.getNextEvictable()) == head)
                    {
/*3163*/                return null;
                    } else
                    {
/*3166*/                remove(referenceentry);
/*3167*/                return referenceentry;
                    }
                }

                public final boolean remove(Object obj)
                {
/*3173*/            ReferenceEntry referenceentry = ((ReferenceEntry) (obj = (ReferenceEntry)obj)).getPreviousEvictable();
/*3175*/            ReferenceEntry referenceentry1 = ((ReferenceEntry) (obj)).getNextEvictable();
/*3176*/            MapMakerInternalMap.connectEvictables(referenceentry, referenceentry1);
/*3177*/            MapMakerInternalMap.nullifyEvictable(((ReferenceEntry) (obj)));
/*3179*/            return referenceentry1 != NullEntry.INSTANCE;
                }

                public final boolean contains(Object obj)
                {
/*3185*/            return ((ReferenceEntry) (obj = (ReferenceEntry)obj)).getNextEvictable() != NullEntry.INSTANCE;
                }

                public final boolean isEmpty()
                {
/*3191*/            return head.getNextEvictable() == head;
                }

                public final int size()
                {
/*3196*/            int i = 0;
/*3197*/            for(ReferenceEntry referenceentry = head.getNextEvictable(); referenceentry != head; referenceentry = referenceentry.getNextEvictable())
/*3198*/                i++;

/*3200*/            return i;
                }

                public final void clear()
                {
                    ReferenceEntry referenceentry1;
/*3205*/            for(ReferenceEntry referenceentry = head.getNextEvictable(); referenceentry != head; referenceentry = referenceentry1)
                    {
/*3207*/                referenceentry1 = referenceentry.getNextEvictable();
/*3208*/                MapMakerInternalMap.nullifyEvictable(referenceentry);
                    }

/*3212*/            head.setNextEvictable(head);
/*3213*/            head.setPreviousEvictable(head);
                }

                public final Iterator iterator()
                {
/*3218*/            return new AbstractSequentialIterator(peek()) {

                        protected ReferenceEntry computeNext(ReferenceEntry referenceentry)
                        {
/*3221*/                    if((referenceentry = referenceentry.getNextEvictable()) == head)
/*3222*/                        return null;
/*3222*/                    else
/*3222*/                        return referenceentry;
                        }

                        protected volatile Object computeNext(Object obj)
                        {
/*3218*/                    return computeNext((ReferenceEntry)obj);
                        }

                        final EvictionQueue this$0;

                        
                        {
/*3218*/                    this$0 = EvictionQueue.this;
/*3218*/                    super(referenceentry);
                        }
            };
                }

                public final volatile Object peek()
                {
/*3111*/            return peek();
                }

                public final volatile Object poll()
                {
/*3111*/            return poll();
                }

                public final volatile boolean offer(Object obj)
                {
/*3111*/            return offer((ReferenceEntry)obj);
                }

                final ReferenceEntry head = new AbstractReferenceEntry() {

                    public ReferenceEntry getNextEvictable()
                    {
/*3118*/                return nextEvictable;
                    }

                    public void setNextEvictable(ReferenceEntry referenceentry)
                    {
/*3123*/                nextEvictable = referenceentry;
                    }

                    public ReferenceEntry getPreviousEvictable()
                    {
/*3130*/                return previousEvictable;
                    }

                    public void setPreviousEvictable(ReferenceEntry referenceentry)
                    {
/*3135*/                previousEvictable = referenceentry;
                    }

                    ReferenceEntry nextEvictable;
                    ReferenceEntry previousEvictable;
                    final EvictionQueue this$0;

                        
                        {
/*3112*/                    this$0 = EvictionQueue.this;
/*3112*/                    super();
/*3114*/                    nextEvictable = this;
/*3126*/                    previousEvictable = this;
                        }
        };

                EvictionQueue()
                {
                }
    }

    static class Segment extends ReentrantLock
    {

                AtomicReferenceArray newEntryArray(int i)
                {
/*2115*/            return new AtomicReferenceArray(i);
                }

                void initTable(AtomicReferenceArray atomicreferencearray)
                {
/*2119*/            threshold = (atomicreferencearray.length() * 3) / 4;
/*2120*/            if(threshold == maxSegmentSize)
/*2122*/                threshold++;
/*2124*/            table = atomicreferencearray;
                }

                ReferenceEntry newEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/*2129*/            return map.entryFactory.newEntry(this, obj, i, referenceentry);
                }

                ReferenceEntry copyEntry(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/*2138*/            if(referenceentry.getKey() == null)
/*2140*/                return null;
                    ValueReference valuereference;
                    Object obj;
/*2143*/            if((obj = (valuereference = referenceentry.getValueReference()).get()) == null && !valuereference.isComputingReference())
                    {
/*2147*/                return null;
                    } else
                    {
/*2150*/                (referenceentry = map.entryFactory.copyEntry(this, referenceentry, referenceentry1)).setValueReference(valuereference.copyFor(valueReferenceQueue, obj, referenceentry));
/*2152*/                return referenceentry;
                    }
                }

                void setValue(ReferenceEntry referenceentry, Object obj)
                {
/*2160*/            obj = map.valueStrength.referenceValue(this, referenceentry, obj);
/*2161*/            referenceentry.setValueReference(((ValueReference) (obj)));
/*2162*/            recordWrite(referenceentry);
                }

                void tryDrainReferenceQueues()
                {
/*2171*/            if(!tryLock())
/*2173*/                break MISSING_BLOCK_LABEL_23;
/*2173*/            drainReferenceQueues();
/*2175*/            unlock();
/*2176*/            return;
                    Exception exception;
/*2175*/            exception;
/*2175*/            unlock();
/*2175*/            throw exception;
                }

                void drainReferenceQueues()
                {
/*2186*/            if(map.usesKeyReferences())
/*2187*/                drainKeyReferenceQueue();
/*2189*/            if(map.usesValueReferences())
/*2190*/                drainValueReferenceQueue();
                }

                void drainKeyReferenceQueue()
                {
/*2197*/            int i = 0;
/*2198*/            do
                    {
                        Object obj;
/*2198*/                if((obj = keyReferenceQueue.poll()) == null)
/*2200*/                    break;
/*2200*/                obj = (ReferenceEntry)obj;
/*2201*/                map.reclaimKey(((ReferenceEntry) (obj)));
                    } while(++i != 16);
                }

                void drainValueReferenceQueue()
                {
/*2211*/            int i = 0;
/*2212*/            do
                    {
                        Object obj;
/*2212*/                if((obj = valueReferenceQueue.poll()) == null)
/*2214*/                    break;
/*2214*/                obj = (ValueReference)obj;
/*2215*/                map.reclaimValue(((ValueReference) (obj)));
                    } while(++i != 16);
                }

                void clearReferenceQueues()
                {
/*2226*/            if(map.usesKeyReferences())
/*2227*/                clearKeyReferenceQueue();
/*2229*/            if(map.usesValueReferences())
/*2230*/                clearValueReferenceQueue();
                }

                void clearKeyReferenceQueue()
                {
/*2235*/            while(keyReferenceQueue.poll() != null) ;
                }

                void clearValueReferenceQueue()
                {
/*2239*/            while(valueReferenceQueue.poll() != null) ;
                }

                void recordRead(ReferenceEntry referenceentry)
                {
/*2252*/            if(map.expiresAfterAccess())
/*2253*/                recordExpirationTime(referenceentry, map.expireAfterAccessNanos);
/*2255*/            recencyQueue.add(referenceentry);
                }

                void recordLockedRead(ReferenceEntry referenceentry)
                {
/*2267*/            evictionQueue.add(referenceentry);
/*2268*/            if(map.expiresAfterAccess())
                    {
/*2269*/                recordExpirationTime(referenceentry, map.expireAfterAccessNanos);
/*2270*/                expirationQueue.add(referenceentry);
                    }
                }

                void recordWrite(ReferenceEntry referenceentry)
                {
/*2281*/            drainRecencyQueue();
/*2282*/            evictionQueue.add(referenceentry);
/*2283*/            if(map.expires())
                    {
/*2286*/                long l = map.expiresAfterAccess() ? map.expireAfterAccessNanos : map.expireAfterWriteNanos;
/*2289*/                recordExpirationTime(referenceentry, l);
/*2290*/                expirationQueue.add(referenceentry);
                    }
                }

                void drainRecencyQueue()
                {
/*2303*/            do
                    {
                        ReferenceEntry referenceentry;
/*2303*/                if((referenceentry = (ReferenceEntry)recencyQueue.poll()) == null)
/*2308*/                    break;
/*2308*/                if(evictionQueue.contains(referenceentry))
/*2309*/                    evictionQueue.add(referenceentry);
/*2311*/                if(map.expiresAfterAccess() && expirationQueue.contains(referenceentry))
/*2312*/                    expirationQueue.add(referenceentry);
                    } while(true);
                }

                void recordExpirationTime(ReferenceEntry referenceentry, long l)
                {
/*2321*/            referenceentry.setExpirationTime(map.ticker.read() + l);
                }

                void tryExpireEntries()
                {
/*2328*/            if(!tryLock())
/*2330*/                break MISSING_BLOCK_LABEL_23;
/*2330*/            expireEntries();
/*2332*/            unlock();
/*2334*/            return;
                    Exception exception;
/*2332*/            exception;
/*2332*/            unlock();
/*2332*/            throw exception;
                }

                void expireEntries()
                {
/*2340*/            drainRecencyQueue();
/*2342*/            if(expirationQueue.isEmpty())
/*2345*/                return;
                    ReferenceEntry referenceentry;
/*2347*/            for(long l = map.ticker.read(); (referenceentry = (ReferenceEntry)expirationQueue.peek()) != null && map.isExpired(referenceentry, l);)
/*2350*/                if(!removeEntry(referenceentry, referenceentry.getHash(), MapMaker.RemovalCause.EXPIRED))
/*2351*/                    throw new AssertionError();

                }

                void enqueueNotification(ReferenceEntry referenceentry, MapMaker.RemovalCause removalcause)
                {
/*2359*/            enqueueNotification(referenceentry.getKey(), referenceentry.getHash(), referenceentry.getValueReference().get(), removalcause);
                }

                void enqueueNotification(Object obj, int i, Object obj1, MapMaker.RemovalCause removalcause)
                {
/*2363*/            if(map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE)
                    {
/*2364*/                obj = new MapMaker.RemovalNotification(obj, obj1, removalcause);
/*2365*/                map.removalNotificationQueue.offer(obj);
                    }
                }

                boolean evictEntries()
                {
/*2377*/            if(map.evictsBySize() && count >= maxSegmentSize)
                    {
/*2378*/                drainRecencyQueue();
/*2380*/                ReferenceEntry referenceentry = (ReferenceEntry)evictionQueue.remove();
/*2381*/                if(!removeEntry(referenceentry, referenceentry.getHash(), MapMaker.RemovalCause.SIZE))
/*2382*/                    throw new AssertionError();
/*2384*/                else
/*2384*/                    return true;
                    } else
                    {
/*2386*/                return false;
                    }
                }

                ReferenceEntry getFirst(int i)
                {
                    AtomicReferenceArray atomicreferencearray;
/*2394*/            return (ReferenceEntry)(atomicreferencearray = table).get(i & atomicreferencearray.length() - 1);
                }

                ReferenceEntry getEntry(Object obj, int i)
                {
/*2401*/            if(count != 0)
                    {
/*2402*/                for(ReferenceEntry referenceentry = getFirst(i); referenceentry != null; referenceentry = referenceentry.getNext())
                        {
/*2403*/                    if(referenceentry.getHash() != i)
/*2407*/                        continue;
                            Object obj1;
/*2407*/                    if((obj1 = referenceentry.getKey()) == null)
                            {
/*2409*/                        tryDrainReferenceQueues();
/*2410*/                        continue;
                            }
/*2413*/                    if(map.keyEquivalence.equivalent(obj, obj1))
/*2414*/                        return referenceentry;
                        }

                    }
/*2419*/            return null;
                }

                ReferenceEntry getLiveEntry(Object obj, int i)
                {
/*2423*/            if((obj = getEntry(obj, i)) == null)
/*2425*/                return null;
/*2426*/            if(map.expires() && map.isExpired(((ReferenceEntry) (obj))))
                    {
/*2427*/                tryExpireEntries();
/*2428*/                return null;
                    } else
                    {
/*2430*/                return ((ReferenceEntry) (obj));
                    }
                }

                Object get(Object obj, int i)
                {
/*2435*/            if((obj = getLiveEntry(obj, i)) == null)
                    {
/*2448*/                postReadCleanup();
/*2448*/                return null;
                    }
/*2440*/            if((i = ((int) (((ReferenceEntry) (obj)).getValueReference().get()))) != null)
/*2442*/                recordRead(((ReferenceEntry) (obj)));
/*2444*/            else
/*2444*/                tryDrainReferenceQueues();
/*2446*/            obj = i;
/*2448*/            postReadCleanup();
/*2448*/            return obj;
/*2448*/            obj;
/*2448*/            postReadCleanup();
/*2448*/            throw obj;
                }

                boolean containsKey(Object obj, int i)
                {
/*2454*/            if(count == 0)
/*2455*/                break MISSING_BLOCK_LABEL_50;
/*2455*/            if((obj = getLiveEntry(obj, i)) == null)
                    {
/*2464*/                postReadCleanup();
/*2464*/                return false;
                    }
/*2459*/            obj = ((ReferenceEntry) (obj)).getValueReference().get() == null ? 0 : 1;
/*2464*/            postReadCleanup();
/*2464*/            return ((boolean) (obj));
/*2464*/            postReadCleanup();
/*2464*/            return false;
/*2464*/            obj;
/*2464*/            postReadCleanup();
/*2464*/            throw obj;
                }

                Object put(Object obj, int i, Object obj1, boolean flag)
                {
/*2498*/            lock();
                    int j;
                    Object obj2;
                    int k;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*2500*/            preWriteCleanup();
/*2502*/            if((j = count + 1) > threshold)
                    {
/*2504*/                expand();
/*2505*/                j = count + 1;
                    }
/*2508*/            obj2 = table;
/*2509*/            k = i & ((AtomicReferenceArray) (obj2)).length() - 1;
/*2510*/            referenceentry1 = referenceentry = (ReferenceEntry)((AtomicReferenceArray) (obj2)).get(k);
_L3:
/*2513*/            if(referenceentry1 == null)
/*2514*/                break MISSING_BLOCK_LABEL_290;
/*2514*/            Object obj3 = referenceentry1.getKey();
/*2515*/            if(referenceentry1.getHash() != i || obj3 == null || !map.keyEquivalence.equivalent(obj, obj3))
/*2519*/                break MISSING_BLOCK_LABEL_278;
/*2519*/            if((k = ((int) (((ValueReference) (obj2 = referenceentry1.getValueReference())).get()))) != null) goto _L2; else goto _L1
_L1:
/*2523*/            modCount++;
/*2524*/            setValue(referenceentry1, obj1);
/*2525*/            if(!((ValueReference) (obj2)).isComputingReference())
                    {
/*2526*/                enqueueNotification(obj, i, k, MapMaker.RemovalCause.COLLECTED);
/*2527*/                j = count;
                    } else
/*2528*/            if(evictEntries())
/*2529*/                j = count + 1;
/*2531*/            count = j;
/*2560*/            unlock();
/*2561*/            postWriteCleanup();
/*2561*/            return null;
_L2:
/*2533*/            if(!flag)
/*2537*/                break MISSING_BLOCK_LABEL_237;
/*2537*/            recordLockedRead(referenceentry1);
/*2538*/            obj = k;
/*2560*/            unlock();
/*2561*/            postWriteCleanup();
/*2561*/            return obj;
/*2541*/            modCount++;
/*2542*/            enqueueNotification(obj, i, k, MapMaker.RemovalCause.REPLACED);
/*2543*/            setValue(referenceentry1, obj1);
/*2544*/            obj = k;
/*2560*/            unlock();
/*2561*/            postWriteCleanup();
/*2561*/            return obj;
/*2513*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*2550*/            modCount++;
/*2551*/            ReferenceEntry referenceentry2 = newEntry(obj, i, referenceentry);
/*2552*/            setValue(referenceentry2, obj1);
/*2553*/            ((AtomicReferenceArray) (obj2)).set(k, referenceentry2);
/*2554*/            if(evictEntries())
/*2555*/                j = count + 1;
/*2557*/            count = j;
/*2560*/            unlock();
/*2561*/            postWriteCleanup();
/*2561*/            return null;
/*2560*/            obj;
/*2560*/            unlock();
/*2561*/            postWriteCleanup();
/*2561*/            throw obj;
                }

                void expand()
                {
                    AtomicReferenceArray atomicreferencearray;
                    int i;
/*2570*/            if((i = (atomicreferencearray = table).length()) >= 0x40000000)
/*2573*/                return;
/*2586*/            int j = count;
/*2587*/            AtomicReferenceArray atomicreferencearray1 = newEntryArray(i << 1);
/*2588*/            threshold = (atomicreferencearray1.length() * 3) / 4;
/*2589*/            int k = atomicreferencearray1.length() - 1;
/*2590*/            for(int l = 0; l < i; l++)
                    {
                        ReferenceEntry referenceentry;
/*2593*/                if((referenceentry = (ReferenceEntry)atomicreferencearray.get(l)) == null)
/*2596*/                    continue;
/*2596*/                ReferenceEntry referenceentry2 = referenceentry.getNext();
/*2597*/                int i1 = referenceentry.getHash() & k;
/*2600*/                if(referenceentry2 == null)
                        {
/*2601*/                    atomicreferencearray1.set(i1, referenceentry);
/*2601*/                    continue;
                        }
/*2606*/                ReferenceEntry referenceentry4 = referenceentry;
/*2607*/                i1 = i1;
/*2608*/                for(referenceentry2 = referenceentry2; referenceentry2 != null; referenceentry2 = referenceentry2.getNext())
                        {
                            int j1;
/*2609*/                    if((j1 = referenceentry2.getHash() & k) != i1)
                            {
/*2612*/                        i1 = j1;
/*2613*/                        referenceentry4 = referenceentry2;
                            }
                        }

/*2616*/                atomicreferencearray1.set(i1, referenceentry4);
/*2619*/                for(ReferenceEntry referenceentry3 = referenceentry; referenceentry3 != referenceentry4; referenceentry3 = referenceentry3.getNext())
                        {
/*2620*/                    int k1 = referenceentry3.getHash() & k;
/*2621*/                    ReferenceEntry referenceentry1 = (ReferenceEntry)atomicreferencearray1.get(k1);
/*2622*/                    if((referenceentry1 = copyEntry(referenceentry3, referenceentry1)) != null)
                            {
/*2624*/                        atomicreferencearray1.set(k1, referenceentry1);
                            } else
                            {
/*2626*/                        removeCollectedEntry(referenceentry3);
/*2627*/                        j--;
                            }
                        }

                    }

/*2633*/            table = atomicreferencearray1;
/*2634*/            count = j;
                }

                boolean replace(Object obj, int i, Object obj1, Object obj2)
                {
/*2638*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*2640*/            preWriteCleanup();
/*2642*/            atomicreferencearray = table;
/*2643*/            j = i & atomicreferencearray.length() - 1;
/*2644*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L3:
                    Object obj3;
/*2646*/            if(referenceentry1 == null)
/*2647*/                break MISSING_BLOCK_LABEL_265;
/*2647*/            obj3 = referenceentry1.getKey();
/*2648*/            if(referenceentry1.getHash() != i || obj3 == null || !map.keyEquivalence.equivalent(obj, obj3))
/*2652*/                break MISSING_BLOCK_LABEL_253;
                    ValueReference valuereference;
                    Object obj4;
/*2652*/            if((obj4 = (valuereference = referenceentry1.getValueReference()).get()) != null) goto _L2; else goto _L1
_L1:
/*2655*/            if(isCollected(valuereference))
                    {
/*2656*/                count;
/*2657*/                modCount++;
/*2658*/                enqueueNotification(obj3, i, obj4, MapMaker.RemovalCause.COLLECTED);
/*2659*/                i = removeFromChain(referenceentry, referenceentry1);
/*2660*/                obj = count - 1;
/*2661*/                atomicreferencearray.set(j, i);
/*2662*/                count = ((int) (obj));
                    }
/*2683*/            unlock();
/*2684*/            postWriteCleanup();
/*2684*/            return false;
_L2:
/*2667*/            if(!map.valueEquivalence.equivalent(obj1, obj4))
/*2668*/                break MISSING_BLOCK_LABEL_237;
/*2668*/            modCount++;
/*2669*/            enqueueNotification(obj, i, obj4, MapMaker.RemovalCause.REPLACED);
/*2670*/            setValue(referenceentry1, obj2);
/*2683*/            unlock();
/*2684*/            postWriteCleanup();
/*2684*/            return true;
/*2675*/            recordLockedRead(referenceentry1);
/*2683*/            unlock();
/*2684*/            postWriteCleanup();
/*2684*/            return false;
/*2646*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*2683*/            unlock();
/*2684*/            postWriteCleanup();
/*2684*/            return false;
/*2683*/            obj;
/*2683*/            unlock();
/*2684*/            postWriteCleanup();
/*2684*/            throw obj;
                }

                Object replace(Object obj, int i, Object obj1)
                {
/*2689*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*2691*/            preWriteCleanup();
/*2693*/            atomicreferencearray = table;
/*2694*/            j = i & atomicreferencearray.length() - 1;
/*2695*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L3:
                    Object obj2;
/*2697*/            if(referenceentry1 == null)
/*2698*/                break MISSING_BLOCK_LABEL_235;
/*2698*/            obj2 = referenceentry1.getKey();
/*2699*/            if(referenceentry1.getHash() != i || obj2 == null || !map.keyEquivalence.equivalent(obj, obj2))
/*2703*/                break MISSING_BLOCK_LABEL_223;
                    ValueReference valuereference;
                    Object obj3;
/*2703*/            if((obj3 = (valuereference = referenceentry1.getValueReference()).get()) != null) goto _L2; else goto _L1
_L1:
/*2706*/            if(isCollected(valuereference))
                    {
/*2707*/                count;
/*2708*/                modCount++;
/*2709*/                enqueueNotification(obj2, i, obj3, MapMaker.RemovalCause.COLLECTED);
/*2710*/                i = removeFromChain(referenceentry, referenceentry1);
/*2711*/                obj = count - 1;
/*2712*/                atomicreferencearray.set(j, i);
/*2713*/                count = ((int) (obj));
                    }
/*2727*/            unlock();
/*2728*/            postWriteCleanup();
/*2728*/            return null;
_L2:
/*2718*/            modCount++;
/*2719*/            enqueueNotification(obj, i, obj3, MapMaker.RemovalCause.REPLACED);
/*2720*/            setValue(referenceentry1, obj1);
/*2721*/            obj = obj3;
/*2727*/            unlock();
/*2728*/            postWriteCleanup();
/*2728*/            return obj;
/*2697*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*2727*/            unlock();
/*2728*/            postWriteCleanup();
/*2728*/            return null;
/*2727*/            obj;
/*2727*/            unlock();
/*2728*/            postWriteCleanup();
/*2728*/            throw obj;
                }

                Object remove(Object obj, int i)
                {
/*2733*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*2735*/            preWriteCleanup();
/*2737*/            count;
/*2738*/            atomicreferencearray = table;
/*2739*/            j = i & atomicreferencearray.length() - 1;
/*2740*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L6:
                    Object obj1;
/*2742*/            if(referenceentry1 == null)
/*2743*/                break MISSING_BLOCK_LABEL_213;
/*2743*/            obj1 = referenceentry1.getKey();
/*2744*/            if(referenceentry1.getHash() != i || obj1 == null || !map.keyEquivalence.equivalent(obj, obj1))
/*2746*/                break MISSING_BLOCK_LABEL_201;
                    Object obj2;
/*2746*/            if((obj2 = ((ValueReference) (obj = referenceentry1.getValueReference())).get()) == null) goto _L2; else goto _L1
_L1:
/*2751*/            obj = MapMaker.RemovalCause.EXPLICIT;
                      goto _L3
_L2:
/*2752*/            if(!isCollected(((ValueReference) (obj)))) goto _L5; else goto _L4
_L4:
/*2753*/            obj = MapMaker.RemovalCause.COLLECTED;
                      goto _L3
_L5:
/*2770*/            unlock();
/*2771*/            postWriteCleanup();
/*2771*/            return null;
_L3:
/*2758*/            modCount++;
/*2759*/            enqueueNotification(obj1, i, obj2, ((MapMaker.RemovalCause) (obj)));
/*2760*/            i = removeFromChain(referenceentry, referenceentry1);
/*2761*/            obj = count - 1;
/*2762*/            atomicreferencearray.set(j, i);
/*2763*/            count = ((int) (obj));
/*2764*/            obj = obj2;
/*2770*/            unlock();
/*2771*/            postWriteCleanup();
/*2771*/            return obj;
/*2742*/            referenceentry1 = referenceentry1.getNext();
                      goto _L6
/*2770*/            unlock();
/*2771*/            postWriteCleanup();
/*2771*/            return null;
/*2770*/            obj;
/*2770*/            unlock();
/*2771*/            postWriteCleanup();
/*2771*/            throw obj;
                }

                boolean remove(Object obj, int i, Object obj1)
                {
/*2776*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*2778*/            preWriteCleanup();
/*2780*/            count;
/*2781*/            atomicreferencearray = table;
/*2782*/            j = i & atomicreferencearray.length() - 1;
/*2783*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L6:
                    Object obj2;
                    Object obj3;
/*2785*/            if(referenceentry1 == null)
/*2786*/                break MISSING_BLOCK_LABEL_239;
/*2786*/            obj2 = referenceentry1.getKey();
/*2787*/            if(referenceentry1.getHash() != i || obj2 == null || !map.keyEquivalence.equivalent(obj, obj2))
/*2789*/                break MISSING_BLOCK_LABEL_227;
/*2789*/            obj3 = ((ValueReference) (obj = referenceentry1.getValueReference())).get();
/*2793*/            if(!map.valueEquivalence.equivalent(obj1, obj3)) goto _L2; else goto _L1
_L1:
/*2794*/            obj1 = MapMaker.RemovalCause.EXPLICIT;
                      goto _L3
_L2:
/*2795*/            if(!isCollected(((ValueReference) (obj)))) goto _L5; else goto _L4
_L4:
/*2796*/            obj1 = MapMaker.RemovalCause.COLLECTED;
                      goto _L3
_L5:
/*2813*/            unlock();
/*2814*/            postWriteCleanup();
/*2814*/            return false;
_L3:
/*2801*/            modCount++;
/*2802*/            enqueueNotification(obj2, i, obj3, ((MapMaker.RemovalCause) (obj1)));
/*2803*/            i = removeFromChain(referenceentry, referenceentry1);
/*2804*/            obj = count - 1;
/*2805*/            atomicreferencearray.set(j, i);
/*2806*/            count = ((int) (obj));
/*2807*/            obj = obj1 != MapMaker.RemovalCause.EXPLICIT ? 0 : 1;
/*2813*/            unlock();
/*2814*/            postWriteCleanup();
/*2814*/            return ((boolean) (obj));
/*2785*/            referenceentry1 = referenceentry1.getNext();
                      goto _L6
/*2813*/            unlock();
/*2814*/            postWriteCleanup();
/*2814*/            return false;
/*2813*/            obj;
/*2813*/            unlock();
/*2814*/            postWriteCleanup();
/*2814*/            throw obj;
                }

                void clear()
                {
/*2819*/            if(count == 0)
/*2820*/                break MISSING_BLOCK_LABEL_177;
/*2820*/            lock();
/*2822*/            AtomicReferenceArray atomicreferencearray = table;
/*2823*/            if(map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE)
                    {
/*2824*/                for(int i = 0; i < atomicreferencearray.length(); i++)
                        {
/*2825*/                    for(ReferenceEntry referenceentry = (ReferenceEntry)atomicreferencearray.get(i); referenceentry != null; referenceentry = referenceentry.getNext())
/*2827*/                        if(!referenceentry.getValueReference().isComputingReference())
/*2828*/                            enqueueNotification(referenceentry, MapMaker.RemovalCause.EXPLICIT);

                        }

                    }
/*2833*/            for(int j = 0; j < atomicreferencearray.length(); j++)
/*2834*/                atomicreferencearray.set(j, null);

/*2836*/            clearReferenceQueues();
/*2837*/            evictionQueue.clear();
/*2838*/            expirationQueue.clear();
/*2839*/            readCount.set(0);
/*2841*/            modCount++;
/*2842*/            count = 0;
/*2844*/            unlock();
/*2845*/            postWriteCleanup();
/*2846*/            return;
                    Exception exception;
/*2844*/            exception;
/*2844*/            unlock();
/*2845*/            postWriteCleanup();
/*2845*/            throw exception;
                }

                ReferenceEntry removeFromChain(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/*2864*/            evictionQueue.remove(referenceentry1);
/*2865*/            expirationQueue.remove(referenceentry1);
/*2867*/            int i = count;
/*2868*/            ReferenceEntry referenceentry2 = referenceentry1.getNext();
/*2869*/            for(referenceentry = referenceentry; referenceentry != referenceentry1; referenceentry = referenceentry.getNext())
                    {
                        ReferenceEntry referenceentry3;
/*2870*/                if((referenceentry3 = copyEntry(referenceentry, referenceentry2)) != null)
                        {
/*2872*/                    referenceentry2 = referenceentry3;
                        } else
                        {
/*2874*/                    removeCollectedEntry(referenceentry);
/*2875*/                    i--;
                        }
                    }

/*2878*/            count = i;
/*2879*/            return referenceentry2;
                }

                void removeCollectedEntry(ReferenceEntry referenceentry)
                {
/*2883*/            enqueueNotification(referenceentry, MapMaker.RemovalCause.COLLECTED);
/*2884*/            evictionQueue.remove(referenceentry);
/*2885*/            expirationQueue.remove(referenceentry);
                }

                boolean reclaimKey(ReferenceEntry referenceentry, int i)
                {
/*2892*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry1;
                    ReferenceEntry referenceentry2;
/*2894*/            count;
/*2895*/            atomicreferencearray = table;
/*2896*/            j = i & atomicreferencearray.length() - 1;
/*2897*/            referenceentry2 = referenceentry1 = (ReferenceEntry)atomicreferencearray.get(j);
_L1:
/*2899*/            if(referenceentry2 == null)
/*2900*/                break MISSING_BLOCK_LABEL_136;
/*2900*/            if(referenceentry2 != referenceentry)
/*2901*/                break MISSING_BLOCK_LABEL_124;
/*2901*/            modCount++;
/*2902*/            enqueueNotification(referenceentry2.getKey(), i, referenceentry2.getValueReference().get(), MapMaker.RemovalCause.COLLECTED);
/*2904*/            i = removeFromChain(referenceentry1, referenceentry2);
/*2905*/            referenceentry = count - 1;
/*2906*/            atomicreferencearray.set(j, i);
/*2907*/            count = referenceentry;
/*2914*/            unlock();
/*2915*/            postWriteCleanup();
/*2915*/            return true;
/*2899*/            referenceentry2 = referenceentry2.getNext();
                      goto _L1
/*2914*/            unlock();
/*2915*/            postWriteCleanup();
/*2915*/            return false;
/*2914*/            referenceentry;
/*2914*/            unlock();
/*2915*/            postWriteCleanup();
/*2915*/            throw referenceentry;
                }

                boolean reclaimValue(Object obj, int i, ValueReference valuereference)
                {
/*2923*/            lock();
                    AtomicReferenceArray atomicreferencearray;
                    int j;
                    ReferenceEntry referenceentry;
                    ReferenceEntry referenceentry1;
/*2925*/            count;
/*2926*/            atomicreferencearray = table;
/*2927*/            j = i & atomicreferencearray.length() - 1;
/*2928*/            referenceentry1 = referenceentry = (ReferenceEntry)atomicreferencearray.get(j);
_L3:
/*2930*/            if(referenceentry1 == null)
/*2931*/                break MISSING_BLOCK_LABEL_201;
/*2931*/            Object obj1 = referenceentry1.getKey();
/*2932*/            if(referenceentry1.getHash() != i || obj1 == null || !map.keyEquivalence.equivalent(obj, obj1))
/*2934*/                break MISSING_BLOCK_LABEL_189;
                    ValueReference valuereference1;
/*2934*/            if((valuereference1 = referenceentry1.getValueReference()) != valuereference) goto _L2; else goto _L1
_L1:
/*2936*/            modCount++;
/*2937*/            enqueueNotification(obj, i, valuereference.get(), MapMaker.RemovalCause.COLLECTED);
/*2938*/            i = removeFromChain(referenceentry, referenceentry1);
/*2939*/            obj = count - 1;
/*2940*/            atomicreferencearray.set(j, i);
/*2941*/            count = ((int) (obj));
/*2950*/            unlock();
/*2951*/            if(!isHeldByCurrentThread())
/*2952*/                postWriteCleanup();
/*2952*/            return true;
_L2:
/*2950*/            unlock();
/*2951*/            if(!isHeldByCurrentThread())
/*2952*/                postWriteCleanup();
/*2952*/            return false;
/*2930*/            referenceentry1 = referenceentry1.getNext();
                      goto _L3
/*2950*/            unlock();
/*2951*/            if(!isHeldByCurrentThread())
/*2952*/                postWriteCleanup();
/*2952*/            return false;
/*2950*/            obj;
/*2950*/            unlock();
/*2951*/            if(!isHeldByCurrentThread())
/*2952*/                postWriteCleanup();
/*2952*/            throw obj;
                }

                boolean removeEntry(ReferenceEntry referenceentry, int i, MapMaker.RemovalCause removalcause)
                {
/*2990*/            int _tmp = count;
/*2991*/            AtomicReferenceArray atomicreferencearray = table;
/*2992*/            int j = i & atomicreferencearray.length() - 1;
                    ReferenceEntry referenceentry1;
/*2993*/            for(ReferenceEntry referenceentry2 = referenceentry1 = (ReferenceEntry)atomicreferencearray.get(j); referenceentry2 != null; referenceentry2 = referenceentry2.getNext())
/*2996*/                if(referenceentry2 == referenceentry)
                        {
/*2997*/                    modCount++;
/*2998*/                    enqueueNotification(referenceentry2.getKey(), i, referenceentry2.getValueReference().get(), removalcause);
/*2999*/                    i = removeFromChain(referenceentry1, referenceentry2);
/*3000*/                    referenceentry = count - 1;
/*3001*/                    atomicreferencearray.set(j, i);
/*3002*/                    count = referenceentry;
/*3003*/                    return true;
                        }

/*3007*/            return false;
                }

                boolean isCollected(ValueReference valuereference)
                {
/*3015*/            if(valuereference.isComputingReference())
/*3016*/                return false;
/*3018*/            return valuereference.get() == null;
                }

                Object getLiveValue(ReferenceEntry referenceentry)
                {
/*3026*/            if(referenceentry.getKey() == null)
                    {
/*3027*/                tryDrainReferenceQueues();
/*3028*/                return null;
                    }
                    Object obj;
/*3030*/            if((obj = referenceentry.getValueReference().get()) == null)
                    {
/*3032*/                tryDrainReferenceQueues();
/*3033*/                return null;
                    }
/*3036*/            if(map.expires() && map.isExpired(referenceentry))
                    {
/*3037*/                tryExpireEntries();
/*3038*/                return null;
                    } else
                    {
/*3040*/                return obj;
                    }
                }

                void postReadCleanup()
                {
/*3049*/            if((readCount.incrementAndGet() & 0x3f) == 0)
/*3050*/                runCleanup();
                }

                void preWriteCleanup()
                {
/*3062*/            runLockedCleanup();
                }

                void postWriteCleanup()
                {
/*3069*/            runUnlockedCleanup();
                }

                void runCleanup()
                {
/*3073*/            runLockedCleanup();
/*3074*/            runUnlockedCleanup();
                }

                void runLockedCleanup()
                {
/*3078*/            if(!tryLock())
/*3080*/                break MISSING_BLOCK_LABEL_35;
/*3080*/            drainReferenceQueues();
/*3081*/            expireEntries();
/*3082*/            readCount.set(0);
/*3084*/            unlock();
/*3085*/            return;
                    Exception exception;
/*3084*/            exception;
/*3084*/            unlock();
/*3084*/            throw exception;
                }

                void runUnlockedCleanup()
                {
/*3091*/            if(!isHeldByCurrentThread())
/*3092*/                map.processPendingNotifications();
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

                Segment(MapMakerInternalMap mapmakerinternalmap, int i, int j)
                {
/*2091*/            map = mapmakerinternalmap;
/*2092*/            maxSegmentSize = j;
/*2093*/            initTable(newEntryArray(i));
/*2095*/            keyReferenceQueue = mapmakerinternalmap.usesKeyReferences() ? new ReferenceQueue() : null;
/*2098*/            valueReferenceQueue = mapmakerinternalmap.usesValueReferences() ? new ReferenceQueue() : null;
/*2101*/            recencyQueue = ((Queue) (!mapmakerinternalmap.evictsBySize() && !mapmakerinternalmap.expiresAfterAccess() ? MapMakerInternalMap.discardingQueue() : ((Queue) (new ConcurrentLinkedQueue()))));
/*2105*/            evictionQueue = ((Queue) (mapmakerinternalmap.evictsBySize() ? ((Queue) (new EvictionQueue())) : MapMakerInternalMap.discardingQueue()));
/*2109*/            expirationQueue = ((Queue) (mapmakerinternalmap.expires() ? ((Queue) (new ExpirationQueue())) : MapMakerInternalMap.discardingQueue()));
                }
    }

    static final class StrongValueReference
        implements ValueReference
    {

                public final Object get()
                {
/*1773*/            return referent;
                }

                public final ReferenceEntry getEntry()
                {
/*1778*/            return null;
                }

                public final ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1784*/            return this;
                }

                public final boolean isComputingReference()
                {
/*1789*/            return false;
                }

                public final void clear(ValueReference valuereference)
                {
                }

                final Object referent;

                StrongValueReference(Object obj)
                {
/*1768*/            referent = obj;
                }
    }

    static final class SoftValueReference extends SoftReference
        implements ValueReference
    {

                public final ReferenceEntry getEntry()
                {
/*1736*/            return entry;
                }

                public final void clear(ValueReference valuereference)
                {
/*1741*/            clear();
                }

                public final ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1747*/            return new SoftValueReference(referencequeue, obj, referenceentry);
                }

                public final boolean isComputingReference()
                {
/*1752*/            return false;
                }

                final ReferenceEntry entry;

                SoftValueReference(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1730*/            super(obj, referencequeue);
/*1731*/            entry = referenceentry;
                }
    }

    static final class WeakValueReference extends WeakReference
        implements ValueReference
    {

                public final ReferenceEntry getEntry()
                {
/*1697*/            return entry;
                }

                public final void clear(ValueReference valuereference)
                {
/*1702*/            clear();
                }

                public final ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1708*/            return new WeakValueReference(referencequeue, obj, referenceentry);
                }

                public final boolean isComputingReference()
                {
/*1713*/            return false;
                }

                final ReferenceEntry entry;

                WeakValueReference(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/*1691*/            super(obj, referencequeue);
/*1692*/            entry = referenceentry;
                }
    }

    static final class WeakExpirableEvictableEntry extends WeakEntry
        implements ReferenceEntry
    {

                public final long getExpirationTime()
                {
/*1620*/            return time;
                }

                public final void setExpirationTime(long l)
                {
/*1625*/            time = l;
                }

                public final ReferenceEntry getNextExpirable()
                {
/*1633*/            return nextExpirable;
                }

                public final void setNextExpirable(ReferenceEntry referenceentry)
                {
/*1638*/            nextExpirable = referenceentry;
                }

                public final ReferenceEntry getPreviousExpirable()
                {
/*1646*/            return previousExpirable;
                }

                public final void setPreviousExpirable(ReferenceEntry referenceentry)
                {
/*1651*/            previousExpirable = referenceentry;
                }

                public final ReferenceEntry getNextEvictable()
                {
/*1661*/            return nextEvictable;
                }

                public final void setNextEvictable(ReferenceEntry referenceentry)
                {
/*1666*/            nextEvictable = referenceentry;
                }

                public final ReferenceEntry getPreviousEvictable()
                {
/*1674*/            return previousEvictable;
                }

                public final void setPreviousEvictable(ReferenceEntry referenceentry)
                {
/*1679*/            previousEvictable = referenceentry;
                }

                volatile long time;
                ReferenceEntry nextExpirable;
                ReferenceEntry previousExpirable;
                ReferenceEntry nextEvictable;
                ReferenceEntry previousEvictable;

                WeakExpirableEvictableEntry(ReferenceQueue referencequeue, Object obj, int i, ReferenceEntry referenceentry)
                {
/*1611*/            super(referencequeue, obj, i, referenceentry);
/*1616*/            time = 0x7fffffffffffffffL;
/*1629*/            nextExpirable = MapMakerInternalMap.nullEntry();
/*1642*/            previousExpirable = MapMakerInternalMap.nullEntry();
/*1657*/            nextEvictable = MapMakerInternalMap.nullEntry();
/*1670*/            previousEvictable = MapMakerInternalMap.nullEntry();
                }
    }

    static final class WeakEvictableEntry extends WeakEntry
        implements ReferenceEntry
    {

                public final ReferenceEntry getNextEvictable()
                {
/*1585*/            return nextEvictable;
                }

                public final void setNextEvictable(ReferenceEntry referenceentry)
                {
/*1590*/            nextEvictable = referenceentry;
                }

                public final ReferenceEntry getPreviousEvictable()
                {
/*1598*/            return previousEvictable;
                }

                public final void setPreviousEvictable(ReferenceEntry referenceentry)
                {
/*1603*/            previousEvictable = referenceentry;
                }

                ReferenceEntry nextEvictable;
                ReferenceEntry previousEvictable;

                WeakEvictableEntry(ReferenceQueue referencequeue, Object obj, int i, ReferenceEntry referenceentry)
                {
/*1575*/            super(referencequeue, obj, i, referenceentry);
/*1581*/            nextEvictable = MapMakerInternalMap.nullEntry();
/*1594*/            previousEvictable = MapMakerInternalMap.nullEntry();
                }
    }

    static final class WeakExpirableEntry extends WeakEntry
        implements ReferenceEntry
    {

                public final long getExpirationTime()
                {
/*1536*/            return time;
                }

                public final void setExpirationTime(long l)
                {
/*1541*/            time = l;
                }

                public final ReferenceEntry getNextExpirable()
                {
/*1549*/            return nextExpirable;
                }

                public final void setNextExpirable(ReferenceEntry referenceentry)
                {
/*1554*/            nextExpirable = referenceentry;
                }

                public final ReferenceEntry getPreviousExpirable()
                {
/*1562*/            return previousExpirable;
                }

                public final void setPreviousExpirable(ReferenceEntry referenceentry)
                {
/*1567*/            previousExpirable = referenceentry;
                }

                volatile long time;
                ReferenceEntry nextExpirable;
                ReferenceEntry previousExpirable;

                WeakExpirableEntry(ReferenceQueue referencequeue, Object obj, int i, ReferenceEntry referenceentry)
                {
/*1527*/            super(referencequeue, obj, i, referenceentry);
/*1532*/            time = 0x7fffffffffffffffL;
/*1545*/            nextExpirable = MapMakerInternalMap.nullEntry();
/*1558*/            previousExpirable = MapMakerInternalMap.nullEntry();
                }
    }

    static class WeakEntry extends WeakReference
        implements ReferenceEntry
    {

                public Object getKey()
                {
/*1437*/            return get();
                }

                public long getExpirationTime()
                {
/*1444*/            throw new UnsupportedOperationException();
                }

                public void setExpirationTime(long l)
                {
/*1449*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextExpirable()
                {
/*1454*/            throw new UnsupportedOperationException();
                }

                public void setNextExpirable(ReferenceEntry referenceentry)
                {
/*1459*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousExpirable()
                {
/*1464*/            throw new UnsupportedOperationException();
                }

                public void setPreviousExpirable(ReferenceEntry referenceentry)
                {
/*1469*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextEvictable()
                {
/*1476*/            throw new UnsupportedOperationException();
                }

                public void setNextEvictable(ReferenceEntry referenceentry)
                {
/*1481*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousEvictable()
                {
/*1486*/            throw new UnsupportedOperationException();
                }

                public void setPreviousEvictable(ReferenceEntry referenceentry)
                {
/*1491*/            throw new UnsupportedOperationException();
                }

                public ValueReference getValueReference()
                {
/*1502*/            return valueReference;
                }

                public void setValueReference(ValueReference valuereference)
                {
/*1507*/            ValueReference valuereference1 = valueReference;
/*1508*/            valueReference = valuereference;
/*1509*/            valuereference1.clear(valuereference);
                }

                public int getHash()
                {
/*1514*/            return hash;
                }

                public ReferenceEntry getNext()
                {
/*1519*/            return next;
                }

                final int hash;
                final ReferenceEntry next;
                volatile ValueReference valueReference;

                WeakEntry(ReferenceQueue referencequeue, Object obj, int i, ReferenceEntry referenceentry)
                {
/*1430*/            super(obj, referencequeue);
/*1498*/            valueReference = MapMakerInternalMap.unset();
/*1431*/            hash = i;
/*1432*/            next = referenceentry;
                }
    }

    static final class StrongExpirableEvictableEntry extends StrongEntry
        implements ReferenceEntry
    {

                public final long getExpirationTime()
                {
/*1105*/            return time;
                }

                public final void setExpirationTime(long l)
                {
/*1110*/            time = l;
                }

                public final ReferenceEntry getNextExpirable()
                {
/*1118*/            return nextExpirable;
                }

                public final void setNextExpirable(ReferenceEntry referenceentry)
                {
/*1123*/            nextExpirable = referenceentry;
                }

                public final ReferenceEntry getPreviousExpirable()
                {
/*1131*/            return previousExpirable;
                }

                public final void setPreviousExpirable(ReferenceEntry referenceentry)
                {
/*1136*/            previousExpirable = referenceentry;
                }

                public final ReferenceEntry getNextEvictable()
                {
/*1146*/            return nextEvictable;
                }

                public final void setNextEvictable(ReferenceEntry referenceentry)
                {
/*1151*/            nextEvictable = referenceentry;
                }

                public final ReferenceEntry getPreviousEvictable()
                {
/*1159*/            return previousEvictable;
                }

                public final void setPreviousEvictable(ReferenceEntry referenceentry)
                {
/*1164*/            previousEvictable = referenceentry;
                }

                volatile long time;
                ReferenceEntry nextExpirable;
                ReferenceEntry previousExpirable;
                ReferenceEntry nextEvictable;
                ReferenceEntry previousEvictable;

                StrongExpirableEvictableEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/*1096*/            super(obj, i, referenceentry);
/*1101*/            time = 0x7fffffffffffffffL;
/*1114*/            nextExpirable = MapMakerInternalMap.nullEntry();
/*1127*/            previousExpirable = MapMakerInternalMap.nullEntry();
/*1142*/            nextEvictable = MapMakerInternalMap.nullEntry();
/*1155*/            previousEvictable = MapMakerInternalMap.nullEntry();
                }
    }

    static final class StrongEvictableEntry extends StrongEntry
        implements ReferenceEntry
    {

                public final ReferenceEntry getNextEvictable()
                {
/*1071*/            return nextEvictable;
                }

                public final void setNextEvictable(ReferenceEntry referenceentry)
                {
/*1076*/            nextEvictable = referenceentry;
                }

                public final ReferenceEntry getPreviousEvictable()
                {
/*1084*/            return previousEvictable;
                }

                public final void setPreviousEvictable(ReferenceEntry referenceentry)
                {
/*1089*/            previousEvictable = referenceentry;
                }

                ReferenceEntry nextEvictable;
                ReferenceEntry previousEvictable;

                StrongEvictableEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/*1061*/            super(obj, i, referenceentry);
/*1067*/            nextEvictable = MapMakerInternalMap.nullEntry();
/*1080*/            previousEvictable = MapMakerInternalMap.nullEntry();
                }
    }

    static final class StrongExpirableEntry extends StrongEntry
        implements ReferenceEntry
    {

                public final long getExpirationTime()
                {
/*1023*/            return time;
                }

                public final void setExpirationTime(long l)
                {
/*1028*/            time = l;
                }

                public final ReferenceEntry getNextExpirable()
                {
/*1036*/            return nextExpirable;
                }

                public final void setNextExpirable(ReferenceEntry referenceentry)
                {
/*1041*/            nextExpirable = referenceentry;
                }

                public final ReferenceEntry getPreviousExpirable()
                {
/*1049*/            return previousExpirable;
                }

                public final void setPreviousExpirable(ReferenceEntry referenceentry)
                {
/*1054*/            previousExpirable = referenceentry;
                }

                volatile long time;
                ReferenceEntry nextExpirable;
                ReferenceEntry previousExpirable;

                StrongExpirableEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/*1014*/            super(obj, i, referenceentry);
/*1019*/            time = 0x7fffffffffffffffL;
/*1032*/            nextExpirable = MapMakerInternalMap.nullEntry();
/*1045*/            previousExpirable = MapMakerInternalMap.nullEntry();
                }
    }

    static class StrongEntry
        implements ReferenceEntry
    {

                public Object getKey()
                {
/* 925*/            return key;
                }

                public long getExpirationTime()
                {
/* 932*/            throw new UnsupportedOperationException();
                }

                public void setExpirationTime(long l)
                {
/* 937*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextExpirable()
                {
/* 942*/            throw new UnsupportedOperationException();
                }

                public void setNextExpirable(ReferenceEntry referenceentry)
                {
/* 947*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousExpirable()
                {
/* 952*/            throw new UnsupportedOperationException();
                }

                public void setPreviousExpirable(ReferenceEntry referenceentry)
                {
/* 957*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextEvictable()
                {
/* 964*/            throw new UnsupportedOperationException();
                }

                public void setNextEvictable(ReferenceEntry referenceentry)
                {
/* 969*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousEvictable()
                {
/* 974*/            throw new UnsupportedOperationException();
                }

                public void setPreviousEvictable(ReferenceEntry referenceentry)
                {
/* 979*/            throw new UnsupportedOperationException();
                }

                public ValueReference getValueReference()
                {
/* 990*/            return valueReference;
                }

                public void setValueReference(ValueReference valuereference)
                {
/* 995*/            ValueReference valuereference1 = valueReference;
/* 996*/            valueReference = valuereference;
/* 997*/            valuereference1.clear(valuereference);
                }

                public int getHash()
                {
/*1002*/            return hash;
                }

                public ReferenceEntry getNext()
                {
/*1007*/            return next;
                }

                final Object key;
                final int hash;
                final ReferenceEntry next;
                volatile ValueReference valueReference;

                StrongEntry(Object obj, int i, ReferenceEntry referenceentry)
                {
/* 986*/            valueReference = MapMakerInternalMap.unset();
/* 918*/            key = obj;
/* 919*/            hash = i;
/* 920*/            next = referenceentry;
                }
    }

    static abstract class AbstractReferenceEntry
        implements ReferenceEntry
    {

                public ValueReference getValueReference()
                {
/* 789*/            throw new UnsupportedOperationException();
                }

                public void setValueReference(ValueReference valuereference)
                {
/* 794*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNext()
                {
/* 799*/            throw new UnsupportedOperationException();
                }

                public int getHash()
                {
/* 804*/            throw new UnsupportedOperationException();
                }

                public Object getKey()
                {
/* 809*/            throw new UnsupportedOperationException();
                }

                public long getExpirationTime()
                {
/* 814*/            throw new UnsupportedOperationException();
                }

                public void setExpirationTime(long l)
                {
/* 819*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextExpirable()
                {
/* 824*/            throw new UnsupportedOperationException();
                }

                public void setNextExpirable(ReferenceEntry referenceentry)
                {
/* 829*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousExpirable()
                {
/* 834*/            throw new UnsupportedOperationException();
                }

                public void setPreviousExpirable(ReferenceEntry referenceentry)
                {
/* 839*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getNextEvictable()
                {
/* 844*/            throw new UnsupportedOperationException();
                }

                public void setNextEvictable(ReferenceEntry referenceentry)
                {
/* 849*/            throw new UnsupportedOperationException();
                }

                public ReferenceEntry getPreviousEvictable()
                {
/* 854*/            throw new UnsupportedOperationException();
                }

                public void setPreviousEvictable(ReferenceEntry referenceentry)
                {
/* 859*/            throw new UnsupportedOperationException();
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
/* 724*/            return null;
                }

                public final void setValueReference(ValueReference valuereference)
                {
                }

                public final ReferenceEntry getNext()
                {
/* 732*/            return null;
                }

                public final int getHash()
                {
/* 737*/            return 0;
                }

                public final Object getKey()
                {
/* 742*/            return null;
                }

                public final long getExpirationTime()
                {
/* 747*/            return 0L;
                }

                public final void setExpirationTime(long l)
                {
                }

                public final ReferenceEntry getNextExpirable()
                {
/* 755*/            return this;
                }

                public final void setNextExpirable(ReferenceEntry referenceentry)
                {
                }

                public final ReferenceEntry getPreviousExpirable()
                {
/* 763*/            return this;
                }

                public final void setPreviousExpirable(ReferenceEntry referenceentry)
                {
                }

                public final ReferenceEntry getNextEvictable()
                {
/* 771*/            return this;
                }

                public final void setNextEvictable(ReferenceEntry referenceentry)
                {
                }

                public final ReferenceEntry getPreviousEvictable()
                {
/* 779*/            return this;
                }

                public final void setPreviousEvictable(ReferenceEntry referenceentry)
                {
                }

                public static final NullEntry INSTANCE;
                private static final NullEntry $VALUES[];

                static 
                {
/* 720*/            INSTANCE = new NullEntry("INSTANCE", 0);
/* 719*/            $VALUES = (new NullEntry[] {
/* 719*/                INSTANCE
                    });
                }

                private NullEntry(String s, int i)
                {
/* 719*/            super(s, i);
                }
    }

    static interface ReferenceEntry
    {

        public abstract ValueReference getValueReference();

        public abstract void setValueReference(ValueReference valuereference);

        public abstract ReferenceEntry getNext();

        public abstract int getHash();

        public abstract Object getKey();

        public abstract long getExpirationTime();

        public abstract void setExpirationTime(long l);

        public abstract ReferenceEntry getNextExpirable();

        public abstract void setNextExpirable(ReferenceEntry referenceentry);

        public abstract ReferenceEntry getPreviousExpirable();

        public abstract void setPreviousExpirable(ReferenceEntry referenceentry);

        public abstract ReferenceEntry getNextEvictable();

        public abstract void setNextEvictable(ReferenceEntry referenceentry);

        public abstract ReferenceEntry getPreviousEvictable();

        public abstract void setPreviousEvictable(ReferenceEntry referenceentry);
    }

    static interface ValueReference
    {

        public abstract Object get();

        public abstract ReferenceEntry getEntry();

        public abstract ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry);

        public abstract void clear(ValueReference valuereference);

        public abstract boolean isComputingReference();
    }

    static abstract class EntryFactory extends Enum
    {

                static EntryFactory getFactory(Strength strength, boolean flag, boolean flag1)
                {
/* 478*/            flag = (flag ? 1 : 0) | (flag1 ? 2 : 0);
/* 479*/            return factories[strength.ordinal()][flag];
                }

                abstract ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry);

                ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/* 502*/            return newEntry(segment, referenceentry.getKey(), referenceentry.getHash(), referenceentry1);
                }

                void copyExpirableEntry(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/* 509*/            referenceentry1.setExpirationTime(referenceentry.getExpirationTime());
/* 511*/            MapMakerInternalMap.connectExpirables(referenceentry.getPreviousExpirable(), referenceentry1);
/* 512*/            MapMakerInternalMap.connectExpirables(referenceentry1, referenceentry.getNextExpirable());
/* 514*/            MapMakerInternalMap.nullifyExpirable(referenceentry);
                }

                void copyEvictableEntry(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                {
/* 521*/            MapMakerInternalMap.connectEvictables(referenceentry.getPreviousEvictable(), referenceentry1);
/* 522*/            MapMakerInternalMap.connectEvictables(referenceentry1, referenceentry.getNextEvictable());
/* 524*/            MapMakerInternalMap.nullifyEvictable(referenceentry);
                }

                public static final EntryFactory STRONG;
                public static final EntryFactory STRONG_EXPIRABLE;
                public static final EntryFactory STRONG_EVICTABLE;
                public static final EntryFactory STRONG_EXPIRABLE_EVICTABLE;
                public static final EntryFactory WEAK;
                public static final EntryFactory WEAK_EXPIRABLE;
                public static final EntryFactory WEAK_EVICTABLE;
                public static final EntryFactory WEAK_EXPIRABLE_EVICTABLE;
                static final EntryFactory factories[][];
                private static final EntryFactory $VALUES[];

                static 
                {
/* 352*/            STRONG = new EntryFactory("STRONG", 0) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 356*/                    return new StrongEntry(obj, i, referenceentry);
                        }

            };
/* 359*/            STRONG_EXPIRABLE = new EntryFactory("STRONG_EXPIRABLE", 1) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 363*/                    return new StrongExpirableEntry(obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 369*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 370*/                    copyExpirableEntry(referenceentry, segment);
/* 371*/                    return segment;
                        }

            };
/* 374*/            STRONG_EVICTABLE = new EntryFactory("STRONG_EVICTABLE", 2) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 378*/                    return new StrongEvictableEntry(obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 384*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 385*/                    copyEvictableEntry(referenceentry, segment);
/* 386*/                    return segment;
                        }

            };
/* 389*/            STRONG_EXPIRABLE_EVICTABLE = new EntryFactory("STRONG_EXPIRABLE_EVICTABLE", 3) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 393*/                    return new StrongExpirableEvictableEntry(obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 399*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 400*/                    copyExpirableEntry(referenceentry, segment);
/* 401*/                    copyEvictableEntry(referenceentry, segment);
/* 402*/                    return segment;
                        }

            };
/* 406*/            WEAK = new EntryFactory("WEAK", 4) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 410*/                    return new WeakEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                        }

            };
/* 413*/            WEAK_EXPIRABLE = new EntryFactory("WEAK_EXPIRABLE", 5) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 417*/                    return new WeakExpirableEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 423*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 424*/                    copyExpirableEntry(referenceentry, segment);
/* 425*/                    return segment;
                        }

            };
/* 428*/            WEAK_EVICTABLE = new EntryFactory("WEAK_EVICTABLE", 6) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 432*/                    return new WeakEvictableEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 438*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 439*/                    copyEvictableEntry(referenceentry, segment);
/* 440*/                    return segment;
                        }

            };
/* 443*/            WEAK_EXPIRABLE_EVICTABLE = new EntryFactory("WEAK_EXPIRABLE_EVICTABLE", 7) {

                        final ReferenceEntry newEntry(Segment segment, Object obj, int i, ReferenceEntry referenceentry)
                        {
/* 447*/                    return new WeakExpirableEvictableEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                        }

                        final ReferenceEntry copyEntry(Segment segment, ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
                        {
/* 453*/                    segment = copyEntry(segment, referenceentry, referenceentry1);
/* 454*/                    copyExpirableEntry(referenceentry, segment);
/* 455*/                    copyEvictableEntry(referenceentry, segment);
/* 456*/                    return segment;
                        }

            };
/* 351*/            $VALUES = (new EntryFactory[] {
/* 351*/                STRONG, STRONG_EXPIRABLE, STRONG_EVICTABLE, STRONG_EXPIRABLE_EVICTABLE, WEAK, WEAK_EXPIRABLE, WEAK_EVICTABLE, WEAK_EXPIRABLE_EVICTABLE
                    });
/* 470*/            factories = (new EntryFactory[][] {
/* 470*/                new EntryFactory[] {
/* 470*/                    STRONG, STRONG_EXPIRABLE, STRONG_EVICTABLE, STRONG_EXPIRABLE_EVICTABLE
                        }, new EntryFactory[0], new EntryFactory[] {
/* 470*/                    WEAK, WEAK_EXPIRABLE, WEAK_EVICTABLE, WEAK_EXPIRABLE_EVICTABLE
                        }
                    });
                }

                private EntryFactory(String s, int i)
                {
/* 351*/            super(s, i);
                }

    }

    static abstract class Strength extends Enum
    {

                abstract ValueReference referenceValue(Segment segment, ReferenceEntry referenceentry, Object obj);

                abstract Equivalence defaultEquivalence();

                public static final Strength STRONG;
                public static final Strength SOFT;
                public static final Strength WEAK;
                private static final Strength $VALUES[];

                static 
                {
/* 295*/            STRONG = new Strength("STRONG", 0) {

                        final ValueReference referenceValue(Segment segment, ReferenceEntry referenceentry, Object obj)
                        {
/* 299*/                    return new StrongValueReference(obj);
                        }

                        final Equivalence defaultEquivalence()
                        {
/* 304*/                    return Equivalence.equals();
                        }

            };
/* 308*/            SOFT = new Strength("SOFT", 1) {

                        final ValueReference referenceValue(Segment segment, ReferenceEntry referenceentry, Object obj)
                        {
/* 312*/                    return new SoftValueReference(segment.valueReferenceQueue, obj, referenceentry);
                        }

                        final Equivalence defaultEquivalence()
                        {
/* 317*/                    return Equivalence.identity();
                        }

            };
/* 321*/            WEAK = new Strength("WEAK", 2) {

                        final ValueReference referenceValue(Segment segment, ReferenceEntry referenceentry, Object obj)
                        {
/* 325*/                    return new WeakValueReference(segment.valueReferenceQueue, obj, referenceentry);
                        }

                        final Equivalence defaultEquivalence()
                        {
/* 330*/                    return Equivalence.identity();
                        }

            };
/* 289*/            $VALUES = (new Strength[] {
/* 289*/                STRONG, SOFT, WEAK
                    });
                }

                private Strength(String s, int i)
                {
/* 289*/            super(s, i);
                }

    }


            MapMakerInternalMap(MapMaker mapmaker)
            {
/* 196*/        concurrencyLevel = Math.min(mapmaker.getConcurrencyLevel(), 0x10000);
/* 198*/        keyStrength = mapmaker.getKeyStrength();
/* 199*/        valueStrength = mapmaker.getValueStrength();
/* 201*/        keyEquivalence = mapmaker.getKeyEquivalence();
/* 202*/        valueEquivalence = valueStrength.defaultEquivalence();
/* 204*/        maximumSize = mapmaker.maximumSize;
/* 205*/        expireAfterAccessNanos = mapmaker.getExpireAfterAccessNanos();
/* 206*/        expireAfterWriteNanos = mapmaker.getExpireAfterWriteNanos();
/* 208*/        entryFactory = EntryFactory.getFactory(keyStrength, expires(), evictsBySize());
/* 209*/        ticker = mapmaker.getTicker();
/* 211*/        removalListener = mapmaker.getRemovalListener();
/* 212*/        removalNotificationQueue = ((Queue) (removalListener != GenericMapMaker.NullListener.INSTANCE ? ((Queue) (new ConcurrentLinkedQueue())) : discardingQueue()));
/* 216*/        mapmaker = Math.min(mapmaker.getInitialCapacity(), 0x40000000);
/* 217*/        if(evictsBySize())
/* 218*/            mapmaker = Math.min(mapmaker, maximumSize);
/* 224*/        int i = 0;
                int l;
/* 225*/        for(l = 1; l < concurrencyLevel && (!evictsBySize() || l << 1 <= maximumSize); l <<= 1)
/* 228*/            i++;

/* 231*/        segmentShift = 32 - i;
/* 232*/        segmentMask = l - 1;
/* 234*/        segments = newSegmentArray(l);
/* 236*/        if((i = mapmaker / l) * l < mapmaker)
/* 238*/            i++;
/* 241*/        for(mapmaker = 1; mapmaker < i; mapmaker <<= 1);
/* 246*/        if(evictsBySize())
                {
/* 248*/            int j = maximumSize / l + 1;
/* 249*/            l = maximumSize % l;
/* 250*/            for(int i1 = 0; i1 < segments.length; i1++)
                    {
/* 251*/                if(i1 == l)
/* 252*/                    j--;
/* 254*/                segments[i1] = createSegment(mapmaker, j);
                    }

/* 257*/            return;
                }
/* 258*/        for(int k = 0; k < segments.length; k++)
/* 259*/            segments[k] = createSegment(mapmaker, -1);

            }

            boolean evictsBySize()
            {
/* 266*/        return maximumSize != -1;
            }

            boolean expires()
            {
/* 270*/        return expiresAfterWrite() || expiresAfterAccess();
            }

            boolean expiresAfterWrite()
            {
/* 274*/        return expireAfterWriteNanos > 0L;
            }

            boolean expiresAfterAccess()
            {
/* 278*/        return expireAfterAccessNanos > 0L;
            }

            boolean usesKeyReferences()
            {
/* 282*/        return keyStrength != Strength.STRONG;
            }

            boolean usesValueReferences()
            {
/* 286*/        return valueStrength != Strength.STRONG;
            }

            static ValueReference unset()
            {
/* 614*/        return UNSET;
            }

            static ReferenceEntry nullEntry()
            {
/* 865*/        return NullEntry.INSTANCE;
            }

            static Queue discardingQueue()
            {
/* 900*/        return DISCARDING_QUEUE;
            }

            static int rehash(int i)
            {
/*1813*/        return (i = (i = (i = (i = (i += i << 15 ^ 0xffffcd7d) ^ i >>> 10) + (i << 3)) ^ i >>> 6) + ((i << 2) + (i << 14))) ^ i >>> 16;
            }

            int hash(Object obj)
            {
/*1851*/        return rehash(((int) (obj = keyEquivalence.hash(obj))));
            }

            void reclaimValue(ValueReference valuereference)
            {
                ReferenceEntry referenceentry;
/*1856*/        int i = (referenceentry = valuereference.getEntry()).getHash();
/*1858*/        segmentFor(i).reclaimValue(referenceentry.getKey(), i, valuereference);
            }

            void reclaimKey(ReferenceEntry referenceentry)
            {
/*1862*/        int i = referenceentry.getHash();
/*1863*/        segmentFor(i).reclaimKey(referenceentry, i);
            }

            Segment segmentFor(int i)
            {
/*1883*/        return segments[i >>> segmentShift & segmentMask];
            }

            Segment createSegment(int i, int j)
            {
/*1887*/        return new Segment(this, i, j);
            }

            Object getLiveValue(ReferenceEntry referenceentry)
            {
/*1896*/        if(referenceentry.getKey() == null)
/*1897*/            return null;
                Object obj;
/*1899*/        if((obj = referenceentry.getValueReference().get()) == null)
/*1901*/            return null;
/*1904*/        if(expires() && isExpired(referenceentry))
/*1905*/            return null;
/*1907*/        else
/*1907*/            return obj;
            }

            boolean isExpired(ReferenceEntry referenceentry)
            {
/*1916*/        return isExpired(referenceentry, ticker.read());
            }

            boolean isExpired(ReferenceEntry referenceentry, long l)
            {
/*1924*/        return l - referenceentry.getExpirationTime() > 0L;
            }

            static void connectExpirables(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
            {
/*1929*/        referenceentry.setNextExpirable(referenceentry1);
/*1930*/        referenceentry1.setPreviousExpirable(referenceentry);
            }

            static void nullifyExpirable(ReferenceEntry referenceentry)
            {
/*1935*/        ReferenceEntry referenceentry1 = nullEntry();
/*1936*/        referenceentry.setNextExpirable(referenceentry1);
/*1937*/        referenceentry.setPreviousExpirable(referenceentry1);
            }

            void processPendingNotifications()
            {
                MapMaker.RemovalNotification removalnotification;
/*1949*/        while((removalnotification = (MapMaker.RemovalNotification)removalNotificationQueue.poll()) != null) 
/*1951*/            try
                    {
/*1951*/                removalListener.onRemoval(removalnotification);
                    }
/*1952*/            catch(Exception exception)
                    {
/*1953*/                logger.log(Level.WARNING, "Exception thrown by removal listener", exception);
                    }
            }

            static void connectEvictables(ReferenceEntry referenceentry, ReferenceEntry referenceentry1)
            {
/*1961*/        referenceentry.setNextEvictable(referenceentry1);
/*1962*/        referenceentry1.setPreviousEvictable(referenceentry);
            }

            static void nullifyEvictable(ReferenceEntry referenceentry)
            {
/*1967*/        ReferenceEntry referenceentry1 = nullEntry();
/*1968*/        referenceentry.setNextEvictable(referenceentry1);
/*1969*/        referenceentry.setPreviousEvictable(referenceentry1);
            }

            final Segment[] newSegmentArray(int i)
            {
/*1974*/        return new Segment[i];
            }

            public boolean isEmpty()
            {
/*3395*/        long l = 0L;
/*3396*/        Segment asegment[] = segments;
/*3397*/        for(int i = 0; i < asegment.length; i++)
                {
/*3398*/            if(asegment[i].count != 0)
/*3399*/                return false;
/*3401*/            l += asegment[i].modCount;
                }

/*3404*/        if(l != 0L)
                {
/*3405*/            for(int j = 0; j < asegment.length; j++)
                    {
/*3406*/                if(asegment[j].count != 0)
/*3407*/                    return false;
/*3409*/                l -= asegment[j].modCount;
                    }

/*3411*/            if(l != 0L)
/*3412*/                return false;
                }
/*3415*/        return true;
            }

            public int size()
            {
/*3420*/        Segment asegment[] = segments;
/*3421*/        long l = 0L;
/*3422*/        for(int i = 0; i < asegment.length; i++)
/*3423*/            l += asegment[i].count;

/*3425*/        return Ints.saturatedCast(l);
            }

            public Object get(Object obj)
            {
/*3430*/        if(obj == null)
                {
/*3431*/            return null;
                } else
                {
/*3433*/            int i = hash(obj);
/*3434*/            return segmentFor(i).get(obj, i);
                }
            }

            public boolean containsKey(Object obj)
            {
/*3451*/        if(obj == null)
                {
/*3452*/            return false;
                } else
                {
/*3454*/            int i = hash(obj);
/*3455*/            return segmentFor(i).containsKey(obj, i);
                }
            }

            public boolean containsValue(Object obj)
            {
/*3460*/        if(obj == null)
/*3461*/            return false;
/*3469*/        Segment asegment[] = segments;
/*3470*/        long l = -1L;
/*3471*/        int i = 0;
/*3471*/        do
                {
/*3471*/            if(i >= 3)
/*3472*/                break;
/*3472*/            long l1 = 0L;
                    Segment asegment1[];
/*3473*/            int j = (asegment1 = asegment).length;
/*3473*/            for(int k = 0; k < j; k++)
                    {
                        Segment segment;
/*3473*/                int _tmp = (segment = asegment1[k]).count;
/*3478*/                AtomicReferenceArray atomicreferencearray = segment.table;
/*3479*/                for(int i1 = 0; i1 < atomicreferencearray.length(); i1++)
                        {
/*3480*/                    for(ReferenceEntry referenceentry = (ReferenceEntry)atomicreferencearray.get(i1); referenceentry != null; referenceentry = referenceentry.getNext())
                            {
                                Object obj1;
/*3481*/                        if((obj1 = segment.getLiveValue(referenceentry)) != null && valueEquivalence.equivalent(obj, obj1))
/*3483*/                            return true;
                            }

                        }

/*3487*/                l1 += segment.modCount;
                    }

/*3489*/            if(l1 == l)
/*3492*/                break;
/*3492*/            l = l1;
/*3471*/            i++;
                } while(true);
/*3494*/        return false;
            }

            public Object put(Object obj, Object obj1)
            {
/*3499*/        Preconditions.checkNotNull(obj);
/*3500*/        Preconditions.checkNotNull(obj1);
/*3501*/        int i = hash(obj);
/*3502*/        return segmentFor(i).put(obj, i, obj1, false);
            }

            public Object putIfAbsent(Object obj, Object obj1)
            {
/*3507*/        Preconditions.checkNotNull(obj);
/*3508*/        Preconditions.checkNotNull(obj1);
/*3509*/        int i = hash(obj);
/*3510*/        return segmentFor(i).put(obj, i, obj1, true);
            }

            public void putAll(Map map)
            {
                java.util.Map.Entry entry;
/*3515*/        for(map = map.entrySet().iterator(); map.hasNext(); put(entry.getKey(), entry.getValue()))
/*3515*/            entry = (java.util.Map.Entry)map.next();

            }

            public Object remove(Object obj)
            {
/*3522*/        if(obj == null)
                {
/*3523*/            return null;
                } else
                {
/*3525*/            int i = hash(obj);
/*3526*/            return segmentFor(i).remove(obj, i);
                }
            }

            public boolean remove(Object obj, Object obj1)
            {
/*3531*/        if(obj == null || obj1 == null)
                {
/*3532*/            return false;
                } else
                {
/*3534*/            int i = hash(obj);
/*3535*/            return segmentFor(i).remove(obj, i, obj1);
                }
            }

            public boolean replace(Object obj, Object obj1, Object obj2)
            {
/*3540*/        Preconditions.checkNotNull(obj);
/*3541*/        Preconditions.checkNotNull(obj2);
/*3542*/        if(obj1 == null)
                {
/*3543*/            return false;
                } else
                {
/*3545*/            int i = hash(obj);
/*3546*/            return segmentFor(i).replace(obj, i, obj1, obj2);
                }
            }

            public Object replace(Object obj, Object obj1)
            {
/*3551*/        Preconditions.checkNotNull(obj);
/*3552*/        Preconditions.checkNotNull(obj1);
/*3553*/        int i = hash(obj);
/*3554*/        return segmentFor(i).replace(obj, i, obj1);
            }

            public void clear()
            {
                Segment asegment[];
/*3559*/        int i = (asegment = segments).length;
/*3559*/        for(int j = 0; j < i; j++)
                {
                    Segment segment;
/*3559*/            (segment = asegment[j]).clear();
                }

            }

            public Set keySet()
            {
                Set set;
/*3568*/        if((set = keySet) != null)
/*3569*/            return set;
/*3569*/        else
/*3569*/            return keySet = new KeySet();
            }

            public Collection values()
            {
                Collection collection;
/*3576*/        if((collection = values) != null)
/*3577*/            return collection;
/*3577*/        else
/*3577*/            return values = new Values();
            }

            public Set entrySet()
            {
                Set set;
/*3584*/        if((set = entrySet) != null)
/*3585*/            return set;
/*3585*/        else
/*3585*/            return entrySet = new EntrySet();
            }

            private static final Logger logger = Logger.getLogger(jersey/repackaged/com/google/common/collect/MapMakerInternalMap.getName());
            final transient int segmentMask;
            final transient int segmentShift;
            final transient Segment segments[];
            final int concurrencyLevel;
            final Equivalence keyEquivalence;
            final Equivalence valueEquivalence;
            final Strength keyStrength;
            final Strength valueStrength;
            final int maximumSize;
            final long expireAfterAccessNanos;
            final long expireAfterWriteNanos;
            final Queue removalNotificationQueue;
            final MapMaker.RemovalListener removalListener;
            final transient EntryFactory entryFactory;
            final Ticker ticker;
            static final ValueReference UNSET = new ValueReference() {

                public final Object get()
                {
/* 581*/            return null;
                }

                public final ReferenceEntry getEntry()
                {
/* 586*/            return null;
                }

                public final ValueReference copyFor(ReferenceQueue referencequeue, Object obj, ReferenceEntry referenceentry)
                {
/* 592*/            return this;
                }

                public final boolean isComputingReference()
                {
/* 597*/            return false;
                }

                public final void clear(ValueReference valuereference)
                {
                }

    };
            static final Queue DISCARDING_QUEUE = new AbstractQueue() {

                public final boolean offer(Object obj)
                {
/* 871*/            return true;
                }

                public final Object peek()
                {
/* 876*/            return null;
                }

                public final Object poll()
                {
/* 881*/            return null;
                }

                public final int size()
                {
/* 886*/            return 0;
                }

                public final Iterator iterator()
                {
/* 891*/            return Iterators.emptyIterator();
                }

    };
            transient Set keySet;
            transient Collection values;
            transient Set entrySet;

}
