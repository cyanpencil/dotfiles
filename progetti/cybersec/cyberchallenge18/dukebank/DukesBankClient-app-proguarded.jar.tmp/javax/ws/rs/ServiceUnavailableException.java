// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceUnavailableException.java

package javax.ws.rs;

import java.util.Date;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.RuntimeDelegate;

// Referenced classes of package javax.ws.rs:
//            ServerErrorException

public class ServiceUnavailableException extends ServerErrorException
{

            public ServiceUnavailableException()
            {
/*  66*/        super(Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).build());
            }

            public ServiceUnavailableException(String s)
            {
/*  77*/        super(s, Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).build());
            }

            public ServiceUnavailableException(Long long1)
            {
/*  87*/        super(Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).header("Retry-After", long1).build());
            }

            public ServiceUnavailableException(String s, Long long1)
            {
/*  99*/        super(s, Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).header("Retry-After", long1).build());
            }

            public ServiceUnavailableException(Date date)
            {
/* 109*/        super(Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).header("Retry-After", date).build());
            }

            public ServiceUnavailableException(String s, Date date)
            {
/* 121*/        super(s, Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).header("Retry-After", date).build());
            }

            public ServiceUnavailableException(Response response)
            {
/* 132*/        super(validate(response, javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE));
            }

            public ServiceUnavailableException(String s, Response response)
            {
/* 145*/        super(s, validate(response, javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE));
            }

            public ServiceUnavailableException(Date date, Throwable throwable)
            {
/* 157*/        super(Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).header("Retry-After", date).build(), throwable);
            }

            public ServiceUnavailableException(String s, Date date, Throwable throwable)
            {
/* 171*/        super(s, Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).header("Retry-After", date).build(), throwable);
            }

            public ServiceUnavailableException(Long long1, Throwable throwable)
            {
/* 183*/        super(Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).header("Retry-After", long1).build(), throwable);
            }

            public ServiceUnavailableException(String s, Long long1, Throwable throwable)
            {
/* 197*/        super(s, Response.status(javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE).header("Retry-After", long1).build(), throwable);
            }

            public ServiceUnavailableException(Response response, Throwable throwable)
            {
/* 209*/        super(validate(response, javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE), throwable);
            }

            public ServiceUnavailableException(String s, Response response, Throwable throwable)
            {
/* 223*/        super(s, validate(response, javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE), throwable);
            }

            public boolean hasRetryAfter()
            {
/* 234*/        return getResponse().getHeaders().containsKey("Retry-After");
            }

            public Date getRetryTime(Date date)
            {
                String s;
/* 248*/        if((s = getResponse().getHeaderString("Retry-After")) == null)
/* 250*/            return null;
/* 254*/        Object obj = Long.valueOf(Long.parseLong(s));
/* 255*/        return new Date(date.getTime() + ((Long) (obj)).longValue() * 1000L);
/* 256*/        JVM INSTR pop ;
/* 260*/        return (Date)((javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate) (obj = RuntimeDelegate.getInstance().createHeaderDelegate(java/util/Date))).fromString(s);
            }

            private static final long serialVersionUID = 0x35072949c25eb299L;
}
