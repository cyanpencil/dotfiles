// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExtendedExceptionMapper.java

package org.glassfish.jersey.spi;

import javax.ws.rs.ext.ExceptionMapper;

public interface ExtendedExceptionMapper
    extends ExceptionMapper
{

    public abstract boolean isMappable(Throwable throwable);
}
