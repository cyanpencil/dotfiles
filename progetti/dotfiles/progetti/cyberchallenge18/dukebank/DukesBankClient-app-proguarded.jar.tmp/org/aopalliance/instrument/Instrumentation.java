// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Instrumentation.java

package org.aopalliance.instrument;

import org.aopalliance.reflect.Locator;

public interface Instrumentation
{

    public abstract Locator getLocation();

    public abstract int getType();

    public static final int ADD_INTERFACE = 0;
    public static final int SET_SUPERCLASS = 1;
    public static final int ADD_CLASS = 2;
    public static final int ADD_BEFORE_CODE = 3;
    public static final int ADD_AFTER_CODE = 4;
    public static final int ADD_METADATA = 5;
}
