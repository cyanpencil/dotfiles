// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Cache.java

package jersey.repackaged.com.google.common.cache;

import java.util.Map;
import java.util.concurrent.*;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheStats

public interface Cache
{

    public abstract Object getIfPresent(Object obj);

    public abstract Object get(Object obj, Callable callable)
        throws ExecutionException;

    public abstract ImmutableMap getAllPresent(Iterable iterable);

    public abstract void put(Object obj, Object obj1);

    public abstract void putAll(Map map);

    public abstract void invalidate(Object obj);

    public abstract void invalidateAll(Iterable iterable);

    public abstract void invalidateAll();

    public abstract long size();

    public abstract CacheStats stats();

    public abstract ConcurrentMap asMap();

    public abstract void cleanUp();
}
