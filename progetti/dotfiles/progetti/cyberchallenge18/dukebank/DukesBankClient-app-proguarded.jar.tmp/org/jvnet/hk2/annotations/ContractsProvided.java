// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContractsProvided.java

package org.jvnet.hk2.annotations;

import java.lang.annotation.Annotation;

public interface ContractsProvided
    extends Annotation
{

    public abstract Class[] value();
}
