// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilder.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            AbstractCache, CacheBuilder, CacheStats

static class tatsCounter
    implements tatsCounter
{

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
/* 177*/        return CacheBuilder.EMPTY_STATS;
            }

            tatsCounter()
            {
            }
}
