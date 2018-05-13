// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProcessingException.java

package javax.ws.rs;


public class ProcessingException extends RuntimeException
{

            public ProcessingException(Throwable throwable)
            {
/*  86*/        super(throwable);
            }

            public ProcessingException(String s, Throwable throwable)
            {
/* 103*/        super(s, throwable);
            }

            public ProcessingException(String s)
            {
/* 115*/        super(s);
            }

            private static final long serialVersionUID = 0xc54361e2dc08f53eL;
}
