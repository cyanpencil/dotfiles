// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TracingLogger.java

package org.glassfish.jersey.message.internal;

import java.lang.reflect.Method;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.internal.PropertiesDelegate;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            TracingInfo

public abstract class TracingLogger
{
    public static interface Event
    {

        public abstract String name();

        public abstract String category();

        public abstract Level level();

        public abstract String messageFormat();
    }

    public static final class Level extends Enum
    {

                public static Level[] values()
                {
/* 389*/            return (Level[])$VALUES.clone();
                }

                public static Level valueOf(String s)
                {
/* 389*/            return (Level)Enum.valueOf(org/glassfish/jersey/message/internal/TracingLogger$Level, s);
                }

                public static final Level SUMMARY;
                public static final Level TRACE;
                public static final Level VERBOSE;
                private static final Level $VALUES[];

                static 
                {
/* 393*/            SUMMARY = new Level("SUMMARY", 0);
/* 397*/            TRACE = new Level("TRACE", 1);
/* 401*/            VERBOSE = new Level("VERBOSE", 2);
/* 389*/            $VALUES = (new Level[] {
/* 389*/                SUMMARY, TRACE, VERBOSE
                    });
                }

                private Level(String s, int i)
                {
/* 389*/            super(s, i);
                }
    }

    static final class TracingLoggerImpl extends TracingLogger
    {

                public final boolean isLogEnabled(Event event)
                {
/* 235*/            return isEnabled(event.level());
                }

                public final transient void log(Event event, Object aobj[])
                {
/* 240*/            logDuration(event, -1L, aobj);
                }

                public final transient void logDuration(Event event, long l, Object aobj[])
                {
/* 245*/            if(isEnabled(event.level()))
                    {
                        long l1;
/* 247*/                if(l == -1L)
/* 248*/                    l1 = -1L;
/* 250*/                else
/* 250*/                    l1 = System.nanoTime();
/* 252*/                long l2 = 0L;
/* 253*/                if(l != -1L && l1 != -1L)
/* 254*/                    l2 = l1 - l;
/* 256*/                logImpl(event, l2, aobj);
                    }
                }

                public final long timestamp(Event event)
                {
/* 262*/            if(isEnabled(event.level()))
/* 263*/                return System.nanoTime();
/* 265*/            else
/* 265*/                return -1L;
                }

                public final void flush(MultivaluedMap multivaluedmap)
                {
/* 270*/            String as[] = tracingInfo.getMessages();
/* 271*/            for(int i = 0; i < as.length; i++)
/* 272*/                multivaluedmap.putSingle(String.format("X-Jersey-Tracing-%03d", new Object[] {
/* 272*/                    Integer.valueOf(i)
                        }), as[i]);

                }

                private transient void logImpl(Event event, long l, Object aobj[])
                {
/* 289*/            if(isEnabled(event.level()))
                    {
/* 290*/                String as[] = new String[aobj.length];
/* 291*/                for(int i = 0; i < aobj.length; i++)
/* 292*/                    as[i] = formatInstance(aobj[i]);

/* 294*/                TracingInfo.Message message = new TracingInfo.Message(event, l, as);
/* 295*/                tracingInfo.addMessage(message);
                static class _cls2
                {

                            static final int $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level[];

                            static 
                            {
/* 298*/                        $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level = new int[Level.values().length];
/* 298*/                        try
                                {
/* 298*/                            $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level[Level.SUMMARY.ordinal()] = 1;
                                }
/* 298*/                        catch(NoSuchFieldError _ex) { }
/* 298*/                        try
                                {
/* 298*/                            $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level[Level.TRACE.ordinal()] = 2;
                                }
/* 298*/                        catch(NoSuchFieldError _ex) { }
/* 298*/                        try
                                {
/* 298*/                            $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level[Level.VERBOSE.ordinal()] = 3;
                                }
/* 298*/                        catch(NoSuchFieldError _ex) { }
                            }
                }

/* 298*/                switch(_cls2..SwitchMap.org.glassfish.jersey.message.internal.TracingLogger.Level[event.level().ordinal()])
                        {
/* 300*/                case 1: // '\001'
/* 300*/                    aobj = java.util.logging.Level.FINE;
                            break;

/* 303*/                case 2: // '\002'
/* 303*/                    aobj = java.util.logging.Level.FINER;
                            break;

/* 306*/                case 3: // '\003'
/* 306*/                    aobj = java.util.logging.Level.FINEST;
                            break;

/* 309*/                default:
/* 309*/                    aobj = java.util.logging.Level.OFF;
                            break;
                        }
/* 311*/                if(logger.isLoggable(((java.util.logging.Level) (aobj))))
/* 312*/                    logger.log(((java.util.logging.Level) (aobj)), (new StringBuilder()).append(event.name()).append(' ').append(message.toString()).append(" [").append(TracingInfo.formatDuration(l)).append(" ms]").toString());
                    }
                }

                private boolean isEnabled(Level level)
                {
/* 319*/            return threshold.ordinal() >= level.ordinal();
                }

                private static String formatInstance(Object obj)
                {
/* 331*/            StringBuilder stringbuilder = new StringBuilder();
/* 332*/            if(obj == null)
/* 333*/                stringbuilder.append("null");
/* 334*/            else
/* 334*/            if((obj instanceof Number) || (obj instanceof String) || (obj instanceof Method))
/* 335*/                stringbuilder.append(obj.toString());
/* 336*/            else
/* 336*/            if(obj instanceof javax.ws.rs.core.Response.StatusType)
                    {
/* 337*/                stringbuilder.append(formatStatusInfo((javax.ws.rs.core.Response.StatusType)obj));
                    } else
                    {
/* 339*/                stringbuilder.append('[');
/* 340*/                formatInstance(obj, stringbuilder);
/* 341*/                if(obj.getClass().isAnnotationPresent(javax/annotation/Priority))
/* 342*/                    stringbuilder.append(" #").append(((Priority)obj.getClass().getAnnotation(javax/annotation/Priority)).value());
/* 344*/                if(obj instanceof WebApplicationException)
/* 345*/                    formatResponse(((WebApplicationException)obj).getResponse(), stringbuilder);
/* 346*/                else
/* 346*/                if(obj instanceof Response)
/* 347*/                    formatResponse((Response)obj, stringbuilder);
/* 349*/                stringbuilder.append(']');
                    }
/* 351*/            return stringbuilder.toString();
                }

                private static void formatInstance(Object obj, StringBuilder stringbuilder)
                {
/* 361*/            stringbuilder.append(obj.getClass().getName()).append(" @").append(Integer.toHexString(System.identityHashCode(obj)));
                }

                private static void formatResponse(Response response, StringBuilder stringbuilder)
                {
/* 372*/            stringbuilder.append(" <").append(formatStatusInfo(response.getStatusInfo())).append('|');
/* 373*/            if(response.hasEntity())
/* 374*/                formatInstance(response.getEntity(), stringbuilder);
/* 376*/            else
/* 376*/                stringbuilder.append("-no-entity-");
/* 378*/            stringbuilder.append('>');
                }

                private static String formatStatusInfo(javax.ws.rs.core.Response.StatusType statustype)
                {
/* 382*/            return (new StringBuilder()).append(String.valueOf(statustype.getStatusCode())).append('/').append(statustype.getFamily()).append('|').append(statustype.getReasonPhrase()).toString();
                }

                private final Logger logger;
                private final Level threshold;
                private final TracingInfo tracingInfo = new TracingInfo();

                public TracingLoggerImpl(Level level, String s)
                {
/* 225*/            threshold = level;
/* 229*/            s = s == null ? "general" : s;
/* 230*/            logger = Logger.getLogger((new StringBuilder("org.glassfish.jersey.tracing.")).append(s).toString());
                }
    }


            public TracingLogger()
            {
            }

            public static TracingLogger getInstance(PropertiesDelegate propertiesdelegate)
            {
/* 140*/        if(propertiesdelegate == null)
/* 142*/            return EMPTY;
/* 144*/        if((propertiesdelegate = (TracingLogger)propertiesdelegate.getProperty(PROPERTY_NAME)) != null)
/* 145*/            return propertiesdelegate;
/* 145*/        else
/* 145*/            return EMPTY;
            }

            public static TracingLogger create(Level level, String s)
            {
/* 156*/        return new TracingLoggerImpl(level, s);
            }

            public static TracingLogger empty()
            {
/* 165*/        return EMPTY;
            }

            public abstract boolean isLogEnabled(Event event);

            public transient abstract void log(Event event, Object aobj[]);

            public transient abstract void logDuration(Event event, long l, Object aobj[]);

            public abstract long timestamp(Event event);

            public abstract void flush(MultivaluedMap multivaluedmap);

            public static final String PROPERTY_NAME = org/glassfish/jersey/message/internal/TracingLogger.getName();
            private static final String HEADER_TRACING_PREFIX = "X-Jersey-Tracing-";
            public static final String HEADER_THRESHOLD = "X-Jersey-Tracing-Threshold";
            public static final String HEADER_ACCEPT = "X-Jersey-Tracing-Accept";
            public static final String HEADER_LOGGER = "X-Jersey-Tracing-Logger";
            private static final String HEADER_RESPONSE_FORMAT = "X-Jersey-Tracing-%03d";
            public static final Level DEFAULT_LEVEL;
            private static final String TRACING_LOGGER_NAME_PREFIX = "org.glassfish.jersey.tracing";
            private static final String DEFAULT_LOGGER_NAME_SUFFIX = "general";
            private static final TracingLogger EMPTY = new TracingLogger() {

                public final boolean isLogEnabled(Event event)
                {
/* 107*/            return false;
                }

                public final transient void log(Event event, Object aobj[])
                {
                }

                public final transient void logDuration(Event event, long l, Object aobj[])
                {
                }

                public final long timestamp(Event event)
                {
/* 122*/            return -1L;
                }

                public final void flush(MultivaluedMap multivaluedmap)
                {
                }

    };

            static 
            {
/*  91*/        DEFAULT_LEVEL = Level.TRACE;
            }
}
