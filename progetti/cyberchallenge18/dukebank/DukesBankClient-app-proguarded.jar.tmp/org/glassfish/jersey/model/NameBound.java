// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NameBound.java

package org.glassfish.jersey.model;

import java.util.Collection;

public interface NameBound
{

    public abstract boolean isNameBound();

    public abstract Collection getNameBindings();
}
