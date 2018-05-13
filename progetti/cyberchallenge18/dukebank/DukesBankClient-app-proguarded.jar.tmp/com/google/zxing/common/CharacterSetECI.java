// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharacterSetECI.java

package com.google.zxing.common;

import com.google.zxing.FormatException;
import java.util.HashMap;
import java.util.Map;

public final class CharacterSetECI extends Enum
{

            public static CharacterSetECI[] values()
            {
/*  30*/        return (CharacterSetECI[])$VALUES.clone();
            }

            public static CharacterSetECI valueOf(String s)
            {
/*  30*/        return (CharacterSetECI)Enum.valueOf(com/google/zxing/common/CharacterSetECI, s);
            }

            private CharacterSetECI(String s, int i, int j)
            {
/*  79*/        this(s, i, new int[] {
/*  79*/            j
                }, new String[0]);
            }

            private transient CharacterSetECI(String s, int i, int j, String as[])
            {
/*  82*/        super(s, i);
/*  83*/        values = (new int[] {
/*  83*/            j
                });
/*  84*/        otherEncodingNames = as;
            }

            private transient CharacterSetECI(String s, int i, int ai[], String as[])
            {
/*  87*/        super(s, i);
/*  88*/        values = ai;
/*  89*/        otherEncodingNames = as;
            }

            public final int getValue()
            {
/*  93*/        return values[0];
            }

            public static CharacterSetECI getCharacterSetECIByValue(int i)
                throws FormatException
            {
/* 103*/        if(i < 0 || i >= 900)
/* 104*/            throw FormatException.getFormatInstance();
/* 106*/        else
/* 106*/            return (CharacterSetECI)VALUE_TO_ECI.get(Integer.valueOf(i));
            }

            public static CharacterSetECI getCharacterSetECIByName(String s)
            {
/* 115*/        return (CharacterSetECI)NAME_TO_ECI.get(s);
            }

            public static final CharacterSetECI Cp437;
            public static final CharacterSetECI ISO8859_1;
            public static final CharacterSetECI ISO8859_2;
            public static final CharacterSetECI ISO8859_3;
            public static final CharacterSetECI ISO8859_4;
            public static final CharacterSetECI ISO8859_5;
            public static final CharacterSetECI ISO8859_6;
            public static final CharacterSetECI ISO8859_7;
            public static final CharacterSetECI ISO8859_8;
            public static final CharacterSetECI ISO8859_9;
            public static final CharacterSetECI ISO8859_10;
            public static final CharacterSetECI ISO8859_11;
            public static final CharacterSetECI ISO8859_13;
            public static final CharacterSetECI ISO8859_14;
            public static final CharacterSetECI ISO8859_15;
            public static final CharacterSetECI ISO8859_16;
            public static final CharacterSetECI SJIS;
            public static final CharacterSetECI Cp1250;
            public static final CharacterSetECI Cp1251;
            public static final CharacterSetECI Cp1252;
            public static final CharacterSetECI Cp1256;
            public static final CharacterSetECI UnicodeBigUnmarked;
            public static final CharacterSetECI UTF8;
            public static final CharacterSetECI ASCII;
            public static final CharacterSetECI Big5;
            public static final CharacterSetECI GB18030;
            public static final CharacterSetECI EUC_KR;
            private static final Map VALUE_TO_ECI;
            private static final Map NAME_TO_ECI;
            private final int values[];
            private final String otherEncodingNames[];
            private static final CharacterSetECI $VALUES[];

            static 
            {
/*  33*/        Cp437 = new CharacterSetECI("Cp437", 0, new int[] {
/*  33*/            0, 2
                }, new String[0]);
/*  34*/        ISO8859_1 = new CharacterSetECI("ISO8859_1", 1, new int[] {
/*  34*/            1, 3
                }, new String[] {
/*  34*/            "ISO-8859-1"
                });
/*  35*/        ISO8859_2 = new CharacterSetECI("ISO8859_2", 2, 4, new String[] {
/*  35*/            "ISO-8859-2"
                });
/*  36*/        ISO8859_3 = new CharacterSetECI("ISO8859_3", 3, 5, new String[] {
/*  36*/            "ISO-8859-3"
                });
/*  37*/        ISO8859_4 = new CharacterSetECI("ISO8859_4", 4, 6, new String[] {
/*  37*/            "ISO-8859-4"
                });
/*  38*/        ISO8859_5 = new CharacterSetECI("ISO8859_5", 5, 7, new String[] {
/*  38*/            "ISO-8859-5"
                });
/*  39*/        ISO8859_6 = new CharacterSetECI("ISO8859_6", 6, 8, new String[] {
/*  39*/            "ISO-8859-6"
                });
/*  40*/        ISO8859_7 = new CharacterSetECI("ISO8859_7", 7, 9, new String[] {
/*  40*/            "ISO-8859-7"
                });
/*  41*/        ISO8859_8 = new CharacterSetECI("ISO8859_8", 8, 10, new String[] {
/*  41*/            "ISO-8859-8"
                });
/*  42*/        ISO8859_9 = new CharacterSetECI("ISO8859_9", 9, 11, new String[] {
/*  42*/            "ISO-8859-9"
                });
/*  43*/        ISO8859_10 = new CharacterSetECI("ISO8859_10", 10, 12, new String[] {
/*  43*/            "ISO-8859-10"
                });
/*  44*/        ISO8859_11 = new CharacterSetECI("ISO8859_11", 11, 13, new String[] {
/*  44*/            "ISO-8859-11"
                });
/*  45*/        ISO8859_13 = new CharacterSetECI("ISO8859_13", 12, 15, new String[] {
/*  45*/            "ISO-8859-13"
                });
/*  46*/        ISO8859_14 = new CharacterSetECI("ISO8859_14", 13, 16, new String[] {
/*  46*/            "ISO-8859-14"
                });
/*  47*/        ISO8859_15 = new CharacterSetECI("ISO8859_15", 14, 17, new String[] {
/*  47*/            "ISO-8859-15"
                });
/*  48*/        ISO8859_16 = new CharacterSetECI("ISO8859_16", 15, 18, new String[] {
/*  48*/            "ISO-8859-16"
                });
/*  49*/        SJIS = new CharacterSetECI("SJIS", 16, 20, new String[] {
/*  49*/            "Shift_JIS"
                });
/*  50*/        Cp1250 = new CharacterSetECI("Cp1250", 17, 21, new String[] {
/*  50*/            "windows-1250"
                });
/*  51*/        Cp1251 = new CharacterSetECI("Cp1251", 18, 22, new String[] {
/*  51*/            "windows-1251"
                });
/*  52*/        Cp1252 = new CharacterSetECI("Cp1252", 19, 23, new String[] {
/*  52*/            "windows-1252"
                });
/*  53*/        Cp1256 = new CharacterSetECI("Cp1256", 20, 24, new String[] {
/*  53*/            "windows-1256"
                });
/*  54*/        UnicodeBigUnmarked = new CharacterSetECI("UnicodeBigUnmarked", 21, 25, new String[] {
/*  54*/            "UTF-16BE", "UnicodeBig"
                });
/*  55*/        UTF8 = new CharacterSetECI("UTF8", 22, 26, new String[] {
/*  55*/            "UTF-8"
                });
/*  56*/        ASCII = new CharacterSetECI("ASCII", 23, new int[] {
/*  56*/            27, 170
                }, new String[] {
/*  56*/            "US-ASCII"
                });
/*  57*/        Big5 = new CharacterSetECI("Big5", 24, 28);
/*  58*/        GB18030 = new CharacterSetECI("GB18030", 25, 29, new String[] {
/*  58*/            "GB2312", "EUC_CN", "GBK"
                });
/*  59*/        EUC_KR = new CharacterSetECI("EUC_KR", 26, 30, new String[] {
/*  59*/            "EUC-KR"
                });
/*  30*/        $VALUES = (new CharacterSetECI[] {
/*  30*/            Cp437, ISO8859_1, ISO8859_2, ISO8859_3, ISO8859_4, ISO8859_5, ISO8859_6, ISO8859_7, ISO8859_8, ISO8859_9, 
/*  30*/            ISO8859_10, ISO8859_11, ISO8859_13, ISO8859_14, ISO8859_15, ISO8859_16, SJIS, Cp1250, Cp1251, Cp1252, 
/*  30*/            Cp1256, UnicodeBigUnmarked, UTF8, ASCII, Big5, GB18030, EUC_KR
                });
/*  61*/        VALUE_TO_ECI = new HashMap();
/*  62*/        NAME_TO_ECI = new HashMap();
                CharacterSetECI acharacterseteci[];
/*  64*/        int i = (acharacterseteci = values()).length;
/*  64*/        for(int j = 0; j < i; j++)
                {
                    CharacterSetECI characterseteci;
                    Object aobj[];
/*  64*/            int k = (aobj = (characterseteci = acharacterseteci[j]).values).length;
/*  65*/            for(int l = 0; l < k; l++)
                    {
/*  65*/                int j1 = aobj[l];
/*  66*/                VALUE_TO_ECI.put(Integer.valueOf(j1), characterseteci);
                    }

/*  68*/            NAME_TO_ECI.put(characterseteci.name(), characterseteci);
/*  69*/            k = (aobj = characterseteci.otherEncodingNames).length;
/*  69*/            for(int i1 = 0; i1 < k; i1++)
                    {
/*  69*/                String s = aobj[i1];
/*  70*/                NAME_TO_ECI.put(s, characterseteci);
                    }

                }

            }
}
