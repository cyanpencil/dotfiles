// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedBitStreamParser.java

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import java.io.UnsupportedEncodingException;
import java.util.*;

final class DecodedBitStreamParser
{
    static final class Mode extends Enum
    {

                public static Mode[] values()
                {
/*  39*/            return (Mode[])$VALUES.clone();
                }

                public static Mode valueOf(String s)
                {
/*  39*/            return (Mode)Enum.valueOf(com/google/zxing/datamatrix/decoder/DecodedBitStreamParser$Mode, s);
                }

                public static final Mode PAD_ENCODE;
                public static final Mode ASCII_ENCODE;
                public static final Mode C40_ENCODE;
                public static final Mode TEXT_ENCODE;
                public static final Mode ANSIX12_ENCODE;
                public static final Mode EDIFACT_ENCODE;
                public static final Mode BASE256_ENCODE;
                private static final Mode $VALUES[];

                static 
                {
/*  40*/            PAD_ENCODE = new Mode("PAD_ENCODE", 0);
/*  41*/            ASCII_ENCODE = new Mode("ASCII_ENCODE", 1);
/*  42*/            C40_ENCODE = new Mode("C40_ENCODE", 2);
/*  43*/            TEXT_ENCODE = new Mode("TEXT_ENCODE", 3);
/*  44*/            ANSIX12_ENCODE = new Mode("ANSIX12_ENCODE", 4);
/*  45*/            EDIFACT_ENCODE = new Mode("EDIFACT_ENCODE", 5);
/*  46*/            BASE256_ENCODE = new Mode("BASE256_ENCODE", 6);
/*  39*/            $VALUES = (new Mode[] {
/*  39*/                PAD_ENCODE, ASCII_ENCODE, C40_ENCODE, TEXT_ENCODE, ANSIX12_ENCODE, EDIFACT_ENCODE, BASE256_ENCODE
                    });
                }

                private Mode(String s, int i)
                {
/*  39*/            super(s, i);
                }
    }


            private DecodedBitStreamParser()
            {
            }

            static DecoderResult decode(byte abyte0[])
                throws FormatException
            {
/*  83*/        BitSource bitsource = new BitSource(abyte0);
/*  84*/        StringBuilder stringbuilder = new StringBuilder(100);
/*  85*/        StringBuilder stringbuilder1 = new StringBuilder(0);
/*  86*/        ArrayList arraylist = new ArrayList(1);
/*  87*/        Mode mode = Mode.ASCII_ENCODE;
/*  89*/        do
/*  89*/            if(mode == Mode.ASCII_ENCODE)
                    {
/*  90*/                mode = decodeAsciiSegment(bitsource, stringbuilder, stringbuilder1);
                    } else
                    {
                static class _cls1
                {

                            static final int $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[];

                            static 
                            {
/*  92*/                        $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode = new int[Mode.values().length];
/*  92*/                        try
                                {
/*  92*/                            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[Mode.C40_ENCODE.ordinal()] = 1;
                                }
/*  92*/                        catch(NoSuchFieldError _ex) { }
/*  92*/                        try
                                {
/*  92*/                            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[Mode.TEXT_ENCODE.ordinal()] = 2;
                                }
/*  92*/                        catch(NoSuchFieldError _ex) { }
/*  92*/                        try
                                {
/*  92*/                            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[Mode.ANSIX12_ENCODE.ordinal()] = 3;
                                }
/*  92*/                        catch(NoSuchFieldError _ex) { }
/*  92*/                        try
                                {
/*  92*/                            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[Mode.EDIFACT_ENCODE.ordinal()] = 4;
                                }
/*  92*/                        catch(NoSuchFieldError _ex) { }
/*  92*/                        try
                                {
/*  92*/                            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[Mode.BASE256_ENCODE.ordinal()] = 5;
                                }
/*  92*/                        catch(NoSuchFieldError _ex) { }
                            }
                }

/*  92*/                switch(_cls1..SwitchMap.com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode[mode.ordinal()])
                        {
/*  94*/                case 1: // '\001'
/*  94*/                    decodeC40Segment(bitsource, stringbuilder);
                            break;

/*  97*/                case 2: // '\002'
/*  97*/                    decodeTextSegment(bitsource, stringbuilder);
                            break;

/* 100*/                case 3: // '\003'
/* 100*/                    decodeAnsiX12Segment(bitsource, stringbuilder);
                            break;

/* 103*/                case 4: // '\004'
/* 103*/                    decodeEdifactSegment(bitsource, stringbuilder);
                            break;

/* 106*/                case 5: // '\005'
/* 106*/                    decodeBase256Segment(bitsource, stringbuilder, arraylist);
                            break;

/* 109*/                default:
/* 109*/                    throw FormatException.getFormatInstance();
                        }
/* 111*/                mode = Mode.ASCII_ENCODE;
                    }
/* 113*/        while(mode != Mode.PAD_ENCODE && bitsource.available() > 0);
/* 114*/        if(stringbuilder1.length() > 0)
/* 115*/            stringbuilder.append(stringbuilder1);
/* 117*/        return new DecoderResult(abyte0, stringbuilder.toString(), arraylist.isEmpty() ? null : ((List) (arraylist)), null);
            }

            private static Mode decodeAsciiSegment(BitSource bitsource, StringBuilder stringbuilder, StringBuilder stringbuilder1)
                throws FormatException
            {
/* 126*/        boolean flag = false;
/* 128*/        do
                {
                    int i;
/* 128*/            if((i = bitsource.readBits(8)) == 0)
/* 130*/                throw FormatException.getFormatInstance();
/* 131*/            if(i <= 128)
                    {
/* 132*/                if(flag)
/* 133*/                    i += 128;
/* 136*/                stringbuilder.append((char)(i - 1));
/* 137*/                return Mode.ASCII_ENCODE;
                    }
/* 138*/            if(i == 129)
/* 139*/                return Mode.PAD_ENCODE;
/* 140*/            if(i <= 229)
                    {
/* 141*/                if((i -= 130) < 10)
/* 143*/                    stringbuilder.append('0');
/* 145*/                stringbuilder.append(i);
                    } else
                    {
/* 146*/                if(i == 230)
/* 147*/                    return Mode.C40_ENCODE;
/* 148*/                if(i == 231)
/* 149*/                    return Mode.BASE256_ENCODE;
/* 150*/                if(i == 232)
/* 152*/                    stringbuilder.append('\035');
/* 153*/                else
/* 153*/                if(i != 233 && i != 234)
/* 157*/                    if(i == 235)
/* 158*/                        flag = true;
/* 159*/                    else
/* 159*/                    if(i == 236)
                            {
/* 160*/                        stringbuilder.append("[)>\03605\035");
/* 161*/                        stringbuilder1.insert(0, "\036\004");
                            } else
/* 162*/                    if(i == 237)
                            {
/* 163*/                        stringbuilder.append("[)>\03606\035");
/* 164*/                        stringbuilder1.insert(0, "\036\004");
                            } else
                            {
/* 165*/                        if(i == 238)
/* 166*/                            return Mode.ANSIX12_ENCODE;
/* 167*/                        if(i == 239)
/* 168*/                            return Mode.TEXT_ENCODE;
/* 169*/                        if(i == 240)
/* 170*/                            return Mode.EDIFACT_ENCODE;
/* 171*/                        if(i != 241 && i >= 242 && (i != 254 || bitsource.available() != 0))
/* 178*/                            throw FormatException.getFormatInstance();
                            }
                    }
                } while(bitsource.available() > 0);
/* 182*/        return Mode.ASCII_ENCODE;
            }

            private static void decodeC40Segment(BitSource bitsource, StringBuilder stringbuilder)
                throws FormatException
            {
/* 192*/        boolean flag = false;
/* 194*/        int ai[] = new int[3];
/* 195*/        int i = 0;
/* 199*/        do
                {
/* 199*/            if(bitsource.available() == 8)
/* 200*/                return;
                    int j;
/* 202*/            if((j = bitsource.readBits(8)) == 254)
/* 204*/                return;
/* 207*/            parseTwoBytes(j, bitsource.readBits(8), ai);
/* 209*/            for(int k = 0; k < 3; k++)
                    {
/* 210*/                int l = ai[k];
/* 211*/                switch(i)
                        {
/* 213*/                case 0: // '\0'
/* 213*/                    if(l < 3)
                            {
/* 214*/                        i = l + 1;
/* 214*/                        break;
                            }
/* 215*/                    if(l < C40_BASIC_SET_CHARS.length)
                            {
/* 216*/                        l = C40_BASIC_SET_CHARS[l];
/* 217*/                        if(flag)
                                {
/* 218*/                            stringbuilder.append((char)(l + 128));
/* 219*/                            flag = false;
                                } else
                                {
/* 221*/                            stringbuilder.append(l);
                                }
                            } else
                            {
/* 224*/                        throw FormatException.getFormatInstance();
                            }
/* 228*/                    break;

/* 228*/                case 1: // '\001'
/* 228*/                    if(flag)
                            {
/* 229*/                        stringbuilder.append((char)(l + 128));
/* 230*/                        flag = false;
                            } else
                            {
/* 232*/                        stringbuilder.append((char)l);
                            }
/* 234*/                    i = 0;
/* 235*/                    break;

/* 237*/                case 2: // '\002'
/* 237*/                    if(l < C40_SHIFT2_SET_CHARS.length)
                            {
/* 238*/                        l = C40_SHIFT2_SET_CHARS[l];
/* 239*/                        if(flag)
                                {
/* 240*/                            stringbuilder.append((char)(l + 128));
/* 241*/                            flag = false;
                                } else
                                {
/* 243*/                            stringbuilder.append(l);
                                }
                            } else
/* 245*/                    if(l == 27)
/* 246*/                        stringbuilder.append('\035');
/* 247*/                    else
/* 247*/                    if(l == 30)
/* 248*/                        flag = true;
/* 250*/                    else
/* 250*/                        throw FormatException.getFormatInstance();
/* 252*/                    i = 0;
/* 253*/                    break;

/* 255*/                case 3: // '\003'
/* 255*/                    if(flag)
                            {
/* 256*/                        stringbuilder.append((char)(l + 224));
/* 257*/                        flag = false;
                            } else
                            {
/* 259*/                        stringbuilder.append((char)(l + 96));
                            }
/* 261*/                    i = 0;
                            break;

/* 264*/                default:
/* 264*/                    throw FormatException.getFormatInstance();
                        }
                    }

                } while(bitsource.available() > 0);
            }

            private static void decodeTextSegment(BitSource bitsource, StringBuilder stringbuilder)
                throws FormatException
            {
/* 277*/        boolean flag = false;
/* 279*/        int ai[] = new int[3];
/* 280*/        int i = 0;
/* 283*/        do
                {
/* 283*/            if(bitsource.available() == 8)
/* 284*/                return;
                    int j;
/* 286*/            if((j = bitsource.readBits(8)) == 254)
/* 288*/                return;
/* 291*/            parseTwoBytes(j, bitsource.readBits(8), ai);
/* 293*/            for(int k = 0; k < 3; k++)
                    {
/* 294*/                int l = ai[k];
/* 295*/                switch(i)
                        {
/* 297*/                case 0: // '\0'
/* 297*/                    if(l < 3)
                            {
/* 298*/                        i = l + 1;
/* 298*/                        break;
                            }
/* 299*/                    if(l < TEXT_BASIC_SET_CHARS.length)
                            {
/* 300*/                        l = TEXT_BASIC_SET_CHARS[l];
/* 301*/                        if(flag)
                                {
/* 302*/                            stringbuilder.append((char)(l + 128));
/* 303*/                            flag = false;
                                } else
                                {
/* 305*/                            stringbuilder.append(l);
                                }
                            } else
                            {
/* 308*/                        throw FormatException.getFormatInstance();
                            }
/* 312*/                    break;

/* 312*/                case 1: // '\001'
/* 312*/                    if(flag)
                            {
/* 313*/                        stringbuilder.append((char)(l + 128));
/* 314*/                        flag = false;
                            } else
                            {
/* 316*/                        stringbuilder.append((char)l);
                            }
/* 318*/                    i = 0;
/* 319*/                    break;

/* 322*/                case 2: // '\002'
/* 322*/                    if(l < C40_SHIFT2_SET_CHARS.length)
                            {
/* 323*/                        l = C40_SHIFT2_SET_CHARS[l];
/* 324*/                        if(flag)
                                {
/* 325*/                            stringbuilder.append((char)(l + 128));
/* 326*/                            flag = false;
                                } else
                                {
/* 328*/                            stringbuilder.append(l);
                                }
                            } else
/* 330*/                    if(l == 27)
/* 331*/                        stringbuilder.append('\035');
/* 332*/                    else
/* 332*/                    if(l == 30)
/* 333*/                        flag = true;
/* 335*/                    else
/* 335*/                        throw FormatException.getFormatInstance();
/* 337*/                    i = 0;
/* 338*/                    break;

/* 340*/                case 3: // '\003'
/* 340*/                    if(l < TEXT_SHIFT3_SET_CHARS.length)
                            {
/* 341*/                        l = TEXT_SHIFT3_SET_CHARS[l];
/* 342*/                        if(flag)
                                {
/* 343*/                            stringbuilder.append((char)(l + 128));
/* 344*/                            flag = false;
                                } else
                                {
/* 346*/                            stringbuilder.append(l);
                                }
/* 348*/                        i = 0;
                            } else
                            {
/* 350*/                        throw FormatException.getFormatInstance();
                            }
                            break;

/* 354*/                default:
/* 354*/                    throw FormatException.getFormatInstance();
                        }
                    }

                } while(bitsource.available() > 0);
            }

            private static void decodeAnsiX12Segment(BitSource bitsource, StringBuilder stringbuilder)
                throws FormatException
            {
/* 368*/        int ai[] = new int[3];
/* 371*/        do
                {
/* 371*/            if(bitsource.available() == 8)
/* 372*/                return;
                    int i;
/* 374*/            if((i = bitsource.readBits(8)) == 254)
/* 376*/                return;
/* 379*/            parseTwoBytes(i, bitsource.readBits(8), ai);
/* 381*/            for(int j = 0; j < 3; j++)
                    {
                        int k;
/* 382*/                if((k = ai[j]) == 0)
                        {
/* 384*/                    stringbuilder.append('\r');
/* 384*/                    continue;
                        }
/* 385*/                if(k == 1)
                        {
/* 386*/                    stringbuilder.append('*');
/* 386*/                    continue;
                        }
/* 387*/                if(k == 2)
                        {
/* 388*/                    stringbuilder.append('>');
/* 388*/                    continue;
                        }
/* 389*/                if(k == 3)
                        {
/* 390*/                    stringbuilder.append(' ');
/* 390*/                    continue;
                        }
/* 391*/                if(k < 14)
                        {
/* 392*/                    stringbuilder.append((char)(k + 44));
/* 392*/                    continue;
                        }
/* 393*/                if(k < 40)
/* 394*/                    stringbuilder.append((char)(k + 51));
/* 396*/                else
/* 396*/                    throw FormatException.getFormatInstance();
                    }

                } while(bitsource.available() > 0);
            }

            private static void parseTwoBytes(int i, int j, int ai[])
            {
/* 403*/        j = (i = ((i << 8) + j) - 1) / 1600;
/* 405*/        ai[0] = j;
/* 406*/        j = (i -= j * 1600) / 40;
/* 408*/        ai[1] = j;
/* 409*/        ai[2] = i - j * 40;
            }

            private static void decodeEdifactSegment(BitSource bitsource, StringBuilder stringbuilder)
            {
/* 418*/        do
                {
/* 418*/            if(bitsource.available() <= 16)
/* 419*/                return;
/* 422*/            for(int i = 0; i < 4; i++)
                    {
                        int j;
/* 423*/                if((j = bitsource.readBits(6)) == 31)
                        {
/* 428*/                    if((stringbuilder = 8 - bitsource.getBitOffset()) != 8)
/* 430*/                        bitsource.readBits(stringbuilder);
/* 432*/                    return;
                        }
/* 435*/                if((j & 0x20) == 0)
/* 436*/                    j |= 0x40;
/* 438*/                stringbuilder.append((char)j);
                    }

                } while(bitsource.available() > 0);
            }

            private static void decodeBase256Segment(BitSource bitsource, StringBuilder stringbuilder, Collection collection)
                throws FormatException
            {
/* 451*/        int i = 1 + bitsource.getByteOffset();
                int j;
/* 452*/        if((j = unrandomize255State(bitsource.readBits(8), i++)) == 0)
/* 455*/            j = bitsource.available() / 8;
/* 456*/        else
/* 456*/        if(j < 250)
/* 457*/            j = j;
/* 459*/        else
/* 459*/            j = 250 * (j - 249) + unrandomize255State(bitsource.readBits(8), i++);
/* 463*/        if(j < 0)
/* 464*/            throw FormatException.getFormatInstance();
/* 467*/        byte abyte0[] = new byte[j];
/* 468*/        for(int k = 0; k < j; k++)
                {
/* 471*/            if(bitsource.available() < 8)
/* 472*/                throw FormatException.getFormatInstance();
/* 474*/            abyte0[k] = (byte)unrandomize255State(bitsource.readBits(8), i++);
                }

/* 476*/        collection.add(abyte0);
/* 478*/        try
                {
/* 478*/            stringbuilder.append(new String(abyte0, "ISO8859_1"));
/* 481*/            return;
                }
/* 479*/        catch(UnsupportedEncodingException unsupportedencodingexception)
                {
/* 480*/            throw new IllegalStateException((new StringBuilder("Platform does not support required encoding: ")).append(unsupportedencodingexception).toString());
                }
            }

            private static int unrandomize255State(int i, int j)
            {
/* 489*/        j = (j * 149) % 255 + 1;
/* 490*/        if((i -= j) >= 0)
/* 491*/            return i;
/* 491*/        else
/* 491*/            return i + 256;
            }

            private static final char C40_BASIC_SET_CHARS[] = {
/*  53*/        '*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', 
/*  53*/        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 
/*  53*/        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
/*  53*/        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            };
            private static final char C40_SHIFT2_SET_CHARS[] = {
/*  59*/        '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', 
/*  59*/        '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', 
/*  59*/        '?', '@', '[', '\\', ']', '^', '_'
            };
            private static final char TEXT_BASIC_SET_CHARS[] = {
/*  68*/        '*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', 
/*  68*/        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 
/*  68*/        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 
/*  68*/        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            };
            private static final char TEXT_SHIFT3_SET_CHARS[] = {
/*  74*/        '`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
/*  74*/        'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
/*  74*/        'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', 
/*  74*/        '~', '\177'
            };

}
