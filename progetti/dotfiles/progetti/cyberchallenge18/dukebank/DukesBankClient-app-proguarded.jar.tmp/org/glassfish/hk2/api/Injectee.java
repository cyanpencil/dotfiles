// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Injectee.java

package org.glassfish.hk2.api;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.Set;

// Referenced classes of package org.glassfish.hk2.api:
//            Unqualified, ActiveDescriptor

public interface Injectee
{

    public abstract Type getRequiredType();

    public abstract Set getRequiredQualifiers();

    public abstract int getPosition();

    public abstract Class getInjecteeClass();

    public abstract AnnotatedElement getParent();

    public abstract boolean isOptional();

    public abstract boolean isSelf();

    public abstract Unqualified getUnqualified();

    public abstract ActiveDescriptor getInjecteeDescriptor();
}
