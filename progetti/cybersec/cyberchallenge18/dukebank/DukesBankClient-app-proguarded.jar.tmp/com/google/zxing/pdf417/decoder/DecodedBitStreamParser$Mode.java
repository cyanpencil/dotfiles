// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedBitStreamParser.java

package com.google.zxing.pdf417.decoder;


// Referenced classes of package com.google.zxing.pdf417.decoder:
//            DecodedBitStreamParser

static final class A extends Enum
{

            public static A[] values()
            {
/*  34*/        return (A[])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/*  34*/        return (e_3B_.clone)Enum.valueOf(com/google/zxing/pdf417/decoder/DecodedBitStreamParser$Mode, s);
            }

            public static final PUNCT_SHIFT ALPHA;
            public static final PUNCT_SHIFT LOWER;
            public static final PUNCT_SHIFT MIXED;
            public static final PUNCT_SHIFT PUNCT;
            public static final PUNCT_SHIFT ALPHA_SHIFT;
            public static final PUNCT_SHIFT PUNCT_SHIFT;
            private static final PUNCT_SHIFT $VALUES[];

            static 
            {
/*  35*/        ALPHA = new <init>("ALPHA", 0);
/*  36*/        LOWER = new <init>("LOWER", 1);
/*  37*/        MIXED = new <init>("MIXED", 2);
/*  38*/        PUNCT = new <init>("PUNCT", 3);
/*  39*/        ALPHA_SHIFT = new <init>("ALPHA_SHIFT", 4);
/*  40*/        PUNCT_SHIFT = new <init>("PUNCT_SHIFT", 5);
/*  34*/        $VALUES = (new .VALUES[] {
/*  34*/            ALPHA, LOWER, MIXED, PUNCT, ALPHA_SHIFT, PUNCT_SHIFT
                });
            }

            private A(String s, int i)
            {
/*  34*/        super(s, i);
            }
}
