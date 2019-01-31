// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedBitStreamParser.java

package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.*;
import java.io.UnsupportedEncodingException;
import java.util.*;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            ErrorCorrectionLevel, Mode, Version

final class DecodedBitStreamParser
{

            private DecodedBitStreamParser()
            {
            }

            static DecoderResult decode(byte abyte0[], Version version, ErrorCorrectionLevel errorcorrectionlevel, Map map)
                throws FormatException
            {
/*  60*/        BitSource bitsource = new BitSource(abyte0);
/*  61*/        StringBuilder stringbuilder = new StringBuilder(50);
/*  62*/        ArrayList arraylist = new ArrayList(1);
/*  63*/        int i = -1;
/*  64*/        int j = -1;
/*  67*/        try
                {
/*  67*/            CharacterSetECI characterseteci = null;
/*  68*/            boolean flag = false;
                    Mode mode;
/*  72*/            do
                    {
/*  72*/                if(bitsource.available() < 4)
/*  74*/                    mode = Mode.TERMINATOR;
/*  76*/                else
/*  76*/                    mode = Mode.forBits(bitsource.readBits(4));
                        int k;
/*  78*/                if(mode != Mode.TERMINATOR)
/*  79*/                    if(mode == Mode.FNC1_FIRST_POSITION || mode == Mode.FNC1_SECOND_POSITION)
/*  81*/                        flag = true;
/*  82*/                    else
/*  82*/                    if(mode == Mode.STRUCTURED_APPEND)
                            {
/*  83*/                        if(bitsource.available() < 16)
/*  84*/                            throw FormatException.getFormatInstance();
/*  88*/                        i = bitsource.readBits(8);
/*  89*/                        j = bitsource.readBits(8);
                            } else
/*  90*/                    if(mode == Mode.ECI)
                            {
/*  92*/                        if((characterseteci = CharacterSetECI.getCharacterSetECIByValue(k = parseECIValue(bitsource))) == null)
/*  95*/                            throw FormatException.getFormatInstance();
                            } else
/*  99*/                    if(mode == Mode.HANZI)
                            {
/* 101*/                        int l = bitsource.readBits(4);
/* 102*/                        int j1 = bitsource.readBits(mode.getCharacterCountBits(version));
/* 103*/                        if(l == 1)
/* 104*/                            decodeHanziSegment(bitsource, stringbuilder, j1);
                            } else
                            {
/* 109*/                        int i1 = bitsource.readBits(mode.getCharacterCountBits(version));
/* 110*/                        if(mode == Mode.NUMERIC)
/* 111*/                            decodeNumericSegment(bitsource, stringbuilder, i1);
/* 112*/                        else
/* 112*/                        if(mode == Mode.ALPHANUMERIC)
/* 113*/                            decodeAlphanumericSegment(bitsource, stringbuilder, i1, flag);
/* 114*/                        else
/* 114*/                        if(mode == Mode.BYTE)
/* 115*/                            decodeByteSegment(bitsource, stringbuilder, i1, characterseteci, arraylist, map);
/* 116*/                        else
/* 116*/                        if(mode == Mode.KANJI)
/* 117*/                            decodeKanjiSegment(bitsource, stringbuilder, i1);
/* 119*/                        else
/* 119*/                            throw FormatException.getFormatInstance();
                            }
                    } while(mode != Mode.TERMINATOR);
                }
/* 125*/        catch(IllegalArgumentException _ex)
                {
/* 127*/            throw FormatException.getFormatInstance();
                }
/* 130*/        return new DecoderResult(abyte0, stringbuilder.toString(), arraylist.isEmpty() ? null : ((List) (arraylist)), errorcorrectionlevel != null ? errorcorrectionlevel.toString() : null, i, j);
            }

            private static void decodeHanziSegment(BitSource bitsource, StringBuilder stringbuilder, int i)
                throws FormatException
            {
/* 145*/        if(i * 13 > bitsource.available())
/* 146*/            throw FormatException.getFormatInstance();
/* 151*/        byte abyte0[] = new byte[2 * i];
/* 152*/        int j = 0;
/* 153*/        for(; i > 0; i--)
                {
                    int k;
/* 155*/            if((k = (k = bitsource.readBits(13)) / 96 << 8 | k % 96) < 959)
/* 159*/                k += 41377;
/* 162*/            else
/* 162*/                k += 42657;
/* 164*/            abyte0[j] = (byte)(k >> 8);
/* 165*/            abyte0[j + 1] = (byte)k;
/* 166*/            j += 2;
                }

/* 171*/        try
                {
/* 171*/            stringbuilder.append(new String(abyte0, "GB2312"));
/* 174*/            return;
                }
/* 172*/        catch(UnsupportedEncodingException _ex)
                {
/* 173*/            throw FormatException.getFormatInstance();
                }
            }

            private static void decodeKanjiSegment(BitSource bitsource, StringBuilder stringbuilder, int i)
                throws FormatException
            {
/* 181*/        if(i * 13 > bitsource.available())
/* 182*/            throw FormatException.getFormatInstance();
/* 187*/        byte abyte0[] = new byte[2 * i];
/* 188*/        int j = 0;
/* 189*/        for(; i > 0; i--)
                {
                    int k;
/* 191*/            if((k = (k = bitsource.readBits(13)) / 192 << 8 | k % 192) < 7936)
/* 195*/                k += 33088;
/* 198*/            else
/* 198*/                k += 49472;
/* 200*/            abyte0[j] = (byte)(k >> 8);
/* 201*/            abyte0[j + 1] = (byte)k;
/* 202*/            j += 2;
                }

/* 207*/        try
                {
/* 207*/            stringbuilder.append(new String(abyte0, "SJIS"));
/* 210*/            return;
                }
/* 208*/        catch(UnsupportedEncodingException _ex)
                {
/* 209*/            throw FormatException.getFormatInstance();
                }
            }

            private static void decodeByteSegment(BitSource bitsource, StringBuilder stringbuilder, int i, CharacterSetECI characterseteci, Collection collection, Map map)
                throws FormatException
            {
/* 220*/        if(i << 3 > bitsource.available())
/* 221*/            throw FormatException.getFormatInstance();
/* 224*/        byte abyte0[] = new byte[i];
/* 225*/        for(int j = 0; j < i; j++)
/* 226*/            abyte0[j] = (byte)bitsource.readBits(8);

                String s;
/* 229*/        if(characterseteci == null)
/* 235*/            s = StringUtils.guessEncoding(abyte0, map);
/* 237*/        else
/* 237*/            s = characterseteci.name();
/* 240*/        try
                {
/* 240*/            stringbuilder.append(new String(abyte0, s));
                }
/* 241*/        catch(UnsupportedEncodingException _ex)
                {
/* 242*/            throw FormatException.getFormatInstance();
                }
/* 244*/        collection.add(abyte0);
            }

            private static char toAlphaNumericChar(int i)
                throws FormatException
            {
/* 248*/        if(i >= ALPHANUMERIC_CHARS.length)
/* 249*/            throw FormatException.getFormatInstance();
/* 251*/        else
/* 251*/            return ALPHANUMERIC_CHARS[i];
            }

            private static void decodeAlphanumericSegment(BitSource bitsource, StringBuilder stringbuilder, int i, boolean flag)
                throws FormatException
            {
/* 259*/        int j = stringbuilder.length();
/* 260*/        for(; i > 1; i -= 2)
                {
/* 261*/            if(bitsource.available() < 11)
/* 262*/                throw FormatException.getFormatInstance();
/* 264*/            int k = bitsource.readBits(11);
/* 265*/            stringbuilder.append(toAlphaNumericChar(k / 45));
/* 266*/            stringbuilder.append(toAlphaNumericChar(k % 45));
                }

/* 269*/        if(i == 1)
                {
/* 271*/            if(bitsource.available() < 6)
/* 272*/                throw FormatException.getFormatInstance();
/* 274*/            stringbuilder.append(toAlphaNumericChar(bitsource.readBits(6)));
                }
/* 277*/        if(flag)
                {
/* 279*/            for(int l = j; l < stringbuilder.length(); l++)
                    {
/* 280*/                if(stringbuilder.charAt(l) != '%')
/* 281*/                    continue;
/* 281*/                if(l < stringbuilder.length() - 1 && stringbuilder.charAt(l + 1) == '%')
/* 283*/                    stringbuilder.deleteCharAt(l + 1);
/* 286*/                else
/* 286*/                    stringbuilder.setCharAt(l, '\035');
                    }

                }
            }

            private static void decodeNumericSegment(BitSource bitsource, StringBuilder stringbuilder, int i)
                throws FormatException
            {
/* 297*/        for(; i >= 3; i -= 3)
                {
/* 299*/            if(bitsource.available() < 10)
/* 300*/                throw FormatException.getFormatInstance();
                    int j;
/* 302*/            if((j = bitsource.readBits(10)) >= 1000)
/* 304*/                throw FormatException.getFormatInstance();
/* 306*/            stringbuilder.append(toAlphaNumericChar(j / 100));
/* 307*/            stringbuilder.append(toAlphaNumericChar((j / 10) % 10));
/* 308*/            stringbuilder.append(toAlphaNumericChar(j % 10));
                }

/* 311*/        if(i == 2)
                {
/* 313*/            if(bitsource.available() < 7)
/* 314*/                throw FormatException.getFormatInstance();
                    int k;
/* 316*/            if((k = bitsource.readBits(7)) >= 100)
                    {
/* 318*/                throw FormatException.getFormatInstance();
                    } else
                    {
/* 320*/                stringbuilder.append(toAlphaNumericChar(k / 10));
/* 321*/                stringbuilder.append(toAlphaNumericChar(k % 10));
/* 322*/                return;
                    }
                }
/* 322*/        if(i == 1)
                {
/* 324*/            if(bitsource.available() < 4)
/* 325*/                throw FormatException.getFormatInstance();
                    int l;
/* 327*/            if((l = bitsource.readBits(4)) >= 10)
/* 329*/                throw FormatException.getFormatInstance();
/* 331*/            stringbuilder.append(toAlphaNumericChar(l));
                }
            }

            private static int parseECIValue(BitSource bitsource)
                throws FormatException
            {
                int i;
/* 336*/        if(((i = bitsource.readBits(8)) & 0x80) == 0)
/* 339*/            return i & 0x7f;
/* 341*/        if((i & 0xc0) == 128)
                {
/* 343*/            bitsource = bitsource.readBits(8);
/* 344*/            return (i & 0x3f) << 8 | bitsource;
                }
/* 346*/        if((i & 0xe0) == 192)
                {
/* 348*/            bitsource = bitsource.readBits(16);
/* 349*/            return (i & 0x1f) << 16 | bitsource;
                } else
                {
/* 351*/            throw FormatException.getFormatInstance();
                }
            }

            private static final char ALPHANUMERIC_CHARS[] = {
/*  45*/        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/*  45*/        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
/*  45*/        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
/*  45*/        'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', 
/*  45*/        '+', '-', '.', '/', ':'
            };
            private static final int GB2312_SUBSET = 1;

}
