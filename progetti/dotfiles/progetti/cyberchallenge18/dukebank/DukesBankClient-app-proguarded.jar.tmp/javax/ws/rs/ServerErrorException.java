// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServerErrorException.java

package javax.ws.rs;

import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            WebApplicationException

public class ServerErrorException extends WebApplicationException
{

            public ServerErrorException(javax.ws.rs.core.Response.Status status)
            {
/*  64*/        super(null, validate(Response.status(status).build(), javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(String s, javax.ws.rs.core.Response.Status status)
            {
/*  78*/        super(s, null, validate(Response.status(status).build(), javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(int i)
            {
/*  90*/        super(null, validate(Response.status(i).build(), javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(String s, int i)
            {
/* 104*/        super(s, null, validate(Response.status(i).build(), javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(Response response)
            {
/* 116*/        super(null, validate(response, javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(String s, Response response)
            {
/* 130*/        super(s, null, validate(response, javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(javax.ws.rs.core.Response.Status status, Throwable throwable)
            {
/* 143*/        super(throwable, validate(Response.status(status).build(), javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(String s, javax.ws.rs.core.Response.Status status, Throwable throwable)
            {
/* 158*/        super(s, throwable, validate(Response.status(status).build(), javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(int i, Throwable throwable)
            {
/* 171*/        super(throwable, validate(Response.status(i).build(), javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(String s, int i, Throwable throwable)
            {
/* 186*/        super(s, throwable, validate(Response.status(i).build(), javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(Response response, Throwable throwable)
            {
/* 199*/        super(throwable, validate(response, javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            public ServerErrorException(String s, Response response, Throwable throwable)
            {
/* 214*/        super(s, throwable, validate(response, javax.ws.rs.core.Response.Status.Family.SERVER_ERROR));
            }

            private static final long serialVersionUID = 0x41a7843049b01914L;
}
