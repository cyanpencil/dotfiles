// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NotAuthorizedException.java

package javax.ws.rs;

import java.util.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            ClientErrorException

public class NotAuthorizedException extends ClientErrorException
{

            public transient NotAuthorizedException(Object obj, Object aobj[])
            {
/*  86*/        super(createUnauthorizedResponse(obj, aobj));
/*  87*/        challenges = cacheChallenges(obj, aobj);
            }

            public transient NotAuthorizedException(String s, Object obj, Object aobj[])
            {
/* 102*/        super(s, createUnauthorizedResponse(obj, aobj));
/* 103*/        challenges = cacheChallenges(obj, aobj);
            }

            public NotAuthorizedException(Response response)
            {
/* 114*/        super(validate(response, javax.ws.rs.core.Response.Status.UNAUTHORIZED));
            }

            public NotAuthorizedException(String s, Response response)
            {
/* 127*/        super(s, validate(response, javax.ws.rs.core.Response.Status.UNAUTHORIZED));
            }

            public transient NotAuthorizedException(Throwable throwable, Object obj, Object aobj[])
            {
/* 139*/        super(createUnauthorizedResponse(obj, aobj), throwable);
/* 140*/        challenges = cacheChallenges(obj, aobj);
            }

            public transient NotAuthorizedException(String s, Throwable throwable, Object obj, Object aobj[])
            {
/* 154*/        super(s, createUnauthorizedResponse(obj, aobj), throwable);
/* 155*/        challenges = cacheChallenges(obj, aobj);
            }

            public NotAuthorizedException(Response response, Throwable throwable)
            {
/* 167*/        super(validate(response, javax.ws.rs.core.Response.Status.UNAUTHORIZED), throwable);
            }

            public NotAuthorizedException(String s, Response response, Throwable throwable)
            {
/* 181*/        super(s, validate(response, javax.ws.rs.core.Response.Status.UNAUTHORIZED), throwable);
            }

            public List getChallenges()
            {
/* 192*/        if(challenges == null)
/* 193*/            challenges = (List)getResponse().getHeaders().get("WWW-Authenticate");
/* 195*/        return challenges;
            }

            private static Response createUnauthorizedResponse(Object obj, Object aobj[])
            {
/* 199*/        if(obj == null)
/* 200*/            throw new NullPointerException("Primary challenge parameter must not be null.");
/* 203*/        obj = Response.status(javax.ws.rs.core.Response.Status.UNAUTHORIZED).header("WWW-Authenticate", obj);
/* 206*/        if(aobj != null)
                {
/* 207*/            int i = (aobj = aobj).length;
/* 207*/            for(int j = 0; j < i; j++)
                    {
/* 207*/                Object obj1 = aobj[j];
/* 208*/                ((javax.ws.rs.core.Response.ResponseBuilder) (obj)).header("WWW-Authenticate", obj1);
                    }

                }
/* 212*/        return ((javax.ws.rs.core.Response.ResponseBuilder) (obj)).build();
            }

            private static List cacheChallenges(Object obj, Object aobj[])
            {
                ArrayList arraylist;
/* 216*/        (arraylist = new ArrayList(1 + (aobj != null ? aobj.length : 0))).add(obj);
/* 218*/        if(aobj != null)
/* 219*/            arraylist.addAll(Arrays.asList(aobj));
/* 221*/        return Collections.unmodifiableList(arraylist);
            }

            private static final long serialVersionUID = 0xd4337da8a4e8291aL;
            private transient List challenges;
}
