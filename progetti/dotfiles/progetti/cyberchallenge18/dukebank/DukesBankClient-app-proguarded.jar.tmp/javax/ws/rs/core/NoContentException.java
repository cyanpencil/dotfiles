// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NoContentException.java

package javax.ws.rs.core;

import java.io.IOException;

public class NoContentException extends IOException
{

            public NoContentException(String s)
            {
/*  68*/        super(s);
            }

            public NoContentException(String s, Throwable throwable)
            {
/*  79*/        super(s, throwable);
            }

            public NoContentException(Throwable throwable)
            {
/*  88*/        super(throwable);
            }

            private static final long serialVersionUID = 0xd5387bdc135926a3L;
}
