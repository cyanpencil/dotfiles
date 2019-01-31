// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NotFoundException.java

package javax.ws.rs;

import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            ClientErrorException

public class NotFoundException extends ClientErrorException
{

            public NotFoundException()
            {
/*  60*/        super(javax.ws.rs.core.Response.Status.NOT_FOUND);
            }

            public NotFoundException(String s)
            {
/*  70*/        super(s, javax.ws.rs.core.Response.Status.NOT_FOUND);
            }

            public NotFoundException(Response response)
            {
/*  81*/        super(validate(response, javax.ws.rs.core.Response.Status.NOT_FOUND));
            }

            public NotFoundException(String s, Response response)
            {
/*  94*/        super(s, validate(response, javax.ws.rs.core.Response.Status.NOT_FOUND));
            }

            public NotFoundException(Throwable throwable)
            {
/* 103*/        super(javax.ws.rs.core.Response.Status.NOT_FOUND, throwable);
            }

            public NotFoundException(String s, Throwable throwable)
            {
/* 114*/        super(s, javax.ws.rs.core.Response.Status.NOT_FOUND, throwable);
            }

            public NotFoundException(Response response, Throwable throwable)
            {
/* 126*/        super(validate(response, javax.ws.rs.core.Response.Status.NOT_FOUND), throwable);
            }

            public NotFoundException(String s, Response response, Throwable throwable)
            {
/* 140*/        super(s, validate(response, javax.ws.rs.core.Response.Status.NOT_FOUND), throwable);
            }

            private static final long serialVersionUID = 0xa1576a679538b99cL;
}
