// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import jersey.repackaged.com.google.common.util.concurrent.UncheckedExecutionException;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheLoader, LoadingCache, LocalCache, CacheBuilder

static class init> extends init>
    implements LoadingCache
{

            public Object get(Object obj)
                throws ExecutionException
            {
/*4824*/        return localCache.getOrLoad(obj);
            }

            public Object getUnchecked(Object obj)
            {
/*4830*/        return get(obj);
/*4831*/        obj;
/*4832*/        throw new UncheckedExecutionException(((ExecutionException) (obj)).getCause());
            }

            public ImmutableMap getAll(Iterable iterable)
                throws ExecutionException
            {
/*4838*/        return localCache.getAll(iterable);
            }

            public void refresh(Object obj)
            {
/*4843*/        localCache.refresh(obj);
            }

            public final Object apply(Object obj)
            {
/*4848*/        return getUnchecked(obj);
            }

            xception(CacheBuilder cachebuilder, CacheLoader cacheloader)
            {
/*4817*/        super(new LocalCache(cachebuilder, (CacheLoader)Preconditions.checkNotNull(cacheloader)));
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
