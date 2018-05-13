// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Trilean.java

package com.owlike.genson;


public abstract class Trilean extends Enum
{

            public static Trilean[] values()
            {
/*   8*/        return (Trilean[])$VALUES.clone();
            }

            public static Trilean valueOf(String s)
            {
/*   8*/        return (Trilean)Enum.valueOf(com/owlike/genson/Trilean, s);
            }

            private Trilean(String s, int i)
            {
/*   8*/        super(s, i);
            }

            public static Trilean valueOf(boolean flag)
            {
/*  30*/        if(flag)
/*  30*/            return TRUE;
/*  30*/        else
/*  30*/            return FALSE;
            }

            public abstract boolean booleanValue();


            public static final Trilean TRUE;
            public static final Trilean FALSE;
            public static final Trilean UNKNOWN;
            private static final Trilean $VALUES[];

            static 
            {
/*   9*/        TRUE = new Trilean("TRUE", 0) {

                    public final boolean booleanValue()
                    {
/*  12*/                return true;
                    }

        };
/*  15*/        FALSE = new Trilean("FALSE", 1) {

                    public final boolean booleanValue()
                    {
/*  18*/                return false;
                    }

        };
/*  21*/        UNKNOWN = new Trilean("UNKNOWN", 2) {

                    public final boolean booleanValue()
                    {
/*  24*/                throw new IllegalStateException("Unknown state can not be converter to a boolean, only TRUE AND FALSE can!");
                    }

        };
/*   8*/        $VALUES = (new Trilean[] {
/*   8*/            TRUE, FALSE, UNKNOWN
                });
            }
}
