// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyProcessingException.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ProcessingException;

public class MessageBodyProcessingException extends ProcessingException
{

            public MessageBodyProcessingException(Throwable throwable)
            {
/*  59*/        super(throwable);
            }

            public MessageBodyProcessingException(String s, Throwable throwable)
            {
/*  68*/        super(s, throwable);
            }

            public MessageBodyProcessingException(String s)
            {
/*  76*/        super(s);
            }

            private static final long serialVersionUID = 0x1d0c7431405743ecL;
}
