// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RemovalCause.java

package jersey.repackaged.com.google.common.cache;


public abstract class RemovalCause extends Enum
{

            private RemovalCause(String s, int i)
            {
/*  34*/        super(s, i);
            }

            abstract boolean wasEvicted();


            public static final RemovalCause EXPLICIT;
            public static final RemovalCause REPLACED;
            public static final RemovalCause COLLECTED;
            public static final RemovalCause EXPIRED;
            public static final RemovalCause SIZE;
            private static final RemovalCause $VALUES[];

            static 
            {
/*  40*/        EXPLICIT = new RemovalCause("EXPLICIT", 0) {

                    final boolean wasEvicted()
                    {
/*  43*/                return false;
                    }

        };
/*  53*/        REPLACED = new RemovalCause("REPLACED", 1) {

                    final boolean wasEvicted()
                    {
/*  56*/                return false;
                    }

        };
/*  65*/        COLLECTED = new RemovalCause("COLLECTED", 2) {

                    final boolean wasEvicted()
                    {
/*  68*/                return true;
                    }

        };
/*  76*/        EXPIRED = new RemovalCause("EXPIRED", 3) {

                    final boolean wasEvicted()
                    {
/*  79*/                return true;
                    }

        };
/*  87*/        SIZE = new RemovalCause("SIZE", 4) {

                    final boolean wasEvicted()
                    {
/*  90*/                return true;
                    }

        };
/*  32*/        $VALUES = (new RemovalCause[] {
/*  32*/            EXPLICIT, REPLACED, COLLECTED, EXPIRED, SIZE
                });
            }
}
