// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            AbstractCache, CacheStats, LongAddable, LongAddables

public static final class 
    implements 
{

            public final void recordHits(int i)
            {
/* 226*/        hitCount.add(i);
            }

            public final void recordMisses(int i)
            {
/* 234*/        missCount.add(i);
            }

            public final void recordLoadSuccess(long l)
            {
/* 239*/        loadSuccessCount.increment();
/* 240*/        totalLoadTime.add(l);
            }

            public final void recordLoadException(long l)
            {
/* 245*/        loadExceptionCount.increment();
/* 246*/        totalLoadTime.add(l);
            }

            public final void recordEviction()
            {
/* 251*/        evictionCount.increment();
            }

            public final CacheStats snapshot()
            {
/* 256*/        return new CacheStats(hitCount.sum(), missCount.sum(), loadSuccessCount.sum(), loadExceptionCount.sum(), totalLoadTime.sum(), evictionCount.sum());
            }

            public final void incrementBy(evictionCount evictioncount)
            {
/* 269*/        evictioncount = evictioncount.ot();
/* 270*/        hitCount.add(evictioncount.hitCount());
/* 271*/        missCount.add(evictioncount.missCount());
/* 272*/        loadSuccessCount.add(evictioncount.loadSuccessCount());
/* 273*/        loadExceptionCount.add(evictioncount.loadExceptionCount());
/* 274*/        totalLoadTime.add(evictioncount.totalLoadTime());
/* 275*/        evictionCount.add(evictioncount.evictionCount());
            }

            private final LongAddable hitCount = LongAddables.create();
            private final LongAddable missCount = LongAddables.create();
            private final LongAddable loadSuccessCount = LongAddables.create();
            private final LongAddable loadExceptionCount = LongAddables.create();
            private final LongAddable totalLoadTime = LongAddables.create();
            private final LongAddable evictionCount = LongAddables.create();

            public ()
            {
            }
}
