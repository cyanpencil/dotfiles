// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HeaderValueException.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ProcessingException;

public class HeaderValueException extends ProcessingException
{
    public static final class Context extends Enum
    {

                public static Context[] values()
                {
/*  59*/            return (Context[])$VALUES.clone();
                }

                public static Context valueOf(String s)
                {
/*  59*/            return (Context)Enum.valueOf(org/glassfish/jersey/message/internal/HeaderValueException$Context, s);
                }

                public static final Context INBOUND;
                public static final Context OUTBOUND;
                private static final Context $VALUES[];

                static 
                {
/*  63*/            INBOUND = new Context("INBOUND", 0);
/*  68*/            OUTBOUND = new Context("OUTBOUND", 1);
/*  59*/            $VALUES = (new Context[] {
/*  59*/                INBOUND, OUTBOUND
                    });
                }

                private Context(String s, int i)
                {
/*  59*/            super(s, i);
                }
    }


            public HeaderValueException(String s, Throwable throwable, Context context1)
            {
/*  79*/        super(s, throwable);
/*  80*/        context = context1;
            }

            public HeaderValueException(String s, Context context1)
            {
/*  90*/        super(s);
/*  91*/        context = context1;
            }

            public Context getContext()
            {
/* 100*/        return context;
            }

            private static final long serialVersionUID = 0xda017b25d272135L;
            private final Context context;
}
