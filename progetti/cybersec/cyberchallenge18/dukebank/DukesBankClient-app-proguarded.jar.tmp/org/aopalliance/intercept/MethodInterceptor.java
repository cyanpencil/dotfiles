// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodInterceptor.java

package org.aopalliance.intercept;


// Referenced classes of package org.aopalliance.intercept:
//            Interceptor, MethodInvocation

public interface MethodInterceptor
    extends Interceptor
{

    public abstract Object invoke(MethodInvocation methodinvocation)
        throws Throwable;
}
