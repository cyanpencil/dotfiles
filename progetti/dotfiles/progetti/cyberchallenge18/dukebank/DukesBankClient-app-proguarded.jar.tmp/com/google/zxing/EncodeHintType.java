// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EncodeHintType.java

package com.google.zxing;


public final class EncodeHintType extends Enum
{

            public static EncodeHintType[] values()
            {
/*  24*/        return (EncodeHintType[])$VALUES.clone();
            }

            public static EncodeHintType valueOf(String s)
            {
/*  24*/        return (EncodeHintType)Enum.valueOf(com/google/zxing/EncodeHintType, s);
            }

            private EncodeHintType(String s, int i)
            {
/*  24*/        super(s, i);
            }

            public static final EncodeHintType ERROR_CORRECTION;
            public static final EncodeHintType CHARACTER_SET;
            public static final EncodeHintType DATA_MATRIX_SHAPE;
            public static final EncodeHintType MIN_SIZE;
            public static final EncodeHintType MAX_SIZE;
            public static final EncodeHintType MARGIN;
            public static final EncodeHintType PDF417_COMPACT;
            public static final EncodeHintType PDF417_COMPACTION;
            public static final EncodeHintType PDF417_DIMENSIONS;
            public static final EncodeHintType AZTEC_LAYERS;
            private static final EncodeHintType $VALUES[];

            static 
            {
/*  33*/        ERROR_CORRECTION = new EncodeHintType("ERROR_CORRECTION", 0);
/*  38*/        CHARACTER_SET = new EncodeHintType("CHARACTER_SET", 1);
/*  43*/        DATA_MATRIX_SHAPE = new EncodeHintType("DATA_MATRIX_SHAPE", 2);
/*  48*/        MIN_SIZE = new EncodeHintType("MIN_SIZE", 3);
/*  53*/        MAX_SIZE = new EncodeHintType("MAX_SIZE", 4);
/*  60*/        MARGIN = new EncodeHintType("MARGIN", 5);
/*  65*/        PDF417_COMPACT = new EncodeHintType("PDF417_COMPACT", 6);
/*  71*/        PDF417_COMPACTION = new EncodeHintType("PDF417_COMPACTION", 7);
/*  77*/        PDF417_DIMENSIONS = new EncodeHintType("PDF417_DIMENSIONS", 8);
/*  85*/        AZTEC_LAYERS = new EncodeHintType("AZTEC_LAYERS", 9);
/*  24*/        $VALUES = (new EncodeHintType[] {
/*  24*/            ERROR_CORRECTION, CHARACTER_SET, DATA_MATRIX_SHAPE, MIN_SIZE, MAX_SIZE, MARGIN, PDF417_COMPACT, PDF417_COMPACTION, PDF417_DIMENSIONS, AZTEC_LAYERS
                });
            }
}
