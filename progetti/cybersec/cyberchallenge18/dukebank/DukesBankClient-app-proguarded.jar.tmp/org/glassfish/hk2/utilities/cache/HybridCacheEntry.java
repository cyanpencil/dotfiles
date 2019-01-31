// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HybridCacheEntry.java

package org.glassfish.hk2.utilities.cache;


// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            CacheEntry

public interface HybridCacheEntry
    extends CacheEntry
{

    public abstract Object getValue();

    public abstract boolean dropMe();
}
