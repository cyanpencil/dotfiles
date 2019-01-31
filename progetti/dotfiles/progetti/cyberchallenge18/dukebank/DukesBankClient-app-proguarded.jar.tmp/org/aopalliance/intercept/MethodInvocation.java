// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodInvocation.java

package org.aopalliance.intercept;

import java.lang.reflect.Method;

// Referenced classes of package org.aopalliance.intercept:
//            Invocation

public interface MethodInvocation
    extends Invocation
{

    public abstract Method getMethod();
}
