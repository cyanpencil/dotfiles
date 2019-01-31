// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertiesDelegate.java

package org.glassfish.jersey.internal;

import java.util.Collection;

public interface PropertiesDelegate
{

    public abstract Object getProperty(String s);

    public abstract Collection getPropertyNames();

    public abstract void setProperty(String s, Object obj);

    public abstract void removeProperty(String s);
}
