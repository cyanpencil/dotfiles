// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PDF417ScanningDecoder.java

package com.google.zxing.pdf417.decoder;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.util.*;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            BarcodeMetadata, BarcodeValue, BoundingBox, Codeword, 
//            DecodedBitStreamParser, DetectionResult, DetectionResultColumn, DetectionResultRowIndicatorColumn, 
//            PDF417CodewordDecoder

public final class PDF417ScanningDecoder
{

            private PDF417ScanningDecoder()
            {
            }

            public static DecoderResult decode(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i, int j)
                throws NotFoundException, FormatException, ChecksumException
            {
/*  58*/        resultpoint1 = new BoundingBox(bitmatrix, resultpoint, resultpoint1, resultpoint2, resultpoint3);
/*  59*/        resultpoint3 = null;
/*  60*/        DetectionResultRowIndicatorColumn detectionresultrowindicatorcolumn = null;
/*  61*/        DetectionResult detectionresult = null;
/*  62*/        int k = 0;
/*  62*/        do
                {
/*  62*/            if(k >= 2)
/*  63*/                break;
/*  63*/            if(resultpoint != null)
/*  64*/                resultpoint3 = getRowIndicatorColumn(bitmatrix, resultpoint1, resultpoint, true, i, j);
/*  67*/            if(resultpoint2 != null)
/*  68*/                detectionresultrowindicatorcolumn = getRowIndicatorColumn(bitmatrix, resultpoint1, resultpoint2, false, i, j);
/*  71*/            if((detectionresult = merge(resultpoint3, detectionresultrowindicatorcolumn)) == null)
/*  73*/                throw NotFoundException.getNotFoundInstance();
/*  75*/            if(k == 0 && detectionresult.getBoundingBox() != null && (detectionresult.getBoundingBox().getMinY() < resultpoint1.getMinY() || detectionresult.getBoundingBox().getMaxY() > resultpoint1.getMaxY()))
                    {
/*  78*/                resultpoint1 = detectionresult.getBoundingBox();
                    } else
                    {
/*  80*/                detectionresult.setBoundingBox(resultpoint1);
/*  81*/                break;
                    }
/*  62*/            k++;
                } while(true);
/*  84*/        k = detectionresult.getBarcodeColumnCount() + 1;
/*  85*/        detectionresult.setDetectionResultColumn(0, resultpoint3);
/*  86*/        detectionresult.setDetectionResultColumn(k, detectionresultrowindicatorcolumn);
/*  88*/        resultpoint = resultpoint3 == null ? 0 : 1;
/*  89*/        for(resultpoint2 = 1; resultpoint2 <= k; resultpoint2++)
                {
/*  90*/            resultpoint3 = ((ResultPoint) (resultpoint == 0 ? ((ResultPoint) (k - resultpoint2)) : resultpoint2));
/*  91*/            if(detectionresult.getDetectionResultColumn(resultpoint3) != null)
/*  96*/                continue;
                    Object obj;
/*  96*/            if(resultpoint3 == 0 || resultpoint3 == k)
/*  97*/                obj = new DetectionResultRowIndicatorColumn(resultpoint1, resultpoint3 == 0);
/*  99*/            else
/*  99*/                obj = new DetectionResultColumn(resultpoint1);
/* 101*/            detectionresult.setDetectionResultColumn(resultpoint3, ((DetectionResultColumn) (obj)));
/* 103*/            int i1 = -1;
/* 105*/            for(int j1 = resultpoint1.getMinY(); j1 <= resultpoint1.getMaxY(); j1++)
                    {
                        int l;
/* 106*/                if((l = getStartColumn(detectionresult, resultpoint3, j1, resultpoint)) < 0 || l > resultpoint1.getMaxX())
                        {
/* 108*/                    if(i1 == -1)
/* 111*/                        continue;
/* 111*/                    l = i1;
                        }
                        Codeword codeword;
/* 113*/                if((codeword = detectCodeword(bitmatrix, resultpoint1.getMinX(), resultpoint1.getMaxX(), resultpoint, l, j1, i, j)) != null)
                        {
/* 116*/                    ((DetectionResultColumn) (obj)).setCodeword(j1, codeword);
/* 117*/                    i1 = l;
/* 118*/                    i = Math.min(i, codeword.getWidth());
/* 119*/                    j = Math.max(j, codeword.getWidth());
                        }
                    }

                }

/* 123*/        return createDecoderResult(detectionresult);
            }

            private static DetectionResult merge(DetectionResultRowIndicatorColumn detectionresultrowindicatorcolumn, DetectionResultRowIndicatorColumn detectionresultrowindicatorcolumn1)
                throws NotFoundException, FormatException
            {
/* 129*/        if(detectionresultrowindicatorcolumn == null && detectionresultrowindicatorcolumn1 == null)
/* 130*/            return null;
                BarcodeMetadata barcodemetadata;
/* 132*/        if((barcodemetadata = getBarcodeMetadata(detectionresultrowindicatorcolumn, detectionresultrowindicatorcolumn1)) == null)
                {
/* 134*/            return null;
                } else
                {
/* 136*/            detectionresultrowindicatorcolumn = BoundingBox.merge(adjustBoundingBox(detectionresultrowindicatorcolumn), adjustBoundingBox(detectionresultrowindicatorcolumn1));
/* 138*/            return new DetectionResult(barcodemetadata, detectionresultrowindicatorcolumn);
                }
            }

            private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn detectionresultrowindicatorcolumn)
                throws NotFoundException, FormatException
            {
/* 143*/        if(detectionresultrowindicatorcolumn == null)
/* 144*/            return null;
                int ai[];
/* 146*/        if((ai = detectionresultrowindicatorcolumn.getRowHeights()) == null)
/* 148*/            return null;
/* 150*/        int i = getMax(ai);
/* 151*/        int j = 0;
                Object aobj[];
/* 152*/        int k = (aobj = ai).length;
/* 152*/        int l = 0;
/* 152*/        do
                {
/* 152*/            if(l >= k)
/* 152*/                break;
/* 152*/            int j1 = aobj[l];
/* 153*/            j += i - j1;
/* 154*/            if(j1 > 0)
/* 152*/                break;
/* 152*/            l++;
                } while(true);
/* 158*/        aobj = detectionresultrowindicatorcolumn.getCodewords();
/* 159*/        for(k = 0; j > 0 && aobj[k] == null; k++)
/* 160*/            j--;

/* 162*/        k = 0;
/* 163*/        l = ai.length - 1;
/* 163*/        do
                {
/* 163*/            if(l < 0)
/* 164*/                break;
/* 164*/            k += i - ai[l];
/* 165*/            if(ai[l] > 0)
/* 163*/                break;
/* 163*/            l--;
                } while(true);
/* 169*/        for(int i1 = aobj.length - 1; k > 0 && aobj[i1] == null; i1--)
/* 170*/            k--;

/* 172*/        return detectionresultrowindicatorcolumn.getBoundingBox().addMissingRows(j, k, detectionresultrowindicatorcolumn.isLeft());
            }

            private static int getMax(int ai[])
            {
/* 177*/        int i = -1;
/* 178*/        int j = (ai = ai).length;
/* 178*/        for(int k = 0; k < j; k++)
                {
/* 178*/            int l = ai[k];
/* 179*/            i = Math.max(i, l);
                }

/* 181*/        return i;
            }

            private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn detectionresultrowindicatorcolumn, DetectionResultRowIndicatorColumn detectionresultrowindicatorcolumn1)
            {
/* 187*/        if(detectionresultrowindicatorcolumn == null || (detectionresultrowindicatorcolumn = detectionresultrowindicatorcolumn.getBarcodeMetadata()) == null)
/* 189*/            if(detectionresultrowindicatorcolumn1 == null)
/* 189*/                return null;
/* 189*/            else
/* 189*/                return detectionresultrowindicatorcolumn1.getBarcodeMetadata();
/* 192*/        if(detectionresultrowindicatorcolumn1 == null || (detectionresultrowindicatorcolumn1 = detectionresultrowindicatorcolumn1.getBarcodeMetadata()) == null)
/* 194*/            return detectionresultrowindicatorcolumn;
/* 197*/        if(detectionresultrowindicatorcolumn.getColumnCount() != detectionresultrowindicatorcolumn1.getColumnCount() && detectionresultrowindicatorcolumn.getErrorCorrectionLevel() != detectionresultrowindicatorcolumn1.getErrorCorrectionLevel() && detectionresultrowindicatorcolumn.getRowCount() != detectionresultrowindicatorcolumn1.getRowCount())
/* 200*/            return null;
/* 202*/        else
/* 202*/            return detectionresultrowindicatorcolumn;
            }

            private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix bitmatrix, BoundingBox boundingbox, ResultPoint resultpoint, boolean flag, int i, int j)
            {
/* 211*/        DetectionResultRowIndicatorColumn detectionresultrowindicatorcolumn = new DetectionResultRowIndicatorColumn(boundingbox, flag);
/* 213*/        for(int k = 0; k < 2; k++)
                {
/* 214*/            byte byte0 = ((byte)(k != 0 ? -1 : 1));
/* 215*/            int l = (int)resultpoint.getX();
/* 216*/            for(int i1 = (int)resultpoint.getY(); i1 <= boundingbox.getMaxY() && i1 >= boundingbox.getMinY(); i1 += byte0)
                    {
                        Codeword codeword;
/* 218*/                if((codeword = detectCodeword(bitmatrix, 0, bitmatrix.getWidth(), flag, l, i1, i, j)) == null)
/* 221*/                    continue;
/* 221*/                detectionresultrowindicatorcolumn.setCodeword(i1, codeword);
/* 222*/                if(flag)
/* 223*/                    l = codeword.getStartX();
/* 225*/                else
/* 225*/                    l = codeword.getEndX();
                    }

                }

/* 230*/        return detectionresultrowindicatorcolumn;
            }

            private static void adjustCodewordCount(DetectionResult detectionresult, BarcodeValue abarcodevalue[][])
                throws NotFoundException
            {
/* 235*/        int ai[] = abarcodevalue[0][1].getValue();
/* 236*/        detectionresult = detectionresult.getBarcodeColumnCount() * detectionresult.getBarcodeRowCount() - getNumberOfECCodeWords(detectionresult.getBarcodeECLevel());
/* 239*/        if(ai.length == 0)
/* 240*/            if(detectionresult <= 0 || detectionresult > 928)
                    {
/* 241*/                throw NotFoundException.getNotFoundInstance();
                    } else
                    {
/* 243*/                abarcodevalue[0][1].setValue(detectionresult);
/* 243*/                return;
                    }
/* 244*/        if(ai[0] != detectionresult)
/* 246*/            abarcodevalue[0][1].setValue(detectionresult);
            }

            private static DecoderResult createDecoderResult(DetectionResult detectionresult)
                throws FormatException, ChecksumException, NotFoundException
            {
/* 252*/        BarcodeValue abarcodevalue[][] = createBarcodeMatrix(detectionresult);
/* 253*/        adjustCodewordCount(detectionresult, abarcodevalue);
/* 254*/        ArrayList arraylist = new ArrayList();
/* 255*/        int ai[] = new int[detectionresult.getBarcodeRowCount() * detectionresult.getBarcodeColumnCount()];
/* 256*/        ArrayList arraylist1 = new ArrayList();
/* 257*/        ArrayList arraylist2 = new ArrayList();
/* 258*/        for(int i = 0; i < detectionresult.getBarcodeRowCount(); i++)
                {
/* 259*/            for(int j = 0; j < detectionresult.getBarcodeColumnCount(); j++)
                    {
/* 260*/                int ai2[] = abarcodevalue[i][j + 1].getValue();
/* 261*/                int l = i * detectionresult.getBarcodeColumnCount() + j;
/* 262*/                if(ai2.length == 0)
                        {
/* 263*/                    arraylist.add(Integer.valueOf(l));
/* 263*/                    continue;
                        }
/* 264*/                if(ai2.length == 1)
                        {
/* 265*/                    ai[l] = ai2[0];
                        } else
                        {
/* 267*/                    arraylist2.add(Integer.valueOf(l));
/* 268*/                    arraylist1.add(ai2);
                        }
                    }

                }

/* 272*/        int ai1[][] = new int[arraylist1.size()][];
/* 273*/        for(int k = 0; k < ai1.length; k++)
/* 274*/            ai1[k] = (int[])arraylist1.get(k);

/* 276*/        return createDecoderResultFromAmbiguousValues(detectionresult.getBarcodeECLevel(), ai, PDF417Common.toIntArray(arraylist), PDF417Common.toIntArray(arraylist2), ai1);
            }

            private static DecoderResult createDecoderResultFromAmbiguousValues(int i, int ai[], int ai1[], int ai2[], int ai3[][])
                throws FormatException, ChecksumException
            {
                int ai4[];
                int j;
/* 299*/        ai4 = new int[ai2.length];
/* 301*/        j = 100;
_L2:
/* 302*/        if(j-- <= 0)
/* 303*/            break MISSING_BLOCK_LABEL_138;
/* 303*/        for(int k = 0; k < ai4.length; k++)
/* 304*/            ai[ai2[k]] = ai3[k][ai4[k]];

/* 307*/        return decodeCodewords(ai, i, ai1);
/* 308*/        JVM INSTR pop ;
/* 311*/        if(ai4.length == 0)
/* 312*/            throw ChecksumException.getChecksumInstance();
/* 314*/        int l = 0;
/* 314*/        do
                {
/* 314*/            if(l >= ai4.length)
/* 315*/                continue; /* Loop/switch isn't completed */
/* 315*/            if(ai4[l] < ai3[l].length - 1)
                    {
/* 316*/                ai4[l]++;
/* 317*/                continue; /* Loop/switch isn't completed */
                    }
/* 319*/            ai4[l] = 0;
/* 320*/            if(l == ai4.length - 1)
/* 321*/                throw ChecksumException.getChecksumInstance();
/* 314*/            l++;
                } while(true);
/* 326*/        throw ChecksumException.getChecksumInstance();
/* 326*/        if(true) goto _L2; else goto _L1
_L1:
            }

            private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionresult)
            {
/* 330*/        BarcodeValue abarcodevalue[][] = new BarcodeValue[detectionresult.getBarcodeRowCount()][detectionresult.getBarcodeColumnCount() + 2];
/* 332*/        for(int i = 0; i < abarcodevalue.length; i++)
                {
/* 333*/            for(int k = 0; k < abarcodevalue[i].length; k++)
/* 334*/                abarcodevalue[i][k] = new BarcodeValue();

                }

/* 338*/        int j = -1;
                DetectionResultColumn adetectionresultcolumn[];
/* 339*/        detectionresult = (adetectionresultcolumn = detectionresult.getDetectionResultColumns()).length;
/* 339*/        for(int l = 0; l < detectionresult; l++)
                {
/* 339*/            DetectionResultColumn detectionresultcolumn = adetectionresultcolumn[l];
/* 340*/            j++;
/* 341*/            if(detectionresultcolumn == null)
/* 344*/                continue;
                    Codeword acodeword[];
/* 344*/            int i1 = (acodeword = detectionresultcolumn.getCodewords()).length;
/* 344*/            for(int j1 = 0; j1 < i1; j1++)
                    {
                        Codeword codeword;
/* 344*/                if((codeword = acodeword[j1]) != null && codeword.getRowNumber() != -1)
/* 348*/                    abarcodevalue[codeword.getRowNumber()][j].setValue(codeword.getValue());
                    }

                }

/* 351*/        return abarcodevalue;
            }

            private static boolean isValidBarcodeColumn(DetectionResult detectionresult, int i)
            {
/* 355*/        return i >= 0 && i <= detectionresult.getBarcodeColumnCount() + 1;
            }

            private static int getStartColumn(DetectionResult detectionresult, int i, int j, boolean flag)
            {
/* 362*/        byte byte0 = ((byte)(flag ? 1 : -1));
/* 363*/        Codeword codeword = null;
/* 364*/        if(isValidBarcodeColumn(detectionresult, i - byte0))
/* 365*/            codeword = detectionresult.getDetectionResultColumn(i - byte0).getCodeword(j);
/* 367*/        if(codeword != null)
/* 368*/            if(flag)
/* 368*/                return codeword.getEndX();
/* 368*/            else
/* 368*/                return codeword.getStartX();
/* 370*/        if((codeword = detectionresult.getDetectionResultColumn(i).getCodewordNearby(j)) != null)
/* 372*/            if(flag)
/* 372*/                return codeword.getStartX();
/* 372*/            else
/* 372*/                return codeword.getEndX();
/* 374*/        if(isValidBarcodeColumn(detectionresult, i - byte0))
/* 375*/            codeword = detectionresult.getDetectionResultColumn(i - byte0).getCodewordNearby(j);
/* 377*/        if(codeword != null)
/* 378*/            if(flag)
/* 378*/                return codeword.getEndX();
/* 378*/            else
/* 378*/                return codeword.getStartX();
/* 380*/        for(j = 0; isValidBarcodeColumn(detectionresult, i - byte0); j++)
                {
/* 383*/            i -= byte0;
                    Codeword acodeword[];
/* 384*/            int k = (acodeword = detectionresult.getDetectionResultColumn(i).getCodewords()).length;
/* 384*/            for(int l = 0; l < k; l++)
                    {
                        Codeword codeword1;
/* 384*/                if((codeword1 = acodeword[l]) != null)
/* 386*/                    return (flag ? codeword1.getEndX() : codeword1.getStartX()) + byte0 * j * (codeword1.getEndX() - codeword1.getStartX());
                    }

                }

/* 394*/        if(flag)
/* 394*/            return detectionresult.getBoundingBox().getMinX();
/* 394*/        else
/* 394*/            return detectionresult.getBoundingBox().getMaxX();
            }

            private static Codeword detectCodeword(BitMatrix bitmatrix, int i, int j, boolean flag, int k, int l, int i1, int j1)
            {
/* 405*/        k = adjustCodewordStartColumn(bitmatrix, i, j, flag, k, l);
/* 410*/        if((bitmatrix = getModuleBitCount(bitmatrix, i, j, flag, k, l)) == null)
/* 412*/            return null;
/* 415*/        j = PDF417Common.getBitCountSum(bitmatrix);
/* 416*/        if(flag)
                {
/* 417*/            i = k + j;
                } else
                {
/* 419*/            for(flag = false; flag < bitmatrix.length >> 1; flag++)
                    {
/* 420*/                l = bitmatrix[flag];
/* 421*/                bitmatrix[flag] = bitmatrix[bitmatrix.length - 1 - flag];
/* 422*/                bitmatrix[bitmatrix.length - 1 - flag] = l;
                    }

/* 424*/            i = k;
/* 425*/            k -= j;
                }
/* 441*/        if(!checkCodewordSkew(j, i1, j1))
/* 444*/            return null;
/* 447*/        if((l = PDF417Common.getCodeword(flag = PDF417CodewordDecoder.getDecodedValue(bitmatrix))) == -1)
/* 450*/            return null;
/* 452*/        else
/* 452*/            return new Codeword(k, i, getCodewordBucketNumber(flag), l);
            }

            private static int[] getModuleBitCount(BitMatrix bitmatrix, int i, int j, boolean flag, int k, int l)
            {
/* 461*/        k = k;
/* 462*/        int ai[] = new int[8];
/* 463*/        int i1 = 0;
/* 464*/        byte byte0 = ((byte)(flag ? 1 : -1));
/* 465*/        boolean flag1 = flag;
/* 466*/        while((flag && k < j || !flag && k >= i) && i1 < 8) 
/* 468*/            if(bitmatrix.get(k, l) == flag1)
                    {
/* 469*/                ai[i1]++;
/* 470*/                k += byte0;
                    } else
                    {
/* 472*/                i1++;
/* 473*/                flag1 = !flag1;
                    }
/* 476*/        if(i1 == 8 || (flag && k == j || !flag && k == i) && i1 == 7)
/* 478*/            return ai;
/* 480*/        else
/* 480*/            return null;
            }

            private static int getNumberOfECCodeWords(int i)
            {
/* 484*/        return 2 << i;
            }

            private static int adjustCodewordStartColumn(BitMatrix bitmatrix, int i, int j, boolean flag, int k, int l)
            {
/* 493*/        int i1 = k;
/* 494*/        int j1 = flag ? -1 : 1;
/* 496*/        for(int k1 = 0; k1 < 2; k1++)
                {
/* 497*/            for(; (flag && i1 >= i || !flag && i1 < j) && flag == bitmatrix.get(i1, l); i1 += j1)
/* 499*/                if(Math.abs(k - i1) > 2)
/* 500*/                    return k;

/* 504*/            j1 = -j1;
/* 505*/            flag = !flag;
                }

/* 507*/        return i1;
            }

            private static boolean checkCodewordSkew(int i, int j, int k)
            {
/* 511*/        return j - 2 <= i && i <= k + 2;
            }

            private static DecoderResult decodeCodewords(int ai[], int i, int ai1[])
                throws FormatException, ChecksumException
            {
/* 517*/        if(ai.length == 0)
                {
/* 518*/            throw FormatException.getFormatInstance();
                } else
                {
/* 521*/            int j = 1 << i + 1;
/* 522*/            int k = correctErrors(ai, ai1, j);
/* 523*/            verifyCodewordCount(ai, j);
/* 526*/            (ai = DecodedBitStreamParser.decode(ai, String.valueOf(i))).setErrorsCorrected(Integer.valueOf(k));
/* 528*/            ai.setErasures(Integer.valueOf(ai1.length));
/* 529*/            return ai;
                }
            }

            private static int correctErrors(int ai[], int ai1[], int i)
                throws ChecksumException
            {
/* 542*/        if(ai1 != null && ai1.length > i / 2 + 3 || i < 0 || i > 512)
/* 547*/            throw ChecksumException.getChecksumInstance();
/* 549*/        else
/* 549*/            return errorCorrection.decode(ai, i, ai1);
            }

            private static void verifyCodewordCount(int ai[], int i)
                throws FormatException
            {
/* 556*/        if(ai.length < 4)
/* 559*/            throw FormatException.getFormatInstance();
                int j;
/* 564*/        if((j = ai[0]) > ai.length)
/* 566*/            throw FormatException.getFormatInstance();
/* 568*/        if(j == 0)
                {
/* 570*/            if(i < ai.length)
                    {
/* 571*/                ai[0] = ai.length - i;
/* 571*/                return;
                    } else
                    {
/* 573*/                throw FormatException.getFormatInstance();
                    }
                } else
                {
/* 576*/            return;
                }
            }

            private static int[] getBitCountForCodeword(int i)
            {
/* 579*/        int ai[] = new int[8];
/* 580*/        int j = 0;
/* 581*/        int k = 7;
/* 583*/        do
                {
/* 583*/            if((i & 1) != j)
                    {
/* 584*/                j = i & 1;
/* 585*/                if(--k < 0)
/* 590*/                    break;
                    }
/* 590*/            ai[k]++;
/* 591*/            i >>= 1;
                } while(true);
/* 593*/        return ai;
            }

            private static int getCodewordBucketNumber(int i)
            {
/* 597*/        return getCodewordBucketNumber(getBitCountForCodeword(i));
            }

            private static int getCodewordBucketNumber(int ai[])
            {
/* 601*/        return ((((ai[0] - ai[2]) + ai[4]) - ai[6]) + 9) % 9;
            }

            public static String toString(BarcodeValue abarcodevalue[][])
            {
/* 605*/        Formatter formatter = new Formatter();
/* 606*/        for(int i = 0; i < abarcodevalue.length; i++)
                {
/* 607*/            formatter.format("Row %2d: ", new Object[] {
/* 607*/                Integer.valueOf(i)
                    });
/* 608*/            for(int j = 0; j < abarcodevalue[i].length; j++)
                    {
                        BarcodeValue barcodevalue;
/* 609*/                if((barcodevalue = abarcodevalue[i][j]).getValue().length == 0)
/* 611*/                    formatter.format("        ", null);
/* 613*/                else
/* 613*/                    formatter.format("%4d(%2d)", new Object[] {
/* 613*/                        Integer.valueOf(barcodevalue.getValue()[0]), barcodevalue.getConfidence(barcodevalue.getValue()[0])
                            });
                    }

/* 617*/            formatter.format("%n", new Object[0]);
                }

/* 619*/        String s = formatter.toString();
/* 620*/        formatter.close();
/* 621*/        return s;
            }

            private static final int CODEWORD_SKEW_SIZE = 2;
            private static final int MAX_ERRORS = 3;
            private static final int MAX_EC_CODEWORDS = 512;
            private static final ErrorCorrection errorCorrection = new ErrorCorrection();

}
