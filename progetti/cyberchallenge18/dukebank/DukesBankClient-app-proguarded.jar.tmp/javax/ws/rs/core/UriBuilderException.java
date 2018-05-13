// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriBuilderException.java

package javax.ws.rs.core;


public class UriBuilderException extends RuntimeException
{

            public UriBuilderException()
            {
            }

            public UriBuilderException(String s)
            {
/*  67*/        super(s);
            }

            public UriBuilderException(String s, Throwable throwable)
            {
/*  78*/        super(s, throwable);
            }

            public UriBuilderException(Throwable throwable)
            {
/*  90*/        super(throwable);
            }

            private static final long serialVersionUID = 0xd454dafced41ba9L;
}
