// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyProcessingUncaughtExceptionHandler.java

package org.glassfish.jersey.process;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.LocalizationMessages;

public class JerseyProcessingUncaughtExceptionHandler
    implements Thread.UncaughtExceptionHandler
{

            public JerseyProcessingUncaughtExceptionHandler()
            {
/*  65*/        this(Level.WARNING);
            }

            public JerseyProcessingUncaughtExceptionHandler(Level level)
            {
/*  77*/        logLevel = level;
            }

            public void uncaughtException(Thread thread, Throwable throwable)
            {
/*  82*/        LOGGER.log(logLevel, LocalizationMessages.UNHANDLED_EXCEPTION_DETECTED(thread.getName()), throwable);
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/process/JerseyProcessingUncaughtExceptionHandler.getName());
            private final Level logLevel;

}
