// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.util.Map;
import java.util.concurrent.*;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            AbstractCache, Cache, LocalCache, CacheBuilder, 
//            CacheStats, CacheLoader

static class localCache
    implements Serializable, Cache
{

            public Object getIfPresent(Object obj)
            {
/*4733*/        return localCache.getIfPresent(obj);
            }

            public Object get(Object obj, final Callable valueLoader)
                throws ExecutionException
            {
/*4738*/        Preconditions.checkNotNull(valueLoader);
/*4739*/        return localCache.get(obj, new CacheLoader() {

                    public Object load(Object obj1)
                        throws Exception
                    {
/*4742*/                return valueLoader.call();
                    }

                    final Callable val$valueLoader;
                    final LocalCache.LocalManualCache this$0;

                    
                    {
/*4739*/                this$0 = LocalCache.LocalManualCache.this;
/*4739*/                valueLoader = callable;
/*4739*/                super();
                    }
        });
            }

            public ImmutableMap getAllPresent(Iterable iterable)
            {
/*4749*/        return localCache.getAllPresent(iterable);
            }

            public void put(Object obj, Object obj1)
            {
/*4754*/        localCache.put(obj, obj1);
            }

            public void putAll(Map map)
            {
/*4759*/        localCache.putAll(map);
            }

            public void invalidate(Object obj)
            {
/*4764*/        Preconditions.checkNotNull(obj);
/*4765*/        localCache.remove(obj);
            }

            public void invalidateAll(Iterable iterable)
            {
/*4770*/        localCache.invalidateAll(iterable);
            }

            public void invalidateAll()
            {
/*4775*/        localCache.clear();
            }

            public long size()
            {
/*4780*/        return localCache.longSize();
            }

            public ConcurrentMap asMap()
            {
/*4785*/        return localCache;
            }

            public CacheStats stats()
            {
                nter nter;
/*4790*/        (nter = new nter()).incrementBy(localCache.globalStatsCounter);
                nter anter[];
/*4792*/        int i = (anter = localCache.segments).length;
/*4792*/        for(int j = 0; j < i; j++)
                {
/*4792*/            nter nter1 = anter[j];
/*4793*/            nter.incrementBy(nter1.ter);
                }

/*4795*/        return nter.snapshot();
            }

            public void cleanUp()
            {
/*4800*/        localCache.cleanUp();
            }

            final LocalCache localCache;

            _cls1.val.valueLoader(CacheBuilder cachebuilder)
            {
/*4721*/        this(new LocalCache(cachebuilder, null));
            }

            private <init>(LocalCache localcache)
            {
/*4725*/        localCache = localcache;
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/cache/LocalCache$1

/* anonymous class */
    static class LocalCache._cls1
        implements LocalCache.ValueReference
    {

                public final Object get()
                {
/* 690*/            return null;
                }

                public final int getWeight()
                {
/* 695*/            return 0;
                }

                public final LocalCache.ReferenceEntry getEntry()
                {
/* 700*/            return null;
                }

                public final LocalCache.ValueReference copyFor(ReferenceQueue referencequeue, Object obj, LocalCache.ReferenceEntry referenceentry)
                {
/* 706*/            return this;
                }

                public final boolean isLoading()
                {
/* 711*/            return false;
                }

                public final boolean isActive()
                {
/* 716*/            return false;
                }

                public final Object waitForValue()
                {
/* 721*/            return null;
                }

                public final void notifyNewValue(Object obj)
                {
                }

    }

}
