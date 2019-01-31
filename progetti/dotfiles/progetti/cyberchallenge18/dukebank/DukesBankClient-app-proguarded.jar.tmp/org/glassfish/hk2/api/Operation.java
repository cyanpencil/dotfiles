// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Operation.java

package org.glassfish.hk2.api;


public final class Operation extends Enum
{

            public static Operation[] values()
            {
/*  47*/        return (Operation[])$VALUES.clone();
            }

            public static Operation valueOf(String s)
            {
/*  47*/        return (Operation)Enum.valueOf(org/glassfish/hk2/api/Operation, s);
            }

            private Operation(String s, int i)
            {
/*  47*/        super(s, i);
            }

            public static final Operation LOOKUP;
            public static final Operation BIND;
            public static final Operation UNBIND;
            private static final Operation $VALUES[];

            static 
            {
/*  49*/        LOOKUP = new Operation("LOOKUP", 0);
/*  52*/        BIND = new Operation("BIND", 1);
/*  55*/        UNBIND = new Operation("UNBIND", 2);
/*  47*/        $VALUES = (new Operation[] {
/*  47*/            LOOKUP, BIND, UNBIND
                });
            }
}
