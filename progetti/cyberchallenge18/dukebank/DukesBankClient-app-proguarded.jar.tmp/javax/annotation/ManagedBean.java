// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ManagedBean.java

package javax.annotation;

import java.lang.annotation.Annotation;

public interface ManagedBean
    extends Annotation
{

    public abstract String value();
}
