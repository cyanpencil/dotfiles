// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Decoder.java

package com.google.zxing.aztec.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.*;
import java.util.Arrays;

public final class Decoder
{
    static final class Table extends Enum
    {

                public static Table[] values()
                {
/*  37*/            return (Table[])$VALUES.clone();
                }

                public static Table valueOf(String s)
                {
/*  37*/            return (Table)Enum.valueOf(com/google/zxing/aztec/decoder/Decoder$Table, s);
                }

                public static final Table UPPER;
                public static final Table LOWER;
                public static final Table MIXED;
                public static final Table DIGIT;
                public static final Table PUNCT;
                public static final Table BINARY;
                private static final Table $VALUES[];

                static 
                {
/*  38*/            UPPER = new Table("UPPER", 0);
/*  39*/            LOWER = new Table("LOWER", 1);
/*  40*/            MIXED = new Table("MIXED", 2);
/*  41*/            DIGIT = new Table("DIGIT", 3);
/*  42*/            PUNCT = new Table("PUNCT", 4);
/*  43*/            BINARY = new Table("BINARY", 5);
/*  37*/            $VALUES = (new Table[] {
/*  37*/                UPPER, LOWER, MIXED, DIGIT, PUNCT, BINARY
                    });
                }

                private Table(String s, int i)
                {
/*  37*/            super(s, i);
                }
    }


            public Decoder()
            {
            }

            public final DecoderResult decode(AztecDetectorResult aztecdetectorresult)
                throws FormatException
            {
/*  74*/        ddata = aztecdetectorresult;
/*  75*/        aztecdetectorresult = aztecdetectorresult.getBits();
/*  76*/        aztecdetectorresult = extractBits(aztecdetectorresult);
/*  77*/        aztecdetectorresult = getEncodedData(aztecdetectorresult = correctBits(aztecdetectorresult));
/*  79*/        return new DecoderResult(null, aztecdetectorresult, null, null);
            }

            public static String highLevelDecode(boolean aflag[])
            {
/*  84*/        return getEncodedData(aflag);
            }

            private static String getEncodedData(boolean aflag[])
            {
/*  93*/        int i = aflag.length;
/*  94*/        Table table = Table.UPPER;
/*  95*/        Table table1 = Table.UPPER;
/*  96*/        StringBuilder stringbuilder = new StringBuilder(20);
/*  97*/        int j = 0;
/*  98*/        do
                {
/*  98*/            if(j >= i)
/*  99*/                break;
/*  99*/            if(table1 == Table.BINARY)
                    {
/* 100*/                if(i - j < 5)
/* 103*/                    break;
/* 103*/                int k = readCode(aflag, j, 5);
/* 104*/                j += 5;
/* 105*/                if(k == 0)
                        {
/* 106*/                    if(i - j < 11)
/* 109*/                        break;
/* 109*/                    k = readCode(aflag, j, 11) + 31;
/* 110*/                    j += 11;
                        }
/* 112*/                int l = 0;
/* 112*/                do
                        {
/* 112*/                    if(l >= k)
/* 113*/                        break;
/* 113*/                    if(i - j < 8)
                            {
/* 114*/                        j = i;
/* 115*/                        break;
                            }
/* 117*/                    int j1 = readCode(aflag, j, 8);
/* 118*/                    stringbuilder.append((char)j1);
/* 119*/                    j += 8;
/* 112*/                    l++;
                        } while(true);
/* 122*/                table1 = table;
/* 123*/                continue;
                    }
/* 124*/            byte byte0 = ((byte)(table1 != Table.DIGIT ? 5 : 4));
/* 125*/            if(i - j < byte0)
/* 128*/                break;
/* 128*/            int i1 = readCode(aflag, j, byte0);
/* 129*/            j += byte0;
                    String s;
/* 130*/            if((s = getCharacter(table1, i1)).startsWith("CTRL_"))
                    {
/* 133*/                table1 = getTable(s.charAt(5));
/* 134*/                if(s.charAt(6) == 'L')
/* 135*/                    table = table1;
                    } else
                    {
/* 138*/                stringbuilder.append(s);
/* 140*/                table1 = table;
                    }
                } while(true);
/* 144*/        return stringbuilder.toString();
            }

            private static Table getTable(char c)
            {
/* 151*/        switch(c)
                {
/* 153*/        case 76: // 'L'
/* 153*/            return Table.LOWER;

/* 155*/        case 80: // 'P'
/* 155*/            return Table.PUNCT;

/* 157*/        case 77: // 'M'
/* 157*/            return Table.MIXED;

/* 159*/        case 68: // 'D'
/* 159*/            return Table.DIGIT;

/* 161*/        case 66: // 'B'
/* 161*/            return Table.BINARY;

/* 164*/        case 67: // 'C'
/* 164*/        case 69: // 'E'
/* 164*/        case 70: // 'F'
/* 164*/        case 71: // 'G'
/* 164*/        case 72: // 'H'
/* 164*/        case 73: // 'I'
/* 164*/        case 74: // 'J'
/* 164*/        case 75: // 'K'
/* 164*/        case 78: // 'N'
/* 164*/        case 79: // 'O'
/* 164*/        default:
/* 164*/            return Table.UPPER;
                }
            }

            private static String getCharacter(Table table, int i)
            {
        static class _cls1
        {

                    static final int $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[];

                    static 
                    {
/* 175*/                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = new int[Table.values().length];
/* 175*/                try
                        {
/* 175*/                    $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.UPPER.ordinal()] = 1;
                        }
/* 175*/                catch(NoSuchFieldError _ex) { }
/* 175*/                try
                        {
/* 175*/                    $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.LOWER.ordinal()] = 2;
                        }
/* 175*/                catch(NoSuchFieldError _ex) { }
/* 175*/                try
                        {
/* 175*/                    $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.MIXED.ordinal()] = 3;
                        }
/* 175*/                catch(NoSuchFieldError _ex) { }
/* 175*/                try
                        {
/* 175*/                    $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.PUNCT.ordinal()] = 4;
                        }
/* 175*/                catch(NoSuchFieldError _ex) { }
/* 175*/                try
                        {
/* 175*/                    $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.DIGIT.ordinal()] = 5;
                        }
/* 175*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/* 175*/        switch(_cls1..SwitchMap.com.google.zxing.aztec.decoder.Decoder.Table[table.ordinal()])
                {
/* 177*/        case 1: // '\001'
/* 177*/            return UPPER_TABLE[i];

/* 179*/        case 2: // '\002'
/* 179*/            return LOWER_TABLE[i];

/* 181*/        case 3: // '\003'
/* 181*/            return MIXED_TABLE[i];

/* 183*/        case 4: // '\004'
/* 183*/            return PUNCT_TABLE[i];

/* 185*/        case 5: // '\005'
/* 185*/            return DIGIT_TABLE[i];
                }
/* 188*/        throw new IllegalStateException("Bad table");
            }

            private boolean[] correctBits(boolean aflag[])
                throws FormatException
            {
                GenericGF genericgf;
                byte byte0;
/* 202*/        if(ddata.getNbLayers() <= 2)
                {
/* 203*/            byte0 = 6;
/* 204*/            genericgf = GenericGF.AZTEC_DATA_6;
                } else
/* 205*/        if(ddata.getNbLayers() <= 8)
                {
/* 206*/            byte0 = 8;
/* 207*/            genericgf = GenericGF.AZTEC_DATA_8;
                } else
/* 208*/        if(ddata.getNbLayers() <= 22)
                {
/* 209*/            byte0 = 10;
/* 210*/            genericgf = GenericGF.AZTEC_DATA_10;
                } else
                {
/* 212*/            byte0 = 12;
/* 213*/            genericgf = GenericGF.AZTEC_DATA_12;
                }
/* 216*/        int j = ddata.getNbDatablocks();
/* 217*/        int k = aflag.length / byte0;
/* 218*/        int l = aflag.length % byte0;
/* 219*/        int j1 = k - j;
/* 221*/        int ai[] = new int[k];
/* 222*/        for(int l1 = 0; l1 < k;)
                {
/* 223*/            ai[l1] = readCode(aflag, l, byte0);
/* 222*/            l1++;
/* 222*/            l += byte0;
                }

                ReedSolomonDecoder reedsolomondecoder;
/* 227*/        try
                {
/* 227*/            (reedsolomondecoder = new ReedSolomonDecoder(genericgf)).decode(ai, j1);
                }
/* 229*/        catch(ReedSolomonException _ex)
                {
/* 230*/            throw FormatException.getFormatInstance();
                }
/* 235*/        int i2 = (1 << byte0) - 1;
/* 236*/        aflag = 0;
/* 237*/        for(int i = 0; i < j; i++)
                {
/* 238*/            if((k = ai[i]) == 0 || k == i2)
/* 240*/                throw FormatException.getFormatInstance();
/* 241*/            if(k == 1 || k == i2 - 1)
/* 242*/                aflag++;
                }

/* 246*/        boolean aflag1[] = new boolean[j * byte0 - aflag];
/* 247*/        k = 0;
/* 248*/        for(aflag = 0; aflag < j; aflag++)
                {
                    int i1;
/* 249*/            if((i1 = ai[aflag]) == 1 || i1 == i2 - 1)
                    {
/* 252*/                Arrays.fill(aflag1, k, (k + byte0) - 1, i1 > 1);
/* 253*/                k += byte0 - 1;
/* 253*/                continue;
                    }
/* 255*/            for(int k1 = byte0 - 1; k1 >= 0; k1--)
/* 256*/                aflag1[k++] = (i1 & 1 << k1) != 0;

                }

/* 260*/        return aflag1;
            }

            final boolean[] extractBits(BitMatrix bitmatrix)
            {
/* 269*/        boolean flag = ddata.isCompact();
/* 270*/        int i = ddata.getNbLayers();
                int j;
/* 271*/        int ai[] = new int[j = flag ? 11 + (i << 2) : 14 + (i << 2)];
/* 273*/        boolean aflag[] = new boolean[totalBitsInLayer(i, flag)];
/* 275*/        if(flag)
                {
/* 276*/            for(int k = 0; k < ai.length; k++)
/* 277*/                ai[k] = k;

                } else
                {
/* 280*/            int l = j + 1 + 2 * ((j / 2 - 1) / 15);
/* 281*/            int j1 = j / 2;
/* 282*/            int l1 = l / 2;
/* 283*/            for(int j2 = 0; j2 < j1; j2++)
                    {
/* 284*/                int l2 = j2 + j2 / 15;
/* 285*/                ai[j1 - j2 - 1] = l1 - l2 - 1;
/* 286*/                ai[j1 + j2] = l1 + l2 + 1;
                    }

                }
/* 289*/        int i1 = 0;
/* 289*/        int k1 = 0;
/* 289*/        for(; i1 < i; i1++)
                {
/* 290*/            int i2 = flag ? (i - i1 << 2) + 9 : (i - i1 << 2) + 12;
/* 292*/            int k2 = i1 << 1;
/* 294*/            int i3 = j - 1 - k2;
/* 296*/            for(int j3 = 0; j3 < i2; j3++)
                    {
/* 297*/                int k3 = j3 << 1;
/* 298*/                for(int l3 = 0; l3 < 2; l3++)
                        {
/* 300*/                    aflag[k1 + k3 + l3] = bitmatrix.get(ai[k2 + l3], ai[k2 + j3]);
/* 303*/                    aflag[k1 + 2 * i2 + k3 + l3] = bitmatrix.get(ai[k2 + j3], ai[i3 - l3]);
/* 306*/                    aflag[k1 + 4 * i2 + k3 + l3] = bitmatrix.get(ai[i3 - l3], ai[i3 - j3]);
/* 309*/                    aflag[k1 + i2 * 6 + k3 + l3] = bitmatrix.get(ai[i3 - j3], ai[k2 + l3]);
                        }

                    }

/* 313*/            k1 += i2 << 3;
                }

/* 315*/        return aflag;
            }

            private static int readCode(boolean aflag[], int i, int j)
            {
/* 322*/        int k = 0;
/* 323*/        for(int l = i; l < i + j; l++)
                {
/* 324*/            k <<= 1;
/* 325*/            if(aflag[l])
/* 326*/                k++;
                }

/* 329*/        return k;
            }

            private static int totalBitsInLayer(int i, boolean flag)
            {
/* 333*/        return ((flag ? 88 : 112) + i * 16) * i;
            }

            private static final String UPPER_TABLE[] = {
/*  46*/        "CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", 
/*  46*/        "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
/*  46*/        "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", 
/*  46*/        "CTRL_DL", "CTRL_BS"
            };
            private static final String LOWER_TABLE[] = {
/*  51*/        "CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", 
/*  51*/        "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", 
/*  51*/        "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", 
/*  51*/        "CTRL_DL", "CTRL_BS"
            };
            private static final String MIXED_TABLE[] = {
/*  56*/        "CTRL_PS", " ", "\001", "\002", "\003", "\004", "\005", "\006", "\007", "\b", 
/*  56*/        "\t", "\n", "\013", "\f", "\r", "\033", "\034", "\035", "\036", "\037", 
/*  56*/        "@", "\\", "^", "_", "`", "|", "~", "\177", "CTRL_LL", "CTRL_UL", 
/*  56*/        "CTRL_PL", "CTRL_BS"
            };
            private static final String PUNCT_TABLE[] = {
/*  62*/        "", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", 
/*  62*/        "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", 
/*  62*/        "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", 
/*  62*/        "}", "CTRL_UL"
            };
            private static final String DIGIT_TABLE[] = {
/*  67*/        "CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", 
/*  67*/        "8", "9", ",", ".", "CTRL_UL", "CTRL_US"
            };
            private AztecDetectorResult ddata;

}
