// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextResolvers.java

package org.glassfish.jersey.spi;

import java.lang.reflect.Type;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;

public interface ContextResolvers
{

    public abstract ContextResolver resolve(Type type, MediaType mediatype);
}
