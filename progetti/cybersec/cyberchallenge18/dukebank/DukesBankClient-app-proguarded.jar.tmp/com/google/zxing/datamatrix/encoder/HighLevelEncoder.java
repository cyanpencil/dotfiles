// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HighLevelEncoder.java

package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.util.Arrays;

// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            ASCIIEncoder, Base256Encoder, C40Encoder, EdifactEncoder, 
//            Encoder, EncoderContext, SymbolInfo, SymbolShapeHint, 
//            TextEncoder, X12Encoder

public final class HighLevelEncoder
{

            private HighLevelEncoder()
            {
            }

            private static char randomize253State(char c, int i)
            {
/* 129*/        i = (i * 149) % 253 + 1;
/* 130*/        if((c += i) <= 254)
/* 131*/            return (char)c;
/* 131*/        else
/* 131*/            return (char)(c - 254);
            }

            public static String encodeHighLevel(String s)
            {
/* 142*/        return encodeHighLevel(s, SymbolShapeHint.FORCE_NONE, null, null);
            }

            public static String encodeHighLevel(String s, SymbolShapeHint symbolshapehint, Dimension dimension, Dimension dimension1)
            {
/* 161*/        Encoder aencoder[] = {
/* 161*/            new ASCIIEncoder(), new C40Encoder(), new TextEncoder(), new X12Encoder(), new EdifactEncoder(), new Base256Encoder()
                };
                EncoderContext encodercontext;
/* 166*/        (encodercontext = new EncoderContext(s)).setSymbolShape(symbolshapehint);
/* 168*/        encodercontext.setSizeConstraints(dimension, dimension1);
/* 170*/        if(s.startsWith("[)>\03605\035") && s.endsWith("\036\004"))
                {
/* 171*/            encodercontext.writeCodeword('\354');
/* 172*/            encodercontext.setSkipAtEnd(2);
/* 173*/            encodercontext.pos += 7;
                } else
/* 174*/        if(s.startsWith("[)>\03606\035") && s.endsWith("\036\004"))
                {
/* 175*/            encodercontext.writeCodeword('\355');
/* 176*/            encodercontext.setSkipAtEnd(2);
/* 177*/            encodercontext.pos += 7;
                }
/* 180*/        s = 0;
/* 181*/        do
                {
/* 181*/            if(!encodercontext.hasMoreCharacters())
/* 182*/                break;
/* 182*/            aencoder[s].encode(encodercontext);
/* 183*/            if(encodercontext.getNewEncoding() >= 0)
                    {
/* 184*/                s = encodercontext.getNewEncoding();
/* 185*/                encodercontext.resetEncoderSignal();
                    }
                } while(true);
/* 188*/        symbolshapehint = encodercontext.getCodewordCount();
/* 189*/        encodercontext.updateSymbolInfo();
/* 190*/        dimension = encodercontext.getSymbolInfo().getDataCapacity();
/* 191*/        if(symbolshapehint < dimension && s != 0 && s != 5)
/* 193*/            encodercontext.writeCodeword('\376');
/* 197*/        if((s = encodercontext.getCodewords()).length() < dimension)
/* 199*/            s.append('\201');
/* 201*/        for(; s.length() < dimension; s.append(randomize253State('\201', s.length() + 1)));
/* 205*/        return encodercontext.getCodewords().toString();
            }

            static int lookAheadTest(CharSequence charsequence, int i, int j)
            {
/* 209*/        if(i >= charsequence.length())
/* 210*/            return j;
                float af[];
/* 214*/        if(j == 0)
/* 215*/            af = (new float[] {
/* 215*/                0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.25F
                    });
/* 217*/        else
/* 217*/            (af = (new float[] {
/* 217*/                1.0F, 2.0F, 2.0F, 2.0F, 2.0F, 2.25F
                    }))[j] = 0.0F;
/* 221*/        j = 0;
                int ai[];
/* 224*/        do
                {
/* 224*/            do
                    {
/* 224*/                do
                        {
/* 224*/                    if(i + j == charsequence.length())
                            {
/* 226*/                        byte abyte0[] = new byte[6];
/* 227*/                        int ai1[] = new int[6];
/* 228*/                        int k = findMinimums(af, ai1, 0x7fffffff, abyte0);
/* 229*/                        int l = getMinimumCount(abyte0);
/* 231*/                        if(ai1[0] == k)
/* 232*/                            return 0;
/* 234*/                        if(l == 1 && abyte0[5] > 0)
/* 235*/                            return 5;
/* 237*/                        if(l == 1 && abyte0[4] > 0)
/* 238*/                            return 4;
/* 240*/                        if(l == 1 && abyte0[2] > 0)
/* 241*/                            return 2;
/* 243*/                        return l != 1 || abyte0[3] <= 0 ? 1 : 3;
                            }
/* 249*/                    char c = charsequence.charAt(i + j);
/* 250*/                    j++;
/* 253*/                    if(isDigit(c))
/* 254*/                        af[0] += 0.5D;
/* 255*/                    else
/* 255*/                    if(isExtendedASCII(c))
                            {
/* 256*/                        af[0] = (int)Math.ceil(af[0]);
/* 257*/                        af[0] += 2.0F;
                            } else
                            {
/* 259*/                        af[0] = (int)Math.ceil(af[0]);
/* 260*/                        af[0]++;
                            }
/* 264*/                    if(isNativeC40(c))
/* 265*/                        af[1] += 0.6666667F;
/* 266*/                    else
/* 266*/                    if(isExtendedASCII(c))
/* 267*/                        af[1] += 2.666667F;
/* 269*/                    else
/* 269*/                        af[1] += 1.333333F;
/* 273*/                    if(isNativeText(c))
/* 274*/                        af[2] += 0.6666667F;
/* 275*/                    else
/* 275*/                    if(isExtendedASCII(c))
/* 276*/                        af[2] += 2.666667F;
/* 278*/                    else
/* 278*/                        af[2] += 1.333333F;
/* 282*/                    if(isNativeX12(c))
/* 283*/                        af[3] += 0.6666667F;
/* 284*/                    else
/* 284*/                    if(isExtendedASCII(c))
/* 285*/                        af[3] += 4.333333F;
/* 287*/                    else
/* 287*/                        af[3] += 3.333333F;
/* 291*/                    if(isNativeEDIFACT(c))
/* 292*/                        af[4] += 0.75F;
/* 293*/                    else
/* 293*/                    if(isExtendedASCII(c))
/* 294*/                        af[4] += 4.25F;
/* 296*/                    else
/* 296*/                        af[4] += 3.25F;
/* 300*/                    if(isSpecialB256(c))
/* 301*/                        af[5] += 4F;
/* 303*/                    else
/* 303*/                        af[5]++;
                        } while(j < 4);
/* 308*/                ai = new int[6];
/* 309*/                byte abyte1[] = new byte[6];
/* 310*/                findMinimums(af, ai, 0x7fffffff, abyte1);
/* 311*/                int i1 = getMinimumCount(abyte1);
/* 313*/                if(ai[0] < ai[5] && ai[0] < ai[1] && ai[0] < ai[2] && ai[0] < ai[3] && ai[0] < ai[4])
/* 318*/                    return 0;
/* 320*/                if(ai[5] < ai[0] || abyte1[1] + abyte1[2] + abyte1[3] + abyte1[4] == 0)
/* 322*/                    return 5;
/* 324*/                if(i1 == 1 && abyte1[4] > 0)
/* 325*/                    return 4;
/* 327*/                if(i1 == 1 && abyte1[2] > 0)
/* 328*/                    return 2;
/* 330*/                if(i1 == 1 && abyte1[3] > 0)
/* 331*/                    return 3;
                    } while(ai[1] + 1 >= ai[0] || ai[1] + 1 >= ai[5] || ai[1] + 1 >= ai[4] || ai[1] + 1 >= ai[2]);
/* 337*/            if(ai[1] < ai[3])
/* 338*/                return 1;
                } while(ai[1] != ai[3]);
/* 341*/        i = i + j + 1;
/* 342*/        do
                {
/* 342*/            if(i >= charsequence.length())
/* 343*/                break;
/* 343*/            if(isX12TermSep(j = charsequence.charAt(i)))
/* 345*/                return 3;
/* 347*/            if(!isNativeX12(j))
/* 350*/                break;
/* 350*/            i++;
                } while(true);
/* 352*/        return 1;
            }

            private static int findMinimums(float af[], int ai[], int i, byte abyte0[])
            {
/* 360*/        Arrays.fill(abyte0, (byte)0);
/* 361*/        for(int j = 0; j < 6; j++)
                {
/* 362*/            ai[j] = (int)Math.ceil(af[j]);
/* 363*/            int k = ai[j];
/* 364*/            if(i > k)
                    {
/* 365*/                i = k;
/* 366*/                Arrays.fill(abyte0, (byte)0);
                    }
/* 368*/            if(i == k)
/* 369*/                abyte0[j]++;
                }

/* 373*/        return i;
            }

            private static int getMinimumCount(byte abyte0[])
            {
/* 377*/        int i = 0;
/* 378*/        for(int j = 0; j < 6; j++)
/* 379*/            i += abyte0[j];

/* 381*/        return i;
            }

            static boolean isDigit(char c)
            {
/* 385*/        return c >= '0' && c <= '9';
            }

            static boolean isExtendedASCII(char c)
            {
/* 389*/        return c >= '\200' && c <= '\377';
            }

            private static boolean isNativeC40(char c)
            {
/* 393*/        return c == ' ' || c >= '0' && c <= '9' || c >= 'A' && c <= 'Z';
            }

            private static boolean isNativeText(char c)
            {
/* 397*/        return c == ' ' || c >= '0' && c <= '9' || c >= 'a' && c <= 'z';
            }

            private static boolean isNativeX12(char c)
            {
/* 401*/        return isX12TermSep(c) || c == ' ' || c >= '0' && c <= '9' || c >= 'A' && c <= 'Z';
            }

            private static boolean isX12TermSep(char c)
            {
/* 405*/        return c == '\r' || c == '*' || c == '>';
            }

            private static boolean isNativeEDIFACT(char c)
            {
/* 411*/        return c >= ' ' && c <= '^';
            }

            private static boolean isSpecialB256(char c)
            {
/* 415*/        return false;
            }

            public static int determineConsecutiveDigitCount(CharSequence charsequence, int i)
            {
/* 426*/        int j = 0;
/* 427*/        int k = charsequence.length();
/* 428*/        int l = i;
/* 429*/        if(i < k)
                {
/* 430*/            i = charsequence.charAt(i);
/* 431*/            do
                    {
/* 431*/                if(!isDigit(i) || l >= k)
/* 432*/                    break;
/* 432*/                j++;
/* 433*/                if(++l < k)
/* 435*/                    i = charsequence.charAt(l);
                    } while(true);
                }
/* 439*/        return j;
            }

            static void illegalCharacter(char c)
            {
/* 443*/        String s = Integer.toHexString(c);
/* 444*/        s = (new StringBuilder()).append("0000".substring(0, 4 - s.length())).append(s).toString();
/* 445*/        throw new IllegalArgumentException((new StringBuilder("Illegal character: ")).append(c).append(" (0x").append(s).append(')').toString());
            }

            private static final char PAD = 129;
            static final char LATCH_TO_C40 = 230;
            static final char LATCH_TO_BASE256 = 231;
            static final char UPPER_SHIFT = 235;
            private static final char MACRO_05 = 236;
            private static final char MACRO_06 = 237;
            static final char LATCH_TO_ANSIX12 = 238;
            static final char LATCH_TO_TEXT = 239;
            static final char LATCH_TO_EDIFACT = 240;
            static final char C40_UNLATCH = 254;
            static final char X12_UNLATCH = 254;
            private static final String MACRO_05_HEADER = "[)>\03605\035";
            private static final String MACRO_06_HEADER = "[)>\03606\035";
            private static final String MACRO_TRAILER = "\036\004";
            static final int ASCII_ENCODATION = 0;
            static final int C40_ENCODATION = 1;
            static final int TEXT_ENCODATION = 2;
            static final int X12_ENCODATION = 3;
            static final int EDIFACT_ENCODATION = 4;
            static final int BASE256_ENCODATION = 5;
}
