// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Code.java

package org.aopalliance.reflect;


// Referenced classes of package org.aopalliance.reflect:
//            CodeLocator, Method, Field, Class

public interface Code
{

    public abstract CodeLocator getLocator();

    public abstract CodeLocator getCallLocator(Method method);

    public abstract CodeLocator getReadLocator(Field field);

    public abstract CodeLocator getWriteLocator(Field field);

    public abstract CodeLocator getThrowLocator(Class class1);

    public abstract CodeLocator getCatchLocator(Class class1);
}
