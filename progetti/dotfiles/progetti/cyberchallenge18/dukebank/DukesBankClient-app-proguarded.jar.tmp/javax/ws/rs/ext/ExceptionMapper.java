// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionMapper.java

package javax.ws.rs.ext;

import javax.ws.rs.core.Response;

public interface ExceptionMapper
{

    public abstract Response toResponse(Throwable throwable);
}
