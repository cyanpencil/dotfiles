// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Unqualified.java

package org.glassfish.hk2.api;

import java.lang.annotation.Annotation;

public interface Unqualified
    extends Annotation
{

    public abstract Class[] value();
}
