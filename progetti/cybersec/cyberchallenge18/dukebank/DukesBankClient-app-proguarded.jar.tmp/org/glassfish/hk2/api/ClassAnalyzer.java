// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassAnalyzer.java

package org.glassfish.hk2.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

// Referenced classes of package org.glassfish.hk2.api:
//            MultiException

public interface ClassAnalyzer
{

    public abstract Constructor getConstructor(Class class1)
        throws MultiException, NoSuchMethodException;

    public abstract Set getInitializerMethods(Class class1)
        throws MultiException;

    public abstract Set getFields(Class class1)
        throws MultiException;

    public abstract Method getPostConstructMethod(Class class1)
        throws MultiException;

    public abstract Method getPreDestroyMethod(Class class1)
        throws MultiException;

    public static final String DEFAULT_IMPLEMENTATION_NAME = "default";
}
