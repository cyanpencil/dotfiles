// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Logger.java

package org.glassfish.hk2.utilities.reflection;

import java.io.PrintStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;

public class Logger
{

            private Logger()
            {
            }

            public static Logger getLogger()
            {
/*  74*/        return INSTANCE;
            }

            public void debug(String s)
            {
/*  83*/        jdkLogger.finer(s);
/*  84*/        if(STDOUT_DEBUG)
/*  85*/            System.out.println((new StringBuilder("HK2DEBUG: ")).append(s).toString());
            }

            public void debug(String s, Throwable throwable)
            {
/*  95*/        jdkLogger.log(Level.FINER, s, throwable);
/*  96*/        if(STDOUT_DEBUG)
                {
/*  97*/            System.out.println((new StringBuilder("HK2DEBUG: ")).append(s).toString());
/*  98*/            printThrowable(throwable);
                }
            }

            public void warning(String s)
            {
/* 108*/        jdkLogger.warning(s);
/* 109*/        if(STDOUT_DEBUG)
/* 110*/            System.out.println((new StringBuilder("HK2DEBUG (Warning): ")).append(s).toString());
            }

            public void warning(String s, Throwable throwable)
            {
/* 120*/        jdkLogger.log(Level.WARNING, s, throwable);
/* 121*/        if(STDOUT_DEBUG)
                {
/* 122*/            System.out.println((new StringBuilder("HK2DEBUG (Warning): ")).append(s).toString());
/* 123*/            printThrowable(throwable);
                }
            }

            public static void printThrowable(Throwable throwable)
            {
/* 133*/        int i = 0;
/* 134*/        for(throwable = throwable; throwable != null; throwable = throwable.getCause())
                {
/* 137*/            System.out.println((new StringBuilder("HK2DEBUG: Throwable[")).append(i++).append("] message is ").append(throwable.getMessage()).toString());
/* 138*/            throwable.printStackTrace(System.out);
                }

            }

            public void debug(String s, String s1, Throwable throwable)
            {
/* 152*/        jdkLogger.throwing(s, s1, throwable);
/* 153*/        if(STDOUT_DEBUG)
                {
/* 154*/            System.out.println((new StringBuilder("HK2DEBUG: className=")).append(s).append(" methodName=").append(s1).toString());
/* 155*/            printThrowable(throwable);
                }
            }

            private static final Logger INSTANCE = new Logger();
            private static final String HK2_LOGGER_NAME = "org.jvnet.hk2.logger";
            private static final boolean STDOUT_DEBUG = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

                public final Boolean run()
                {
/*  57*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.logger.debugToStdout", "false")));
                }

                public final volatile Object run()
                {
/*  54*/            return run();
                }

    })).booleanValue();
            private final java.util.logging.Logger jdkLogger = java.util.logging.Logger.getLogger("org.jvnet.hk2.logger");

}
