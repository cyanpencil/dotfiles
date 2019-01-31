// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            Cache, CacheStats, LongAddable, LongAddables

public abstract class AbstractCache
    implements Cache
{
    public static final class SimpleStatsCounter
        implements StatsCounter
    {

                public final void recordHits(int i)
                {
/* 226*/            hitCount.add(i);
                }

                public final void recordMisses(int i)
                {
/* 234*/            missCount.add(i);
                }

                public final void recordLoadSuccess(long l)
                {
/* 239*/            loadSuccessCount.increment();
/* 240*/            totalLoadTime.add(l);
                }

                public final void recordLoadException(long l)
                {
/* 245*/            loadExceptionCount.increment();
/* 246*/            totalLoadTime.add(l);
                }

                public final void recordEviction()
                {
/* 251*/            evictionCount.increment();
                }

                public final CacheStats snapshot()
                {
/* 256*/            return new CacheStats(hitCount.sum(), missCount.sum(), loadSuccessCount.sum(), loadExceptionCount.sum(), totalLoadTime.sum(), evictionCount.sum());
                }

                public final void incrementBy(StatsCounter statscounter)
                {
/* 269*/            statscounter = statscounter.snapshot();
/* 270*/            hitCount.add(statscounter.hitCount());
/* 271*/            missCount.add(statscounter.missCount());
/* 272*/            loadSuccessCount.add(statscounter.loadSuccessCount());
/* 273*/            loadExceptionCount.add(statscounter.loadExceptionCount());
/* 274*/            totalLoadTime.add(statscounter.totalLoadTime());
/* 275*/            evictionCount.add(statscounter.evictionCount());
                }

                private final LongAddable hitCount = LongAddables.create();
                private final LongAddable missCount = LongAddables.create();
                private final LongAddable loadSuccessCount = LongAddables.create();
                private final LongAddable loadExceptionCount = LongAddables.create();
                private final LongAddable totalLoadTime = LongAddables.create();
                private final LongAddable evictionCount = LongAddables.create();

                public SimpleStatsCounter()
                {
                }
    }

    public static interface StatsCounter
    {

        public abstract void recordHits(int i);

        public abstract void recordMisses(int i);

        public abstract void recordLoadSuccess(long l);

        public abstract void recordLoadException(long l);

        public abstract void recordEviction();

        public abstract CacheStats snapshot();
    }

}
