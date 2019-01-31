// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstructorInterceptor.java

package org.aopalliance.intercept;


// Referenced classes of package org.aopalliance.intercept:
//            Interceptor, ConstructorInvocation

public interface ConstructorInterceptor
    extends Interceptor
{

    public abstract Object construct(ConstructorInvocation constructorinvocation)
        throws Throwable;
}
