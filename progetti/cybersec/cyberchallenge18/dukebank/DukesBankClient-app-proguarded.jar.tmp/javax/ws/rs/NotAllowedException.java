// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NotAllowedException.java

package javax.ws.rs;

import java.util.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            ClientErrorException

public class NotAllowedException extends ClientErrorException
{

            public transient NotAllowedException(String s, String as[])
            {
/*  68*/        super(validateAllow(createNotAllowedResponse(s, as)));
            }

            public transient NotAllowedException(String s, String s1, String as[])
            {
/*  81*/        super(s, validateAllow(createNotAllowedResponse(s1, as)));
            }

            private static transient Response createNotAllowedResponse(String s, String as[])
            {
/*  85*/        if(s == null)
/*  86*/            throw new NullPointerException("No allowed method specified.");
                Object obj;
/*  89*/        if(as != null && as.length > 0)
                {
/*  90*/            ((Set) (obj = new HashSet(as.length + 1))).add(s);
/*  92*/            Collections.addAll(((java.util.Collection) (obj)), as);
                } else
                {
/*  94*/            obj = Collections.singleton(s);
                }
/*  97*/        return Response.status(javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED).allow(((Set) (obj))).build();
            }

            public NotAllowedException(Response response)
            {
/* 114*/        super(validate(response, javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED));
            }

            public NotAllowedException(String s, Response response)
            {
/* 133*/        super(s, validate(response, javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED));
            }

            public transient NotAllowedException(Throwable throwable, String as[])
            {
/* 144*/        super(validateAllow(Response.status(javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED).allow(as).build()), throwable);
            }

            public transient NotAllowedException(String s, Throwable throwable, String as[])
            {
/* 157*/        super(s, validateAllow(Response.status(javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED).allow(as).build()), throwable);
            }

            public NotAllowedException(Response response, Throwable throwable)
            {
/* 170*/        super(validateAllow(validate(response, javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED)), throwable);
            }

            public NotAllowedException(String s, Response response, Throwable throwable)
            {
/* 185*/        super(s, validateAllow(validate(response, javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED)), throwable);
            }

            private static Response validateAllow(Response response)
            {
/* 189*/        if(!response.getHeaders().containsKey("Allow"))
/* 190*/            throw new IllegalArgumentException("Response does not contain required 'Allow' HTTP header.");
/* 193*/        else
/* 193*/            return response;
            }

            private static final long serialVersionUID = 0xf7db5a499497d7f9L;
}
