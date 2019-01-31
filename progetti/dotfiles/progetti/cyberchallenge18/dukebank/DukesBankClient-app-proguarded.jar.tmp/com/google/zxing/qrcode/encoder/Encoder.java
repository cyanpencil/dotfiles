// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Encoder.java

package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.*;
import java.io.UnsupportedEncodingException;
import java.util.*;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            BlockPair, ByteMatrix, MaskUtil, MatrixUtil, 
//            QRCode

public final class Encoder
{

            private Encoder()
            {
            }

            private static int calculateMaskPenalty(ByteMatrix bytematrix)
            {
/*  58*/        return MaskUtil.applyMaskPenaltyRule1(bytematrix) + MaskUtil.applyMaskPenaltyRule2(bytematrix) + MaskUtil.applyMaskPenaltyRule3(bytematrix) + MaskUtil.applyMaskPenaltyRule4(bytematrix);
            }

            public static QRCode encode(String s, ErrorCorrectionLevel errorcorrectionlevel)
                throws WriterException
            {
/*  76*/        return encode(s, errorcorrectionlevel, null);
            }

            public static QRCode encode(String s, ErrorCorrectionLevel errorcorrectionlevel, Map map)
                throws WriterException
            {
/*  84*/        if((map = map != null ? ((Map) ((String)map.get(EncodeHintType.CHARACTER_SET))) : null) == null)
/*  86*/            map = "ISO-8859-1";
/*  91*/        Object obj = chooseMode(s, map);
/*  95*/        Object obj1 = new BitArray();
                Object obj2;
/*  98*/        if(obj == Mode.BYTE && !"ISO-8859-1".equals(map) && (obj2 = CharacterSetECI.getCharacterSetECIByName(map)) != null)
/* 101*/            appendECI(((CharacterSetECI) (obj2)), ((BitArray) (obj1)));
/* 106*/        appendModeInfo(((Mode) (obj)), ((BitArray) (obj1)));
/* 110*/        obj2 = new BitArray();
/* 111*/        appendBytes(s, ((Mode) (obj)), ((BitArray) (obj2)), map);
/* 117*/        map = chooseVersion(map = ((BitArray) (obj1)).getSize() + ((Mode) (obj)).getCharacterCountBits(Version.getVersionForNumber(1)) + ((BitArray) (obj2)).getSize(), errorcorrectionlevel);
/* 124*/        map = chooseVersion(map = ((BitArray) (obj1)).getSize() + ((Mode) (obj)).getCharacterCountBits(map) + ((BitArray) (obj2)).getSize(), errorcorrectionlevel);
                BitArray bitarray;
/* 129*/        (bitarray = new BitArray()).appendBitArray(((BitArray) (obj1)));
/* 132*/        appendLengthInfo(s = obj != Mode.BYTE ? ((String) (s.length())) : ((String) (((BitArray) (obj2)).getSizeInBytes())), map, ((Mode) (obj)), bitarray);
/* 135*/        bitarray.appendBitArray(((BitArray) (obj2)));
/* 137*/        s = map.getECBlocksForLevel(errorcorrectionlevel);
                int j;
/* 138*/        terminateBits(j = map.getTotalCodewords() - s.getTotalECCodewords(), bitarray);
/* 144*/        s = interleaveWithECBytes(bitarray, map.getTotalCodewords(), j, s.getNumBlocks());
/* 149*/        (j = new QRCode()).setECLevel(errorcorrectionlevel);
/* 152*/        j.setMode(((Mode) (obj)));
/* 153*/        j.setVersion(map);
/* 156*/        int i = map.getDimensionForVersion();
/* 157*/        i = new ByteMatrix(i, i);
/* 158*/        int k = chooseMaskPattern(s, errorcorrectionlevel, map, i);
/* 159*/        j.setMaskPattern(k);
/* 162*/        MatrixUtil.buildMatrix(s, errorcorrectionlevel, map, k, i);
/* 163*/        j.setMatrix(i);
/* 165*/        return j;
            }

            static int getAlphanumericCode(int i)
            {
/* 173*/        if(i < ALPHANUMERIC_TABLE.length)
/* 174*/            return ALPHANUMERIC_TABLE[i];
/* 176*/        else
/* 176*/            return -1;
            }

            public static Mode chooseMode(String s)
            {
/* 180*/        return chooseMode(s, null);
            }

            private static Mode chooseMode(String s, String s1)
            {
/* 188*/        if("Shift_JIS".equals(s1))
/* 190*/            if(isOnlyDoubleByteKanji(s))
/* 190*/                return Mode.KANJI;
/* 190*/            else
/* 190*/                return Mode.BYTE;
/* 192*/        s1 = 0;
/* 193*/        boolean flag = false;
/* 194*/        for(int i = 0; i < s.length(); i++)
                {
                    char c;
/* 195*/            if((c = s.charAt(i)) >= '0' && c <= '9')
                    {
/* 197*/                s1 = 1;
/* 197*/                continue;
                    }
/* 198*/            if(getAlphanumericCode(c) != -1)
/* 199*/                flag = true;
/* 201*/            else
/* 201*/                return Mode.BYTE;
                }

/* 204*/        if(flag)
/* 205*/            return Mode.ALPHANUMERIC;
/* 207*/        if(s1 != 0)
/* 208*/            return Mode.NUMERIC;
/* 210*/        else
/* 210*/            return Mode.BYTE;
            }

            private static boolean isOnlyDoubleByteKanji(String s)
            {
/* 216*/        try
                {
/* 216*/            s = s.getBytes("Shift_JIS");
                }
/* 217*/        catch(UnsupportedEncodingException _ex)
                {
/* 218*/            return false;
                }
                int i;
/* 220*/        if((i = s.length) % 2 != 0)
/* 222*/            return false;
/* 224*/        for(int j = 0; j < i; j += 2)
                {
                    int k;
/* 225*/            if(((k = s[j] & 0xff) < 129 || k > 159) && (k < 224 || k > 235))
/* 227*/                return false;
                }

/* 230*/        return true;
            }

            private static int chooseMaskPattern(BitArray bitarray, ErrorCorrectionLevel errorcorrectionlevel, Version version, ByteMatrix bytematrix)
                throws WriterException
            {
/* 238*/        int i = 0x7fffffff;
/* 239*/        int j = -1;
/* 241*/        for(int k = 0; k < 8; k++)
                {
/* 242*/            MatrixUtil.buildMatrix(bitarray, errorcorrectionlevel, version, k, bytematrix);
                    int l;
/* 243*/            if((l = calculateMaskPenalty(bytematrix)) < i)
                    {
/* 245*/                i = l;
/* 246*/                j = k;
                    }
                }

/* 249*/        return j;
            }

            private static Version chooseVersion(int i, ErrorCorrectionLevel errorcorrectionlevel)
                throws WriterException
            {
/* 254*/        for(int j = 1; j <= 40; j++)
                {
                    Version version;
/* 255*/            int k = (version = Version.getVersionForNumber(j)).getTotalCodewords();
                    com.google.zxing.qrcode.decoder.Version.ECBlocks ecblocks;
/* 259*/            int l = (ecblocks = version.getECBlocksForLevel(errorcorrectionlevel)).getTotalECCodewords();
/* 262*/            k -= l;
/* 263*/            l = (i + 7) / 8;
/* 264*/            if(k >= l)
/* 265*/                return version;
                }

/* 268*/        throw new WriterException("Data too big");
            }

            static void terminateBits(int i, BitArray bitarray)
                throws WriterException
            {
/* 275*/        int j = i << 3;
/* 276*/        if(bitarray.getSize() > j)
/* 277*/            throw new WriterException((new StringBuilder("data bits cannot fit in the QR Code")).append(bitarray.getSize()).append(" > ").append(j).toString());
/* 280*/        for(int k = 0; k < 4 && bitarray.getSize() < j; k++)
/* 281*/            bitarray.appendBit(false);

                int l;
/* 285*/        if((l = bitarray.getSize() & 7) > 0)
/* 287*/            for(l = l; l < 8; l++)
/* 288*/                bitarray.appendBit(false);

/* 292*/        l = i - bitarray.getSizeInBytes();
/* 293*/        for(i = 0; i < l; i++)
/* 294*/            bitarray.appendBits((i & 1) != 0 ? 17 : 236, 8);

/* 296*/        if(bitarray.getSize() != j)
/* 297*/            throw new WriterException("Bits size does not equal capacity");
/* 299*/        else
/* 299*/            return;
            }

            static void getNumDataBytesAndNumECBytesForBlockID(int i, int j, int k, int l, int ai[], int ai1[])
                throws WriterException
            {
/* 312*/        if(l >= k)
/* 313*/            throw new WriterException("Block ID too large");
/* 316*/        int i1 = i % k;
/* 318*/        int j1 = k - i1;
                int k1;
/* 320*/        int l1 = (k1 = i / k) + 1;
/* 324*/        int i2 = (j /= k) + 1;
/* 328*/        k1 -= j;
/* 330*/        l1 -= i2;
/* 333*/        if(k1 != l1)
/* 334*/            throw new WriterException("EC bytes mismatch");
/* 337*/        if(k != j1 + i1)
/* 338*/            throw new WriterException("RS blocks mismatch");
/* 341*/        if(i != (j + k1) * j1 + (i2 + l1) * i1)
/* 346*/            throw new WriterException("Total bytes mismatch");
/* 349*/        if(l < j1)
                {
/* 350*/            ai[0] = j;
/* 351*/            ai1[0] = k1;
/* 351*/            return;
                } else
                {
/* 353*/            ai[0] = i2;
/* 354*/            ai1[0] = l1;
/* 356*/            return;
                }
            }

            static BitArray interleaveWithECBytes(BitArray bitarray, int i, int j, int k)
                throws WriterException
            {
/* 368*/        if(bitarray.getSizeInBytes() != j)
/* 369*/            throw new WriterException("Number of bits and data bytes does not match");
/* 374*/        int l = 0;
/* 375*/        int i1 = 0;
/* 376*/        int j1 = 0;
/* 379*/        ArrayList arraylist = new ArrayList(k);
/* 381*/        for(int k1 = 0; k1 < k; k1++)
                {
/* 382*/            int ai[] = new int[1];
/* 383*/            int ai1[] = new int[1];
/* 384*/            getNumDataBytesAndNumECBytesForBlockID(i, j, k, k1, ai, ai1);
                    int j2;
/* 388*/            byte abyte0[] = new byte[j2 = ai[0]];
/* 390*/            bitarray.toBytes(l * 8, abyte0, 0, j2);
/* 391*/            ai1 = generateECBytes(abyte0, ai1[0]);
/* 392*/            arraylist.add(new BlockPair(abyte0, ai1));
/* 394*/            i1 = Math.max(i1, j2);
/* 395*/            j1 = Math.max(j1, ai1.length);
/* 396*/            l += ai[0];
                }

/* 398*/        if(j != l)
/* 399*/            throw new WriterException("Data bytes does not match offset");
/* 402*/        BitArray bitarray1 = new BitArray();
/* 405*/label0:
/* 405*/        for(int l1 = 0; l1 < i1; l1++)
                {
/* 406*/            Iterator iterator = arraylist.iterator();
/* 406*/            do
                    {
/* 406*/                if(!iterator.hasNext())
/* 406*/                    continue label0;
                        BlockPair blockpair;
/* 406*/                byte abyte1[] = (blockpair = (BlockPair)iterator.next()).getDataBytes();
/* 408*/                if(l1 < abyte1.length)
/* 409*/                    bitarray1.appendBits(abyte1[l1], 8);
                    } while(true);
                }

/* 414*/label1:
/* 414*/        for(int i2 = 0; i2 < j1; i2++)
                {
/* 415*/            Iterator iterator1 = arraylist.iterator();
/* 415*/            do
                    {
/* 415*/                if(!iterator1.hasNext())
/* 415*/                    continue label1;
                        BlockPair blockpair1;
/* 415*/                byte abyte2[] = (blockpair1 = (BlockPair)iterator1.next()).getErrorCorrectionBytes();
/* 417*/                if(i2 < abyte2.length)
/* 418*/                    bitarray1.appendBits(abyte2[i2], 8);
                    } while(true);
                }

/* 422*/        if(i != bitarray1.getSizeInBytes())
/* 423*/            throw new WriterException((new StringBuilder("Interleaving error: ")).append(i).append(" and ").append(bitarray1.getSizeInBytes()).append(" differ.").toString());
/* 427*/        else
/* 427*/            return bitarray1;
            }

            static byte[] generateECBytes(byte abyte0[], int i)
            {
                int j;
/* 431*/        int ai[] = new int[(j = abyte0.length) + i];
/* 433*/        for(int k = 0; k < j; k++)
/* 434*/            ai[k] = abyte0[k] & 0xff;

/* 436*/        (new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256)).encode(ai, i);
/* 438*/        byte abyte1[] = new byte[i];
/* 439*/        for(abyte0 = 0; abyte0 < i; abyte0++)
/* 440*/            abyte1[abyte0] = (byte)ai[j + abyte0];

/* 442*/        return abyte1;
            }

            static void appendModeInfo(Mode mode, BitArray bitarray)
            {
/* 449*/        bitarray.appendBits(mode.getBits(), 4);
            }

            static void appendLengthInfo(int i, Version version, Mode mode, BitArray bitarray)
                throws WriterException
            {
/* 457*/        version = mode.getCharacterCountBits(version);
/* 458*/        if(i >= 1 << version)
                {
/* 459*/            throw new WriterException((new StringBuilder()).append(i).append(" is bigger than ").append((1 << version) - 1).toString());
                } else
                {
/* 461*/            bitarray.appendBits(i, version);
/* 462*/            return;
                }
            }

            static void appendBytes(String s, Mode mode, BitArray bitarray, String s1)
                throws WriterException
            {
        static class _cls1
        {

                    static final int $SwitchMap$com$google$zxing$qrcode$decoder$Mode[];

                    static 
                    {
/* 471*/                $SwitchMap$com$google$zxing$qrcode$decoder$Mode = new int[Mode.values().length];
/* 471*/                try
                        {
/* 471*/                    $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.NUMERIC.ordinal()] = 1;
                        }
/* 471*/                catch(NoSuchFieldError _ex) { }
/* 471*/                try
                        {
/* 471*/                    $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ALPHANUMERIC.ordinal()] = 2;
                        }
/* 471*/                catch(NoSuchFieldError _ex) { }
/* 471*/                try
                        {
/* 471*/                    $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.BYTE.ordinal()] = 3;
                        }
/* 471*/                catch(NoSuchFieldError _ex) { }
/* 471*/                try
                        {
/* 471*/                    $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.KANJI.ordinal()] = 4;
                        }
/* 471*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/* 471*/        switch(_cls1..SwitchMap.com.google.zxing.qrcode.decoder.Mode[mode.ordinal()])
                {
/* 473*/        case 1: // '\001'
/* 473*/            appendNumericBytes(s, bitarray);
/* 474*/            return;

/* 476*/        case 2: // '\002'
/* 476*/            appendAlphanumericBytes(s, bitarray);
/* 477*/            return;

/* 479*/        case 3: // '\003'
/* 479*/            append8BitBytes(s, bitarray, s1);
/* 480*/            return;

/* 482*/        case 4: // '\004'
/* 482*/            appendKanjiBytes(s, bitarray);
/* 483*/            return;
                }
/* 485*/        throw new WriterException((new StringBuilder("Invalid mode: ")).append(mode).toString());
            }

            static void appendNumericBytes(CharSequence charsequence, BitArray bitarray)
            {
/* 490*/        int i = charsequence.length();
/* 491*/        for(int j = 0; j < i;)
                {
/* 493*/            int k = charsequence.charAt(j) - 48;
/* 494*/            if(j + 2 < i)
                    {
/* 496*/                int l = charsequence.charAt(j + 1) - 48;
/* 497*/                int j1 = charsequence.charAt(j + 2) - 48;
/* 498*/                bitarray.appendBits(k * 100 + l * 10 + j1, 10);
/* 499*/                j += 3;
                    } else
/* 500*/            if(j + 1 < i)
                    {
/* 502*/                int i1 = charsequence.charAt(j + 1) - 48;
/* 503*/                bitarray.appendBits(k * 10 + i1, 7);
/* 504*/                j += 2;
                    } else
                    {
/* 507*/                bitarray.appendBits(k, 4);
/* 508*/                j++;
                    }
                }

            }

            static void appendAlphanumericBytes(CharSequence charsequence, BitArray bitarray)
                throws WriterException
            {
/* 514*/        int i = charsequence.length();
/* 515*/        for(int j = 0; j < i;)
                {
                    int k;
/* 517*/            if((k = getAlphanumericCode(charsequence.charAt(j))) == -1)
/* 519*/                throw new WriterException();
/* 521*/            if(j + 1 < i)
                    {
                        int l;
/* 522*/                if((l = getAlphanumericCode(charsequence.charAt(j + 1))) == -1)
/* 524*/                    throw new WriterException();
/* 527*/                bitarray.appendBits(k * 45 + l, 11);
/* 528*/                j += 2;
                    } else
                    {
/* 531*/                bitarray.appendBits(k, 6);
/* 532*/                j++;
                    }
                }

            }

            static void append8BitBytes(String s, BitArray bitarray, String s1)
                throws WriterException
            {
/* 541*/        try
                {
/* 541*/            s = s.getBytes(s1);
                }
                // Misplaced declaration of an exception variable
/* 542*/        catch(String s)
                {
/* 543*/            throw new WriterException(s);
                }
/* 545*/        s1 = (s = s).length;
/* 545*/        for(int i = 0; i < s1; i++)
                {
/* 545*/            byte byte0 = s[i];
/* 546*/            bitarray.appendBits(byte0, 8);
                }

            }

            static void appendKanjiBytes(String s, BitArray bitarray)
                throws WriterException
            {
/* 553*/        try
                {
/* 553*/            s = s.getBytes("Shift_JIS");
                }
/* 554*/        catch(UnsupportedEncodingException unsupportedencodingexception)
                {
/* 555*/            throw new WriterException(unsupportedencodingexception);
                }
/* 557*/        int i = s.length;
/* 558*/        for(int j = 0; j < i; j += 2)
                {
/* 559*/            int k = s[j] & 0xff;
/* 560*/            int l = s[j + 1] & 0xff;
/* 561*/            k = k << 8 | l;
/* 562*/            l = -1;
/* 563*/            if(k >= 33088 && k <= 40956)
/* 564*/                l = k - 33088;
/* 565*/            else
/* 565*/            if(k >= 57408 && k <= 60351)
/* 566*/                l = k - 49472;
/* 568*/            if(l == -1)
/* 569*/                throw new WriterException("Invalid byte sequence");
/* 571*/            k = (l >> 8) * 192 + (l & 0xff);
/* 572*/            bitarray.appendBits(k, 13);
                }

            }

            private static void appendECI(CharacterSetECI characterseteci, BitArray bitarray)
            {
/* 577*/        bitarray.appendBits(Mode.ECI.getBits(), 4);
/* 579*/        bitarray.appendBits(characterseteci.getValue(), 8);
            }

            private static final int ALPHANUMERIC_TABLE[] = {
/*  41*/        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/*  41*/        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/*  41*/        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
/*  41*/        -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, 
/*  41*/        -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 
/*  41*/        2, 3, 4, 5, 6, 7, 8, 9, 44, -1, 
/*  41*/        -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 
/*  41*/        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
/*  41*/        25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 
/*  41*/        35, -1, -1, -1, -1, -1
            };
            static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

}
