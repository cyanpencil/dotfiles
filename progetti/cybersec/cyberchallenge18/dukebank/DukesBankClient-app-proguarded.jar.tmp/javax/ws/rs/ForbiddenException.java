// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForbiddenException.java

package javax.ws.rs;

import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            ClientErrorException

public class ForbiddenException extends ClientErrorException
{

            public ForbiddenException()
            {
/*  60*/        super(javax.ws.rs.core.Response.Status.FORBIDDEN);
            }

            public ForbiddenException(String s)
            {
/*  70*/        super(s, javax.ws.rs.core.Response.Status.FORBIDDEN);
            }

            public ForbiddenException(Response response)
            {
/*  81*/        super(validate(response, javax.ws.rs.core.Response.Status.FORBIDDEN));
            }

            public ForbiddenException(String s, Response response)
            {
/*  94*/        super(s, validate(response, javax.ws.rs.core.Response.Status.FORBIDDEN));
            }

            public ForbiddenException(Throwable throwable)
            {
/* 103*/        super(javax.ws.rs.core.Response.Status.FORBIDDEN, throwable);
            }

            public ForbiddenException(String s, Throwable throwable)
            {
/* 114*/        super(s, javax.ws.rs.core.Response.Status.FORBIDDEN, throwable);
            }

            public ForbiddenException(Response response, Throwable throwable)
            {
/* 126*/        super(validate(response, javax.ws.rs.core.Response.Status.FORBIDDEN), throwable);
            }

            public ForbiddenException(String s, Response response, Throwable throwable)
            {
/* 140*/        super(s, validate(response, javax.ws.rs.core.Response.Status.FORBIDDEN), throwable);
            }

            private static final long serialVersionUID = 0xd9f9674172f31f7bL;
}
