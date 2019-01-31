// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NotSupportedException.java

package javax.ws.rs;

import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs:
//            ClientErrorException

public class NotSupportedException extends ClientErrorException
{

            public NotSupportedException()
            {
/*  60*/        super(javax.ws.rs.core.Response.Status.UNSUPPORTED_MEDIA_TYPE);
            }

            public NotSupportedException(String s)
            {
/*  70*/        super(s, javax.ws.rs.core.Response.Status.UNSUPPORTED_MEDIA_TYPE);
            }

            public NotSupportedException(Response response)
            {
/*  81*/        super(validate(response, javax.ws.rs.core.Response.Status.UNSUPPORTED_MEDIA_TYPE));
            }

            public NotSupportedException(String s, Response response)
            {
/*  94*/        super(s, validate(response, javax.ws.rs.core.Response.Status.UNSUPPORTED_MEDIA_TYPE));
            }

            public NotSupportedException(Throwable throwable)
            {
/* 103*/        super(javax.ws.rs.core.Response.Status.UNSUPPORTED_MEDIA_TYPE, throwable);
            }

            public NotSupportedException(String s, Throwable throwable)
            {
/* 114*/        super(s, javax.ws.rs.core.Response.Status.UNSUPPORTED_MEDIA_TYPE, throwable);
            }

            public NotSupportedException(Response response, Throwable throwable)
            {
/* 126*/        super(validate(response, javax.ws.rs.core.Response.Status.UNSUPPORTED_MEDIA_TYPE), throwable);
            }

            public NotSupportedException(String s, Response response, Throwable throwable)
            {
/* 140*/        super(s, validate(response, javax.ws.rs.core.Response.Status.UNSUPPORTED_MEDIA_TYPE), throwable);
            }

            private static final long serialVersionUID = 0x8d000084655d7628L;
}
