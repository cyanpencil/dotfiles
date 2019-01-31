// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WebApplicationException.java

package javax.ws.rs;

import javax.ws.rs.core.Response;

public class WebApplicationException extends RuntimeException
{

            public WebApplicationException()
            {
/*  66*/        this(((Throwable) (null)), javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR);
            }

            public WebApplicationException(String s)
            {
/*  77*/        this(s, null, javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR);
            }

            public WebApplicationException(Response response1)
            {
/*  90*/        this(((Throwable) (null)), response1);
            }

            public WebApplicationException(String s, Response response1)
            {
/* 104*/        this(s, null, response1);
            }

            public WebApplicationException(int i)
            {
/* 114*/        this(((Throwable) (null)), i);
            }

            public WebApplicationException(String s, int i)
            {
/* 126*/        this(s, null, i);
            }

            public WebApplicationException(javax.ws.rs.core.Response.Status status)
            {
/* 137*/        this(((Throwable) (null)), status);
            }

            public WebApplicationException(String s, javax.ws.rs.core.Response.Status status)
            {
/* 150*/        this(s, null, status);
            }

            public WebApplicationException(Throwable throwable)
            {
/* 160*/        this(throwable, javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR);
            }

            public WebApplicationException(String s, Throwable throwable)
            {
/* 172*/        this(s, throwable, javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR);
            }

            public WebApplicationException(Throwable throwable, Response response1)
            {
/* 185*/        this(computeExceptionMessage(response1), throwable, response1);
            }

            public WebApplicationException(String s, Throwable throwable, Response response1)
            {
/* 200*/        super(s, throwable);
/* 201*/        if(response1 == null)
                {
/* 202*/            response = Response.serverError().build();
/* 202*/            return;
                } else
                {
/* 204*/            response = response1;
/* 206*/            return;
                }
            }

            private static String computeExceptionMessage(Response response1)
            {
/* 210*/        if(response1 != null)
/* 211*/            response1 = response1.getStatusInfo();
/* 213*/        else
/* 213*/            response1 = javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
/* 215*/        return (new StringBuilder("HTTP ")).append(response1.getStatusCode()).append(' ').append(response1.getReasonPhrase()).toString();
            }

            public WebApplicationException(Throwable throwable, int i)
            {
/* 226*/        this(throwable, Response.status(i).build());
            }

            public WebApplicationException(String s, Throwable throwable, int i)
            {
/* 239*/        this(s, throwable, Response.status(i).build());
            }

            public WebApplicationException(Throwable throwable, javax.ws.rs.core.Response.Status status)
                throws IllegalArgumentException
            {
/* 252*/        this(throwable, Response.status(status).build());
            }

            public WebApplicationException(String s, Throwable throwable, javax.ws.rs.core.Response.Status status)
                throws IllegalArgumentException
            {
/* 266*/        this(s, throwable, Response.status(status).build());
            }

            public Response getResponse()
            {
/* 275*/        return response;
            }

            static Response validate(Response response1, javax.ws.rs.core.Response.Status status)
            {
/* 289*/        if(status.getStatusCode() != response1.getStatus())
/* 290*/            throw new IllegalArgumentException(String.format("Invalid response status code. Expected [%d], was [%d].", new Object[] {
/* 290*/                Integer.valueOf(status.getStatusCode()), Integer.valueOf(response1.getStatus())
                    }));
/* 293*/        else
/* 293*/            return response1;
            }

            static Response validate(Response response1, javax.ws.rs.core.Response.Status.Family family)
            {
/* 307*/        if(response1.getStatusInfo().getFamily() != family)
/* 308*/            throw new IllegalArgumentException(String.format("Status code of the supplied response [%d] is not from the required status code family \"%s\".", new Object[] {
/* 308*/                Integer.valueOf(response1.getStatus()), family
                    }));
/* 312*/        else
/* 312*/            return response1;
            }

            private static final long serialVersionUID = 0x72d30c3db54d33eaL;
            private final Response response;
}
