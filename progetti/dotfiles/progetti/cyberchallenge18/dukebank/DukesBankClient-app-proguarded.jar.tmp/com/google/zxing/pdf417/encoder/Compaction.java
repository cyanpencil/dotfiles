// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Compaction.java

package com.google.zxing.pdf417.encoder;


public final class Compaction extends Enum
{

            public static Compaction[] values()
            {
/*  19*/        return (Compaction[])$VALUES.clone();
            }

            public static Compaction valueOf(String s)
            {
/*  19*/        return (Compaction)Enum.valueOf(com/google/zxing/pdf417/encoder/Compaction, s);
            }

            private Compaction(String s, int i)
            {
/*  19*/        super(s, i);
            }

            public static final Compaction AUTO;
            public static final Compaction TEXT;
            public static final Compaction BYTE;
            public static final Compaction NUMERIC;
            private static final Compaction $VALUES[];

            static 
            {
/*  21*/        AUTO = new Compaction("AUTO", 0);
/*  22*/        TEXT = new Compaction("TEXT", 1);
/*  23*/        BYTE = new Compaction("BYTE", 2);
/*  24*/        NUMERIC = new Compaction("NUMERIC", 3);
/*  19*/        $VALUES = (new Compaction[] {
/*  19*/            AUTO, TEXT, BYTE, NUMERIC
                });
            }
}
