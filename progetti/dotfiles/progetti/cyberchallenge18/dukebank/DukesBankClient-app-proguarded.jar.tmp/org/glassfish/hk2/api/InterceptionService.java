// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InterceptionService.java

package org.glassfish.hk2.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

// Referenced classes of package org.glassfish.hk2.api:
//            Filter

public interface InterceptionService
{

    public abstract Filter getDescriptorFilter();

    public abstract List getMethodInterceptors(Method method);

    public abstract List getConstructorInterceptors(Constructor constructor);
}
