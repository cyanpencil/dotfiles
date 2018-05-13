// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheStats.java

package jersey.repackaged.com.google.common.cache;

import jersey.repackaged.com.google.common.base.*;

public final class CacheStats
{

            public CacheStats(long l, long l1, long l2, long l3, long l4, long l5)
            {
/*  80*/        Preconditions.checkArgument(l >= 0L);
/*  81*/        Preconditions.checkArgument(l1 >= 0L);
/*  82*/        Preconditions.checkArgument(l2 >= 0L);
/*  83*/        Preconditions.checkArgument(l3 >= 0L);
/*  84*/        Preconditions.checkArgument(l4 >= 0L);
/*  85*/        Preconditions.checkArgument(l5 >= 0L);
/*  87*/        hitCount = l;
/*  88*/        missCount = l1;
/*  89*/        loadSuccessCount = l2;
/*  90*/        loadExceptionCount = l3;
/*  91*/        totalLoadTime = l4;
/*  92*/        evictionCount = l5;
            }

            public final long hitCount()
            {
/* 107*/        return hitCount;
            }

            public final long missCount()
            {
/* 127*/        return missCount;
            }

            public final long loadSuccessCount()
            {
/* 161*/        return loadSuccessCount;
            }

            public final long loadExceptionCount()
            {
/* 172*/        return loadExceptionCount;
            }

            public final long totalLoadTime()
            {
/* 193*/        return totalLoadTime;
            }

            public final long evictionCount()
            {
/* 212*/        return evictionCount;
            }

            public final int hashCode()
            {
/* 248*/        return Objects.hashCode(new Object[] {
/* 248*/            Long.valueOf(hitCount), Long.valueOf(missCount), Long.valueOf(loadSuccessCount), Long.valueOf(loadExceptionCount), Long.valueOf(totalLoadTime), Long.valueOf(evictionCount)
                });
            }

            public final boolean equals(Object obj)
            {
/* 254*/        if(obj instanceof CacheStats)
                {
/* 255*/            obj = (CacheStats)obj;
/* 256*/            return hitCount == ((CacheStats) (obj)).hitCount && missCount == ((CacheStats) (obj)).missCount && loadSuccessCount == ((CacheStats) (obj)).loadSuccessCount && loadExceptionCount == ((CacheStats) (obj)).loadExceptionCount && totalLoadTime == ((CacheStats) (obj)).totalLoadTime && evictionCount == ((CacheStats) (obj)).evictionCount;
                } else
                {
/* 263*/            return false;
                }
            }

            public final String toString()
            {
/* 268*/        return MoreObjects.toStringHelper(this).add("hitCount", hitCount).add("missCount", missCount).add("loadSuccessCount", loadSuccessCount).add("loadExceptionCount", loadExceptionCount).add("totalLoadTime", totalLoadTime).add("evictionCount", evictionCount).toString();
            }

            private final long hitCount;
            private final long missCount;
            private final long loadSuccessCount;
            private final long loadExceptionCount;
            private final long totalLoadTime;
            private final long evictionCount;
}
