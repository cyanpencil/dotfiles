// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstructorAction.java

package org.jvnet.hk2.internal;

import java.lang.reflect.Constructor;

public interface ConstructorAction
{

    public abstract Object makeMe(Constructor constructor, Object aobj[], boolean flag)
        throws Throwable;
}
