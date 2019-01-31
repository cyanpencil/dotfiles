// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LongAddable.java

package jersey.repackaged.com.google.common.cache;


interface LongAddable
{

    public abstract void increment();

    public abstract void add(long l);

    public abstract long sum();
}
