// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TracingLogger.java

package org.glassfish.jersey.message.internal;


// Referenced classes of package org.glassfish.jersey.message.internal:
//            TracingLogger

public static final class  extends Enum
{

            public static [] values()
            {
/* 389*/        return ([])$VALUES.clone();
            }

            public static l_3B_.clone valueOf(String s)
            {
/* 389*/        return (l_3B_.clone)Enum.valueOf(org/glassfish/jersey/message/internal/TracingLogger$Level, s);
            }

            public static final VERBOSE SUMMARY;
            public static final VERBOSE TRACE;
            public static final VERBOSE VERBOSE;
            private static final VERBOSE $VALUES[];

            static 
            {
/* 393*/        SUMMARY = new <init>("SUMMARY", 0);
/* 397*/        TRACE = new <init>("TRACE", 1);
/* 401*/        VERBOSE = new <init>("VERBOSE", 2);
/* 389*/        $VALUES = (new .VALUES[] {
/* 389*/            SUMMARY, TRACE, VERBOSE
                });
            }

            private (String s, int i)
            {
/* 389*/        super(s, i);
            }
}
