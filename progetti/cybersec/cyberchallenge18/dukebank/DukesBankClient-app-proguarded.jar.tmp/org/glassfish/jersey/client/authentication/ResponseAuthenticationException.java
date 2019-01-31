// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResponseAuthenticationException.java

package org.glassfish.jersey.client.authentication;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.Response;

public class ResponseAuthenticationException extends ResponseProcessingException
{

            public ResponseAuthenticationException(Response response, Throwable throwable)
            {
/*  59*/        super(response, throwable);
            }

            public ResponseAuthenticationException(Response response, String s)
            {
/*  69*/        super(response, s);
            }

            public ResponseAuthenticationException(Response response, String s, Throwable throwable)
            {
/*  80*/        super(response, s, throwable);
            }
}
