// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WeakCARCache.java

package org.glassfish.hk2.utilities.cache;


// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            Computable, CacheKeyFilter

public interface WeakCARCache
{

    public abstract Object compute(Object obj);

    public abstract int getKeySize();

    public abstract int getValueSize();

    public abstract int getT1Size();

    public abstract int getT2Size();

    public abstract int getB1Size();

    public abstract int getB2Size();

    public abstract void clear();

    public abstract int getMaxSize();

    public abstract Computable getComputable();

    public abstract boolean remove(Object obj);

    public abstract void releaseMatching(CacheKeyFilter cachekeyfilter);

    public abstract void clearStaleReferences();

    public abstract int getP();

    public abstract String dumpAllLists();
}
