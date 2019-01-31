// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionUtils.java

package org.glassfish.jersey.internal.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ExceptionUtils
{

            private ExceptionUtils()
            {
            }

            public static String exceptionStackTraceAsString(Throwable throwable)
            {
/*  64*/        StringWriter stringwriter = new StringWriter();
/*  65*/        throwable.printStackTrace(new PrintWriter(stringwriter));
/*  66*/        return stringwriter.toString();
            }

            public static void conditionallyReThrow(Exception exception, boolean flag, Logger logger, String s, Level level)
                throws Exception
            {
/*  82*/        if(flag)
                {
/*  83*/            throw exception;
                } else
                {
/*  86*/            logger.log(level, s, exception);
/*  88*/            return;
                }
            }
}
