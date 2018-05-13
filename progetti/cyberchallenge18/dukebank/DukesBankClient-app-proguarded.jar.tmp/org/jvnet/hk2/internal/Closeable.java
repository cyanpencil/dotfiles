// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Closeable.java

package org.jvnet.hk2.internal;


public interface Closeable
{

    public abstract boolean close();

    public abstract boolean isClosed();
}
