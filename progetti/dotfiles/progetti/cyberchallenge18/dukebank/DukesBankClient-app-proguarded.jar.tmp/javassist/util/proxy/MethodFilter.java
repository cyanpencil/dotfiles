// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodFilter.java

package javassist.util.proxy;

import java.lang.reflect.Method;

public interface MethodFilter
{

    public abstract boolean isHandled(Method method);
}
