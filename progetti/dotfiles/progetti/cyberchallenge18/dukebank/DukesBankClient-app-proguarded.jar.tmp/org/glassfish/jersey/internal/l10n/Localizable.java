// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Localizable.java

package org.glassfish.jersey.internal.l10n;


public interface Localizable
{

    public abstract String getKey();

    public abstract Object[] getArguments();

    public abstract String getResourceBundleName();

    public static final String NOT_LOCALIZABLE = "\0";
}
