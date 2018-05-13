// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RequestAuthenticationException.java

package org.glassfish.jersey.client.authentication;

import javax.ws.rs.ProcessingException;

public class RequestAuthenticationException extends ProcessingException
{

            public RequestAuthenticationException(Throwable throwable)
            {
/*  57*/        super(throwable);
            }

            public RequestAuthenticationException(String s)
            {
/*  66*/        super(s);
            }

            public RequestAuthenticationException(String s, Throwable throwable)
            {
/*  76*/        super(s, throwable);
            }
}
