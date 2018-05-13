// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExtendedLogger.java

package org.glassfish.jersey.internal.util;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.*;

public final class ExtendedLogger
{

            public ExtendedLogger(Logger logger1, Level level)
            {
/*  69*/        logger = logger1;
/*  70*/        debugLevel = level;
            }

            public final boolean isDebugLoggable()
            {
/*  80*/        return logger.isLoggable(debugLevel);
            }

            public final Level getDebugLevel()
            {
/*  89*/        return debugLevel;
            }

            public final void debugLog(String s)
            {
/* 100*/        debugLog(s, null);
            }

            public final transient void debugLog(String s, Object aobj[])
            {
/* 113*/        if(logger.isLoggable(debugLevel))
                {
/* 115*/            if(aobj == null || aobj.length == 0)
/* 116*/                aobj = new Object[1];
/* 118*/            else
/* 118*/                aobj = Arrays.copyOf(aobj, aobj.length + 1);
/* 120*/            aobj[aobj.length - 1] = Thread.currentThread().getName();
/* 122*/            logger.log(debugLevel, (new StringBuilder("[DEBUG] ")).append(s).append(" on thread {").append(aobj.length - 1).append('}').toString(), aobj);
                }
            }

            public final String toString()
            {
/* 129*/        return (new StringBuilder("ExtendedLogger{logger=")).append(logger).append(", debugLevel=").append(debugLevel).append('}').toString();
            }

            public final boolean equals(Object obj)
            {
/* 134*/        if(obj == null)
/* 135*/            return false;
/* 137*/        if(getClass() != obj.getClass())
/* 138*/            return false;
/* 140*/        obj = (ExtendedLogger)obj;
/* 141*/        if(logger != ((ExtendedLogger) (obj)).logger && (logger == null || !logger.equals(((ExtendedLogger) (obj)).logger)))
/* 142*/            return false;
/* 144*/        return debugLevel == ((ExtendedLogger) (obj)).debugLevel || debugLevel != null && debugLevel.equals(((ExtendedLogger) (obj)).debugLevel);
            }

            public final int hashCode()
            {
/* 153*/        int i = 51 + (logger == null ? 0 : logger.hashCode());
/* 154*/        return i = i * 17 + (debugLevel == null ? 0 : debugLevel.hashCode());
            }

            public final void warning(String s)
            {
/* 159*/        logger.warning(s);
            }

            public final void throwing(String s, String s1, Throwable throwable)
            {
/* 163*/        logger.throwing(s, s1, throwable);
            }

            public final void severe(String s)
            {
/* 167*/        logger.severe(s);
            }

            public final void setUseParentHandlers(boolean flag)
            {
/* 171*/        logger.setUseParentHandlers(flag);
            }

            public final void setParent(Logger logger1)
            {
/* 175*/        logger.setParent(logger1);
            }

            public final void setLevel(Level level)
                throws SecurityException
            {
/* 179*/        logger.setLevel(level);
            }

            public final void setFilter(Filter filter)
                throws SecurityException
            {
/* 183*/        logger.setFilter(filter);
            }

            public final void removeHandler(Handler handler)
                throws SecurityException
            {
/* 187*/        logger.removeHandler(handler);
            }

            public final void logrb(Level level, String s, String s1, String s2, String s3, Throwable throwable)
            {
/* 196*/        logger.logrb(level, s, s1, s2, s3, throwable);
            }

            public final void logrb(Level level, String s, String s1, String s2, String s3, Object aobj[])
            {
/* 205*/        logger.logrb(level, s, s1, s2, s3, aobj);
            }

            public final void logrb(Level level, String s, String s1, String s2, String s3, Object obj)
            {
/* 214*/        logger.logrb(level, s, s1, s2, s3, obj);
            }

            public final void logrb(Level level, String s, String s1, String s2, String s3)
            {
/* 222*/        logger.logrb(level, s, s1, s2, s3);
            }

            public final void logp(Level level, String s, String s1, String s2, Throwable throwable)
            {
/* 230*/        logger.logp(level, s, s1, s2, throwable);
            }

            public final void logp(Level level, String s, String s1, String s2, Object aobj[])
            {
/* 238*/        logger.logp(level, s, s1, s2, aobj);
            }

            public final void logp(Level level, String s, String s1, String s2, Object obj)
            {
/* 246*/        logger.logp(level, s, s1, s2, obj);
            }

            public final void logp(Level level, String s, String s1, String s2)
            {
/* 250*/        logger.logp(level, s, s1, s2);
            }

            public final void log(Level level, String s, Throwable throwable)
            {
/* 254*/        logger.log(level, s, throwable);
            }

            public final void log(Level level, String s, Object aobj[])
            {
/* 258*/        logger.log(level, s, aobj);
            }

            public final void log(Level level, String s, Object obj)
            {
/* 262*/        logger.log(level, s, obj);
            }

            public final void log(Level level, String s)
            {
/* 266*/        logger.log(level, s);
            }

            public final void log(LogRecord logrecord)
            {
/* 270*/        logger.log(logrecord);
            }

            public final boolean isLoggable(Level level)
            {
/* 274*/        return logger.isLoggable(level);
            }

            public final void info(String s)
            {
/* 278*/        logger.info(s);
            }

            public final boolean getUseParentHandlers()
            {
/* 282*/        return logger.getUseParentHandlers();
            }

            public final String getResourceBundleName()
            {
/* 286*/        return logger.getResourceBundleName();
            }

            public final ResourceBundle getResourceBundle()
            {
/* 290*/        return logger.getResourceBundle();
            }

            public final Logger getParent()
            {
/* 294*/        return logger.getParent();
            }

            public final String getName()
            {
/* 298*/        return logger.getName();
            }

            public final Level getLevel()
            {
/* 302*/        return logger.getLevel();
            }

            public final Handler[] getHandlers()
            {
/* 306*/        return logger.getHandlers();
            }

            public final Filter getFilter()
            {
/* 310*/        return logger.getFilter();
            }

            public final void finest(String s)
            {
/* 314*/        logger.finest(s);
            }

            public final void finer(String s)
            {
/* 318*/        logger.finer(s);
            }

            public final void fine(String s)
            {
/* 322*/        logger.fine(s);
            }

            public final void exiting(String s, String s1, Object obj)
            {
/* 326*/        logger.exiting(s, s1, obj);
            }

            public final void exiting(String s, String s1)
            {
/* 330*/        logger.exiting(s, s1);
            }

            public final void entering(String s, String s1, Object aobj[])
            {
/* 334*/        logger.entering(s, s1, aobj);
            }

            public final void entering(String s, String s1, Object obj)
            {
/* 338*/        logger.entering(s, s1, obj);
            }

            public final void entering(String s, String s1)
            {
/* 342*/        logger.entering(s, s1);
            }

            public final void config(String s)
            {
/* 346*/        logger.config(s);
            }

            public final void addHandler(Handler handler)
                throws SecurityException
            {
/* 350*/        logger.addHandler(handler);
            }

            private final Logger logger;
            private final Level debugLevel;
}
