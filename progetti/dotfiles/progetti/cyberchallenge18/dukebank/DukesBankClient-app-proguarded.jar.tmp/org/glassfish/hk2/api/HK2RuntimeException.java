// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HK2RuntimeException.java

package org.glassfish.hk2.api;


public class HK2RuntimeException extends RuntimeException
{

            public HK2RuntimeException()
            {
            }

            public HK2RuntimeException(String s)
            {
/*  65*/        super(s);
            }

            public HK2RuntimeException(Throwable throwable)
            {
/*  72*/        super(throwable);
            }

            public HK2RuntimeException(String s, Throwable throwable)
            {
/*  80*/        super(s, throwable);
            }

            private static final long serialVersionUID = 0x3d5cf45f55de9298L;
}
