// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMaker.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            GenericMapMaker, MapMakerInternalMap, ImmutableEntry

public final class MapMaker extends GenericMapMaker
{
    static class NullConcurrentMap extends AbstractMap
        implements Serializable, ConcurrentMap
    {

                public boolean containsKey(Object obj)
                {
/* 767*/            return false;
                }

                public boolean containsValue(Object obj)
                {
/* 772*/            return false;
                }

                public Object get(Object obj)
                {
/* 777*/            return null;
                }

                void notifyRemoval(Object obj, Object obj1)
                {
/* 781*/            obj = new RemovalNotification(obj, obj1, removalCause);
/* 783*/            removalListener.onRemoval(((RemovalNotification) (obj)));
                }

                public Object put(Object obj, Object obj1)
                {
/* 788*/            Preconditions.checkNotNull(obj);
/* 789*/            Preconditions.checkNotNull(obj1);
/* 790*/            notifyRemoval(obj, obj1);
/* 791*/            return null;
                }

                public Object putIfAbsent(Object obj, Object obj1)
                {
/* 796*/            return put(obj, obj1);
                }

                public Object remove(Object obj)
                {
/* 801*/            return null;
                }

                public boolean remove(Object obj, Object obj1)
                {
/* 806*/            return false;
                }

                public Object replace(Object obj, Object obj1)
                {
/* 811*/            Preconditions.checkNotNull(obj);
/* 812*/            Preconditions.checkNotNull(obj1);
/* 813*/            return null;
                }

                public boolean replace(Object obj, Object obj1, Object obj2)
                {
/* 818*/            Preconditions.checkNotNull(obj);
/* 819*/            Preconditions.checkNotNull(obj2);
/* 820*/            return false;
                }

                public Set entrySet()
                {
/* 825*/            return Collections.emptySet();
                }

                private final RemovalListener removalListener;
                private final RemovalCause removalCause;

                NullConcurrentMap(MapMaker mapmaker)
                {
/* 759*/            removalListener = mapmaker.getRemovalListener();
/* 760*/            removalCause = mapmaker.nullRemovalCause;
                }
    }

    static abstract class RemovalCause extends Enum
    {

                public static final RemovalCause EXPLICIT;
                public static final RemovalCause REPLACED;
                public static final RemovalCause COLLECTED;
                public static final RemovalCause EXPIRED;
                public static final RemovalCause SIZE;
                private static final RemovalCause $VALUES[];

                static 
                {
/* 690*/            EXPLICIT = new RemovalCause("EXPLICIT", 0) {

            };
/* 703*/            REPLACED = new RemovalCause("REPLACED", 1) {

            };
/* 714*/            COLLECTED = new RemovalCause("COLLECTED", 2) {

            };
/* 725*/            EXPIRED = new RemovalCause("EXPIRED", 3) {

            };
/* 736*/            SIZE = new RemovalCause("SIZE", 4) {

            };
/* 685*/            $VALUES = (new RemovalCause[] {
/* 685*/                EXPLICIT, REPLACED, COLLECTED, EXPIRED, SIZE
                    });
                }

                private RemovalCause(String s, int i)
                {
/* 685*/            super(s, i);
                }

    }

    static final class RemovalNotification extends ImmutableEntry
    {

                private final RemovalCause cause;

                RemovalNotification(Object obj, Object obj1, RemovalCause removalcause)
                {
/* 662*/            super(obj, obj1);
/* 663*/            cause = removalcause;
                }
    }

    static interface RemovalListener
    {

        public abstract void onRemoval(RemovalNotification removalnotification);
    }


            public MapMaker()
            {
/* 116*/        initialCapacity = -1;
/* 117*/        concurrencyLevel = -1;
/* 118*/        maximumSize = -1;
/* 123*/        expireAfterWriteNanos = -1L;
/* 124*/        expireAfterAccessNanos = -1L;
            }

            final Equivalence getKeyEquivalence()
            {
/* 155*/        return (Equivalence)MoreObjects.firstNonNull(keyEquivalence, getKeyStrength().defaultEquivalence());
            }

            final int getInitialCapacity()
            {
/* 178*/        if(initialCapacity == -1)
/* 178*/            return 16;
/* 178*/        else
/* 178*/            return initialCapacity;
            }

            final int getConcurrencyLevel()
            {
/* 248*/        if(concurrencyLevel == -1)
/* 248*/            return 4;
/* 248*/        else
/* 248*/            return concurrencyLevel;
            }

            final MapMakerInternalMap.Strength getKeyStrength()
            {
/* 280*/        return (MapMakerInternalMap.Strength)MoreObjects.firstNonNull(keyStrength, MapMakerInternalMap.Strength.STRONG);
            }

            final MapMakerInternalMap.Strength getValueStrength()
            {
/* 349*/        return (MapMakerInternalMap.Strength)MoreObjects.firstNonNull(valueStrength, MapMakerInternalMap.Strength.STRONG);
            }

            final long getExpireAfterWriteNanos()
            {
/* 399*/        if(expireAfterWriteNanos == -1L)
/* 399*/            return 0L;
/* 399*/        else
/* 399*/            return expireAfterWriteNanos;
            }

            final long getExpireAfterAccessNanos()
            {
/* 442*/        if(expireAfterAccessNanos == -1L)
/* 442*/            return 0L;
/* 442*/        else
/* 442*/            return expireAfterAccessNanos;
            }

            final Ticker getTicker()
            {
/* 447*/        return (Ticker)MoreObjects.firstNonNull(ticker, Ticker.systemTicker());
            }

            public final ConcurrentMap makeMap()
            {
/* 506*/        if(!useCustomMap)
/* 507*/            return new ConcurrentHashMap(getInitialCapacity(), 0.75F, getConcurrencyLevel());
/* 509*/        else
/* 509*/            return (ConcurrentMap)(nullRemovalCause != null ? new NullConcurrentMap(this) : new MapMakerInternalMap(this));
            }

            public final String toString()
            {
/* 596*/        jersey.repackaged.com.google.common.base.MoreObjects.ToStringHelper tostringhelper = MoreObjects.toStringHelper(this);
/* 597*/        if(initialCapacity != -1)
/* 598*/            tostringhelper.add("initialCapacity", initialCapacity);
/* 600*/        if(concurrencyLevel != -1)
/* 601*/            tostringhelper.add("concurrencyLevel", concurrencyLevel);
/* 603*/        if(maximumSize != -1)
/* 604*/            tostringhelper.add("maximumSize", maximumSize);
                long l;
/* 606*/        if(expireAfterWriteNanos != -1L)
/* 607*/            tostringhelper.add("expireAfterWrite", (new StringBuilder(22)).append(l = expireAfterWriteNanos).append("ns").toString());
/* 609*/        if(expireAfterAccessNanos != -1L)
/* 610*/            tostringhelper.add("expireAfterAccess", (new StringBuilder(22)).append(l = expireAfterAccessNanos).append("ns").toString());
/* 612*/        if(keyStrength != null)
/* 613*/            tostringhelper.add("keyStrength", Ascii.toLowerCase(keyStrength.toString()));
/* 615*/        if(valueStrength != null)
/* 616*/            tostringhelper.add("valueStrength", Ascii.toLowerCase(valueStrength.toString()));
/* 618*/        if(keyEquivalence != null)
/* 619*/            tostringhelper.addValue("keyEquivalence");
/* 621*/        if(removalListener != null)
/* 622*/            tostringhelper.addValue("removalListener");
/* 624*/        return tostringhelper.toString();
            }

            boolean useCustomMap;
            int initialCapacity;
            int concurrencyLevel;
            int maximumSize;
            MapMakerInternalMap.Strength keyStrength;
            MapMakerInternalMap.Strength valueStrength;
            long expireAfterWriteNanos;
            long expireAfterAccessNanos;
            RemovalCause nullRemovalCause;
            Equivalence keyEquivalence;
            Ticker ticker;
}
