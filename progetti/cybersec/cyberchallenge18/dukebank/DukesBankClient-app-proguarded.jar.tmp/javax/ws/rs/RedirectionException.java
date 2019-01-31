// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RedirectionException.java

package javax.ws.rs;

import java.net.URI;
import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            WebApplicationException

public class RedirectionException extends WebApplicationException
{

            public RedirectionException(javax.ws.rs.core.Response.Status status, URI uri)
            {
/*  67*/        super(null, validate(Response.status(status).location(uri).build(), javax.ws.rs.core.Response.Status.Family.REDIRECTION));
            }

            public RedirectionException(String s, javax.ws.rs.core.Response.Status status, URI uri)
            {
/*  82*/        super(s, null, validate(Response.status(status).location(uri).build(), javax.ws.rs.core.Response.Status.Family.REDIRECTION));
            }

            public RedirectionException(int i, URI uri)
            {
/*  95*/        super(null, validate(Response.status(i).location(uri).build(), javax.ws.rs.core.Response.Status.Family.REDIRECTION));
            }

            public RedirectionException(String s, int i, URI uri)
            {
/* 110*/        super(s, null, validate(Response.status(i).location(uri).build(), javax.ws.rs.core.Response.Status.Family.REDIRECTION));
            }

            public RedirectionException(Response response)
            {
/* 122*/        super(null, validate(response, javax.ws.rs.core.Response.Status.Family.REDIRECTION));
            }

            public RedirectionException(String s, Response response)
            {
/* 136*/        super(s, null, validate(response, javax.ws.rs.core.Response.Status.Family.REDIRECTION));
            }

            public URI getLocation()
            {
/* 145*/        return getResponse().getLocation();
            }

            private static final long serialVersionUID = 0xdc22a1bbc6caf264L;
}
