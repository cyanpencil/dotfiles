// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassReflectionHelper.java

package org.glassfish.hk2.utilities.reflection;

import java.lang.reflect.Method;
import java.util.Set;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            MethodWrapper

public interface ClassReflectionHelper
{

    public abstract Set getAllMethods(Class class1);

    public abstract MethodWrapper createMethodWrapper(Method method);

    public abstract Set getAllFields(Class class1);

    public abstract Method findPostConstruct(Class class1, Class class2)
        throws IllegalArgumentException;

    public abstract Method findPreDestroy(Class class1, Class class2)
        throws IllegalArgumentException;

    public abstract void clean(Class class1);

    public abstract void dispose();

    public abstract int size();
}
