// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HK2Exception.java

package org.glassfish.hk2.api;


public class HK2Exception extends Exception
{

            public HK2Exception()
            {
            }

            public HK2Exception(String s)
            {
/*  65*/        super(s);
            }

            public HK2Exception(Throwable throwable)
            {
/*  72*/        super(throwable);
            }

            public HK2Exception(String s, Throwable throwable)
            {
/*  80*/        super(s, throwable);
            }

            private static final long serialVersionUID = 0x9fc5c15de9d75ae6L;
}
