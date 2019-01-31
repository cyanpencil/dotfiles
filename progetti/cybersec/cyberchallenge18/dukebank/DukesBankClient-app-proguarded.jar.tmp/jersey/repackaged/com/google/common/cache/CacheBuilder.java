// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilder.java

package jersey.repackaged.com.google.common.cache;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            AbstractCache, CacheBuilderSpec, CacheStats, LocalCache, 
//            RemovalListener, Weigher, CacheLoader, LoadingCache, 
//            Cache, RemovalNotification

public final class CacheBuilder
{
    static final class OneWeigher extends Enum
        implements Weigher
    {

                public final int weigh(Object obj, Object obj1)
                {
/* 202*/            return 1;
                }

                public static final OneWeigher INSTANCE;
                private static final OneWeigher $VALUES[];

                static 
                {
/* 198*/            INSTANCE = new OneWeigher("INSTANCE", 0);
/* 197*/            $VALUES = (new OneWeigher[] {
/* 197*/                INSTANCE
                    });
                }

                private OneWeigher(String s, int i)
                {
/* 197*/            super(s, i);
                }
    }

    static final class NullListener extends Enum
        implements RemovalListener
    {

                public final void onRemoval(RemovalNotification removalnotification)
                {
                }

                public static final NullListener INSTANCE;
                private static final NullListener $VALUES[];

                static 
                {
/* 191*/            INSTANCE = new NullListener("INSTANCE", 0);
/* 190*/            $VALUES = (new NullListener[] {
/* 190*/                INSTANCE
                    });
                }

                private NullListener(String s, int i)
                {
/* 190*/            super(s, i);
                }
    }


            CacheBuilder()
            {
/* 217*/        strictParsing = true;
/* 219*/        initialCapacity = -1;
/* 220*/        concurrencyLevel = -1;
/* 221*/        maximumSize = -1L;
/* 222*/        maximumWeight = -1L;
/* 228*/        expireAfterWriteNanos = -1L;
/* 229*/        expireAfterAccessNanos = -1L;
/* 230*/        refreshNanos = -1L;
/* 238*/        statsCounterSupplier = NULL_STATS_COUNTER;
            }

            public static CacheBuilder newBuilder()
            {
/* 248*/        return new CacheBuilder();
            }

            public static CacheBuilder from(CacheBuilderSpec cachebuilderspec)
            {
/* 259*/        return cachebuilderspec.toCacheBuilder().lenientParsing();
            }

            public static CacheBuilder from(String s)
            {
/* 273*/        return from(CacheBuilderSpec.parse(s));
            }

            final CacheBuilder lenientParsing()
            {
/* 281*/        strictParsing = false;
/* 282*/        return this;
            }

            final CacheBuilder keyEquivalence(Equivalence equivalence)
            {
/* 293*/        Preconditions.checkState(keyEquivalence == null, "key equivalence was already set to %s", new Object[] {
/* 293*/            keyEquivalence
                });
/* 294*/        keyEquivalence = (Equivalence)Preconditions.checkNotNull(equivalence);
/* 295*/        return this;
            }

            final Equivalence getKeyEquivalence()
            {
/* 299*/        return (Equivalence)MoreObjects.firstNonNull(keyEquivalence, getKeyStrength().defaultEquivalence());
            }

            final CacheBuilder valueEquivalence(Equivalence equivalence)
            {
/* 311*/        Preconditions.checkState(valueEquivalence == null, "value equivalence was already set to %s", new Object[] {
/* 311*/            valueEquivalence
                });
/* 313*/        valueEquivalence = (Equivalence)Preconditions.checkNotNull(equivalence);
/* 314*/        return this;
            }

            final Equivalence getValueEquivalence()
            {
/* 318*/        return (Equivalence)MoreObjects.firstNonNull(valueEquivalence, getValueStrength().defaultEquivalence());
            }

            public final CacheBuilder initialCapacity(int i)
            {
/* 332*/        Preconditions.checkState(initialCapacity == -1, "initial capacity was already set to %s", new Object[] {
/* 332*/            Integer.valueOf(initialCapacity)
                });
/* 334*/        Preconditions.checkArgument(i >= 0);
/* 335*/        initialCapacity = i;
/* 336*/        return this;
            }

            final int getInitialCapacity()
            {
/* 340*/        if(initialCapacity == -1)
/* 340*/            return 16;
/* 340*/        else
/* 340*/            return initialCapacity;
            }

            public final CacheBuilder concurrencyLevel(int i)
            {
/* 374*/        Preconditions.checkState(concurrencyLevel == -1, "concurrency level was already set to %s", new Object[] {
/* 374*/            Integer.valueOf(concurrencyLevel)
                });
/* 376*/        Preconditions.checkArgument(i > 0);
/* 377*/        concurrencyLevel = i;
/* 378*/        return this;
            }

            final int getConcurrencyLevel()
            {
/* 382*/        if(concurrencyLevel == -1)
/* 382*/            return 4;
/* 382*/        else
/* 382*/            return concurrencyLevel;
            }

            public final CacheBuilder maximumSize(long l)
            {
/* 401*/        Preconditions.checkState(maximumSize == -1L, "maximum size was already set to %s", new Object[] {
/* 401*/            Long.valueOf(maximumSize)
                });
/* 403*/        Preconditions.checkState(maximumWeight == -1L, "maximum weight was already set to %s", new Object[] {
/* 403*/            Long.valueOf(maximumWeight)
                });
/* 405*/        Preconditions.checkState(weigher == null, "maximum size can not be combined with weigher");
/* 406*/        Preconditions.checkArgument(l >= 0L, "maximum size must not be negative");
/* 407*/        maximumSize = l;
/* 408*/        return this;
            }

            public final CacheBuilder maximumWeight(long l)
            {
/* 437*/        Preconditions.checkState(maximumWeight == -1L, "maximum weight was already set to %s", new Object[] {
/* 437*/            Long.valueOf(maximumWeight)
                });
/* 439*/        Preconditions.checkState(maximumSize == -1L, "maximum size was already set to %s", new Object[] {
/* 439*/            Long.valueOf(maximumSize)
                });
/* 441*/        maximumWeight = l;
/* 442*/        Preconditions.checkArgument(l >= 0L, "maximum weight must not be negative");
/* 443*/        return this;
            }

            public final CacheBuilder weigher(Weigher weigher1)
            {
/* 477*/        Preconditions.checkState(weigher == null);
/* 478*/        if(strictParsing)
/* 479*/            Preconditions.checkState(maximumSize == -1L, "weigher can not be combined with maximum size", new Object[] {
/* 479*/                Long.valueOf(maximumSize)
                    });
                CacheBuilder cachebuilder;
/* 485*/        (cachebuilder = this).weigher = (Weigher)Preconditions.checkNotNull(weigher1);
/* 487*/        return cachebuilder;
            }

            final long getMaximumWeight()
            {
/* 491*/        if(expireAfterWriteNanos == 0L || expireAfterAccessNanos == 0L)
/* 492*/            return 0L;
/* 494*/        if(weigher == null)
/* 494*/            return maximumSize;
/* 494*/        else
/* 494*/            return maximumWeight;
            }

            final Weigher getWeigher()
            {
/* 500*/        return (Weigher)MoreObjects.firstNonNull(weigher, OneWeigher.INSTANCE);
            }

            public final CacheBuilder weakKeys()
            {
/* 518*/        return setKeyStrength(LocalCache.Strength.WEAK);
            }

            final CacheBuilder setKeyStrength(LocalCache.Strength strength)
            {
/* 522*/        Preconditions.checkState(keyStrength == null, "Key strength was already set to %s", new Object[] {
/* 522*/            keyStrength
                });
/* 523*/        keyStrength = (LocalCache.Strength)Preconditions.checkNotNull(strength);
/* 524*/        return this;
            }

            final LocalCache.Strength getKeyStrength()
            {
/* 528*/        return (LocalCache.Strength)MoreObjects.firstNonNull(keyStrength, LocalCache.Strength.STRONG);
            }

            public final CacheBuilder weakValues()
            {
/* 549*/        return setValueStrength(LocalCache.Strength.WEAK);
            }

            public final CacheBuilder softValues()
            {
/* 573*/        return setValueStrength(LocalCache.Strength.SOFT);
            }

            final CacheBuilder setValueStrength(LocalCache.Strength strength)
            {
/* 577*/        Preconditions.checkState(valueStrength == null, "Value strength was already set to %s", new Object[] {
/* 577*/            valueStrength
                });
/* 578*/        valueStrength = (LocalCache.Strength)Preconditions.checkNotNull(strength);
/* 579*/        return this;
            }

            final LocalCache.Strength getValueStrength()
            {
/* 583*/        return (LocalCache.Strength)MoreObjects.firstNonNull(valueStrength, LocalCache.Strength.STRONG);
            }

            public final CacheBuilder expireAfterWrite(long l, TimeUnit timeunit)
            {
/* 606*/        Preconditions.checkState(expireAfterWriteNanos == -1L, "expireAfterWrite was already set to %s ns", new Object[] {
/* 606*/            Long.valueOf(expireAfterWriteNanos)
                });
/* 608*/        Preconditions.checkArgument(l >= 0L, "duration cannot be negative: %s %s", new Object[] {
/* 608*/            Long.valueOf(l), timeunit
                });
/* 609*/        expireAfterWriteNanos = timeunit.toNanos(l);
/* 610*/        return this;
            }

            final long getExpireAfterWriteNanos()
            {
/* 614*/        if(expireAfterWriteNanos == -1L)
/* 614*/            return 0L;
/* 614*/        else
/* 614*/            return expireAfterWriteNanos;
            }

            public final CacheBuilder expireAfterAccess(long l, TimeUnit timeunit)
            {
/* 640*/        Preconditions.checkState(expireAfterAccessNanos == -1L, "expireAfterAccess was already set to %s ns", new Object[] {
/* 640*/            Long.valueOf(expireAfterAccessNanos)
                });
/* 642*/        Preconditions.checkArgument(l >= 0L, "duration cannot be negative: %s %s", new Object[] {
/* 642*/            Long.valueOf(l), timeunit
                });
/* 643*/        expireAfterAccessNanos = timeunit.toNanos(l);
/* 644*/        return this;
            }

            final long getExpireAfterAccessNanos()
            {
/* 648*/        if(expireAfterAccessNanos == -1L)
/* 648*/            return 0L;
/* 648*/        else
/* 648*/            return expireAfterAccessNanos;
            }

            public final CacheBuilder refreshAfterWrite(long l, TimeUnit timeunit)
            {
/* 680*/        Preconditions.checkNotNull(timeunit);
/* 681*/        Preconditions.checkState(refreshNanos == -1L, "refresh was already set to %s ns", new Object[] {
/* 681*/            Long.valueOf(refreshNanos)
                });
/* 682*/        Preconditions.checkArgument(l > 0L, "duration must be positive: %s %s", new Object[] {
/* 682*/            Long.valueOf(l), timeunit
                });
/* 683*/        refreshNanos = timeunit.toNanos(l);
/* 684*/        return this;
            }

            final long getRefreshNanos()
            {
/* 688*/        if(refreshNanos == -1L)
/* 688*/            return 0L;
/* 688*/        else
/* 688*/            return refreshNanos;
            }

            public final CacheBuilder ticker(Ticker ticker1)
            {
/* 701*/        Preconditions.checkState(ticker == null);
/* 702*/        ticker = (Ticker)Preconditions.checkNotNull(ticker1);
/* 703*/        return this;
            }

            final Ticker getTicker(boolean flag)
            {
/* 707*/        if(ticker != null)
/* 708*/            return ticker;
/* 710*/        if(flag)
/* 710*/            return Ticker.systemTicker();
/* 710*/        else
/* 710*/            return NULL_TICKER;
            }

            public final CacheBuilder removalListener(RemovalListener removallistener)
            {
/* 737*/        Preconditions.checkState(removalListener == null);
                CacheBuilder cachebuilder;
/* 741*/        (cachebuilder = this).removalListener = (RemovalListener)Preconditions.checkNotNull(removallistener);
/* 743*/        return cachebuilder;
            }

            final RemovalListener getRemovalListener()
            {
/* 749*/        return (RemovalListener)MoreObjects.firstNonNull(removalListener, NullListener.INSTANCE);
            }

            public final CacheBuilder recordStats()
            {
/* 762*/        statsCounterSupplier = CACHE_STATS_COUNTER;
/* 763*/        return this;
            }

            final boolean isRecordingStats()
            {
/* 767*/        return statsCounterSupplier == CACHE_STATS_COUNTER;
            }

            final Supplier getStatsCounterSupplier()
            {
/* 771*/        return statsCounterSupplier;
            }

            public final LoadingCache build(CacheLoader cacheloader)
            {
/* 788*/        checkWeightWithWeigher();
/* 789*/        return new LocalCache.LocalLoadingCache(this, cacheloader);
            }

            public final Cache build()
            {
/* 805*/        checkWeightWithWeigher();
/* 806*/        checkNonLoadingCache();
/* 807*/        return new LocalCache.LocalManualCache(this);
            }

            private void checkNonLoadingCache()
            {
/* 811*/        Preconditions.checkState(refreshNanos == -1L, "refreshAfterWrite requires a LoadingCache");
            }

            private void checkWeightWithWeigher()
            {
/* 815*/        if(weigher == null)
                {
/* 816*/            Preconditions.checkState(maximumWeight == -1L, "maximumWeight requires weigher");
/* 816*/            return;
                }
/* 818*/        if(strictParsing)
                {
/* 819*/            Preconditions.checkState(maximumWeight != -1L, "weigher requires maximumWeight");
/* 819*/            return;
                }
/* 821*/        if(maximumWeight == -1L)
/* 822*/            logger.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
            }

            public final String toString()
            {
/* 834*/        jersey.repackaged.com.google.common.base.MoreObjects.ToStringHelper tostringhelper = MoreObjects.toStringHelper(this);
/* 835*/        if(initialCapacity != -1)
/* 836*/            tostringhelper.add("initialCapacity", initialCapacity);
/* 838*/        if(concurrencyLevel != -1)
/* 839*/            tostringhelper.add("concurrencyLevel", concurrencyLevel);
/* 841*/        if(maximumSize != -1L)
/* 842*/            tostringhelper.add("maximumSize", maximumSize);
/* 844*/        if(maximumWeight != -1L)
/* 845*/            tostringhelper.add("maximumWeight", maximumWeight);
                long l;
/* 847*/        if(expireAfterWriteNanos != -1L)
/* 848*/            tostringhelper.add("expireAfterWrite", (new StringBuilder(22)).append(l = expireAfterWriteNanos).append("ns").toString());
/* 850*/        if(expireAfterAccessNanos != -1L)
/* 851*/            tostringhelper.add("expireAfterAccess", (new StringBuilder(22)).append(l = expireAfterAccessNanos).append("ns").toString());
/* 853*/        if(keyStrength != null)
/* 854*/            tostringhelper.add("keyStrength", Ascii.toLowerCase(keyStrength.toString()));
/* 856*/        if(valueStrength != null)
/* 857*/            tostringhelper.add("valueStrength", Ascii.toLowerCase(valueStrength.toString()));
/* 859*/        if(keyEquivalence != null)
/* 860*/            tostringhelper.addValue("keyEquivalence");
/* 862*/        if(valueEquivalence != null)
/* 863*/            tostringhelper.addValue("valueEquivalence");
/* 865*/        if(removalListener != null)
/* 866*/            tostringhelper.addValue("removalListener");
/* 868*/        return tostringhelper.toString();
            }

            private static final int DEFAULT_INITIAL_CAPACITY = 16;
            private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
            private static final int DEFAULT_EXPIRATION_NANOS = 0;
            private static final int DEFAULT_REFRESH_NANOS = 0;
            static final Supplier NULL_STATS_COUNTER = Suppliers.ofInstance(new AbstractCache.StatsCounter() {

                public final void recordHits(int i)
                {
                }

                public final void recordMisses(int i)
                {
                }

                public final void recordLoadSuccess(long l)
                {
                }

                public final void recordLoadException(long l)
                {
                }

                public final void recordEviction()
                {
                }

                public final CacheStats snapshot()
                {
/* 177*/            return CacheBuilder.EMPTY_STATS;
                }

    });
            static final CacheStats EMPTY_STATS = new CacheStats(0L, 0L, 0L, 0L, 0L, 0L);
            static final Supplier CACHE_STATS_COUNTER = new Supplier() {

                public final AbstractCache.StatsCounter get()
                {
/* 186*/            return new AbstractCache.SimpleStatsCounter();
                }

                public final volatile Object get()
                {
/* 183*/            return get();
                }

    };
            static final Ticker NULL_TICKER = new Ticker() {

                public final long read()
                {
/* 209*/            return 0L;
                }

    };
            private static final Logger logger = Logger.getLogger(jersey/repackaged/com/google/common/cache/CacheBuilder.getName());
            static final int UNSET_INT = -1;
            boolean strictParsing;
            int initialCapacity;
            int concurrencyLevel;
            long maximumSize;
            long maximumWeight;
            Weigher weigher;
            LocalCache.Strength keyStrength;
            LocalCache.Strength valueStrength;
            long expireAfterWriteNanos;
            long expireAfterAccessNanos;
            long refreshNanos;
            Equivalence keyEquivalence;
            Equivalence valueEquivalence;
            RemovalListener removalListener;
            Ticker ticker;
            Supplier statsCounterSupplier;

}
