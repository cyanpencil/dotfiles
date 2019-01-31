// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ErrorCorrectionLevel.java

package com.google.zxing.qrcode.decoder;


public final class ErrorCorrectionLevel extends Enum
{

            public static ErrorCorrectionLevel[] values()
            {
/*  25*/        return (ErrorCorrectionLevel[])$VALUES.clone();
            }

            public static ErrorCorrectionLevel valueOf(String s)
            {
/*  25*/        return (ErrorCorrectionLevel)Enum.valueOf(com/google/zxing/qrcode/decoder/ErrorCorrectionLevel, s);
            }

            private ErrorCorrectionLevel(String s, int i, int j)
            {
/*  40*/        super(s, i);
/*  41*/        bits = j;
            }

            public final int getBits()
            {
/*  45*/        return bits;
            }

            public static ErrorCorrectionLevel forBits(int i)
            {
/*  53*/        if(i < 0 || i >= FOR_BITS.length)
/*  54*/            throw new IllegalArgumentException();
/*  56*/        else
/*  56*/            return FOR_BITS[i];
            }

            public static final ErrorCorrectionLevel L;
            public static final ErrorCorrectionLevel M;
            public static final ErrorCorrectionLevel Q;
            public static final ErrorCorrectionLevel H;
            private static final ErrorCorrectionLevel FOR_BITS[];
            private final int bits;
            private static final ErrorCorrectionLevel $VALUES[];

            static 
            {
/*  28*/        L = new ErrorCorrectionLevel("L", 0, 1);
/*  30*/        M = new ErrorCorrectionLevel("M", 1, 0);
/*  32*/        Q = new ErrorCorrectionLevel("Q", 2, 3);
/*  34*/        H = new ErrorCorrectionLevel("H", 3, 2);
/*  25*/        $VALUES = (new ErrorCorrectionLevel[] {
/*  25*/            L, M, Q, H
                });
/*  36*/        FOR_BITS = (new ErrorCorrectionLevel[] {
/*  36*/            M, L, H, Q
                });
            }
}
