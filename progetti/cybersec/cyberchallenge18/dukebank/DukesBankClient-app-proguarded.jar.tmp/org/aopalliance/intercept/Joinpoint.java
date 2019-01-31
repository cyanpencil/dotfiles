// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Joinpoint.java

package org.aopalliance.intercept;

import java.lang.reflect.AccessibleObject;

public interface Joinpoint
{

    public abstract Object proceed()
        throws Throwable;

    public abstract Object getThis();

    public abstract AccessibleObject getStaticPart();
}
