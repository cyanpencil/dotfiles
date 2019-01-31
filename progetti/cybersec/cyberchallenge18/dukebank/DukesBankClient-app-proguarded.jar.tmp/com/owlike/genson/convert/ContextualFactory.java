// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextualFactory.java

package com.owlike.genson.convert;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.reflect.BeanProperty;

public interface ContextualFactory
{

    public abstract Converter create(BeanProperty beanproperty, Genson genson);
}
