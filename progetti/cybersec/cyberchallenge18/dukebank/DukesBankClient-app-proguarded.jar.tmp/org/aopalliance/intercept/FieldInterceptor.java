// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FieldInterceptor.java

package org.aopalliance.intercept;


// Referenced classes of package org.aopalliance.intercept:
//            Interceptor, FieldAccess

public interface FieldInterceptor
    extends Interceptor
{

    public abstract Object get(FieldAccess fieldaccess)
        throws Throwable;

    public abstract Object set(FieldAccess fieldaccess)
        throws Throwable;
}
