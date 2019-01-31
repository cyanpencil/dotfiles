// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BadHttpRequest.java

package javassist.tools.web;


public class BadHttpRequest extends Exception
{

            public BadHttpRequest()
            {
/*  25*/        e = null;
            }

            public BadHttpRequest(Exception exception)
            {
/*  27*/        e = exception;
            }

            public String toString()
            {
/*  30*/        if(e == null)
/*  31*/            return super.toString();
/*  33*/        else
/*  33*/            return e.toString();
            }

            private Exception e;
}
