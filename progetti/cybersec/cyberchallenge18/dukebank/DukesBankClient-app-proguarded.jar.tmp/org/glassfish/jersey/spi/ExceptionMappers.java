// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionMappers.java

package org.glassfish.jersey.spi;

import javax.ws.rs.ext.ExceptionMapper;

public interface ExceptionMappers
{

    public abstract ExceptionMapper find(Class class1);

    public abstract ExceptionMapper findMapping(Throwable throwable);
}
