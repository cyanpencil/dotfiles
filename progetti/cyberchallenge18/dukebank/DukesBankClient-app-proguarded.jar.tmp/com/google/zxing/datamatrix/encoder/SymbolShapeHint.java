// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SymbolShapeHint.java

package com.google.zxing.datamatrix.encoder;


public final class SymbolShapeHint extends Enum
{

            public static SymbolShapeHint[] values()
            {
/*  23*/        return (SymbolShapeHint[])$VALUES.clone();
            }

            public static SymbolShapeHint valueOf(String s)
            {
/*  23*/        return (SymbolShapeHint)Enum.valueOf(com/google/zxing/datamatrix/encoder/SymbolShapeHint, s);
            }

            private SymbolShapeHint(String s, int i)
            {
/*  23*/        super(s, i);
            }

            public static final SymbolShapeHint FORCE_NONE;
            public static final SymbolShapeHint FORCE_SQUARE;
            public static final SymbolShapeHint FORCE_RECTANGLE;
            private static final SymbolShapeHint $VALUES[];

            static 
            {
/*  25*/        FORCE_NONE = new SymbolShapeHint("FORCE_NONE", 0);
/*  26*/        FORCE_SQUARE = new SymbolShapeHint("FORCE_SQUARE", 1);
/*  27*/        FORCE_RECTANGLE = new SymbolShapeHint("FORCE_RECTANGLE", 2);
/*  23*/        $VALUES = (new SymbolShapeHint[] {
/*  23*/            FORCE_NONE, FORCE_SQUARE, FORCE_RECTANGLE
                });
            }
}
