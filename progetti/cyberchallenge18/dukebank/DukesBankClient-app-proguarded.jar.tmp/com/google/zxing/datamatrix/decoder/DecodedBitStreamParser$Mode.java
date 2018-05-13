// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedBitStreamParser.java

package com.google.zxing.datamatrix.decoder;


// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            DecodedBitStreamParser

static final class  extends Enum
{

            public static [] values()
            {
/*  39*/        return ([])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/*  39*/        return (e_3B_.clone)Enum.valueOf(com/google/zxing/datamatrix/decoder/DecodedBitStreamParser$Mode, s);
            }

            public static final BASE256_ENCODE PAD_ENCODE;
            public static final BASE256_ENCODE ASCII_ENCODE;
            public static final BASE256_ENCODE C40_ENCODE;
            public static final BASE256_ENCODE TEXT_ENCODE;
            public static final BASE256_ENCODE ANSIX12_ENCODE;
            public static final BASE256_ENCODE EDIFACT_ENCODE;
            public static final BASE256_ENCODE BASE256_ENCODE;
            private static final BASE256_ENCODE $VALUES[];

            static 
            {
/*  40*/        PAD_ENCODE = new <init>("PAD_ENCODE", 0);
/*  41*/        ASCII_ENCODE = new <init>("ASCII_ENCODE", 1);
/*  42*/        C40_ENCODE = new <init>("C40_ENCODE", 2);
/*  43*/        TEXT_ENCODE = new <init>("TEXT_ENCODE", 3);
/*  44*/        ANSIX12_ENCODE = new <init>("ANSIX12_ENCODE", 4);
/*  45*/        EDIFACT_ENCODE = new <init>("EDIFACT_ENCODE", 5);
/*  46*/        BASE256_ENCODE = new <init>("BASE256_ENCODE", 6);
/*  39*/        $VALUES = (new .VALUES[] {
/*  39*/            PAD_ENCODE, ASCII_ENCODE, C40_ENCODE, TEXT_ENCODE, ANSIX12_ENCODE, EDIFACT_ENCODE, BASE256_ENCODE
                });
            }

            private (String s, int i)
            {
/*  39*/        super(s, i);
            }
}
