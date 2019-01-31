// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedBitStreamParser.java

package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.util.Arrays;

final class DecodedBitStreamParser
{
    static final class Mode extends Enum
    {

                public static Mode[] values()
                {
/*  34*/            return (Mode[])$VALUES.clone();
                }

                public static Mode valueOf(String s)
                {
/*  34*/            return (Mode)Enum.valueOf(com/google/zxing/pdf417/decoder/DecodedBitStreamParser$Mode, s);
                }

                public static final Mode ALPHA;
                public static final Mode LOWER;
                public static final Mode MIXED;
                public static final Mode PUNCT;
                public static final Mode ALPHA_SHIFT;
                public static final Mode PUNCT_SHIFT;
                private static final Mode $VALUES[];

                static 
                {
/*  35*/            ALPHA = new Mode("ALPHA", 0);
/*  36*/            LOWER = new Mode("LOWER", 1);
/*  37*/            MIXED = new Mode("MIXED", 2);
/*  38*/            PUNCT = new Mode("PUNCT", 3);
/*  39*/            ALPHA_SHIFT = new Mode("ALPHA_SHIFT", 4);
/*  40*/            PUNCT_SHIFT = new Mode("PUNCT_SHIFT", 5);
/*  34*/            $VALUES = (new Mode[] {
/*  34*/                ALPHA, LOWER, MIXED, PUNCT, ALPHA_SHIFT, PUNCT_SHIFT
                    });
                }

                private Mode(String s, int i)
                {
/*  34*/            super(s, i);
                }
    }


            private DecodedBitStreamParser()
            {
            }

            static DecoderResult decode(int ai[], String s)
                throws FormatException
            {
/*  92*/        StringBuilder stringbuilder = new StringBuilder(ai.length << 1);
/*  94*/        int i = 1;
/*  95*/        i++;
/*  95*/        int j = ai[1];
/*  96*/        PDF417ResultMetadata pdf417resultmetadata = new PDF417ResultMetadata();
/*  97*/        while(i < ai[0]) 
                {
/*  98*/            switch(j)
                    {
/* 100*/            case 900: 
/* 100*/                i = textCompaction(ai, i, stringbuilder);
                        break;

/* 105*/            case 901: 
/* 105*/            case 913: 
/* 105*/            case 924: 
/* 105*/                i = byteCompaction(j, ai, i, stringbuilder);
                        break;

/* 108*/            case 902: 
/* 108*/                i = numericCompaction(ai, i, stringbuilder);
                        break;

/* 111*/            case 928: 
/* 111*/                i = decodeMacroBlock(ai, i, pdf417resultmetadata);
                        break;

/* 116*/            case 922: 
/* 116*/            case 923: 
/* 116*/                throw FormatException.getFormatInstance();

/* 121*/            case 903: 
/* 121*/            case 904: 
/* 121*/            case 905: 
/* 121*/            case 906: 
/* 121*/            case 907: 
/* 121*/            case 908: 
/* 121*/            case 909: 
/* 121*/            case 910: 
/* 121*/            case 911: 
/* 121*/            case 912: 
/* 121*/            case 914: 
/* 121*/            case 915: 
/* 121*/            case 916: 
/* 121*/            case 917: 
/* 121*/            case 918: 
/* 121*/            case 919: 
/* 121*/            case 920: 
/* 121*/            case 921: 
/* 121*/            case 925: 
/* 121*/            case 926: 
/* 121*/            case 927: 
/* 121*/            default:
/* 121*/                i--;
/* 122*/                i = textCompaction(ai, i, stringbuilder);
                        break;
                    }
/* 125*/            if(i < ai.length)
/* 126*/                j = ai[i++];
/* 128*/            else
/* 128*/                throw FormatException.getFormatInstance();
                }
/* 131*/        if(stringbuilder.length() == 0)
                {
/* 132*/            throw FormatException.getFormatInstance();
                } else
                {
/* 134*/            (ai = new DecoderResult(null, stringbuilder.toString(), null, s)).setOther(pdf417resultmetadata);
/* 136*/            return ai;
                }
            }

            private static int decodeMacroBlock(int ai[], int i, PDF417ResultMetadata pdf417resultmetadata)
                throws FormatException
            {
/* 141*/        if(i + 2 > ai[0])
/* 143*/            throw FormatException.getFormatInstance();
/* 145*/        int ai1[] = new int[2];
/* 146*/        for(int j = 0; j < 2;)
                {
/* 147*/            ai1[j] = ai[i];
/* 146*/            j++;
/* 146*/            i++;
                }

/* 149*/        pdf417resultmetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(ai1, 2)));
/* 152*/        StringBuilder stringbuilder = new StringBuilder();
/* 153*/        i = textCompaction(ai, i, stringbuilder);
/* 154*/        pdf417resultmetadata.setFileId(stringbuilder.toString());
/* 156*/        if(ai[i] == 923)
                {
/* 157*/            i++;
/* 158*/            int ai2[] = new int[ai[0] - i];
/* 159*/            int k = 0;
/* 161*/            boolean flag = false;
/* 162*/label0:
/* 162*/            do
                    {
                        int l;
/* 162*/                do
                        {
/* 162*/                    if(i >= ai[0] || flag)
/* 163*/                        break label0;
/* 163*/                    if((l = ai[i++]) >= 900)
/* 165*/                        break;
/* 165*/                    ai2[k++] = l;
                        } while(true);
/* 167*/                switch(l)
                        {
/* 169*/                case 922: 
/* 169*/                    pdf417resultmetadata.setLastSegment(true);
/* 170*/                    i++;
/* 171*/                    flag = true;
                            break;

/* 174*/                default:
/* 174*/                    throw FormatException.getFormatInstance();
                        }
                    } while(true);
/* 179*/            pdf417resultmetadata.setOptionalData(Arrays.copyOf(ai2, k));
                } else
/* 180*/        if(ai[i] == 922)
                {
/* 181*/            pdf417resultmetadata.setLastSegment(true);
/* 182*/            i++;
                }
/* 185*/        return i;
            }

            private static int textCompaction(int ai[], int i, StringBuilder stringbuilder)
            {
/* 200*/        int ai1[] = new int[ai[0] - i << 1];
/* 202*/        int ai2[] = new int[ai[0] - i << 1];
/* 204*/        int j = 0;
/* 205*/label0:
/* 205*/        do
                {
                    int k;
/* 205*/            for(boolean flag = false; i < ai[0] && !flag;)
/* 207*/                if((k = ai[i++]) < 900)
                        {
/* 209*/                    ai1[j] = k / 30;
/* 210*/                    ai1[j + 1] = k % 30;
/* 211*/                    j += 2;
                        } else
                        {
/* 213*/                    switch(k)
                            {
/* 216*/                    case 900: 
/* 216*/                        ai1[j++] = 900;
                                break;

/* 224*/                    case 901: 
/* 224*/                    case 902: 
/* 224*/                    case 922: 
/* 224*/                    case 923: 
/* 224*/                    case 924: 
/* 224*/                    case 928: 
/* 224*/                        i--;
/* 225*/                        flag = true;
                                break;

/* 234*/                    case 913: 
/* 234*/                        ai1[j] = 913;
/* 235*/                        int l = ai[i++];
/* 236*/                        ai2[j] = l;
/* 237*/                        j++;
                                break;
                            }
/* 241*/                    continue label0;
                        }

/* 242*/            decodeTextCompaction(ai1, ai2, j, stringbuilder);
/* 243*/            return i;
                } while(true);
            }

            private static void decodeTextCompaction(int ai[], int ai1[], int i, StringBuilder stringbuilder)
            {
/* 270*/        Mode mode = Mode.ALPHA;
/* 271*/        Mode mode1 = Mode.ALPHA;
/* 272*/        for(int j = 0; j < i; j++)
                {
/* 274*/            int k = ai[j];
/* 275*/            char c = '\0';
            static class _cls1
            {

                        static final int $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[];

                        static 
                        {
/* 276*/                    $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode = new int[Mode.values().length];
/* 276*/                    try
                            {
/* 276*/                        $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.ALPHA.ordinal()] = 1;
                            }
/* 276*/                    catch(NoSuchFieldError _ex) { }
/* 276*/                    try
                            {
/* 276*/                        $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.LOWER.ordinal()] = 2;
                            }
/* 276*/                    catch(NoSuchFieldError _ex) { }
/* 276*/                    try
                            {
/* 276*/                        $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.MIXED.ordinal()] = 3;
                            }
/* 276*/                    catch(NoSuchFieldError _ex) { }
/* 276*/                    try
                            {
/* 276*/                        $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT.ordinal()] = 4;
                            }
/* 276*/                    catch(NoSuchFieldError _ex) { }
/* 276*/                    try
                            {
/* 276*/                        $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.ALPHA_SHIFT.ordinal()] = 5;
                            }
/* 276*/                    catch(NoSuchFieldError _ex) { }
/* 276*/                    try
                            {
/* 276*/                        $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT_SHIFT.ordinal()] = 6;
                            }
/* 276*/                    catch(NoSuchFieldError _ex) { }
                        }
            }

/* 276*/            switch(_cls1..SwitchMap.com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode[mode.ordinal()])
                    {
/* 279*/            case 1: // '\001'
/* 279*/                if(k < 26)
/* 281*/                    c = (char)(k + 65);
/* 283*/                else
/* 283*/                if(k == 26)
/* 284*/                    c = ' ';
/* 285*/                else
/* 285*/                if(k == 27)
/* 286*/                    mode = Mode.LOWER;
/* 287*/                else
/* 287*/                if(k == 28)
/* 288*/                    mode = Mode.MIXED;
/* 289*/                else
/* 289*/                if(k == 29)
                        {
/* 291*/                    mode1 = mode;
/* 292*/                    mode = Mode.PUNCT_SHIFT;
                        } else
/* 293*/                if(k == 913)
/* 294*/                    stringbuilder.append((char)ai1[j]);
/* 295*/                else
/* 295*/                if(k == 900)
/* 296*/                    mode = Mode.ALPHA;
                        break;

/* 303*/            case 2: // '\002'
/* 303*/                if(k < 26)
/* 304*/                    c = (char)(k + 97);
/* 306*/                else
/* 306*/                if(k == 26)
/* 307*/                    c = ' ';
/* 308*/                else
/* 308*/                if(k == 27)
                        {
/* 310*/                    mode1 = mode;
/* 311*/                    mode = Mode.ALPHA_SHIFT;
                        } else
/* 312*/                if(k == 28)
/* 313*/                    mode = Mode.MIXED;
/* 314*/                else
/* 314*/                if(k == 29)
                        {
/* 316*/                    mode1 = mode;
/* 317*/                    mode = Mode.PUNCT_SHIFT;
                        } else
/* 318*/                if(k == 913)
/* 319*/                    stringbuilder.append((char)ai1[j]);
/* 320*/                else
/* 320*/                if(k == 900)
/* 321*/                    mode = Mode.ALPHA;
                        break;

/* 328*/            case 3: // '\003'
/* 328*/                if(k < 25)
/* 329*/                    c = MIXED_CHARS[k];
/* 331*/                else
/* 331*/                if(k == 25)
/* 332*/                    mode = Mode.PUNCT;
/* 333*/                else
/* 333*/                if(k == 26)
/* 334*/                    c = ' ';
/* 335*/                else
/* 335*/                if(k == 27)
/* 336*/                    mode = Mode.LOWER;
/* 337*/                else
/* 337*/                if(k == 28)
/* 338*/                    mode = Mode.ALPHA;
/* 339*/                else
/* 339*/                if(k == 29)
                        {
/* 341*/                    mode1 = mode;
/* 342*/                    mode = Mode.PUNCT_SHIFT;
                        } else
/* 343*/                if(k == 913)
/* 344*/                    stringbuilder.append((char)ai1[j]);
/* 345*/                else
/* 345*/                if(k == 900)
/* 346*/                    mode = Mode.ALPHA;
                        break;

/* 353*/            case 4: // '\004'
/* 353*/                if(k < 29)
/* 354*/                    c = PUNCT_CHARS[k];
/* 356*/                else
/* 356*/                if(k == 29)
/* 357*/                    mode = Mode.ALPHA;
/* 358*/                else
/* 358*/                if(k == 913)
/* 359*/                    stringbuilder.append((char)ai1[j]);
/* 360*/                else
/* 360*/                if(k == 900)
/* 361*/                    mode = Mode.ALPHA;
                        break;

/* 368*/            case 5: // '\005'
/* 368*/                mode = mode1;
/* 369*/                if(k < 26)
/* 370*/                    c = (char)(k + 65);
/* 372*/                else
/* 372*/                if(k == 26)
/* 373*/                    c = ' ';
/* 374*/                else
/* 374*/                if(k == 900)
/* 375*/                    mode = Mode.ALPHA;
                        break;

/* 382*/            case 6: // '\006'
/* 382*/                mode = mode1;
/* 383*/                if(k < 29)
/* 384*/                    c = PUNCT_CHARS[k];
/* 386*/                else
/* 386*/                if(k == 29)
/* 387*/                    mode = Mode.ALPHA;
/* 388*/                else
/* 388*/                if(k == 913)
/* 391*/                    stringbuilder.append((char)ai1[j]);
/* 392*/                else
/* 392*/                if(k == 900)
/* 393*/                    mode = Mode.ALPHA;
                        break;
                    }
/* 398*/            if(c != 0)
/* 400*/                stringbuilder.append(c);
                }

            }

            private static int byteCompaction(int i, int ai[], int j, StringBuilder stringbuilder)
            {
/* 418*/        if(i == 901)
                {
/* 421*/            i = 0;
/* 422*/            long l = 0L;
/* 423*/            char ac[] = new char[6];
/* 424*/            int ai1[] = new int[6];
/* 425*/            boolean flag1 = false;
/* 426*/            int i1 = ai[j++];
/* 427*/            do
                    {
/* 427*/                if(j >= ai[0] || flag1)
/* 428*/                    break;
/* 428*/                ai1[i++] = i1;
/* 430*/                l = 900L * l + (long)i1;
/* 431*/                if((i1 = ai[j++]) == 900 || i1 == 901 || i1 == 902 || i1 == 924 || i1 == 928 || i1 == 923 || i1 == 922)
                        {
/* 440*/                    j--;
/* 441*/                    flag1 = true;
                        } else
/* 443*/                if(i % 5 == 0 && i > 0)
                        {
/* 446*/                    for(int k1 = 0; k1 < 6; k1++)
                            {
/* 447*/                        ac[5 - k1] = (char)(int)(l % 256L);
/* 448*/                        l >>= 8;
                            }

/* 450*/                    stringbuilder.append(ac);
/* 451*/                    i = 0;
                        }
                    } while(true);
/* 457*/            if(j == ai[0] && i1 < 900)
/* 458*/                ai1[i++] = i1;
/* 464*/            for(int i2 = 0; i2 < i; i2++)
/* 465*/                stringbuilder.append((char)ai1[i2]);

                } else
/* 468*/        if(i == 924)
                {
/* 471*/            i = 0;
/* 472*/            long l1 = 0L;
/* 473*/            boolean flag = false;
/* 474*/            do
                    {
/* 474*/                if(j >= ai[0] || flag)
/* 475*/                    break;
                        int k;
/* 475*/                if((k = ai[j++]) < 900)
                        {
/* 477*/                    i++;
/* 479*/                    l1 = 900L * l1 + (long)k;
                        } else
/* 481*/                if(k == 900 || k == 901 || k == 902 || k == 924 || k == 928 || k == 923 || k == 922)
                        {
/* 488*/                    j--;
/* 489*/                    flag = true;
                        }
/* 492*/                if(i % 5 == 0 && i > 0)
                        {
/* 495*/                    char ac1[] = new char[6];
/* 496*/                    for(int j1 = 0; j1 < 6; j1++)
                            {
/* 497*/                        ac1[5 - j1] = (char)(int)(l1 & 255L);
/* 498*/                        l1 >>= 8;
                            }

/* 500*/                    stringbuilder.append(ac1);
/* 501*/                    i = 0;
                        }
                    } while(true);
                }
/* 505*/        return j;
            }

            private static int numericCompaction(int ai[], int i, StringBuilder stringbuilder)
                throws FormatException
            {
/* 517*/        String s = 0;
/* 518*/        boolean flag = false;
/* 520*/        int ai1[] = new int[15];
/* 522*/        do
                {
/* 522*/            if(i >= ai[0] || flag)
/* 523*/                break;
/* 523*/            int j = ai[i++];
/* 524*/            if(i == ai[0])
/* 525*/                flag = true;
/* 527*/            if(j < 900)
                    {
/* 528*/                ai1[s] = j;
/* 529*/                s++;
                    } else
/* 531*/            if(j == 900 || j == 901 || j == 924 || j == 928 || j == 923 || j == 922)
                    {
/* 537*/                i--;
/* 538*/                flag = true;
                    }
/* 541*/            if(s % 15 == 0 || j == 902 || flag)
                    {
/* 548*/                s = decodeBase900toBase10(ai1, s);
/* 549*/                stringbuilder.append(s);
/* 550*/                s = 0;
                    }
                } while(true);
/* 553*/        return i;
            }

            private static String decodeBase900toBase10(int ai[], int i)
                throws FormatException
            {
/* 600*/        BigInteger biginteger = BigInteger.ZERO;
/* 601*/        for(int j = 0; j < i; j++)
/* 602*/            biginteger = biginteger.add(EXP900[i - j - 1].multiply(BigInteger.valueOf(ai[j])));

                String s;
/* 604*/        if((s = biginteger.toString()).charAt(0) != '1')
/* 606*/            throw FormatException.getFormatInstance();
/* 608*/        else
/* 608*/            return s.substring(1);
            }

            private static final int TEXT_COMPACTION_MODE_LATCH = 900;
            private static final int BYTE_COMPACTION_MODE_LATCH = 901;
            private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
            private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
            private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
            private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
            private static final int MACRO_PDF417_TERMINATOR = 922;
            private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
            private static final int MAX_NUMERIC_CODEWORDS = 15;
            private static final int PL = 25;
            private static final int LL = 27;
            private static final int AS = 27;
            private static final int ML = 28;
            private static final int AL = 28;
            private static final int PS = 29;
            private static final int PAL = 29;
            private static final char PUNCT_CHARS[] = {
/*  61*/        ';', '<', '>', '@', '[', '\\', '}', '_', '`', '~', 
/*  61*/        '!', '\r', '\t', ',', ':', '\n', '-', '.', '$', '/', 
/*  61*/        '"', '|', '*', '(', ')', '?', '{', '}', '\''
            };
            private static final char MIXED_CHARS[] = {
/*  66*/        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/*  66*/        '&', '\r', '\t', ',', ':', '#', '-', '.', '$', '/', 
/*  66*/        '+', '%', '*', '=', '^'
            };
            private static final BigInteger EXP900[];
            private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;

            static 
            {
/*  77*/        (EXP900 = new BigInteger[16])[0] = BigInteger.ONE;
/*  79*/        BigInteger biginteger = BigInteger.valueOf(900L);
/*  80*/        EXP900[1] = biginteger;
/*  81*/        for(int i = 2; i < EXP900.length; i++)
/*  82*/            EXP900[i] = EXP900[i - 1].multiply(biginteger);

            }
}
