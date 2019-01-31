// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Topic.java

package org.glassfish.hk2.api.messaging;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

public interface Topic
{

    public abstract void publish(Object obj);

    public abstract Topic named(String s);

    public abstract Topic ofType(Type type);

    public transient abstract Topic qualifiedWith(Annotation aannotation[]);

    public abstract Type getTopicType();

    public abstract Set getTopicQualifiers();
}
