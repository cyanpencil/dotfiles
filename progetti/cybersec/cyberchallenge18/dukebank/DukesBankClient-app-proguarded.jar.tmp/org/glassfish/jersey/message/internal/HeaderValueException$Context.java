// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HeaderValueException.java

package org.glassfish.jersey.message.internal;


// Referenced classes of package org.glassfish.jersey.message.internal:
//            HeaderValueException

public static final class  extends Enum
{

            public static [] values()
            {
/*  59*/        return ([])$VALUES.clone();
            }

            public static t_3B_.clone valueOf(String s)
            {
/*  59*/        return (t_3B_.clone)Enum.valueOf(org/glassfish/jersey/message/internal/HeaderValueException$Context, s);
            }

            public static final OUTBOUND INBOUND;
            public static final OUTBOUND OUTBOUND;
            private static final OUTBOUND $VALUES[];

            static 
            {
/*  63*/        INBOUND = new <init>("INBOUND", 0);
/*  68*/        OUTBOUND = new <init>("OUTBOUND", 1);
/*  59*/        $VALUES = (new .VALUES[] {
/*  59*/            INBOUND, OUTBOUND
                });
            }

            private (String s, int i)
            {
/*  59*/        super(s, i);
            }
}
