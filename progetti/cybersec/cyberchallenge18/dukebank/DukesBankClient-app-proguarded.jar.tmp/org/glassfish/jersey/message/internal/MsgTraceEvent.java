// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MsgTraceEvent.java

package org.glassfish.jersey.message.internal;


// Referenced classes of package org.glassfish.jersey.message.internal:
//            TracingLogger

public final class MsgTraceEvent extends Enum
    implements TracingLogger.Event
{

            public static MsgTraceEvent[] values()
            {
/*  48*/        return (MsgTraceEvent[])$VALUES.clone();
            }

            public static MsgTraceEvent valueOf(String s)
            {
/*  48*/        return (MsgTraceEvent)Enum.valueOf(org/glassfish/jersey/message/internal/MsgTraceEvent, s);
            }

            private MsgTraceEvent(String s, int i, TracingLogger.Level level1, String s1, String s2)
            {
/* 118*/        super(s, i);
/* 119*/        level = level1;
/* 120*/        category = s1;
/* 121*/        messageFormat = s2;
            }

            public final String category()
            {
/* 126*/        return category;
            }

            public final TracingLogger.Level level()
            {
/* 131*/        return level;
            }

            public final String messageFormat()
            {
/* 136*/        return messageFormat;
            }

            public static final MsgTraceEvent RI_BEFORE;
            public static final MsgTraceEvent RI_AFTER;
            public static final MsgTraceEvent RI_SUMMARY;
            public static final MsgTraceEvent MBR_FIND;
            public static final MsgTraceEvent MBR_NOT_READABLE;
            public static final MsgTraceEvent MBR_SELECTED;
            public static final MsgTraceEvent MBR_SKIPPED;
            public static final MsgTraceEvent MBR_READ_FROM;
            public static final MsgTraceEvent MBW_FIND;
            public static final MsgTraceEvent MBW_NOT_WRITEABLE;
            public static final MsgTraceEvent MBW_SELECTED;
            public static final MsgTraceEvent MBW_SKIPPED;
            public static final MsgTraceEvent MBW_WRITE_TO;
            public static final MsgTraceEvent WI_BEFORE;
            public static final MsgTraceEvent WI_AFTER;
            public static final MsgTraceEvent WI_SUMMARY;
            private final TracingLogger.Level level;
            private final String category;
            private final String messageFormat;
            private static final MsgTraceEvent $VALUES[];

            static 
            {
/*  52*/        RI_BEFORE = new MsgTraceEvent("RI_BEFORE", 0, TracingLogger.Level.TRACE, "RI", "%s BEFORE context.proceed()");
/*  56*/        RI_AFTER = new MsgTraceEvent("RI_AFTER", 1, TracingLogger.Level.TRACE, "RI", "%s AFTER context.proceed()");
/*  60*/        RI_SUMMARY = new MsgTraceEvent("RI_SUMMARY", 2, TracingLogger.Level.SUMMARY, "RI", "ReadFrom summary: %s interceptors");
/*  64*/        MBR_FIND = new MsgTraceEvent("MBR_FIND", 3, TracingLogger.Level.TRACE, "MBR", "Find MBR for type=[%s] genericType=[%s] mediaType=[%s] annotations=%s");
/*  68*/        MBR_NOT_READABLE = new MsgTraceEvent("MBR_NOT_READABLE", 4, TracingLogger.Level.VERBOSE, "MBR", "%s is NOT readable");
/*  72*/        MBR_SELECTED = new MsgTraceEvent("MBR_SELECTED", 5, TracingLogger.Level.TRACE, "MBR", "%s IS readable");
/*  76*/        MBR_SKIPPED = new MsgTraceEvent("MBR_SKIPPED", 6, TracingLogger.Level.VERBOSE, "MBR", "%s is skipped");
/*  80*/        MBR_READ_FROM = new MsgTraceEvent("MBR_READ_FROM", 7, TracingLogger.Level.TRACE, "MBR", "ReadFrom by %s");
/*  84*/        MBW_FIND = new MsgTraceEvent("MBW_FIND", 8, TracingLogger.Level.TRACE, "MBW", "Find MBW for type=[%s] genericType=[%s] mediaType=[%s] annotations=%s");
/*  88*/        MBW_NOT_WRITEABLE = new MsgTraceEvent("MBW_NOT_WRITEABLE", 9, TracingLogger.Level.VERBOSE, "MBW", "%s is NOT writeable");
/*  92*/        MBW_SELECTED = new MsgTraceEvent("MBW_SELECTED", 10, TracingLogger.Level.TRACE, "MBW", "%s IS writeable");
/*  96*/        MBW_SKIPPED = new MsgTraceEvent("MBW_SKIPPED", 11, TracingLogger.Level.VERBOSE, "MBW", "%s is skipped");
/* 100*/        MBW_WRITE_TO = new MsgTraceEvent("MBW_WRITE_TO", 12, TracingLogger.Level.TRACE, "MBW", "WriteTo by %s");
/* 104*/        WI_BEFORE = new MsgTraceEvent("WI_BEFORE", 13, TracingLogger.Level.TRACE, "WI", "%s BEFORE context.proceed()");
/* 108*/        WI_AFTER = new MsgTraceEvent("WI_AFTER", 14, TracingLogger.Level.TRACE, "WI", "%s AFTER context.proceed()");
/* 112*/        WI_SUMMARY = new MsgTraceEvent("WI_SUMMARY", 15, TracingLogger.Level.SUMMARY, "WI", "WriteTo summary: %s interceptors");
/*  48*/        $VALUES = (new MsgTraceEvent[] {
/*  48*/            RI_BEFORE, RI_AFTER, RI_SUMMARY, MBR_FIND, MBR_NOT_READABLE, MBR_SELECTED, MBR_SKIPPED, MBR_READ_FROM, MBW_FIND, MBW_NOT_WRITEABLE, 
/*  48*/            MBW_SELECTED, MBW_SKIPPED, MBW_WRITE_TO, WI_BEFORE, WI_AFTER, WI_SUMMARY
                });
            }
}
