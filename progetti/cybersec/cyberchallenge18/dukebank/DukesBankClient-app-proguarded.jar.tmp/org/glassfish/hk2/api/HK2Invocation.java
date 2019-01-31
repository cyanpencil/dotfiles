// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HK2Invocation.java

package org.glassfish.hk2.api;


public interface HK2Invocation
{

    public abstract void setUserData(String s, Object obj);

    public abstract Object getUserData(String s);
}
