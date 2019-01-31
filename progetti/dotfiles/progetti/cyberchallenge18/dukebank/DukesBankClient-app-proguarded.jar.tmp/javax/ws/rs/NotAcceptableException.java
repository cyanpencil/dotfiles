// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NotAcceptableException.java

package javax.ws.rs;

import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            ClientErrorException

public class NotAcceptableException extends ClientErrorException
{

            public NotAcceptableException()
            {
/*  61*/        super(javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE);
            }

            public NotAcceptableException(String s)
            {
/*  71*/        super(s, javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE);
            }

            public NotAcceptableException(Response response)
            {
/*  82*/        super(validate(response, javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE));
            }

            public NotAcceptableException(String s, Response response)
            {
/*  95*/        super(s, validate(response, javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE));
            }

            public NotAcceptableException(Throwable throwable)
            {
/* 104*/        super(javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE, throwable);
            }

            public NotAcceptableException(String s, Throwable throwable)
            {
/* 115*/        super(s, javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE, throwable);
            }

            public NotAcceptableException(Response response, Throwable throwable)
            {
/* 127*/        super(validate(response, javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE), throwable);
            }

            public NotAcceptableException(String s, Response response, Throwable throwable)
            {
/* 141*/        super(s, validate(response, javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE), throwable);
            }

            private static final long serialVersionUID = 0xeb839cd4658ede4aL;
}
