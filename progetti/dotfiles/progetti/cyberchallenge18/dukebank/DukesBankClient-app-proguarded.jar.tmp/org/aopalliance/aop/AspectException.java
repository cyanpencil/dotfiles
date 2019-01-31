// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AspectException.java

package org.aopalliance.aop;

import java.io.*;

public class AspectException extends RuntimeException
{

            public AspectException(String s)
            {
/*  28*/        super(s);
/*  29*/        message = s;
/*  30*/        stackTrace = s;
            }

            public AspectException(String s, Throwable throwable)
            {
/*  39*/        super((new StringBuilder()).append(s).append("; nested exception is ").append(throwable.getMessage()).toString());
/*  40*/        t = throwable;
/*  41*/        s = new StringWriter();
/*  42*/        throwable.printStackTrace(new PrintWriter(s));
/*  43*/        stackTrace = s.toString();
            }

            public Throwable getCause()
            {
/*  52*/        return t;
            }

            public String toString()
            {
/*  56*/        return getMessage();
            }

            public String getMessage()
            {
/*  60*/        return message;
            }

            public void printStackTrace()
            {
/*  64*/        System.err.print(stackTrace);
            }

            public void printStackTrace(PrintStream printstream)
            {
/*  68*/        printStackTrace(new PrintWriter(printstream));
            }

            public void printStackTrace(PrintWriter printwriter)
            {
/*  72*/        printwriter.print(stackTrace);
            }

            private String message;
            private String stackTrace;
            private Throwable t;
}
