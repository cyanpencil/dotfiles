// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MatrixUtil.java

package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix, MaskUtil, QRCode

final class MatrixUtil
{

            private MatrixUtil()
            {
            }

            static void clearMatrix(ByteMatrix bytematrix)
            {
/* 127*/        bytematrix.clear((byte)-1);
            }

            static void buildMatrix(BitArray bitarray, ErrorCorrectionLevel errorcorrectionlevel, Version version, int i, ByteMatrix bytematrix)
                throws WriterException
            {
/* 137*/        clearMatrix(bytematrix);
/* 138*/        embedBasicPatterns(version, bytematrix);
/* 140*/        embedTypeInfo(errorcorrectionlevel, i, bytematrix);
/* 142*/        maybeEmbedVersionInfo(version, bytematrix);
/* 144*/        embedDataBits(bitarray, i, bytematrix);
            }

            static void embedBasicPatterns(Version version, ByteMatrix bytematrix)
                throws WriterException
            {
/* 155*/        embedPositionDetectionPatternsAndSeparators(bytematrix);
/* 157*/        embedDarkDotAtLeftBottomCorner(bytematrix);
/* 160*/        maybeEmbedPositionAdjustmentPatterns(version, bytematrix);
/* 162*/        embedTimingPatterns(bytematrix);
            }

            static void embedTypeInfo(ErrorCorrectionLevel errorcorrectionlevel, int i, ByteMatrix bytematrix)
                throws WriterException
            {
/* 168*/        BitArray bitarray = new BitArray();
/* 169*/        makeTypeInfoBits(errorcorrectionlevel, i, bitarray);
/* 171*/        for(errorcorrectionlevel = 0; errorcorrectionlevel < bitarray.getSize(); errorcorrectionlevel++)
                {
/* 174*/            i = bitarray.get(bitarray.getSize() - 1 - errorcorrectionlevel);
/* 177*/            int j = TYPE_INFO_COORDINATES[errorcorrectionlevel][0];
/* 178*/            int i1 = TYPE_INFO_COORDINATES[errorcorrectionlevel][1];
/* 179*/            bytematrix.set(j, i1, i);
/* 181*/            if(errorcorrectionlevel < 8)
                    {
/* 183*/                int k = bytematrix.getWidth() - errorcorrectionlevel - 1;
/* 185*/                bytematrix.set(k, 8, i);
                    } else
                    {
/* 189*/                int l = (bytematrix.getHeight() - 7) + (errorcorrectionlevel - 8);
/* 190*/                bytematrix.set(8, l, i);
                    }
                }

            }

            static void maybeEmbedVersionInfo(Version version, ByteMatrix bytematrix)
                throws WriterException
            {
/* 198*/        if(version.getVersionNumber() < 7)
/* 199*/            return;
/* 201*/        BitArray bitarray = new BitArray();
/* 202*/        makeVersionInfoBits(version, bitarray);
/* 204*/        version = 17;
/* 205*/        for(int i = 0; i < 6; i++)
                {
/* 206*/            for(int j = 0; j < 3; j++)
                    {
/* 208*/                boolean flag = bitarray.get(version);
/* 209*/                version--;
/* 211*/                bytematrix.set(i, (bytematrix.getHeight() - 11) + j, flag);
/* 213*/                bytematrix.set((bytematrix.getHeight() - 11) + j, i, flag);
                    }

                }

            }

            static void embedDataBits(BitArray bitarray, int i, ByteMatrix bytematrix)
                throws WriterException
            {
/* 223*/        int j = 0;
/* 224*/        int k = -1;
/* 226*/        int l = bytematrix.getWidth() - 1;
/* 227*/        int i1 = bytematrix.getHeight() - 1;
/* 228*/        for(; l > 0; l -= 2)
                {
/* 230*/            if(l == 6)
/* 231*/                l--;
/* 233*/            for(; i1 >= 0 && i1 < bytematrix.getHeight(); i1 += k)
                    {
/* 234*/                for(int j1 = 0; j1 < 2; j1++)
                        {
/* 235*/                    int k1 = l - j1;
/* 237*/                    if(!isEmpty(bytematrix.get(k1, i1)))
/* 241*/                        continue;
                            boolean flag;
/* 241*/                    if(j < bitarray.getSize())
                            {
/* 242*/                        flag = bitarray.get(j);
/* 243*/                        j++;
                            } else
                            {
/* 247*/                        flag = false;
                            }
/* 251*/                    if(i != -1 && MaskUtil.getDataMaskBit(i, k1, i1))
/* 252*/                        flag = !flag;
/* 254*/                    bytematrix.set(k1, i1, flag);
                        }

                    }

/* 258*/            k = -k;
/* 259*/            i1 += k;
                }

/* 263*/        if(j != bitarray.getSize())
/* 264*/            throw new WriterException((new StringBuilder("Not all bits consumed: ")).append(j).append('/').append(bitarray.getSize()).toString());
/* 266*/        else
/* 266*/            return;
            }

            static int findMSBSet(int i)
            {
                int j;
/* 274*/        for(j = 0; i != 0; j++)
/* 276*/            i >>>= 1;

/* 279*/        return j;
            }

            static int calculateBCHCode(int i, int j)
            {
/* 310*/        int k = findMSBSet(j);
/* 311*/        for(i <<= k - 1; findMSBSet(i) >= k; i ^= j << findMSBSet(i) - k);
/* 317*/        return i;
            }

            static void makeTypeInfoBits(ErrorCorrectionLevel errorcorrectionlevel, int i, BitArray bitarray)
                throws WriterException
            {
/* 325*/        if(!QRCode.isValidMaskPattern(i))
/* 326*/            throw new WriterException("Invalid mask pattern");
/* 328*/        errorcorrectionlevel = errorcorrectionlevel.getBits() << 3 | i;
/* 329*/        bitarray.appendBits(errorcorrectionlevel, 5);
/* 331*/        errorcorrectionlevel = calculateBCHCode(errorcorrectionlevel, 1335);
/* 332*/        bitarray.appendBits(errorcorrectionlevel, 10);
/* 334*/        (errorcorrectionlevel = new BitArray()).appendBits(21522, 15);
/* 336*/        bitarray.xor(errorcorrectionlevel);
/* 338*/        if(bitarray.getSize() != 15)
/* 339*/            throw new WriterException((new StringBuilder("should not happen but we got: ")).append(bitarray.getSize()).toString());
/* 341*/        else
/* 341*/            return;
            }

            static void makeVersionInfoBits(Version version, BitArray bitarray)
                throws WriterException
            {
/* 346*/        bitarray.appendBits(version.getVersionNumber(), 6);
/* 347*/        version = calculateBCHCode(version.getVersionNumber(), 7973);
/* 348*/        bitarray.appendBits(version, 12);
/* 350*/        if(bitarray.getSize() != 18)
/* 351*/            throw new WriterException((new StringBuilder("should not happen but we got: ")).append(bitarray.getSize()).toString());
/* 353*/        else
/* 353*/            return;
            }

            private static boolean isEmpty(int i)
            {
/* 357*/        return i == -1;
            }

            private static void embedTimingPatterns(ByteMatrix bytematrix)
            {
/* 363*/        for(int i = 8; i < bytematrix.getWidth() - 8; i++)
                {
/* 364*/            int j = (i + 1) % 2;
/* 366*/            if(isEmpty(bytematrix.get(i, 6)))
/* 367*/                bytematrix.set(i, 6, j);
/* 370*/            if(isEmpty(bytematrix.get(6, i)))
/* 371*/                bytematrix.set(6, i, j);
                }

            }

            private static void embedDarkDotAtLeftBottomCorner(ByteMatrix bytematrix)
                throws WriterException
            {
/* 378*/        if(bytematrix.get(8, bytematrix.getHeight() - 8) == 0)
                {
/* 379*/            throw new WriterException();
                } else
                {
/* 381*/            bytematrix.set(8, bytematrix.getHeight() - 8, 1);
/* 382*/            return;
                }
            }

            private static void embedHorizontalSeparationPattern(int i, int j, ByteMatrix bytematrix)
                throws WriterException
            {
/* 387*/        for(int k = 0; k < 8; k++)
                {
/* 388*/            if(!isEmpty(bytematrix.get(i + k, j)))
/* 389*/                throw new WriterException();
/* 391*/            bytematrix.set(i + k, j, 0);
                }

            }

            private static void embedVerticalSeparationPattern(int i, int j, ByteMatrix bytematrix)
                throws WriterException
            {
/* 398*/        for(int k = 0; k < 7; k++)
                {
/* 399*/            if(!isEmpty(bytematrix.get(i, j + k)))
/* 400*/                throw new WriterException();
/* 402*/            bytematrix.set(i, j + k, 0);
                }

            }

            private static void embedPositionAdjustmentPattern(int i, int j, ByteMatrix bytematrix)
            {
/* 410*/        for(int k = 0; k < 5; k++)
                {
/* 411*/            for(int l = 0; l < 5; l++)
/* 412*/                bytematrix.set(i + l, j + k, POSITION_ADJUSTMENT_PATTERN[k][l]);

                }

            }

            private static void embedPositionDetectionPattern(int i, int j, ByteMatrix bytematrix)
            {
/* 418*/        for(int k = 0; k < 7; k++)
                {
/* 419*/            for(int l = 0; l < 7; l++)
/* 420*/                bytematrix.set(i + l, j + k, POSITION_DETECTION_PATTERN[k][l]);

                }

            }

            private static void embedPositionDetectionPatternsAndSeparators(ByteMatrix bytematrix)
                throws WriterException
            {
/* 428*/        int i = POSITION_DETECTION_PATTERN[0].length;
/* 430*/        embedPositionDetectionPattern(0, 0, bytematrix);
/* 432*/        embedPositionDetectionPattern(bytematrix.getWidth() - i, 0, bytematrix);
/* 434*/        embedPositionDetectionPattern(0, bytematrix.getWidth() - i, bytematrix);
/* 439*/        embedHorizontalSeparationPattern(0, 7, bytematrix);
/* 441*/        embedHorizontalSeparationPattern(bytematrix.getWidth() - 8, 7, bytematrix);
/* 444*/        embedHorizontalSeparationPattern(0, bytematrix.getWidth() - 8, bytematrix);
/* 449*/        embedVerticalSeparationPattern(7, 0, bytematrix);
/* 451*/        embedVerticalSeparationPattern(bytematrix.getHeight() - 7 - 1, 0, bytematrix);
/* 453*/        embedVerticalSeparationPattern(7, bytematrix.getHeight() - 7, bytematrix);
            }

            private static void maybeEmbedPositionAdjustmentPatterns(Version version, ByteMatrix bytematrix)
            {
/* 459*/        if(version.getVersionNumber() < 2)
/* 460*/            return;
/* 462*/        version = version.getVersionNumber() - 1;
/* 463*/        int ai[] = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[version];
/* 464*/        version = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[version].length;
/* 465*/        for(int i = 0; i < version; i++)
                {
/* 466*/            for(int j = 0; j < version; j++)
                    {
/* 467*/                int k = ai[i];
                        int l;
/* 468*/                if((l = ai[j]) != -1 && k != -1 && isEmpty(bytematrix.get(l, k)))
/* 476*/                    embedPositionAdjustmentPattern(l - 2, k - 2, bytematrix);
                    }

                }

            }

            private static final int POSITION_DETECTION_PATTERN[][] = {
/*  34*/        {
/*  34*/            1, 1, 1, 1, 1, 1, 1
                }, {
/*  34*/            1, 0, 0, 0, 0, 0, 1
                }, {
/*  34*/            1, 0, 1, 1, 1, 0, 1
                }, {
/*  34*/            1, 0, 1, 1, 1, 0, 1
                }, {
/*  34*/            1, 0, 1, 1, 1, 0, 1
                }, {
/*  34*/            1, 0, 0, 0, 0, 0, 1
                }, {
/*  34*/            1, 1, 1, 1, 1, 1, 1
                }
            };
            private static final int POSITION_ADJUSTMENT_PATTERN[][] = {
/*  44*/        {
/*  44*/            1, 1, 1, 1, 1
                }, {
/*  44*/            1, 0, 0, 0, 1
                }, {
/*  44*/            1, 0, 1, 0, 1
                }, {
/*  44*/            1, 0, 0, 0, 1
                }, {
/*  44*/            1, 1, 1, 1, 1
                }
            };
            private static final int POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[][] = {
/*  53*/        {
/*  53*/            -1, -1, -1, -1, -1, -1, -1
                }, {
/*  53*/            6, 18, -1, -1, -1, -1, -1
                }, {
/*  53*/            6, 22, -1, -1, -1, -1, -1
                }, {
/*  53*/            6, 26, -1, -1, -1, -1, -1
                }, {
/*  53*/            6, 30, -1, -1, -1, -1, -1
                }, {
/*  53*/            6, 34, -1, -1, -1, -1, -1
                }, {
/*  53*/            6, 22, 38, -1, -1, -1, -1
                }, {
/*  53*/            6, 24, 42, -1, -1, -1, -1
                }, {
/*  53*/            6, 26, 46, -1, -1, -1, -1
                }, {
/*  53*/            6, 28, 50, -1, -1, -1, -1
                }, {
/*  53*/            6, 30, 54, -1, -1, -1, -1
                }, {
/*  53*/            6, 32, 58, -1, -1, -1, -1
                }, {
/*  53*/            6, 34, 62, -1, -1, -1, -1
                }, {
/*  53*/            6, 26, 46, 66, -1, -1, -1
                }, {
/*  53*/            6, 26, 48, 70, -1, -1, -1
                }, {
/*  53*/            6, 26, 50, 74, -1, -1, -1
                }, {
/*  53*/            6, 30, 54, 78, -1, -1, -1
                }, {
/*  53*/            6, 30, 56, 82, -1, -1, -1
                }, {
/*  53*/            6, 30, 58, 86, -1, -1, -1
                }, {
/*  53*/            6, 34, 62, 90, -1, -1, -1
                }, {
/*  53*/            6, 28, 50, 72, 94, -1, -1
                }, {
/*  53*/            6, 26, 50, 74, 98, -1, -1
                }, {
/*  53*/            6, 30, 54, 78, 102, -1, -1
                }, {
/*  53*/            6, 28, 54, 80, 106, -1, -1
                }, {
/*  53*/            6, 32, 58, 84, 110, -1, -1
                }, {
/*  53*/            6, 30, 58, 86, 114, -1, -1
                }, {
/*  53*/            6, 34, 62, 90, 118, -1, -1
                }, {
/*  53*/            6, 26, 50, 74, 98, 122, -1
                }, {
/*  53*/            6, 30, 54, 78, 102, 126, -1
                }, {
/*  53*/            6, 26, 52, 78, 104, 130, -1
                }, {
/*  53*/            6, 30, 56, 82, 108, 134, -1
                }, {
/*  53*/            6, 34, 60, 86, 112, 138, -1
                }, {
/*  53*/            6, 30, 58, 86, 114, 142, -1
                }, {
/*  53*/            6, 34, 62, 90, 118, 146, -1
                }, {
/*  53*/            6, 30, 54, 78, 102, 126, 150
                }, {
/*  53*/            6, 24, 50, 76, 102, 128, 154
                }, {
/*  53*/            6, 28, 54, 80, 106, 132, 158
                }, {
/*  53*/            6, 32, 58, 84, 110, 136, 162
                }, {
/*  53*/            6, 26, 54, 82, 110, 138, 166
                }, {
/*  53*/            6, 30, 58, 86, 114, 142, 170
                }
            };
            private static final int TYPE_INFO_COORDINATES[][] = {
/*  97*/        {
/*  97*/            8, 0
                }, {
/*  97*/            8, 1
                }, {
/*  97*/            8, 2
                }, {
/*  97*/            8, 3
                }, {
/*  97*/            8, 4
                }, {
/*  97*/            8, 5
                }, {
/*  97*/            8, 7
                }, {
/*  97*/            8, 8
                }, {
/*  97*/            7, 8
                }, {
/*  97*/            5, 8
                }, {
/*  97*/            4, 8
                }, {
/*  97*/            3, 8
                }, {
/*  97*/            2, 8
                }, {
/*  97*/            1, 8
                }, {
/*  97*/            0, 8
                }
            };
            private static final int VERSION_INFO_POLY = 7973;
            private static final int TYPE_INFO_POLY = 1335;
            private static final int TYPE_INFO_MASK_PATTERN = 21522;

}
