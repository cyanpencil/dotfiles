// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodHandler.java

package javassist.util.proxy;

import java.lang.reflect.Method;

public interface MethodHandler
{

    public abstract Object invoke(Object obj, Method method, Method method1, Object aobj[])
        throws Throwable;
}
