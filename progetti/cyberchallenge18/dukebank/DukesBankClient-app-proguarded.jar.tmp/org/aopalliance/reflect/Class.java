// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Class.java

package org.aopalliance.reflect;


// Referenced classes of package org.aopalliance.reflect:
//            ProgramUnit, ClassLocator, Field, Method

public interface Class
    extends ProgramUnit
{

    public abstract ClassLocator getClassLocator();

    public abstract String getName();

    public abstract Field[] getFields();

    public abstract Field[] getDeclaredFields();

    public abstract Method[] getMethods();

    public abstract Method[] getDeclaredMethods();

    public abstract Class getSuperclass();

    public abstract Class[] getInterfaces();
}
