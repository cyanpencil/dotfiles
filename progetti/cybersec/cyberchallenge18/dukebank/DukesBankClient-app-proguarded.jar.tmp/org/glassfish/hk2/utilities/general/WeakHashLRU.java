// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WeakHashLRU.java

package org.glassfish.hk2.utilities.general;

import org.glassfish.hk2.utilities.cache.CacheKeyFilter;

public interface WeakHashLRU
{

    public abstract void add(Object obj);

    public abstract boolean contains(Object obj);

    public abstract boolean remove(Object obj);

    public abstract void releaseMatching(CacheKeyFilter cachekeyfilter);

    public abstract int size();

    public abstract Object remove();

    public abstract void clear();

    public abstract void clearStaleReferences();
}
