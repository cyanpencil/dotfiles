// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InternalServerErrorException.java

package javax.ws.rs;

import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            ServerErrorException

public class InternalServerErrorException extends ServerErrorException
{

            public InternalServerErrorException()
            {
/*  60*/        super(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR);
            }

            public InternalServerErrorException(String s)
            {
/*  70*/        super(s, javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR);
            }

            public InternalServerErrorException(Response response)
            {
/*  81*/        super(validate(response, javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR));
            }

            public InternalServerErrorException(String s, Response response)
            {
/*  94*/        super(s, validate(response, javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR));
            }

            public InternalServerErrorException(Throwable throwable)
            {
/* 103*/        super(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR, throwable);
            }

            public InternalServerErrorException(String s, Throwable throwable)
            {
/* 114*/        super(s, javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR, throwable);
            }

            public InternalServerErrorException(Response response, Throwable throwable)
            {
/* 126*/        super(validate(response, javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR), throwable);
            }

            public InternalServerErrorException(String s, Response response, Throwable throwable)
            {
/* 140*/        super(s, validate(response, javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR), throwable);
            }

            private static final long serialVersionUID = 0xa5938ba46de1f5a3L;
}
