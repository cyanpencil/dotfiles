// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IterableProvider.java

package org.glassfish.hk2.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Provider;

// Referenced classes of package org.glassfish.hk2.api:
//            ServiceHandle

public interface IterableProvider
    extends Iterable, Provider
{

    public abstract ServiceHandle getHandle();

    public abstract int getSize();

    public abstract IterableProvider named(String s);

    public abstract IterableProvider ofType(Type type);

    public transient abstract IterableProvider qualifiedWith(Annotation aannotation[]);

    public abstract Iterable handleIterator();
}
