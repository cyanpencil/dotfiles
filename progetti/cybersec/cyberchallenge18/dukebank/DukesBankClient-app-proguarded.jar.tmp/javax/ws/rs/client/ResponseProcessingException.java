// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResponseProcessingException.java

package javax.ws.rs.client;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;

public class ResponseProcessingException extends ProcessingException
{

            public ResponseProcessingException(Response response1, Throwable throwable)
            {
/*  74*/        super(throwable);
/*  75*/        response = response1;
            }

            public ResponseProcessingException(Response response1, String s, Throwable throwable)
            {
/*  93*/        super(s, throwable);
/*  94*/        response = response1;
            }

            public ResponseProcessingException(Response response1, String s)
            {
/* 107*/        super(s);
/* 108*/        response = response1;
            }

            public Response getResponse()
            {
/* 117*/        return response;
            }

            private static final long serialVersionUID = 0xbbad6a97af1a7381L;
            private final Response response;
}
