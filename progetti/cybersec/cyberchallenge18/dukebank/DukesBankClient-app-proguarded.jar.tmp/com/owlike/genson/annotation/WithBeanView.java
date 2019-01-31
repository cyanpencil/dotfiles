// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WithBeanView.java

package com.owlike.genson.annotation;

import java.lang.annotation.Annotation;

public interface WithBeanView
    extends Annotation
{

    public abstract Class[] views();
}
