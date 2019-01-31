// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BadRequestException.java

package javax.ws.rs;

import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            ClientErrorException

public class BadRequestException extends ClientErrorException
{

            public BadRequestException()
            {
/*  60*/        super(javax.ws.rs.core.Response.Status.BAD_REQUEST);
            }

            public BadRequestException(String s)
            {
/*  70*/        super(s, javax.ws.rs.core.Response.Status.BAD_REQUEST);
            }

            public BadRequestException(Response response)
            {
/*  81*/        super(validate(response, javax.ws.rs.core.Response.Status.BAD_REQUEST));
            }

            public BadRequestException(String s, Response response)
            {
/*  94*/        super(s, validate(response, javax.ws.rs.core.Response.Status.BAD_REQUEST));
            }

            public BadRequestException(Throwable throwable)
            {
/* 103*/        super(javax.ws.rs.core.Response.Status.BAD_REQUEST, throwable);
            }

            public BadRequestException(String s, Throwable throwable)
            {
/* 114*/        super(s, javax.ws.rs.core.Response.Status.BAD_REQUEST, throwable);
            }

            public BadRequestException(Response response, Throwable throwable)
            {
/* 126*/        super(validate(response, javax.ws.rs.core.Response.Status.BAD_REQUEST), throwable);
            }

            public BadRequestException(String s, Response response, Throwable throwable)
            {
/* 140*/        super(s, validate(response, javax.ws.rs.core.Response.Status.BAD_REQUEST), throwable);
            }

            private static final long serialVersionUID = 0x64d1369455e57049L;
}
