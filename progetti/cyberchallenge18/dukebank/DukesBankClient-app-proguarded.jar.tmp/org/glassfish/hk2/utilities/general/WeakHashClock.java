// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WeakHashClock.java

package org.glassfish.hk2.utilities.general;

import java.util.Map;
import org.glassfish.hk2.utilities.cache.CacheKeyFilter;

public interface WeakHashClock
{

    public abstract void put(Object obj, Object obj1);

    public abstract Object get(Object obj);

    public abstract Object remove(Object obj);

    public abstract void releaseMatching(CacheKeyFilter cachekeyfilter);

    public abstract int size();

    public abstract java.util.Map.Entry next();

    public abstract void clear();

    public abstract void clearStaleReferences();

    public abstract boolean hasWeakKeys();
}
