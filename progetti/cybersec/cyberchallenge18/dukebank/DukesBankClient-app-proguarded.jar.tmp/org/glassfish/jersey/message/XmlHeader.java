// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   XmlHeader.java

package org.glassfish.jersey.message;

import java.lang.annotation.Annotation;

public interface XmlHeader
    extends Annotation
{

    public abstract String value();
}
