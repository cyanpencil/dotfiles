// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Decoder.java

package com.google.zxing.aztec.decoder;


// Referenced classes of package com.google.zxing.aztec.decoder:
//            Decoder

static final class  extends Enum
{

            public static [] values()
            {
/*  37*/        return ([])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/*  37*/        return (e_3B_.clone)Enum.valueOf(com/google/zxing/aztec/decoder/Decoder$Table, s);
            }

            public static final BINARY UPPER;
            public static final BINARY LOWER;
            public static final BINARY MIXED;
            public static final BINARY DIGIT;
            public static final BINARY PUNCT;
            public static final BINARY BINARY;
            private static final BINARY $VALUES[];

            static 
            {
/*  38*/        UPPER = new <init>("UPPER", 0);
/*  39*/        LOWER = new <init>("LOWER", 1);
/*  40*/        MIXED = new <init>("MIXED", 2);
/*  41*/        DIGIT = new <init>("DIGIT", 3);
/*  42*/        PUNCT = new <init>("PUNCT", 4);
/*  43*/        BINARY = new <init>("BINARY", 5);
/*  37*/        $VALUES = (new .VALUES[] {
/*  37*/            UPPER, LOWER, MIXED, DIGIT, PUNCT, BINARY
                });
            }

            private (String s, int i)
            {
/*  37*/        super(s, i);
            }
}
