// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Factory.java

package org.glassfish.hk2.api;


public interface Factory
{

    public abstract Object provide();

    public abstract void dispose(Object obj);
}
