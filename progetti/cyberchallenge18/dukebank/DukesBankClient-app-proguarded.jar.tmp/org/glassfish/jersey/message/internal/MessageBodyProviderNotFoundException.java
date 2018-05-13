// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyProviderNotFoundException.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ProcessingException;

public class MessageBodyProviderNotFoundException extends ProcessingException
{

            public MessageBodyProviderNotFoundException(Throwable throwable)
            {
/*  55*/        super(throwable);
            }

            public MessageBodyProviderNotFoundException(String s, Throwable throwable)
            {
/*  59*/        super(s, throwable);
            }

            public MessageBodyProviderNotFoundException(String s)
            {
/*  63*/        super(s);
            }

            private static final long serialVersionUID = 0x1d0c7431405743ecL;
}
