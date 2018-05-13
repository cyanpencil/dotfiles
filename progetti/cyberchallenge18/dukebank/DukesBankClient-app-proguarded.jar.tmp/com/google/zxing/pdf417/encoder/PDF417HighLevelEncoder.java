// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PDF417HighLevelEncoder.java

package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

// Referenced classes of package com.google.zxing.pdf417.encoder:
//            Compaction

final class PDF417HighLevelEncoder
{

            private PDF417HighLevelEncoder()
            {
            }

            static String encodeHighLevel(String s, Compaction compaction, Charset charset)
                throws WriterException
            {
/* 162*/        StringBuilder stringbuilder = new StringBuilder(s.length());
                CharacterSetECI characterseteci;
/* 164*/        if(!DEFAULT_ENCODING.equals(charset) && (characterseteci = CharacterSetECI.getCharacterSetECIByName(charset.name())) != null)
/* 167*/            encodingECI(characterseteci.getValue(), stringbuilder);
/* 171*/        int i = s.length();
/* 172*/        int j = 0;
/* 173*/        int k = 0;
/* 176*/        byte abyte0[] = null;
/* 177*/        if(compaction == Compaction.TEXT)
/* 178*/            encodeText(s, 0, i, stringbuilder, 0);
/* 180*/        else
/* 180*/        if(compaction == Compaction.BYTE)
/* 181*/            encodeBinary(abyte0 = s.getBytes(charset), 0, abyte0.length, 1, stringbuilder);
/* 184*/        else
/* 184*/        if(compaction == Compaction.NUMERIC)
                {
/* 185*/            stringbuilder.append('\u0386');
/* 186*/            encodeNumeric(s, 0, i, stringbuilder);
                } else
                {
/* 189*/            compaction = 0;
                    int l;
                    int j1;
/* 190*/            while(j < i) 
/* 191*/                if((l = determineConsecutiveDigitCount(s, j)) >= 13)
                        {
/* 193*/                    stringbuilder.append('\u0386');
/* 194*/                    compaction = 2;
/* 195*/                    k = 0;
/* 196*/                    encodeNumeric(s, j, l, stringbuilder);
/* 197*/                    j += l;
                        } else
/* 199*/                if((j1 = determineConsecutiveTextCount(s, j)) >= 5 || l == i)
                        {
/* 201*/                    if(compaction != 0)
                            {
/* 202*/                        stringbuilder.append('\u0384');
/* 203*/                        compaction = 0;
/* 204*/                        k = 0;
                            }
/* 206*/                    k = encodeText(s, j, j1, stringbuilder, k);
/* 207*/                    j += j1;
                        } else
                        {
/* 209*/                    if(abyte0 == null)
/* 210*/                        abyte0 = s.getBytes(charset);
                            int i1;
/* 212*/                    if((i1 = determineConsecutiveBinaryCount(s, abyte0, j)) == 0)
/* 214*/                        i1 = 1;
/* 216*/                    if(i1 == 1 && compaction == 0)
                            {
/* 218*/                        encodeBinary(abyte0, j, 1, 0, stringbuilder);
                            } else
                            {
/* 221*/                        encodeBinary(abyte0, j, i1, compaction, stringbuilder);
/* 222*/                        compaction = 1;
/* 223*/                        k = 0;
                            }
/* 225*/                    j += i1;
                        }
                }
/* 231*/        return stringbuilder.toString();
            }

            private static int encodeText(CharSequence charsequence, int i, int j, StringBuilder stringbuilder, int k)
            {
/* 250*/        StringBuilder stringbuilder1 = new StringBuilder(j);
/* 251*/        k = k;
/* 252*/        int l = 0;
/* 254*/        do
/* 254*/label0:
/* 254*/            do
                    {
/* 254*/                char c = charsequence.charAt(i + l);
/* 255*/                switch(k)
                        {
/* 257*/                case 0: // '\0'
/* 257*/                    if(isAlphaUpper(c))
                            {
/* 258*/                        if(c == ' ')
/* 259*/                            stringbuilder1.append('\032');
/* 261*/                        else
/* 261*/                            stringbuilder1.append((char)(c - 65));
/* 261*/                        break label0;
                            }
/* 264*/                    if(isAlphaLower(c))
                            {
/* 265*/                        k = 1;
/* 266*/                        stringbuilder1.append('\033');
/* 267*/                        continue;
                            }
/* 268*/                    if(isMixed(c))
                            {
/* 269*/                        k = 2;
/* 270*/                        stringbuilder1.append('\034');
/* 271*/                        continue;
                            }
/* 273*/                    stringbuilder1.append('\035');
/* 274*/                    stringbuilder1.append((char)PUNCTUATION[c]);
/* 275*/                    break label0;

/* 280*/                case 1: // '\001'
/* 280*/                    if(isAlphaLower(c))
                            {
/* 281*/                        if(c == ' ')
/* 282*/                            stringbuilder1.append('\032');
/* 284*/                        else
/* 284*/                            stringbuilder1.append((char)(c - 97));
/* 284*/                        break label0;
                            }
/* 287*/                    if(isAlphaUpper(c))
                            {
/* 288*/                        stringbuilder1.append('\033');
/* 289*/                        stringbuilder1.append((char)(c - 65));
/* 291*/                        break label0;
                            }
/* 292*/                    if(isMixed(c))
                            {
/* 293*/                        k = 2;
/* 294*/                        stringbuilder1.append('\034');
/* 295*/                        continue;
                            }
/* 297*/                    stringbuilder1.append('\035');
/* 298*/                    stringbuilder1.append((char)PUNCTUATION[c]);
/* 299*/                    break label0;

/* 304*/                case 2: // '\002'
/* 304*/                    if(isMixed(c))
                            {
/* 305*/                        stringbuilder1.append((char)MIXED[c]);
/* 305*/                        break label0;
                            }
/* 307*/                    if(isAlphaUpper(c))
                            {
/* 308*/                        k = 0;
/* 309*/                        stringbuilder1.append('\034');
/* 310*/                        continue;
                            }
/* 311*/                    if(isAlphaLower(c))
                            {
/* 312*/                        k = 1;
/* 313*/                        stringbuilder1.append('\033');
/* 314*/                        continue;
                            }
                            char c2;
/* 316*/                    if(i + l + 1 < j && isPunctuation(c2 = charsequence.charAt(i + l + 1)))
                            {
/* 319*/                        k = 3;
/* 320*/                        stringbuilder1.append('\031');
/* 321*/                        continue;
                            }
/* 324*/                    stringbuilder1.append('\035');
/* 325*/                    stringbuilder1.append((char)PUNCTUATION[c]);
/* 328*/                    break label0;
                        }
/* 330*/                if(isPunctuation(c))
                        {
/* 331*/                    stringbuilder1.append((char)PUNCTUATION[c]);
/* 331*/                    break;
                        }
/* 333*/                k = 0;
/* 334*/                stringbuilder1.append('\035');
                    } while(true);
/* 339*/        while(++l < j);
/* 343*/        char c1 = '\0';
/* 344*/        int i1 = stringbuilder1.length();
/* 345*/        for(charsequence = 0; charsequence < i1; charsequence++)
/* 346*/            if((i = charsequence % 2 == 0 ? 0 : 1) != 0)
                    {
/* 348*/                c1 = (char)(c1 * 30 + stringbuilder1.charAt(charsequence));
/* 349*/                stringbuilder.append(c1);
                    } else
                    {
/* 351*/                c1 = stringbuilder1.charAt(charsequence);
                    }

/* 354*/        if(i1 % 2 != 0)
/* 355*/            stringbuilder.append((char)(c1 * 30 + 29));
/* 357*/        return k;
            }

            private static void encodeBinary(byte abyte0[], int i, int j, int k, StringBuilder stringbuilder)
            {
/* 376*/        if(j == 1 && k == 0)
/* 377*/            stringbuilder.append('\u0391');
/* 379*/        else
/* 379*/        if((k = j % 6 != 0 ? 0 : 1) != 0)
/* 381*/            stringbuilder.append('\u039C');
/* 383*/        else
/* 383*/            stringbuilder.append('\u0385');
/* 387*/        k = i;
/* 389*/        if(j >= 6)
                {
/* 390*/            char ac[] = new char[5];
/* 391*/            for(; (i + j) - k >= 6; k += 6)
                    {
/* 392*/                long l1 = 0L;
/* 393*/                for(int i1 = 0; i1 < 6; i1++)
/* 394*/                    l1 = (l1 <<= 8) + (long)(abyte0[k + i1] & 0xff);

/* 397*/                for(int j1 = 0; j1 < 5; j1++)
                        {
/* 398*/                    ac[j1] = (char)(int)(l1 % 900L);
/* 399*/                    l1 /= 900L;
                        }

/* 401*/                for(int k1 = 4; k1 >= 0; k1--)
/* 402*/                    stringbuilder.append(ac[k1]);

                    }

                }
/* 408*/        for(int l = k; l < i + j; l++)
                {
/* 409*/            int i2 = abyte0[l] & 0xff;
/* 410*/            stringbuilder.append((char)i2);
                }

            }

            private static void encodeNumeric(String s, int i, int j, StringBuilder stringbuilder)
            {
/* 415*/        int k = 0;
/* 416*/        StringBuilder stringbuilder1 = new StringBuilder(j / 3 + 1);
/* 417*/        BigInteger biginteger = BigInteger.valueOf(900L);
/* 418*/        BigInteger biginteger1 = BigInteger.valueOf(0L);
                int l;
/* 419*/        for(; k < j - 1; k += l)
                {
/* 420*/            stringbuilder1.setLength(0);
/* 421*/            l = Math.min(44, j - k);
/* 422*/            Object obj = (new StringBuilder("1")).append(s.substring(i + k, i + k + l)).toString();
/* 423*/            obj = new BigInteger(((String) (obj)));
/* 425*/            do
/* 425*/                stringbuilder1.append((char)((BigInteger) (obj)).mod(biginteger).intValue());
/* 426*/            while(!((BigInteger) (obj = ((BigInteger) (obj)).divide(biginteger))).equals(biginteger1));
/* 430*/            for(int i1 = stringbuilder1.length() - 1; i1 >= 0; i1--)
/* 431*/                stringbuilder.append(stringbuilder1.charAt(i1));

                }

            }

            private static boolean isDigit(char c)
            {
/* 439*/        return c >= '0' && c <= '9';
            }

            private static boolean isAlphaUpper(char c)
            {
/* 443*/        return c == ' ' || c >= 'A' && c <= 'Z';
            }

            private static boolean isAlphaLower(char c)
            {
/* 447*/        return c == ' ' || c >= 'a' && c <= 'z';
            }

            private static boolean isMixed(char c)
            {
/* 451*/        return MIXED[c] != -1;
            }

            private static boolean isPunctuation(char c)
            {
/* 455*/        return PUNCTUATION[c] != -1;
            }

            private static boolean isText(char c)
            {
/* 459*/        return c == '\t' || c == '\n' || c == '\r' || c >= ' ' && c <= '~';
            }

            private static int determineConsecutiveDigitCount(CharSequence charsequence, int i)
            {
/* 470*/        int j = 0;
/* 471*/        int k = charsequence.length();
/* 472*/        int l = i;
/* 473*/        if(i < k)
                {
/* 474*/            i = charsequence.charAt(i);
/* 475*/            do
                    {
/* 475*/                if(!isDigit(i) || l >= k)
/* 476*/                    break;
/* 476*/                j++;
/* 477*/                if(++l < k)
/* 479*/                    i = charsequence.charAt(l);
                    } while(true);
                }
/* 483*/        return j;
            }

            private static int determineConsecutiveTextCount(CharSequence charsequence, int i)
            {
/* 494*/        int j = charsequence.length();
/* 495*/        int k = i;
/* 496*/        do
                {
/* 496*/            if(k >= j)
/* 497*/                break;
/* 497*/            char c = charsequence.charAt(k);
/* 498*/            int l = 0;
/* 499*/            do
                    {
/* 499*/                if(l >= 13 || !isDigit(c) || k >= j)
/* 500*/                    break;
/* 500*/                l++;
/* 501*/                if(++k < j)
/* 503*/                    c = charsequence.charAt(k);
                    } while(true);
/* 506*/            if(l >= 13)
/* 507*/                return k - i - l;
/* 509*/            if(l > 0)
/* 513*/                continue;
/* 513*/            if(!isText(c = charsequence.charAt(k)))
/* 519*/                break;
/* 519*/            k++;
                } while(true);
/* 521*/        return k - i;
            }

            private static int determineConsecutiveBinaryCount(CharSequence charsequence, byte abyte0[], int i)
                throws WriterException
            {
/* 534*/        int j = charsequence.length();
                int k;
/* 535*/        for(k = i; k < j; k++)
                {
/* 537*/            int l = charsequence.charAt(k);
/* 538*/            int i1 = 0;
/* 540*/            do
                    {
/* 540*/                if(i1 >= 13 || !isDigit(l))
/* 541*/                    break;
/* 541*/                i1++;
                        int j1;
/* 543*/                if((j1 = k + i1) >= j)
/* 547*/                    break;
/* 547*/                l = charsequence.charAt(j1);
                    } while(true);
/* 549*/            if(i1 >= 13)
/* 550*/                return k - i;
/* 552*/            int k1 = 0;
/* 553*/            do
                    {
/* 553*/                if(k1 >= 5 || !isText(l))
/* 554*/                    break;
/* 554*/                k1++;
/* 555*/                if((l = k + k1) >= j)
/* 559*/                    break;
/* 559*/                l = charsequence.charAt(l);
                    } while(true);
/* 561*/            if(k1 >= 5)
/* 562*/                return k - i;
/* 564*/            l = charsequence.charAt(k);
/* 569*/            if(abyte0[k] == 63 && l != '?')
/* 570*/                throw new WriterException((new StringBuilder("Non-encodable character detected: ")).append(l).append(" (Unicode: ").append(l).append(')').toString());
                }

/* 574*/        return k - i;
            }

            private static void encodingECI(int i, StringBuilder stringbuilder)
                throws WriterException
            {
/* 578*/        if(i >= 0 && i < 900)
                {
/* 579*/            stringbuilder.append('\u039F');
/* 580*/            stringbuilder.append((char)i);
/* 580*/            return;
                }
/* 581*/        if(i < 0xc5f94)
                {
/* 582*/            stringbuilder.append('\u039E');
/* 583*/            stringbuilder.append((char)(i / 900 - 1));
/* 584*/            stringbuilder.append((char)(i % 900));
/* 584*/            return;
                }
/* 585*/        if(i < 0xc6318)
                {
/* 586*/            stringbuilder.append('\u039D');
/* 587*/            stringbuilder.append((char)(0xc5f94 - i));
/* 587*/            return;
                } else
                {
/* 589*/            throw new WriterException((new StringBuilder("ECI number not in valid range from 0..811799, but was ")).append(i).toString());
                }
            }

            private static final int TEXT_COMPACTION = 0;
            private static final int BYTE_COMPACTION = 1;
            private static final int NUMERIC_COMPACTION = 2;
            private static final int SUBMODE_ALPHA = 0;
            private static final int SUBMODE_LOWER = 1;
            private static final int SUBMODE_MIXED = 2;
            private static final int SUBMODE_PUNCTUATION = 3;
            private static final int LATCH_TO_TEXT = 900;
            private static final int LATCH_TO_BYTE_PADDED = 901;
            private static final int LATCH_TO_NUMERIC = 902;
            private static final int SHIFT_TO_BYTE = 913;
            private static final int LATCH_TO_BYTE = 924;
            private static final int ECI_USER_DEFINED = 925;
            private static final int ECI_GENERAL_PURPOSE = 926;
            private static final int ECI_CHARSET = 927;
            private static final byte TEXT_MIXED_RAW[] = {
/* 114*/        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
/* 114*/        38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 
/* 114*/        43, 37, 42, 61, 94, 0, 32, 0, 0, 0
            };
            private static final byte TEXT_PUNCTUATION_RAW[] = {
/* 121*/        59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 
/* 121*/        33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 
/* 121*/        34, 124, 42, 40, 41, 63, 123, 125, 39, 0
            };
            private static final byte MIXED[];
            private static final byte PUNCTUATION[];
            static final Charset DEFAULT_ENCODING = Charset.forName("Cp437");

            static 
            {
/* 125*/        MIXED = new byte[128];
/* 126*/        PUNCTUATION = new byte[128];
/* 135*/        Arrays.fill(MIXED, (byte)-1);
/* 136*/        for(byte byte0 = 0; byte0 < TEXT_MIXED_RAW.length; byte0++)
                {
                    byte byte2;
/* 137*/            if((byte2 = TEXT_MIXED_RAW[byte0]) > 0)
/* 139*/                MIXED[byte2] = byte0;
                }

/* 142*/        Arrays.fill(PUNCTUATION, (byte)-1);
/* 143*/        for(byte byte1 = 0; byte1 < TEXT_PUNCTUATION_RAW.length; byte1++)
                {
                    byte byte3;
/* 144*/            if((byte3 = TEXT_PUNCTUATION_RAW[byte1]) > 0)
/* 146*/                PUNCTUATION[byte3] = byte1;
                }

            }
}
