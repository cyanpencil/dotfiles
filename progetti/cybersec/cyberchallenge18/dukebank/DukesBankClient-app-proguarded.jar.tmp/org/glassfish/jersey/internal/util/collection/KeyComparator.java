// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   KeyComparator.java

package org.glassfish.jersey.internal.util.collection;

import java.io.Serializable;

public interface KeyComparator
    extends Serializable
{

    public abstract boolean equals(Object obj, Object obj1);

    public abstract int hash(Object obj);
}
