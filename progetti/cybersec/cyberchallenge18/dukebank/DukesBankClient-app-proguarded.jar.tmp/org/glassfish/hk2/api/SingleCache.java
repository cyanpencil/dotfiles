// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SingleCache.java

package org.glassfish.hk2.api;


public interface SingleCache
{

    public abstract Object getCache();

    public abstract boolean isCacheSet();

    public abstract void setCache(Object obj);

    public abstract void releaseCache();
}
