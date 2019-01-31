// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientErrorException.java

package javax.ws.rs;

import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            WebApplicationException

public class ClientErrorException extends WebApplicationException
{

            public ClientErrorException(javax.ws.rs.core.Response.Status status)
            {
/*  64*/        super(null, validate(Response.status(status).build(), javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(String s, javax.ws.rs.core.Response.Status status)
            {
/*  79*/        super(s, null, validate(Response.status(status).build(), javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(int i)
            {
/*  91*/        super(null, validate(Response.status(i).build(), javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(String s, int i)
            {
/* 105*/        super(s, null, validate(Response.status(i).build(), javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(Response response)
            {
/* 117*/        super(null, validate(response, javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(String s, Response response)
            {
/* 131*/        super(s, null, validate(response, javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(javax.ws.rs.core.Response.Status status, Throwable throwable)
            {
/* 144*/        super(throwable, validate(Response.status(status).build(), javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(String s, javax.ws.rs.core.Response.Status status, Throwable throwable)
            {
/* 159*/        super(s, throwable, validate(Response.status(status).build(), javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(int i, Throwable throwable)
            {
/* 172*/        super(throwable, validate(Response.status(i).build(), javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(String s, int i, Throwable throwable)
            {
/* 187*/        super(s, throwable, validate(Response.status(i).build(), javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(Response response, Throwable throwable)
            {
/* 200*/        super(throwable, validate(response, javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            public ClientErrorException(String s, Response response, Throwable throwable)
            {
/* 215*/        super(s, throwable, validate(response, javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR));
            }

            private static final long serialVersionUID = 0xc712df69f2b02e2aL;
}
