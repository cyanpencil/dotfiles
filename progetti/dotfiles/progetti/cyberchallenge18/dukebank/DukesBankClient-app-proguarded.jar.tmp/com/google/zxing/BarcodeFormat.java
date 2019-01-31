// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BarcodeFormat.java

package com.google.zxing;


public final class BarcodeFormat extends Enum
{

            public static BarcodeFormat[] values()
            {
/*  24*/        return (BarcodeFormat[])$VALUES.clone();
            }

            public static BarcodeFormat valueOf(String s)
            {
/*  24*/        return (BarcodeFormat)Enum.valueOf(com/google/zxing/BarcodeFormat, s);
            }

            private BarcodeFormat(String s, int i)
            {
/*  24*/        super(s, i);
            }

            public static final BarcodeFormat AZTEC;
            public static final BarcodeFormat CODABAR;
            public static final BarcodeFormat CODE_39;
            public static final BarcodeFormat CODE_93;
            public static final BarcodeFormat CODE_128;
            public static final BarcodeFormat DATA_MATRIX;
            public static final BarcodeFormat EAN_8;
            public static final BarcodeFormat EAN_13;
            public static final BarcodeFormat ITF;
            public static final BarcodeFormat MAXICODE;
            public static final BarcodeFormat PDF_417;
            public static final BarcodeFormat QR_CODE;
            public static final BarcodeFormat RSS_14;
            public static final BarcodeFormat RSS_EXPANDED;
            public static final BarcodeFormat UPC_A;
            public static final BarcodeFormat UPC_E;
            public static final BarcodeFormat UPC_EAN_EXTENSION;
            private static final BarcodeFormat $VALUES[];

            static 
            {
/*  27*/        AZTEC = new BarcodeFormat("AZTEC", 0);
/*  30*/        CODABAR = new BarcodeFormat("CODABAR", 1);
/*  33*/        CODE_39 = new BarcodeFormat("CODE_39", 2);
/*  36*/        CODE_93 = new BarcodeFormat("CODE_93", 3);
/*  39*/        CODE_128 = new BarcodeFormat("CODE_128", 4);
/*  42*/        DATA_MATRIX = new BarcodeFormat("DATA_MATRIX", 5);
/*  45*/        EAN_8 = new BarcodeFormat("EAN_8", 6);
/*  48*/        EAN_13 = new BarcodeFormat("EAN_13", 7);
/*  51*/        ITF = new BarcodeFormat("ITF", 8);
/*  54*/        MAXICODE = new BarcodeFormat("MAXICODE", 9);
/*  57*/        PDF_417 = new BarcodeFormat("PDF_417", 10);
/*  60*/        QR_CODE = new BarcodeFormat("QR_CODE", 11);
/*  63*/        RSS_14 = new BarcodeFormat("RSS_14", 12);
/*  66*/        RSS_EXPANDED = new BarcodeFormat("RSS_EXPANDED", 13);
/*  69*/        UPC_A = new BarcodeFormat("UPC_A", 14);
/*  72*/        UPC_E = new BarcodeFormat("UPC_E", 15);
/*  75*/        UPC_EAN_EXTENSION = new BarcodeFormat("UPC_EAN_EXTENSION", 16);
/*  24*/        $VALUES = (new BarcodeFormat[] {
/*  24*/            AZTEC, CODABAR, CODE_39, CODE_93, CODE_128, DATA_MATRIX, EAN_8, EAN_13, ITF, MAXICODE, 
/*  24*/            PDF_417, QR_CODE, RSS_14, RSS_EXPANDED, UPC_A, UPC_E, UPC_EAN_EXTENSION
                });
            }
}
