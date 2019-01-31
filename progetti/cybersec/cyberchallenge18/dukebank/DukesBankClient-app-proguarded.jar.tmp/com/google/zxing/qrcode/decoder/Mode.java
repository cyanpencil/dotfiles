// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Mode.java

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            Version

public final class Mode extends Enum
{

            public static Mode[] values()
            {
/*  25*/        return (Mode[])$VALUES.clone();
            }

            public static Mode valueOf(String s)
            {
/*  25*/        return (Mode)Enum.valueOf(com/google/zxing/qrcode/decoder/Mode, s);
            }

            private Mode(String s, int i, int ai[], int j)
            {
/*  42*/        super(s, i);
/*  43*/        characterCountBitsForVersions = ai;
/*  44*/        bits = j;
            }

            public static Mode forBits(int i)
            {
/*  53*/        switch(i)
                {
/*  55*/        case 0: // '\0'
/*  55*/            return TERMINATOR;

/*  57*/        case 1: // '\001'
/*  57*/            return NUMERIC;

/*  59*/        case 2: // '\002'
/*  59*/            return ALPHANUMERIC;

/*  61*/        case 3: // '\003'
/*  61*/            return STRUCTURED_APPEND;

/*  63*/        case 4: // '\004'
/*  63*/            return BYTE;

/*  65*/        case 5: // '\005'
/*  65*/            return FNC1_FIRST_POSITION;

/*  67*/        case 7: // '\007'
/*  67*/            return ECI;

/*  69*/        case 8: // '\b'
/*  69*/            return KANJI;

/*  71*/        case 9: // '\t'
/*  71*/            return FNC1_SECOND_POSITION;

/*  74*/        case 13: // '\r'
/*  74*/            return HANZI;

/*  76*/        case 6: // '\006'
/*  76*/        case 10: // '\n'
/*  76*/        case 11: // '\013'
/*  76*/        case 12: // '\f'
/*  76*/        default:
/*  76*/            throw new IllegalArgumentException();
                }
            }

            public final int getCharacterCountBits(Version version)
            {
/*  86*/        if((version = version.getVersionNumber()) <= 9)
/*  89*/            version = 0;
/*  90*/        else
/*  90*/        if(version <= 26)
/*  91*/            version = 1;
/*  93*/        else
/*  93*/            version = 2;
/*  95*/        return characterCountBitsForVersions[version];
            }

            public final int getBits()
            {
/*  99*/        return bits;
            }

            public static final Mode TERMINATOR;
            public static final Mode NUMERIC;
            public static final Mode ALPHANUMERIC;
            public static final Mode STRUCTURED_APPEND;
            public static final Mode BYTE;
            public static final Mode ECI;
            public static final Mode KANJI;
            public static final Mode FNC1_FIRST_POSITION;
            public static final Mode FNC1_SECOND_POSITION;
            public static final Mode HANZI;
            private final int characterCountBitsForVersions[];
            private final int bits;
            private static final Mode $VALUES[];

            static 
            {
/*  27*/        TERMINATOR = new Mode("TERMINATOR", 0, new int[] {
/*  27*/            0, 0, 0
                }, 0);
/*  28*/        NUMERIC = new Mode("NUMERIC", 1, new int[] {
/*  28*/            10, 12, 14
                }, 1);
/*  29*/        ALPHANUMERIC = new Mode("ALPHANUMERIC", 2, new int[] {
/*  29*/            9, 11, 13
                }, 2);
/*  30*/        STRUCTURED_APPEND = new Mode("STRUCTURED_APPEND", 3, new int[] {
/*  30*/            0, 0, 0
                }, 3);
/*  31*/        BYTE = new Mode("BYTE", 4, new int[] {
/*  31*/            8, 16, 16
                }, 4);
/*  32*/        ECI = new Mode("ECI", 5, new int[] {
/*  32*/            0, 0, 0
                }, 7);
/*  33*/        KANJI = new Mode("KANJI", 6, new int[] {
/*  33*/            8, 10, 12
                }, 8);
/*  34*/        FNC1_FIRST_POSITION = new Mode("FNC1_FIRST_POSITION", 7, new int[] {
/*  34*/            0, 0, 0
                }, 5);
/*  35*/        FNC1_SECOND_POSITION = new Mode("FNC1_SECOND_POSITION", 8, new int[] {
/*  35*/            0, 0, 0
                }, 9);
/*  37*/        HANZI = new Mode("HANZI", 9, new int[] {
/*  37*/            8, 10, 12
                }, 13);
/*  25*/        $VALUES = (new Mode[] {
/*  25*/            TERMINATOR, NUMERIC, ALPHANUMERIC, STRUCTURED_APPEND, BYTE, ECI, KANJI, FNC1_FIRST_POSITION, FNC1_SECOND_POSITION, HANZI
                });
            }
}
