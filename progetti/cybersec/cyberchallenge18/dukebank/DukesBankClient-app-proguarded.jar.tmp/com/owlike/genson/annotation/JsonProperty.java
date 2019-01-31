// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JsonProperty.java

package com.owlike.genson.annotation;

import java.lang.annotation.Annotation;

public interface JsonProperty
    extends Annotation
{

    public abstract String value();

    public abstract String[] aliases();

    public abstract boolean serialize();

    public abstract boolean deserialize();
}
