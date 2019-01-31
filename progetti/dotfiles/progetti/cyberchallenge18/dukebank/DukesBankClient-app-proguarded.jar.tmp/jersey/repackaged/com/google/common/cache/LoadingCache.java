// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LoadingCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            Cache

public interface LoadingCache
    extends Function, Cache
{

    public abstract Object get(Object obj)
        throws ExecutionException;

    public abstract Object getUnchecked(Object obj);

    public abstract ImmutableMap getAll(Iterable iterable)
        throws ExecutionException;

    /**
     * @deprecated Method apply is deprecated
     */

    public abstract Object apply(Object obj);

    public abstract void refresh(Object obj);

    public abstract ConcurrentMap asMap();
}
